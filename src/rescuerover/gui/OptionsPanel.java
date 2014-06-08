package rescuerover.gui;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import rescuerover.gui.states.MenuScreenState;
import rescuerover.gui.states.OptionsScreenState;
import rescuerover.logic.Constants;
import sun.audio.ContinuousAudioDataStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class OptionsPanel extends JPanel {

    OptionsScreenState optionsScreenState;
    JCheckBox muteCheckBox;
    JButton goBackButton;
    JComboBox resolutions;
    JComboBox movements;

    public OptionsPanel(OptionsScreenState optionsScreenState) {
        this.optionsScreenState = optionsScreenState;

        muteCheckBox = new JCheckBox("Mute");
        muteCheckBox.setSelected(false);
        muteCheckBox.setBackground(new Color(0x00ffffff, true));
        muteCheckBox.addItemListener(new MuteListener());

        goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(new goBackListener(this));

        String[] resolutionTypes = {"800x600", "1024x768", "1280x1024"};
        resolutions = new JComboBox(resolutionTypes);
        resolutions.setSelectedIndex(0);
        resolutions.addItemListener(new resolutionChangeListener(this));

        String[] movementTypes = {"Arrow Keys", "WASD Keys"};
        movements = new JComboBox(movementTypes);
        movements.setSelectedIndex(1);
        movements.addItemListener(new keyMovementListener());

        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());

        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());

        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(muteCheckBox);
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());

        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(resolutions);
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());

        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(movements);
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());

        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(new TransparentPanel());
        add(goBackButton);
        add(new TransparentPanel());


    }

    protected void paintComponent(Graphics g) {

        BufferedImage optionsImage = null;

        try {
            optionsImage = ImageIO.read(
                    getClass().getResourceAsStream("/options.png"));
        } catch (IOException e) {
            System.out.println("Error loading image");
        }

        g.drawImage(optionsImage, 0, 0, this.getWidth(), this.getHeight(), null);

        repaint();
    }


    private class goBackListener implements ActionListener {
        OptionsPanel panel;

        public goBackListener(OptionsPanel optionsPanel) {
            this.panel = optionsPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            panel.optionsScreenState.setNextState(MenuScreenState.getInstance(panel.optionsScreenState.getFrame()));
            panel.optionsScreenState.notifyObservers();
        }
    }

    private class TransparentPanel extends JPanel {
        private TransparentPanel() {
            setBackground(new Color(0x00ffffff, true));
        }
    }

    private class resolutionChangeListener implements ItemListener {

        OptionsPanel optionsPanel;

        public resolutionChangeListener(OptionsPanel optionsPanel) {
            this.optionsPanel = optionsPanel;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String item = (String) e.getItem();

                if (item == "800x600") {
                    optionsPanel.optionsScreenState.getFrame().setSize(new Dimension(800, 600));
                    Constants.WIDTH = 800;
                    Constants.HEIGHT = 600;
                } else if (item == "1024x768") {
                    optionsPanel.optionsScreenState.getFrame().setSize(new Dimension(1024, 768));
                    Constants.WIDTH = 1024;
                    Constants.HEIGHT = 768;
                } else if (item == "1280x1024") {
                    optionsPanel.optionsScreenState.getFrame().setSize(new Dimension(1280, 1024));
                    Constants.WIDTH = 1280;
                    Constants.HEIGHT = 1024;
                }
            }
        }
    }

    private class MuteListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Constants.MUTED = true;
            } else {
                Constants.MUTED = false;
            }

            System.out.println(Constants.MUTED);
        }
    }

    private class keyMovementListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String item = (String) e.getItem();

                if (item == "Arrow Keys") {
                    Constants.WASD_KEYS = false;
                } else if (item == "WASD Keys") {
                    Constants.WASD_KEYS = true;
                }
            }
        }
    }
}

