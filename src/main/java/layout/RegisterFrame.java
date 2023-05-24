package layout;

import ConstantPacket.ConstantParameters;
import com.google.gson.Gson;
import entity.Student;
import util.DataUtil;
import util.IOUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterFrame extends JFrame{
    private JTextField name;
    private JTextField nickname;
    private JTextField phone;
    private JTextField password;

    private JButton signUp;
    private JButton goBack;
    Container container = this.getContentPane();

    /**
     * Constructor for Register Page
     */
    public RegisterFrame(){

        Image buptImage = IOUtil.gainImage("src/main/resources/Icon/BUPT.png");
        JLabel buptImgLable = new JLabel(new ImageIcon(buptImage));
        buptImgLable.setLocation(10,10);
        buptImgLable.setSize(51,48);
        container.add(buptImgLable);

        Image QMULImage = IOUtil.gainImage("src/main/resources/Icon/QMUL.png");
        JLabel QMULImgLable = new JLabel(new ImageIcon(QMULImage));
        QMULImgLable.setLocation(240,10);
        QMULImgLable.setSize(190,48);
        container.add(QMULImgLable);

        //set Login information
        JLabel infoLabel = new JLabel("<html>Hello,the news!<br> Please to enter the information below to " +
                "sign up.</html>");
        infoLabel.setSize(500,150);
        infoLabel.setLocation(35,20);
        infoLabel.setFont(new Font("Comic Sans Ms", Font.BOLD, 15));
        container.add(infoLabel);

        //All elements layouts are handcrafted.
        this.setBounds(520,30,450,600);
        this.setTitle("Register");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);
        container.setLayout(null);

//   //Setting the layout of statements
        JPanel textPanel = new JPanel();
        textPanel.setSize(450,400);
        textPanel.setLocation(10,85);
        textPanel.setLayout(null);
        container.add(textPanel);
        //layout for "username"
        Image userIDImg = IOUtil.gainImage("src/main/resources/Icon/userID.jpg");
        ImageIcon userIDIcon = new ImageIcon(userIDImg);
        JLabel usernameLabel = new JLabel("Name:",userIDIcon,JLabel.LEFT);
        usernameLabel.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
        usernameLabel.setSize(200,20);
        usernameLabel.setLocation(50,50);
        textPanel.add(usernameLabel);

        //layout for "username enter block"
        name = new JTextField();
        name.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
        name.setSize(300,40);
        name.setLocation(50,85);
        textPanel.add(name);

        //layout for "nickname"
        Image nickNameImg = IOUtil.gainImage("src/main/resources/Icon/NickName.jpg");
        ImageIcon nickNameIcon = new ImageIcon(nickNameImg);
        JLabel nicknameLabel = new JLabel("Nickname:",nickNameIcon,JLabel.LEFT);
        nicknameLabel.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
        nicknameLabel.setSize(200,20);
        nicknameLabel.setLocation(50,140);
        textPanel.add(nicknameLabel);

        //layout for "nickname enter block"
        nickname = new JTextField();
        nickname.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
        nickname.setSize(300,40);
        nickname.setLocation(50,175);
        textPanel.add(nickname);

        //layout for "phone"
        Image phoneImg = IOUtil.gainImage("src/main/resources/Icon/phone.jpg");
        ImageIcon phoneIcon = new ImageIcon(phoneImg);
        JLabel phoneLabel = new JLabel("Phone No.:",phoneIcon,JLabel.LEFT);
        phoneLabel.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
        phoneLabel.setSize(200,20);
        phoneLabel.setLocation(50,230);
        textPanel.add(phoneLabel);

        //layout for "phone enter block"
        phone = new JTextField();
        phone.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
        phone.setSize(300,40);
        phone.setLocation(50,265);
        textPanel.add(phone);

        //layout for "password"
        Image passwordImg = IOUtil.gainImage("src/main/resources/Icon/password.jpg");
        ImageIcon passwordIcon = new ImageIcon(passwordImg);
        JLabel passwordLable = new JLabel("Password:",passwordIcon,JLabel.LEFT);
        passwordLable.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
        passwordLable.setSize(200,20);
        passwordLable.setLocation(50,320);
        textPanel.add(passwordLable);

        //layout for "password enter block"
        password = new JPasswordField();
        password.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
        password.setSize(300,40);
        password.setLocation(50,355);
        textPanel.add(password);
  //    //setting over
  //    //layout for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(450,200);
        buttonPanel.setLocation(10,485);
        buttonPanel.setLayout(null);
        container.add(buttonPanel);

        //add buttons
        signUp = new JButton("Sign Up");
        signUp.setSize(140,40);
        signUp.setLocation(200,30);
        signUp.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        signUp.setBackground(Color.WHITE);

        //add "goback" button
        goBack = new JButton("Go Back");
        goBack.setSize(140,40);
        goBack.setLocation(50,30);
        goBack.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        goBack.setBackground(Color.WHITE);

        //add action listeners
        signUp.addActionListener(new signUpListener());
        goBack.addActionListener(new goBackListener());
        buttonPanel.add(signUp);
        buttonPanel.add(goBack);

        //    //setting over
        this.setVisible(true);
    }

//    //button action  listener classes

    /**
     * Action Listener for sign up button
     */
    class signUpListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //to authenticate if the inputs are empty
            if(name.getText().equals("")||nickname.getText().equals("")||password.getText().equals("")||phone.getText().equals("")){
                JOptionPane.showMessageDialog(RegisterFrame.this, "Warning! None of the blank could be space.");
                return;
            }
            //to authenticate if phone number is valid
            try{
                long phoneNumber = Long.parseLong(phone.getText());
            }catch (Exception err){
                JOptionPane.showMessageDialog(RegisterFrame.this, "Warning! Your phone number should be pure numbers!");
                return;
            }
            if(lenth(Long.parseLong(phone.getText()))!=8){
                JOptionPane.showMessageDialog(RegisterFrame.this, "Warning! The lenth of your phone number is invalid!");
                return;
            }
            //to authenticate if password is valid
            String test_password = password.getText();
            if(!DataUtil.checkPassword(test_password)){
                System.out.println(test_password);
                JOptionPane.showMessageDialog(RegisterFrame.this, "Warning! The password should contain at least a capital letter, a lower case letter and a number!");
                return;
            }
            String name_from_text = name.getText();
            int phoneNumber_from_text = Integer.parseInt(phone.getText());
            String nickName_from_text = nickname.getText();
            String password_from_text = password.getText();

            //Polling and gives out the student ID
            IOUtil.readJson();
            int temp_username=2020001;
            while(ConstantParameters.studentMap.get(temp_username)!=null){
                temp_username++;
            }
            Student student = new Student(name_from_text,temp_username,phoneNumber_from_text,nickName_from_text,password_from_text);
            ConstantParameters.studentMap.put(temp_username,student);
            System.out.println(ConstantParameters.studentMap.size());

            //write back to json
            Gson gson = new Gson();
            String studentJson = gson.toJson(ConstantParameters.studentMap);
            try {
                FileWriter fileWriter = new FileWriter("src/main/java/DataSet/student.json");
                fileWriter.write(studentJson);
                fileWriter.flush();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            JOptionPane.showMessageDialog(null, "Successfully Registered!\nYour id is "+temp_username+".");
            dispose();
            new LoginFrame();
        }
    }
    /**
     * Action Listener for Go Back button
     */
    class goBackListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            dispose();
            new LoginFrame();
        }
    }

    /**
     * To calculate the length of an integer
     * @param number the number which is needed to calculate
     * @return the length of the number.
     */
    public int lenth(long number){
        int count = 0;
        while(number!=0){
            number = number / 10;
            count++;
        }
        return count;
    }
}
