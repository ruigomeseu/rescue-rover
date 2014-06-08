package rescuerover.gui;

import rescuerover.gui.states.GameScreenState;
import rescuerover.gui.states.OptionsScreenState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardMenuListener implements KeyListener {
    MenuPanel panel;

    public KeyboardMenuListener(MenuPanel panel) {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_UP:
                if (panel.image == 3) {
                    panel.image = 1;
                } else if (panel.image == 4) {
                    panel.image = 2;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (panel.image == 2) {
                    panel.image = 1;
                } else if (panel.image == 4) {
                    panel.image = 3;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (panel.image == 1) {
                    panel.image = 3;
                } else if (panel.image == 2) {
                    panel.image = 4;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (panel.image == 1) {
                    panel.image = 2;
                } else if (panel.image == 3) {
                    panel.image = 4;
                }
                break;
            case KeyEvent.VK_ENTER:
                if (panel.image == 1) {
                    panel.menuScreenState.setNextState(GameScreenState.getInstance(panel.menuScreenState.getFrame()));
                    panel.menuScreenState.notifyObservers();
                } else if (panel.image == 4) {
                    System.exit(0);
                }
                else if(panel.image == 3) {
                    panel.menuScreenState.setNextState(OptionsScreenState.getInstance(panel.menuScreenState.getFrame()));
                    panel.menuScreenState.notifyObservers();
                }
                else if(panel.image == 2) {

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