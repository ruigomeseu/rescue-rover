package rescuerover.gui.states;

import com.sun.prism.GraphicsResource;
import rescuerover.gui.GamePanel;
import rescuerover.gui.GameThread;
import rescuerover.gui.MovementKeyListener;
import rescuerover.gui.TileMap;
import rescuerover.logic.*;

import javax.swing.*;
import java.awt.*;
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
    StationaryRobot robot, robot1;
    Dog dog;

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
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        gamePanel = new GamePanel(new Dimension(Constants.WIDTH, Constants.HEIGHT), this);

        panel.add(gamePanel, gbc);

        tileSet = new TileSet(32, 25, 18, "/tileset.png");
        // loads tiles -> no blocks
        tileSet.loadTile();
        tileSet.loadTilesProperties("/tileproperties", ",");

        tileMap = new TileMap(new Dimension(30,30), "/map");
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
        robot = new StationaryRobot(10,10,Constants.RIGHT);
        robot1 = new StationaryRobot(8,8,Constants.RIGHT);
        dog = new Dog(9,9, Constants.LEFT);

        map.addMapObject(hero);
        map.addMapObject(robot);
        map.addMapObject(robot1);
        map.addMapObject(dog);
        tileMap.setHero(hero);
        gamePanel.setMap(map);

        gamePanel.addKeyListener(new MovementKeyListener(hero));
    }

    public static GameScreenState getInstance(JFrame frame) {
        if(instance == null) {
            instance = new GameScreenState(frame);
        }
        return instance;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void onEnter() {
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

    public void setNextState(ScreenState state){
        nextState = state;
    }
}
