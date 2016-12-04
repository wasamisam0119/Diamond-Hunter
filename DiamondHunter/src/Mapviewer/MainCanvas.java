package Mapviewer;

/**
 * Created by Sam_Du on 16/12/4.
 */
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MainCanvas extends Canvas {
    private GameMap gameMap;
    private GraphicsContext gContext;
    private Image map;
    private int tileWidth = 16;
    private int tileHeight = 16;
    private boolean isRunning = true;
    private long sleep = 100;
    // Main thread
    private Thread thread = new Thread(new Runnable() {

        @Override
        public void run() {
            while (isRunning) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        draw();
                        update();
                    }
                });
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    public MainCanvas(double width, double height) {
        super(width, height);
        map = new Image(getClass().getResourceAsStream("/Tilesets/testtileset.gif"));
        gContext = getGraphicsContext2D();
        // initialize game map
        gameMap = new GameMap(tileWidth, tileHeight, map);
        gameMap.loadMap("/Maps/testmap.map");
        thread.start();
    }

    public void draw() {
        gameMap.drawMap(gContext);
    }

    public void update() {

    }
   /* @FXML public void initialize()
    {
        gameMap = new GameMap(16,16,);
        tileMap.loadTiles("/Tilesets/testtileset.gif");
        tileMap.loadMap("/Maps/testmap.map");
    }
    */
}
