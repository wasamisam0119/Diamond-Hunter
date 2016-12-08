package Mapviewer;

import com.sun.source.tree.AssertTree;
import javafx.scene.image.Image;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameMapTest {
    public Image map;
    public GameMap gameMap;

    /* test if the GameMap is loaded correctly compared to the original map value*/
    @Test
    public void testLoadMap() throws NullPointerException{

        map = new Image(getClass().getResourceAsStream("/Tilesets/testtileset.gif"));
        gameMap=new GameMap(16,16,map);
        gameMap.loadMap("/Maps/testmap.map");
        int maptest[][]=gameMap.getMap();
        assertTrue(maptest[0][0]==20);
        assertTrue(maptest[2][2]==2);
    }



}