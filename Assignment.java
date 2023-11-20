package Ei;

public class Assignment {
	private String details;
	private boolean submitted;

	Assignment(String details) {
		this.details = details;
		this.submitted = false;
	}

	String getDetails() {
		return details;
	}

	boolean isSubmitted() {
		return submitted;
	}

	void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}
}