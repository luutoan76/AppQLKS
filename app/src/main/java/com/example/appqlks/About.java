package com.example.appqlks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class About extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Button mtbnLogin;

        mtbnLogin = findViewById(R.id.btntoLogin);
        mtbnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLG = new Intent(About.this,LoginActivity.class);
                startActivity(toLG);

            }


        });
        Button mtbnLogout;
        mtbnLogout = findViewById(R.id.btdangxuat);
        mtbnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xóa text của textView nếu nó tồn tại
                if (textView.getText() != null && !textView.getText().toString().isEmpty()) {
                    textView.setText("");
                }

                // Xóa dữ liệu đăng nhập của người dùng
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("username");
                editor.remove("password");
                editor.apply();

                // Chuyển đến LoginActivity
                Intent toLo = new Intent(About.this,LoginActivity.class);
                startActivity(toLo);
            }
        });
        // Trong Activity thứ hai
        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        TextView textView = findViewById(R.id.TV4);
        textView.setText(text);









        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.about);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),InfoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.about:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}