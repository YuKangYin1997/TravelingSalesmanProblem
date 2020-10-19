import java.io.File;

public class SimulatedAnnealing {

    /**
     * basic cooling strategy
     * @param temperature temperature before cooling
     * @param coolingRate
     * @return temperature after cooling
     */
    public double cool(double temperature, double coolingRate) {
        temperature *= (1-coolingRate);
        return temperature;
    }

    /**
     * enhanced cooling strategy
     * @param temperature temperature before cooling
     * @param coolingRate
     * @param coolingEnhancer an adaptive parameter to slow down cooling process
     * @return temperature after cooling
     */
    public double coolWithCoolingEnhancer(double temperature, double coolingRate, double coolingEnhancer) {
        temperature *= (1-coolingRate * coolingEnhancer);
        return temperature;
    }

    /**
     * get parameter cooling enhancer based on the number of cities
     * @param numberOfCities
     * @return cooling enhancer
     */
    public double getCoolingEnhancer(int numberOfCities) {
        double coolingEnhancer = 0.0;
        if (numberOfCities < 30) {
            coolingEnhancer = 0.5;
        } else if (numberOfCities < 150) {
            coolingEnhancer = 0.05;
        } else if (numberOfCities < 750) {
            coolingEnhancer = 0.005;
        } else {
            coolingEnhancer = 0.0005;
        }
        return coolingEnhancer;
    }

    /**
     * Solution 1: Basic Simulated Annealing
     * For a280.tsp bestDistance is Around 6000 when Operator is SWAP
     * For a280.tsp bestDistance is Around 4500 when Operator is INSERT
     * For a280.tsp bestDistance is Around 3000 when Operator is REVERSE
     * @param startingTravel a random travel
     * @param startingTemperature
     * @param endingTemperature
     * @param coolingRate
     * @param operatorType three options: SWAP, INSERT and REVERSE (default REVERSE)
     * @param timeThreshold the maximum amount of minutes to spend
     * @return best ever travel in the searching space
     */
    public Travel basicSA(Travel startingTravel, double startingTemperature, double endingTemperature, double coolingRate, String operatorType, int timeThreshold) {
        StopWatch stopWatch = new StopWatch();
        Travel bestEverTravel = startingTravel;
        Travel currentTravel = startingTravel;
        double temperature = startingTemperature;

        while (temperature > endingTemperature && stopWatch.elapsedMinutes() < timeThreshold) {
            Travel neighbour = currentTravel.getRandomNeighbour(operatorType);
            double neighbourDistance = neighbour.getDistance();
            double currentDistance = currentTravel.getDistance();
            double deltaDistance = neighbourDistance - currentDistance;

            // normal acceptance possibility
            double p = Math.exp(-deltaDistance / temperature);

            if (deltaDistance < 0) {
                currentTravel = neighbour;
            } else if (p >= Math.random()) {
                currentTravel = neighbour;
            }

            double bestEverDistance = bestEverTravel.getDistance();
            if (bestEverDistance > neighbourDistance) {
                bestEverTravel = neighbour;
            }

            temperature = cool(temperature, coolingRate);
        }
        return bestEverTravel;
    }

    /**
     * Solution 2: Dynamic Simulated Annealing-Cooling Enhancer-Modified Acceptance Possibility Simulated Annealing
     * For a280.tsp bestDistance is Around 3300 when Operator is SWAP
     * For a280.tsp bestDistance is Around 2800 when Operator is INSERT
     * For a280.tsp bestDistance is Around 2600 when Operator is REVERSE
     * @param startingTravel a random travel
     * @param startingTemperature
     * @param endingTemperature
     * @param coolingRate
     * @param coolingEnhancer an adaptive parameter to slow down cooling process
     * @param operatorType three options: SWAP, INSERT and REVERSE (default REVERSE)
     * @param timeThreshold the maximum amount of minutes to spend
     * @return best ever travel in the searching space
     */
    public Travel DSA_CE_MAP_SA(Travel startingTravel, double startingTemperature, double endingTemperature, double coolingRate, double coolingEnhancer, String operatorType, int timeThreshold) {
        StopWatch stopWatch = new StopWatch();
        Travel bestEverTravel = startingTravel;
        Travel currentTravel = startingTravel;
        double temperature = startingTemperature;

        while (temperature > endingTemperature && stopWatch.elapsedMinutes() < timeThreshold) {
            Travel neighbour = currentTravel.getRandomNeighbour(operatorType);
            double neighbourDistance = neighbour.getDistance();
            double currentDistance = currentTravel.getDistance();
            double bestEverDistance = bestEverTravel.getDistance();

            double deltaDistance = neighbourDistance - currentDistance;
            double deltaDistance2 = bestEverDistance - neighbourDistance;

            // modified acceptance probability
            double p = (Math.exp(1) - deltaDistance / temperature) / (Math.exp(1) - deltaDistance2 / temperature);

            if (deltaDistance < 0) {
                currentTravel = neighbour;
            } else if (p >= Math.random()) {
                currentTravel = neighbour;
            }

            if (bestEverDistance > neighbourDistance) {
                bestEverTravel = neighbour;
            }
            temperature = coolWithCoolingEnhancer(temperature, coolingRate, coolingEnhancer);
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

        // Solution 1 Basic SA
//        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing();
//        Travel startingTravel = graph.getRandomTravel();
//        double startingTemperature = 100000.0;
//        double endingTemperature = 0.0001;
//        double coolingRate = 0.0001;
//        String operatorType = OperatorType.REVERSE;
//        int timeThreshold = 5;
//        Travel bestTravel = simulatedAnnealing.basicSA(startingTravel, startingTemperature, endingTemperature, coolingRate, operatorType, timeThreshold);
//        int bestDistance = (int)bestTravel.getDistance();
//        System.out.println(bestDistance);

        // Solution 2 Dynamic Simulated Annealing-Cooling Enhancer-Modified Acceptance Probability SA
        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing();
        Travel startingTravel = graph.getRandomTravel();
        double startingTemperature = 100000.0;
        double endingTemperature = 0.0001;
        double coolingRate = 0.0001;
        int numberOfCities = startingTravel.getCities().size();
        double coolingEnhancer = simulatedAnnealing.getCoolingEnhancer(numberOfCities);
        String operatorType = OperatorType.REVERSE;
        int timeThreshold = 5;
        Travel bestTravel = simulatedAnnealing.DSA_CE_MAP_SA(startingTravel, startingTemperature, endingTemperature, coolingRate, coolingEnhancer, operatorType, timeThreshold);
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
