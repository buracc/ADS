package nl.hva.ict.se.ads;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Place all your own tests for ChampionSelector in this class. Tests in any other class will be ignored!
 */
public class ExtendedChampionSelectorTest extends ChampionSelectorTest {

    private List<Archer> archers = new ArrayList<>();

    @BeforeEach
    public void createLists(){
        archers = Archer.generateArchers(23);
    }

    @Test
    public void checkQuickSort(){
        List<Archer> quickSorted = ChampionSelector.quickSort(archers, comparator);
        List<Archer> collectionsSorted = ChampionSelector.collectionSort(archers, comparator);
        assertEquals(quickSorted, collectionsSorted);
    }

    //Add a test that counts the time for execution
    @Test
    public void quickSortTiming(){
        long diff = 0;
        //All sorts in 1 method, use the same list of Archers for each sort
        for (int numberOfArch = 100; numberOfArch < 5_000_000 && diff <= 20000; numberOfArch *= 2){

            long startingTime = System.currentTimeMillis();

            ChampionSelector.quickSort(archers, comparator);

            long endTime = System.currentTimeMillis();
            diff = endTime - startingTime;

            System.out.println(numberOfArch + " : " + diff);
        }
    }

    @Test
    public void insSortTiming(){
        long diff = 0;
        //All sorts in 1 method, use the same list of Archers for each sort
        for (int numberOfArch = 100; numberOfArch < 5_000_000 && diff <= 20000; numberOfArch *= 2){

            long startingTime = System.currentTimeMillis();

            ChampionSelector.selInsSort(archers, comparator);

            long endTime = System.currentTimeMillis();
            diff = endTime - startingTime;

            System.out.println(numberOfArch + " : " + diff);
        }
    }

    @Test
    public void collectionsSortTiming(){
        long diff = 0;
        //All sorts in 1 method, use the same list of Archers for each sort
        for (int numberOfArch = 100; numberOfArch < 5_000_000 && diff <= 20000; numberOfArch *= 2){

            long startingTime = System.currentTimeMillis();

            ChampionSelector.collectionSort(archers, comparator);

            long endTime = System.currentTimeMillis();
            diff = endTime - startingTime;

            System.out.println(numberOfArch + " : " + diff);
        }
    }
}
