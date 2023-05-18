package entity;

import java.util.ArrayList;

public class StudentAward {
    private ArrayList<String> achievement;
    private ArrayList<String> role;
    public StudentAward(){}
    public StudentAward(ArrayList<String> achievement, ArrayList<String> role) {
        this.achievement=achievement;
        this.role=role;
    }
    public ArrayList<String> getAchievement() {
        return achievement;
    }

    public void setAchievement(ArrayList<String> achievement) {
        this.achievement = achievement;
    }

    public ArrayList<String> getRole() {
        return role;
    }

    public void setRole(ArrayList<String> role) {
        this.role = role;
    }

    @Override
    public String toString() {
        String s="";
        for(String a : achievement){
            s=s+a+" ";
        }
        return s;
    }
}
