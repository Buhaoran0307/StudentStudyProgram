package layout;

import ConstantPacket.ConstantParameters;
import entity.Subject;
import entity.SubjectInfo;
import util.LayoutUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SubjectPage extends JPanel {
    /**
     * This Box object contains every component.
     */
    public Box vBox1;
    public Box vBox2;
    public Box vBox;
    public Box hBox;
    public JButton back;
    public Subject subject;
    public SubjectInfo subjectInfo;

    {
        this.back = new JButton(" back ");
        this.vBox1 = Box.createVerticalBox();
        this.vBox2 = Box.createVerticalBox();
        this.vBox = Box.createVerticalBox();
        this.hBox = Box.createHorizontalBox();
    }

    public SubjectPage(Subject subject){
        this.subject = subject;
        this.subjectInfo = ConstantParameters.subjectInfoMap.get(subject.getSubjectNo());
        System.out.println("Create SubjectPage...");
        this.vBox.add(Box.createVerticalStrut(50));
        JLabel subjectLabel = new JLabel(subjectInfo.getSubjectName());
        subjectLabel.setFont(new Font(Font.SERIF,Font.BOLD,20));
        subjectLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.vBox.add(subjectLabel);
        this.vBox.add(Box.createVerticalStrut(30));

        JLabel jID1 = new JLabel("Subject ID");
        JLabel jGrade1 = new JLabel("Grade");
        JLabel jCharacter1 = new JLabel("Character");
        JLabel jCredit1 = new JLabel("Credit");
        JLabel jStartTime1 = new JLabel("Start Time");
        JLabel jRank1 = new JLabel("Rank");
        this.vBox1.add(jID1);
        this.vBox1.add(jGrade1);
        this.vBox1.add(jCharacter1);
        this.vBox1.add(jCredit1);
        this.vBox1.add(jStartTime1);
        this.vBox1.add(jRank1);

        HashMap<String,String> mapSubjectInfo = LayoutUtil.getMapSubjectInfo(subject);
        String subjectNo = mapSubjectInfo.get("ID");
        String grade = mapSubjectInfo.get("Grade");
        String character = mapSubjectInfo.get("Character");
        String credit = mapSubjectInfo.get("Credit");
        String startTime = mapSubjectInfo.get("Start Time");
        String rank = mapSubjectInfo.get("Rank");

        /*System.out.println(subjectNo);
        System.out.println(grade);
        System.out.println(character);
        System.out.println(credit);
        System.out.println(startTime);
        System.out.println(rank);*/


        JLabel jID2 = new JLabel(subjectNo);
        JLabel jGrade2 = new JLabel(grade);
        JLabel jCharacter2 = new JLabel(character);
        JLabel jCredit2 = new JLabel(credit);
        JLabel jStartTime2 = new JLabel(startTime);
        JLabel jRank2 = new JLabel(rank);
        this.vBox2.add(jID2);
        this.vBox2.add(jGrade2);
        this.vBox2.add(jCharacter2);
        this.vBox2.add(jCredit2);
        this.vBox2.add(jStartTime2);
        this.vBox2.add(jRank2);

        this.hBox.add(vBox1);
        this.hBox.add(Box.createHorizontalStrut(20));
        this.hBox.add(vBox2);
        this.vBox.add(hBox);
        this.vBox.add(Box.createVerticalStrut(30));
        this.vBox.add(this.back);
        this.back.setAlignmentX(CENTER_ALIGNMENT);
        this.back.addActionListener(e -> WindowsFrame.cardLayout.show(WindowsFrame.cards,"MainPage"));
        this.add(this.vBox);
        System.out.println("Create SubjectPage : [successful]");
    }

}
