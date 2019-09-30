package java.nl.hva.ict.se.ads;

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

    private static List<Archer> quickSort(List<Archer> archers, Comparator<Archer> scoringScheme, int first, int last) {
        int firstIndex = first;
        int lastIndex = last;

        if (archers.size() <= 1) {
            return archers;
        }

        Archer pivot = archers.get(firstIndex + (lastIndex - firstIndex) / 2);

        while (firstIndex <= lastIndex) {
            while (scoringScheme.compare(archers.get(firstIndex), pivot) > 0) {
                firstIndex++;
            }

            while (scoringScheme.compare(pivot, archers.get(lastIndex)) > 0) {
                lastIndex--;
            }

            if (firstIndex <= lastIndex) {
                Archer firstPlayer = archers.get(firstIndex);
                archers.set(firstIndex, archers.get(lastIndex));
                archers.set(lastIndex, firstPlayer);
                firstIndex++;
                lastIndex--;
            }
        }

        if (first < lastIndex) {
            quickSort(archers, scoringScheme, first, lastIndex);
        }
        if (firstIndex < last) {
            quickSort(archers, scoringScheme, firstIndex, last);
        }

        return archers;
    }

    /**
     * This method uses quick sort for sorting the archers.
     */
    public static List<Archer> quickSort(List<Archer> archers, Comparator<Archer> scoringScheme) {
        return quickSort(archers, scoringScheme, 0, archers.size() - 1);
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
