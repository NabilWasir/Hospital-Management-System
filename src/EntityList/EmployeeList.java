package EntityList;
import Entity.Doctor;
import Entity.Employee;
public class EmployeeList{
    private Employee Employees[];

    public EmployeeList(){
        Employees = new Employee[50];
    }
    public EmployeeList(int size){
        Employees = new Employee[size];
    }

    //show all Employees
    public void showAllEmployees(){
        for(int i=0;i<Employees.length;i++){
            if(Employees[i] != null){
                Employees[i].showInfo();
            }
        }
    }

    //insert Employee into Array
    public void insertEmployee(Employee s){
        boolean flag = false;
        for(int i=0;i<Employees.length;i++){
            if(Employees[i] == null){
                Employees[i] = s;
                flag = true;
                break;
            }
        }
        if(flag){
            System.out.println("Employee added");
        }
        else{
            System.out.println("Employee List is Fulll ! Failed to add");
        }
    }

    public void removeEmployeeById(double id){
        boolean flag = false;
        for(int i=0;i<Employees.length;i++){
            if(Employees[i] != null){
                if(Employees[i].getId() == id){
                    Employees[i] = null;
                    flag = true;
                    break;
                }
            }
        }
        if(flag){
            System.out.println("Employee Deleted Successfully");
        }
        else{
            System.out.println("Employee is not Found");
        }
    }

    public Employee[] getAllEmployee(){return Employees;}
    public void setAllEmployees(Employee[] Employees ){
        this.Employees = Employees;
    }
}