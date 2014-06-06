package rescuerover.gui;

import rescuerover.gui.states.MenuScreenState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class MenuPanel extends JPanel {

    Integer image;
    MenuScreenState menuScreenState;

    public MenuPanel(MenuScreenState menuScreenState) {
        this.image = 1;
        this.menuScreenState = menuScreenState;
        addKeyListener(new KeyboardMenuListener(this));
    }

    protected void paintComponent(Graphics g) {
        BufferedImage menuImage = null;

        switch (image) {
            case 1:
                try {
                    menuImage = ImageIO.read(
                            getClass().getResourceAsStream("/menu/play.png"));
                } catch (IOException e) {
                    System.out.println("Error loading image");
                }
                break;
            case 2:
                try {
                    menuImage = ImageIO.read(
                            getClass().getResourceAsStream("/menu/multiplayer.png"));
                } catch (IOException e) {
                    System.out.println("Error loading image");
                }
                break;
            case 3:
                try {
                    menuImage = ImageIO.read(
                            getClass().getResourceAsStream("/menu/options.png"));
                } catch (IOException e) {
                    System.out.println("Error loading image");
                }
                break;
            case 4:
                try {
                    menuImage = ImageIO.read(
                            getClass().getResourceAsStream("/menu/exit.png"));
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
