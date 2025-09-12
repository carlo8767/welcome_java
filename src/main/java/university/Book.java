package university;

public class Book {

    public int size;


    public Book(int size){
        this.size = size;
    }

    public int getSize() {
      return  this.size;
    }


    public boolean equals (Object object){

        if(this == object){
            return true;
        }

        else{
            return  false;
        }

    }


}
