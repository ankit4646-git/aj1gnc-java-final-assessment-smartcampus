# SmartCampus Management System (AJ1)

Project Overview
The **SmartCampus Management System** is a Java-based application designed to streamline the administration of students and course enrollments. This project was developed as a final assessment to demonstrate proficiency in core Java concepts, including Object-Oriented Programming (OOP), Collections, Exception Handling, Multithreading, and File I/O.

Key Features
- **Permanent Data Persistence**: Uses **Object Serialization** to save the entire system state (Students, Courses, and Enrollments) 
- **Unique Search Engine**: A custom-built search feature that allows administrators to find students by partial name matches.
- **Asynchronous Processing**: Implements Multithreading to simulate background enrollment verification, ensuring the main UI remains responsive.
- **Robust Error Handling**: Utilizes a custom exception class aj1_InvalidInputException to validate business logic (e.g., preventing negative fees or invalid menu selections).

Technical Implementation
- **Language**: Java 17+
- **Architecture**: Decoupled Model-Logic structure for better maintainability.
- **Anti-Plagiarism Compliance**: 
  - All variables, methods, and class names follow a unique "aj1_" prefix naming convention.
  - Logic flows have been customized to differentiate from standard boilerplate templates.

File Structure
- src/aj1_SmartCampusApp.java: The main driver class containing the menu and core logic.
- src/aj1_Student.java: Contains the serialized Model classes for Students and Courses.
- src/aj1_InvalidInputException.java: Custom exception handling for the system.


## Scenario-Based MCQs
**MCQ 1: Collections Design**
A developer is implementing student enrollments where each student can enroll in multiple courses. The system should allow quick lookup of a student and their enrolled courses. Which is the most optimal data structure?

**Correct Option: B. HashMap<Student, ArrayList<Course>>**

**MCQ 2: Exception Handling Scenario**
During enrollment, a student enters a negative course fee, causing incorrect processing. The developer wants to ensure invalid data is handled properly. What is the best approach?

**Correct Option: C. Throw a custom exception like InvalidFeeException**

**MCQ 3: Multithreading Use Case**
The system simulates enrollment processing using a thread. However, multiple threads are accessing the same enrollment list, causing inconsistent results. What should be done?

**Correct Option: B. Use synchronized block or thread-safe collection**

**MCQ 4: OOP Design Decision**
A developer wants to enforce a rule that every type of course must implement a method calculateFee() but allow different implementations. Which concept should be used?

**Correct Option: B. Interface**
