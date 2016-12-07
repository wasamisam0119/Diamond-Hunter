package Mapviewer;

import java.io.*;
import java.util.ArrayList;

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
    public static final int BOAT = 0;
    public static final int AXE = 1;
    public static final int TILESIZE = 16;
    public int [][] mapValue;
    private GameMap gameMap;
    private GraphicsContext gContext;
    int x,y;
    public Image map=new Image(getClass().getResourceAsStream("/Tilesets/testtileset.gif"));
    public Image image=new Image(getClass().getResourceAsStream("/Sprites/items.gif"));
    String filename= "ItemMap.data";
    File file;
    Tuple axe = null;
    Tuple boat = null;
    ArrayList<Tuple> items;
    @FXML private TextArea xPosition,yPosition;
    @FXML private Canvas canvas;
    public void initialize() {

        try {
            file= new File(filename);
            if (!file.exists())
            {
                file.createNewFile();
                items= new ArrayList<Tuple>();
            }
            else{

            }
            items=new ArrayList();
            // initialize game map
            gameMap = new GameMap(TILESIZE, TILESIZE, map);
            gameMap.loadMap("/Maps/testmap.map");
            gContext = canvas.getGraphicsContext2D();
            gameMap.drawMap(gContext);
        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    @FXML public void axePressed() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("X: " + event.getX() + " Y: " + event.getY());
                x = (int)event.getX()/16;
                y = (int)event.getY()/16;
                System.out.println(x);
                mapValue=gameMap.getMap();
                int currentPoint=mapValue[x][y];
                checkAvailable(currentPoint,x,y);

            }
        });

    }

    @FXML public void boatPressed() {
        //setPosition();
    }

    public void checkAvailable (int value,int x, int y) {

        if (value == 20 || value == 22 || value == 21) {
            ringAlert(Alert.AlertType.ERROR,"This positon is not available!");
        }
        else if (axe != null) {
            drawItem(3, axe.x, axe.y);
            axe.setPosition(x, y);
            drawItem(1, x, y);
        }
        else {

            drawItem(1, x, y);
            axe = new Tuple(x,y);
        }
    }

    public void ringAlert(Alert.AlertType alertType, String message){
        Alert alert = new Alert(alertType, message);
        alert.showAndWait();
    }
    @FXML public void saveClose(){
        write_file();
        System.exit(0);
    }

    public void drawItem(int type, int x, int y ){
        PixelReader pixelReader= image.getPixelReader();
        PixelReader p = map.getPixelReader();

        if (type==1)
        {
            canvas.getGraphicsContext2D().drawImage(new WritableImage(pixelReader, TILESIZE, TILESIZE, TILESIZE, TILESIZE), x * TILESIZE , y * TILESIZE);
        }
        else if (type==0)
        {
            canvas.getGraphicsContext2D().drawImage(new WritableImage(pixelReader,0, TILESIZE, TILESIZE, TILESIZE), x * TILESIZE , y * TILESIZE);

        }
        else{
            canvas.getGraphicsContext2D().drawImage(new WritableImage(p,TILESIZE,0,TILESIZE,TILESIZE),x*TILESIZE,y*TILESIZE);
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
