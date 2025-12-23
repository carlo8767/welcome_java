package hello_thread.ExecutorConcurrent;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Executors and
Futur:  result of an asynchronous computations )
Methods are provided to check if the computation is complete,
to wait for its completion, and to retrieve the result of the computation
 */
public class HelloExecutor {


    private static final Logger log = LoggerFactory.getLogger(HelloExecutor.class);

    static void main (String [] args){

        try (final ExecutorService executorService = Executors.newFixedThreadPool(3)){
            executorService.submit(()-> System.out.println("Execution Begin"));
            executorService.shutdown();

        }
        catch(Exception e){
            log.error("e: ", e);
        }

    }
}
