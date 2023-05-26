package util;

import ConstantPacket.ConstantParameters;
import entity.Subject;
import entity.SubjectInfo;

import java.util.*;

/**
 * The utils package to deal with layout
 */
public class LayoutUtil {
    /**
     * Gain all subject information of one student
     * @param selectedSubjects the selected subjects to be shown
     * @param columnNames the names of the columns
     * @param column sort by this column value
     * @param isAscending the sort order
     * @return the sorted subject information
     */
    public static Object[][] getSubjectInfo(ArrayList<Subject> selectedSubjects, String[] columnNames, String column, boolean isAscending){
        Object[][] data;
        sortByColumn(selectedSubjects,column,isAscending);
        int rowNum = selectedSubjects.size();
        int columNum = columnNames.length;
        HashMap<String,String> mapSubjectInfo;
        if (rowNum == 0){
            data = new Object[1][columNum];
            Arrays.fill(data[0], "NULL");
            System.out.println("[log] You didn't select any subject !");
        }else {
            data = new Object[rowNum][columNum];
            for (int i=0; i<rowNum; i++){
                mapSubjectInfo = getMapSubjectInfo(selectedSubjects.get(i));
                for (int j=0; j<columNum; j++){
                    data[i][j] = mapSubjectInfo.get(columnNames[j]);
                }
            }
        }
        return data;
    }
    /*
      ###### Table column name ######
      ID --> subjectNo
      Subject --> subjectName
      grade --> grade
      character --> character
      credit --> credit
      rank --> rank
      startTime --> startTime
     */
    /**
     * get the subject info of the subject
     * @param subject the subject wanted to be searched
     * @return the map contain subject information
     */
    public static HashMap<String, String> getMapSubjectInfo(Subject subject){
        HashMap<String,String> data = new HashMap<>();
        SubjectInfo subjectInfo = ConstantParameters.subjectInfoMap.get(subject.getSubjectNo());
        data.put("ID", Integer.toString(subject.getSubjectNo()));
        data.put("Subject", subjectInfo.getSubjectName());
        data.put("Grade",Integer.toString(subject.getGrade()));
        data.put("Character",subjectInfo.getCharacter());
        data.put("Credit",Integer.toString(subjectInfo.getCredit()));
        data.put("Rank",Integer.toString(subject.getRank()));
        data.put("Start Time",subject.getStartTime());
        return data;
    }

    /**
     * Sort the array list by the value of the object in it
     * @param selectedSubjects the array list wanted to be sorted
     * @param column the column selected to be sorted
     * @param isAscending the sort order
     */
    public static void sortByColumn(ArrayList<Subject> selectedSubjects, String column, boolean isAscending){
        switch (column){
            case "Grade":
                if (isAscending){
                    selectedSubjects.sort(Comparator.comparingInt(Subject::getGrade));
                }else {
                    selectedSubjects.sort((s1, s2) -> s2.getGrade() - s1.getGrade());
                }
                break;
            case "Start Time":
                if (isAscending){
                    selectedSubjects.sort(Comparator.comparing(Subject::getStartTime));
                }else {
                    selectedSubjects.sort((s1, s2) -> s2.getStartTime().compareTo(s1.getStartTime()));
                }
                break;
            case "ID":
                if (isAscending){
                    selectedSubjects.sort(Comparator.comparingInt(Subject::getSubjectNo));
                }else {
                    selectedSubjects.sort((s1, s2) -> s2.getSubjectNo() - s1.getSubjectNo());
                }
                break;
            case "Credit":
                if (isAscending){
                    selectedSubjects.sort(Comparator.comparingInt(s -> ConstantParameters.subjectInfoMap.get(s.getSubjectNo()).getCredit()));
                }else {
                    selectedSubjects.sort((s1, s2) -> ConstantParameters.subjectInfoMap.get(s2.getSubjectNo()).getCredit() - ConstantParameters.subjectInfoMap.get(s1.getSubjectNo()).getCredit());
                }
                break;
        }
    }

}
