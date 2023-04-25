package com.example.appproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button btnLogin;
    String email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setTitle("Login");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
    }
    private void checkLogin(){
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        if (email.isEmpty() || password.isEmpty()){
            alertFail("Correo y Contraseña requeridos.");
        }else{
            sendLogin();
        }
    }

    private void sendLogin() {
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
        finish();
    }

    private void alertFail(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(s)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        })
        .show();
    }
}