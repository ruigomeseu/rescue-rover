package rescuerover.gui;

import rescuerover.logic.Constants;
import rescuerover.logic.Hero;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MovementKeyListener implements KeyListener {
    Hero hero;

    public MovementKeyListener(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        moveHero(code);
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        moveHero(code);
    }

    private void moveHero(int code) {
        if (!hero.isMoving()) {
            if(Constants.WASD_KEYS) {
                switch (code) {
                    case KeyEvent.VK_W:
                        hero.move(Constants.UP);
                        break;
                    case KeyEvent.VK_A:
                        hero.move(Constants.LEFT);
                        break;
                    case KeyEvent.VK_S:
                        hero.move(Constants.DOWN);
                        break;
                    case KeyEvent.VK_D:
                        hero.move(Constants.RIGHT);
                        break;
                    default:
                        break;
                }
            }
            else {
                switch (code) {
                    case KeyEvent.VK_UP:
                        hero.move(Constants.UP);
                        break;
                    case KeyEvent.VK_LEFT:
                        hero.move(Constants.LEFT);
                        break;
                    case KeyEvent.VK_DOWN:
                        hero.move(Constants.DOWN);
                        break;
                    case KeyEvent.VK_RIGHT:
                        hero.move(Constants.RIGHT);
                        break;
                    default:
                        break;
                }
            }

        }
    }
}
