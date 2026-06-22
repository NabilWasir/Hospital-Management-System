package Entity;

public abstract class Person{
    protected String name;
    protected int age;
    protected String gender;
    protected int id;

    public Person(String name, int age, String gender, int id){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public int getAge(){
        return age;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String getGender(){
        return gender;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }


    public abstract void showInfo();

}

