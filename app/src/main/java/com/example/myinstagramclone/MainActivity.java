package com.example.myinstagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseInstallation;

public class MainActivity extends AppCompatActivity {

    private EditText edtAddEmail, edtUserLogIn, edtPasswordLogIn;
    private Button btnSignUp, btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Save the current Installation to Back4App
        ParseInstallation.getCurrentInstallation().saveInBackground();

        setContentView(R.layout.activity_main);

        edtAddEmail = findViewById(R.id.edtAddEmail);
        edtUserLogIn = findViewById(R.id.edtUserLogIn);
        edtPasswordLogIn = findViewById(R.id.edtPasswordLogIn);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogIn);
    }
}
