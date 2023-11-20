package Ei;

import java.util.HashMap;
import java.util.NoSuchElementException;

class StudentManager {
	private HashMap<String, String> students;

	StudentManager() {
		students = new HashMap<>();
	}

	void addStudent(String studentId, String className) throws Exception {
		if (!students.containsKey(studentId)) {
			students.put(studentId, className);
		} else {
			throw new IllegalArgumentException("Student ID '" + studentId + "' already exists.");
		}
	}

	void removeStudent(String studentId) {
		students.remove(studentId);
	}

	void listStudents(String className) {
		System.out.println("List of Students in '" + className + "':");
		for (String studentId : students.keySet()) {
			if (students.get(studentId).equals(className)) {
				System.out.println(studentId);
			}
		}
	}
}