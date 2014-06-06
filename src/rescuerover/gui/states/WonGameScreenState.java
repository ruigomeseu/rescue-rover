package rescuerover.gui.states;

import rescuerover.gui.MenuPanel;
import rescuerover.gui.WonGamePanel;
import rescuerover.logic.Observer;
import rescuerover.logic.ScreenState;
import rescuerover.logic.Subject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class WonGameScreenState implements ScreenState, Subject {

    JPanel panel;
    JFrame frame;
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    ScreenState nextState;

    private static WonGameScreenState instance = null;

    protected WonGameScreenState(JFrame frame) {
        this.frame = frame;
        panel = new WonGamePanel(this);
        panel.setLayout(new GridBagLayout());

        panel.setVisible(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.EAST;

        panel.setFocusable(true);
        frame.getContentPane().add(panel, gbc);

        frame.repaint();

    }

    public static WonGameScreenState getInstance(JFrame frame) {
        if (instance == null) {
            instance = new WonGameScreenState(frame);
        }
        return instance;
    }
    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void onEnter() {
        System.out.println("TA NO ENTER DO WON");
        panel.setVisible(true);
        panel.setFocusable(true);
        panel.requestFocus();
        panel.revalidate();
    }

    @Override
    public void onExit() {
        panel.setVisible(false);
        panel.setFocusable(false);
    }

    @Override
    public void register(Observer obj) {
        observers.add(obj);
    }

    @Override
    public void unregister(Observer obj) {
        observers.remove(obj);

    }

    @Override
    public void notifyObservers() {
        for (Observer ob : observers) {
            ob.update(nextState);
        }
    }

    public void setNextState(ScreenState state){
        nextState = state;
    }

    public JFrame getFrame() {
        return this.frame;
    }
}
