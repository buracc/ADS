package test;

import model.Location;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;




public class LocationTest {

    private Location a;
    private Location b;

    @Before
    public void setUp() throws Exception {
        a = new Location(0,0);
        b = new Location(10,10);
    }

    @Test
    public void checkSquares() {
        int distance = 20;
        assertEquals(distance, a.getTravelledSquares(b));
    }

    @Test
    public void checkTravelTime(){
        int distance = 20;
        double timePassed = distance*1.5;
        assertEquals(timePassed, a.travelTime(b), 0.00001);

    }
}