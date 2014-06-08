package rescuerover.gui.states;

import rescuerover.gui.GamePanel;
import rescuerover.gui.MenuPanel;
import rescuerover.logic.Observer;
import rescuerover.logic.ScreenState;
import rescuerover.logic.Subject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The first state to be active, shows all the available
 * options
 */
public class MenuScreenState implements ScreenState, Subject {

    JPanel panel;
    JFrame frame;
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    ScreenState nextState;

    private static MenuScreenState instance = null;

    protected MenuScreenState(JFrame frame) {
        this.frame = frame;
        panel = new MenuPanel(this);
        panel.setLayout(new GridBagLayout());

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

    public static MenuScreenState getInstance(JFrame frame) {
        if (instance == null) {
            instance = new MenuScreenState(frame);
        }
        return instance;
    }

    @Override
    public void draw(Graphics g) {

    }


    @Override
    public void onEnter() {
        panel.setVisible(true);
        panel.setFocusable(true);
        panel.requestFocus();
        panel.revalidate();
    }

    @Override
    public void onExit() {
        panel.setVisible(false);
        panel.setFocusable(false);
        panel.requestFocus(false);
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

