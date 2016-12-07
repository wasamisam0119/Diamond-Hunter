package Mapviewer;

import java.io.Serializable;

/**
 * Created by Sam_Du on 16/12/7.
 */
public class Tuple implements Serializable{
    public int x;
    public int y;

    public Tuple(int x,int y) {
        this.x = x;
        this.y = y;
    }
    public void setPosition(int x,int y){
        this.x=x;
        this.y=y;
    }


}
