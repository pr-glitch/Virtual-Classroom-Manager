package Ei;

import java.util.HashMap;
import java.util.NoSuchElementException;

class AssignmentManager {
	private static HashMap<String, Assignment> assignments;

	AssignmentManager() {
		assignments = new HashMap<>();
	}

	void scheduleAssignment(String className, String assignmentDetails) {
		String assignmentKey = className + "_" + (assignments.size() + 1);
		addAssignment(assignmentKey, assignmentDetails);
		System.out.println("Assignment for '" + className + "' has been scheduled.");
	}

	void submitAssignment(String studentId, String className, String assignmentDetails) throws Exception {
		String assignmentKey = findAssignmentKey(className, assignmentDetails);

		if (assignmentKey != null) {
			if (!assignments.get(assignmentKey).isSubmitted()) {
				assignments.get(assignmentKey).setSubmitted(true);
				System.out.println("Assignment submitted by Student '" + studentId + "' in '" + className + "'.");
			} else {
				throw new IllegalStateException("Student '" + studentId + "' has already submitted this assignment.");
			}
		} else {
			throw new NoSuchElementException(
					"Assignment '" + assignmentDetails + "' not found in '" + className + "'.");
		}
	}

	void listAssignments() {
		System.out.println("List of Assignments:");
		for (String assignmentKey : assignments.keySet()) {
			System.out.println(assignmentKey + ": " + assignments.get(assignmentKey).getDetails());
		}
	}

	static void removeAssignmentsByClass(String className) {
		HashMap<String, Assignment> assignmentsCopy = new HashMap<>(assignments);
		for (String assignmentKey : assignmentsCopy.keySet()) {
			if (assignmentKey.startsWith(className)) {
				assignments.remove(assignmentKey);
			}
		}
	}

	private String findAssignmentKey(String className, String assignmentDetails) {
		for (String assignmentKey : assignments.keySet()) {
			if (assignmentKey.startsWith(className)
					&& assignments.get(assignmentKey).getDetails().equals(assignmentDetails)) {
				return assignmentKey;
			}
		}
		return null;
	}

	static void addAssignment(String assignmentKey, String assignmentDetails) {
		assignments.put(assignmentKey, new Assignment(assignmentDetails));
	}
}
