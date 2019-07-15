package com.example.empleados;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class DeleteActivity extends AppCompatActivity {

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        final EditText input = findViewById(R.id.idText);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String temp = input.getText().toString();
                id = Integer.parseInt(temp);
                AlertDialog.Builder alert = new AlertDialog.Builder(DeleteActivity.this);
                alert.setMessage("Are you sure you want to delete employee '"+id+"'?");
                alert.setPositiveButton("Yes",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id2){
                        new sendDelete(id).execute();
                    }
                })
                .setNegativeButton("No", null)
                .show();
            }
        });


    }

    private class sendDelete extends AsyncTask<Integer, Void, Void>{

        private int id;
        //AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Boolean deleted = false;


        public sendDelete(int id){
            super();
            this.id = id;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            HttpURLConnection connection;

            try {

                URL url = new URL("http://apollo.artlabs.xyz:8080/empleados/"+id+"/delete");
                System.out.println(url);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("DELETE");
                connection.setDoOutput(true);
                System.out.println("DELETED CODE : " + connection.getResponseCode());

                if(connection.getResponseCode()>=400){
                    deleted= false;
                }else if(connection.getResponseCode()==200){
                    deleted = true;
                }

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            AlertDialog.Builder alert = new AlertDialog.Builder(DeleteActivity.this);
            if(deleted == true){
                alert.setMessage("Employee deleted successfully");
            }else{
                alert.setMessage("There was an error.");
            }
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                    startActivity(getIntent());
                }
            });
            alert.show();

        }
    }
}
