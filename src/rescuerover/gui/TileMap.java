package rescuerover.gui;

import rescuerover.logic.Hero;
import rescuerover.logic.Tile;
import rescuerover.logic.TileSet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Contains the map file and associates it with the corresponding images
 */
public class TileMap {
    private TileSet tileSet;

    // map current position
    private int x;
    private int y;

    private String mapFile;

    private int[][] map;

    //the dimension of the tilemap
    private Dimension dimension;

    //dimension of each tile, in pixels
    private Dimension tileDimension;
    private Hero hero;

    //how many tiles to show
    private Dimension showDimension;

    public TileMap(Dimension dimension, String mapFile) {
        this.dimension = dimension;
        this.mapFile = mapFile;
        this.tileDimension = dimension;
        map = new int[(int) dimension.getWidth()][(int) dimension.getHeight()];
    }

    public void setTileDimension(Dimension tileDimension) {
        this.tileDimension = tileDimension;
    }

    public void setShowDimension(Dimension showDimension) {
        this.showDimension = showDimension;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setTileSet(TileSet tileSet) {
        this.tileSet = tileSet;
    }

    public Dimension getTileDimension() {
        return this.tileDimension;
    }

    /**
     * Reads from the defined map file, starting from the
     * mapStartLine and stores it in the map array
     * @param mapStartLine First line to read
     * @param delim The delimiter that separates each tile
     */
    public void loadMap(int mapStartLine, String delim) {
        InputStream in = getClass().getResourceAsStream(mapFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        for (int i = 0; i < mapStartLine; i++) {
            try {
                br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            for (int row = 0; row < dimension.getHeight(); row++) {
                String line = br.readLine();
                String[] tokens = line.split(delim);
                for (int col = 0; col < dimension.getWidth(); col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws a portion of the map, centered on the hero
     * If the hero is moving, draws the map accordingly, on increments
     * @param g Graphics
     */
    public void draw(Graphics g) {
        int counterx = 0;
        int countery = 0;
        BufferedImage tileAtPosition;

        long offsetIntegerX = (long) hero.getX();
        double offsetDecimalX = hero.getOffsetX() - offsetIntegerX;

        long offsetIntegerY = (long) hero.getY();
        double offsetDecimalY = hero.getOffsetY() - offsetIntegerY;

        for (int i = x ; i <= x + showDimension.getWidth(); i++) {
            for (int j = y; j <= y + showDimension.getHeight(); j++) {
                try {
                    tileAtPosition = tileSet.getImageAtPosition(map[j][i] - 1);
                } catch(ArrayIndexOutOfBoundsException e) {
                    tileAtPosition = new BufferedImage(32, 32, BufferedImage.TYPE_BYTE_GRAY);
                }

                if(offsetDecimalX > 0) {
                    drawMovingRight(g, counterx, countery, tileAtPosition, offsetDecimalX);
                } else if(offsetDecimalX < 0) {
                    drawMovingLeft(g, counterx, countery, tileAtPosition, offsetDecimalX, i, j);
                } else if(offsetDecimalY > 0) {
                    drawMovingDown(g, counterx, countery, tileAtPosition, offsetDecimalY);
                } else if(offsetDecimalY < 0) {
                    drawMovingUp(g, counterx, countery, tileAtPosition, offsetDecimalY, i, j);
                } else {
                    g.drawImage(
                            tileAtPosition,
                            counterx * (int) tileDimension.getWidth(),
                            countery * (int) tileDimension.getHeight(),
                            (int) Math.round((int) tileDimension.getWidth()),
                            (int) tileDimension.getHeight(), null);
                }
                countery++;
            }
            counterx++;
            countery = 0;
        }
    }

    private void drawMovingUp(Graphics g, int counterx, int countery, BufferedImage tileAtPosition, double offsetDecimalY, int i, int j) {
        if(countery == 0) {
            BufferedImage oldTileAtPosition = tileAtPosition;
            try {
                tileAtPosition = tileSet.getImageAtPosition(map[j-1][i] - 1);
            } catch(ArrayIndexOutOfBoundsException e) {
                tileAtPosition = new BufferedImage(32, 32, BufferedImage.TYPE_BYTE_GRAY);
            }

            g.drawImage(
                    tileAtPosition,
                    counterx * (int) tileDimension.getWidth(),
                    0,
                    (counterx + 1) * (int) tileDimension.getHeight(),
                    (int) Math.round (-1 * offsetDecimalY * (int) tileDimension.getWidth() ),
                    0,
                    (int) Math.round(32  + offsetDecimalY * 32),
                    (int) 32,
                    (int) 32,
                    null);
            tileAtPosition = oldTileAtPosition;
            g.drawImage(
                    tileAtPosition,
                    counterx * (int) tileDimension.getHeight(),
                    (int) Math.round( (countery - offsetDecimalY) * (int) tileDimension.getWidth() + offsetDecimalY ),

                    (int) Math.round((int) tileDimension.getWidth()),
                    (int) tileDimension.getHeight(),
                    null);
        } else {
            g.drawImage(
                    tileAtPosition,
                    counterx * (int) tileDimension.getHeight(),
                    (int) Math.round( (countery - offsetDecimalY) * (int) tileDimension.getWidth() + offsetDecimalY ),
                    (int) Math.round((int) tileDimension.getWidth()),
                    (int) tileDimension.getHeight(),
                    null);
        }
    }

    private void drawMovingDown(Graphics g, int counterx, int countery, BufferedImage tileAtPosition, double offsetDecimalY) {
        if(countery == 0) {
            g.drawImage(
                    tileAtPosition,
                    counterx * (int) tileDimension.getWidth(),
                    countery * (int) tileDimension.getHeight(),
                    (counterx + 1) * (int) tileDimension.getHeight(),
                    (int) Math.round(tileDimension.getWidth() - tileDimension.getWidth() * offsetDecimalY),
                    0,
                    (int) Math.round(offsetDecimalY * 32),
                    (int) tileAtPosition.getWidth(),
                    (int) 32,
                    null);
        } else {
            g.drawImage(
                    tileAtPosition,
                    counterx * (int) tileDimension.getHeight(),
                    (int) Math.round( (countery - offsetDecimalY) * (int) tileDimension.getWidth() ),
                    (int) Math.round((int) tileDimension.getWidth()),
                    (int) tileDimension.getHeight(),
                    null);
        }
    }

    private void drawMovingLeft(Graphics g, int counterx, int countery, BufferedImage tileAtPosition, double offsetDecimalX, int i, int j) {
        if(counterx == 0) {
            BufferedImage oldTileAtPosition = tileAtPosition;
            try {
                tileAtPosition = tileSet.getImageAtPosition(map[j][i-1] - 1);
            } catch(ArrayIndexOutOfBoundsException e) {
                tileAtPosition = new BufferedImage(32, 32, BufferedImage.TYPE_BYTE_GRAY);
            }

            g.drawImage(
                    tileAtPosition,
                    0,
                    countery * (int) tileDimension.getHeight(),
                    (int) Math.round (-1 * offsetDecimalX * (int) tileDimension.getWidth() ),
                    (countery + 1) * (int) tileDimension.getHeight(),
                    (int) Math.round(32  + offsetDecimalX * 32),
                    0,
                    (int) 32,
                    (int) 32,
                    null);
            tileAtPosition = oldTileAtPosition;
            g.drawImage(
                    tileAtPosition,
                    (int) Math.round( (counterx - offsetDecimalX) * (int) tileDimension.getWidth() + offsetDecimalX ),
                    countery * (int) tileDimension.getHeight(),
                    (int) Math.round((int) tileDimension.getWidth()),
                    (int) tileDimension.getHeight(),
                    null);
        } else {
            g.drawImage(
                    tileAtPosition,
                    (int) Math.round( (counterx - offsetDecimalX) * (int) tileDimension.getWidth() + offsetDecimalX ),
                    countery * (int) tileDimension.getHeight(),
                    (int) Math.round((int) tileDimension.getWidth()),
                    (int) tileDimension.getHeight(),
                    null);
        }
    }

    private void drawMovingRight(Graphics g, int counterx, int countery, BufferedImage tileAtPosition, double offsetDecimalX) {
        if(counterx == 0) {
            g.drawImage(
                    tileAtPosition,
                    counterx * (int) tileDimension.getWidth(),
                    countery * (int) tileDimension.getHeight(),
                    (int) Math.round(tileDimension.getWidth() - tileDimension.getWidth() * offsetDecimalX),
                    (countery + 1) * (int) tileDimension.getHeight(),
                    (int) Math.round(offsetDecimalX * 32),
                    0,
                    (int) tileAtPosition.getWidth(),
                    (int) 32,
                    null);
        } else {
            g.drawImage(
                    tileAtPosition,
                    (int) Math.round( (counterx - offsetDecimalX) * (int) tileDimension.getWidth() ),
                    countery * (int) tileDimension.getHeight(),
                    (int) Math.round((int) tileDimension.getWidth()),
                    (int) tileDimension.getHeight(),
                    null);
        }
    }

    public boolean isTileUnpassable(int x, int y) {
        return (tileSet.getTileAtPosition(map[y][x] - 1).getType() == Tile.UNPASSABLE);
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public boolean isKillingTile(int x, int y) {
        return (tileSet.getTileAtPosition(map[y][x] - 1).getType() == Tile.KILL);
    }
}
