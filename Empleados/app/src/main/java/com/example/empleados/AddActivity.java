package com.example.empleados;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AddActivity extends AppCompatActivity {

    Empleado empleado = new Empleado();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        final EditText idText = findViewById(R.id.idField);
        final EditText nameText = findViewById(R.id.firstField);
        final EditText lastText = findViewById(R.id.lastField);
        final Spinner genderText = findViewById(R.id.genderField);
        final EditText birthText = findViewById(R.id.birthField);
        final EditText hireText = findViewById(R.id.hireField);
        Button submit = findViewById(R.id.button);
        String[] items = {"M", "F"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        genderText.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                empleado.setEmp_no(Integer.parseInt(idText.getText().toString()));
                empleado.setFirst_name(nameText.getText().toString());
                empleado.setLast_name(lastText.getText().toString());
                empleado.setGender(genderText.getSelectedItem().toString());
                if(empleado.setBirth_date(birthText.getText().toString())==true ||
                empleado.setHire_date(hireText.getText().toString())==true ){
                    new sendPost().execute();
                }else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(AddActivity.this);
                    alert.setMessage("Invalid date format");
                    alert.setPositiveButton("Ok", null);
                }

            }
        });

    }

    private class sendPost extends AsyncTask<Void, Void, Void>{

        Boolean success = false;

        @Override
        protected Void doInBackground(Void... voids) {


            Gson gson = new Gson();
            String json = gson.toJson(empleado);

            System.out.println(json);

            try{
                HttpURLConnection connection;
                URL url = new URL("http://apollo.artlabs.xyz:8080/empleados/add");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setRequestProperty("Accept", "application/json");

                OutputStream os = connection.getOutputStream();
                byte[] input = json.getBytes("utf-8");
                os.write(input, 0, input.length);
                os.close();

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());

            }catch(Exception e) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            AlertDialog.Builder alert = new AlertDialog.Builder(AddActivity.this);
            if(success == true){
                alert.setMessage("Employee added successfully");
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
