import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class FileWriterUtil {

    public static void writeBestTravelToCSVFile(String solutionCSVFilePath, Travel bestTravel) {
        try (PrintWriter writer = new PrintWriter(new File(solutionCSVFilePath))) {
            StringBuilder sb = new StringBuilder();
            List<City> cities = bestTravel.getCities();

            for (City city : cities) {
                int indexOfCity = city.getIndex();
                sb.append(Integer.toString(indexOfCity));
                sb.append("\n");
            }
            writer.write(sb.toString());
//            System.out.println("\nWrite " + csvFilePath + " success!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
