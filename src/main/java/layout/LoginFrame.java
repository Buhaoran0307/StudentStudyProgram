package layout;

import ConstantPacket.ConstantParameters;
import util.IOUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JButton forgottenButton;
    private JLabel buptImgLable;

    Container container = this.getContentPane();

    public LoginFrame() {
        System.out.println("[log] Create LoginFrame .......");
        //采用所有元素手动布局的方式
        Image image;
        image = IOUtil.gainImage("src/main/resources/Icon/EStudy.png");
        this.setIconImage(image);
        this.setBounds(520, 30, 450, 600);
        this.setTitle("EStudy");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);
        container.setLayout(null);


        Image buptImage = IOUtil.gainImage("src/main/resources/Icon/BUPT.png");
        buptImgLable = new JLabel(new ImageIcon(buptImage));
        buptImgLable.setLocation(10,10);
        buptImgLable.setSize(51,48);
        container.add(buptImgLable);

        Image QMULImage = IOUtil.gainImage("src/main/resources/Icon/QMUL.png");
        JLabel QMULImgLable = new JLabel(new ImageIcon(QMULImage));
        QMULImgLable.setLocation(240,10);
        QMULImgLable.setSize(190,48);
        container.add(QMULImgLable);

        //set welcome text
        JLabel welcomeLabel = new JLabel("<html>Welcome to<br> student study system!</html>");
        welcomeLabel.setSize(500,100);
        welcomeLabel.setLocation(12,50);
        welcomeLabel.setFont(new Font("Comic Sans Ms", Font.BOLD, 25));
        container.add(welcomeLabel);

        //set Login information
        JLabel infoLabel = new JLabel("Please Log in using your personal information below:");
        infoLabel.setSize(500,200);
        infoLabel.setLocation(15,70);
        infoLabel.setFont(new Font("Comic Sans Ms", Font.BOLD, 15));
        container.add(infoLabel);


        //设置输入框布局
        JPanel textPanel = new JPanel();
        textPanel.setSize(450, 250);
        textPanel.setLocation(10, 160);
        textPanel.setLayout(null);
        container.add(textPanel);
        //username文字布局
        Image userIDImg = IOUtil.gainImage("src/main/resources/Icon/userID.jpg");
        ImageIcon userIDIcon = new ImageIcon(userIDImg);
        JLabel usernameLabel = new JLabel("Username:",userIDIcon,JLabel.LEFT);
        usernameLabel.setFont(new Font("Comic Sans Ms", Font.BOLD, 20));
        usernameLabel.setSize(200, 20);
        usernameLabel.setLocation(50, 50);
        textPanel.add(usernameLabel);

        //username输入框布局
        usernameField = new JTextField();
        usernameField.setFont(new Font("Comic Sans Ms", Font.BOLD, 20));
        usernameField.setSize(300, 40);
        usernameField.setLocation(50, 85);
        textPanel.add(usernameField);

        //password文字布局
        Image passwordImg = IOUtil.gainImage("src/main/resources/Icon/password.jpg");
        ImageIcon passwordIcon = new ImageIcon(passwordImg);
        JLabel passwordLabel = new JLabel("Password:",passwordIcon,JLabel.LEFT);
        passwordLabel.setFont(new Font("Comic Sans Ms", Font.BOLD, 20));
        passwordLabel.setSize(200, 20);
        passwordLabel.setLocation(50, 140);
        textPanel.add(passwordLabel);

        //password输入框布局
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Comic Sans Ms", Font.BOLD, 20));
        passwordField.setSize(300, 40);
        passwordField.setLocation(50, 175);
        textPanel.add(passwordField);

        //设置完成

        //设置button布局
        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(450, 200);
        buttonPanel.setLocation(10, 400);
        buttonPanel.setLayout(null);
        container.add(buttonPanel);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        forgottenButton = new JButton("Forget your password?");
        //添加button布局
        loginButton.setSize(100, 40);
        loginButton.setLocation(60, 10);
        loginButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        loginButton.setBackground(Color.WHITE);

        registerButton.setSize(130, 40);
        registerButton.setLocation(200, 10);
        registerButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        registerButton.setBackground(Color.WHITE);

        forgottenButton.setSize(270, 40);
        forgottenButton.setLocation(60, 80);
        forgottenButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        forgottenButton.setBackground(Color.WHITE);

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(forgottenButton);

        loginButton.addActionListener(new LoginListener());
        registerButton.addActionListener(new RegisterListener());
        forgottenButton.addActionListener(new forgetPasswordListener());

        setVisible(true);
        System.out.println("[log] Create LoginFrame : successful");
    }

    //内部类实现button响应
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
             //验证非空
            String test_password = new String(passwordField.getPassword());
            if(usernameField.getText().equals("")||test_password.equals("")){
                JOptionPane.showMessageDialog(LoginFrame.this, "Warning! None of the blank could be space.");
                return;
            }

            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (authenticate(username, password)) {
                JOptionPane.showMessageDialog(LoginFrame.this, "Login successful!");
                int StudentID = Integer.parseInt(username);
                MainFrame.localUser = ConstantParameters.studentMap.get(StudentID);
                dispose();
                new MainFrame();
            } else {
                JOptionPane.showMessageDialog(LoginFrame.this, "Invalid username or password.");
            }
        }

        //登陆验证
        private boolean authenticate(String username, String password) {
            IOUtil.readJson();
            int username_int = Integer.parseInt(username);
            //是否查询非空，是否登陆成功
            if(ConstantParameters.studentMap.get(username_int) != null){
                String password_from_json = ConstantParameters.studentMap.get(username_int).getPassword();
                return password.equals(password_from_json);
            }
            else return false;
        }

    }
    class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new RegisterFrame();
        }
    }

    class forgetPasswordListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new ForgetPasswordFrame();
        }
    }
}