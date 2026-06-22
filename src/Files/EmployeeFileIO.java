package Files;
import java.io.*;
import java.util.Scanner;
import Entity.*;
import EntityList.EmployeeList;

public class EmployeeFileIO{

    public static void readFromFile(String fname, EmployeeList EmployeeList){
        try{
            File file = new File(fname);
            if (!file.exists()) {
                System.out.println("File does not exist. Creating new file: " + fname);
                file.createNewFile();
                return;
            }

            Scanner sc = new Scanner(file);
            int lineCount = 0;
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                lineCount++;
                if (line.trim().isEmpty()) {
                    continue;
                }

                String data[] = line.split(";");

                if (data.length != 5) {
                    System.out.println("Skipping invalid line " + lineCount + ": " + line);
                    continue;
                }

                String name = data[0];
                int age = Integer.parseInt(data[1]);
                String gender = data[2];
                int id = Integer.parseInt(data[3]);
                String role = data[4];

                Entity.Employee s = new Entity.Employee(name, age, gender, id, role);
                EmployeeList.insertEmployee(s);
            }
            sc.close();
            System.out.println("Loaded " + lineCount + " employees from file");
        }
        catch(Exception e){
            System.out.println("Cannot Read the File: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void writeInFile(String line, String fname, boolean append){
        try {
            FileWriter writer = new FileWriter(fname, append);
            writer.write(line + "\n");
            writer.close();
            System.out.println("Successfully wrote to file: " + line);
        } catch (IOException e) {
            System.out.println("Cannot Write in File: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void saveEmployeeListInFile(EmployeeList EmployeeList, String fname, boolean append){
        try {
            FileWriter writer = new FileWriter(fname, false);
            Employee Employees[] = EmployeeList.getAllEmployee();
            int savedCount = 0;

            for(int i = 0; i < Employees.length; i++){
                if(Employees[i] != null){
                    String line = Employees[i].getName() + ";" +
                            Employees[i].getAge() + ";" +
                            Employees[i].getGender() + ";" +
                            Employees[i].getId() + ";" +
                            Employees[i].getRole();

                    writer.write(line + "\n");
                    savedCount++;
                }
            }

            writer.close();
            System.out.println("Saved " + savedCount + " employees to file");
        } catch (IOException e) {
            System.out.println("Cannot Write in File: " + e.getMessage());
            e.printStackTrace();
        }
    }
}