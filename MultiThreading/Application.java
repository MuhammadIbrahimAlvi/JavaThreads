import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    static Map<Integer, Map<String, Object>> dataMap = new HashMap<>();

    public static void main(String[] args) {
        initializeDataMap();

        int numThreads = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        int chunkSize = dataMap.size() / numThreads;

        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = (i == numThreads - 1) ? dataMap.size() : (i + 1) * chunkSize;

            final int threadIndex = i;
            executorService.submit(() -> processChunk(start, end, threadIndex));
        }

        executorService.shutdown();

        System.out.println("Multi-threaded processed data map: " + dataMap);
    }

    static void initializeDataMap() {
        for (int i = 1; i <= 9900000; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("amount", 100);
            item.put("status", "unchanged");
            dataMap.put(i, item);
        }
    }

    static void processChunk(int startIndex, int endIndex, int threadIndex) {
        for (int i = startIndex + 1; i <= endIndex; i++) {
            Map<String, Object> item = dataMap.get(i);

            long startTime = System.currentTimeMillis();
            System.out.println("Thread " + threadIndex + " processing item " + i + " at " + startTime);

            int amount = (int) item.get("amount");
            item.put("amount", amount + Thread.currentThread().getId() * 10);
            item.put("status", "changed");

            long endTime = System.currentTimeMillis();
            System.out.println("Thread " + threadIndex + " finished processing item " + i + " at " + endTime
                    + ". Elapsed time: " + (endTime - startTime) + " ms");
        }
    }
}
