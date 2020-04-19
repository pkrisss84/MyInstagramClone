package com.example.myinstagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LogInMain extends AppCompatActivity {

    private EditText edtUserLogIn, edtUserPassword;
    private Button btnLogIn, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_main);

        edtUserLogIn = findViewById(R.id.edtUserLogIn);
        edtUserPassword = findViewById(R.id.edtUserPassword);

        edtUserPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {

                    onClick(btnSignUp);
                }
                return false;
            }

            private void onClick(Button btnSignUp) {
            }
        });

        btnLogIn = findViewById(R.id.btnLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtUserLogIn.getText().toString().equals("") || edtUserPassword.getText().toString().equals("")) {
                    Toast.makeText(LogInMain.this, "Username, Password is required!", Toast.LENGTH_SHORT).show();
                } else {


                    ParseUser.logInInBackground(edtUserLogIn.getText().toString(),
                            edtUserPassword.getText().toString(), new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {

                                    if (user != null && e == null) {
                                        Toast.makeText(LogInMain.this, user.get("username")
                                                + " is logged succesfully", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(LogInMain.this, WelcomeMain.class);
                                        startActivity(intent);

                                    } else {
                                        Toast.makeText(LogInMain.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                    }

                                }

                            });

                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInMain.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    //avoid the tap around the screen
    public void rootLayoutTapped (View view){

        //the try and catch enable to leave out the screen errors and the app don't crash
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
