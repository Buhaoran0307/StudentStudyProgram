package layout;

import ConstantPacket.ConstantParameters;
import util.DataUtil;
import util.IOUtil;

import javax.swing.*;
import java.awt.*;

import static layout.MainFrame.*;

public class PersonalFrame extends JFrame {
    PanelStyle p1;
    PanelStyle p2;
    PanelStyle p3;
    PanelStyle p4;
    PanelStyle p5;
    LastPanel p6;
    int User;
    //用来返回主页面
    private MainFrame mainFrame;

    public PersonalFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        iniFrame();
    }

    private void iniFrame() {
        this.setTitle("personal page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponent();
        this.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
        this.setVisible(true);
        Image image;
        image = IOUtil.gainImage("src/main/resources/Icon/EStudy.png");
        this.setIconImage(image);
    }

    void disposeMain() {
        this.dispose();
    }

    private void addComponent() {
        User = MainFrame.localUser.getStudentNo();
        Box total = Box.createVerticalBox();
        this.add(total);
        JLabel welcome = new JLabel("Welcome To Personal Page");
        welcome.setFont(new Font("Papyrus", Font.PLAIN, 30));
        p1 = new PanelStyle("name", ConstantParameters.studentMap.get(User).getName());
        p2 = new PanelStyle("StudentNo", Integer.toString(ConstantParameters.studentMap.get(User).getStudentNo()));
        p3 = new PanelStyle("nickName", ConstantParameters.studentMap.get(User).getNickName());
        p4 = new PanelStyle("phoneNumber", Integer.toString(ConstantParameters.studentMap.get(User).getPhoneNumber()));
        p5 = new PanelStyle("password", ConstantParameters.studentMap.get(User).getPassword());
        p6 = new LastPanel();
        total.add(welcome);
        total.add(p2);
        total.add(p1);
        total.add(p3);
        total.add(p4);
        total.add(p5);
        total.add(p6);

    }

    class PanelStyle extends JPanel {
        public PanelStyle() {

        }

        public PanelStyle(String tip, String context) {
            this.setSize(FRAME_WIDTH, 400);
            this.setLayout(new BorderLayout());
            JLabel tipL = new JLabel(tip);
            JLabel contextL = new JLabel(context);
            tipL.setPreferredSize(new Dimension(150, 300));
            contextL.setPreferredSize(new Dimension(200, 300));
            JButton modify = new JButton("modify");

            tipL.setFont(new Font("Tahoma", Font.PLAIN, 20));
            contextL.setFont(new Font("Tahoma", Font.PLAIN, 20));
            modify.setFont(new Font("Tahoma", Font.PLAIN, 20));


            if(tip.equals("password")){
                String a="";
                for(int i=0;i<context.length();i++)a+=".";
                contextL.setText(a);
            }

                //第一项
            this.add("West", tipL);

            //第二项


            this.add("Center", contextL);

            //第三项
            if(!tip.equals("StudentNo"))
                this.add("East", modify);
            pack();
            this.setOpaque(false);
            //监听器
            modify.addActionListener(e -> {
                //弹窗
                new MyDialog(tip, context);
            });
        }

    }

    class MyDialog extends JDialog {
        public MyDialog(String tip, String context) {
            this.setTitle("modify");
            setVisible(true);
            setBounds(500, 100, 500, 200);

            //Container container = getContentPane();
            Box total = Box.createVerticalBox();

            //第一行
            JPanel p1 = new JPanel();

            JLabel tipL = new JLabel(tip);
            tipL.setFont(new Font("Tahoma", Font.PLAIN, 20));
            TextField contextL = new TextField(context, 20);
            contextL.setFont(new Font("Tahoma", Font.PLAIN, 20));
            contextL.setPreferredSize(new Dimension(200, 30));
            JPasswordField passwordField = new JPasswordField();
            passwordField.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
            passwordField.setPreferredSize(new Dimension(200, 30));



            p1.add(tipL);
            if(tip.equals("password"))p1.add(passwordField);
            else p1.add(contextL);

            //第二行
            JPanel p2 = new JPanel();
            JButton back = new JButton("back");
            JButton save = new JButton("save");
            back.setFont(new Font("Tahoma", Font.PLAIN, 20));
            save.setFont(new Font("Tahoma", Font.PLAIN, 20));
            p2.add(back);
            p2.add(save);

            total.add(p1);

            //密码，多出的第二行
            TextField contextL2 = new TextField();
            JPasswordField passwordField2=new JPasswordField();;
            if (tip.equals("password")) {
                JPanel p3 = new JPanel();

                JLabel tipL2 = new JLabel("confirm password");
                tipL2.setFont(new Font("Tahoma", Font.PLAIN, 20));
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
                switch (tip) {
                    case "name":
                        ConstantParameters.studentMap.get(User).setName(contextL.getText());
                        break;
                    case "StudentNo":
                        ConstantParameters.studentMap.get(User).setStudentNo(Integer.parseInt(contextL.getText()));
                        break;
                    case "nickName":
                        ConstantParameters.studentMap.get(User).setNickName(contextL.getText());
                        break;
                    case "phoneNumber":
                        if(DataUtil.checkPhoneNumber(contextL.getText()))
                        ConstantParameters.studentMap.get(User).setPhoneNumber(Integer.parseInt(contextL.getText()));
                        else {
                            JOptionPane.showMessageDialog(null, "Please type in the correct phone number!");
//                            dispose();
//                            new MyDialog(tip,context);
                            contextL.setText("");
                            return;
                        }
                        break;
                    case "password":
                        System.out.println(passwordField.getPassword());
                        System.out.println(passwordField2.getPassword());
                        // && !passwordField.getPassword().equals("")
                        if (new String(passwordField.getPassword()).equals(new String(passwordField2.getPassword())) )
                            ConstantParameters.studentMap.get(User).setPassword(new String(passwordField2.getPassword()));
                        else if (!DataUtil.checkPassword(new String(passwordField.getPassword()))) {
                            JOptionPane.showMessageDialog(null, "Please type in the password in correct form!");
//                            dispose();
//                            new MyDialog(tip,context);
                            passwordField2.setText("");
                            return;

                        } else {
                            JOptionPane.showMessageDialog(null, "Please type in the same password!");
//                            dispose();
//                            new MyDialog(tip,context);
                            passwordField2.setText("");
                            return;
                        }
                        break;
                }

                IOUtil.writeStudentJson();
                dispose();
                disposeMain();
                new PersonalFrame(mainFrame);

            });
            getContentPane().add(total);
        }
    }

    class LastPanel extends JPanel {
        JButton back = new JButton("back");
        JButton logOut = new JButton("Log out");

        public LastPanel() {

            back.setFont(new Font("Tahoma", Font.PLAIN, 20));
            logOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
            this.add(back);
            this.add(logOut);
            //监听器
            back.addActionListener(e -> {
                dispose();
                mainFrame.setVisible(true);
            });
            //监听器
            logOut.addActionListener(e ->{
                new LoginFrame();
                MainFrame.localUser=null;
                mainFrame.dispose();
                dispose();
            } );
        }


    }
}
