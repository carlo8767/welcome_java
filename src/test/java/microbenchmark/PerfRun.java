package microbenchmark;


import org.json.JSONArray;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class StreamPerf implements Runnable {
    private InputStream inputStream;
    private Consumer<String> consumer;

    public StreamPerf(InputStream inputStream, Consumer<String> consumer) {
        this.inputStream = inputStream;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        // READ TEXT FROM A CHARACTER INPUT
        new BufferedReader(new InputStreamReader(inputStream)).lines()
                .forEach(consumer);
    }

    /*
    TASK ONE :  a: perf list | grep -i l1
                    result in : dcache-load-misses
                    l1d_pend_miss.pending_cycles
                b: perf list | grep -i l2
                    l2_rqsts.miss
                    perf list | grep -i l2
                    cycle_activity.cycles_l2_miss
                c:mem_inst_retired.all_loads
                  perf list | grep -i mem
     */


    public static void  extraction_L1(String compressed, String type) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        PerfReport pf = new PerfReport();
        Map<Integer,PerfReport> mapValue = new HashMap<>();
        JSONArray jsonArray = new JSONArray();
        int i = 0;
        while(i <2) {
            builder.command(
                    "perf", "stat",
                    "-e",
                    "L1-dcache-load-misses,l1d_pend_miss.pending_cycles,mem_inst_retired.all_loads",
                    "-j",
                    "--append",
                    "-o",
                    "report_l1.json",
                    compressed, type,
                    "05_InfrastructureObservability.pdf"

            );
            String path = System.getProperty("user.home");
            builder.redirectErrorStream(true);
            builder.directory(new File(path + "/Desktop/project_/project_Java/hello_java/welcome_java/src/test/java/microbenchmark"));
            Process process = builder.start();
            i++;


            builder.directory(new File(path + "/Desktop/project_/project_Java/hello_java/welcome_java/src/test/java/microbenchmark"));
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        mapValue = pf.extract_info("report_l1.json");
        pf.getAverage(mapValue,"L1-dcache-load-misses", "L1");
        pf.getAverage(mapValue,"l1d_pend_miss.pending_cycles", "L1");
        pf.getAverage(mapValue,"mem_inst_retired.all_loads", "L1");

    }


    public static void  extraction_L2 (String compressed, String type) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        PerfReport pf = new PerfReport();
        Map<Integer,PerfReport> mapValue = new HashMap<>();
        JSONArray jsonArray = new JSONArray();
        int i = 0;
        while(i <10) {
            builder.command(
                    "perf", "stat",
                    "-e",
                    "l2_rqsts.miss,cycle_activity.cycles_l2_miss,mem_inst_retired.all_loads",
                    "-j",
                    "--append",
                    "-o",
                    "report_l2.json",
                    compressed, type,
                    "05_InfrastructureObservability.pdf"


            );

            String path = System.getProperty("user.home");
            builder.redirectErrorStream(true);
            builder.directory(new File(path + "/Desktop/project_/project_Java/hello_java/welcome_java/src/test/java/microbenchmark"));
            Process process = builder.start();
            i++;


            builder.directory(new File(path + "/Desktop/project_/project_Java/hello_java/welcome_java/src/test/java/microbenchmark"));
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            i++;
        }
        mapValue = pf.extract_info("report_l2.json");
        pf.getAverage(mapValue,"l2_rqsts.miss", "L2");
        pf.getAverage(mapValue,"cycle_activity.cycles_l2_miss", "L2");
        pf.getAverage(mapValue,"mem_inst_retired.all_loads", "L2");

    }

    public static void main (String[] args) throws IOException {
        StreamPerf.extraction_L1("xz", "-k");
        StreamPerf.extraction_L2("xz", "-k");
        StreamPerf.extraction_L1("zstd", "-k");
        StreamPerf.extraction_L2("zstd", "-k");
    }

}