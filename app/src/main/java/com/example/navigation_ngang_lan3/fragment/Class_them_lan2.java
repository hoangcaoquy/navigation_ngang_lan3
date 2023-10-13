package com.example.navigation_ngang_lan3.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.navigation_ngang_lan3.R;

public class Class_them_lan2 extends AppCompatActivity {

    EditText edtTienganh3,edtTiengviet3;
    Button btnThem;
    DataSqlite2 dataSqlite2 = new DataSqlite2(Class_them_lan2.this,"Tudien2",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_du_lieu);

        //ánh xạ ở đây
        btnThem = findViewById(R.id.btnThem);
        edtTienganh3 = findViewById(R.id.edtTienganh3);
        edtTiengviet3 = findViewById(R.id.edtTiengviet3);

        // bắt sự kiện click vào button
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Tienganh3 = edtTienganh3.getText().toString();
                String Tiengviet3 = edtTiengviet3.getText().toString();

                //thêm dữ liệu vào bảng
                String sql1 = "INSERT INTO dictionary VALUES (null,'" + Tienganh3 + "', '" + Tiengviet3 + "')";
                dataSqlite2.ExecuteSql(sql1);
                // Hiển thị Toast thông báo thành công
                Toast.makeText(Class_them_lan2.this, "Thêm dữ liệu thành công", Toast.LENGTH_SHORT).show();

                // Quay lại trang đầu
                finish();

            }
        });
    }
}