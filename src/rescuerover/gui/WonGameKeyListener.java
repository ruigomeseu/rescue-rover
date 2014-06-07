package rescuerover.gui;

import rescuerover.gui.states.MenuScreenState;
import rescuerover.gui.states.WonGameScreenState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class WonGameKeyListener implements KeyListener {
    WonGamePanel panel;

    public WonGameKeyListener(WonGamePanel wonGamePanel) {
        this.panel = wonGamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_LEFT:
                if (panel.image == 2) {
                    panel.image = 1;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (panel.image == 1) {
                    panel.image = 2;
                }
                break;
            case KeyEvent.VK_ENTER:
                if (panel.image == 1) {
                    panel.wonGameScreenState.setNextState(MenuScreenState.getInstance(panel.wonGameScreenState.getFrame()));
                    panel.wonGameScreenState.notifyObservers();
                } else if (panel.image == 2) {
                    panel.wonGameScreenState.setNextState(MenuScreenState.getInstance(panel.wonGameScreenState.getFrame()));
                    panel.wonGameScreenState.notifyObservers();
                }
                break;
            default:
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

