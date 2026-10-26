package com.example.midexam;

public class StudentModel {
    private Integer id;
    private String name;
    private String studentId;
    private String major;

    public StudentModel(String name, String studentId, String major) {
        this.name = name;
        this.studentId = studentId;
        this.major = major;
    }

    public StudentModel(Integer id, String name, String studentId, String major) {
        this.id = id;
        this.name = name;
        this.studentId = studentId;
        this.major = major;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
}
