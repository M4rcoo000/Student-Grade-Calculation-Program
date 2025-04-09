
import java.util.ArrayList;
import java.util.Scanner;

class Student { //Created a new class for Students
	 
    private String name;
    private int studentID;
    private ArrayList<String> enrolledCourses;

    public Student(String name, int studentID) {  //Created the constructor for name and studentID
        this.name = name;
        this.studentID = studentID;
        this.enrolledCourses = new ArrayList<>(); //Start the list of courses
    }

    public void enrollCourse() { //Function to enroll a student into a course
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter the name of the course you want to enroll in:");
        String courseName = obj.nextLine(); //Create a variable with the name of the course that the user typed
        enrolledCourses.add(courseName); //Add the name of the course to the Array list
        System.out.println(name+ " is now enrolled in " + courseName);
        
        obj.close();
    }

    //Getters for enrolledCourses, name, and studentID
    public ArrayList<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public String getName() {
        return name;
    }

    public int getStudentID() {
        return studentID;
    }
}



class Course { //Created a class for Courses

    private String courseName;
    private String courseCode;
    private ArrayList<Student> studentsEnrolled;
    private ArrayList<Double> studentsGrades;

    public Course(String courseName, String courseCode) { //Constructor for courseName and courseCode
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.studentsEnrolled = new ArrayList<>(); //Start list of students enrolled
        this.studentsGrades = new ArrayList<>(); //Start list of students grades
    }

    public void addStudent(Student student) { //Function to add a new student to a course
        studentsEnrolled.add(student); //Add the student to the list of enrolled students in a course
        studentsGrades.add(null);  //Add null so that it doesn't add anything until it is assigned
        System.out.println(student.getName() + " is now added to the " +courseName+ " course ");
    }

    public void addGrade(Student student, double grade) { //Function to add a grade to a student in a course
        int studentIndex = studentsEnrolled.indexOf(student); //Variable to get the index of each student enrolled
        if (studentIndex >= 0) {
            studentsGrades.set(studentIndex, grade);  // Set grade at the correct index 
            System.out.println("A " + grade + " has been added for " + student.getName()+ "\'s grade");
        }
    }
    	//Getters for studentsEnrolled, courseName, courseCode, and studentsGrades
    public ArrayList<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public ArrayList<Double> getStudentsGrades() {
        return studentsGrades;
    }
}



class Grade { //Created a class for grades

    private int studentID;
    private String courseCode;
    private Double gradeValue;

    public Grade(int studentID, String courseCode, double gradeValue) { //Constructor for this variables:
        this.studentID = studentID;
        this.courseCode = courseCode;
        this.gradeValue = gradeValue;
    }

    	//Getters for studentID, courseCode, and gradeValue
    public int getStudentID() {
        return studentID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public Double getGradeValue() {
        return gradeValue;
    }
}


class Main { //Created the main class that will run in the console
	public static void main(String[] args) {
	    Scanner obj = new Scanner(System.in);

	    // Create lists for students and courses
	    ArrayList<Student> students = new ArrayList<>();
	    ArrayList<Course> courses = new ArrayList<>();

	    // Input the number of students to add
	    System.out.print("How many students do you want to add? ");
	    int studentNumber = obj.nextInt(); //Store that integer as a variable
	    obj.nextLine(); // Consume newline

	    //Loop to add students
	    for (int j = 0; j < studentNumber; j++) { //looping is form 0 to the variable stored right before
	        System.out.println("\nEnter details for Student " + (j + 1) + ":"); //(j +1) because the index starts form 0
	        System.out.print("Enter student name: ");
	        String studentName = obj.nextLine();
	        System.out.print("Enter student ID: ");
	        int studentID = obj.nextInt();
	        obj.nextLine();

	        Student student = new Student(studentName, studentID); //add that information as a student
	        students.add(student);

	        // Input number of courses for that student
	        System.out.print("Enter number of courses for " + studentName + ": "); 
	        int numCourses = obj.nextInt(); //number of courses stored as a variable
	        obj.nextLine();

	        for (int i = 0; i < numCourses; i++) { //loop from 0 to the number entered right before
	            System.out.print("Enter course name: ");
	            String courseName = obj.nextLine();
	            System.out.print("Enter course code: ");
	            String courseCode = obj.nextLine();

	            //Check if the course already exists (this is in case more than 1 student is enrolled to the same course
	            Course course = null;
	            for (Course c : courses) {
	                if (c.getCourseCode().equals(courseCode)) {
	                    course = c;
	                    break;
	                }
	            }

	            //If the course doesn't exist, create it
	            if (course == null) {
	                course = new Course(courseName, courseCode);
	                courses.add(course);
	            }

	            //Add student to the course
	            course.addStudent(student);
	        }
	    }

	    //Assign grades to each student in their respective courses
	    for (Student student : students) {
	        System.out.println("\nAssigning grades for " + student.getName() + ":");
	        for (Course course : courses) { //Loop through all the courses added
	            if (course.getStudentsEnrolled().contains(student)) { //If there is a student in that course, ask to add grade
	                System.out.print("Enter grade for " + student.getName() + " in " + course.getCourseName() + ": ");
	                double grade = obj.nextDouble();
	                course.addGrade(student, grade);
	            }
	        }
	    }

	 //Show all the students and their grades in each course
	    for (Course course : courses) {
	        System.out.println("\nCourse: " + course.getCourseName());

	        ArrayList<Student> enrolledStudents = course.getStudentsEnrolled(); //Get both array lists
	        ArrayList<Double> gradesList = course.getStudentsGrades();

	        float total_grade = 0.0f;
	        int gradedStudents = 0; // To avoid dividing by zero if there are no no grades

	        for (int i = 0; i < enrolledStudents.size(); i++) { //Loop to go through every student
	            Student enrolledStudent = enrolledStudents.get(i);
	            Double grade = gradesList.get(i);

	            //Print student its grade
	            System.out.println("Student: " + enrolledStudent.getName() + ", Grade: " + grade);

	            //Add to total grade if the grade is not null
	            if (grade != null) {
	                total_grade += grade;
	                gradedStudents++;
	            }
	        }

	        //Calculate and print the average grade for the course
	        if (gradedStudents > 0) {
	            System.out.println("Average Grade for " + course.getCourseName() + ": " + (total_grade / gradedStudents));
	        } else {
	            System.out.println("No grades available for " + course.getCourseName());
	        }
	    }

	    obj.close();
	}
}




