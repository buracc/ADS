package nl.hva.ict.se.ads;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TimingTest extends ExtendedChampionSelectorTest {


    @Test
    public void timing() {
        long insSortTime = 0;
        long quickSortTime = 0;
        long collectionsSortTime = 0;

        for (int i = 0; i < 10; i++) {
            System.out.println("###################### Test: " + (i + 1) + " ##########################");
            for (int numberOfArch = 100; numberOfArch < 5_000_000; numberOfArch *= 2) {
                List<Archer> insSortCopy = Archer.generateArchers(numberOfArch);
                List<Archer> quickSortCopy = new ArrayList<>(insSortCopy);
                List<Archer> collectionsSortCopy = new ArrayList<>(insSortCopy);

                if (insSortTime <= 20000) {
                    insSortTime = timeInsSort(insSortCopy);
                }

                if (quickSortTime <= 20000) {
                    quickSortTime = timeQuickSort(quickSortCopy);
                }

                if (collectionsSortTime <= 20000) {
                    collectionsSortTime = timeCollectionsSort(collectionsSortCopy);
                }
            }
            insSortTime = 0;
            quickSortTime = 0;
            collectionsSortTime = 0;
        }
    }

    private long timeInsSort(List<Archer> archers) {
        long startTime = System.currentTimeMillis();
        ChampionSelector.selInsSort(archers, comparator);
        long endTime = System.currentTimeMillis();

        long diff = endTime - startTime;
        System.out.println("Insertion Sort," + archers.size() + "," + diff);
        return diff;
    }

    private long timeQuickSort(List<Archer> archers) {
        long startTime = System.currentTimeMillis();
        ChampionSelector.quickSort(archers, comparator);
        long endTime = System.currentTimeMillis();

        long diff = endTime - startTime;
        System.out.println("Quick Sort," + archers.size() + "," + diff);
        return diff;
    }

    private long timeCollectionsSort(List<Archer> archers) {
        long startTime = System.currentTimeMillis();
        ChampionSelector.collectionSort(archers, comparator);
        long endTime = System.currentTimeMillis();

        long diff = endTime - startTime;
        System.out.println("Collections Sort," + archers.size() + ", " + diff);
        return diff;
    }
}
