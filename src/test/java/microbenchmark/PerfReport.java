package microbenchmark;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public void getAverage(Map<Integer,PerfReport > map,String event){



        double sum = map.values().stream()
                .filter(x->x.getEvent().equals(event))
                .mapToDouble(x -> Double.parseDouble(x.getCounter()))
                .sum();

        double average = sum/2;
        System.out.println("mean: "+ event+ " "+ average);
        double variance = map.values().stream()
                .filter(x->x.getEvent().equals(event))
                .mapToDouble(x -> Double.parseDouble(x.getCounter()))
                .map(x->  (Math.pow(x-average, 2.00)))
                .sum();


        double standard_deviation = Math.sqrt(variance/1);
        System.out.println("standard_deviation: "+ event + " "+  standard_deviation);

    }


    public   Map<Integer, PerfReport> extract_info(int value) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<Integer,PerfReport> mapValue = new HashMap<>();
        String path = "/home/robothg/Desktop/project_/project_Java/hello_java/welcome_java/src/test/java/microbenchmark/report.json";
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
