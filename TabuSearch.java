import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TabuSearch {

    /**
     * Tabu Search
     * @param startingTravel a random travel
     * @param numberOfMaxIterations the maximum amount of iterations
     * @param maxTabuListSize the maximum size of tabu list
     * @param timeThreshold the maximum amount of minutes to spend
     * @return best ever travel in the searching space
     */
    public Travel tabuSearch(Travel startingTravel, int numberOfMaxIterations, int maxTabuListSize, int timeThreshold) {
        StopWatch stopWatch = new StopWatch();
        Travel currentTravel = startingTravel;
        Travel bestEverTravel = currentTravel;
        List<Travel> tabuList = new ArrayList<>();
        int numberOfIteration = -1;

        while (numberOfIteration < numberOfMaxIterations && stopWatch.elapsedMinutes() < timeThreshold) {
            Travel bestNeighbour = null;
            List<Travel> neighbours = currentTravel.getNeighbours();

            // one iteration
            for (Travel neighbour : neighbours) {
                if (!tabuList.contains(neighbour) && (bestNeighbour == null || neighbour.getFitness() > bestNeighbour.getFitness())) {
                    bestNeighbour = neighbour;
                }
            }
            numberOfIteration += 1;

            currentTravel = bestNeighbour;  // maybe downhill
            if (bestNeighbour.getFitness() > bestEverTravel.getFitness()) {
                bestEverTravel = bestNeighbour;
            }
            tabuList.add(bestNeighbour);
            if (tabuList.size() > maxTabuListSize) {
                tabuList.remove(0);
            }
        }
        return bestEverTravel;
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

        // Solution of Tabu Search
        TabuSearch tabuSearch = new TabuSearch();
        Travel startingTravel = graph.getRandomTravel();
        int numberOfMaxIterations = 2000;
        int maxTabuListSize = 10;
        int timeThreshold = 10;
        Travel bestTravel = tabuSearch.tabuSearch(startingTravel, numberOfMaxIterations, maxTabuListSize, timeThreshold);
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
