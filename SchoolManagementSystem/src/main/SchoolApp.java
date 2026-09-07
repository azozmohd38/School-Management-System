package main;

import entities.CourseRecord;
import entities.Enrollment;
import entities.HeadTeacher;
import entities.Person;
import entities.SeniorStudent;
import entities.Student;
import entities.Teacher;
import services.CourseService;
import services.EnrollmentService;
import services.StudentService;
import services.TeacherService;
import utils.InputHandler;

public class SchoolApp {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;
    private final InputHandler input;

    public SchoolApp() {
        studentService = new StudentService();
        teacherService = new TeacherService();
        courseService = new CourseService();
        enrollmentService = new EnrollmentService();
        input = new InputHandler();
    }

    public static void main(String[] args) {
        new SchoolApp().run();
    }

    public void run() {
        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = input.readInt("Choose: ", 1, 6);

            if (choice == 6) {
                running = false;
            }
        }

        System.out.println("School Management System closed.");
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("=== School Management System ===");
        System.out.println("1. Students");
        System.out.println("2. Teachers");
        System.out.println("3. Courses");
        System.out.println("4. Enrollments");
        System.out.println("5. Reports");
        System.out.println("6. Exit");
    }
}
