package com.example.empleados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeAct();
    }

    public void changeAct(){
        Button buttonGet = findViewById(R.id.buttonGet);
        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityGet = new Intent(getApplicationContext(), GetActivity.class);
                startActivity(activityGet);

            }
        });

        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityGet = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(activityGet);

            }
        });


        Button buttonDel = findViewById(R.id.buttonDelete);
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityDel = new Intent(getApplicationContext(), DeleteActivity.class);
                startActivity(activityDel);
            }
        });
    }


}
