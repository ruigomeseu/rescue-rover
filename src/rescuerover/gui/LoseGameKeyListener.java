package rescuerover.gui;

import rescuerover.gui.states.MenuScreenState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class LoseGameKeyListener implements KeyListener {
    LoseGamePanel panel;

    public LoseGameKeyListener(LoseGamePanel loseGamePanel) {
        this.panel = loseGamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_ENTER:
                panel.loseGameScreenState.setNextState(MenuScreenState.getInstance(panel.loseGameScreenState.getFrame()));
                panel.loseGameScreenState.notifyObservers();
                break;
            default:
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
