package nl.hva.ict.se.ads;

import java.util.Comparator;

/**
 * @author Safak Inan & Burak Inan
 */
public class ArcherComparator implements Comparator<Archer> {

    /**
     * This method returns the winner between 2 Archers.
     * @param a1 first Archer.
     * @param a2 second Archer to compare with.
     * @return the Archer that won.
     */
    public Archer getWinner(Archer a1, Archer a2) {
        if (compare(a1, a2) < 0) {
            return a1;
        }
        return a2;
    }

    /**
     * This method compares 2 Archers with each other based on their score, weighted score or id.
     * @param o1 first Archer.
     * @param o2 second Archer to compare with.
     * @return a value based on the difference of scores between teh Archers.
     */
    @Override
    public int compare(Archer o1, Archer o2) {
        int winner = o2.getTotalScore() - o1.getTotalScore();
        if (winner == 0) {
            winner = o2.getTotalWeightedScore() - o1.getTotalWeightedScore();
        }

        if (winner == 0) {
            winner = o2.getId() - o1.getId();
        }

        return winner;
    }
}
