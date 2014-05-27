package rescuerover.gui.states;

import rescuerover.logic.ScreenState;
import rescuerover.logic.Observer;

import javax.swing.*;
import java.awt.*;

/**
 * This class implements the State Design Pattern.
 * It manages all the possible Screen States and draws them.
 */
public class ScreenStateManager implements Observer {

    ScreenState currentState;
    JFrame frame;

    public ScreenStateManager() {
        setup();

        MenuScreenState menu = MenuScreenState.getInstance(frame);
        GameScreenState game = GameScreenState.getInstance(frame);
        game.register(this);
        menu.register(this);
        currentState = menu;
    }

    /**
     * Sets the default JFrame and the default contentPane using
     * a GridBagLayout
     */
    public void setup() {
        frame = new JFrame("Rescue Rover");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(null);
        frame.setContentPane(contentPane);
        frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[] {780, 0};
        gbl_contentPane.rowHeights = new int[]{580, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0};
        gbl_contentPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        frame.getContentPane().setLayout(gbl_contentPane);
        frame.pack();
        frame.setVisible(true);
    }

    public void draw() {
        currentState.draw(frame);
    }

    @Override
    public void update(Object newState) {
        currentState.onExit();
        currentState = (ScreenState) newState;
        currentState.onEnter();
    }

}
