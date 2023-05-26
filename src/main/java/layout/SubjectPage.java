package layout;
/**
 * A page for the subjects
 * */
import ConstantPacket.ConstantParameters;
import entity.Subject;
import entity.SubjectInfo;
import util.IOUtil;
import util.LayoutUtil;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SubjectPage extends JPanel {
    /**
     * A button to go back
     * */
    public JButton back;
    /**
     * A Subject called subject
     * */
    public Subject subject;
    /**
     * A SubjectInfo called subjectInfo
     * */
    public SubjectInfo subjectInfo;
    /**
     * A map called subjectHelper
     * */
    public Map<String,String> subjectHelper;

    {
        this.back = new JButton(" back ");
        this.subjectHelper = IOUtil.readSubjectHelper();
    }
    /**
     * SubjectPage's constructor
     * @param  subject the Subject
     * */
    public SubjectPage(Subject subject){
        this.setLayout(null);
        this.setBounds(0,0,MainFrame.FRAME_WIDTH,MainFrame.FRAME_HEIGHT);
        this.subject = subject;
        this.subjectInfo = ConstantParameters.subjectInfoMap.get(subject.getSubjectNo());
        System.out.println("Create SubjectPage...");

        JLabel subjectLabel = new JLabel(subjectInfo.getSubjectName());
        subjectLabel.setFont(new Font("Comic Sans Ms",Font.PLAIN, 30));
        int labelWidth = subjectLabel.getPreferredSize().width;
        int labelHeight = subjectLabel.getPreferredSize().height;
        subjectLabel.setBounds((MainFrame.FRAME_WIDTH-labelWidth)/2,40,250,labelHeight);
        this.add(subjectLabel);

        int lx = 255;
        int y = 90;
        int space = 20;
        JLabel jCharacter1 = new JLabel("Character  :");
        JLabel jStartTime1 = new JLabel("Start Time :");
        jCharacter1.setFont(new Font("Comic Sans Ms",Font.PLAIN, 14));
        jStartTime1.setFont(new Font("Comic Sans Ms",Font.PLAIN, 14));
        jCharacter1.setBounds(lx,y,100,20);
        jStartTime1.setBounds(lx,y+space,100,20);
        this.add(jCharacter1);
        this.add(jStartTime1);

        int rx = lx + 110;
        HashMap<String,String> mapSubjectInfo = LayoutUtil.getMapSubjectInfo(subject);
        String character = mapSubjectInfo.get("Character");
        String startTime = mapSubjectInfo.get("Start Time");
        JLabel jCharacter2 = new JLabel(character);
        JLabel jStartTime2 = new JLabel(startTime);
        jCharacter2.setFont(new Font("Comic Sans Ms",Font.PLAIN, 14));
        jStartTime2.setFont(new Font("Comic Sans Ms",Font.PLAIN, 14));
        jCharacter2.setBounds(rx,y,100,20);
        jStartTime2.setBounds(rx,y+space,100,20);
        this.add(jCharacter2);
        this.add(jStartTime2);

        String key = getSubjectKey(subject.getSubjectNo());
        String text = subjectHelper.get(key);
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(new Font("Comic Sans Ms",Font.PLAIN, 14));
        textArea.setBounds(0,0,400,200);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(155,150,400,200);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane);

        this.back.setBounds(300,370,100,30);
        this.back.setFont(new Font("Comic Sans Ms",Font.PLAIN, 14));
        this.back.addActionListener(e -> MainFrame.cardLayout.show(MainFrame.cards,"MainPage"));
        this.add(back);
        System.out.println("Create SubjectPage : [successful]");
    }
    /**
     * the method to get the subject's name
     * @param  subjectNo the number of the subject
     * @return the name of the subject
     * */
    private String getSubjectKey(int subjectNo) {
        switch (subjectNo){
            case 1:
                return "Java Development";
            case 2:
                return "Data Structures";
            case 3:
                return "AI";
            default:
                return "";
        }
    }

}
