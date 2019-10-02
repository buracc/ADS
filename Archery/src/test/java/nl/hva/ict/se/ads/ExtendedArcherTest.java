package nl.hva.ict.se.ads;

import org.junit.jupiter.api.Test;


import java.util.List;

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
    public void checkWinner(){
        List<Archer> archers = Archer.generateArchers(10);
        ArcherComparator comparator = new ArcherComparator();
        ChampionSelector.quickSort(archers, comparator);

        System.out.println(archers);
    }
}
