package com.example.studentApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.studentApp.model.Model;
import com.example.studentApp.model.Student;

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        EditText nameEt = findViewById(R.id.addstudent_name_et);
        EditText idEt = findViewById(R.id.addstudent_id_et);
        EditText phoneEt = findViewById(R.id.addstudent_phone_et);
        EditText addressEt = findViewById(R.id.addstudent_address_et);
        CheckBox checkedCb = findViewById(R.id.addstudent_checked_cb);
        Button saveBtn = findViewById(R.id.addstudent_save_btn);
        Button cancelBtn = findViewById(R.id.addstudent_cancel_btn);

        saveBtn.setOnClickListener(view -> {
            String name = nameEt.getText().toString();
            String id = idEt.getText().toString();
            String phone = phoneEt.getText().toString();
            String address = addressEt.getText().toString();
            Boolean checked = checkedCb.isChecked();

            if (!name.isEmpty()  && !id.isEmpty()) {
                Model.instance().addStudent(new Student(name, id, phone, address, checked));
            }

            finish();
        });

        cancelBtn.setOnClickListener(view -> finish());
    }
}