import java.util.ArrayList;
import java.util.List;

public class Travel {
    private List<City> cities;
    private int numberOfCities;

    public Travel() {
        this.cities = new ArrayList<>();
        this.numberOfCities = 0;
    }

    public Travel(List<City> cities) {
        this.cities = new ArrayList<>();
        for (City city : cities) {
            this.cities.add(new City(city.getIndex(), city.getX(), city.getY()));
        }
        this.numberOfCities = this.cities.size();
    }

    public List<City> getCities() {
        return cities;
    }

    private Travel copy(Travel another) {
        return new Travel(another.cities);
    }

    public City getCity(int index) {
        return cities.get(index);
    }

    public double getDistance() {
        double distance = 0;
        for (int i = 0; i < numberOfCities; ++i) {
            City starting = getCity(i);
            City destination;
            if (i < numberOfCities - 1) {
                destination = getCity(i + 1);
            } else {
                destination = getCity(0);
            }
            distance += starting.distanceTo(destination);
        }
        return distance;
    }

    public double getFitness() {
        return -getDistance();
    }

    // Operator 1: Swap Two Cities
    private Travel swapCities(int indexA, int indexB) {
        Travel copy = copy(this);
        City cityA = copy.getCity(indexA);
        City cityB = copy.getCity(indexB);
        copy.cities.set(indexA, cityB);
        copy.cities.set(indexB, cityA);
        return copy;
    }

    // Operator 2: Reverse All Cities Between FromIndex to ToIndex
    private Travel reverseCities(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {  // make sure fromIndex < toIndex
            int temp = fromIndex;
            fromIndex = toIndex;
            toIndex = temp;
        }

        Travel copy = copy(this);
        List<City> partialCities = new ArrayList<>();
        for (int index = fromIndex; index <= toIndex; index++) {
            partialCities.add(getCity(index));
        }
        for (int index = toIndex; index >= fromIndex; index--) {
            copy.cities.set(index, partialCities.get(0));
            partialCities.remove(0);
        }
        return copy;
    }

    // Operator 3: Insert the City at IndexOfCity at the Index InsertAtIndex
    private Travel insertCity(int insertAtIndex, int indexOfCity) {
        Travel copy = copy(this);
        City city = getCity(indexOfCity);
        if (insertAtIndex < indexOfCity) {
            copy.cities.remove(indexOfCity);
            copy.cities.add(insertAtIndex, city);
        } else if (insertAtIndex > indexOfCity) {
            copy.cities.add(insertAtIndex, city);
            copy.cities.remove(indexOfCity);
        }
        return copy;
    }

    // Get a Random Neighbour Based On Operator
    public Travel getRandomNeighbour(String operatorType) {
        int indexA = 0, indexB = 0;
        while (indexA == indexB) {
            indexA = (int)(Math.random() * numberOfCities);
            indexB = (int)(Math.random() * numberOfCities);
        }

        if (operatorType.equals(OperatorType.SWAP)) {
            return this.swapCities(indexA, indexB);  // Method1: Swap CityA at indexA with CityB at indexB
        } else if (operatorType.equals(OperatorType.REVERSE)) {
            return this.reverseCities(indexA, indexB);  // Method2: Reverse Cities Between IndexA and IndexB
        } else if (operatorType.equals(OperatorType.INSERT)) {
            return this.insertCity(indexA, indexB);  // Method3: Insert CityB at indexB at indexA
        }
        return null;
    }

    // Get All the Neighbour Based on Operator 1: Swap
    public List<Travel> getNeighbours() {
        List<Travel> neighbours = new ArrayList<>();
        for (int i = 0; i < numberOfCities; ++i) {
            for (int j = 0; j < numberOfCities; ++j) {
                if (i < j) {
                    Travel aNeighbour = swapCities(i, j);
                    neighbours.add(aNeighbour);
                }
            }
        }
        return neighbours;
    }
}
