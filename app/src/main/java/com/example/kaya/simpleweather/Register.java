package com.example.kaya.simpleweather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button register = (Button) findViewById(R.id.btnrgstr);
        final EditText name = (EditText) findViewById(R.id.txtNameSurname);
        final EditText email = (EditText) findViewById(R.id.txtEmail);
        final EditText age = (EditText) findViewById(R.id.txtAge);
        final EditText password = (EditText) findViewById(R.id.txtPassword);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().isEmpty()) {
                    Toast.makeText(Register.this, "Email cannot be empty!", Toast.LENGTH_LONG).show();
                } else {
                    SharedPreferences preference = getSharedPreferences("Login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preference.edit();
                    String strEmail = email.getText().toString();
                    strEmail = strEmail.toLowerCase();
                    editor.putString("name", name.getText().toString());
                    editor.putString("email", strEmail);
                    editor.putString("age", age.getText().toString());
                    editor.putString("password", password.getText().toString());

                    editor.commit();


                    Intent Reg = new Intent(Register.this, LoginActivity.class);

                    startActivity(Reg);

                }

            }

        });

    }

}
