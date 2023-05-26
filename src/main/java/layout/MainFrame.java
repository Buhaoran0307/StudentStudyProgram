package layout;

import entity.Student;
import util.IOUtil;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used for represent GUI. It will create the Main window of this program.
 * @version 1.0
 * @author Haoran Bu
 */
public class MainFrame extends JFrame {
    /**
     * Record the user who is operating now
     */
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
     * This instant contains every Page used in the program which is invoked by cardLayout.
     */
    public static JPanel cards;
    /**
     * Create the main JPanel
     */
    private final MainPage mainPage;
    /**
     * Create the personal page JPanel
     */
    private final PersonalPage personalPage;

    static {
        FRAME_X = 400;
        FRAME_Y = 100;
        FRAME_HEIGHT = 490;
        FRAME_WIDTH = 700;
    }

    {
        this.mainPage = new MainPage();
        this.personalPage = new PersonalPage(this);
    }

    /**
     * Create the main JFrame window
     */
    public MainFrame() {
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        this.initJPanel();
        this.initActionSource();
        this.initFrame();
    }

    /**
     * Init the main page and personal page in the program
     */
    private void initJPanel() {
        cards.add(this.mainPage, "MainPage");
        cards.add(this.personalPage,"PersonalPage");
        cardLayout.show(cards,"MainPage");
        this.add(cards);
    }

    /**
     * Transfer this JFrame window to the page instance
     */
    private void initActionSource() {
        mainPage.setMainFrame(this);
    }

    /**
     * Init the main JFrame configs
     */
    private void initFrame() {
        this.setTitle(" Student journey ");
        Image image;
        image = IOUtil.gainImage("src/main/resources/Icon/EStudy.png");
        this.setIconImage(image);
        this.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }
}
