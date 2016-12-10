package Mapviewer;

import java.io.Serializable;

/* Tuple object mainly used for store the coordinates of item and implements the Serializable,
   which will be written into files later.
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
