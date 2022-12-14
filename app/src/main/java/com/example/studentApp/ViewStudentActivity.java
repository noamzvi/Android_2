package com.example.studentApp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentApp.model.Student;

public class ViewStudentActivity extends AppCompatActivity {

    TextView name_tv;
    TextView id_tv;
    TextView phone_tv;
    TextView address_tv;
    CheckBox checked_cb;

    ActivityResultLauncher<Intent> startActivityForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==2){
                        updateFields(result.getData(), "updated_student");
                    }
                    if(result.getResultCode()==3){
                        finish();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        updateFields(getIntent(),"selected_student");

        Button edit_button = findViewById(R.id.viewstudent_edit_btn);
        edit_button.setOnClickListener(view -> {
            Intent editIntent = new Intent(this, EditStudentActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("name", name_tv.getText().toString());
            bundle.putString("id", id_tv.getText().toString());
            bundle.putString("phone", phone_tv.getText().toString());
            bundle.putString("address", address_tv.getText().toString());
            bundle.putString("checked", String.valueOf(checked_cb.isChecked()));
            editIntent.putExtra("selected_student", bundle);
            startActivityForResult.launch(editIntent);
        });

    }

    private void updateFields(Intent intent, String bundleKeyName){
        Bundle student = intent.getBundleExtra(bundleKeyName);
        String name = student.getString("name");
        String id = student.getString("id");
        String phone = student.getString("phone");
        String address = student.getString("address");
        Boolean checked = Boolean.valueOf(student.getString("checked"));

        name_tv = findViewById(R.id.viewstudent_name_tv);
        name_tv.setText(name);

        id_tv = findViewById(R.id.viewstudent_id_tv);
        id_tv.setText(id);

        phone_tv = findViewById(R.id.viewstudent_phone_tv);
        phone_tv.setText(phone);

        address_tv = findViewById(R.id.viewstudent_address_tv);
        address_tv.setText(address);

        checked_cb = findViewById(R.id.viewstudent_checked_cb);
        checked_cb.setChecked(checked);
        checked_cb.setEnabled(false);
    }
}
