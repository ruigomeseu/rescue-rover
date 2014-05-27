package rescuerover.logic;

import java.awt.image.BufferedImage;

public class Tile {
    public static final int UNPASSABLE = 1;
    public static final int NORMAL = 0;

    private BufferedImage tile;
    private int tileSize;

    private int type;

    public Tile(BufferedImage image, int tileSize){
        this.setTile(image);
        this.setTileSize(tileSize);
    }

    public void setType(int type){ this.type = type; }
    public int getType(){ return this.type;}
    public BufferedImage getTile() {return tile;}
    public void setTile(BufferedImage tile) {this.tile = tile;}
    public int getTileSize() {return tileSize;}
    public void setTileSize(int tileSize) {	this.tileSize = tileSize;}

}
