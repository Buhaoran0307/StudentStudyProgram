package entity;

public class SubjectInfo {
    private int subjectNo;
    private String subjectName;
    private int credit;
    private String character;

    public SubjectInfo(){}

    @Override
    public String toString() {
        return "SubjectInfo{" +
                "subjectNo=" + subjectNo +
                ", subjectName='" + subjectName + '\'' +
                ", credit=" + credit +
                ", character='" + character + '\'' +
                '}';
    }

    public SubjectInfo(int subjectNo, String subjectName, int credit, String character) {
        this.subjectNo = subjectNo;
        this.subjectName = subjectName;
        this.credit = credit;
        this.character = character;
    }

    public int getSubjectNo() {
        return subjectNo;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getCredit() {
        return credit;
    }

    public String getCharacter() {
        return character;
    }

    public void setSubjectNo(int subjectNo) {
        this.subjectNo = subjectNo;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}
