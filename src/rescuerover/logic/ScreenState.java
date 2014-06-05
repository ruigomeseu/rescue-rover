package rescuerover.logic;

import javax.swing.*;
import java.awt.*;

public interface ScreenState {
    public void draw(Graphics g);
    public void onEnter();
    public void onExit();
}