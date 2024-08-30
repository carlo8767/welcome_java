package stream;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


public class StudentComputationalStream {


    public List<Student> filterByGender (List<Student> studentList) {
        Optional<List<Student>> optionalStudents = Optional.ofNullable(studentList);
        List<Student> listGender;
        // VERIFY THAT THE OBJECT IS NOT NULL
        if(optionalStudents.isEmpty()){
            throw new NullPointerException();
        }
        // VERIFY THAT THE VALUE IS NOT EMPTY
        if (optionalStudents.get().isEmpty()) {
            throw new NullPointerException();
        } else {
            listGender = optionalStudents.get().stream()
                    .filter(x -> x.getGender().equals("Female"))
                    .toList();
            return listGender;
        }

    }

    public List<Student> filterByName(List<Student> studentList, String name) {
        Optional<List<Student>> optionalStudents = Optional.ofNullable(studentList);
        List<Student> listName;
        if(optionalStudents.isEmpty()){
            throw new NullPointerException();
        }
        if (optionalStudents.get().isEmpty()) {
            throw new NullPointerException();
        }
        else{
            String filterName= name;
            // USE DROP WHILE ( WHEN THERE IS A MATCH
            // DO NOT SEARCH FOR OTHER MATCHES
            listName = optionalStudents.get().stream()
                    .filter(x-> x.getName().equals(filterName))
                    .collect(Collectors.toList());
        }
        return  listName;
    }

    public List<Student> filterMaxAge(List<Student> studentList, int thresholdAge) {
        Optional<List<Student>> optionalStudents = Optional.ofNullable(studentList);
        List<Student> listName;
        if(optionalStudents.isEmpty()){
            throw new NullPointerException();
        }
        if (optionalStudents.get().isEmpty()) {
            throw new NullPointerException();
        }
        else{
            // USE TAKEWHILE WHILE ( WHEN THERE IS A MATCH
            // DO NOT SEARCH FOR OTHER MATCHES
            // REMEMBER THAT THE LIST MUST BE SORTED
            listName = optionalStudents.get().stream()
                    .takeWhile(x-> x.getAge() < thresholdAge)
                    .collect(Collectors.toList());
        }
        return  listName;
    }

    public long countAge (List<Student> list){
        // WITH MAP I CREATE A NEW OBJECT
        long value = list.stream().map(Student::getAge)
                .filter(x-> x> 18)
                .count();
        return  value;

    }
    public Integer SumAge (List<Student> list){
        Optional<List<Student>>optionalStudentsList = Optional.of(list);
             return  optionalStudentsList.get().stream()
                     .filter(Objects::nonNull)
                     .map(Student::getAge)
                     .reduce(0,Integer::sum);
    }





}

