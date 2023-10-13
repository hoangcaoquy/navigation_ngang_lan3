package com.example.navigation_ngang_lan3.fragment;

import static androidx.core.content.ContextCompat.startActivities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.navigation_ngang_lan3.R;

import java.util.ArrayList;

public class fragment_home extends Fragment {

    Button btnTra,btnThem;
    EditText edtTienganh;
    TextView txtKetqua;
    ListView lvLichsu;
    DataSqlite2 dataSqlite2 = null;
    ArrayList<Class_tu_dien> arrayData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // đây là chô ánh xạ
        btnTra= view.findViewById(R.id.btnTra);
        edtTienganh=view.findViewById(R.id.edtTienganh);
        txtKetqua=view.findViewById(R.id.txtKetqua);
        lvLichsu=view.findViewById(R.id.lvLichsu);
        btnThem=view.findViewById(R.id.btnThem);

        //đây là chỗ tạo bảng
        dataSqlite2 = new DataSqlite2(getContext(),"Tudien2",null,1);
        String sql_create_table = "CREATE TABLE IF NOT EXISTS dictionary(Tu_id INTEGER PRIMARY KEY AUTOINCREMENT, english VARCHAR(50), vietnamese VARCHAR(50))";
        dataSqlite2.ExecuteSql(sql_create_table);

        //đây là chỗ click vào btnThem
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), Class_them_lan2.class);
                startActivity(intent);
            }
        });


        // đây là sự kiện click vào button tra
        btnTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tuKhoa = edtTienganh.getText().toString();
                LoadData(tuKhoa);
            }
        });



        // đây là su kiên kick vào các item trong listview
        lvLichsu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Class_tu_dien item = (Class_tu_dien) lvLichsu.getItemAtPosition(i);

                String tiengviet2 = "";
                if (item != null && item.getTiengviet2() != null) {
                    tiengviet2 = item.getTiengviet2();
                }

                String tienganh2 = "";
                if (item != null && item.getTienganh2() != null) {
                    tienganh2 = item.getTienganh2();
                }
                Log.d("Debug", "Tiengviet2: " + tiengviet2);
                Log.d("Debug", "Tienganh2: " + tienganh2);

                // Tạo Intent
                Intent intent = new Intent(requireContext(), Sua_Xoa_quy.class);

                // Truyền dữ liệu từ trang A sang trang B
                intent.putExtra("tiengviet", tiengviet2);
                intent.putExtra("tienganh", tienganh2);

                // Khởi chạy trang B
                startActivity(intent);
            }
        });

        return view;
    }
    // dây là phương thức lấy dữ liệu ra
    public void LoadData(String Tukhoa) {

        String sql_select = "SELECT * FROM dictionary WHERE english LIKE '%" + Tukhoa + "%'";
        Cursor cursor = dataSqlite2.SelectData(sql_select);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            String english = cursor.getString(1);
            String vietnamese = cursor.getString(2);
            //Toast.makeText(this, "id" + id + "tienganh" + english + "viet" + vietnamese, Toast.LENGTH_SHORT).show();
            txtKetqua.setText(vietnamese);
            arrayData.add(new Class_tu_dien(english,vietnamese));
        }else{
            Toast.makeText(getContext(), "Chưa co từ này trong từ điển :))", Toast.LENGTH_SHORT).show();

        }

        tu_adapter adapter = new tu_adapter(getContext(), R.layout.item_listview, arrayData);
        lvLichsu.setAdapter(adapter);
    }
}