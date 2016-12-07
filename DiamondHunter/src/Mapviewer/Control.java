package Mapviewer;

/**
 * Created by Sam_Du on 16/12/4.
 */
import java.io.*;
import java.text.*;
import java.util.*;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javafx.scene.canvas.GraphicsContext;

public class Control {
    public static final int BOAT = 0;
    public static final int AXE = 1;
    public static final int TILESIZE = 16;
    @FXML private TextArea xPosition,yPosition;
    @FXML private Canvas canvas;
    int x,y;
    int axeOrboat=-1;
    private GameMap gameMap;
    private GraphicsContext gContext;
    private Image map;
    private int tileWidth = 16;
    private int tileHeight = 16;
    public Image image=new Image(getClass().getResourceAsStream("/Sprites/items.gif"));;
    public int [][] mapValue;
    PrintWriter pw;
    String filename= "ItemMap.data";
    String itemStr;

    public void initialize() {


        map = new Image(getClass().getResourceAsStream("/Tilesets/testtileset.gif"));
        // initialize game map
        gameMap = new GameMap(tileWidth, tileHeight, map);
        gameMap.loadMap("/Maps/testmap.map");
        gContext = canvas.getGraphicsContext2D();
        gameMap.drawMap(gContext);
    }

    @FXML public void axePressed() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("X: " + event.getX() + " Y: " + event.getY());
                x = (int)event.getX()/16;
                y = (int)event.getY()/16;
                mapValue=gameMap.getMap();
                //checkAvailable();
                drawItem(AXE,x,y);

            }
        });
        itemStr="0"+xPosition.getText()+yPosition.getText();
        axeOrboat=1;
        write_file(itemStr);

    }

    @FXML public void boatPressed() {
        axeOrboat=0;
        //setPosition();
    }
    public void checkAvailable ()
    {

    }
    public void drawItem(int type, int x, int y ){
        PixelReader pixelReader= image.getPixelReader();
        if (type==1)
        {
            canvas.getGraphicsContext2D().drawImage(new WritableImage(pixelReader, TILESIZE, TILESIZE, TILESIZE, TILESIZE), x * TILESIZE , y * TILESIZE);
        }
        else
        {
            canvas.getGraphicsContext2D().drawImage(new WritableImage(pixelReader,0, TILESIZE, TILESIZE, TILESIZE), x * TILESIZE , y * TILESIZE);

        }
    }

    public void write_file(String itemStr) {
        try {
            pw = new PrintWriter(filename);
            pw.println(itemStr);
            pw.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("cannot open " + filename + " for writing!");
        }

    }


}
