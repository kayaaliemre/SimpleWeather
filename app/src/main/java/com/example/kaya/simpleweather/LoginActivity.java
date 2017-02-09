package com.example.kaya.simpleweather;

/**
 * Created by GÖZDE on 9.01.2017.
 */


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences preference = getSharedPreferences("Login", Context.MODE_PRIVATE);

        Button register = (Button) findViewById(R.id.btnRegister);
        Button signIn = (Button) findViewById(R.id.btnSignIn);

        final EditText etEmail = (EditText) findViewById(R.id.email);
        final EditText etPassword = (EditText) findViewById(R.id.password);
        final String email = preference.getString("email", "");
        final String password = preference.getString("password", "");


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Reg = new Intent(LoginActivity.this, Register.class);
                startActivity(Reg);

            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String typedEmail = etEmail.getText().toString();
                typedEmail = typedEmail.toLowerCase();
                if (email.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please, register first!", Toast.LENGTH_LONG).show();
                } else {
                    if (password.isEmpty()) {
                        if (email.equals(typedEmail)) {
                            Toast.makeText(LoginActivity.this, "Enter a password", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Wrong password or email", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        String typedPassword = etPassword.getText().toString();
                        if ((email.equals(typedEmail)) && (password.equals(typedPassword))) {

                            Intent cities = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(cities);
                        } else {
                            Toast.makeText(LoginActivity.this, "Wrong password or email", Toast.LENGTH_LONG).show();
                        }
                    }
                }


            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        EditText etEmail = (EditText) findViewById(R.id.email);
        EditText etPassword = (EditText) findViewById(R.id.password);

        SharedPreferences preference = getSharedPreferences("saveTextEdit", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString("email", etEmail.getText().toString());
        editor.putString("password", etPassword.getText().toString());
        editor.commit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences preference = getSharedPreferences("saveTextEdit", Context.MODE_PRIVATE);
        EditText email = (EditText) findViewById(R.id.email);
        EditText etPassword = (EditText) findViewById(R.id.password);
        email.setText(preference.getString("email", ""));
        etPassword.setText(preference.getString("password", ""));
    }



}

