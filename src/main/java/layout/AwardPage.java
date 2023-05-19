package layout;

import ConstantPacket.ConstantParameters;
import util.IOUtil;
import util.LayoutUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AwardPage extends JPanel {
    private ArrayList<String> achievement;
    private ArrayList<String> role;
    public JButton back= new JButton(" back ");
    public AwardPage(int studentID) {
        IOUtil.readAwardHelper();
        this.setLayout(null);
        this.setBounds(0, 0, MainFrame.FRAME_WIDTH, MainFrame.FRAME_HEIGHT);
        //System.out.println(studentID);
        this.role = ConstantParameters.studentAwardMap.get(studentID).getRole();
        this.achievement = ConstantParameters.studentAwardMap.get(studentID).getAchievement();
        //System.out.println();
        System.out.println("Create AwardPage...");

        JLabel studentLabel = new JLabel(ConstantParameters.studentMap.get(studentID).getName()+"'s Award and Roles undertaken");
        studentLabel.setFont(new Font("Comic Sans Ms", Font.PLAIN, 30));
        int labelWidth = studentLabel.getPreferredSize().width;
        int labelHeight = studentLabel.getPreferredSize().height;
        studentLabel.setBounds((MainFrame.FRAME_WIDTH - labelWidth) / 3, 40, 800, labelHeight);
        this.add(studentLabel);

        int lx = 100;
        int y = 80;
        int space = 300;
        JLabel jAward1 = new JLabel("Award  :");
        jAward1.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        jAward1.setBounds(lx, y, 300, 30);
        this.add(jAward1);
        String awards="";
        for(String a:achievement){
            awards+=" "+a+"\n";

        }
        if(awards.equals(""))awards="Not found anything";
        JTextArea textArea = new JTextArea(awards);
        textArea.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        textArea.setBounds(0, 0, 400, 100);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(155, 120, 400, 100);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane);

        JLabel jrole = new JLabel("Roles undertaken :");
        jrole.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        jrole.setBounds(lx, 220, 500, 30);
        this.add(jrole);
        String roles="";
        for(String a:role){
            roles+=" "+a+"\n";

        }
        if(roles.equals(""))roles="Not found anything";
        System.out.println(roles);
        JTextArea textArea2 = new JTextArea(roles);
        textArea2.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        textArea2.setBounds(0, 0, 400, 100);
        textArea2.setLineWrap(true);
        textArea2.setWrapStyleWord(true);
        JScrollPane scrollPane2 = new JScrollPane(textArea2);
        scrollPane2.setBounds(155, 250, 400, 100);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane2);


        this.back.setBounds(300, 370, 100, 30);
        this.back.setFont(new Font("Comic Sans Ms", Font.PLAIN, 14));
        this.back.addActionListener(e -> MainFrame.cardLayout.show(MainFrame.cards, "MainPage"));
        this.add(back);
        System.out.println("Create AwardPage : [successful]");
    }
}
