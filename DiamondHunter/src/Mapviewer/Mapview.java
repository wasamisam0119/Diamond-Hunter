package Mapviewer;

import com.neet.DiamondHunter.TileMap.Tile;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import com.neet.DiamondHunter.*;
import com.neet.DiamondHunter.Main.GamePanel;
import com.neet.DiamondHunter.TileMap.TileMap;
import javafx.fxml.FXML;

public class Mapview {
    private int x;
    private int y;
    private int xdest;
    private int ydest;
    private int speed;
    private boolean moving;

    // bounds
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;

    // map
    private int[][] map;
    private int tileSize;
    private int numRows;
    private int numCols;
    private int width;
    private int height;

    // tileset
    private BufferedImage tileset;
    private int numTilesAcross;
    private Tile[][] tiles;

    private TileMap tileMap;
    // drawing
    private int rowOffset;
    private int colOffset;
    private int numRowsToDraw;
    private int numColsToDraw;
    @FXML public void initialize()
    {
        tileMap = new TileMap(16);
        tileMap.loadTiles("/Tilesets/testtileset.gif");
        tileMap.loadMap("/Maps/testmap.map");
    }
    public void loadTiles(String s) {

        try {

            tileset = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );
            numTilesAcross = tileset.getWidth() / tileSize;
            tiles = new Tile[2][numTilesAcross];

            BufferedImage subimage;
            for(int col = 0; col < numTilesAcross; col++) {
                subimage = tileset.getSubimage(
                        col * tileSize,
                        0,
                        tileSize,
                        tileSize
                );
                tiles[0][col] = new Tile(subimage, Tile.NORMAL);
                subimage = tileset.getSubimage(
                        col * tileSize,
                        tileSize,
                        tileSize,
                        tileSize
                );
                tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    public void loadMap(String s) {
        try {
            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(in)
            );

            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];
            width = numCols * tileSize;
            height = numRows * tileSize;

            xmin = GamePanel.WIDTH - width;
            xmin = -width;
            xmax = 0;
            ymin = GamePanel.HEIGHT - height;
            ymin = -height;
            ymax = 0;

            String delims = "\\s+";
            for (int row = 0; row < numRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for (int col = 0; col < numCols; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
