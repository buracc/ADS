package nl.hva.ict.se.ads;

import java.util.Comparator;

public class ArcherComparator implements Comparator<Archer> {

    //    @Override
//    public int compare(Archer o1, Archer o2) {
//        if (checkWinner(o1, o2)) {
//            return -1;
//        } else if (!checkWinner(o1, o2)) {
//            return 1;
//        } else {
//            return 0;
//        }
//    }
//
//    private boolean checkWinner(Archer o1, Archer o2) {
//        if (o1.getTotalScore() > o2.getTotalScore()) {
//            return true;
//        } else if (o2.getTotalScore() > o1.getTotalScore()) {
//            return false;
//        } else if (o1.getWeightedScore() > o2.getWeightedScore()) {
//            return true;
//        } else if (o2.getWeightedScore() > o1.getWeightedScore()) {
//            return false;
//        } else {
//            return o1.getId() > o2.getId();
//        }
//
//    }
    public Archer getWinner(Archer a1, Archer a2) {
        if (compare(a1, a2) < 0) {
            return a2;
        }

        return a1;
    }

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
