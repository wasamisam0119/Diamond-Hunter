package Mapviewer;

import java.io.*;
import java.util.HashMap;

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
    int x,y;
    public static final int BOAT = 0;
    public static final int AXE = 1;
    public static final int TILESIZE = 16;
    public int [][] mapValue;   // the value of each of the map block
    private GameMap gameMap;
    private GraphicsContext gContext;
    private ObjectInputStream objectReader;
    private ObjectOutputStream objectWriter;
    public Image map=new Image(getClass().getResourceAsStream("/Tilesets/testtileset.gif"));
    public Image image=new Image(getClass().getResourceAsStream("/Sprites/items.gif"));
    String filename= "ItemMap.data";
    File file;
    Tuple axe = null;   //store the item's positon
    Tuple boat = null;
    HashMap<Integer,Tuple> items;
    @FXML private TextArea xPosition,yPosition;
    @FXML private Canvas canvas;
    public void initialize() {

        try {
            gameMap = new GameMap(TILESIZE, TILESIZE, map);
            gameMap.loadMap("/Maps/testmap.map");
            gContext = canvas.getGraphicsContext2D();
            gameMap.drawMap(gContext);
            file= new File(filename);
            if (!file.exists())
            {
                items= new HashMap<Integer,Tuple>();
            }
            else{
                objectReader = new ObjectInputStream(new FileInputStream(file));
                items=(HashMap<Integer,Tuple>) objectReader.readObject();
                objectReader.close();

            }
            if (items.containsKey(0))
            {
                boat=items.get(0);
                drawItem(0,boat.x,boat.y);
            }
            if (items.containsKey(1))
            {
                axe=items.get(1);
                drawItem(1,axe.x,axe.y);
            }
            // initialize game map
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
                System.out.println(y);
                mapValue=gameMap.getMap();
                /*for (int i=0;i<40;i++)
                {
                    for (int j=0;j<40;j++)
                    {
                        System.out.print(mapValue[i][j]);
                        System.out.print(" ");
                    }
                    System.out.println();
                }*/
                int currentPoint=mapValue[y][x];
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
            displayCoordinate();
            drawItem(1, x, y);
        }
        else {

            drawItem(1, x, y);
            axe = new Tuple(x,y);
            displayCoordinate();
        }
    }

    public void displayCoordinate()
    {
        xPosition.setText(Integer.toString(x));
        yPosition.setText(Integer.toString(y));
    }
    public void ringAlert(Alert.AlertType alertType, String message){
        Alert alert = new Alert(alertType, message);
        alert.showAndWait();
    }
    @FXML public void saveClose(){
        if (boat!=null)
        {
            items.put(0,boat);
        }
        if (axe!=null)
        {
            items.put(1,axe);
        }
        if (!items.isEmpty())
        {
            try {
                file.createNewFile();
            }catch (IOException e) {
                e.printStackTrace();
            }
            objectWriter(file);
        }
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

    public void objectWriter(File file) {
        try {
            objectWriter = new ObjectOutputStream(new FileOutputStream(file));
            objectWriter.writeObject(items);
            objectWriter.close();
        } catch (IOException ioe) {
            System.err.println("cannot write " + filename + " for writing!");
        }

    }


}
