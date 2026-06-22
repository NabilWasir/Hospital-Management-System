package Entity;

public final class Patient extends Person{
    private String disease;

    public Patient(String name, int age, String gender, int id,
                   String disease){
        super(name, age, gender, id);
        this.disease = disease;
    }


    public void setDisease(String disease){
        this.disease = disease;
    }


    public String getDisease(){
        return disease;
    }


    public void showInfo(){
        System.out.println("Patient's Name : " + name);
        System.out.println("Patient's Age : " + age);
        System.out.println("Patient's Gender : " + gender);
        System.out.println("Patient's ID : " + id);
        System.out.println("Patient's Disease : " + disease);
    }

}