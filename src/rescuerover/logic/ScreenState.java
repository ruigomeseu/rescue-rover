package rescuerover.logic;

import javax.swing.*;
import java.awt.*;

/**
 * An interface that defines a State
 * Used to implement the State Pattern
 */
public interface ScreenState {
    public void draw(Graphics g);
    public void onEnter();
    public void onExit();
}