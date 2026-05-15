public class Student {

    private int studentId;
    private String rollNumber;
    private String firstName;
    private String lastName;
    private String gender;

    private double marks1;
    private double marks2;
    private double marks3;

    private double total;
    private double average;

    private String grade;
    private String passStatus;

    // FOR INSERT (NO ID - USER INPUT)
    public Student(String rollNumber,
                   String firstName,
                   String lastName,
                   String gender,
                   double marks1,
                   double marks2,
                   double marks3) {

        this.rollNumber = rollNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;

        this.marks1 = marks1;
        this.marks2 = marks2;
        this.marks3 = marks3;

        calculate();
    }

    // FOR DB FETCH (WITH ID)
    public Student(int studentId,
                   String rollNumber,
                   String firstName,
                   String lastName,
                   String gender,
                   double marks1,
                   double marks2,
                   double marks3) {

        this(rollNumber, firstName, lastName, gender, marks1, marks2, marks3);
        this.studentId = studentId;
    }

    private void calculate() {

        total = marks1 + marks2 + marks3;
        average = Math.round((total / 3) * 100.0) / 100.0;

        if (average >= 90) grade = "A+";
        else if (average >= 80) grade = "A";
        else if (average >= 70) grade = "B";
        else if (average >= 60) grade = "C";
        else if (average >= 50) grade = "D";
        else grade = "F";

        passStatus = (average >= 50) ? "PASS" : "FAIL";
    }

    public void display() {

        System.out.println("\n----- STUDENT DETAILS -----");
        System.out.println("ID       : " + studentId);
        System.out.println("Roll No  : " + rollNumber);
        System.out.println("Name     : " + firstName + " " + lastName);
        System.out.println("Gender   : " + gender);
        System.out.println("Marks    : " + marks1 + ", " + marks2 + ", " + marks3);
        System.out.println("Total    : " + total);
        System.out.println("Average  : " + average);
        System.out.println("Grade    : " + grade);
        System.out.println("Status   : " + passStatus);
    }

    // GETTERS
    public int getStudentId() { return studentId; }
    public String getRollNumber() { return rollNumber; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getGender() { return gender; }
    public double getMarks1() { return marks1; }
    public double getMarks2() { return marks2; }
    public double getMarks3() { return marks3; }
    public double getTotal() { return total; }
    public double getAverage() { return average; }
    public String getGrade() { return grade; }
    public String getPassStatus() { return passStatus; }
}