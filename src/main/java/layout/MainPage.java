package layout;

import entity.Subject;
import util.DataUtil;
import util.IOUtil;
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

/**
 * ###### Table column name ######
 * ID --> subjectNo
 * Subject --> subjectName
 * Grade --> grade
 * Character --> character
 * Credit --> credit
 * Rank --> rank
 * Start Time --> startTime
 */


public class MainPage extends JPanel {
    public final ImagePanel personalImage;
    private final String[] columnNames = {"ID", "Subject", "Grade", "Character", "Credit", "Start Time"};
    private final boolean[] sortOfColumns;
    private final JLabel GPA;
    private final JLabel rank;
    private final JLabel welcome;
    private final JLabel name;
    public JScrollPane jScrollPane;
    public JButton refresh;
    public JButton top;
    public JButton select;
    public JButton schedule;
    /**
     * This Box object contains every component.
     */
    private final Box vRBox;
    private final Box vLBox;
    public JSplitPane splitPane;
    private MainFrame mainFrame;
    private JTable subjectTable;
    private HashMap<String, Object> subjectMap;

    /*
    Init attributes in this class
     */ {
        Image image = IOUtil.gainImage("src/main/resources/Icon/defaultUserIcon.png");
        this.personalImage = new ImagePanel(image);
        this.refresh = new JButton("refresh");
        this.refresh.addActionListener(e -> refresh());
        this.GPA = new JLabel();
        this.rank = new JLabel();
        this.top = new JButton();
        this.name = new JLabel();
        this.select = new JButton("Course selection");
        this.schedule = new JButton("Curriculum schedule");
        this.welcome = new JLabel("Welcome to our system!");
        top.setLayout(new FlowLayout());
        this.sortOfColumns = new boolean[this.columnNames.length];
        for (int i = 0; i < columnNames.length; i++) {
            sortOfColumns[i] = true;
        }
        this.jScrollPane = new JScrollPane();
        this.vRBox = Box.createVerticalBox();
        this.vLBox = Box.createVerticalBox();
    }

    public MainPage() {
        System.out.println("[log] Create MainFrame .......");
        this.setLayout(null);

        //Set JLabel
        JLabel title = new JLabel(" Subject list");
        Image image = IOUtil.gainImage("src/main/resources/Icon/rankListIcon.png");
        if (image != null) {
            title.setIcon(new ImageIcon(image));
        }
        title.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        title.setBounds(345, 80, 200, 50);
        this.add(title);

        //Init JTable and JScrollPane
        refresh();
        this.jScrollPane.setBounds(190, 150, 480, 200);
        this.add(this.jScrollPane);

        //set GPA and rank
        top.setBounds(325,40,200,30);
        top.setBackground(new Color(242, 242, 242));
        top.setBorder(BorderFactory.createLoweredBevelBorder());
        this.GPA.setFont(new Font(Font.SERIF, Font.PLAIN , 14));
        top.add(GPA);
        this.rank.setFont(new Font(Font.SERIF, Font.PLAIN , 14));
        top.add(rank);
        this.add(top);

        //set person picture
        personalImage.setImageIcon(new ImageIcon());
        personalImage.setBounds(60,70,60,60);
        this.add(personalImage);
        name.setBounds(45,110,150,50);
        name.setFont(new Font(Font.MONOSPACED, Font.BOLD , 12));
        welcome.setBounds(18,130,250,50);
        welcome.setFont(new Font(Font.MONOSPACED, Font.PLAIN , 12));
        this.add(name);
        this.add(welcome);

        //Set refresh Button
        Image image1 = IOUtil.gainImage("src/main/resources/Icon/refresh.png");
        if (image1 != null) {
            this.refresh.setIcon(new ImageIcon(image1));
        }
        this.refresh.setBounds(300,380,100,30);
        this.add(refresh);
        this.refresh.addActionListener(e -> {
            refresh();
            JOptionPane.showConfirmDialog(mainFrame, "Refresh successfully !", "Confirmation", JOptionPane.DEFAULT_OPTION);
        });

        //Select and Schedule button
        this.select.setBounds(20,250,150,30);
        this.schedule.setBounds(20,300,150,30);
        this.select.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        this.schedule.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        this.add(select);
        this.add(schedule);

        System.out.println("[log] Create MainFrame : successful");
    }

    public void refresh() {
        IOUtil.readJson();
        subjectMap = new HashMap<>();
        refresh("ID", true);
    }

    public void refresh(String column, boolean isAscending) {
        if (MainFrame.localUser.getSelectedSubjects() == null) {
            MainFrame.localUser.setSelectedSubjects(new ArrayList<>());
        }
        this.GPA.setText("GPA : " + DataUtil.calculateGPA(MainFrame.localUser) + "      ");
        this.rank.setText("Rank : " + DataUtil.calculateGPARank(DataUtil.calculateGPA(MainFrame.localUser)));
        this.name.setText("Hello, " + MainFrame.localUser.getName() + "!");

        ArrayList<Subject> selectedSubjects = MainFrame.localUser.getSelectedSubjects();
        Object[][] data;
        DefaultTableModel model;
        //Remove old rankList component.

        //Create new Subject List
        data = LayoutUtil.getSubjectInfo(selectedSubjects, columnNames, column, isAscending);

        //Create new subject table
        model = new unEditionTable(data, columnNames);
        this.subjectTable = new JTable(model);

        this.subjectTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (selectedSubjects.size() == 0) {
                    return;
                }
                int row = subjectTable.getSelectedRow();
                String id = (String) subjectTable.getValueAt(row, 0);
                String name = (String) subjectTable.getValueAt(row, 1);
                String age = (String) subjectTable.getValueAt(row, 2);
                String gender = (String) subjectTable.getValueAt(row, 3);
                System.out.println("[log] Selected row: " + id + ", " + name + ", " + age + ", " + gender);
                int column = subjectTable.getSelectedColumn();
                if (columnNames[column].equals("Subject")) {
                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(mainFrame, "Do you want to see subject information ?", "Confirmation", JOptionPane.YES_NO_OPTION)) {
                        if (subjectMap.get(name) == null) {
                            subjectMap.put(name, new Object());
                            Subject subject = selectedSubjects.get(row);
                            SubjectPage subjectPage = new SubjectPage(subject);
                            MainFrame.cards.add(subjectPage, name);
                            MainFrame.cardLayout.show(MainFrame.cards, name);
                        } else {
                            MainFrame.cardLayout.show(MainFrame.cards, name);
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
                refresh(selectColumn, sortOfColumns[index]);
                System.out.println("[log] select column \"" + columnNames[index] + "\"");
            }
        });

        //Put JTable into jScrollPane
        this.jScrollPane.setViewportView(this.subjectTable);
        this.jScrollPane.setAlignmentX(CENTER_ALIGNMENT);
        int tableHeight = subjectTable.getRowHeight() * subjectTable.getRowCount();
        if (tableHeight > 200) {
            tableHeight = 200;
        }
        this.jScrollPane.setPreferredSize(new Dimension(450, 200));
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
}
class unEditionTable extends DefaultTableModel {
    public unEditionTable(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

class ImagePanel extends JPanel implements ActionListener {
    private final JButton imageButton;
    private MainFrame mainFrame;
    private ImageIcon imageIcon;

    public ImagePanel(Image image) {
        if (image == null) {
            image = IOUtil.gainImage("src/main/resources/Icon/defaultUserIcon.png");
            System.out.println("[log] can't get user's picture! Use default picture.");
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
            mainFrame.setVisible(false);
            new PersonalFrame(mainFrame);
        }
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
}
