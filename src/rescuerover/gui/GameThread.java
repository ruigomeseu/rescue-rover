package rescuerover.gui;

import rescuerover.logic.Hero;

import javax.swing.*;

public class GameThread extends Thread implements Runnable {

    private JPanel gamePanel;
    private Hero hero;

    public GameThread(JPanel gamePanel, Hero hero) {
        this.gamePanel = gamePanel;
        this.hero = hero;
    }


    @Override
    public void run() {
        while(hero.isAlive()) {
            gamePanel.repaint();
            try {
                Thread.sleep(50);
            } catch(Exception e) {
                System.out.println("Exception at Thread Sleep: " + e);
            }
        }

    }
}
