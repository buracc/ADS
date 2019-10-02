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
}
