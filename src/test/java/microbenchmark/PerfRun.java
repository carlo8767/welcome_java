package microbenchmark;


import org.json.JSONArray;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class StreamGobbler implements Runnable {
    private InputStream inputStream;
    private Consumer<String> consumer;

    public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
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


    public static void  extraction_zstd() throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        PerfReport pf = new PerfReport();
        Map<Integer,PerfReport> mapValue = new HashMap<>();
        JSONArray jsonArray = new JSONArray();
        int i = 0;
        while(i <2) {
                builder.command(
                        "perf", "stat",
                        "-e",
                        "L1-dcache-loads,L1-dcache-load-misses,cycles,l1d_pend_miss.pending",
                        "-j",
                        "--append",
                        "-o",
                        "report.json",
                        "zstd",
                        "05_InfrastructureObservabilityS.pdf"

                );
            String path = System.getProperty("user.home");
            builder.redirectErrorStream(true);
            builder.directory(new File(path + "/Desktop/project_/project_Java/hello_java/welcome_java/src/test/java/microbenchmark"));
            Process process = builder.start();
            i++;
        }
        mapValue = pf.extract_info(i);
        pf.getAverage(mapValue,"l1d_pend_miss.pending");
        pf.getAverage(mapValue,"L1-dcache-load-misses");

        }




    public static void main (String[] args) throws IOException {
        StreamGobbler.extraction_zstd();
        boolean isLinux = System.getProperty("os.name").toLowerCase().equals("linux");
        ProcessBuilder builder = new ProcessBuilder();
        PerfReport pf = new PerfReport();
        Map<Integer,PerfReport> mapValue = new HashMap<>();
        JSONArray jsonArray = new JSONArray();
        int i = 0;
        while(i <2) {

            if (isLinux) {
                //  builder.command("perf", "stat", "-e", "cycles", "instructions", "cache-references" ,"cache-misses" ,"1-dcache-load-misses","xz",  "-d" ,"05_InfrastructureObservability.pdf.xz");
                builder.command(
                        "perf", "stat",
                        "-e",
                        "L1-dcache-loads,L1-dcache-load-misses,cycles,l1d_pend_miss.pending",
                        "-j",
                        "--append",
                        "-o",
                        "report.json",
                        "xz", "-k",
                        "05_InfrastructureObservability.pdf"

                );

            } else {
                builder.command("sh", "-c", "ls");
            }
            String path = System.getProperty("user.home");
            builder.redirectErrorStream(true);
            builder.directory(new File(path + "/Desktop/project_/project_Java/hello_java/welcome_java/src/test/java/microbenchmark"));
            Process process = builder.start();
        /*    BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }*/
            i++;

        }
        mapValue = pf.extract_info(i);
        pf.getAverage(mapValue,"l1d_pend_miss.pending");
        pf.getAverage(mapValue,"L1-dcache-load-misses");




/*

        StreamGobbler streamGobbler =
                new StreamGobbler(process.getInputStream(), System.out::println);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<?> future = executorService.submit(streamGobbler);

*/
        // xz --decompress file1.txt.xz
        // xz -d file1.txt.xz
        // perf list give the event
        // https://perfwiki.github.io/main/tutorial/
        // xz -d
        //
        // sudo sysctl -w kernel.perf_event_paranoid=1
        // perf --help list pmu
       //  perf list | grep -i dcache
        // cache hit found in the cahche
        // l1d_pend_miss.pending
        // l1d_pend_miss.pending_cycles
        // perf list | grep -i L1 | grep -i cycle
        // perf list | grep -i L1 | grep -i cycle
        //  cycle_activity.cycles_l2_miss
        //  cycle_activity.cycles_l2_miss


    }

}