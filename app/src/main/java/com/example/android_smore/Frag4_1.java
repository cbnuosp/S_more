package com.example.android_smore;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.InputStream;

public class Frag4_1 extends AppCompatActivity{
    private final int GALLERY_CODE = 10;
    private EditText m_name;
    private EditText m_phone;
    private ImageView photo;
    private Button cancel;
    private Button save;
    private TextView change_photo;
    FirebaseAuth firebaseAuth;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private StorageReference riverRef;
    private Bitmap img;
    private DocumentReference docRef;
    private InputStream in;
    private Uri file;
    private UploadTask uploadTask;
    private static final String TAG = "DocSnippets";
    String name, phonenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.frag4_1);

        cancel = (Button)findViewById(R.id.cancelBtn);
        save = (Button)findViewById(R.id.saveBtn);
        change_photo = (TextView) findViewById(R.id.changebtn);
        m_name = (EditText)findViewById(R.id.my_name);
        m_phone = (EditText)findViewById(R.id.my_pnum);
        photo = (ImageView)findViewById(R.id.profile);

        firebaseAuth = firebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        String email = user.getEmail();

        storage = FirebaseStorage.getInstance("gs://test1-85778.appspot.com");
        storageRef = storage.getReference();
        storageRef.child("profile/"+email).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //이미지 로드 성공시

                Glide.with(getApplicationContext())
                        .load(uri)
                        .into(photo);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //이미지 로드 실패시
                photo.setImageResource(R.drawable.ic_baseline_person_24);
            }
        });

        docRef = db.collection("user").document(email);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        name = document.getString("name");
                        m_name.setText(name);
                        phonenum = document.getString("phone");
                        m_phone.setText(phonenum);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //Intent mIntent = new Intent(Frag4_1.this, Frag4.class);
                //startActivity(mIntent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n_name = m_name.getText().toString().trim();
                String n_phone = m_phone.getText().toString().trim();
                DocumentReference docRef = db.collection("user").document(email);
                docRef.update("name",n_name);
                docRef.update("phone",n_phone);

                Intent intent = getIntent();
                finish(); //현재 액티비티 종료 실시
                overridePendingTransition(0, 0); //인텐트 애니메이션 없애기
                startActivity(intent); //현재 액티비티 재실행 실시
                overridePendingTransition(0, 0); //인텐트 애니메이션 없애기
            }
        });

        change_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAlbum();
            }
        });
    }

    private void loadAlbum(){
        //Intent photoIntent = new Intent(Intent.ACTION_PICK);
        //photoIntent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        Intent photoIntent = new Intent();
        photoIntent.setType("image/*");
        photoIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(photoIntent, GALLERY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_CODE) {
            file = Uri.fromFile(new File(getPath(data.getData())));
            final FirebaseUser user = firebaseAuth.getCurrentUser();
            String email = user.getEmail();
            storage = FirebaseStorage.getInstance();
            storageRef = storage.getReferenceFromUrl("gs://test1-85778.appspot.com");
            riverRef = storageRef.child("profile/"+email);
            uploadTask = riverRef.putFile(file);

            try {
                in = getContentResolver().openInputStream(data.getData());
                img = BitmapFactory.decodeStream(in);
                in.close();
                photo.setImageBitmap(img);
            } catch(Exception e) {
                e.printStackTrace();
            }

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.v("알림","업로드 실패");
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.v("알림","업로드 성공");
                }
            });
        }
    }

    public String getPath(Uri uri){
        String [] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(index);
    }

}