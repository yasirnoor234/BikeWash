package com.example.yasirnoor.bikewash;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {

    private EditText l_email,l_password;
    private String email,password;
    Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        l_email = (EditText)findViewById(R.id.email);
        l_password =(EditText)findViewById(R.id.password);
        btnSignIn = (Button)findViewById(R.id.signIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(); //call when the button is clicked to validate the button

            }
        });

    }
    public void login(View view){
        DatabaseHelper myDb = new DatabaseHelper(this);
        SQLiteDatabase db = myDb.getReadableDatabase();

        EditText s_username = (EditText)findViewById(R.id.email);
        EditText s_password = (EditText)findViewById(R.id.password);

        String[] columns = {"email","password"};
        String[] uValues = {s_username.getText().toString(),s_password.getText().toString()};

        Cursor cursor=db.query("login",columns,"email = ? AND pass = ? ",uValues,null,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                Intent intent = new Intent(this,ProfileActivity.class);
                intent.putExtra("Email",s_username.getText().toString());
                startActivity(intent);
            }
        }

    }
    public void signIn(){
        initialize(); //initialize the input to string variables
        if(!validate()){
            Toast.makeText(this,"SignIn has Failed",Toast.LENGTH_SHORT).show();
        }else{
            onSignInSuccess();
        }

    }


    public void onSignInSuccess(){
        //TODO what will go after the valid input
        Intent welcome = new Intent(SignIn.this,WelcomeScreen.class);
        startActivity(welcome);
    }
    public boolean validate(){
        boolean valid = true;
        if(email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            l_email.setError("Please Enter Valid Email Address");
            valid = false;
        }
        if(password.isEmpty()||password.length()<8){
            l_password.setError("Please Enter Minimum 8 Character");
            valid = false;
        }

        return valid;
    }

    public void initialize() {
        email =l_email.getText().toString().trim();
        password =l_password.getText().toString().trim();

    }

}
