package Ei;

import java.util.*;

public class Driver {
	public static void main(String[] args) {
		int numberOfClassrooms = 0;
		int numberOfStudents = 0;
		int numberOfAssignments = 0;
		Set<String> allStudentIds = new HashSet<>();
		ClassroomManager cm = new ClassroomManager();
		StudentManager sm = new StudentManager();
		AssignmentManager am = new AssignmentManager();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Enter a command:");
			String command = scanner.nextLine();

			try {
				if (command.equals("add_classroom")) {
					System.out.print("Enter classroom name: ");
					String className = scanner.nextLine();
					cm.addClassroom(className);
					numberOfClassrooms++;
				} else if (command.equals("list_classrooms")) {
					cm.listClassrooms();
				} else if (command.equals("remove_classroom")) {
					System.out.print("Enter classroom name to remove: ");
					String className = scanner.nextLine();
					cm.removeClassroom(className, sm, am);
					numberOfClassrooms--;
				} else if (command.equals("add_student")) {
					System.out.print("Enter student ID: ");
					String studentId = scanner.nextLine();
					System.out.print("Enter classroom name: ");
					String className = scanner.nextLine();
					cm.addStudentToClassroom(className, studentId, sm);
					numberOfStudents++;
				} else if (command.equals("list_students")) {
					System.out.print("Enter classroom name: ");
					String className = scanner.nextLine();
					sm.listStudents(className);
				} else if (command.equals("add_assignment")) {
					System.out.print("Enter classroom name: ");
					String className = scanner.nextLine();
					System.out.print("Enter Assignment details: ");
					String assignmentDetails = scanner.nextLine();
					am.addAssignment(className, assignmentDetails);
					numberOfAssignments++;
				} else if (command.equals("submit_assignment")) {
					System.out.print("Enter student ID: ");
					String studentId = scanner.nextLine();
					System.out.print("Enter classroom name: ");
					String className = scanner.nextLine();
					System.out.print("Enter Assignment details: ");
					String assignmentDetails = scanner.nextLine();
					am.submitAssignment(studentId, className, assignmentDetails);

				} else if (command.equals("list_assignments")) {
					am.listAssignments();
				} else if (command.equals("exit")) {
					break;
				} else {
					System.out.println("Invalid command");
				}
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		}

		scanner.close();
		System.out.println("Exiting the program.");
	}

}
