package stream;


 public class Student  {

  private String name;
  private String nationality;
  private String gender;
  private int age;


  public Student(String name, String nationality,String gender, int age) {
   this.name = name;
   this.nationality = nationality;
   this.gender = gender;
   this.age = age;

  }


  public String getName() {
   return name;
  }

  public void setName(String name) {
   this.name = name;
  }

  public int getAge() {
   return age;
  }

  public void setAge(int age) {
   this.age = age;
  }

  public String getGender() {
   return gender;
  }

  public void setGender(String gender) {
   this.gender = gender;
  }

  public String getNationality() {
   return nationality;
  }

  public void setNationality(String nationality) {
   this.nationality = nationality;
  }
 }

