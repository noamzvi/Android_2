package com.example.studentApp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import android.os.Bundle;

import com.example.studentApp.model.Model;
import com.example.studentApp.model.Student;

import java.util.List;


public class StudentList extends AppCompatActivity {
    List<Student> data;
    RecyclerView list;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            Log.d("TAG", "Returned");
            list.getAdapter().notifyDataSetChanged();
            //TODO: this doesn't work
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        data = Model.instance().getAllStudents();
        list = findViewById(R.id.student_list);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(this));
        StudentAdapter adapter = new StudentAdapter();
        list.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Intent viewIntent = new Intent(StudentList.this, ViewStudentActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("name", data.get(pos).name);
                bundle.putString("id", data.get(pos).id);
                bundle.putString("phone", data.get(pos).phone);
                bundle.putString("address", data.get(pos).address);
                bundle.putString("checked", String.valueOf(data.get(pos).cb));
                viewIntent.putExtra("selected_student", bundle);
                startActivity(viewIntent);
            }
        });

        Button add_button = findViewById(R.id.student_list_add_btn);
        add_button.setOnClickListener(view -> {
            Intent addIntent = new Intent(this, AddStudentActivity.class);
//            startActivity(addIntent);
            startActivityForResult(addIntent, 1);

        });
    }


    class StudentViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv;
        TextView idTv;
        CheckBox cb;
        public StudentViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.studentlistrow_name_tv);
            idTv = itemView.findViewById(R.id.studentlistrow_id_tv);
            cb = itemView.findViewById(R.id.studentlistrow_cb);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    listener.onItemClick(pos);
                }
            });
        }

        public void bind(Student st) {
            nameTv.setText(st.name);
            idTv.setText(st.id);
            cb.setChecked(st.cb);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int pos);
    }

    class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder>{
        OnItemClickListener listener;
        void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }
        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row,parent,false);
            view.findViewById(R.id.studentlistrow_cb).setEnabled(false);
            return new StudentViewHolder(view,listener);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student st = data.get(position);
            holder.bind(st);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}