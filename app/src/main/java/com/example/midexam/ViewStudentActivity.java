package com.example.midexam;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ViewStudentActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DBHelper dbHelper = new DBHelper(this);
        List<StudentModel> studentModels = dbHelper.getStudentList();

        if (studentModels.size() > 0) {
            StudentAdapter studentAdapter = new StudentAdapter(studentModels, ViewStudentActivity.this);
            recyclerView.setAdapter(studentAdapter);
        } else {
            Toast.makeText(this, "No Student Found", Toast.LENGTH_SHORT).show();
        }
    }
}