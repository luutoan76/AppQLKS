package com.example.appqlks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edUsernameC,edPasswordC;
    Button btloginC,mtbnRegisC;
    SQLiteDatabase mydatabase;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //anh xa
        edUsernameC = findViewById(R.id.edUsername);
        edPasswordC = findViewById(R.id.edPassword);
        btloginC = findViewById(R.id.btnLogin);
        mtbnRegisC = findViewById(R.id.btnRegis);
        mydatabase = openOrCreateDatabase("qltaikhoan.db", MODE_PRIVATE, null);




        btloginC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsernameC.getText().toString().trim();
                String password = edPasswordC.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
///vao admincontrol neu nhap tk mk nhu ben duoi
//                else if (username.equals("admin") && password.equals("123")) {
//                    Intent i = new Intent(LoginActivity.this, AdminControl.class);
//                    startActivity(i);}

                else {
                    // truy van database
                    Cursor cursor = mydatabase.rawQuery("SELECT * FROM tbltaikhoan WHERE tendn = ? AND pass = ?", new String[]{username, password});
                    if (cursor.getCount() > 0) {

                        Intent intent = new Intent(LoginActivity.this, About.class);
                        String text = "Hello " + edUsernameC.getText().toString(); // Thêm chữ "Hello" vào trước giá trị
                        intent.putExtra("text", text);
                        startActivity(intent);

                    } else {
                        Toast.makeText(LoginActivity.this, "Sai mật khẩu hoặc tên đăng nhập", Toast.LENGTH_SHORT).show();
                    }
                    cursor.close();
                }
                // Trong Activity đầu tiên





            }

        });







        mtbnRegisC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regis = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(regis);
            }
        });



    }
}