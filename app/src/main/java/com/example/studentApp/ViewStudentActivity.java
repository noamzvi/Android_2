package com.example.studentApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentApp.model.Student;

public class ViewStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        Intent intent = getIntent();
        Bundle student = intent.getBundleExtra("selected_student");
        String name = student.getString("name");
        String id = student.getString("id");
        String phone = student.getString("phone");
        String address = student.getString("address");
        Boolean checked = Boolean.valueOf(student.getString("checked"));

        TextView name_tv = findViewById(R.id.viewstudent_name_tv);
        name_tv.setText(name);

        TextView id_tv = findViewById(R.id.viewstudent_id_tv);
        id_tv.setText(id);

        TextView phone_tv = findViewById(R.id.viewstudent_phone_tv);
        phone_tv.setText(phone);

        TextView address_tv = findViewById(R.id.viewstudent_address_tv);
        address_tv.setText(address);

        CheckBox checked_cb = findViewById(R.id.viewstudent_checked_cb);
        checked_cb.setChecked(checked);
        checked_cb.setEnabled(false);

        Button edit_button = findViewById(R.id.viewstudent_edit_btn);
        edit_button.setOnClickListener(view -> {
            Intent editIntent = new Intent(this, EditStudentActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("name", name);
            bundle.putString("id", id);
            bundle.putString("phone", phone);
            bundle.putString("address", address);
            bundle.putString("checked", String.valueOf(checked));
            editIntent.putExtra("selected_student", bundle);
            startActivity(editIntent);
        });
    }
}
