import java.sql.*;
import java.util.ArrayList;

public class StudentDataAccessObject {

    // INSERT STUDENT
    void addStudent(Student s) {

        String query =
                "INSERT INTO students " +
                        "(roll_number, first_name, last_name, gender, marks1, marks2, marks3, total, average, grade, pass_status) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseCon.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, s.getRollNumber());
            ps.setString(2, s.getFirstName());
            ps.setString(3, s.getLastName());
            ps.setString(4, s.getGender());

            ps.setDouble(5, s.getMarks1());
            ps.setDouble(6, s.getMarks2());
            ps.setDouble(7, s.getMarks3());

            ps.setDouble(8, s.getTotal());
            ps.setDouble(9, s.getAverage());
            ps.setString(10, s.getGrade());
            ps.setString(11, s.getPassStatus());

            ps.executeUpdate();

            System.out.println("Student Inserted Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // FETCH ALL STUDENTS
    ArrayList<Student> getAllStudents() {

        ArrayList<Student> list = new ArrayList<>();

        String query = "SELECT * FROM students";

        try (Connection con = DatabaseCon.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {

                Student s = new Student(
                        rs.getInt("student_id"),
                        rs.getString("roll_number"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("gender"),
                        rs.getDouble("marks1"),
                        rs.getDouble("marks2"),
                        rs.getDouble("marks3")
                );

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // SEARCH STUDENT
    Student searchStudent(int id) {

        String query = "SELECT * FROM students WHERE student_id=?";

        try (Connection con = DatabaseCon.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Student(
                        rs.getInt("student_id"),
                        rs.getString("roll_number"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("gender"),
                        rs.getDouble("marks1"),
                        rs.getDouble("marks2"),
                        rs.getDouble("marks3")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // TOPPER
    Student getTopper() {

        ArrayList<Student> list = getAllStudents();

        if (list.isEmpty()) return null;

        Student topper = list.get(0);

        for (Student s : list) {
            if (s.getAverage() > topper.getAverage()) {
                topper = s;
            }
        }

        return topper;
    }

    //LOWEST
    Student getLowest() {

        ArrayList<Student> list = getAllStudents();

        if (list.isEmpty())
            return null;

        Student lowest = list.get(0);

        for (Student s : list) {

            if (s.getAverage() < lowest.getAverage()) {
                lowest = s;
            }
        }

        return lowest;
    }

    //SUMMARY
    public void showSummaryReport() {

        ArrayList<Student> list = getAllStudents();

        if (list.isEmpty()) {
            System.out.println("No Students Found!");
            return;
        }

        int totalStudents = list.size();

        double totalSum = 0;
        double highest = list.get(0).getAverage();
        double lowest = list.get(0).getAverage();

        for (Student s : list) {

            double avg = s.getAverage();
            totalSum += avg;

            if (avg > highest) highest = avg;
            if (avg < lowest) lowest = avg;
        }

        double classAverage = totalSum / totalStudents;

        System.out.println("\n===== CLASS SUMMARY REPORT =====");
        System.out.println("Total Students   : " + totalStudents);
        System.out.println("Highest Average  : " + highest);
        System.out.println("Lowest Average   : " + lowest);
        System.out.println("Class Average    : " + String.format("%.2f", classAverage));
    }
}