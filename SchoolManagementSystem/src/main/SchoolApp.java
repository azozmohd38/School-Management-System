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

            switch (choice) {
                case 1:
                    handleStudents();
                    break;
                case 2:
                    handleTeachers();
                    break;
                case 3:
                    handleCourses();
                    break;
                case 4:
                    handleEnrollments();
                    break;
                case 5:
                    handleReports();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("School Management System closed.");
    }

    public void printAll(Person[] people) {
        if (people == null) {
            return;
        }

        for (Person person : people) {
            if (person != null) {
                person.displayInfo();
            }
        }
    }

    public int[] countByType(Person[] people) {
        int seniorStudents = 0;
        int students = 0;
        int headTeachers = 0;
        int teachers = 0;

        if (people != null) {
            for (Person person : people) {
                if (person instanceof SeniorStudent) {
                    seniorStudents++;
                } else if (person instanceof Student) {
                    students++;
                } else if (person instanceof HeadTeacher) {
                    headTeachers++;
                } else if (person instanceof Teacher) {
                    teachers++;
                }
            }
        }

        return new int[]{students, seniorStudents, teachers, headTeachers};
    }

    public Person findOldest(Person[] people) {
        Person oldest = null;

        if (people != null) {
            for (Person person : people) {
                if (person != null && (oldest == null || person.getAge() > oldest.getAge())) {
                    oldest = person;
                }
            }
        }

        return oldest;
    }

    private void handleStudents() {
        System.out.println("Student menu");
    }

    private void handleTeachers() {
        System.out.println("Teacher menu");
    }

    private void handleCourses() {
        System.out.println("Course menu");
    }

    private void handleEnrollments() {
        System.out.println("Enrollment menu");
    }

    private void handleReports() {
        System.out.println("Reports menu");
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
