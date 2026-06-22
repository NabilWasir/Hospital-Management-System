package Entity;

public final class Employee extends Person{
    private String role;

    public Employee(String name, int age, String gender, int id, String role){
        super(name, age, gender, id);
        this.role = role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

    public void showInfo(){
        System.out.println("Employee's Name : " + name);
        System.out.println("Employee's Age : " + age);
        System.out.println("Employee's Gender : " + gender);
        System.out.println("Employee's ID : " + id);
        System.out.println("Employee's Role : " + role);
    }
}