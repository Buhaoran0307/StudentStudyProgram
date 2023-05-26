package ConstantPacket;

import entity.Student;
import entity.StudentAward;
import entity.SubjectInfo;

import java.util.Map;

/**
 * Constant parameters
 */
public  class ConstantParameters {
    /**
     * contain all subject information
     */
    public static Map<Integer, SubjectInfo> subjectInfoMap;
    /**
     * contain all students' awards information
     */
    public static Map<Integer, StudentAward> studentAwardMap;
    /**
     * contain all students' information
     */
    public static Map<Integer, Student> studentMap;

}
