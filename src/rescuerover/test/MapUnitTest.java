package rescuerover.test;

import static org.junit.Assert.*;

import org.junit.Test;

import rescuerover.gui.TileMap;
import rescuerover.logic.*;

import java.awt.*;

public class MapUnitTest {

    Hero hero;
    TileSet tileSet;
    TileMap tileMap;
    Map map;

    public void setUp(int heroX, int heroY) {
        tileSet = new TileSet(32, 25, 18, "/tileset.png");
        tileSet.loadTile();
        tileSet.loadTilesProperties("/tileproperties", ",");

        tileMap = new TileMap(new Dimension(30, 30), "/map");

        tileMap.setTileSet(tileSet);
        // Set different position to start showing map
        tileMap.setPosition(-3, -1);

        tileMap.setTileDimension(new Dimension(Constants.WIDTH / Constants.VISIBLE_TILES, Constants.HEIGHT / Constants.VISIBLE_TILES));
        tileMap.setShowDimension(new Dimension(Constants.VISIBLE_TILES, Constants.VISIBLE_TILES));

        // loads the map from map file
        tileMap.loadMap(0, ",");

        map = new Map(tileMap);

        hero = new Hero(heroX, heroY, Constants.UP, map);

        map.addMapObject(hero);

        tileMap.setHero(hero);
    }

	/**
	 * Adds a new hero and moves it to the left
	 */
	@Test
	public void testHeroMoveLeft() {
        setUp(2, 4);
        int heroLastX = hero.getX();
        int heroLastY = hero.getY();
        hero.move(Constants.LEFT);

        //simulate the 6 sprites moving from one block to the next one
        for(int i = 0; i<6; i++)
            hero.step();

        assertEquals(heroLastY, hero.getY());
        assertEquals(heroLastX - 1, hero.getX());
        assertEquals(hero.getDirection(), Constants.LEFT);
	}
	
	/**
	 * Adds a new hero and moves it to the right
	 */
	@Test
	public void testHeroMoveRight() {
        setUp(2, 4);
        int heroLastX = hero.getX();
        int heroLastY = hero.getY();
        hero.move(Constants.RIGHT);

        //simulate the 6 sprites moving from one block to the next one
        for(int i = 0; i<6; i++)
            hero.step();

        assertEquals(heroLastY, hero.getY());
        assertEquals(heroLastX + 1, hero.getX());
        assertEquals(hero.getDirection(), Constants.RIGHT);
	}
	
	/**
	 * Adds a new hero and moves it up
	 */
	@Test
	public void testHeroMoveUp() {
        setUp(2, 4);
        int heroLastX = hero.getX();
        int heroLastY = hero.getY();
        hero.move(Constants.UP);

        //simulate the 6 sprites moving from one block to the next one
        for(int i = 0; i<6; i++)
            hero.step();

        assertEquals(heroLastY - 1, hero.getY());
        assertEquals(heroLastX, hero.getX());
        assertEquals(hero.getDirection(), Constants.UP);
	}
	
	/**
	 * Adds a new hero and moves it down
	 */
	@Test
	public void testHeroMoveDown() {
        setUp(2, 4);
        int heroLastX = hero.getX();
        int heroLastY = hero.getY();
        hero.move(Constants.DOWN);

        //simulate the 6 sprites moving from one block to the next one
        for(int i = 0; i<6; i++)
            hero.step();

        assertEquals(heroLastY + 1, hero.getY());
        assertEquals(heroLastX, hero.getX());
        assertEquals(hero.getDirection(), Constants.DOWN);
	}
	
	/**
	 * Adds a new hero and moves (invalidly) it to the left
	 * The position remains the same but the direction that
	 * the hero's facing changes accordingly to the failed
	 * movement.
	 */
	@Test
	public void testHeroInvalidMovement() {
        setUp(1, 4);
        int heroLastX = hero.getX();
        int heroLastY = hero.getY();
        hero.move(Constants.LEFT);

        assertFalse(hero.isMoving());
        assertEquals(heroLastY, hero.getY());
        assertEquals(heroLastX, hero.getX());
        assertEquals(hero.getDirection(), Constants.LEFT);
	}

    /**
     * Adds a new hero next to the dog. The hero
     * moves to the dog position and catches
     * the dog.
     */
    @Test
    public void testHeroCatchDog() {
        setUp(1, 4);
        Dog dog = new Dog(2, 4, Constants.LEFT);
        map.addMapObject(dog);

        assertFalse(hero.hasDog());

        hero.move(Constants.RIGHT);

        //simulate the 6 sprites moving from one block to the next one
        for(int i = 0; i<6; i++)
            hero.step();

        assertTrue(hero.hasDog());
        assertTrue(dog.getWithHero());
    }

    /**
     * Adds a new hero next to the key. The hero
     * moves to the key position and catches
     * the key.
     */
    @Test
    public void testHeroCatchKey() {
        setUp(1, 4);
        Key key = new Key(2, 4, Constants.LEFT, "real");
        map.addMapObject(key);

        assertFalse(hero.hasKey());

        hero.move(Constants.RIGHT);

        //simulate the 6 sprites moving from one block to the next one
        for(int i = 0; i<6; i++)
            hero.step();

        assertTrue(hero.hasKey());
        assertTrue(key.getWithHero());
    }
	
}