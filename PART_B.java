//Serialization and Deserialization of a Student Object
import java.io.*;
public class PART_B{
    public static void main(String[] args) {
        String fileName = "student.ser";
        Student st = new Student(1, "aadi");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(st);
            System.out.println("Object Serialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Student ds = (Student) ois.readObject();
            System.out.println("Object Deserialized");
            ds.display();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Student implements Serializable {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    void display() {
        System.out.println("id: " + id + " name: " + name);
    }
}
