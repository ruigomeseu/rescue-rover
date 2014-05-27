package rescuerover.gui;

import rescuerover.logic.Map;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Map map;

    public GamePanel(Dimension dimension) {
        setPreferredSize(dimension);
    }

    public void setMap(Map map) {
        this.map = map;
    }

    protected void paintComponent(Graphics g) {
        this.map.tileMap.draw(g);
        this.map.objectsMap.draw(g, this.map.heroPosition());
    }

}
