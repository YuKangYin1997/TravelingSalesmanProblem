public class StopWatch {
    private final long start;

    public StopWatch() {
        start = System.currentTimeMillis();
    }

    public int elapsedMinutes() {
        long now = System.currentTimeMillis();
        return (int) ((now - start) / (1000 * 60));
    }
}
