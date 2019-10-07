package nl.hva.ict.se.ads;

import java.util.Comparator;

/**
 * @author Safak Inan & Burak Inan
 */
public class ArcherComparator implements Comparator<Archer> {

    /**
     * This method returns the winner between 2 archers
     * @param a1 first archer
     * @param a2 second archer to compare with
     * @return the archer that won
     */
    public Archer getWinner(Archer a1, Archer a2) {
        if (compare(a1, a2) < 0) {
            return a1;
        }
        return a2;
    }

    /**
     * This method compares 2 archers with each other based on their score, weighted score or id
     * @param o1 first archer
     * @param o2 second archer to compare with
     * @return
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
