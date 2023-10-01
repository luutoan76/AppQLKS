package com.example.appqlks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText medtname, medtpass, medtpass2;
    Button mtbntoLG, mbtnDK;
    SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        medtname = findViewById(R.id.DKusername);
        medtpass = findViewById(R.id.DKPassword);
        medtpass2 = findViewById(R.id.RePassword);
        mbtnDK = findViewById(R.id.btnRegister);

        mtbntoLG = findViewById(R.id.btntoDN);

        mtbntoLG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        // tạo và mở database
        mydatabase = openOrCreateDatabase("qltaikhoan.db", MODE_PRIVATE,null);
        //tạo table để chứa dữ liệu
        try {
            String sql = "CREATE TABLE tbltaikhoan( tendn TEXT primary key, pass TEXT, repass TEXT) ";
            mydatabase.execSQL(sql);
        }
        catch (Exception e){
            Log.e("error","Table đã tồn tại");
        }

        mbtnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tendn = medtname.getText().toString();
                String pass = medtpass.getText().toString();
                String repass = medtpass2.getText().toString();
                ContentValues myvalue = new ContentValues();
                myvalue.put("tendn",tendn);
                myvalue.put("pass", pass);
                myvalue.put("repass", repass);
                String msg = "";
                if(mydatabase.insert("tbltaikhoan",null,myvalue) == -1){
                    msg = "đăng kí không thành công ";

                }
                else {
                    msg = "đăng kí  thành công ";
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.putExtra("tendn", tendn);
                    intent.putExtra("password", pass);
                    startActivity(intent);
                }
                Toast.makeText(RegisterActivity.this,msg , Toast.LENGTH_SHORT).show();
            }

        });



    }
}