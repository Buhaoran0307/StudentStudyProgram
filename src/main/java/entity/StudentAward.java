package entity;
/**
 * This class includes the awards the student has gained
 * */
import java.util.ArrayList;

public class StudentAward {
    /**
     * The achievements the student has get
     * */
    private ArrayList<String> achievement;
    /**
     * The position held by the student
     * */
    private ArrayList<String> role;
    public StudentAward(){}
    /**
     * StudentAward class's constructor
     * @param achievement The achievements the student has get
     * @param role The position held by the student
     * */
    public StudentAward(ArrayList<String> achievement, ArrayList<String> role) {
        this.achievement=achievement;
        this.role=role;
    }
    /**
     * Get the list achievement
     * @return the list achievement
     * */
    public ArrayList<String> getAchievement() {
        return achievement;
    }
    /**
     * Set the list achievement
     * @param achievement the list achievement you want to set
     * */
    public void setAchievement(ArrayList<String> achievement) {
        this.achievement = achievement;
    }
    /**
     * Get the list role
     * @return the list role
     * */
    public ArrayList<String> getRole() {
        return role;
    }
    /**
     * Set the list achievement
     * @param role the list role you want to set
     * */
    public void setRole(ArrayList<String> role) {
        this.role = role;
    }

    @Override
    /**
     * Put the student's achievements into a string
     * @return this string
     * */
    public String toString() {
        String s="";
        for(String a : achievement){
            s=s+a+" ";
        }
        return s;
    }
}
