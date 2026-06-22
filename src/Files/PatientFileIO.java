package Files;
import java.io.*;
import java.util.Scanner;
import Entity.*;
import EntityList.PatientList;

public class PatientFileIO{

    public static void readFromFile(String fname, PatientList patientList){
        try{
            File file = new File(fname);
            if (!file.exists()) {
                System.out.println("File does not exist. Creating new file: " + fname);
                file.createNewFile();
                return;
            }

            Scanner scanner = new Scanner(file);
            int lineCount = 0;
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
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
                String disease = data[4];

                Entity.Patient patient = new Entity.Patient(name, age, gender, id, disease);
                patientList.insertPatient(patient);
            }
            scanner.close();
            System.out.println("Loaded " + lineCount + " patients from file");
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

    public static void savePatientListInFile(PatientList patientList, String fname, boolean append){
        try {
            // Used false for overwrite to ensure clean save
            FileWriter writer = new FileWriter(fname, false);
            Patient patients[] = patientList.getAllPatient();
            int savedCount = 0;

            for(int i = 0; i < patients.length; i++){
                if(patients[i] != null){
                    String line = patients[i].getName() + ";" +
                            patients[i].getAge() + ";" +
                            patients[i].getGender() + ";" +
                            patients[i].getId() + ";" +
                            patients[i].getDisease();

                    writer.write(line + "\n");
                    savedCount++;
                }
            }

            writer.close();
            System.out.println("Saved " + savedCount + " patients to file");
        } catch (IOException e) {
            System.out.println("Cannot Write in File: " + e.getMessage());
            e.printStackTrace();
        }
    }
}