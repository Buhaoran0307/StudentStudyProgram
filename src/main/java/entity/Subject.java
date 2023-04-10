package entity;

public class Subject {
    private int subjectNo;
    private String startTime;
    private int grade;
    private int rank;

    public Subject(){}
    public Subject(int subjectNo, String startTime, int grade, int rank) {
        this.subjectNo = subjectNo;
        this.startTime = startTime;
        this.grade = grade;
        this.rank = rank;
    }

    public int getSubjectNo() {
        return subjectNo;
    }

    public void setSubjectNo(int subjectNo) {
        this.subjectNo = subjectNo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectNo=" + subjectNo +
                ", startTime='" + startTime + '\'' +
                ", grade=" + grade +
                ", rank=" + rank +
                '}';
    }
}
