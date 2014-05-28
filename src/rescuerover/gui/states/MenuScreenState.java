package rescuerover.gui.states;

import rescuerover.logic.Observer;
import rescuerover.logic.ScreenState;
import rescuerover.logic.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuScreenState implements ScreenState, Subject {

    JPanel panel;
    JFrame frame;
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    ScreenState nextState;

    private static MenuScreenState instance = null;

    protected MenuScreenState(JFrame frame) {
        this.frame = frame;
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        frame.add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx=0;
        gbc.weighty=0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.EAST;

        JButton exitButton = new JButton("Play");

        panel.add(exitButton, gbc);
        exitButton.addActionListener(new playListener());

        frame.repaint();
    }

    public static MenuScreenState getInstance(JFrame frame) {
        if(instance == null) {
            instance = new MenuScreenState(frame);
        }
        return instance;
    }

    @Override
    public void draw(JFrame frame) {

    }

    @Override
    public void onEnter() {
        panel.setVisible(true);
        panel.setFocusable(true);
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

    private class playListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            nextState = GameScreenState.getInstance(frame);
            notifyObservers();
        }
    }


}

