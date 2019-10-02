package nl.hva.ict.se.ads;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Place all your own tests for ChampionSelector in this class. Tests in any other class will be ignored!
 */
public class ExtendedChampionSelectorTest extends ChampionSelectorTest {

    @Test
    public void checkQuickSort(){
        List<Archer> archersQuickSort = Archer.generateArchers(23);
        List<Archer> archersCollectionSort = new ArrayList<>(archersQuickSort);
        ChampionSelector.quickSort(archersQuickSort, comparator);
        ChampionSelector.collectionSort(archersCollectionSort, comparator);
        assertEquals(archersCollectionSort, archersQuickSort);
    }

    //Add a test that counts the time for execution
    @Test
    public void milisCounter(){
        long startingTime = System.currentTimeMillis();
        for (int numberOfArch = 100; numberOfArch < 5000000; numberOfArch *= 2){
            List<Archer> list = Archer.generateArchers(numberOfArch);
            ChampionSelector.quickSort(list, comparator);
            long endTime = System.currentTimeMillis();
            System.out.println(numberOfArch + " : " + (endTime - startingTime));
        }
    }
}
