import java.io.*;
import java.util.*;

public class HealthRecordSystem {

    static final String FILE_NAME = "patients.dat";

    // Save data to file
    public static void savePatients(ArrayList<Patient> patients) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        oos.writeObject(patients);
        oos.close();
    }

    // Load data from file
    public static ArrayList<Patient> loadPatients() throws Exception {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
        ArrayList<Patient> list = (ArrayList<Patient>) ois.readObject();
        ois.close();
        return list;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Patient> patients;

        try {
            patients = loadPatients();
        } catch (Exception e) {
            patients = new ArrayList<>();
        }

        while (true) {
            System.out.println("\n===== Health Record System =====");
            System.out.println("1. Add Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Search Patient by ID");
            System.out.println("4. Update Patient");
            System.out.println("5. Delete Patient");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            try {
                switch (choice) {

                    case 1:
                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Gender: ");
                        String gender = sc.nextLine();

                        System.out.print("Enter Disease: ");
                        String disease = sc.nextLine();

                        System.out.print("Enter Medication: ");
                        String medication = sc.nextLine();

                        patients.add(new Patient(id, name, age, gender, disease, medication));
                        savePatients(patients);

                        System.out.println("✅ Patient Added Successfully!");
                        break;

                    case 2:
                        if (patients.isEmpty()) {
                            System.out.println("No records found.");
                        } else {
                            for (Patient p : patients) {
                                p.display();
                            }
                        }
                        break;

                    case 3:
                        System.out.print("Enter ID to search: ");
                        int searchId = sc.nextInt();
                        boolean found = false;

                        for (Patient p : patients) {
                            if (p.getId() == searchId) {
                                p.display();
                                found = true;
                            }
                        }

                        if (!found) {
                            System.out.println("❌ Patient not found.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter ID to update: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();
                        boolean updated = false;

                        for (Patient p : patients) {
                            if (p.getId() == updateId) {

                                System.out.print("Enter New Name: ");
                                String newName = sc.nextLine();

                                System.out.print("Enter New Age: ");
                                int newAge = sc.nextInt();
                                sc.nextLine();

                                System.out.print("Enter New Gender: ");
                                String newGender = sc.nextLine();

                                System.out.print("Enter New Disease: ");
                                String newDisease = sc.nextLine();

                                System.out.print("Enter New Medication: ");
                                String newMed = sc.nextLine();

                                p.update(newName, newAge, newGender, newDisease, newMed);
                                savePatients(patients);

                                System.out.println("✅ Patient Updated Successfully!");
                                updated = true;
                                break;
                            }
                        }

                        if (!updated) {
                            System.out.println("❌ Patient not found.");
                        }
                        break;

                    case 5:
                        System.out.print("Enter ID to delete: ");
                        int deleteId = sc.nextInt();

                        boolean removed = patients.removeIf(p -> p.getId() == deleteId);

                        if (removed) {
                            savePatients(patients);
                            System.out.println("✅ Patient Deleted Successfully!");
                        } else {
                            System.out.println("❌ Patient not found.");
                        }
                        break;

                    case 6:
                        System.out.println("Exiting program...");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } catch (Exception e) {
                System.out.println("⚠️ Error: " + e.getMessage());
            }
        }
    }
}