package smile;


import org.apache.commons.csv.CSVFormat;
import smile.data.DataFrame;


import smile.data.Tuple;
import smile.io.Read;


import java.io.IOException;
import java.net.URISyntaxException;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class HelloSmile {

    public static  String PATH_FILE = "/home/robothg/Desktop/project_/project_Java/hello_java/welcome_java/src/main/java/smile/Italy.csv";

    public void loadFile() throws IOException, ParseException, URISyntaxException {
        try {
           /*
             We can use smile to manipulate the data and create machine learning model
             1. READ A FILE
              CSVFormat csvFormat = CSVFormat.Builder.create()
                    .setDelimiter(",")
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .get();
             2. EXTRACT DATA

              f Viola-Jones face detector
              Random Forest Algorithm
               https://haifengl.github.io/validation.html BINARY CLASSIFICATION EXAMPLE
               https://www.kaggle.com/datasets/dongruishen/soccer
               // https://haifengl.github.io/api/java/index.html
               data frame documentation : https://haifengl.github.io/api/java/smile/data/DataFrame.html#select(int...)
                         Sea	Lge	Date	HT	AT	HS	AS	GD	WDL
                         00-01	GER1	11/08/2000	Dortmund	Hansa Rostock	1	 0	     1	     W
            */
            CSVFormat csvFormat = CSVFormat.Builder.create()
                    .setDelimiter(",")
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .get();
            DataFrame df = Read.csv(
                    PATH_FILE,
                    csvFormat
            );
            utilityInfo(df);
        } catch (Exception e) {
            System.out.println("There is an error "+ e);
        }
    }
    public void utilityInfo (DataFrame df) throws SQLException {
        System.out.println("Schema: " + df.schema());
        var columnsHomeTown = df.column("HT");
        // Create a new data frame with specific colum
        // DataFrame
        //loc(Object... rows) I need to find the index with a specific value
        DataFrame selectColumn = df.select("HT", "HS");
        System.out.println(selectColumn);
        // LEFT HAND OF THE EQUATION
        List<Tuple> dortmundRows = new ArrayList<>();
        for (Tuple column : df) {
            if ("Dortmund".equals(column.get("HT"))) {
                dortmundRows.add(column);
            }
        }
        for (Tuple s : dortmundRows){
            System.out.println(s);
        }

    }


    static  void main (String [] args ) throws IOException, ParseException, URISyntaxException {

        HelloSmile ns = new HelloSmile();
        ns.loadFile();

    }


    }





