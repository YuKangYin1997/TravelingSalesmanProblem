import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VerifierUtil {

    /**
     * verify if expectedDistance is equals to the distance calculated from solution.csv file
     * @param solutionCSVFilePath path of file solution.csv
     * @param expectedDistance the best distance calculated by HC/SA/TA
     * @param graph graph build from .tsp file
     * @return true if expected distance equals to the distance calculated from solution.csv file, otherwise false
     */
    public static boolean verifyDistance(String solutionCSVFilePath, int expectedDistance, Graph graph) {
        try {
            Scanner sc = new Scanner(new File(solutionCSVFilePath));
            List<City> bestCities = new ArrayList<>();
            List<City> orderedCities = graph.getCities();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                int indexOfCity = Integer.parseInt(line);
                bestCities.add(orderedCities.get(indexOfCity - 1));
            }
            Travel travel = new Travel(bestCities);
            return (int)travel.getDistance() == expectedDistance;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
