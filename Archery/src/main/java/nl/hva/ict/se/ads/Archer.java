package java.nl.hva.ict.se.ads;

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
    public static final int MISSES_WEIGHTED = 7;
    private static Random randomizer = new Random();

    private final String firstName;
    private final String lastName;
    private final int id; // Once assigned a value is not allowed to change.

    private int[][] totalScore = new int[MAX_ROUNDS][MAX_ARROWS];

    private int total;
    private int weightedScore;
    private int misses;

    /**
     * Constructs a new instance of bowman and assigns a unique ID to the instance. The ID is not allowed to ever
     * change during the lifetime of the instance! For this you need to use the correct Java keyword.Each new instance
     * is a assigned a number that is 1 higher than the last one assigned. The first instance created should have
     * ID 135788;
     *
     * @param firstName the archers first name.
     * @param lastName  the archers surname.
     */
    private Archer(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    /**
     * Registers the point for each of the three arrows that have been shot during a round. The <code>points</code>
     * parameter should hold the three points, one per arrow.
     *
     * @param round  the round for which to register the points.
     * @param points the points shot during the round.
     */
    public void registerScoreForRound(int round, int[] points) {
        for (int i = 0; i < points.length; i++) {
            totalScore[round][i] = points[i];
            total += totalScore[round][i];

            if (totalScore[round][i] == 0) {
                misses++;
                continue;
            }

            weightedScore += totalScore[round][i] + 1;
        }

        weightedScore -= misses * 7;
    }

    /**
     * Returns the total score of the archer from all the 10 rounds
     * @return total score
     */

    public int getTotalScore() {
        return total;
    }

    /**
     * This methods creates a List of archers.
     *
     * @param nrOfArchers the number of archers in the list.
     * @return
     */
    public static List<Archer> generateArchers(int nrOfArchers) {
        List<Archer> archers = new ArrayList<>(nrOfArchers);
        int id = 135788;
        for (int i = 0; i < nrOfArchers; i++) {
            Archer archer = new Archer(Names.nextFirstName(), Names.nextSurname(), id += 1);
            letArcherShoot(archer, nrOfArchers % 100 == 0);
            archers.add(archer);
        }

        return archers;
    }

    /**
     * This methods creates a Iterator that can be used to generate all the required archers. If you implement this
     * method it is only allowed to create an instance of Archer inside the next() method!
     *
     * <b>THIS METHODS IS OPTIONAL</b>
     *
     * @param nrOfArchers the number of archers the Iterator will create.
     * @return
     */
    public static Iterator<Archer> generateArchers(long nrOfArchers) {
        return null;
    }


    /**
     * Returns the id of the archer
     * @return id of archer
     */
    public int getId() {
        return id;
    }

    /**
     * This method lets the archer shoot for all rounds and registers teh score
     * @param archer the given archer
     * @param isBeginner checks if the archer is a beginner
     */
    private static void letArcherShoot(Archer archer, boolean isBeginner) {
        for (int round = 0; round < MAX_ROUNDS; round++) {
            archer.registerScoreForRound(round, shootArrows(isBeginner ? 0 : 1));
        }
    }

    /**
     * This method registers the score for a single round, for 3 times (maximum arrows shot)
     * @param min minimum score registered
     * @return an array of 3 numbers which are the scores achieved that round
     */
    private static int[] shootArrows(int min) {
        int[] points = new int[MAX_ARROWS];
        for (int arrow = 0; arrow < MAX_ARROWS; arrow++) {
            points[arrow] = shoot(min);
        }
        return points;
    }

    /**
     * This method generates a random number depending if the archer is a beginner or not
     * @param min if the archer is a beginner the minimum is 0 else it's 1
     * @return returns a random number between min and 10
     */
    private static int shoot(int min) {
        return Math.max(min, randomizer.nextInt(11));
    }

    /**
     * This method returns the weighted score of the archer
     * @return weighted score
     */
    public int getWeightedScore() {
        return weightedScore;
    }

    @Override
    public String toString() {
        return id + " (" + getTotalScore() + " / " + getWeightedScore() + ") " + firstName + " " + lastName;
    }
}
