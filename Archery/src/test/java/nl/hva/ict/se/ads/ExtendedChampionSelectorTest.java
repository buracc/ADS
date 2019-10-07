package nl.hva.ict.se.ads;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Place all your own tests for ChampionSelector in this class. Tests in any other class will be ignored!
 */
public class ExtendedChampionSelectorTest extends ChampionSelectorTest {

    private List<Archer> collectionSortList;
    private List<Archer> insertionSortList;
    private List<Archer> quickSortList;

    public void creatLists(int nrArchers){
        collectionSortList = Archer.generateArchers(nrArchers);
        insertionSortList = new ArrayList<>(collectionSortList);
        quickSortList = new ArrayList<>(collectionSortList);
    }

    @Test
    public void checkQuickSort(){
        creatLists(23);
        ChampionSelector.quickSort(quickSortList, comparator);
        ChampionSelector.collectionSort(collectionSortList, comparator);
        assertEquals(collectionSortList, quickSortList);
    }

    //Add a test that counts the time for execution
    @Test
    public void milisCounter(){
        long diff = 0;
        //All sorts in 1 method, use the same list of Archers for each sort
        for (int numberOfArch = 100; numberOfArch < 5_000_000 && diff <= 20000; numberOfArch *= 2){
            creatLists(numberOfArch);

            long startingTime = System.currentTimeMillis();

            ChampionSelector.quickSort(quickSortList, comparator);

            long endTime = System.currentTimeMillis();
            diff = endTime - startingTime;

            System.out.println(numberOfArch + " : " + diff);
        }
    }
}
