package functionalInterface;

import org.junit.Test;
import stream.Student;
import stream.StudentComputationalStream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestStreamComputationalStream {

    @Test
    public void testFilterGender(){
        StudentComputationalStream studentComputationalStream = new StudentComputationalStream();
        List<Student> listStudent = new ArrayList<>();
        listStudent.add(new Student("Angela","Italian","Female",19));
        listStudent.add(new Student("Mark","Belgium","Male",21));
        listStudent.add(new Student("ChenYu","Chinese","Female",22));
        listStudent.add(new Student("Jorg","German", "Male",33));
        List<Student> listGender = studentComputationalStream.filterByGender(listStudent);
        assertEquals(2, listGender.size());
    }

    @Test
    public void testFilterGenderNull(){
        boolean verification = false;
        try{
            StudentComputationalStream studentComputationalStream = new StudentComputationalStream();
            List<Student> listStudent = new ArrayList<>();
            List<Student> listGender = studentComputationalStream.filterByGender(listStudent);
        }
        catch (NullPointerException n) {
            verification = true;
        }
        assertTrue(verification);
    }

    @Test
    public void testFilterByName(){
        StudentComputationalStream studentComputationalStream = new StudentComputationalStream();
        List<Student> listStudent = new ArrayList<>();
        listStudent.add(new Student("Angela","Italian","Female",19));
        listStudent.add(new Student("Mark","Belgium","Male",21));
        listStudent.add(new Student("ChenYu","Chinese","Female",22));
        listStudent.add(new Student("Jorg","German", "Male",33));
        List<Student> listGender = studentComputationalStream.filterByName(listStudent, "Mark");
        assertEquals("Mark", listGender.getFirst().getName());
    }

    @Test
    public void testFilterMaxAge(){
        StudentComputationalStream studentComputationalStream = new StudentComputationalStream();
        List<Student> listStudent = new ArrayList<>();
        listStudent.add(new Student("Angela","Italian","Female",19));
        listStudent.add(new Student("Mark","Belgium","Male",21));
        listStudent.add(new Student("ChenYu","Chinese","Female",22));
        listStudent.add(new Student("Jorg","German", "Male",33));
        listStudent.add(new Student("Josephine","France", "Female",18));
        // SORT THE LIST WITH COMPARATOR
        listStudent.sort(Comparator.comparing(Student::getAge));
        List<Student> listGender = studentComputationalStream.filterMaxAge(listStudent, 25);
        assertEquals(4, listGender.size());
    }


    @Test
    public void testSumAge(){

        StudentComputationalStream studentComputationalStream = new StudentComputationalStream();
        List<Student> listStudent = new ArrayList<>();
        listStudent.add(new Student("Angela","Italian","Female",19));
        listStudent.add(new Student("Mark","Belgium","Male",19));
        listStudent.add(new Student("ChenYu","Chinese","Female",19));
        listStudent.add(new Student("Jorg","German", "Male",19));
        listStudent.add(new Student("Josephine","France", "Female",19));
        listStudent.add(null);
        Integer sumAge = studentComputationalStream.SumAge(listStudent);
        assertEquals(95, sumAge);

    }



}
