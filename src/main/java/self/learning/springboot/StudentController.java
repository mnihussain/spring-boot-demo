package self.learning.springboot;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	//@GetMapping("/")
	public String home() {
		return "home.jsp";
	}
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getListOfStudents() {
		return ResponseEntity.ok(studentService.getListOfStudents());
	}
	
	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable Long id) throws StudentNotFoundException{
		return ResponseEntity.ok(studentService.getStudent(id));
	}
	
	@PostMapping("/students")
	public ResponseEntity<Student> postStudent(@RequestBody @Valid Student student) {
		return new ResponseEntity<>(studentService.addStudent(student),HttpStatus.CREATED);
	}
	
	@PutMapping("/students/{id}")
	public ResponseEntity<Student> putStudent(@PathVariable Long id, @RequestBody @Valid Student student) {
		return ResponseEntity.ok(studentService.updateStudent(student, id));
	}
	
	@DeleteMapping("/students/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
		studentService.removeStudent(id);
		return ResponseEntity.ok("Student Deleted");
	}
}
