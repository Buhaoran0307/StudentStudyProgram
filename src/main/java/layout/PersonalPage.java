package layout;

import ConstantPacket.ConstantParameters;
import util.DataUtil;
import util.IOUtil;

import javax.swing.*;
import java.awt.*;

public class PersonalPage extends JPanel{
    private final ImagePanel personalImage;
    {
        Image image = IOUtil.gainImage("src/main/resources/Icon/defaultUserIcon.png");
        personalImage = new ImagePanel(image);
    }
    public PersonalPage(MainFrame mainFrame){
        this.setLayout(null);
        JPanel LPanel = new JPanel();
        LPanel.setLayout(null);
        personalImage.setImageIcon(new ImageIcon());
        personalImage.setBounds(55,30,60,60);
        LPanel.add(personalImage);

        int space = 60;
        int lx = 40;
        int ly = 150;
        // Left JPanel
        JLabel welcome = new JLabel("Welcome !");
        welcome.setFont(new Font("Comic Sans Ms",Font.PLAIN, 18));
        welcome.setBounds(45,100,90,30);
        LPanel.add(welcome);
        Label l1 = new Label("StudentNo :");
        Label l2 = new Label("Name      :");
        Label l3 = new Label("NickName  :");
        Label l4 = new Label("Phone     :");
        Label l5 = new Label("Password  :");
        l1.setFont(new Font("Comic Sans Ms",Font.PLAIN, 20));
        l2.setFont(new Font("Comic Sans Ms",Font.PLAIN, 20));
        l3.setFont(new Font("Comic Sans Ms",Font.PLAIN, 20));
        l4.setFont(new Font("Comic Sans Ms",Font.PLAIN, 20));
        l5.setFont(new Font("Comic Sans Ms",Font.PLAIN, 20));
        l1.setBounds(lx,ly,120,50);
        l2.setBounds(lx,ly+space,120,50);
        l3.setBounds(lx,ly+2*space,120,50);
        l4.setBounds(lx,ly+3*space,120,50);
        l5.setBounds(lx,ly+4*space,120,50);
        LPanel.add(l1);
        LPanel.add(l2);
        LPanel.add(l3);
        LPanel.add(l4);
        LPanel.add(l5);


        // Right Up JPanel
        JPanel upPanel = new JPanel();
        upPanel.setLayout(null);
        Image buptImage = IOUtil.gainImage("src/main/resources/Icon/BUPT1.png");
        JLabel buptImgLable = new JLabel(new ImageIcon(buptImage));
        buptImgLable.setLocation(15,10);
        buptImgLable.setSize(110,30);
        upPanel.add(buptImgLable);
        Image QMULImage = IOUtil.gainImage("src/main/resources/Icon/QMUL1.png");
        JLabel QMULImgLable = new JLabel(new ImageIcon(QMULImage));
        QMULImgLable.setLocation(380,10);
        QMULImgLable.setSize(110,30);
        upPanel.add(QMULImgLable);
        JLabel jLabel1 = new JLabel("Here below is your personal information. ");
        JLabel jLabel2 = new JLabel("Your can check, modify your info or log out.");
        jLabel1.setFont(new Font("Comic Sans Ms",Font.PLAIN, 20));
        jLabel2.setFont(new Font("Comic Sans Ms",Font.PLAIN, 20));
        jLabel1.setBounds(45,45,500,50);
        jLabel2.setBounds(45,75,500,50);
        upPanel.add(jLabel1);
        upPanel.add(jLabel2);

        // down left JPanel
        JPanel dlPanel = new JPanel();
        dlPanel.setLayout(null);
        int iy = 5;
        InfoJPanel i1 = new InfoJPanel("StudentNo", Integer.toString(MainFrame.localUser.getStudentNo()),false);
        InfoJPanel i2 = new InfoJPanel("name", MainFrame.localUser.getName(),true);
        InfoJPanel i3 = new InfoJPanel("nickName", MainFrame.localUser.getNickName(),true);
        InfoJPanel i4 = new InfoJPanel("phoneNumber", Integer.toString(MainFrame.localUser.getPhoneNumber()),true);
        InfoJPanel i5 = new InfoJPanel("password", MainFrame.localUser.getPassword(),true);
        i1.setFont(new Font("Comic Sans Ms",Font.PLAIN, 20));
        i2.setFont(new Font("Comic Sans Ms",Font.PLAIN, 20));
        i3.setFont(new Font("Comic Sans Ms",Font.PLAIN, 20));
        i4.setFont(new Font("Comic Sans Ms",Font.PLAIN, 20));
        i5.setFont(new Font("Comic Sans Ms",Font.PLAIN, 20));
        i1.setLocation(0,iy);
        i2.setLocation(0,iy+ space);
        i3.setLocation(0,iy+2*space);
        i4.setLocation(0,iy+3*space);
        i5.setLocation(0,iy+4*space);
        dlPanel.add(i1);
        dlPanel.add(i2);
        dlPanel.add(i3);
        dlPanel.add(i4);
        dlPanel.add(i5);

        // down right JPanel
        JPanel drPanel = new JPanel();
        drPanel.setLayout(null);
        int rx = 13;
        int ry = 30;
        int rSpace = 25;
        int rlSize = 18;
        int rlWidth = 300;
        Label l6 = new Label("To modify your");
        Label l7 = new Label("information, you also");
        Label l8 = new Label("need to follow basic");
        Label l9 = new Label("rules, make sure your");
        Label l10 = new Label("modification is valid.");
        Label l11 = new Label("Thanks, and good ");
        Label l12 = new Label("luck!");
        l6.setFont(new Font("Comic Sans Ms",Font.PLAIN, rlSize));
        l7.setFont(new Font("Comic Sans Ms",Font.PLAIN, rlSize));
        l8.setFont(new Font("Comic Sans Ms",Font.PLAIN, rlSize));
        l9.setFont(new Font("Comic Sans Ms",Font.PLAIN, rlSize));
        l10.setFont(new Font("Comic Sans Ms",Font.PLAIN, rlSize));
        l11.setFont(new Font("Comic Sans Ms",Font.PLAIN, rlSize));
        l12.setFont(new Font("Comic Sans Ms",Font.PLAIN, rlSize));
        l6.setBounds(rx,ry,rlWidth,rlSize);
        l7.setBounds(rx,ry+rSpace,rlWidth,rlSize);
        l8.setBounds(rx,ry+2*rSpace,rlWidth,rlSize);
        l9.setBounds(rx,ry+3*rSpace,rlWidth,rlSize);
        l10.setBounds(rx,ry+4*rSpace,rlWidth,rlSize);
        int by = ry+4*rSpace;
        l11.setBounds(rx,by+60,rlWidth,rlSize);
        l12.setBounds(rx,by+60+rSpace,rlWidth,rlSize);
        ButtonPanel buttonPanel = new ButtonPanel(mainFrame);
        buttonPanel.setLocation(0,by+55+rSpace+47);
        drPanel.add(l6);
        drPanel.add(l7);
        drPanel.add(l8);
        drPanel.add(l9);
        drPanel.add(l10);
        drPanel.add(l11);
        drPanel.add(l12);
        drPanel.add(buttonPanel);


        JSplitPane downPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, dlPanel, drPanel);
        downPanel.setResizeWeight(0.6);
        downPanel.setDividerSize(2);
        downPanel.setEnabled(false);
        downPanel.setBorder(null);

        JSplitPane RPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upPanel, downPanel);
        RPanel.setResizeWeight(0.3);
        RPanel.setDividerSize(2);
        RPanel.setEnabled(false);
        RPanel.setBorder(null);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, LPanel, RPanel);
        splitPane.setResizeWeight(0.25);
        splitPane.setDividerSize(2);
        splitPane.setEnabled(false);
        splitPane.setBorder(null);
        splitPane.setBounds(0,0,700,480);

        this.add(splitPane);
    }
}

class InfoJPanel extends JPanel {
    private final String label;
    private String context;
    private final JLabel contextL;

    public InfoJPanel(String tip, String context, boolean isEditable) {
        this.setSize(315,50);
        this.setLayout(null);
        this.label = tip;
        this.context = context;
        this.contextL = new JLabel(this.context);
        contextL.setPreferredSize(new Dimension(200, 300));
        JButton modify = new JButton("Modify");

        contextL.setFont(new Font("Comic Sans Ms",Font.PLAIN, 20));
        modify.setFont(new Font("Comic Sans Ms",Font.PLAIN, 20));

        if(label.equals("password")){
            StringBuilder a= new StringBuilder();
            for(int i=0;i<10;i++) a.append("*");
            contextL.setText(a.toString());
        }

        // context
        contextL.setBounds(30,0,200,50);
        this.add(contextL);

        // modify
        modify.setBounds(195,12,105,30);
        modify.setEnabled(isEditable);
        this.add(modify);


        // ActionListener
        modify.addActionListener(e -> {
            // Dialog
            new MyDialog(this);
        });
    }

    public void refreshContext(String context){
        this.context = context;
        this.contextL.setText(this.context);
    }
    public String getLabel() {
        return label;
    }
    public String getContext(){return context;}
}

class MyDialog extends JDialog {
    public MyDialog(InfoJPanel infoJPanel) {
        int User;
        User = MainFrame.localUser.getStudentNo();
        Image image;
        image = IOUtil.gainImage("src/main/resources/Icon/EStudy.png");
        this.setIconImage(image);
        this.setTitle("Modify "+infoJPanel.getLabel());
        this.setVisible(true);
        this.setBounds(500, 100, 500, 200);

        //Container container = getContentPane();
        Box total = Box.createVerticalBox();

        //第一行
        JPanel p1 = new JPanel();

        JLabel tipL = new JLabel(infoJPanel.getLabel());
        tipL.setFont(new Font("Comic Sans Ms",Font.PLAIN, 20));
        TextField contextL = new TextField(infoJPanel.getContext(), 20);
        contextL.setFont(new Font("Comic Sans Ms",Font.PLAIN, 20));
        contextL.setPreferredSize(new Dimension(200, 30));
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        passwordField.setPreferredSize(new Dimension(200, 30));

        p1.add(tipL);
        if(infoJPanel.getLabel().equals("password"))p1.add(passwordField);
        else p1.add(contextL);

        // second row
        JPanel p2 = new JPanel();
        JButton back = new JButton("back");
        JButton save = new JButton("save");
        back.setFont(new Font("Comic Sans Ms",Font.PLAIN, 20));
        save.setFont(new Font("Comic Sans Ms",Font.PLAIN, 20));
        p2.add(back);
        p2.add(save);

        total.add(p1);

        JPasswordField passwordField2=new JPasswordField();
        if (infoJPanel.getLabel().equals("password")) {
            JPanel p3 = new JPanel();

            JLabel tipL2 = new JLabel("confirm password");
            tipL2.setFont(new Font("Comic Sans Ms",Font.PLAIN, 20));
            passwordField2.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
            passwordField2.setPreferredSize(new Dimension(200, 30));
            p3.add(tipL2);
            p3.add(passwordField2);
            total.add(p3);
        }
        total.add(p2);
        //监听器
        back.addActionListener(e -> {
            //弹窗
            dispose();
        });
        //监听器
        save.addActionListener(e -> {
            //弹窗
            switch (infoJPanel.getLabel()) {
                case "name":
                    ConstantParameters.studentMap.get(User).setName(contextL.getText());
                    infoJPanel.refreshContext(contextL.getText());
                    break;
                case "StudentNo":
                    ConstantParameters.studentMap.get(User).setStudentNo(Integer.parseInt(contextL.getText()));
                    infoJPanel.refreshContext(contextL.getText());
                    break;
                case "nickName":
                    ConstantParameters.studentMap.get(User).setNickName(contextL.getText());
                    infoJPanel.refreshContext(contextL.getText());
                    break;
                case "phoneNumber":
                    if(DataUtil.checkPhoneNumber(contextL.getText())){
                        ConstantParameters.studentMap.get(User).setPhoneNumber(Integer.parseInt(contextL.getText()));
                        infoJPanel.refreshContext(contextL.getText());
                    }else {
                        JOptionPane.showMessageDialog(null, "Please type in the correct phone number!");
                        contextL.setText("");
                        return;
                    }
                    break;
                case "password":
                    if (new String(passwordField.getPassword()).equals(new String(passwordField2.getPassword()))){
                        ConstantParameters.studentMap.get(User).setPassword(new String(passwordField2.getPassword()));
                        infoJPanel.refreshContext(contextL.getText());
                    } else if (!DataUtil.checkPassword(new String(passwordField.getPassword()))) {
                        JOptionPane.showMessageDialog(null, "Please type in the password in correct form!");
                        passwordField2.setText("");
                        return;

                    } else {
                        JOptionPane.showMessageDialog(null, "Please type in the same password!");
                        passwordField2.setText("");
                        return;
                    }
                    break;
            }
            IOUtil.writeStudentJson();
            dispose();
        });
        getContentPane().add(total);
    }
}

class ButtonPanel extends JPanel {
    JButton back = new JButton("back");
    JButton logOut = new JButton("Log out");
    public ButtonPanel(MainFrame mainFrame) {
        this.setLayout(null);
        this.setSize(195,30);
        back.setFont(new Font("Comic Sans Ms",Font.PLAIN, 15));
        logOut.setFont(new Font("Comic Sans Ms",Font.PLAIN, 15));
        back.setBounds(7,0,86,30);
        logOut.setBounds(102,0,86,30);
        this.add(back);
        this.add(logOut);
        //监听器
        back.addActionListener(e -> MainFrame.cardLayout.show(MainFrame.cards,"MainPage"));
        //监听器
        logOut.addActionListener(e ->{
            MainFrame.localUser=null;
            mainFrame.dispose();
            new LoginFrame();
        } );
    }
}