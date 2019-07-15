package com.example.empleados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Button;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.ResponseCache;
import java.net.URL;
import java.util.ArrayList;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;


public class GetActivity extends AppCompatActivity {

    TextView view;
    ScrollView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        list = findViewById(R.id.scrollBox);
        view = findViewById(R.id.textView);

        new sendGet().execute();
    }

    private class sendGet extends AsyncTask<Void, Void, Void>{

        List<Empleado> empleados = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                URL obj = new URL("http://apollo.artlabs.xyz:8080/empleados");
                HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                Gson gson = new Gson();
                Type listType = new TypeToken<List<Empleado>>(){}.getType();
                empleados = gson.fromJson(response.toString(), listType);

            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            TableLayout table = findViewById(R.id.table);
//            TableRow tableRow = new TableRow(getApplicationContext());
//            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
//            tableRow.setLayoutParams(layoutParams);
//            TextView textView = new TextView(getApplicationContext());
//            line.setText(empleados.get(0).toString());
//            tableRow.addView(line);
//            table.addView(tableRow);

            for(int i=0; i<empleados.size(); i++) {
                TableRow tableRow = new TableRow(getApplicationContext());
                TextView textView = new TextView(getApplicationContext());
                textView.setText(empleados.get(i).toString());
                tableRow.addView(textView);
                table.addView(tableRow);
            }

//            view.setText(empleados.get(0).toString());
//            for(int i=0; i<empleados.size(); i++) {
//                list.addView(empleados.get(i).toString());
//            }

            super.onPostExecute(aVoid);
        }
    }

}
