package smile;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import org.apache.commons.csv.CSVFormat;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvFormat;
import smile.data.Collectors;
import smile.data.DataFrame;
import smile.data.formula.Formula;
import smile.io.Read;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Comparator;

public class Regression {




    public static  String PATH_FILE = "/home/robothg/Desktop/project_/project_Java/hello_java/welcome_java/src/main/java/smile/epl_final.csv";


    public void readFile () throws IOException, URISyntaxException {

        CSVFormat csvFormat = CSVFormat.Builder.create()
                .setDelimiter(",")
                .setHeader()
                .setIgnoreEmptyLines(true)
                .setSkipHeaderRecord(true)
                .get();

        //2. Extract Data
        DataFrame df = Read.csv(
                PATH_FILE,
                csvFormat
                //  schema Optional assign a schema
        );

        System.out.println("Formula before fit: " + df);

        DataFrame select = df.apply("Season","HomeTeam","AwayTeam","FullTimeHomeGoals","TotalScore", "TotalScoreHalfTime");
        df = select.factorize("HomeTeam", "AwayTeam");
        var verify  = Formula.of("species", "island", "sex", "bill_length_mm", "flipper_length_mm", "body_mass_g" );
/*
        DataFrame select = df.apply("Season","HomeTeam","AwayTeam","FullTimeHomeGoals","FullTimeAwayGoals", "FullTimeResult");
        var verify  = Formula.of("species", "island", "sex", "bill_length_mm", "flipper_length_mm", "body_mass_g" );
        System.out.println("Formula before fit: " + verify);*/


    }


    public static void main (String [] args) throws IOException, URISyntaxException {

        Regression s = new Regression();
        s.readFile();

    }





}
