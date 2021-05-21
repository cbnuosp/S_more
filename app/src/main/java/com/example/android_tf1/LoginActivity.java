package com.example.android_tf1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

public class LoginActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//타이틀바 제거
        setContentView(R.layout.activity_login);

        TextView registerButton = (TextView)findViewById(R.id.registerButton);
       registerButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
               LoginActivity.this.startActivity(registerIntent);
           }
       });

        Button loginB = (Button)findViewById(R.id.login);
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                LoginActivity.this.startActivity(mainIntent);
            }
        });
    }
}