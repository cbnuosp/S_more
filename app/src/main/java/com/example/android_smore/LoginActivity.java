package com.example.android_smore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

public class LoginActivity extends FragmentActivity {

    private EditText id_login;
    private EditText pw_login;
    FirebaseAuth firebaseAuth;
    private Button loginB;
    private Button registerT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//타이틀바 제거
        setContentView(R.layout.activity_login);

        id_login = (EditText)findViewById(R.id.idText);
        pw_login = (EditText)findViewById(R.id.pwText);
        Button loginB = (Button)findViewById(R.id.login);
        Button registerT = (Button)findViewById(R.id.registerButton);

        firebaseAuth = firebaseAuth.getInstance();

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = id_login.getText().toString().trim();
                String pw = pw_login.getText().toString().trim();

                firebaseAuth.signInWithEmailAndPassword(id, pw)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent mainIntent = new Intent(LoginActivity.this, SuccessActivity.class);
                                    startActivity(mainIntent);
                                } else {//실패했을때
                                    Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                /*Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                LoginActivity.this.startActivity(mainIntent);*/
            }
        });

        registerT.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });


    }
}