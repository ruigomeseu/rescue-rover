package rescuerover.gui;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import rescuerover.logic.*;

import java.awt.*;
import java.util.ArrayList;

public class ObjectsMap {
    private Dimension tileDimension;
    private ArrayList<MapObject> objects;
    private MapObject hero;
    private MapObject currentObject;
    private Graphics graph;
    private int iter = 0;

    public ObjectsMap(ArrayList<MapObject> objects, Dimension tileDimension) {
        this.objects = objects;
        this.tileDimension = tileDimension;
        this.hero = null;
        this.currentObject = null;
        this.graph = null;
    }

    public ArrayList<MapObject> getObjects() {
        return objects;
    }

    public void draw(Graphics g, Position heroPosition) {
        this.graph = g;
        for (MapObject obj : objects) {
            if (obj.getX() <= Math.abs(heroPosition.getX() + Constants.VISIBLE_TILES / 2)
                    || obj.getX() >= Math.abs(heroPosition.getX() - Constants.VISIBLE_TILES / 2)) {

                if (obj instanceof Hero) {
                    hero = obj;
                    g.drawImage(
                            obj.getSprite().getFrame(),
                            (Constants.VISIBLE_TILES / 2) * (int) tileDimension.getWidth(),
                            (Constants.VISIBLE_TILES / 2) * (int) tileDimension.getHeight(),
                            (int) tileDimension.getWidth(),
                            (int) tileDimension.getHeight(),
                            null);
                } else if (obj instanceof StationaryRobot) {
                    drawStationaryRobots(g, obj, hero);
                }
                else if(obj instanceof Dog){
                    if(! (((Dog) obj).getWithHero())){
                        drawStationaryRobots(g, obj, hero);
                    }
                }

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

        int deltaHeroX = obj.getX() - hero.getX();
        int deltaHeroY = obj.getY() - hero.getY();
        double lastStepX = 0;
        double lastStepY = 0;

        if (offsetDecimalX > 0) {
            lastStepX = -offsetDecimalX + 1 / 6.0;
            lastStepY = 0;
        } else if (offsetDecimalX < 0) {
            lastStepX = -offsetDecimalX - 1 / 6.0;
            lastStepY = 0;
        } else if (offsetDecimalY > 0) {
            lastStepX = 0;
            lastStepY = -offsetDecimalY + 1 / 6.0;
        } else if (offsetDecimalY < 0) {
            lastStepX = 0;
            lastStepY = -offsetDecimalY - 1 / 6.0;
        }

        if (offsetDecimalX != 0 || offsetDecimalY != 0) {
            iter = 0;
            g.drawImage(
                    obj.getSprite().getFrame(),
                    (int) Math.round((((Constants.VISIBLE_TILES / 2) + deltaHeroX) + lastStepX) * tileDimension.getWidth()),
                    (int) Math.round(((Constants.VISIBLE_TILES / 2) + deltaHeroY + lastStepY) * (int) tileDimension.getHeight()),
                    (int) tileDimension.getWidth(),
                    (int) tileDimension.getHeight(),
                    null);
        } else {
            if (iter != 0) {
                g.drawImage(
                        obj.getSprite().getFrame(),
                        (int) Math.round(((Constants.VISIBLE_TILES / 2) + deltaHeroX + lastStepX) * (int) tileDimension.getWidth()),
                        (int) Math.round(((Constants.VISIBLE_TILES / 2) + deltaHeroY) * (int) tileDimension.getWidth()),
                        (int) tileDimension.getWidth(),
                        (int) tileDimension.getHeight(),
                        null);
            } else {
                double lastX = 0, lastY = 0;

                if (hero.getDirection() == Constants.RIGHT) {
                    lastX = (obj.getX() - hero.getOffsetX() + 1 / 6.0);
                    lastY = deltaHeroY;
                } else if (hero.getDirection() == Constants.LEFT) {
                    lastX = (obj.getX() - hero.getOffsetX() - 1 / 6.0);
                    lastY = deltaHeroY;
                } else if (hero.getDirection() == Constants.DOWN) {
                    lastX = deltaHeroX;
                    lastY = (obj.getY() - hero.getOffsetY() + 1 / 6.0);
                } else if (hero.getDirection() == Constants.UP) {
                    lastX = deltaHeroX;
                    lastY = (obj.getY() - hero.getOffsetY() - 1 / 6.0);
                }
                g.drawImage(
                        obj.getSprite().getFrame(),
                        (int) Math.round(((Constants.VISIBLE_TILES / 2) + lastX) * (int) tileDimension.getWidth()),
                        (int) Math.round(((Constants.VISIBLE_TILES / 2) + lastY) * (int) tileDimension.getWidth()),
                        (int) tileDimension.getWidth(),
                        (int) tileDimension.getHeight(),
                        null);
            }
            iter++;
        }

    }
}
