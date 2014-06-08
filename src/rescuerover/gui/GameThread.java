package rescuerover.gui;

import rescuerover.gui.states.LoseGameScreenState;
import rescuerover.gui.states.WonGameScreenState;
import rescuerover.logic.Hero;

import javax.swing.*;

/**
 *  The main game thread, making the game run at 20 FPS
 */
public class GameThread extends Thread implements Runnable {

    private GamePanel gamePanel;
    private Hero hero;

    public GameThread(GamePanel gamePanel, Hero hero) {
        this.gamePanel = gamePanel;
        this.hero = hero;
    }


    @Override
    public void run() {
        while((hero.isAlive() || hero.isMoving()) && !hero.isAtEnd()) {
            gamePanel.repaint();
            try {
                Thread.sleep(50);
            } catch(Exception e) {
                System.out.println("Exception at Thread Sleep: " + e);
            }
        }

        /**
         * If the hero died, show the
         * death animation
         */
        if(!hero.isAlive()) {
            hero.getSprite().setLimits(34, 40);
            hero.getSprite().setFrameNumber(34);
            for (int i = 0; i < 6; i++) {
                hero.getSprite().incrementFrameNumber();
                gamePanel.repaint();
                try {
                    Thread.sleep(250);
                } catch (Exception e) {
                    System.out.println("Exception at Thread Sleep: " + e);
                }
            }
            gamePanel.getScreenState().setNextState(LoseGameScreenState.getInstance(gamePanel.getScreenState().getFrame()));
            gamePanel.getScreenState().notifyObservers();
        }
        else if(hero.isAtEnd()){
            gamePanel.getScreenState().setNextState(WonGameScreenState.getInstance(gamePanel.getScreenState().getFrame()));
            gamePanel.getScreenState().notifyObservers();
        }

    }
}
