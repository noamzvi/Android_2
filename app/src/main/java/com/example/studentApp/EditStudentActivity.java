package com.example.studentApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.studentApp.model.Model;
import com.example.studentApp.model.Student;

public class EditStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        Intent intent = getIntent();
        Bundle student = intent.getBundleExtra("selected_student");
        String name = student.getString("name");
        String id = student.getString("id");
        String phone = student.getString("phone");
        String address = student.getString("address");
        Boolean checked = Boolean.valueOf(student.getString("checked"));

        Student oldData = new Student(name, id, phone, address, checked);

        EditText name_et = findViewById(R.id.editstudent_name_et);
        name_et.setText(name);

        EditText id_et = findViewById(R.id.editstudent_id_et);
        id_et.setText(id);

        EditText phone_et = findViewById(R.id.editstudent_phone_et);
        phone_et.setText(phone);

        EditText address_et = findViewById(R.id.editstudent_address_et);
        address_et.setText(address);

        CheckBox checked_cb = findViewById(R.id.editstudent_checked_cb);
        checked_cb.setChecked(checked);

        Button cancel_button = findViewById(R.id.editstudent_cancel_btn);
        cancel_button.setOnClickListener(view -> finish());

        Button delete_button = findViewById(R.id.editstudent_delete_btn);
        delete_button.setOnClickListener(view -> {
            Model.instance().deleteStudent(oldData);
            setResult(3, null);
            finish();
        });

        Button save_button = findViewById(R.id.editstudent_save_btn);
        save_button.setOnClickListener(view -> {
            Student newStudentData = new Student(
                    name_et.getText().toString(),
                    id_et.getText().toString(),
                    phone_et.getText().toString(),
                    address_et.getText().toString(),
                    checked_cb.isChecked()
            );
            if (!newStudentData.name.isEmpty() && !newStudentData.id.isEmpty()) {

            Model.instance().updateStudent(oldData, newStudentData);

            Intent resultIntent = new Intent();
            Bundle bundle=new Bundle();
            bundle.putString("name", newStudentData.name);
            bundle.putString("id", newStudentData.id);
            bundle.putString("phone", newStudentData.phone);
            bundle.putString("address", newStudentData.address);
            bundle.putString("checked", String.valueOf(newStudentData.cb));
            resultIntent.putExtra("updated_student", bundle);
            setResult(2, resultIntent);
            }

            finish();
        });
    }
}