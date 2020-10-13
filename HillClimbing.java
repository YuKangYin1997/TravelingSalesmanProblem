import javafx.scene.paint.Stop;

import java.io.*;
import java.util.*;

public class HillClimbing {

    /**
     * Steepest Ascent Hill Climbing
     * For a280.tsp bestDistance is Around 6500
     * @param startingTravel a random travel
     * @param numberOfMaxIterations the maximum amount of iterations
     * @param timeThreshold the maximum amount of minutes to spend
     * @return best ever travel in the searching space (local optima in most cases)
     */
    public Travel hcSteepestAscent(Travel startingTravel, int numberOfMaxIterations, int timeThreshold) {
        StopWatch stopWatch = new StopWatch();
        boolean climb = true;
        Travel bestEverTravel = startingTravel;
        int numberOfIterations = -1;

        while (climb && numberOfIterations < numberOfMaxIterations && stopWatch.elapsedMinutes() < timeThreshold) {
            List<Travel> neighbours = bestEverTravel.getNeighbours();
            climb = false;

            // one iteration
            for (Travel neighbour : neighbours) {
                if (neighbour.getFitness() > bestEverTravel.getFitness()) {
                    climb = true;
                    bestEverTravel = neighbour;
                }
            }
            numberOfIterations += 1;
        }
        return bestEverTravel;
    }

    /**
     * Random Ascent Hill Climbing
     * For a280.tsp bestDistance is Around 6500
     * @param startingTravel a random travel
     * @param numberOfMaxIterations the maximum amount of iterations
     * @param timeThreshold the maximum amount of minutes to spend
     * @return best ever travel in the searching space (local optima in most cases)
     */
    public Travel hcRandomAscent(Travel startingTravel, int numberOfMaxIterations, int timeThreshold) {
        StopWatch stopWatch = new StopWatch();
        Travel bestEverTravel = startingTravel;
        int numberOfIteration = -1;

        while (true) {
            List<Travel> neighbours = bestEverTravel.getNeighbours();
            List<Travel> goodNeighbours = new ArrayList<>();

            // one iteration
            for (Travel neighbour : neighbours) {
                if (neighbour.getFitness() > bestEverTravel.getFitness()) {
                    goodNeighbours.add(neighbour);
                }
            }
            numberOfIteration += 1;

            if (goodNeighbours.size() > 0) {
                int randomIndex = (int)(Math.random() * goodNeighbours.size());
                bestEverTravel = goodNeighbours.get(randomIndex);
            } else {
                // break condition 1: reaching local optima
                break;
            }

            // break condition 2: used up all iterations or used up time
            if (numberOfIteration >= numberOfMaxIterations || stopWatch.elapsedMinutes() >= timeThreshold) {
                break;
            }
        }
        return bestEverTravel;
    }

    /**
     * First Ascent Hill Climbing
     * For a280.tsp bestDistance is Around 5500
     * @param startingTravel a random travel
     * @param timeThreshold the maximum amount of minutes to spend
     * @return best ever travel in the searching space (local optima in most cases)
     */
    public Travel hcFirstAscent(Travel startingTravel, int timeThreshold) {
        StopWatch stopWatch = new StopWatch();
        boolean climb = true;
        Travel bestEverTravel = startingTravel;

        while (climb && stopWatch.elapsedMinutes() < timeThreshold) {
            List<Travel> neighbours = bestEverTravel.getNeighbours();
            climb = false;

            for (Travel neighbour : neighbours) {
                if (neighbour.getFitness() > bestEverTravel.getFitness()) {
                    climb = true;
                    bestEverTravel = neighbour;
                    break;
                }
            }
        }
        return bestEverTravel;
    }

    /**
     * First Ascent Hill Climbing with Random Restart
     * @param startingTravels a list of random travels
     * @param timeThresholdForEachRestart the maximum amount of minutes to spend for each restart
     * @return best ever travel in the searching space (local optima in most cases)
     */
    public Travel hcFirstAscentWithRandomRestart(List<Travel> startingTravels, int timeThresholdForEachRestart) {
        // first ascent hill climbing with random start
        List<Travel> potentialBestTravels = new ArrayList<>();
        for (Travel startingTravel : startingTravels) {
            Travel potentialBestTravel = hcFirstAscent(startingTravel, timeThresholdForEachRestart);
            potentialBestTravels.add(potentialBestTravel);
            System.out.println("Potential Best Distance: " + (int)potentialBestTravel.getDistance());
        }

        // find best travel from potentialBestTravels
        Travel bestTravel = null;
        for (Travel travel : potentialBestTravels) {
            if (bestTravel == null || bestTravel.getFitness() < travel.getFitness()) {
                bestTravel = travel;
            }
        }
        return bestTravel;
    }

    public static void main(String[] args) throws Exception {
        // set up tsp file and solution (csv) file
        String tspFileName = args[0];
        String solutionFileName = "solution.csv";

        String projectDirectoryPath = new File("").getAbsolutePath();
        String tspFilePath = projectDirectoryPath + "/" + tspFileName;
        String solutionCSVFilePath = projectDirectoryPath + "/" + solutionFileName;

        // set up graph
        Graph graph = new Graph();
        boolean success = graph.readTSPFile(tspFilePath);
        if (!success) {
            throw new Exception("Load " + tspFileName + " failed, please download .tsp file under TravelingSalesPerson directory.");
        }
        graph.fillDistanceMatrix();

        // Solution 1: Steepest Hill Climbing
//        HillClimbing hillClimbing = new HillClimbing();
//        Travel startingTravel = graph.getRandomTravel();
//        int numberOfMaxIterations = 10000;
//        int timeThreshold = 10;
//        Travel bestTravel = hillClimbing.hcSteepestAscent(startingTravel, numberOfMaxIterations, timeThreshold);
//        int bestDistance = (int)bestTravel.getDistance();
//        System.out.println(bestDistance);

        // Solution 2: Random Ascent Hill Climbing
//        HillClimbing hillClimbing = new HillClimbing();
//        Travel startingTravel = graph.getRandomTravel();
//        int numberOfMaxIterations = 10000;
//        int timeThreshold = 10;
//        Travel bestTravel = hillClimbing.hcRandomAscent(startingTravel, numberOfMaxIterations, timeThreshold);
//        int bestDistance = (int)bestTravel.getDistance();
//        System.out.println(bestDistance);

        // Solution 3: First Ascent Hill Climbing
//        HillClimbing hillClimbing = new HillClimbing();
//        Travel startingTravel = graph.getRandomTravel();
//        int timeThreshold = 10;
//        Travel bestTravel = hillClimbing.hcFirstAscent(startingTravel, timeThreshold);
//        int bestDistance = (int)bestTravel.getDistance();
//        System.out.println(bestDistance);

        // Solution 4: First Ascent Hill Climbing With Random Restart
        int restartTimes = 5;
        int timeThresholdForEachRestart = 10;
        List<Travel> startingTravels = new ArrayList<>();
        for (int times = 0; times < restartTimes; ++times) {
            startingTravels.add(graph.getRandomTravel());
        }
        HillClimbing hillClimbing = new HillClimbing();
        Travel bestTravel = hillClimbing.hcFirstAscentWithRandomRestart(startingTravels, timeThresholdForEachRestart);
        int bestDistance = (int)bestTravel.getDistance();
        System.out.println(bestDistance);

        // write city indexes to solution.csv
        FileWriterUtil.writeBestTravelToCSVFile(solutionCSVFilePath, bestTravel);

        // verify
        boolean correct = VerifierUtil.verifyDistance(solutionCSVFilePath, bestDistance, graph);
        if (!correct) {
            System.out.println("Something wrong in " + solutionCSVFilePath);
        }
    }
}
