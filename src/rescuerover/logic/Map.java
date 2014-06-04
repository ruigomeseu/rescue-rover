package rescuerover.logic;

import javafx.util.Pair;
import rescuerover.gui.ObjectsMap;
import rescuerover.gui.TileMap;

import java.awt.*;
import java.util.ArrayList;

public class Map {
	
	private static final int VIEW_RADIUS = 5;
	
	private ArrayList<MapObject> objects = new ArrayList<MapObject>();
    public TileMap tileMap;
    public ObjectsMap objectsMap;
	
	public Map(TileMap tileMap) {
        this.tileMap = tileMap;
        this.objectsMap = new ObjectsMap(objects, tileMap.getTileDimension());

	}
	
	public void addMapObject(MapObject object) {
		objects.add(object);
	}

    public void setTileMap(TileMap tileMap) {
        this.tileMap = tileMap;
    }
	
	public void removeMapObject(MapObject object) {
		objects.remove(object);
	}

    public Position heroPosition() {
        for(MapObject obj : objects) {
            if(obj.getClass().getName() == "Hero") {
                return new Position(obj.getX(), obj.getY(), obj.getDirection());
            }
        }
        return new Position(0,0,0);
    }

    public boolean isValidMove(int x, int y) {
        for(MapObject obj: this.objects){
            if(obj instanceof Robot){
                if(obj.getX() == x && obj.getY() == y) {
                    return false;
                }
            }
        }
        return (! tileMap.isTileUnpassable(x, y));
    }

    public boolean isKillingTile(int x, int y) {
        return tileMap.isKillingTile(x, y);
    }


}
