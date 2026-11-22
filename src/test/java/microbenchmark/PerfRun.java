package microbenchmark;


import org.json.JSONArray;
import org.mockito.Mockito;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;

class StreamPerf  {
    public final static double eventsCacheExtracted = 4;
    public final static double sizeCycle = 10;


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
                e: mem_load_retired.l1_hit
                   perf list | grep -i L1 | grep -i hit
                   perf list | grep -i L2 | grep  -i hit
                   mem_load_retired.l2_hit

                   Privileged process sudo sysctl -w kernel.perf_event_paranoid=1

     */


    public  Map<Integer,PerfReport>     extraction_L1(String compressed, String type) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder();
        PerfReport pf = new PerfReport();
        Map<Integer,PerfReport> mapValue = new HashMap<>();
        int i = 0;
        while(i <sizeCycle) {
            builder.command(
                    "perf", "stat",
                    "-e",
                    "L1-dcache-load-misses,l1d_pend_miss.pending_cycles,mem_inst_retired.all_loads,mem_load_retired.l1_hit",
                    "-j",
                    "--append",
                    "-o",
                    "report_l1.json",
                    compressed, type,
                    "05_InfrastructureObservability.pdf"
            );
            String path = System.getProperty("user.home");
            builder.directory(new File(path + "/Desktop/project_/project_Java/hello_java/welcome_java/src/test/java/microbenchmark"));
            try {
                Process process = builder.start();
                int code = process.waitFor();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            i++;
        }
        mapValue = pf.extract_info("report_l1.json");
        pf.getAverage(mapValue,"L1-dcache-load-misses", "L1");
        pf.getAverage(mapValue,"l1d_pend_miss.pending_cycles", "L1");
        pf.getAverage(mapValue,"mem_inst_retired.all_loads", "L1");

        return mapValue;

    }


    public  Map<Integer,PerfReport>  extraction_L2 (String compressed, String type) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder();
        PerfReport pf = new PerfReport();
        Map<Integer,PerfReport> mapValue = new HashMap<>();
        int i = 0;
        while(i <sizeCycle) {
            builder.command(
                    "perf", "stat",
                    "-e",
                    "l2_rqsts.miss,cycle_activity.cycles_l2_miss,mem_inst_retired.all_loads,mem_load_retired.l2_hit",
                    "-j",
                    "--append",
                    "-o",
                    "report_l2.json",
                    compressed, type,
                    "05_InfrastructureObservability.pdf"
            );

            String path = System.getProperty("user.home");
          //  builder.redirectErrorStream(true);
            builder.directory(new File(path + "/Desktop/project_/project_Java/hello_java/welcome_java/src/test/java/microbenchmark"));
            Process process = builder.start();
            int code = process.waitFor();

           /* try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }*/

            i++;
        }
        mapValue = pf.extract_info("report_l2.json");
        pf.getAverage(mapValue,"l2_rqsts.miss", "L2");
        pf.getAverage(mapValue,"cycle_activity.cycles_l2_miss", "L2");
        pf.getAverage(mapValue,"mem_inst_retired.all_loads", "L2");
        return mapValue;

    }

    public static void main (String[] args) throws IOException, InterruptedException {

        StreamPerf s = new StreamPerf();
        Map<Integer,PerfReport> m1 =  s.extraction_L1("xz", "-f");
        Map<Integer,PerfReport> m2 = s.extraction_L2("xz", "-f");
        Map<Integer,PerfReport> m3 = s.extraction_L1("zstd", "-f");
        Map<Integer,PerfReport> m4 = s.extraction_L2("zstd", "-f");


        PerfReport pf = new PerfReport();
        pf.t_test_paired_test(m3 ,"mem_load_retired.l1_hit","L1-dcache-load-misses");
        pf.t_test_paired_test( m4, "mem_load_retired.l2_hit","l2_rqsts.miss");
        pf.t_test_paired_test(m1 ,"mem_load_retired.l1_hit","L1-dcache-load-misses");
        pf.t_test_paired_test( m2, "mem_load_retired.l2_hit","l2_rqsts.miss");
    }


}