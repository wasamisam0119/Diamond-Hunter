package Mapviewer;

import java.io.*;
import java.util.HashMap;

import com.neet.DiamondHunter.Main.Game;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.canvas.GraphicsContext;

public class Control {
    int x, y;
    public static final int BOAT = 0;
    public static final int AXE = 1;
    public static final int TILESIZE = 16;
    public int[][] mapValue;   // the value of each of the map block
    public Image map;
    public Image image;
    private GameMap gameMap;
    private GraphicsContext gContext;
    private ObjectInputStream objectReader;
    private ObjectOutputStream objectWriter;
    File file;
    Tuple axe = null;   //store the item's positon
    Tuple boat = null;
    HashMap<Integer, Tuple> items;
    @FXML
    private TextArea xPosition, yPosition;
    @FXML
    private Canvas canvas;


    public void initialize() {
        //initialize the picture resource
        map = new Image(getClass().getResourceAsStream("/Tilesets/testtileset.gif"));
        image = new Image(getClass().getResourceAsStream("/Sprites/items.gif"));
        gameMap = new GameMap(TILESIZE, TILESIZE, map);
        gameMap.loadMap("/Maps/testmap.map");
        gContext = canvas.getGraphicsContext2D();
        //draw the map
        gameMap.drawMap(gContext);
        file = new File("Item.data");
        if (!file.exists()) {

            items = new HashMap<Integer, Tuple>();
        }
        else {
            //load the item file
            try {
                objectReader = new ObjectInputStream(new FileInputStream(file));
                items = (HashMap<Integer, Tuple>) objectReader.readObject();
                objectReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

        }
        if (items.containsKey(0)) {
            boat = items.get(0);
            drawItem(0, boat.x, boat.y);
        }
        if (items.containsKey(1)) {
            axe = items.get(1);
            drawItem(1, axe.x, axe.y);
        }
    }

    /* this method is used to handle the button action*/
    @FXML
    public void axePressed() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x = (int) event.getX() / 16;
                y = (int) event.getY() / 16;
                mapValue = gameMap.getMap();
                int currentPoint = mapValue[y][x];
                checkAvailable(currentPoint, x, y, AXE);

            }
        });

    }

    @FXML
    public void boatPressed() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x = (int) event.getX() / 16;
                y = (int) event.getY() / 16;
                mapValue = gameMap.getMap();
                int currentPoint = mapValue[y][x];
                checkAvailable(currentPoint, x, y, BOAT);

            }
        });

    }

    /*this method is to check if a position is available to set item,
     *if available, draw the item on canvas and put it into a tuple object
     */
    public void checkAvailable(int value, int x, int y, int type) {

        if (value == 20 || value == 22 || value == 21) {        //item can not set on the barrier

            ringAlert(Alert.AlertType.ERROR, "This positon is not available!");
        } else {

            if (type == AXE) {
                if (axe != null) {     //if already have axe, change the positon and draw the axe again
                    drawItem(3, axe.x, axe.y);
                    axe.setPosition(x, y);
                    displayCoordinate();
                    drawItem(1, x, y);
                } else {                  //if do not have item, add one to the axe object and draw
                    drawItem(1, x, y);
                    axe = new Tuple(x, y);
                    displayCoordinate();
                }
            } else {
                if (boat != null) {     //if already have boat, change the positon and draw the axe again
                    drawItem(3, boat.x, boat.y);
                    boat.setPosition(x, y);
                    displayCoordinate();
                    drawItem(0, x, y);
                } else {                  //if do not have item, add one to the boat object and draw
                    drawItem(0, x, y);
                    boat = new Tuple(x, y);
                    displayCoordinate();
                }
            }
        }
    }

    public void displayCoordinate() //display the item position on TextArea after set
    {
        xPosition.setText(Integer.toString(x));
        yPosition.setText(Integer.toString(y));
    }

    public void ringAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType, message);
        alert.showAndWait();
    }

    /*start the Main Game*/
    @FXML
    public void startGame(){
        Game.main(null);
    }

    /* save the change and write items object into the file using a Serializable hashmap.*/
    @FXML
    public void saveClose() {
        if (boat != null) {
            items.put(0, boat);
        }
        if (axe != null) {
            items.put(1, axe);
        }
        if (!items.isEmpty()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            objectWriter(file);
        }
    }

    /*draw the item on canvas, using pixelReader to cut the picture*/
    public void drawItem(int type, int x, int y) {
        PixelReader pixelReader = image.getPixelReader();
        PixelReader p = map.getPixelReader();

        if (type == AXE) {
            canvas.getGraphicsContext2D().drawImage(new WritableImage(pixelReader, TILESIZE, TILESIZE, TILESIZE, TILESIZE), x * TILESIZE, y * TILESIZE);
        }
        else if (type == BOAT) {
            canvas.getGraphicsContext2D().drawImage(new WritableImage(pixelReader, 0, TILESIZE, TILESIZE, TILESIZE), x * TILESIZE, y * TILESIZE);
        }
        //draw a grass block to replace when moving the axe or boat
        else {
            canvas.getGraphicsContext2D().drawImage(new WritableImage(p, TILESIZE, 0, TILESIZE, TILESIZE), x * TILESIZE, y * TILESIZE);
        }
    }

    /*implements the object writer*/
    public void objectWriter(File file) {
        try {
            objectWriter = new ObjectOutputStream(new FileOutputStream(file));
            objectWriter.writeObject(items);
            objectWriter.close();
        } catch (IOException ioe) {
            System.err.println("cannot write Item.data for writing!");
            ioe.printStackTrace();
        }

    }


}
