package rescuerover.gui;

import rescuerover.gui.states.LoseGameScreenState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class LoseGamePanel extends JPanel {

    LoseGameScreenState loseGameScreenState;

    public LoseGamePanel(LoseGameScreenState loseGameScreenState) {
        this.loseGameScreenState = loseGameScreenState;
        addKeyListener(new LoseGameKeyListener(this));
    }

    protected void paintComponent(Graphics g) {
        BufferedImage menuImage = null;
        try {
            menuImage = ImageIO.read(
                    getClass().getResourceAsStream("/lost.png"));
        } catch (IOException e) {
            System.out.println("Error loading image");
        }

        g.drawImage(menuImage, 0, 0, this.getWidth(), this.getHeight(), this);

        repaint();
    }
}
