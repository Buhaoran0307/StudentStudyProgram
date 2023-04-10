package layout;

import entity.Subject;
import util.LayoutUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
    private JTable subjectTable;
    private final String[] columnNames = {"ID", "Subject", "Grade","Character","Credit","Start Time"};
    /**
     * This Box object contains every component.
     */
    public Box vBox;
    /**
     * This is used to contain JList object : subjectList.
     */
    public JScrollPane jScrollPane;
    public JButton test;
    private final boolean[] sortOfColumns;

    /*
    Init attributes in this class
     */
    {
        this.test = new JButton(" refresh ");
        this.vBox = Box.createVerticalBox();
        this.jScrollPane = new JScrollPane();
        this.sortOfColumns = new boolean[this.columnNames.length];
    }

    public MainPage(){
        System.out.println("Create MainPage .......");
        this.vBox.add(Box.createVerticalStrut(70));

        //Set JLabel
        JLabel jLabel = new JLabel("   Subject list");
        Image image = LayoutUtil.gainImage("src/main/resources/"+"rankListIcon.png");
        if(image != null){
            jLabel.setIcon(new ImageIcon(image));
        }
        jLabel.setFont(new Font(Font.SERIF,Font.BOLD,20));
        jLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.vBox.add(jLabel);
        this.vBox.add(Box.createVerticalStrut(30));

        //Init JTable and JScrollPane
        refresh("ID", true);
        this.jScrollPane.setAlignmentX(CENTER_ALIGNMENT);
        this.jScrollPane.setPreferredSize(new Dimension(450,200));
        this.vBox.add(this.jScrollPane);
        this.vBox.add(Box.createVerticalStrut(10));

        //Set JButton
        Image image1 = LayoutUtil.gainImage("src/main/resources/"+"mainPageIcon.png");
        if(image1 != null){
            this.test.setIcon(new ImageIcon(image1));
        }
        this.vBox.add(this.test);
        this.test.setAlignmentX(CENTER_ALIGNMENT);
        this.add(this.vBox);

        this.add(this.vBox);
        System.out.println("Create RankingPage : [successful]");
    }

    public void refresh(String column, boolean isAscending) {
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
                int row = subjectTable.getSelectedRow();
                String id = (String) subjectTable.getValueAt(row, 0);
                String name = (String) subjectTable.getValueAt(row, 1);
                String age = (String) subjectTable.getValueAt(row, 2);
                String gender = (String) subjectTable.getValueAt(row, 3);
                System.out.println("Selected row: " + id + ", " + name + ", " + age + ", " + gender);
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
