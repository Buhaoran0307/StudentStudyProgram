package entity;
/**
 * This class includes the student's personal information
 * */
import java.util.ArrayList;

public class Student {
    /**
     * the student's name
     * */
    private String name;
    /**
     * the student's student number
     * */
    private int StudentNo;
    /**
     * the student's phone number
     * */
    private int phoneNumber;
    /**
     * the student's nickname
     * */
    private String nickName;
    /**
     * the student's password
     * */
    private String password;
    /**
     * the subjects selected by the student
     * */
    private ArrayList<Subject> selectedSubjects;

    @Override
    /**
     * Put the student's personal information into a string
     * @return this string
     * */
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
    /**
     * Student class's constructor
     * @param name the student's name
     * @param studentNo the student's student number
     * @param phoneNumber the student's phone number
     * @param nickName the student's nickname
     * @param password the student's password
     * */
    public Student(String name, int studentNo, int phoneNumber, String nickName, String password) {
        this.name = name;
        StudentNo = studentNo;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.password = password;
    }
    /**
     * Another Student class's constructor contains the list selectedSubjects
     * @param name the student's name
     * @param studentNo the student's student number
     * @param phoneNumber the student's phone number
     * @param nickName the student's nickname
     * @param password the student's password
     * @param selectedSubjects the subjects selected by the student
     * */
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
    /**
     * Get the student's name
     * @return the student's name
     * */
    public String getName() {
        return name;
    }
    /**
     * Set the student's name
     * @param name the student's name you want to set
     * */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Get the student's number
     * @return the student's number
     * */
    public int getStudentNo() {
        return StudentNo;
    }
    /**
     * Set the student's number
     * @param studentNo the student's number you want to set
     * */
    public void setStudentNo(int studentNo) {
        StudentNo = studentNo;
    }
    /**
     * Get the student's phone number
     * @return the student's phone number
     * */
    public int getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Set the student's phone number
     * @param phoneNumber the student's phone number you want to set
     * */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * Get the student's nickname
     * @return the student's nickname
     * */
    public String getNickName() {
        return nickName;
    }
    /**
     * Set the student's nickname
     * @param nickName the student's nickname you want to set
     * */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    /**
     * Get the student's password
     * @return the student's password
     * */
    public String getPassword() {
        return password;
    }
    /**
     * Set the student's password
     * @param password the student's password you want to set
     * */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Get the student's SelectedSubjects
     * @return the student's phone SelectedSubjects
     * */
    public ArrayList<Subject> getSelectedSubjects() {
        return selectedSubjects;
    }
    /**
     * Set the student's selectedSubjects
     * @param selectedSubjects the list of the subjects selected by the student
     * */
    public void setSelectedSubjects(ArrayList<Subject> selectedSubjects) {
        this.selectedSubjects = selectedSubjects;
    }
}
