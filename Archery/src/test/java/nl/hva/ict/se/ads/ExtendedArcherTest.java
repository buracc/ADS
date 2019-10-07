package nl.hva.ict.se.ads;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Place all your own tests for Archer in this class. Tests in any other class will be ignored!
 */
public class ExtendedArcherTest extends ArcherTest {

    @Test
    public void checkToString() {
        List<Archer> archers = Archer.generateArchers(5);
        System.out.println(archers);
    }

    @Test
    public void checkWinnerHigherScore() {
        List<Archer> archers = Archer.generateArchers(2);
        archers.get(0).setTotalScore(100);
        archers.get(1).setTotalScore(200);
        ArcherComparator comparator = new ArcherComparator();
        System.out.println(archers);
<<<<<<< Updated upstream

        //The highest should have the last index
=======
>>>>>>> Stashed changes
        assertTrue(comparator.compare(archers.get(0), archers.get(1)) > 0);
        assertEquals(comparator.getWinner(archers.get(0), archers.get(1)), archers.get(1));
    }

    @Test
    public void checkWinnerHigherWeighted() {
        List<Archer> archers = Archer.generateArchers(2);
        archers.get(0).setTotalScore(100);
        archers.get(1).setTotalScore(100);
        archers.get(0).setTotalWeightedScore(100);
        archers.get(1).setTotalWeightedScore(200);
        ArcherComparator comparator = new ArcherComparator();
        System.out.println(archers);
<<<<<<< Updated upstream

        //The highest should have the last index
=======
>>>>>>> Stashed changes
        assertTrue(comparator.compare(archers.get(0), archers.get(1)) > 0);
        assertEquals(comparator.getWinner(archers.get(0), archers.get(1)), archers.get(1));
    }

    @Test
    public void checkWinnerHigherId() {
        List<Archer> archers = Archer.generateArchers(2);
        archers.get(0).setTotalScore(100);
        archers.get(1).setTotalScore(100);
        archers.get(0).setTotalWeightedScore(100);
        archers.get(1).setTotalWeightedScore(100);
        ArcherComparator comparator = new ArcherComparator();
        System.out.println(archers);
<<<<<<< Updated upstream

        //The highest should have the last index
=======
>>>>>>> Stashed changes
        assertTrue(comparator.compare(archers.get(0), archers.get(1)) > 0);
        assertEquals(archers.get(1), comparator.getWinner(archers.get(0), archers.get(1)));
    }

    @Test
    public void checkRegisterScore() {
        Archer archer = new Archer("Bob", "Ross");
        int[] points = {5, 6, 1};
        archer.registerScoreForRound(3, points);
        assertEquals(12, archer.getTotalScore());
        assertEquals(12 + 3, archer.getTotalWeightedScore());
        archer.registerScoreForRound(1, points);
        assertEquals(12 * 2, archer.getTotalScore());
        assertEquals((12 + 3) * 2, archer.getTotalWeightedScore());
    }

    @Test
    public void checkRegisterScoreRoundThrow() {
        Archer archer = new Archer("Bob", "Ross");
        int[] points = {5, 6, 1};
<<<<<<< Updated upstream
        assertThrows(IndexOutOfBoundsException.class, () -> archer.registerScoreForRound(11, points));
=======
        archer.registerScoreForRound(11, points);
>>>>>>> Stashed changes
    }

    @Test
    public void checkRegisterScorePointsThrow() {
        Archer archer = new Archer("Bob", "Ross");
        int[] points = {5, 6, 1, 3, 1};
<<<<<<< Updated upstream

        assertThrows(IndexOutOfBoundsException.class, () -> archer.registerScoreForRound(8, points));
=======
        archer.registerScoreForRound(8, points);
>>>>>>> Stashed changes
    }

    @Test
    public void checkWeightedScorre() {
        Archer archer = new Archer("Bob", "Ross");
        int[] points = {5, 6, 1};
        archer.registerScoreForRound(0, points);
        int totalScore = archer.getTotalScore();
        int totalWeightedScore = archer.getTotalWeightedScore();
        int expectedWeightedScore = totalScore + 3;
        assertEquals(expectedWeightedScore, totalWeightedScore);

    }

}
