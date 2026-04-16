import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class aj1_SmartCampusApp {
    private static List<aj1_Student> aj1_studentRegistry = new ArrayList<>();
    private static List<aj1_Course> aj1_courseCatalog = new ArrayList<>();
    private static Map<Integer, List<aj1_Course>> aj1_enrollmentMap = new HashMap<>();
    private static final String AJ1_DB_FILE = "aj1_campus_database.ser";
    private static Scanner aj1_inputReader = new Scanner(System.in);

    public static void main(String[] args) {
        // Load data from file as soon as the app opens
        aj1_loadPermanentData();

        boolean aj1_isRunning = true;
        while (aj1_isRunning) {
            System.out.println("\n==== SMART CAMPUS SYSTEM (AJ1) ====");
            System.out.println("1. New Student\n2. New Course\n3. Enroll Student\n4. View Students\n5. Search by Name\n6. View Enrollments\n7. Async Process\n8. Save & Exit");
            System.out.print("Action: ");

            try {
                int aj1_choice = Integer.parseInt(aj1_inputReader.nextLine());
                switch (aj1_choice) {
                    case 1 -> aj1_performStudentAddition();
                    case 2 -> aj1_performCourseAddition();
                    case 3 -> aj1_performEnrollment();
                    case 4 -> aj1_displayStudents();
                    case 5 -> aj1_findStudentByName();
                    case 6 -> aj1_displayEnrollments();
                    case 7 -> aj1_triggerAsyncProcessing();
                    case 8 -> {
                        aj1_savePermanentData(); // Save data before closing
                        aj1_isRunning = false;
                        System.out.println("All data saved permanently. Goodbye!");
                    }
                    default -> throw new aj1_InvalidInputException("Invalid Menu Option!");
                }
            } catch (Exception e) {
                System.out.println("ALERT: " + e.getMessage());
            }
        }
    }

    private static void aj1_performStudentAddition() {
        System.out.print("ID: "); int id = Integer.parseInt(aj1_inputReader.nextLine());
        System.out.print("Name: "); String name = aj1_inputReader.nextLine();
        System.out.print("Email: "); String email = aj1_inputReader.nextLine();
        aj1_studentRegistry.add(new aj1_Student(id, name, email));
        System.out.println("Student added to session memory.");
    }

    private static void aj1_performCourseAddition() throws aj1_InvalidInputException {
        System.out.print("Course ID: "); int id = Integer.parseInt(aj1_inputReader.nextLine());
        System.out.print("Title: "); String title = aj1_inputReader.nextLine();
        System.out.print("Fee: "); double fee = Double.parseDouble(aj1_inputReader.nextLine());
        if (fee < 0) throw new aj1_InvalidInputException("Fee cannot be negative.");
        aj1_courseCatalog.add(new aj1_Course(id, title, fee));
    }

    private static void aj1_performEnrollment() {
        System.out.print("Student ID: "); int sId = Integer.parseInt(aj1_inputReader.nextLine());
        System.out.print("Course ID: "); int cId = Integer.parseInt(aj1_inputReader.nextLine());
        
        aj1_Student s = aj1_studentRegistry.stream().filter(st -> st.getAj1_id() == sId).findFirst().orElse(null);
        aj1_Course c = aj1_courseCatalog.stream().filter(co -> co.getAj1_code() == cId).findFirst().orElse(null);

        if (s != null && c != null) {
            aj1_enrollmentMap.computeIfAbsent(sId, k -> new ArrayList<>()).add(c);
            System.out.println("Enrolled!");
        } else {
            System.out.println("Error: IDs not found.");
        }
    }

    private static void aj1_displayStudents() {
        if (aj1_studentRegistry.isEmpty()) System.out.println("No records found.");
        aj1_studentRegistry.forEach(System.out::println);
    }

    private static void aj1_findStudentByName() {
        System.out.print("Search: ");
        String q = aj1_inputReader.nextLine().toLowerCase();
        aj1_studentRegistry.stream()
            .filter(s -> s.getAj1_fullName().toLowerCase().contains(q))
            .forEach(System.out::println);
    }

    private static void aj1_displayEnrollments() {
        aj1_enrollmentMap.forEach((id, list) -> System.out.println("Student " + id + " -> " + list));
    }

    private static void aj1_triggerAsyncProcessing() {
        new Thread(() -> {
            try {
                System.out.println("[AJ1-Thread] Verifying batch...");
                Thread.sleep(2000);
                System.out.println("[AJ1-Thread] Process complete.");
            } catch (InterruptedException e) { 
                e.printStackTrace(); 
            }
        }).start();
    }

    // STORE DATA
    private static void aj1_savePermanentData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(AJ1_DB_FILE))) {
            oos.writeObject(aj1_studentRegistry);
            oos.writeObject(aj1_courseCatalog);
            oos.writeObject(aj1_enrollmentMap);
            System.out.println("Database Updated: " + AJ1_DB_FILE);
        } catch (IOException e) {
            System.out.println("Save Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void aj1_loadPermanentData() {
        File file = new File(AJ1_DB_FILE);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(AJ1_DB_FILE))) {
            aj1_studentRegistry = (List<aj1_Student>) ois.readObject();
            aj1_courseCatalog = (List<aj1_Course>) ois.readObject();
            aj1_enrollmentMap = (Map<Integer, List<aj1_Course>>) ois.readObject();
            System.out.println("Welcome back! Loaded " + aj1_studentRegistry.size() + " students.");
        } catch (Exception e) {
            System.out.println("No previous data found.");
        }
    }
}