package entity;
/**
 * This class is about one of the subject the student selects
 * */
public class Subject {
    /**
     * The subject's number
     * */
    private int subjectNo;
    /**
     * The start time of this subject
     * */
    private String startTime;
    /**
     * The grade in the exam for this subject the student gains
     * */
    private int grade;
    /**
     * The ranking of the student's garde in the exam for this subject
     * */
    private int rank;

    public Subject(){}
    /**
     * Subject class's constructor
     * @param subjectNo the subject's number
     * @param startTime the start time of this subject
     * @param grade the grade in the exam for this subject the student gains
     * @param rank the ranking of the student's garde in the exam for this subject
     * */
    public Subject(int subjectNo, String startTime, int grade, int rank) {
        this.subjectNo = subjectNo;
        this.startTime = startTime;
        this.grade = grade;
        this.rank = rank;
    }
    /**
     * Get the subject's number
     * @return the subject's number
     * */
    public int getSubjectNo() {
        return subjectNo;
    }
    /**
     * Set the student's name
     * @param subjectNo the subject's number you want to set
     * */
    public void setSubjectNo(int subjectNo) {
        this.subjectNo = subjectNo;
    }
    /**
     * Get the start time of this subject
     * @return the start time of this subject
     * */
    public String getStartTime() {
        return startTime;
    }
    /**
     * Set the start time of this subject
     * @param startTime the start time of this subject you want to set
     * */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    /**
     * Get the grade in the exam for this subject the student gains
     * @return the grade in the exam for this subject the student gains
     * */
    public int getGrade() {
        return grade;
    }
    /**
     * Set the grade in the exam for this subject the student gains
     * @param grade the grade in the exam for this subject the student gains
     * */
    public void setGrade(int grade) {
        this.grade = grade;
    }
    /**
     * Get the ranking of the student's garde in the exam for this subject
     * @return the ranking of the student's garde in the exam for this subject
     * */
    public int getRank() {
        return rank;
    }
    /**
     * Set the ranking of the student's garde in the exam for this subject
     * @param rank set the ranking of the student's garde in the exam for this subject
     * */
    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    /**
     * Put the information of this subject into a string
     * @return this string
     * */
    public String toString() {
        return "Subject{" +
                "subjectNo=" + subjectNo +
                ", startTime='" + startTime + '\'' +
                ", grade=" + grade +
                ", rank=" + rank +
                '}';
    }
}
