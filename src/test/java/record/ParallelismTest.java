package record;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ParallelismTest {


    @Test
    public void printRecord (){
        // RECORD CLASS EXIST ONLY TO CARRY DATA
        Parallelism parallelism = new Parallelism(1, "First");
        Parallelism secondParallelism = new Parallelism(2, "Second");
        ArrayList<Parallelism> listParallelism = new ArrayList<Parallelism>();
        listParallelism.add(parallelism);
        listParallelism.add(secondParallelism);
        listParallelism.forEach(x-> System.out.println(x.count()));
        listParallelism.forEach(x-> System.out.println(x.hashCode()));
        listParallelism.forEach(x-> System.out.println(x.toString()));
    }


}