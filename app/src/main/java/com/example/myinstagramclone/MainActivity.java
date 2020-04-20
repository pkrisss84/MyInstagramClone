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

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    private EditText edtAddEmail, edtUserLogIn, edtPasswordLogIn;
    private Button btnSignUp, btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Instagram Clone");
        // Save the current Installation to Back4App
        ParseInstallation.getCurrentInstallation().saveInBackground();

        setContentView(R.layout.activity_main);

        edtAddEmail = findViewById(R.id.edtAddEmail);
        edtUserLogIn = findViewById(R.id.edtUserLogIn);
        edtPasswordLogIn = findViewById(R.id.edtPasswordLogIn);

        edtPasswordLogIn.setOnKeyListener(new View.OnKeyListener() {
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

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogIn);

        if (ParseUser.getCurrentUser() != null){
            transitionToSocialMediaActivity();
        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (edtAddEmail.getText().toString().equals("") || edtUserLogIn.getText().toString().equals("")
                        || edtPasswordLogIn.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Email, Username, Password is required!", Toast.LENGTH_SHORT).show();
                }else {

                final ParseUser appUser = new ParseUser();
                appUser.setEmail(edtAddEmail.getText().toString());
                appUser.setUsername(edtUserLogIn.getText().toString());
                appUser.setPassword(edtPasswordLogIn.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            Toast.makeText(MainActivity.this, appUser.get("username") +
                                    " is signed up successfully", Toast.LENGTH_SHORT).show();
                            transitionToSocialMediaActivity();
                        }else{
                            Toast.makeText(MainActivity.this, e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
               }
            }
        });


        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, LogInMain.class);
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

        public void transitionToSocialMediaActivity(){
            Intent intent = new Intent(MainActivity.this, SocialMediaActivity.class);
            startActivity(intent);
    }

}
