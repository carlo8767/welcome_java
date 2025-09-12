package university;

public class BookExtends extends Book{
    public BookExtends(int size) {
        super(23);
        this.size = size;
    }





    public int getGetSize(){
        return  super.getSize();
    }

    public void printBokkName(String name){
        System.out.println(name);
    }

    public static void main(String [] args){


        BookExtends s = new BookExtends(23);
        s.printBokkName("");


         Book sff = new Book(3);
         boolean nn = s.equals(sff);
         boolean p = sff.equals(s);
         int n = s.getSize();


    }
}
