package entity;

import java.util.ArrayList;

public class Student {
    private String name;
    private int StudentNo;
    private int phoneNumber;
    private String nickName;
    private String password;
    private ArrayList<Subject> selectedSubjects;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", StudentNo=" + StudentNo +
                ", phoneNumber=" + phoneNumber +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", selectedSubjects=" + selectedSubjects +
                '}';
    }
    public Student(String name, int studentNo, int phoneNumber, String nickName, String password) {
        this.name = name;
        StudentNo = studentNo;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.password = password;
    }

    public Student(String name, int studentNo, int phoneNumber, String nickName, String password, ArrayList<Subject> selectedSubjects) {
        this.name = name;
        StudentNo = studentNo;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.password = password;
        this.selectedSubjects = selectedSubjects;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentNo() {
        return StudentNo;
    }

    public void setStudentNo(int studentNo) {
        StudentNo = studentNo;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Subject> getSelectedSubjects() {
        return selectedSubjects;
    }

    public void setSelectedSubjects(ArrayList<Subject> selectedSubjects) {
        this.selectedSubjects = selectedSubjects;
    }
}
