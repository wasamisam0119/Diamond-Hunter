package Mapviewer;

import org.junit.Test;
import static org.junit.Assert.*;

/*Test the Tuple setter*/
public class TupleTest {
    @Test
    public void setPositionTest() {
        Tuple tuple=new Tuple(5,1);
        tuple.setPosition(0,1);
        assertTrue(tuple.x==0);
        assertTrue(tuple.y==1);
    }

}