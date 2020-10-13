import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerUtil {

    private static final String projectFolderPath = new File("").getAbsolutePath();
    private static final String logFileName = "TSP.log";

    public static Logger getLogger() {
        Logger logger = Logger.getLogger(LoggerUtil.class.getName());
        String logFilePath = projectFolderPath;

        if (projectFolderPath.lastIndexOf("/") != projectFolderPath.length() - 1) {
            logFilePath += "/";
        }
        logFilePath += "src/";  // for IDEA
        logFilePath += logFileName;

        try {
            FileHandler fileHandler = new FileHandler(logFilePath);
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            return logger;
        } catch (IOException e) {
            System.out.println("Fail To Get Logger!");
        }
        return null;
    }
}
