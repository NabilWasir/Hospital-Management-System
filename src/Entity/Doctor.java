package Entity;

public final class Doctor extends Person{
    private String specialization;

    public Doctor(String name, int age, String gender, int id, String specialization){
        super(name, age, gender, id);
        this.specialization = specialization;
    }

    public void setSpecialization(String specialization){
        this.specialization = specialization;
    }

    public String getSpecialization(){
        return specialization;
    }

    public void showInfo(){
        System.out.println("Doctor's Name : " + name);
        System.out.println("Doctor's Age" + age);
        System.out.println("Doctors's Gender : " + gender);
        System.out.println("Doctor's ID : " + id);
        System.out.println("Doctor's Specialization : " + specialization);
    }
}