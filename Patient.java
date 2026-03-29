import java.io.Serializable;

public class Patient implements Serializable {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String disease;
    private String medication;

    // Constructor
    public Patient(int id, String name, int age, String gender, String disease, String medication) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.disease = disease;
        this.medication = medication;
    }

    // Getter
    public int getId() {
        return id;
    }

    // Display method
    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Disease: " + disease);
        System.out.println("Medication: " + medication);
        System.out.println("----------------------------");
    }

    // Update method
    public void update(String name, int age, String gender, String disease, String medication) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.disease = disease;
        this.medication = medication;
    }
}