package nl.hva.ict.se.ads;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Place all your own tests for Archer in this class. Tests in any other class will be ignored!
 */
public class ExtendedArcherTest extends ArcherTest {

    @Test
    public void checkToString(){
        List<Archer> archers = Archer.generateArchers(5);
        System.out.println(archers);
    }

    @Test
    public void checkWinnerHigherScore(){
        List<Archer> archers = Archer.generateArchers(2);

        archers.get(0).setTotal(100);
        archers.get(1).setTotal(200);

        ArcherComparator comparator = new ArcherComparator();

        System.out.println(archers);

        //The highest should have the last index
        assertTrue(comparator.compare(archers.get(0),archers.get(1)) > 0);
        assertEquals(comparator.getWinner(archers.get(0), archers.get(1)), archers.get(1));
    }

    @Test
    public void checkWinnerHigherWeighted(){
        List<Archer> archers = Archer.generateArchers(2);

        archers.get(0).setTotal(100);
        archers.get(1).setTotal(100);

        archers.get(0).setWeightedScore(100);
        archers.get(1).setWeightedScore(200);

        ArcherComparator comparator = new ArcherComparator();

        System.out.println(archers);

        //The highest should have the last index
        assertTrue(comparator.compare(archers.get(0),archers.get(1)) > 0);
        assertEquals(comparator.getWinner(archers.get(0), archers.get(1)), archers.get(1));
    }

    @Test
    public void checkWinnerHigherId(){
        List<Archer> archers = Archer.generateArchers(2);

        archers.get(0).setTotal(100);
        archers.get(1).setTotal(100);

        archers.get(0).setWeightedScore(100);
        archers.get(1).setWeightedScore(100);

        ArcherComparator comparator = new ArcherComparator();

        System.out.println(archers);

        //The highest should have the last index
        assertTrue(comparator.compare(archers.get(0),archers.get(1)) > 0);
        assertEquals(archers.get(1), comparator.getWinner(archers.get(0), archers.get(1)));

    }

}
