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
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    setup();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

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
        frame.getContentPane().setLayout(new GridBagLayout());
        frame.setResizable(false);


        MenuScreenState menu = MenuScreenState.getInstance(frame);
        OptionsScreenState options = OptionsScreenState.getInstance(frame);
        GameScreenState game = GameScreenState.getInstance(frame);
        WonGameScreenState won = WonGameScreenState.getInstance(frame);
        LoseGameScreenState lost = LoseGameScreenState.getInstance(frame);
        options.register(this);
        lost.register(this);
        game.register(this);
        won.register(this);
        menu.register(this);
        currentState = menu;

        frame.pack();
        frame.setVisible(true);
    }

    public void draw() {

    }

    @Override
    public void update(Object newState) {
        currentState.onExit();
        currentState = (ScreenState) newState;
        currentState.onEnter();
    }

}
