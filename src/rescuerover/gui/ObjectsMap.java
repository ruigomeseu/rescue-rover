package rescuerover.gui;

import rescuerover.logic.*;

import java.awt.*;
import java.util.ArrayList;

public class ObjectsMap {
    private Dimension tileDimension;
    private ArrayList<MapObject> objects;
    private Hero hero;
    private boolean lastStep;
    private TileMap tileMap;

    public ObjectsMap(ArrayList<MapObject> objects, Dimension tileDimension, TileMap tileMap) {
        this.objects = objects;
        this.tileDimension = tileDimension;
        this.hero = null;
        this.lastStep = false;
        this.tileMap = tileMap;
    }

    public ArrayList<MapObject> getObjects() {
        return objects;
    }

    public void draw(Graphics g, Position heroPosition) {
        for (MapObject obj : objects) {
            if (obj.getX() <= Math.abs(heroPosition.getX() + Constants.VISIBLE_TILES / 2)
                    || obj.getX() >= Math.abs(heroPosition.getX() - Constants.VISIBLE_TILES / 2)) {

                if (obj instanceof Hero) {
                    hero = (Hero) obj;
                    long offsetIntegerX = (long) hero.getX();
                    double offsetDecimalX = hero.getOffsetX() - offsetIntegerX;

                    long offsetIntegerY = (long) hero.getY();
                    double offsetDecimalY = hero.getOffsetY() - offsetIntegerY;

                    g.drawImage(
                            obj.getSprite().getFrame(),
                            (Constants.VISIBLE_TILES / 2) * (int) tileDimension.getWidth(),
                            (Constants.VISIBLE_TILES / 2) * (int) tileDimension.getHeight(),
                            (int) tileDimension.getWidth(),
                            (int) tileDimension.getHeight(),
                            null);
                    if (Math.abs((int) (offsetDecimalX * 10)) == 8 || Math.abs((int) (offsetDecimalY * 10)) == 8) {
                        lastStep = true;
                    } else {
                        lastStep = false;
                    }
                } else if (obj instanceof StationaryRobot) {
                    drawStationary(g, obj, hero);
                    obj.step();
                    ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
                    for(Bullet bullet : ((StationaryRobot) obj).getBullets()) {
                        drawStationary(g, bullet, hero);
                        bullet.step();
                        if(tileMap.isTileUnpassable(bullet.getX(),bullet.getY())) {
                            bulletsToRemove.add(bullet);
                        }
                        else if(bullet.getX() == hero.getX() && bullet.getY() == hero.getY()) {
                            hero.accept(bullet);
                        }
                    }
                    for(Bullet bullet: bulletsToRemove) {
                        ((StationaryRobot) obj).getBullets().remove(bullet);
                    }
                    bulletsToRemove.clear();
                }
                else if(obj instanceof Dog){
                    if(! (((Dog) obj).getWithHero())){
                        drawStationary(g, obj, hero);
                    }
                }
                else if(obj instanceof Key) {
                    if(! ((Key) obj).getWithHero()) {
                        drawStationary(g, obj, hero);
                    }
                }
                else if(obj instanceof Gate) {
                    if(! ((Gate) obj).getActive()) {
                        drawStationary(g, obj, hero);
                    }
                }

                if (obj.isMoving())
                    obj.step();
            }
        }
    }



    public void drawStationary(Graphics g, MapObject obj, MapObject hero) {
        long offsetIntegerX = (long) hero.getX();
        double offsetDecimalX = hero.getOffsetX() - offsetIntegerX;

        long offsetIntegerY = (long) hero.getY();
        double offsetDecimalY = hero.getOffsetY() - offsetIntegerY;

        double deltaHeroX = obj.getX() - hero.getOffsetX();
        double deltaHeroY = obj.getY() - hero.getOffsetY();

        double lastStepX = 0;
        double lastStepY = 0;


        if (lastStep) {
            switch (hero.getDirection()) {
                case Constants.UP:
                    offsetDecimalY = -1.0;
                    break;
                case Constants.RIGHT:
                    offsetDecimalX = 1.0;
                    break;
                case Constants.DOWN:
                    offsetDecimalY = 1.0;
                    break;
                case Constants.LEFT:
                    offsetDecimalX = -1.0;
                    break;
            }
        }

        if (offsetDecimalX > 0) {
            lastStepX = -1 / 6.0;
            lastStepY = 0;
        } else if (offsetDecimalX < 0) {
            lastStepX = +1 / 6.0;
            lastStepY = 0;
        } else if (offsetDecimalY > 0) {
            lastStepX = 0;
            lastStepY = -1 / 6.0;
        } else if (offsetDecimalY < 0) {
            lastStepX = 0;
            lastStepY = +1 / 6.0;
        }


        g.drawImage(
                obj.getSprite().getFrame(),
                (int) Math.round(((Constants.VISIBLE_TILES / 2) + deltaHeroX - lastStepX) * tileDimension.getWidth()),
                (int) Math.round(((Constants.VISIBLE_TILES / 2) + deltaHeroY - lastStepY) * tileDimension.getHeight()),
                (int) tileDimension.getWidth(),
                (int) tileDimension.getHeight(),
                null);


    }
}
