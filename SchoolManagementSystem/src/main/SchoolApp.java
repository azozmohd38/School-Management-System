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
        seedSampleData();
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
        System.out.println();
        System.out.println("1. Add basic student");
        System.out.println("2. View all students");
        System.out.println("3. Search student");
        System.out.println("4. Update contact");
        System.out.println("5. Remove student");
        System.out.println("6. List senior students");
        System.out.println("7. Back");

        int choice = input.readInt("Choose: ", 1, 7);

        if (choice == 1) {
            String id = input.readText("ID: ");
            String firstName = input.readText("First name: ");
            String lastName = input.readText("Last name: ");
            int grade = input.readInt("Grade level: ", 1, 12);
            studentService.addStudent(id, firstName, lastName, grade);
        } else if (choice == 2) {
            Object[] students = studentService.getAll();
            for (Object student : students) {
                ((Student) student).displayInfo();
            }
        } else if (choice == 3) {
            String keyword = input.readText("Keyword: ");
            Object[] results = studentService.search(keyword);
            for (Object result : results) {
                ((Student) result).displaySummary();
            }
        } else if (choice == 4) {
            String id = input.readText("Student ID: ");
            String phone = input.readText("Phone: ");
            if (input.readConfirmation("Update email too? (yes/no): ")) {
                String email = input.readText("Email: ");
                studentService.updateContact(id, phone, email);
            } else {
                studentService.updateContact(id, phone);
            }
        } else if (choice == 5) {
            studentService.removeById(input.readText("Student ID: "));
        } else if (choice == 6) {
            Student[] seniors = studentService.listSeniors();
            for (Student senior : seniors) {
                senior.displaySummary();
            }
        }
    }

    private void handleTeachers() {
        System.out.println();
        System.out.println("1. View all teachers");
        System.out.println("2. Search teachers");
        System.out.println("3. Assign class");
        System.out.println("4. List by subject");
        System.out.println("5. Find available teachers");
        System.out.println("6. Remove teacher");
        System.out.println("7. Back");

        int choice = input.readInt("Choose: ", 1, 7);

        if (choice == 1) {
            Object[] teachers = teacherService.getAll();
            for (Object teacher : teachers) {
                ((Teacher) teacher).displayInfo();
            }
        } else if (choice == 2) {
            Object[] results = teacherService.search(input.readText("Keyword: "));
            for (Object result : results) {
                ((Teacher) result).displaySummary();
            }
        } else if (choice == 3) {
            String teacherId = input.readText("Teacher ID: ");
            String classId = input.readText("Class ID: ");
            teacherService.assignClass(teacherId, classId);
        } else if (choice == 4) {
            Teacher[] teachers = teacherService.listBySubject(input.readText("Subject: "));
            for (Teacher teacher : teachers) {
                teacher.displaySummary();
            }
        } else if (choice == 5) {
            Teacher[] teachers = teacherService.availableTeachers(input.readText("Time slot: "));
            for (Teacher teacher : teachers) {
                teacher.displaySummary();
            }
        } else if (choice == 6) {
            teacherService.removeById(input.readText("Teacher ID: "));
        }
    }

    private void handleCourses() {
        System.out.println();
        System.out.println("1. View all course records");
        System.out.println("2. Search course records");
        System.out.println("3. List by term");
        System.out.println("4. Count finalized");
        System.out.println("5. Remove course record");
        System.out.println("6. Back");

        int choice = input.readInt("Choose: ", 1, 6);

        if (choice == 1) {
            Object[] records = courseService.getAll();
            for (Object record : records) {
                ((CourseRecord) record).displayInfo();
            }
        } else if (choice == 2) {
            Object[] results = courseService.search(input.readText("Keyword: "));
            for (Object result : results) {
                ((CourseRecord) result).displaySummary();
            }
        } else if (choice == 3) {
            CourseRecord[] records = courseService.listByTerm(input.readText("Term: "));
            for (CourseRecord record : records) {
                record.displaySummary();
            }
        } else if (choice == 4) {
            System.out.println("Finalized records: " + courseService.countFinalized());
        } else if (choice == 5) {
            courseService.removeById(input.readText("Record ID: "));
        }
    }

    private void handleEnrollments() {
        System.out.println();
        System.out.println("1. Enroll student");
        System.out.println("2. View all enrollments");
        System.out.println("3. Cancel enrollment");
        System.out.println("4. Complete enrollment");
        System.out.println("5. Transfer enrollment");
        System.out.println("6. List by status");
        System.out.println("7. List by student");
        System.out.println("8. Back");

        int choice = input.readInt("Choose: ", 1, 8);

        if (choice == 1) {
            String studentId = input.readText("Student ID: ");
            String courseId = input.readText("Course ID: ");
            String date = input.readText("Enroll date: ");
            enrollmentService.enroll(studentId, courseId, date);
        } else if (choice == 2) {
            Object[] enrollments = enrollmentService.getAll();
            for (Object enrollment : enrollments) {
                ((Enrollment) enrollment).displayInfo();
            }
        } else if (choice == 3) {
            enrollmentService.cancel(input.readText("Enrollment ID: "));
        } else if (choice == 4) {
            enrollmentService.complete(input.readText("Enrollment ID: "));
        } else if (choice == 5) {
            String enrollmentId = input.readText("Enrollment ID: ");
            String newCourseId = input.readText("New course ID: ");
            String newDate = input.readText("New date: ");
            enrollmentService.transfer(enrollmentId, newCourseId, newDate);
        } else if (choice == 6) {
            Enrollment[] enrollments = enrollmentService.listByStatus(input.readText("Status: "));
            for (Enrollment enrollment : enrollments) {
                enrollment.displaySummary();
            }
        } else if (choice == 7) {
            Enrollment[] enrollments = enrollmentService.listByStudent(input.readText("Student ID: "));
            for (Enrollment enrollment : enrollments) {
                enrollment.displaySummary();
            }
        }
    }

    private void handleReports() {
        Person[] people = buildPeopleArray();
        int[] counts = countByType(people);

        System.out.println("Regular students: " + counts[0]);
        System.out.println("Senior students: " + counts[1]);
        System.out.println("Teachers: " + counts[2]);
        System.out.println("Head teachers: " + counts[3]);
        System.out.println("Outstanding fees: " + studentService.totalOutstanding());
        System.out.println("Average grade: " + courseService.averageGrade());

        Person oldest = findOldest(people);
        if (oldest != null) {
            System.out.println("Oldest person:");
            oldest.displaySummary();
        }
    }

    private Person[] buildPeopleArray() {
        Object[] studentObjects = studentService.getAll();
        Object[] teacherObjects = teacherService.getAll();
        Person[] people = new Person[studentObjects.length + teacherObjects.length];
        int index = 0;

        for (Object student : studentObjects) {
            people[index++] = (Student) student;
        }

        for (Object teacher : teacherObjects) {
            people[index++] = (Teacher) teacher;
        }

        return people;
    }

    private void seedSampleData() {
        studentService.addStudent(new Student("S1", "Ali", "Salim", "2009-01-10", "Male",
                "90000001", "ali@school.com", "Muscat", "N1001", 17, true,
                11, "2025-09-01", 120.0, false));

        studentService.addStudent(new Student("S2", "Sara", "Ahmed", "2008-02-12", "Female",
                "90000002", "sara@school.com", "Muscat", "N1002", 18, true,
                12, "2024-09-01", 0.0, true));

        studentService.addStudent(new Student("S3", "Maha", "Khalid", "2009-03-15", "Female",
                "90000003", "maha@school.com", "Sohar", "N1003", 17, true,
                11, "2025-09-01", 80.0, false));

        studentService.addStudent(new SeniorStudent("S4", "Omar", "Said", "2006-04-20", "Male",
                "90000004", "omar@school.com", "Nizwa", "N1004", 20, true,
                12, "2022-09-01", 30.0, false, "Science", 3.2, "2027-06-01", 125));

        studentService.addStudent(new SeniorStudent("S5", "Noor", "Hamood", "2005-05-22", "Female",
                "90000005", "noor@school.com", "Muscat", "N1005", 21, true,
                12, "2021-09-01", 0.0, true, "Business", 3.7, "2027-06-01", 130));

        studentService.addStudent(new Student("S6", "Hassan", "Nasser", "2008-06-25", "Male",
                "90000006", "hassan@school.com", "Sur", "N1006", 18, true,
                12, "2024-09-01", 50.0, false));

        Teacher teacher1 = new Teacher("T1", "Ahmed", "Rashid", "1985-01-01", "Male",
                "91000001", "ahmed.teacher@school.com", "Muscat", "T1001", 41, true,
                "Math", 15, 1200.0, true);
        teacher1.addSlot("08:00");

        Teacher teacher2 = new Teacher("T2", "Laila", "Said", "1988-02-02", "Female",
                "91000002", "laila@school.com", "Muscat", "T1002", 38, true,
                "English", 12, 1150.0, false);
        teacher2.addSlot("09:00");

        Teacher teacher3 = new Teacher("T3", "Salim", "Ali", "1990-03-03", "Male",
                "91000003", "salim@school.com", "Sohar", "T1003", 36, true,
                "Science", 10, 1100.0, false);
        teacher3.addSlot("10:00");

        HeadTeacher headTeacher = new HeadTeacher("T4", "Maryam", "Hamed", "1980-04-04", "Female",
                "91000004", "maryam@school.com", "Muscat", "T1004", 46, true,
                "Administration", 20, 1600.0, true, 3, true);

        teacherService.addTeacher(teacher1);
        teacherService.addTeacher(teacher2);
        teacherService.addTeacher(teacher3);
        teacherService.addHeadTeacher(headTeacher);

        courseService.addRecord(new CourseRecord("R1", "S1", "T1", "Term1", 78, "Good", "", true));
        courseService.addRecord(new CourseRecord("R2", "S2", "T2", "Term1", 88, "Very Good", "", true));
        courseService.addRecord(new CourseRecord("R3", "S3", "T3", "Term1", 69, "Good", "", false));
        courseService.addRecord(new CourseRecord("R4", "S4", "T1", "Term2", 91, "Excellent", "", true));
        courseService.addRecord(new CourseRecord("R5", "S5", "T2", "Term2", 84, "Very Good", "", true));

        enrollmentService.enroll("S1", "C1", "2026-09-01");
        enrollmentService.enroll("S2", "C2", "2026-09-01");
        enrollmentService.enroll("S3", "C3", "2026-09-02");
        enrollmentService.enroll("S4", "C1", "2026-09-02");
        enrollmentService.enroll("S5", "C2", "2026-09-03");
        enrollmentService.enroll("S6", "C3", "2026-09-03");
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
