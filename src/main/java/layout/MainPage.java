package layout;

import entity.Subject;
import util.JsonFileReader;
import util.LayoutUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *  ###### Table column name ######
 *  ID --> subjectNo
 *  Subject --> subjectName
 *  Grade --> grade
 *  Character --> character
 *  Credit --> credit
 *  Rank --> rank
 *  Start Time --> startTime
 */


public class MainPage extends JPanel {
    private final String[] columnNames = {"ID", "Subject", "Grade","Character","Credit","Start Time"};
    private final boolean[] sortOfColumns;
    private WindowsFrame windowsFrame;
    public final ImagePanel personalImage;
    private final JLabel GPA;
    private final JLabel rank;
    private JTable subjectTable;
    private HashMap<String,Object> subjectMap;
    public JScrollPane jScrollPane;
    public JButton refresh;
    /**
     * This Box object contains every component.
     */
    public Box vBox;


    /*
    Init attributes in this class
     */
    {
        Image image = LayoutUtil.gainImage("src/main/resources/defaultUserIcon.png");
        this.personalImage = new ImagePanel(image);
        this.refresh = new JButton(" refresh ");
        this.refresh.addActionListener(e -> refresh("ID",true));
        this.GPA = new JLabel();
        this.rank = new JLabel();
        this.sortOfColumns = new boolean[this.columnNames.length];
        this.jScrollPane = new JScrollPane();
        this.vBox = Box.createVerticalBox();
    }

    public MainPage(){
        System.out.println("Create MainPage .......");
        this.vBox.add(Box.createVerticalStrut(70));

        //set person image
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        //Set JLabel
        JLabel tittle = new JLabel(" Subject list");
        Image image = LayoutUtil.gainImage("src/main/resources/"+"rankListIcon.png");
        if(image != null){
            tittle.setIcon(new ImageIcon(image));
        }
        tittle.setFont(new Font(Font.SERIF,Font.BOLD,20));
        tittle.setHorizontalAlignment(SwingConstants.CENTER);
        //set person picture
        //personalImage.setImageIcon(new ImageIcon());
        jPanel.add(personalImage, BorderLayout.WEST);
        jPanel.add(tittle,BorderLayout.CENTER);
        jPanel.setPreferredSize(new Dimension(450,jPanel.getPreferredSize().height));
        /*Border border = BorderFactory.createLineBorder(Color.RED, 2);
        jPanel.setBorder(border);*/
        this.vBox.add(jPanel);
        this.vBox.add(Box.createVerticalStrut(10));

        //set GPA and rank
        Box hBox1 = Box.createHorizontalBox();
        hBox1.add(this.GPA);
        hBox1.add(Box.createHorizontalStrut(50));
        hBox1.add(this.rank);
        this.vBox.add(hBox1);
        this.vBox.add(Box.createVerticalStrut(5));

        //Init JTable and JScrollPane
        refresh();
        this.vBox.add(this.jScrollPane);
        this.vBox.add(Box.createVerticalStrut(10));

        //Set JButton
        Image image1 = LayoutUtil.gainImage("src/main/resources/"+"mainPageIcon.png");
        if(image1 != null){
            this.refresh.setIcon(new ImageIcon(image1));
        }
        this.vBox.add(this.refresh);
        this.refresh.setAlignmentX(CENTER_ALIGNMENT);
        this.refresh.addActionListener(e -> {
            refresh();
            JOptionPane.showConfirmDialog(windowsFrame, "Refresh successfully !", "Confirmation", JOptionPane.DEFAULT_OPTION);
        });
        this.add(this.vBox);

        this.add(this.vBox);
        System.out.println("Create RankingPage : [successful]");
    }

    public void refresh(){
        JsonFileReader.readJson();
        subjectMap = new HashMap<>();
        refresh("ID", true);
    }
    public void refresh(String column, boolean isAscending) {
        this.GPA.setText("GPA : " + LayoutUtil.calculateGPA(WindowsFrame.localUser));
        this.rank.setText("Rank : " + LayoutUtil.calculateGPARank(LayoutUtil.calculateGPA(WindowsFrame.localUser)));

        if (WindowsFrame.localUser.getSelectedSubjects() == null){
            WindowsFrame.localUser.setSelectedSubjects(new ArrayList<>());
        }
        ArrayList<Subject> selectedSubjects = WindowsFrame.localUser.getSelectedSubjects();
        Object[][] data;
        DefaultTableModel model;
        //Remove old rankList component.
        if (this.subjectTable != null) {
            this.vBox.remove(this.subjectTable);
        }
        //Create new Subject List
        data = LayoutUtil.getSubjectInfo(selectedSubjects,columnNames,column,isAscending);

        //Create new subject table
        model = new unEditionTable(data, columnNames);
        this.subjectTable = new JTable(model);

        this.subjectTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (selectedSubjects.size() == 0){
                    return;
                }
                int row = subjectTable.getSelectedRow();
                String id = (String) subjectTable.getValueAt(row, 0);
                String name = (String) subjectTable.getValueAt(row, 1);
                String age = (String) subjectTable.getValueAt(row, 2);
                String gender = (String) subjectTable.getValueAt(row, 3);
                System.out.println("Selected row: " + id + ", " + name + ", " + age + ", " + gender);
                int column =  subjectTable.getSelectedColumn();
                if(columnNames[column].equals("Subject")){
                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(windowsFrame, "Do you want to see subject information ?", "Confirmation", JOptionPane.YES_NO_OPTION)){
                        if(subjectMap.get(name) == null){
                            subjectMap.put(name,new Object());
                            Subject subject = selectedSubjects.get(row);
                            SubjectPage subjectPage = new SubjectPage(subject);
                            WindowsFrame.cards.add(subjectPage,name);
                            WindowsFrame.cardLayout.show(WindowsFrame.cards,name);
                        }else {
                            WindowsFrame.cardLayout.show(WindowsFrame.cards,name);
                        }
                    }
                }
            }
        });
        JTableHeader jTableHeader = this.subjectTable.getTableHeader();
        jTableHeader.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TableColumnModel colModel = jTableHeader.getColumnModel();
                int index = colModel.getColumnIndexAtX(e.getX());
                String selectColumn = (String) colModel.getColumn(index).getHeaderValue();
                sortOfColumns[index] = !sortOfColumns[index];
                //System.out.println(selectColumn);
                refresh(selectColumn,sortOfColumns[index]);
            }
        });
        //Put JTable into jScrollPane
        this.jScrollPane.setViewportView(this.subjectTable);
        this.jScrollPane.setAlignmentX(CENTER_ALIGNMENT);
        this.jScrollPane.setPreferredSize(new Dimension(450,200));
    }

    public WindowsFrame getWindowsFrame() {
        return windowsFrame;
    }

    public void setWindowsFrame(WindowsFrame windowsFrame) {
        this.windowsFrame = windowsFrame;
    }
}

class unEditionTable extends DefaultTableModel{
    public unEditionTable(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

class ImagePanel extends JPanel implements ActionListener {
    private WindowsFrame windowsFrame;
    private ImageIcon imageIcon;
    private final JButton imageButton;

    public ImagePanel(Image image) {
        if(image == null){
            image = LayoutUtil.gainImage("src/main/resources/defaultUserIcon.png");
            System.out.println("[Error] can't get user's picture! Use default picture.");
        }
        imageIcon = new ImageIcon(image);

        imageButton = new JButton(imageIcon);
        // 设置按钮的边界为0，使按钮的边界与图片的边界一致
        imageButton.setBorder(BorderFactory.createEmptyBorder());
        // 设置按钮的背景色为透明
        imageButton.setContentAreaFilled(false);
        // 添加按钮的点击事件监听器
        imageButton.addActionListener(this);

        // 将图片按钮添加到面板中
        add(imageButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 处理按钮的点击事件
        if (e.getSource() == imageButton) {
            windowsFrame.setVisible(false);
            new PersonalPage(windowsFrame);
        }
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public WindowsFrame getWindowsFrame() {
        return windowsFrame;
    }

    public void setWindowsFrame(WindowsFrame windowsFrame) {
        this.windowsFrame = windowsFrame;
    }
}

class ImageClickableComponent extends JComponent implements SwingConstants{

    private Image image;
    private boolean clicked;

    public ImageClickableComponent(Image image) {
        this.image = image;
        this.clicked = false;

        // 添加鼠标点击事件监听器
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                clicked = true;
                repaint();
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 绘制图片
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }

        // 如果被点击，则绘制点击效果
        if (clicked) {
            g.setColor(Color.RED);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
    public void setImage(Image image) {
        this.image = image;
        repaint();
    }
}

