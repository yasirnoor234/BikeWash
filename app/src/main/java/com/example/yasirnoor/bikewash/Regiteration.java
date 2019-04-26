package com.example.yasirnoor.bikewash;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.PatternMatcher;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Regiteration extends AppCompatActivity {

//    SQLiteOpenHelper sqLiteOpenHelper;
  //  SQLiteDatabase db;

    //int val1,val2,val3;

    EditText r_name,r_email,r_password,r_cpassword,r_phone;
    String name,email,password,cpassword,phone;

    Button registration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiteration);

        //sqLiteOpenHelper = new DatabaseHelper(this);
        r_name = (EditText) findViewById(R.id.name);
        r_email = (EditText) findViewById(R.id.email);
        r_password = (EditText) findViewById(R.id.password);
        r_cpassword = (EditText) findViewById(R.id.cpassword);
        r_phone = (EditText) findViewById(R.id.phone);

        registration = (Button) findViewById(R.id.registration);

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registration(); //call when the button is clicked to validate the button
                if(registration!=null){
                    recreate();
                }

                /*                if(registration!=null) {
                    db = sqLiteOpenHelper.getWritableDatabase();
                    String fname = r_name.getText().toString();
                    String email = r_email.getText().toString();
                    String password = r_password.getText().toString();
                    String cpassword = r_cpassword.getText().toString();
                    String phone = r_phone.getText().toString();
                    insertData(fname, email, password, cpassword, phone);
  */

            }
        });
    }
    public void register(View view){
        EditText firstName = (EditText)findViewById(R.id.name);
        EditText email = (EditText)findViewById(R.id.email);
        EditText password = (EditText)findViewById(R.id.password);
        EditText cpassword = (EditText)findViewById(R.id.cpassword);
        EditText phone = (EditText)findViewById(R.id.phone);

        Button registration = (Button)findViewById(R.id.registration);

        String r_name = firstName.getText().toString();
        String r_email = email.getText().toString();
        String r_password = password.getText().toString();
        String r_cpassword = cpassword.getText().toString();
        String r_phone = phone.getText().toString();

        DatabaseHelper myDB = new DatabaseHelper(this);
        SQLiteDatabase db = myDB.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("firstname",r_name);
        values.put("email",r_email);
        values.put("password",r_password);
        values.put("cpassword",r_cpassword);
        values.put("phone",r_phone);

        db.insert("registration",null,values);
        Toast.makeText(this,"Data Insert",Toast.LENGTH_SHORT).show();


    }

/*    public void insertData(String fname,String email,String password,String cpassword,String phone){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2,fname);
        contentValues.put(DatabaseHelper.COL_3,email);
        contentValues.put(DatabaseHelper.COL_4,password);
        contentValues.put(DatabaseHelper.COL_5,cpassword);
        contentValues.put(DatabaseHelper.COL_6,phone);
        long Id = db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);


    }
 */
     public void registration(){
        initialize(); //initialize the input to string variables
        if(!validate()){
            Toast.makeText(this,"Registration has Failed",Toast.LENGTH_SHORT).show();
        }else{
            onSignUpSuccess();
        }

    }
    public void onSignUpSuccess(){
        //TODO what will go after the valid input
        Intent signIn = new Intent(Regiteration.this,SignIn.class);
        startActivity(signIn);
    }
    public boolean validate(){
        boolean valid = true;
        if(name.isEmpty()||name.length()>32){
            r_name.setError("Please Enter Valid Name");
            valid = false;
        }
        if(email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            r_email.setError("Please Enter Valid Email Address");
            valid = false;
        }
        if(password.isEmpty()||password.length()<8){
            r_password.setError("Please Enter Minimum 8 Character");
            valid = false;
        }
        if(cpassword.isEmpty()||cpassword.length()<8 && cpassword!=password){
            r_cpassword.setError("Please Both Password Same");
            valid = false;
        }
        if(phone.isEmpty()|| phone.length()<11 || phone.matches(phone)==false){
            r_phone.setError("Please Enter Valid Phone Number");
            valid = false;
        }

        return valid;
    }

    public void initialize() {
        name =r_name.getText().toString().trim();
        email =r_email.getText().toString().trim();
        password =r_password.getText().toString().trim();
        cpassword =r_cpassword.getText().toString().trim();
        phone =r_phone.getText().toString().trim();

    }
}
