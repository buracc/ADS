package nl.hva.ict.se.ads;

import java.util.*;


/**
 * Holds the name, archer-id and the points scored for 30 arrows.
 * <p>
 * Archers MUST be created by using one of the generator methods. That is way the constructor is private and should stay
 * private. You are also not allowed to add any constructor with an access modifier other then private unless it is for
 * testing purposes in which case the reason why you need that constructor must be contained in a very clear manner
 * in your report.
 */
public class Archer {

    public static final int MAX_ARROWS = 3;
    public static final int MAX_ROUNDS = 10;
    private static Random randomizer = new Random();

    private final String firstName;
    private final String lastName;
    private final int id;

    private int[][] totalScorePerRound = new int[MAX_ROUNDS][MAX_ARROWS];

    private int totalScore;
    private int totalWeightedScore;
    private int misses;

    /**
     * Constructs a new instance of bowman and assigns a unique ID to the instance. The ID is not allowed to ever
     * change during the lifetime of the instance! For this you need to use the correct Java keyword.Each new instance
     * is a assigned a number that is 1 higher than the last one assigned. The first instance created should have
     * ID 135788;
     *
     * @param firstName the Archers first name.
     * @param lastName  the Archers surname.
     */
    private Archer(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public Archer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = 1;
    }

    /**
     * Sets the total score of the Archer.
     * @param totalScore the new total score.
     */
    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void setTotalWeightedScore(int totalWeightedScore) {
        this.totalWeightedScore = totalWeightedScore;
    }

    /**
     * Registers the point for each of the three arrows that have been shot during a round. The <code>points</code>
     * parameter should hold the three points, one per arrow.
     *
     * @param round  the round for which to register the points.
     * @param points the points shot during the round.
     */
    public void registerScoreForRound(int round, int[] points) throws IndexOutOfBoundsException {
        for (int i = 0; i < points.length; i++) {
            totalScorePerRound[round][i] = points[i];
            totalScore += totalScorePerRound[round][i];

            if (totalScorePerRound[round][i] == 0) {
                misses++;
                continue;
            }

            totalWeightedScore += totalScorePerRound[round][i] + 1;
        }

        totalWeightedScore -= misses * 7;
    }

    /**
     * Returns the totalScore score of the Archer.
     *
     * @return totalScore .
     */

    public int getTotalScore() {
        return totalScore;
    }

    /**
     * This methods creates a List of Archers.
     *
     * @param nrOfArchers the number of Archers in the list.
     * @return a list of Archers.
     */
    public static List<Archer> generateArchers(int nrOfArchers) {
        List<Archer> archers = new ArrayList<>(nrOfArchers);
        int id = 135788;
        for (int i = 0; i < nrOfArchers; i++) {
            Archer archer = new Archer(Names.nextFirstName(), Names.nextSurname(), id++);
            letArcherShoot(archer, nrOfArchers % 100 == 0);
            archers.add(archer);
        }

        return archers;
    }

    /**
     * This methods creates a Iterator that can be used to generate all the required Archers. If you implement this
     * method it is only allowed to create an instance of Archer inside the next() method!
     *
     * <b>THIS METHODS IS OPTIONAL</b>
     *
     * @param nrOfArchers the number of Archers the Iterator will create.
     * @return
     */
    public static Iterator<Archer> generateArchers(long nrOfArchers) {
        return null;
    }


    /**
     * Returns the id of the Archer.
     *
     * @return id of Archer.
     */
    public int getId() {
        return id;
    }

    /**
     * This method lets the Archer shoot for all rounds and registers the score.
     *
     * @param archer the given Archer
     * @param isBeginner checks if the Archer is a beginner
     */
    private static void letArcherShoot(Archer archer, boolean isBeginner) {
        for (int round = 0; round < MAX_ROUNDS; round++) {
            archer.registerScoreForRound(round, shootArrows(isBeginner ? 0 : 1));
        }
    }

    /**
     * This method registers the score for a single round, for 3 times (maximum arrows shot).
     *
     * @param min minimum score registered.
     * @return an array consisting of the points achieved.
     */
    private static int[] shootArrows(int min) {
        int[] points = new int[MAX_ARROWS];
        for (int arrow = 0; arrow < MAX_ARROWS; arrow++) {
            points[arrow] = shoot(min);
        }
        return points;
    }

    /**
     * This method generates a random number depending on the Archer if it's a beginner or not.
     *
     * @param min if the Archer is a beginner the minimum is 0 else it's 1.
     * @return returns a random number between min and 10.
     */
    private static int shoot(int min) {
        return Math.max(min, randomizer.nextInt(11));
    }

    /**
     * This method returns the weighted score of the Archer.
     *
     * @return weighted score.
     */
    public int getTotalWeightedScore() {
        return totalWeightedScore;
    }

    @Override
    public String toString() {
        return id + " (" + getTotalScore() + " / " + getTotalWeightedScore() + ") " + firstName + " " + lastName;
    }
}
