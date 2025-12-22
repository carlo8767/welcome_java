package java_21.virtualThread;

import java.time.Duration;
import java.util.concurrent.Executors;

// https://docs.oracle.com/en/java/javase/21/core/virtual-threads.html#GUID-2DDA5807-5BD5-4ABC-B62A-A1230F0566E0
public class VirtualThread {


    public static void main(String[] args) {
        // try (var executor = Executors.newVirtualThreadPerTaskExecutor())
        try (var executor = Executors.newSingleThreadExecutor()) {
            int i = 0;
            while (i < 10_000) {
                executor.execute(() -> {
                    try {
                        System.out.println("Starting to execute task");
                        Thread.sleep(Duration.ofSeconds(1));
                        System.out.println("Task executed");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                });
                i++;
            }
        }

    }
}
