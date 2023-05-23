package entity;
/**
 * This class includes the information about the subject
 * */
public class SubjectInfo {
    /**
     * The subject's number
     * */
    private int subjectNo;
    /**
     * The subject's name
     * */
    private String subjectName;
    /**
     * Credit for this subject
     * */
    private int credit;
    /**
     * This subject is required or optional
     * */
    private String character;

    public SubjectInfo(){}

    @Override
    /**
     * Put the information of this subject into a string
     * @return this string
     * */
    public String toString() {
        return "SubjectInfo{" +
                "subjectNo=" + subjectNo +
                ", subjectName='" + subjectName + '\'' +
                ", credit=" + credit +
                ", character='" + character + '\'' +
                '}';
    }
    /**
     * SubjectInfo class's constructor
     * @param subjectNo the subject's name
     * @param subjectName the subject's student number
     * @param credit the
     * @param character the
     * */
    public SubjectInfo(int subjectNo, String subjectName, int credit, String character) {
        this.subjectNo = subjectNo;
        this.subjectName = subjectName;
        this.credit = credit;
        this.character = character;
    }
    /**
     * Get the subject's number
     * @return the subject's number
     * */
    public int getSubjectNo() {
        return subjectNo;
    }
    /**
     * Get the subject's name
     * @return the subject's name
     * */
    public String getSubjectName() {
        return subjectName;
    }
    /**
     * Get the
     * @return the
     * */
    public int getCredit() {
        return credit;
    }
    /**
     * Get the
     * @return the
     * */
    public String getCharacter() {
        return character;
    }
    /**
     * Set the subject's number
     * @param subjectNo the subject's number you want to set
     * */
    public void setSubjectNo(int subjectNo) {
        this.subjectNo = subjectNo;
    }
    /**
     * Set the subject's name
     * @param subjectName the subject's name you want to set
     * */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    /**
     * Set the
     * @param credit the you want to set
     * */
    public void setCredit(int credit) {
        this.credit = credit;
    }
    /**
     * Set the
     * @param character the you want to set
     * */
    public void setCharacter(String character) {
        this.character = character;
    }
}
