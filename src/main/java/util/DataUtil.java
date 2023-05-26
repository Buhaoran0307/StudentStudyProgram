package util;

import ConstantPacket.ConstantParameters;
import entity.Student;
import entity.Subject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * The utils package to operate data.
 */
public class DataUtil {

    /**
     * this method is used to check whether input phone number is 8 numbers integer.
     *
     * @param input input phone number
     * @return valid phone number or not
     */
    public static boolean checkPhoneNumber(String input) {
        String regex = "\\d{8}";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input).matches();
    }

    /**
     * this method check password
     * whether it contains at least one uppercase letter, one lowercase letter, and one digit, and no other symbols
     *
     * @param input input password
     * @return valid password or not
     */
    public static boolean checkPassword(String input) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input).matches();
    }

    /**
     * this method is used to calculate the GPA of the student
     * @param student the student wanted to be calculated
     * @return the GPA
     */
    public static float calculateGPA(Student student) {
        ArrayList<Subject> selectSubject = student.getSelectedSubjects();
        if (selectSubject == null) {
            return 0;
        }
        if (selectSubject.size() == 0) {
            return 0;
        }
        float totalMarks = 0;
        float totalCredit = 0;
        float credit;
        for (Subject subject : selectSubject) {
            credit = ConstantParameters.subjectInfoMap.get(subject.getSubjectNo()).getCredit();
            totalCredit += credit;
            if (subject.getGrade() >= 60) {
                totalMarks += credit * subject.getGrade();
            }
        }
        return totalMarks / totalCredit;
    }

    /**
     * this method is used to rank the GPA among all the students
     * @param GPA the GPA marks
     * @return the rank
     */
    public static int calculateGPARank(float GPA) {
        Student student;
        int rank = 1;
        Set<Integer> studentKey = ConstantParameters.studentMap.keySet();
        Iterator<Integer> studentIterator = studentKey.iterator();
        while (true) {
            if (studentIterator.hasNext()) {
                student = ConstantParameters.studentMap.get(studentIterator.next());
                if (GPA < calculateGPA(student)) {
                    rank++;
                }
            } else {
                break;
            }
        }
        return rank;
    }

}


