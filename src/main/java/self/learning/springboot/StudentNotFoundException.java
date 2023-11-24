package self.learning.springboot;

public class StudentNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -6895306638238680391L;

	public StudentNotFoundException(String message) {
		super(message);
	}
}
