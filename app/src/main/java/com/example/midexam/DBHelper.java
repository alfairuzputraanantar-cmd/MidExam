package com.example.midexam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "student_db";
    private static final String TB_NAME = "students";

    private static final String Col_ID = "id";
    private static final String Col_Name = "name";
    private static final String Col_StudentID = "student_id";
    private static final String Col_Major = "major";

    private SQLiteDatabase mySQLite;

    private static final String CREATE_TABLE = "CREATE TABLE " + TB_NAME + " ("
            + Col_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Col_Name + " TEXT NOT NULL, "
            + Col_StudentID + " TEXT NOT NULL, "
            + Col_Major + " TEXT NOT NULL);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
        onCreate(db);
    }

    public void addStudent(StudentModel studentModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_Name, studentModel.getName());
        contentValues.put(Col_StudentID, studentModel.getStudentId());
        contentValues.put(Col_Major, studentModel.getMajor());

        mySQLite = this.getWritableDatabase();
        mySQLite.insert(TB_NAME, null, contentValues);
    }

    public List<StudentModel> getStudentList() {
        String viewData = "SELECT id, name, student_id, major FROM " + TB_NAME;
        mySQLite = this.getReadableDatabase();
        List<StudentModel> storeStudents = new ArrayList<>();
        Cursor cursor = mySQLite.rawQuery(viewData, null);

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String studentId = cursor.getString(2);
                String major = cursor.getString(3);
                storeStudents.add(new StudentModel(id, name, studentId, major));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeStudents;
    }

    public void updateStudent(StudentModel studentModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_Name, studentModel.getName());
        contentValues.put(Col_StudentID, studentModel.getStudentId());
        contentValues.put(Col_Major, studentModel.getMajor());

        mySQLite = this.getWritableDatabase();
        mySQLite.update(TB_NAME, contentValues, Col_ID + " = ?",
                new String[]{String.valueOf(studentModel.getId())});
    }

    public void deleteStudent(int id) {
        mySQLite = this.getWritableDatabase();
        mySQLite.delete(TB_NAME, Col_ID + " = ?", new String[]{String.valueOf(id)});
    }
}