package util;

import com.google.gson.Gson;
import entity.Student;
import entity.Subject;
import entity.SubjectInfo;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * The utils package is to init data.
 */
public class Initialization {
    /**
     * this method is used to initialize the test data
     */
    public void DataInitialization() {
        Gson gson = new Gson();
        /*
        create subjectInfo table
         */
        Map<Integer, SubjectInfo> subjectInfoMap = new HashMap<>();
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
        ArrayList<Subject> selectedSubjects3 = new ArrayList<Subject>();
        selectedSubjects3.add(new Subject(1,"2022-09-01",100,1));
        selectedSubjects3.add(new Subject(2,"2022-09-01",96,2));
        selectedSubjects3.add(new Subject(3,"2023-01-01",69,64));
        ArrayList<Subject> selectedSubjects4 = new ArrayList<Subject>();
        selectedSubjects4.add(new Subject(1,"2022-09-01",67,70));
        selectedSubjects4.add(new Subject(2,"2022-09-01",88,22));
        selectedSubjects4.add(new Subject(3,"2023-01-01",90,4));
        ArrayList<Subject> selectedSubjects5 = new ArrayList<Subject>();
        selectedSubjects5.add(new Subject(1,"2022-09-01",68,65));
        selectedSubjects5.add(new Subject(2,"2022-09-01",89,20));
        selectedSubjects5.add(new Subject(3,"2023-01-01",91,3));
        ArrayList<Subject> selectedSubjects6 = new ArrayList<Subject>();
        selectedSubjects6.add(new Subject(1,"2022-09-01",89,10));
        selectedSubjects6.add(new Subject(2,"2022-09-01",77,50));
        selectedSubjects6.add(new Subject(3,"2023-01-01",83,10));
        ArrayList<Subject> selectedSubjects7 = new ArrayList<Subject>();
        selectedSubjects7.add(new Subject(1,"2022-09-01",78,50));
        selectedSubjects7.add(new Subject(2,"2022-09-01",77,50));
        selectedSubjects7.add(new Subject(3,"2023-01-01",76,55));
        ArrayList<Subject> selectedSubjects8 = new ArrayList<Subject>();
        selectedSubjects8.add(new Subject(1,"2022-09-01",8,150));
        selectedSubjects8.add(new Subject(2,"2022-09-01",7,150));
        selectedSubjects8.add(new Subject(3,"2023-01-01",6,155));
        ArrayList<Subject> selectedSubjects9 = new ArrayList<Subject>();
        selectedSubjects9.add(new Subject(1,"2022-09-01",100,1));
        selectedSubjects9.add(new Subject(2,"2022-09-01",100,1));
        selectedSubjects9.add(new Subject(3,"2023-01-01",60,100));
        ArrayList<Subject> selectedSubjects10 = new ArrayList<Subject>();
        selectedSubjects10.add(new Subject(1,"2022-09-01",30,120));
        selectedSubjects10.add(new Subject(2,"2022-09-01",10,130));
        selectedSubjects10.add(new Subject(3,"2023-01-01",20,140));
        Map<Integer, Student> studentMap = new HashMap<>();
        studentMap.put(2020001,new Student("Jack",2020001,13888001,"J66",
                "123",selectedSubjects1));
        studentMap.put(2020002,new Student("Lucy",2020002,13888002,"L66",
                "321",selectedSubjects2));
        studentMap.put(2020003,new Student("Amy",2020003,13888003,"google",
                "111",selectedSubjects3));
        studentMap.put(2020004,new Student("Abandon",2020004,13888004,"chicken",
                "222",selectedSubjects4));
        studentMap.put(2020005,new Student("Rachel",2020005,13888005,"sweetie",
                "333",selectedSubjects5));
        studentMap.put(2020006,new Student("Ross",2020006,13888006,"Red Ross",
                "444",selectedSubjects6));
        studentMap.put(2020007,new Student("Monica",2020007,13888007,"head chief",
                "555",selectedSubjects7));
        studentMap.put(2020008,new Student("Phoebe",2020008,13888008,"smelly cat",
                "666",selectedSubjects8));
        studentMap.put(2020009,new Student("Chandler",2020009,13888009,"chan chan man",
                "777",selectedSubjects9));
        studentMap.put(2020010,new Student("Joey",2020010,13888019,"pizza",
                "888",selectedSubjects10));
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
