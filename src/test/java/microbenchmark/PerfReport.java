package microbenchmark;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.NumberFormat;
import java.util.*;

import static org.apache.xmpbox.type.Types.Locale;

public class PerfReport{


        @JsonProperty("counter-value")
        String counter;
        @JsonProperty("unit")
        String unit;
        @JsonProperty("event")
        String event;
        @JsonProperty("event-runtime")
        long eventRuntime;
        @JsonProperty("pcnt-running")
        double pctRunning;



     public void t_test (Map<Integer,PerfReport > map,String eventOne, String eventTwo){
         double sizeEvent = map.size()/4.00;
         double eventOneSum = map.values().stream()
                 .filter(x->x.getEvent().equals(eventOne))
                 .mapToDouble(x -> Double.parseDouble(x.getCounter()))
                 .sum();

         double eventsTwoSum = map.values().stream()
                 .filter(x->x.getEvent().equals(eventTwo))
                 .mapToDouble(x -> Double.parseDouble(x.getCounter()))
                 .sum();
         double meanEventOne = eventOneSum/sizeEvent;
         double meanEventTwo = eventsTwoSum/sizeEvent;


         double varianceOne = map.values().stream()
                 .filter(x->x.getEvent().equals(eventOne))
                 .mapToDouble(x -> Double.parseDouble(x.getCounter())-meanEventOne)
                 .map(x->  (Math.pow(x, 2.00)))
                 .map(x-> x/(sizeEvent-1))
                 .sum();

         double standard_deviationOne = (varianceOne/sizeEvent);

         double varianceTwo = map.values().stream()
                 .filter(x->x.getEvent().equals(eventTwo))
                 .mapToDouble(x -> Double.parseDouble(x.getCounter())-meanEventTwo)
                 .map(x->  (Math.pow(x, 2.00)))
                 .map(x-> x/(sizeEvent-1))
                 .sum();

         double standard_deviationTwo = varianceTwo/sizeEvent;

         double welche_test = (meanEventOne-meanEventTwo)/ (Math.sqrt(standard_deviationTwo+standard_deviationOne));










     }






    public void getAverage(Map<Integer,PerfReport > map,String event, String type){
        NumberFormat nf = NumberFormat.getInstance(java.util.Locale.GERMAN);

        double sum = map.values().stream()
                .filter(x->x.getEvent().equals(event))
                .mapToDouble(x -> Double.parseDouble(x.getCounter()))
                .sum();


        double sizeEvent = map.size()/4.00;
        System.out.println("\n"+"METRICS FOR "+ type);
        System.out.println("--> TOTAL: "+ event+ " "+ nf.format(sum));
        double average = sum/sizeEvent;
        System.out.println("--> MEAN: "+ event+ " "+ nf.format(average));

        double variance = map.values().stream()
                .filter(x->x.getEvent().equals(event))
                .mapToDouble(x -> Double.parseDouble(x.getCounter())-average)
                .map(x->  (Math.pow(x, 2.00)))
                .map(x-> x/(sizeEvent-1))
                .sum();

        double standard_deviation = Math.sqrt(variance);
        System.out.println("-->STANDARD DEVIATION: "+ event + " "+  nf.format(standard_deviation) +"\n");

    }


    public   Map<Integer, PerfReport> extract_info(String name_file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<Integer,PerfReport> mapValue = new HashMap<>();
        String path = "/home/robothg/Desktop/project_/project_Java/hello_java/welcome_java/src/test/java/microbenchmark/"+name_file;
        PerfReport pf = mapper.readValue(new File(path), PerfReport.class);
        List<PerfReport> reports = new ArrayList<>();

        try (MappingIterator<PerfReport> iterator =
                     mapper.readerFor(PerfReport.class)
                             .readValues(new File(path))) {

            while (iterator.hasNextValue()) {
                reports.add(iterator.nextValue());
            }

            for(int i=0; i<reports.size(); i++){
                mapValue.put(i, reports.get(i));
            }
        }
        // ERASE CONTENT
        try (BufferedWriter writer = Files.newBufferedWriter(
                Paths.get(path),
                StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING)) {

            writer.write("");
        }
        return mapValue;

    }


    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public long getEventRuntime() {
        return eventRuntime;
    }

    public void setEventRuntime(long eventRuntime) {
        this.eventRuntime = eventRuntime;
    }

    public double getPctRunning() {
        return pctRunning;
    }

    public void setPctRunning(double pctRunning) {
        this.pctRunning = pctRunning;
    }
}
