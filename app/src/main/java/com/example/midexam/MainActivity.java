package com.example.midexam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnView;
    EditText editTextName, editTextStudentID;
    Spinner spinnerMajor;
    String[] majors = {"Informatics", "Information Systems"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextStudentID = findViewById(R.id.editTextStudentID);
        spinnerMajor = findViewById(R.id.spinnerMajor);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, majors);
        spinnerMajor.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String studentId = editTextStudentID.getText().toString().trim();
                String major = spinnerMajor.getSelectedItem().toString();

                if (name.length() <= 0 || studentId.length() <= 0) {
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    DBHelper dbHelper = new DBHelper(MainActivity.this);
                    StudentModel studentModel = new StudentModel(name, studentId, major);
                    dbHelper.addStudent(studentModel);
                    Toast.makeText(MainActivity.this, "Add Successful", Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(getIntent());
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewStudentActivity.class);
                startActivity(intent);
            }
        });
    }
}