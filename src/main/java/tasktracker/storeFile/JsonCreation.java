package tasktracker.storeFile;



import com.fasterxml.jackson.databind.util.JSONPObject;
import org.jdesktop.swingx.painter.ImagePainter;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;
import tasktracker.task.TaskModel;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import java.io.*;
import java.nio.file.*;


public class JsonCreation {

    public String path = "/home/robothg/Desktop/project_/project_Java/hello_java/welcome_java/src/main/java/tasktracker/storeFile/task.json";



    public void writeJsonFile (TaskModel taskModel)  {
        // Creation JsonObject
        JSONObject jo = new JSONObject();
        jo.put("id", taskModel.id());
        jo.put("status",taskModel.status());
        jo.put("createdAt", taskModel.createAt());
        jo.put("updateAt:", taskModel.updateAt());
        // We can as well create From a Map pass a JsonObject
        JSONArray ja = new JSONArray();
        ja.put(jo);
        String httpStr = HTTP.toString(jo);
        // Read chank of characther
        StringBuilder jsonData = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(path));){
            String line;
            while ((line = br.readLine()) != null) {
                jsonData.append(line);
            }
            JSONObject js = new JSONObject(jsonData.toString());
        }
        catch (IOException e){
            System.out.println("Error");
        }


    }


    /*
    public void writingTaskFile (TaskModel taskModel) {
        String path = "/home/robothg/Desktop/project_/project_Java/hello_java/welcome_java/src/main/java/tasktracker/storeFile/task.json";

        if(!readingTask()){
            try (FileWriter fileWriter = new FileWriter(path)) {
                String json = String.format(
                        "[{\"id\":\"%s\",\n" +
                                "\"description\":\"%s\",\n" +
                                "\"status\":\"%s\",\n" +
                                "\"createdAt\":\"%s\",\n " +
                                "\"updatedAt\":\"%s\"}]",
                        taskModel.id(), taskModel.description(), taskModel.status(), taskModel.createAt().toLocalDate(), taskModel.updateAt()
                );
                fileWriter.write(json);
            } catch (IOException e) {
                System.err.println("❌ Error writing to file: " + e.getMessage());
            }
        }
    }*/


    public void reading (){
        try (RandomAccessFile raf = new RandomAccessFile(path, "r")) {
            long size = raf.length();
            for (long i = 0; i < size; i++) {
                System.out.print((char) raf.readByte());
            }
        }
        catch (IOException e){
        }
    }




    public void writingOrAppend (TaskModel taskModel) {

            String json = String.format(
                    "[{\"id\":\"%s\",\n" +
                            "\"description\":\"%s\",\n" +
                            "\"status\":\"%s\",\n" +
                            "\"createdAt\":\"%s\",\n " +
                            "\"updatedAt\":\"%s\"}]",
                    taskModel.id(), taskModel.description(), taskModel.status(), taskModel.createAt().toLocalDate(), taskModel.updateAt()
            );
            try (RandomAccessFile raf = new RandomAccessFile(path, "rw")) {
                long length = raf.length();
                if (length == 0) {
                    // FILE IS EMPTY WRITE A FILE
                    raf.writeBytes("[\n" + json + "\n]");
                } else {
                    // ARRIVE AT THE END DIRECTLY
                    raf.seek(length - 1);
                    raf.writeBytes(",\n" + json + "\n]");
                }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    public static void main (String [] args) throws IOException {

        String txt = "We are the so-called"+"+\n+\"Vikings\" from the north.";
        System.out.println(txt);
        JsonCreation s = new JsonCreation();
        TaskModel taskModel = new TaskModel("1", "hello", "OK", LocalDateTime.now(), LocalDateTime.now());
        s.writingOrAppend(taskModel);
        s.reading();
    }
}
