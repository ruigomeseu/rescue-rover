package rescuerover.logic;

import javax.swing.*;

public interface ScreenState {
    public void draw(JFrame frame);
    public void onEnter();
    public void onExit();
}