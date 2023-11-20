package Ei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.HashSet;

public class ClassroomManager {
	private HashMap<String, List<String>> classrooms;
	private Set<String> allStudentIds;

	ClassroomManager() {
		this.classrooms = new HashMap<>();
		this.allStudentIds = new HashSet<>();
	}

	void addClassroom(String className) throws Exception {
		if (!classrooms.containsKey(className)) {
			classrooms.put(className, new ArrayList<>());
			System.out.println("Classroom '" + className + "' has been created.");
		} else {
			throw new IllegalArgumentException("Classroom '" + className + "' already exists.");
		}
	}

	void removeClassroom(String className, StudentManager studentManager, AssignmentManager assignmentManager) {
		if (classrooms.containsKey(className)) {
			ArrayList<String> enrolledStudents = new ArrayList<>(classrooms.get(className));
			for (String studentId : enrolledStudents) {
				studentManager.removeStudent(studentId);
				allStudentIds.remove(studentId);
			}
			classrooms.remove(className);
			assignmentManager.removeAssignmentsByClass(className);
			System.out.println("Classroom '" + className + "' has been removed.");
		} else {
			System.out.println("Classroom '" + className + "' does not exist.");
		}
	}

	void listClassrooms() {
		System.out.println("List of Classrooms:");
		for (String className : classrooms.keySet()) {
			System.out.println(className + " - Students: " + classrooms.get(className).size());
		}
	}

	List<String> getClassroomStudents(String className) {
		return classrooms.getOrDefault(className, new ArrayList<>());
	}

	void addStudentToClassroom(String className, String studentId, StudentManager studentManager) throws Exception {
		if (allStudentIds.contains(studentId)) {
			throw new IllegalArgumentException("Student ID '" + studentId + "' already exists.");
		}

		List<String> students = classrooms.getOrDefault(className, new ArrayList<>());
		students.add(studentId);
		classrooms.put(className, students);
		allStudentIds.add(studentId);
		System.out.println("Student '" + studentId + "' has been enrolled in '" + className + "'.");
		studentManager.addStudent(studentId, className);
	}
}