package layout;

import ConstantPacket.ConstantParameters;
import util.JsonFileWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static layout.WindowsFrame.*;

public class PersonalPage extends JFrame {
    PanelStyle p1;
    PanelStyle p2;
    PanelStyle p3;
    PanelStyle p4;
    PanelStyle p5;
    LastPanel p6;

    public PersonalPage(){
        iniFrame();
    }
    private void iniFrame(){
        this.setTitle("personal page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponent();
        this.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
        this.setVisible(true);

    }
    class PanelStyle extends JPanel{
        public PanelStyle(){

        }
        public PanelStyle(String tip,String context){
            this.setSize(FRAME_WIDTH,400);
            this.setLayout(new BorderLayout());
            JLabel tipL=new JLabel(tip);
            JLabel contextL=new JLabel(context);
            tipL.setPreferredSize(new Dimension(150,300));
            contextL.setPreferredSize(new Dimension(200,300));
            JButton modify = new JButton("modify");
            this.add("West",tipL);
            this.add("Center",contextL);
            this.add("East",modify);
            tipL.setFont(new Font("Tahoma", Font.PLAIN,20));
            contextL.setFont(new Font("Tahoma", Font.PLAIN,20));
            modify.setFont(new Font("Tahoma", Font.PLAIN,20));
            pack();
            this.setOpaque(false);
            modify.addActionListener(new ActionListener() {//监听器
                @Override
                public void actionPerformed(ActionEvent e) {
                    //弹窗
                    new MyDialog(tip,context);
                }
            });
        }

    }
    class MyDialog extends JDialog {
        public MyDialog(String tip,String context) {
            this.setTitle("modify");
            setVisible(true);
            setBounds(500, 100, 500, 200);

            //Container container = getContentPane();
            Box total = Box.createVerticalBox();

            //第一行
            JPanel p1=new JPanel();

            JLabel tipL = new JLabel(tip);
            tipL.setFont(new Font("Tahoma", Font.PLAIN,20));
            TextField contextL=new TextField(context,20);
            contextL.setFont(new Font("Tahoma", Font.PLAIN,20));
            contextL.setPreferredSize(new Dimension(200,30));
            p1.add(tipL);
            p1.add(contextL);

            //第二行
            JPanel p2=new JPanel();
            JButton back = new JButton("back");
            JButton save = new JButton("save");
            back.setFont(new Font("Tahoma", Font.PLAIN,20));
            save.setFont(new Font("Tahoma", Font.PLAIN,20));
            p2.add(back);
            p2.add(save);

            total.add(p1);

            //密码，多出的第二行
            TextField contextL2=new TextField();
            if(tip.equals("password")){
                JPanel p3=new JPanel();

                JLabel tipL2 = new JLabel("confirm password");
                tipL2.setFont(new Font("Tahoma", Font.PLAIN,20));
                contextL2.setFont(new Font("Tahoma", Font.PLAIN,20));
                contextL2.setPreferredSize(new Dimension(200,30));
                p3.add(tipL2);
                p3.add(contextL2);
                total.add(p3);
            }
            total.add(p2);
            back.addActionListener(new ActionListener() {//监听器
                @Override
                public void actionPerformed(ActionEvent e) {
                    //弹窗
                    dispose();
                }
            });
            save.addActionListener(new ActionListener() {//监听器
                @Override
                public void actionPerformed(ActionEvent e) {
                    //弹窗
                    if(tip.equals("name"))
                        ConstantParameters.studentMap.get(2020001).setName(contextL.getText());

                    else if(tip.equals("StudentNo"))
                        ConstantParameters.studentMap.get(2020001).setStudentNo(Integer.parseInt(contextL.getText()));
                    else if(tip.equals("nickName"))
                        ConstantParameters.studentMap.get(2020001).setNickName(contextL.getText());
                    else if(tip.equals("phoneNumber"))
                        ConstantParameters.studentMap.get(2020001).setPhoneNumber(Integer.parseInt(contextL.getText()));
                    else if(tip.equals("password")){
                        if(contextL.getText().equals(contextL2.getText())&&!contextL.getText().equals(""))
                            ConstantParameters.studentMap.get(2020001).setPassword(contextL.getText());
                        else {
                            JOptionPane.showMessageDialog(null, "Please type in the same password!");
//                            dispose();
//                            new MyDialog(tip,context);
                            contextL2.setText("");
                            return;
                        }
                    }

                    JsonFileWriter.writeStudentJson();
                    dispose();
                    disposeMain();
                    new PersonalPage();

                }
            });


            getContentPane().add(total);



        }
    }
    class LastPanel extends JPanel{
        JButton back = new JButton("back");
        JButton logOut = new JButton("Log out");
        public LastPanel(){

            back.setFont(new Font("Tahoma", Font.PLAIN,20));
            logOut.setFont(new Font("Tahoma", Font.PLAIN,20));
            this.add(back);
            this.add(logOut);
            logOut.addActionListener(new ActionListener() {//监听器
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();


                }
            });
            logOut.addActionListener(new ActionListener() {//监听器
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();

                }
            });
        }



    }
    void disposeMain(){
        this.dispose();
    }
    private  void addComponent(){
        Box total = Box.createVerticalBox();
        this.add(total);
        JLabel welcome = new JLabel("Welcome To Personal Page");
        welcome.setFont(new Font("Papyrus", Font.PLAIN,30));
        p1=new PanelStyle("name",ConstantParameters.studentMap.get(2020001).getName());
        p2=new PanelStyle("StudentNo",Integer.toString(ConstantParameters.studentMap.get(2020001).getStudentNo()));
        p3=new PanelStyle("nickName",ConstantParameters.studentMap.get(2020001).getNickName());
        p4=new PanelStyle("phoneNumber",Integer.toString(ConstantParameters.studentMap.get(2020001).getPhoneNumber()));
        p5=new PanelStyle("password",ConstantParameters.studentMap.get(2020001).getPassword());
        p6=new LastPanel();
        total.add(welcome);
        total.add(p1);
        total.add(p2);
        total.add(p3);
        total.add(p4);
        total.add(p5);
        total.add(p6);

    }
}
