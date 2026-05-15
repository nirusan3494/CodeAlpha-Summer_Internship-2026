import java.util.ArrayList;
import java.util.Scanner;

public class GradeTracker {

    Scanner sc = new Scanner(System.in);
    StudentDataAccessObject dao = new StudentDataAccessObject();

    void start() {

        int choice;

        do {

            System.out.println("\n===== STUDENT GRADE TRACKER =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search Student Details");
            System.out.println("4. Show Topper");
            System.out.println("5. Show Summary Report");
            System.out.println("6. Show Lowest Scorer");
            System.out.println("7. Logout");
            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // IMPORTANT FIX

            switch (choice) {

                case 1 -> addStudent();
                case 2 -> displayStudents();
                case 3 -> searchStudent();
                case 4 -> showTopper();
                case 5 -> dao.showSummaryReport();
                case 6 -> showLowest();
                case 7 -> System.out.println("Logging Out...");
            }

        } while (choice != 7);
    }

    // ADD STUDENT
    void addStudent() {

        System.out.println("Student ID will be auto-generated");

        System.out.print("Enter Roll Number: ");
        String roll = sc.nextLine();

        System.out.print("Enter First Name: ");
        String fn = sc.nextLine();

        System.out.print("Enter Last Name: ");
        String ln = sc.nextLine();

        System.out.print("Enter Gender (Male/Female): ");
        String gender = sc.nextLine();

        double m1, m2, m3;

        while (true) {
            System.out.print("Enter first Sub Marks: ");
            m1 = sc.nextDouble();
            if (m1 >= 0 && m1 <= 100) break;
            System.out.println("Invalid Marks");
        }

        while (true) {
            System.out.print("Enter second Sub Marks: ");
            m2 = sc.nextDouble();
            if (m2 >= 0 && m2 <= 100) break;
            System.out.println("Invalid Marks");
        }

        while (true) {
            System.out.print("Enter third Sub Marks: ");
            m3 = sc.nextDouble();
            if (m3 >= 0 && m3 <= 100) break;
            System.out.println("Invalid Marks");
        }

        sc.nextLine(); // IMPORTANT FIX (buffer clear)

        Student s = new Student(roll, fn, ln, gender, m1, m2, m3);
        dao.addStudent(s);
    }

    // DISPLAY
    void displayStudents() {

        ArrayList<Student> list = dao.getAllStudents();

        if (list.isEmpty()) {
            System.out.println("No Students Found!");
            return;
        }

        for (Student s : list) {
            s.display();
        }
    }

    // SEARCH
    void searchStudent() {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Student s = dao.searchStudent(id);

        if (s != null) s.display();
        else System.out.println("Student Not Found!");
    }

    // TOPPER
    void showTopper() {

        Student topper = dao.getTopper();

        if (topper != null) {
            System.out.println("\n===== TOPPER =====");
            topper.display();
        } else {
            System.out.println("No Students Found!");
        }
    }


    //LOWEST
    void showLowest() {

        Student lowest = dao.getLowest();

        if (lowest != null) {
            System.out.println("\n===== LOWEST SCORER =====");
            lowest.display();
        } else {
            System.out.println("No Students Found!");
        }
    }
}

