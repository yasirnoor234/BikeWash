package com.example.yasirnoor.bikewash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String userName = getIntent().getStringExtra("Email");
        TextView textView = (TextView)findViewById(R.id.text);
        textView.setText(userName);
    }
}
