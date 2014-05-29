package rescuerover.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite {

    private String spriteFileName;
    private BufferedImage sprite;
    private BufferedImage[] sprites;

    private int totalFrames;

    //frame being displayed
    private int frameNumber;

    //variables to limit the subset of frames
    //currently being displayed
    private int lowerLimit;
    private int higherLimit;

    private int numRows;
    private int numCols;

    private int tileSize;

    private boolean moving;

    public Sprite(int numCols, int numRows, int tileSize, String spriteFileName) {
        this.frameNumber = 0;
        this.numCols = numCols;
        this.numRows = numRows;
        this.totalFrames = numRows * numCols;
        this.spriteFileName = spriteFileName;
        this.sprites = new BufferedImage[totalFrames];
        try {
            sprite = ImageIO.read(
                    getClass().getResourceAsStream(spriteFileName));
        } catch (IOException e) {
            System.out.println("Error loading sprite");
        }
        this.tileSize = tileSize;
        this.loadSprites();
    }

    public void loadSprites() {
        int counter = 0;
        BufferedImage subImage;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                subImage = sprite.getSubimage(j * tileSize, i * tileSize, tileSize, tileSize);
                sprites[counter] = subImage;
                counter++;
            }
        }
    }

    public BufferedImage getImageSprite() {
        return sprite;
    }

    public BufferedImage getFrame() {
        return sprites[frameNumber];
    }

    public void setLimits(int lowerLimit, int higherLimit) {
        this.lowerLimit = lowerLimit;
        this.higherLimit = higherLimit;
    }

    /**
     *
     * @return true if movement is finished, false otherwise
     */
    public boolean incrementFrameNumber() {
        frameNumber++;
        System.out.println("Frame Number: " + frameNumber);
        if (frameNumber > higherLimit) {
            frameNumber = lowerLimit;
            return true;
        }
        return false;
    }

    public void setFrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public int getTileSize() {
        return tileSize;
    }
}
