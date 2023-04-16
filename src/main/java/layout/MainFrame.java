package layout;

import entity.Student;
import util.IOUtil;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public static Student localUser;
    /**
     * The position of JFrame.(X-axis)
     */
    public static final int FRAME_X;
    /**
     * The position of JFrame.(Y-axis)
     */
    public static final int FRAME_Y;
    /**
     * The height of JFrame.
     */
    public static final int FRAME_HEIGHT;
    /**
     * The width of JFrame.
     */
    public static final int FRAME_WIDTH;
    /**
     * This instant is an object of CardLayout which helps control cards to change Page.
     */
    public static CardLayout cardLayout;
    /**
     * This instant contains every Page used in the game which is invoked by cardLayout.
     */
    public static JPanel cards;
    private final MainPage mainPage;
    private SubjectPage subjectPage;

    static {
        FRAME_X = 400;
        FRAME_Y = 100;
        FRAME_HEIGHT = 480;
        FRAME_WIDTH = 600;
    }

    {
        this.mainPage = new MainPage();
    }

    public MainFrame() {
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        this.initJPanel();
        this.initActionSource(cardLayout);
        this.initFrame();
    }

    private void initJPanel() {
        cards.add(this.mainPage, "MainPage");
        this.add(cards);
    }
    private void initActionSource(CardLayout cardLayout) {
        mainPage.setMainFrame(this);
        mainPage.personalImage.setMainFrame(this);
    }
    private void initFrame() {
        this.setTitle(" Student journey ");
        Image image;
        image = IOUtil.gainImage("src/main/resources/Icon/EStudy.png");
        this.setIconImage(image);
        this.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        //this.setAlwaysOnTop(true);
    }
}
