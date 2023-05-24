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

/**
 * This class is used for represent the main page.
 * @version 1.0
 */
public class MainPage extends JPanel {
    /**
     * This is used to represent the user's image
     */
    public final ImagePanel personalImage;
    /**
     * This is used for grade table's header
     */
    private final String[] columnNames = {"ID", "Subject", "Grade", "Character", "Credit", "Start Time"};
    /**
     * This is used to record the sort config of the grade column. false: from largest to smallest
     */
    private final boolean[] sortOfColumns;
    /**
     * This label component is used to represent the GPA marks
     */
    private final JLabel GPA;
    /**
     * This label component is used to represent the rank among students
     */
    private final JLabel rank;
    /**
     * This label component is used to show hello information
     */
    private final JLabel welcome;
    /**
     * This label component is used to show user's name
     */
    private final JLabel name;
    /**
     * This JScrollPane component is used to create a scroll
     */
    public JScrollPane jScrollPane;
    /**
     * This button component is used to refresh the grade
     */
    public JButton refresh;
    /**
     * This button component is used to contain GPA and Rank info
     */
    public JButton top;
    /**
     * This button component is used to print GPA certification in PDF format
     */
    public JButton outputGPA;
    /**
     * This button component is used to print trusted transcript in PDF format
     */
    public JButton outputGrade;
    /**
     * This button component is used to check the user's award
     */
    public JButton checkAward;
    /**
     * This variable is to track the main JFrame instance
     */
    private MainFrame mainFrame;
    /**
     * This JTable component is used to show the grade and subject info of every subject
     */
    private JTable subjectTable;
    /**
     * This variable is used to save subject information
     */
    private HashMap<String, Object> subjectMap;

    {
        Image image = IOUtil.gainImage("src/main/resources/Icon/defaultUserIcon.png");
        this.personalImage = new ImagePanel(image);
        this.refresh = new JButton("refresh");
        this.refresh.addActionListener(e -> refresh());
        this.GPA = new JLabel();
        this.rank = new JLabel();
        this.top = new JButton();
        this.name = new JLabel();
        this.outputGPA = new JButton("GPA certification");
        this.outputGrade = new JButton("Trusted Transcript");
        this.checkAward=new JButton("Check Award");
        this.welcome = new JLabel("Welcome to our system!");
        top.setLayout(new FlowLayout());
        this.sortOfColumns = new boolean[this.columnNames.length];
        for (int i = 0; i < columnNames.length; i++) {
            sortOfColumns[i] = true;
        }
        this.jScrollPane = new JScrollPane();
    }

    /**
     * Create the main Page and init the component position.
     */
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
        this.GPA.setFont(new Font("Comic Sans Ms",Font.PLAIN, 14));
        top.add(GPA);
        this.rank.setFont(new Font("Comic Sans Ms",Font.PLAIN, 14));
        top.add(rank);
        this.add(top);

        //set person picture
        personalImage.setImageIcon(new ImageIcon());
        personalImage.setBounds(60,80,60,60);
        this.add(personalImage);
        name.setBounds(45,135,150,50);
        name.setFont(new Font("Comic Sans Ms",Font.PLAIN, 16));
        welcome.setBounds(18,165,250,50);
        welcome.setFont(new Font("Comic Sans Ms",Font.PLAIN, 14));
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


        this.checkAward.setBounds(20,200,150,30);
        this.checkAward.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        this.checkAward.addActionListener(e -> {

                MainFrame.localUser.getStudentNo();
            MainFrame.cardLayout.show(MainFrame.cards,"PersonalPage");
            AwardPage awardPage = new AwardPage(MainFrame.localUser.getStudentNo());
            MainFrame.cards.add(awardPage, "awardPage");
            MainFrame.cardLayout.show(MainFrame.cards, "awardPage");

        });
        this.add(checkAward);
        //Select and Schedule button
        this.outputGPA.setBounds(20,250,150,30);
        this.outputGrade.setBounds(20,300,150,30);
        this.outputGPA.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        this.outputGPA.addActionListener(e -> {
            try {
                IOUtil.writeGPAPDF(MainFrame.localUser.getStudentNo());
                JOptionPane.showMessageDialog(mainFrame, "Print your GPA certification successfully !");
            } catch (Exception ignored) {
                JOptionPane.showMessageDialog(mainFrame, "Printer is not available now, please try later.", "Warming", JOptionPane.WARNING_MESSAGE);
            }
        });
        this.outputGrade.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        this.outputGrade.addActionListener(e -> {
            try {
                IOUtil.writeGradePDF(MainFrame.localUser.getStudentNo());
                JOptionPane.showMessageDialog(mainFrame, "Print your Trusted Transcript successfully !");
            } catch (Exception ignored) {
                JOptionPane.showMessageDialog(mainFrame, "Printer is not available now, please try later.", "Warming", JOptionPane.WARNING_MESSAGE);
            }
        });
        this.add(outputGPA);
        this.add(outputGrade);

        System.out.println("[log] Create MainFrame : successful");
    }

    /**
     * Refresh the grade table layout and total grades from files
     */
    public void refresh() {
        IOUtil.readJson();
        subjectMap = new HashMap<>();
        refresh("ID", true);
    }

    /**
     * Refresh the grade table layout and total grades from files
     * @param column sort the row by this column value
     * @param isAscending the sort order (false: from largest to smallest)
     */
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

    /**
     * Save the main JFrame instance
     * @param mainFrame the reference of the main JFrame instance
     */
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
}

/**
 * Refactor the DefaultTableModel class and create a new uneditable JTable
 * @version 1.0
 */
class unEditionTable extends DefaultTableModel {
    /**
     * Create a instance of this class
     * @param data the 2D data wanted to be shown.
     * @param columnNames the headers of this table
     */
    public unEditionTable(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    /**
     *
     * @param row             the row whose value is to be queried
     * @param column          the column whose value is to be queried
     * @return return false to refuse any edition
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

/**
 * This class is used to show user's photo and made to be clickable.
 */
class ImagePanel extends JPanel implements ActionListener {
    /**
     * Used to show photo
     */
    private final JButton imageButton;
    /**
     * Used to contain image
     */
    private ImageIcon imageIcon;

    /**
     * Use JPanel to contain a JButton which shows user's photo
     * @param image
     */
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

    /**
     * Set a click performance
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // 处理按钮的点击事件
        if (e.getSource() == imageButton) {
            MainFrame.cardLayout.show(MainFrame.cards,"PersonalPage");
        }

    }

    /**
     * Change user's photo
     * @param imageIcon user's photo
     */
    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }
}
