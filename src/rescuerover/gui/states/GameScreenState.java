package rescuerover.gui.states;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import rescuerover.gui.GamePanel;
import rescuerover.gui.GameThread;
import rescuerover.gui.MovementKeyListener;
import rescuerover.gui.TileMap;
import rescuerover.logic.*;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GameScreenState implements ScreenState, Subject {

    private static GameScreenState instance = null;
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    ScreenState nextState;
    JPanel panel;
    GamePanel gamePanel;
    JFrame frame;
    GameThread gameThread;
    Hero hero;
    TileSet tileSet;
    TileMap tileMap;
    Map map;
    StationaryRobot robot, robot1, robot2, robot3, robot4;
    Dog dog;
    Key realKey, fakeKey, fakeKey1;
    Gate gate;
    Clip clip;

    protected GameScreenState(JFrame frame) {
        this.frame = frame;
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setVisible(false);
        frame.add(panel);

        setup();
    }

    public void setup() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;

        gamePanel = new GamePanel(new Dimension(Constants.WIDTH, Constants.HEIGHT), this);

        frame.add(gamePanel, gbc);

        tileSet = new TileSet(32, 25, 18, "/tileset.png");
        // loads tiles -> no blocks
        tileSet.loadTile();
        tileSet.loadTilesProperties("/tileproperties", ",");

        tileMap = new TileMap(new Dimension(30, 30), "/map");
        // Adds a tile set to load map
        tileMap.setTileSet(tileSet);
        // Set different position to start showing map
        tileMap.setPosition(-3, -1);

        tileMap.setTileDimension(new Dimension(Constants.WIDTH / Constants.VISIBLE_TILES, Constants.HEIGHT / Constants.VISIBLE_TILES));
        tileMap.setShowDimension(new Dimension(Constants.VISIBLE_TILES, Constants.VISIBLE_TILES));

        // loads the map from map file
        tileMap.loadMap(0, ",");

        map = new Map(tileMap);

        hero = new Hero(2, 4, Constants.UP, map);
        robot = new StationaryRobot(10, 10, Constants.RIGHT);
        robot1 = new StationaryRobot(8, 8, Constants.RIGHT);
        robot2 = new StationaryRobot(7, 7, Constants.UP);
        robot3 = new StationaryRobot(12, 11, Constants.LEFT);
        robot4 = new StationaryRobot(5, 7, Constants.DOWN);
        dog = new Dog(28, 23, Constants.LEFT);
        realKey = new Key(27, 27, Constants.RIGHT, "real");
        fakeKey = new Key(20, 20, Constants.RIGHT, "fake");
        fakeKey1 = new Key(3, 22, Constants.RIGHT, "fake");
        gate = new Gate(24, 23, Constants.RIGHT);

        map.addMapObject(hero);
        map.addMapObject(robot);
        map.addMapObject(robot1);
        map.addMapObject(robot2);
        map.addMapObject(robot3);
        map.addMapObject(robot4);
        map.addMapObject(dog);
        map.addMapObject(realKey);
        map.addMapObject(fakeKey);
        map.addMapObject(fakeKey1);
        map.addMapObject(gate);
        tileMap.setHero(hero);
        gamePanel.setMap(map);

        gamePanel.addKeyListener(new MovementKeyListener(hero));
    }

    public static GameScreenState getInstance(JFrame frame) {
        if (instance == null) {
            instance = new GameScreenState(frame);
        }
        return instance;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void onEnter() {
        if (!Constants.MUTED) {
            playSound();
        }
        tileMap.setTileDimension(new Dimension(Constants.HEIGHT / Constants.VISIBLE_TILES, Constants.HEIGHT / Constants.VISIBLE_TILES));
        map.setTileDimension(tileMap.getTileDimension());
        frame.setPreferredSize(new Dimension(Constants.HEIGHT, Constants.HEIGHT));
        gamePanel.setPreferredSize(new Dimension(Constants.HEIGHT, Constants.HEIGHT));
        gameThread = new GameThread(gamePanel, hero);
        gameThread.start();
        panel.setVisible(true);
        panel.setFocusable(false);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        panel.revalidate();
    }

    @Override
    public void onExit() {
        panel.setVisible(false);
        panel.setFocusable(false);
        gamePanel.requestFocus(false);
        gamePanel.setVisible(false);
        resetGameState();
        if (!Constants.MUTED) {
            clip.stop();
        }

    }

    private void resetGameState() {
        setup();
    }

    @Override
    public void register(Observer obj) {
        observers.add(obj);
    }

    @Override
    public void unregister(Observer obj) {
        observers.remove(obj);
    }

    @Override
    public void notifyObservers() {
        for (Observer ob : observers) {
            System.out.println("Notifying Observers..");
            System.out.println("Next state: " + nextState.getClass().getName());
            ob.update(nextState);
        }
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public void setNextState(ScreenState state) {
        nextState = state;
    }

    private void playSound() {
        try {
            // Open an audio input stream.
            URL url = this.getClass().getClassLoader().getResource("sounds/back.mid");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            clip = AudioSystem.getClip();

            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);

            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-5);
            //clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
