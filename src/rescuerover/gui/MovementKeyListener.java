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
        System.out.println("Key Pressed");
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                hero.move(Constants.UP);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                hero.move(Constants.LEFT);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                hero.move(Constants.DOWN);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                hero.move(Constants.RIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key Pressed");
        int code = e.getKeyCode();
        if (!hero.isMoving())
            switch (code) {
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP:
                    hero.move(Constants.UP);
                    break;
                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                    hero.move(Constants.LEFT);
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    hero.move(Constants.DOWN);
                    break;
                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    hero.move(Constants.RIGHT);
                    break;
            }
    }
}
