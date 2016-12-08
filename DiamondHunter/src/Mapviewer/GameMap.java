package Mapviewer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class GameMap {
    private int tileWidth;
    private int tileHeight;
    private int cols;
    private int numCols;
    private int numRows;
    private Image tileimage;
    private int [][] map;

    public GameMap(int tileWidth,int tileHeight, Image i){
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.tileimage = i;
        cols = (int) (i.getWidth() / tileWidth);
    }
    public void loadMap(String s){
        try {

            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(in)
            );

            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];
            String delims = "\\s+";
            for(int row = 0; row < numRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for(int col = 0; col < numCols; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void drawMap(GraphicsContext gc) {
        int mapWidth = map[0].length;
        int mapHeight = map.length;
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                int px = map[y][x] % cols;
                int py = map[y][x] / cols;
                gc.drawImage(tileimage, px * tileWidth, py * tileHeight, tileWidth, tileHeight, x * tileWidth, y
                        * tileHeight, tileWidth, tileHeight);
            }
        }
    }
    public void drawItem(GraphicsContext gc,Image image){
        //gc.drawImage(image,);
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }
}

