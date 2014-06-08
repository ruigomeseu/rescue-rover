package rescuerover.gui;

import rescuerover.gui.states.GameScreenState;
import rescuerover.logic.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private GameScreenState screenState;
    private Map map;

    public GamePanel(Dimension dimension, GameScreenState screenState) {
        setPreferredSize(dimension);
        this.screenState = screenState;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    protected void paintComponent(Graphics g) {
        if(this.map != null){
            this.map.tileMap.draw(g);
            this.map.objectsMap.draw(g, this.map.heroPosition());
        }

    }

    public GameScreenState getScreenState() {
        return screenState;
    }

}
