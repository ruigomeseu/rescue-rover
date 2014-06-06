package rescuerover.gui;

import rescuerover.gui.states.WonGameScreenState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class WonGamePanel extends JPanel {
    Integer image;
    WonGameScreenState wonGameScreenState;

    public WonGamePanel(WonGameScreenState wonGameScreenState) {
        this.image = 1;
        this.wonGameScreenState = wonGameScreenState;
        addKeyListener(new WonGameKeyListener(this));
    }

    protected void paintComponent(Graphics g) {
        BufferedImage menuImage = null;

        switch (image) {
            case 1:
                try {
                    menuImage = ImageIO.read(
                            getClass().getResourceAsStream("/woncontinue.png"));
                } catch (IOException e) {
                    System.out.println("Error loading image");
                }
                break;
            case 2:
                try {
                    menuImage = ImageIO.read(
                            getClass().getResourceAsStream("/won.png"));
                } catch (IOException e) {
                    System.out.println("Error loading image");
                }
                break;
            default:
                break;
        }

        g.drawImage(menuImage, 0, 0, this.getWidth(), this.getHeight(), this);

        repaint();
    }
}
