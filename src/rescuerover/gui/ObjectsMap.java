package rescuerover.gui;

import rescuerover.logic.*;

import java.awt.*;
import java.util.ArrayList;

public class ObjectsMap {
    private Dimension tileDimension;
    private ArrayList<MapObject> objects;
    private int heroX;
    private int heroY;
    private int x = 0;

    public ObjectsMap(ArrayList<MapObject> objects, Dimension tileDimension) {
        this.objects = objects;
        this.tileDimension = tileDimension;
    }

    public ArrayList<MapObject> getObjects(){
        return objects;
    }

    public void draw(Graphics g, Position heroPosition) {
        heroX = 0;
        heroY = 0;
        MapObject hero = null;
        for (MapObject obj : objects) {
            if (obj.getX() <= Math.abs(heroPosition.getX() + Constants.VISIBLE_TILES / 2)
                    || obj.getX() >= Math.abs(heroPosition.getX() - Constants.VISIBLE_TILES / 2)) {

                if (obj instanceof Hero) {
                    hero = obj;
                    heroX = obj.getX();
                    heroY = obj.getY();
                    g.drawImage(
                            obj.getSprite().getFrame(),
                            (Constants.VISIBLE_TILES / 2) * (int) tileDimension.getWidth(),
                            (Constants.VISIBLE_TILES / 2) * (int) tileDimension.getHeight(),
                            (int) tileDimension.getWidth(),
                            (int) tileDimension.getHeight(),
                            null);
                }
                else if(obj instanceof StationaryRobot){
                    drawStationaryRobots(g, obj, hero);
                }

                System.out.println("Hero frame = " + obj.getSprite().getFrameNumber());
                if (obj.isMoving())
                    obj.step();
            }
        }
    }

    public void drawStationaryRobots(Graphics g, MapObject obj, MapObject hero) {
        long offsetIntegerX = (long) hero.getX();
        double offsetDecimalX = hero.getOffsetX() - offsetIntegerX;

        long offsetIntegerY = (long) hero.getY();
        double offsetDecimalY = hero.getOffsetY() - offsetIntegerY;

        int deltaHeroX = obj.getX() - heroX;
        int deltaHeroY = obj.getY() - heroY;

        System.out.println("offsetDecimalX: " + offsetDecimalX);

        if(offsetDecimalX > 0) {
            x = 0;
            //drawMovingRight(g, counterx, countery, tileAtPosition, offsetDecimalX);
            g.drawImage(
                    obj.getSprite().getFrame(),
                    (int) Math.round((((Constants.VISIBLE_TILES / 2) + deltaHeroX) - offsetDecimalX + 1/6.0) * tileDimension.getWidth()),
                    (int) Math.round(((Constants.VISIBLE_TILES / 2) + deltaHeroY) * (int) tileDimension.getHeight()),
                    (int) tileDimension.getWidth(),
                    (int) tileDimension.getHeight(),
                    null);

        } else if(offsetDecimalX < 0) {
            x = 0;
            g.drawImage(
                    obj.getSprite().getFrame(),
                    (int) Math.round((((Constants.VISIBLE_TILES / 2) + deltaHeroX) - offsetDecimalX - 1/6.0) * (int) tileDimension.getWidth()),
                    (int) Math.round(((Constants.VISIBLE_TILES / 2) + deltaHeroY) * (int) tileDimension.getHeight()),
                    (int) tileDimension.getWidth(),
                    (int) tileDimension.getHeight(),
                    null);
        } else if(offsetDecimalY > 0) {
            x = 0;
            g.drawImage(
                    obj.getSprite().getFrame(),
                    (int) Math.round((((Constants.VISIBLE_TILES / 2) + deltaHeroX)) * (int) tileDimension.getWidth()),
                    (int) Math.round((((Constants.VISIBLE_TILES / 2) + deltaHeroY) - offsetDecimalY + 1/6.0) * (int) tileDimension.getHeight()),
                    (int) tileDimension.getWidth(),
                    (int) tileDimension.getHeight(),
                    null);
        } else if(offsetDecimalY < 0) {
            x = 0;
            g.drawImage(
                    obj.getSprite().getFrame(),
                    (int) Math.round((((Constants.VISIBLE_TILES / 2) + deltaHeroX)) * (int) tileDimension.getWidth()),
                    (int) Math.round((((Constants.VISIBLE_TILES / 2) + deltaHeroY) - offsetDecimalY - 1/6.0) * (int) tileDimension.getHeight()),
                    (int) tileDimension.getWidth(),
                    (int) tileDimension.getHeight(),
                    null);
        } else {
            System.out.println("deltaHeroX: " + deltaHeroX);
            System.out.println("deltaHeroY: " + deltaHeroY);
            if(x != 0) {
                g.drawImage(
                        obj.getSprite().getFrame(),
                        Math.round(((Constants.VISIBLE_TILES / 2) + deltaHeroX) * (int) tileDimension.getWidth()),
                        Math.round(((Constants.VISIBLE_TILES / 2) + deltaHeroY) * (int) tileDimension.getWidth()),
                        (int) tileDimension.getWidth(),
                        (int) tileDimension.getHeight(),
                        null);
            } else {
                g.drawImage(
                        obj.getSprite().getFrame(),
                        (int) Math.round(((Constants.VISIBLE_TILES / 2) + (obj.getX() - hero.getOffsetX())) * (int) tileDimension.getWidth()),
                        Math.round(((Constants.VISIBLE_TILES / 2) + deltaHeroY) * (int) tileDimension.getWidth()),
                        (int) tileDimension.getWidth(),
                        (int) tileDimension.getHeight(),
                        null);
            }
            x++;
        }
    }
}
