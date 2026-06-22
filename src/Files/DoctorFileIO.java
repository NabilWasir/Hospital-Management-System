package Files;
import java.io.*;
import java.util.Scanner;
import Entity.*;
import EntityList.DoctorList;

public class DoctorFileIO{

    public static void readFromFile(String fname, DoctorList doctorList){
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
                String specialization = data[4];

                Entity.Doctor d = new Entity.Doctor(name, age, gender, id, specialization);
                doctorList.insertDoctor(d);
            }
            sc.close();
            System.out.println("Loaded " + lineCount + " doctors from file");
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

    public static void saveDoctorListInFile(DoctorList doctorList, String fname, boolean append){
        try {
            FileWriter writer = new FileWriter(fname, false);
            Doctor doctors[] = doctorList.getAllDoctor();
            int savedCount = 0;

            for(int i = 0; i < doctors.length; i++){
                if(doctors[i] != null){
                    String line = doctors[i].getName() + ";" +
                            doctors[i].getAge() + ";" +
                            doctors[i].getGender() + ";" +
                            doctors[i].getId() + ";" +
                            doctors[i].getSpecialization();

                    writer.write(line + "\n");
                    savedCount++;
                }
            }

            writer.close();
            System.out.println("Saved " + savedCount + " doctors to file");
        } catch (IOException e) {
            System.out.println("Cannot Write in File: " + e.getMessage());
            e.printStackTrace();
        }
    }
}