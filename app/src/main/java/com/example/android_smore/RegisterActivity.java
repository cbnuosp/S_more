package com.example.android_smore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.auth.User;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class RegisterActivity extends FragmentActivity {

    private ArrayAdapter adapter;
    private Spinner spinner;
    private EditText id_join;
    private EditText pw_join;
    private EditText phone_join;
    private EditText name_join;
    private RadioGroup semester_join;
    private RadioButton sem;
    private Button btn;
    private User user;
    FirebaseAuth firebaseAuth;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        //db = FirebaseFirestore.getInstance();

      /*  if(firebaseAuth.getCurrentUser() != null){ // 데베에 맞는 유저가 있으면 -> 그룹 선택 창으로 이동
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }*/


        id_join = (EditText)findViewById(R.id.idText);
        pw_join = (EditText)findViewById(R.id.pwText);
        phone_join = (EditText)findViewById(R.id.phoneText);
        name_join = (EditText)findViewById(R.id.nameText);

        //spinner = (Spinner)findViewById(R.id.gradeSpinner);
        //adapter = ArrayAdapter.createFromResource(this, R.array.grade, android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);
        btn = (Button)findViewById(R.id.registerButton);

        firebaseAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = id_join.getText().toString().trim();
                final String pw = pw_join.getText().toString().trim();
                final String phone = phone_join.getText().toString().trim();
                final String name = name_join.getText().toString().trim();

                /*final String grade = spinner.getSelectedItem().toString().trim();
                sem = (RadioButton)findViewById(semester_join.getCheckedRadioButtonId());
                final String semester = sem.getText().toString().trim();*/

                firebaseAuth.createUserWithEmailAndPassword(id, pw)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Map<String, String> data = new HashMap<>();
                                    data.put("name",name);
                                    data.put("id", id);
                                    data.put("pw",pw);
                                    data.put("phone",phone);

                                   // DocumentReference member = db.collection("user").document();

                                    db.collection("user").document(id).set(data);

                                    /*HashMap<Object,String> hashMap = new HashMap<>();
                                    hashMap.put("id", id);
                                    hashMap.put("pw",pw);
                                    hashMap.put("phone",phone);
                                    hashMap.put("grade",grade);
                                    hashMap.put("semester",semester);*/
                                    //Toast.makeText(RegisterActivity.this, id+"님 환영합니다!",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(RegisterActivity.this, "회원가입 오류가 발생했습니다. 잠시만 기다려주세요.", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });
            }
        });

    }
}