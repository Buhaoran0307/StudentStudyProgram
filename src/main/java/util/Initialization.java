package util;

import com.google.gson.Gson;
import entity.Student;
import entity.Subject;
import entity.SubjectInfo;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Initialization {
    /**
     * this method is used to initialize the test data
     */
    public void DataInitialization() {
        Gson gson = new Gson();
        /*
        create subjectInfo table
         */
        Map<Integer, SubjectInfo> subjectInfoMap = new HashMap<Integer, SubjectInfo>();
        subjectInfoMap.put(1,new SubjectInfo(1,"JAVA",2,"required"));
        subjectInfoMap.put(2,new SubjectInfo(2,"Data structure",4,"required"));
        subjectInfoMap.put(3,new SubjectInfo(3,"AI",2,"optional"));
        String subjectInfoJson = gson.toJson(subjectInfoMap);
        /*
        create student table
         */
        ArrayList<Subject> selectedSubjects1 = new ArrayList<Subject>();
        selectedSubjects1.add(new Subject(1,"2022-09-01",90,5));
        selectedSubjects1.add(new Subject(2,"2022-09-01",93,7));
        selectedSubjects1.add(new Subject(3,"2023-01-01",100,1));
        ArrayList<Subject> selectedSubjects2 = new ArrayList<Subject>();
        selectedSubjects2.add(new Subject(1,"2022-09-01",80,16));
        selectedSubjects2.add(new Subject(2,"2022-09-01",95,3));
        selectedSubjects2.add(new Subject(3,"2023-01-01",70,60));
        Map<Integer, Student> studentMap = new HashMap<>();
        studentMap.put(2020001,new Student("Jack",2020001,13888001,"J66",
                "123",selectedSubjects1));
        studentMap.put(2020002,new Student("Lucy",2020002,13888002,"L66",
                "321",selectedSubjects2));
        String studentJson = gson.toJson(studentMap);
         /*
        write the data into .json file.
         */
        try {
            FileWriter fileWriter1 = new FileWriter("src/main/java/DataSet/subjectInfo.json");
            fileWriter1.write(subjectInfoJson);
            fileWriter1.flush();
            FileWriter fileWriter2 = new FileWriter("src/main/java/DataSet/student.json");
            fileWriter2.write(studentJson);
            fileWriter2.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
