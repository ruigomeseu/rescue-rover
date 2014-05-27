package rescuerover.gui;

import rescuerover.logic.Constants;
import rescuerover.logic.MapObject;
import rescuerover.logic.Position;

import java.awt.*;
import java.util.ArrayList;

public class ObjectsMap {
    private ArrayList<MapObject> objects;

    public ObjectsMap(ArrayList<MapObject> objects) {
        this.objects = objects;
    }

    public void draw(Graphics g, Position heroPosition) {
        g.setColor(Color.CYAN);
        g.fillRect(Constants.WIDTH/2 - 20,Constants.HEIGHT/2 - 20,40,40);
        for(MapObject obj : objects) {
            if( obj.getX() <= Math.abs(heroPosition.getX() + Constants.VISIBLE_TILES / 2) ) {
               /* g.drawImage(
                        tileAtPosition,
                        counterx * (int) tileDimension.getWidth(),
                        countery * (int) tileDimension.getHeight(),
                        (int) tileDimension.getWidth(),
                        (int) tileDimension.getHeight(), null);*/
            }
        }
    }
}
