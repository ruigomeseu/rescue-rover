package rescuerover.gui;

import rescuerover.logic.Hero;
import rescuerover.logic.TileSet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

    public void loadMap(int mapStartLine, String delim) {
        InputStream in = getClass().getResourceAsStream(mapFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        // go to line
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

    public void draw(Graphics g) {
        int counterx = 0;
        int countery = 0;
        System.out.println("Tile map drawing");
        BufferedImage tileAtPosition;
        for (int i = x; i <= x + showDimension.getWidth(); i++) {
            for (int j = y; j < y + showDimension.getHeight(); j++) {
                try {
                    tileAtPosition = tileSet.getImageAtPosition(map[j][i] - 1);
                } catch(ArrayIndexOutOfBoundsException e) {
                    tileAtPosition = new BufferedImage(32, 32, BufferedImage.TYPE_BYTE_GRAY);
                }

                long offsetIntegerX = (long) hero.getX();
                double offsetDecimalX = hero.getOffsetX() - offsetIntegerX;

                if(offsetDecimalX > 0) {
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
                                (int) tileDimension.getHeight(), null);
                    }
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

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
