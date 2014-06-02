package rescuerover.gui;

import rescuerover.logic.Constants;
import rescuerover.logic.Hero;
import rescuerover.logic.MapObject;
import rescuerover.logic.Position;

import java.awt.*;
import java.util.ArrayList;

public class ObjectsMap {
    private Dimension tileDimension;
    private ArrayList<MapObject> objects;

    public ObjectsMap(ArrayList<MapObject> objects, Dimension tileDimension) {
        this.objects = objects;
        this.tileDimension = tileDimension;
    }

    public void draw(Graphics g, Position heroPosition) {
        int heroX = 0, heroY = 0;
        for (MapObject obj : objects) {
            if (obj.getX() <= Math.abs(heroPosition.getX() + Constants.VISIBLE_TILES / 2)
                    || obj.getX() >= Math.abs(heroPosition.getX() - Constants.VISIBLE_TILES / 2)) {

                if (obj instanceof Hero) {
                    heroX = obj.getX();
                    heroY = obj.getY();
                    g.drawImage(
                            obj.getSprite().getFrame(),
                            (int) Math.round((Constants.VISIBLE_TILES / 2) * (int) tileDimension.getWidth()),
                            (Constants.VISIBLE_TILES / 2) * (int) tileDimension.getHeight(),
                            (int) tileDimension.getWidth(),
                            (int) tileDimension.getHeight(),
                            null);
                }
                else{
                    int deltaHeroX = heroX - obj.getX();
                    int deltaHeroY = heroY - obj.getY();
                    System.out.println(deltaHeroX);
                    System.out.println(deltaHeroY);
                    int multX, multY;
                    if(deltaHeroX>0){
                        multX = 1;
                    }
                    else multX = -1;

                    if(deltaHeroY>0){
                        multY = 1;
                    }
                    else multY = -1;

                    System.out.println("it's here");
                    g.drawImage(
                            obj.getSprite().getFrame(),
                            Math.round(((Constants.VISIBLE_TILES / 2) + (multX*deltaHeroX)) * (int) tileDimension.getWidth()),
                            Math.round(((Constants.VISIBLE_TILES / 2) + (multY*deltaHeroY)) * (int) tileDimension.getWidth()),
                            (int) tileDimension.getWidth(),
                            (int) tileDimension.getHeight(),
                            null);
                }


                System.out.println("Hero frame = " + obj.getSprite().getFrameNumber());
                if (obj.isMoving())
                    obj.step();
            }
        }
    }
}
