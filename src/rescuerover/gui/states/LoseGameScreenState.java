package rescuerover.gui.states;


import rescuerover.gui.LoseGamePanel;
import rescuerover.logic.Constants;
import rescuerover.logic.Observer;
import rescuerover.logic.ScreenState;
import rescuerover.logic.Subject;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class LoseGameScreenState implements ScreenState, Subject {

    JPanel panel;
    JFrame frame;
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    ScreenState nextState;
    Clip clip;

    private static LoseGameScreenState instance = null;

    protected LoseGameScreenState(JFrame frame) {
        this.frame = frame;
        panel = new LoseGamePanel(this);
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

    public static LoseGameScreenState getInstance(JFrame frame) {
        if (instance == null) {
            instance = new LoseGameScreenState(frame);
        }
        return instance;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void onEnter() {
        if (!Constants.MUTED) {
            playSound();
        }
        panel.setVisible(true);
        panel.setFocusable(true);
        panel.requestFocus();
        panel.revalidate();
    }

    @Override
    public void onExit() {
        panel.setVisible(false);
        panel.setFocusable(false);
        if (!Constants.MUTED) {
            clip.stop();
        }
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

    public void setNextState(ScreenState state) {
        nextState = state;
    }

    public JFrame getFrame() {
        return this.frame;
    }

    private void playSound() {

        URL url;
        try {
            // Open an audio input stream.

            url = this.getClass().getClassLoader().getResource("sounds/lose.mid");

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            clip = AudioSystem.getClip();

            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            //clip.start();
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }
}
