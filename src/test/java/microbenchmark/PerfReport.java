package microbenchmark;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.NumberFormat;
import java.util.*;

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


        @Test
    public void test_paired_test(){
            PerfReport ps1 = new PerfReport();
            ps1.setCounter("3");
            ps1.setEvent("a");
            PerfReport ps2 = new PerfReport();
            ps2.setCounter("4");
            ps2.setEvent("a");
            PerfReport ps3 = new PerfReport();
            ps3.setCounter("7");
            ps3.setEvent("a");

            PerfReport ps4 = new PerfReport();
            ps4.setCounter("2");
            ps4.setEvent("b");
            PerfReport ps5 = new PerfReport();
            ps5.setCounter("4");
            ps5.setEvent("b");
            PerfReport ps6 = new PerfReport();
            ps6.setCounter("6");
            ps6.setEvent("b");

            Map<Integer,PerfReport > mapEventOne = new HashMap<>();
            mapEventOne.put(1,ps1);
            mapEventOne.put(2,ps2);
            mapEventOne.put(3,ps3);
            mapEventOne.put(4,ps4);
            mapEventOne.put(5,ps5);
            mapEventOne.put(6,ps6);

            double t = ps1.verify(mapEventOne, "a", "b");
            assert t == 1.9999999999999998 : "t is not correct";






        }




    // PAIRED TEST
    public double verify (Map<Integer,PerfReport > mapEventOne, String eventOne, String eventTwo){
        double sizeEventPair = 3;
        // CALCULATION MEAN


        // MEAN PAIRED DIFFERENCES
        double eventOneSum = mapEventOne.values().stream()
                .filter(x->x.getEvent().equals(eventOne))
                .mapToDouble(x -> Double.parseDouble(x.getCounter()))
                .sum();

        double eventsTwoSum = mapEventOne.values().stream()
                .filter(x->x.getEvent().equals(eventTwo))
                .mapToDouble(x -> Double.parseDouble(x.getCounter()))
                .sum();

        double paired_difference = eventOneSum-eventsTwoSum;
        double difference_mean = paired_difference/(sizeEventPair);


        List<Double> listEventOne = mapEventOne.values().stream()
                .filter(x -> x.getEvent().equals(eventOne))
                .map(x -> Double.parseDouble(x.getCounter()))
                .toList();

        List<Double> listEventTwo = mapEventOne.values().stream()
                .filter(x -> x.getEvent().equals(eventTwo))
                .map(x -> Double.parseDouble(x.getCounter()))
                .toList();



        double variance_difference = 0;
        for (int i=0; i<listEventOne.size(); i++){
            var d  = (listEventOne.get(i) - listEventTwo.get(i))-difference_mean;
            var pow = Math.pow(d,2);
                variance_difference+=pow;
        }
        double variance = variance_difference/(sizeEventPair-1);
        double standardDeviation = Math.sqrt(variance);

        double pairedTest = difference_mean/(standardDeviation/(Math.sqrt(sizeEventPair)));
        return pairedTest;
    }



    // PAIRED TEST
    public void t_test_paired_test (Map<Integer,PerfReport > mapEventOne, String eventOne, String eventTwo){
        double sizeEventPair = mapEventOne.size()/StreamPerf.eventsCacheExtracted;
        // CALCULATION MEAN


        // MEAN PAIRED DIFFERENCES
        double eventOneSum = mapEventOne.values().stream()
                .filter(x->x.getEvent().equals(eventOne))
                .mapToDouble(x -> Double.parseDouble(x.getCounter()))
                .sum();

        double eventsTwoSum = mapEventOne.values().stream()
                .filter(x->x.getEvent().equals(eventTwo))
                .mapToDouble(x -> Double.parseDouble(x.getCounter()))
                .sum();

        double paired_difference = eventOneSum-eventsTwoSum;
        double difference_mean = paired_difference/(sizeEventPair);

        List<Double> listEventOne = mapEventOne.values().stream()
                .filter(x -> x.getEvent().equals(eventOne))
                .map(x -> Double.parseDouble(x.getCounter()))
                .toList();


        List<Double> listEventTwo = mapEventOne.values().stream()
                .filter(x -> x.getEvent().equals(eventTwo))
                .map(x -> Double.parseDouble(x.getCounter()))
                .toList();


        double variance_difference = 0;
        for (int i=0; i<listEventOne.size(); i++){
            var d  = (listEventOne.get(i) - listEventTwo.get(i))-difference_mean;
            var pow = Math.pow(d,2);
            variance_difference+=pow;
        }
        double variance = variance_difference/(sizeEventPair-1);
        double standard_deviationDifference = Math.sqrt(variance);


        double pairedTest = difference_mean/(standard_deviationDifference/(Math.sqrt(sizeEventPair)));
        System.out.println( "paired test values is " + pairedTest);

    }



    // WELCH TEST
     public void t_test (Map<Integer,PerfReport > mapEventOne,Map<Integer,PerfReport > mapEventTwo, String eventOne, String eventTwo){
         double sizeEvent = mapEventOne.size()/StreamPerf.eventsCacheExtracted;
            // CALCULATION MEAN
         double eventOneSum = mapEventOne.values().stream()
                 .filter(x->x.getEvent().equals(eventOne))
                 .mapToDouble(x -> Double.parseDouble(x.getCounter()))
                 .sum();

         double eventsTwoSum = mapEventTwo.values().stream()
                 .filter(x->x.getEvent().equals(eventTwo))
                 .mapToDouble(x -> Double.parseDouble(x.getCounter()))
                 .sum();

         double meanEventOne = eventOneSum/sizeEvent;
         double meanEventTwo = eventsTwoSum/sizeEvent;

         //CALCULATION VARIANCE
         double varianceOne = mapEventOne.values().stream()
                 .filter(x->x.getEvent().equals(eventOne))
                 .mapToDouble(x -> Double.parseDouble(x.getCounter())-meanEventOne)
                 .map(x->  (Math.pow(x, 2.00)))
                 .map(x-> x/(sizeEvent-1))
                 .sum();

         double welcheOne = (varianceOne/sizeEvent);
        
         double varianceTwo = mapEventTwo.values().stream()
                 .filter(x->x.getEvent().equals(eventTwo))
                 .mapToDouble(x -> Double.parseDouble(x.getCounter())-meanEventTwo)
                 .map(x->  (Math.pow(x, 2.00)))
                 .map(x-> x/(sizeEvent-1))
                 .sum();

         double welcheTWo = varianceTwo/sizeEvent;

         double welche_test = (meanEventOne-meanEventTwo)/ (Math.sqrt(welcheTWo+welcheOne));

         System.out.println("The t-value is for unpaired test is "+eventOne+ " " +welche_test);


     }






    public void getAverage(Map<Integer,PerfReport > map,String event, String type){
        NumberFormat nf = NumberFormat.getInstance(java.util.Locale.GERMAN);

        double sum = map.values().stream()
                .filter(x->x.getEvent().equals(event))
                .mapToDouble(x -> Double.parseDouble(x.getCounter()))
                .sum();


        double sizeEvent = map.size()/StreamPerf.eventsCacheExtracted;
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
        // HERE THE ERRORR
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


        System.out.println(path);
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
