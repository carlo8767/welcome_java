package smile;

import org.apache.commons.csv.CSVFormat;
import smile.data.DataFrame;

import smile.io.Read;


public class CrossValidation {




    public static  String PATH_FILE = "/home/robothg/Desktop/Leipzig/Lecture_ThirdYear/ArtificIalIntelligence/exam/train.csv";


    public void readFile() {
     try {
         CSVFormat csvFormat = CSVFormat.Builder.create()
                 .setDelimiter(",")
                 .setHeader()
                 .setIgnoreEmptyLines(true)
                 .setSkipHeaderRecord(false)
                 .get();


         DataFrame df = Read.csv(PATH_FILE, csvFormat);
         int nrow = df.nrow();          // number of rows
         int ncol = df.ncol();          // number of columns
         int size = df.size();          // alias for nrow()
     }

     catch (Exception e) {
        System.out.println("Impossible to read");
     }


    }



    public  static  void main (String [] args){
        CrossValidation crossValidation = new CrossValidation();
        crossValidation.readFile();
    }

}
