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
            return a2;
        }

        return a1;
    }

    /**
     * This method compares 2 archers with each other based on their score, weighted score or id
     * @param o1 first archer
     * @param o2 second archer to compare with
     * @return
     */
    @Override
    public int compare(Archer o1, Archer o2) {
        int winner = o1.getTotalScore() - o2.getTotalScore();
        if (winner == 0) {
            winner = o1.getWeightedScore() - o2.getWeightedScore();
        }

        if (winner == 0) {
            winner = o1.getId() - o2.getId();
        }

        return winner;
    }
}
