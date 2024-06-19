package inheritance;

public abstract class Animal {

    private String name;
    private String size;

    public Animal(String name, String size){
        this.name=name;
        this.size=size;
    }

    protected abstract String soundAnimal();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
