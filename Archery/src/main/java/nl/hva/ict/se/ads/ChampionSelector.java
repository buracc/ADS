package nl.hva.ict.se.ads;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Given a list of Archer's this class can be used to sort the list using one of three sorting algorithms.
 */
public class ChampionSelector {
    /**
     * This method uses either selection sort or insertion sort for sorting the archers.
     */
    public static List<Archer> selInsSort(List<Archer> archers, Comparator<Archer> scoringScheme) {
        Archer tempPrev;

        for (int i = 1; i < archers.size(); i++) {
            int j = i - 1;
            while (j >= 0 && scoringScheme.compare(archers.get(j), archers.get(j + 1)) < 0) {
                tempPrev = archers.get(j);
                archers.set(j, archers.get(j + 1));
                archers.set(j + 1, tempPrev);
                j--;
            }
        }
        return archers;
    }

    /**
     * This method uses quick sort to sort the list of archers
     * @param archers the list of archers
     * @param scoringScheme the comparator needed to compare the archers with each other
     * @param first the first indeze in the list
     * @param last the last index in the list
     * @return a list of quick sorted archers
     */
    private static int partition(List<Archer> archers, Comparator<Archer> scoringScheme, int first, int last) {
        int firstCounter = first;
        int lastCounter = last+1;
        int pivot = first;

        while (true) {
            while (scoringScheme.compare(archers.get(++firstCounter), archers.get(pivot)) > 0) {
                if (firstCounter == last){
                    break;
                }
            }

            while (scoringScheme.compare(archers.get(pivot), archers.get(--lastCounter)) > 0) {
                if (lastCounter == first){
                    break;
                }
            }

            if (firstCounter >= lastCounter){
                break;
            }
            //Swap archers.get(firstCounter) met archers.get(lastCounter)
            Archer temp = archers.get(firstCounter);
            archers.set(firstCounter, archers.get(lastCounter));
            archers.set(lastCounter, temp);
        }
        //Swap places with archers.get(pivot) and archers.get(lastCoutner)
        //Set pivot equal to lastCounter, because lastCounter is the "last" lower number of the pivot
        Archer temp = archers.get(pivot);
        archers.set(pivot, archers.get(lastCounter));
        archers.set(lastCounter, temp);
        pivot = lastCounter;
        return pivot;
    }

    /**
     * This method uses quick sort for sorting the archers.
     */
    public static List<Archer> quickSort(List<Archer> archers, Comparator<Archer> scoringScheme) {
        Collections.shuffle(archers);
        benis(archers, scoringScheme, 0, archers.size() - 1);
        return archers;
    }

    private static void benis(List<Archer> archers, Comparator<Archer> scoringScheme, int low, int high){
        if (low >= high){
            return;
        }
        int pivot = partition(archers, scoringScheme, low, high);
        benis(archers, scoringScheme, low, pivot-1);
        benis(archers, scoringScheme, pivot+1, high);
    }

    /**
     * This method uses the Java collections sort algorithm for sorting the archers.
     */
    public static List<Archer> collectionSort(List<Archer> archers, Comparator<Archer> scoringScheme) {
        archers.sort(scoringScheme);
        return archers;
    }

    /**
     * This method uses quick sort for sorting the archers in such a way that it is able to cope with an Iterator.
     *
     * <b>THIS METHOD IS OPTIONAL</b>
     */
    public static Iterator<Archer> quickSort(Iterator<Archer> archers, Comparator<Archer> scoringScheme) {
        return null;
    }
}
