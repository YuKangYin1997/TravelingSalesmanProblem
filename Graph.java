import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph {

    private int numberOfCities;
    private double[][] distanceMatrix;
    private List<City> cities;

    public Graph() {
        cities = new ArrayList<>();
        numberOfCities = 0;
    }

    public List<City> getCities() {
        return cities;
    }

    public boolean readTSPFile(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("EOF")) {
                    break;
                }
                // remove spaces at the head and tail of each line and split each line with space/spaces
                String[] nums = line.trim().split("\\s+");
                try {
                    int index = Integer.parseInt(nums[0]);
                    double x = Double.parseDouble(nums[1]);
                    double y = Double.parseDouble(nums[2]);
                    this.cities.add(new City(index, x, y));
                } catch (Exception ex) {
                    continue;
                }
            }
            numberOfCities = this.cities.size();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Read File " + filePath + " Failed!");
            e.printStackTrace();
        }
        return false;
    }

    public void fillDistanceMatrix() {
        distanceMatrix = new double[numberOfCities][numberOfCities];
        for (int i = 0; i < numberOfCities; ++i) {
            for (int j = 0; j < numberOfCities; ++j) {
                if (i == j) {
                    distanceMatrix[i][j] = Double.MAX_VALUE;
                    continue;
                }
                // distance between starting city and destination city
                City starting = cities.get(i);
                City destination = cities.get(j);
                distanceMatrix[i][j] = starting.distanceTo(destination);
            }
        }
    }

    public Travel getRandomTravel() {
        List<City> randomCities = new ArrayList<>();
        for (int i = 0; i < numberOfCities; ++i) {
            randomCities.add(getCity(i));
        }
        Collections.shuffle(randomCities);
        return new Travel(randomCities);
    }

    private City getCity(int index) {
        return this.cities.get(index);
    }
}
