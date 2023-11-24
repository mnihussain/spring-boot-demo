package self.learning.springboot;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

//@Component
@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> students = new ArrayList<>(List.of(
				new Student(1L,"Johnny", "Doe", "jd@test.com", LocalDate.of(2000, Month.JANUARY, 8)),
				new Student(2L,"Jean", "Doe", "jnd@test.com", LocalDate.of(1988, Month.MARCH, 18)),
				new Student(3L,"Perri", "Doe", "pd@test.com", LocalDate.of(1977, Month.DECEMBER, 20))
				));
	
	public List<Student> getListOfStudents(){
		//return students;
		List<Student> students = new ArrayList<>();
		studentRepository.findAll().forEach(students::add);
		return students;
	}
	
	public Student getStudent(Long id) throws StudentNotFoundException{
		//return students.stream().filter(s -> s.getId() == id).findFirst().get();
		
		return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found with given id: "+ id));
	}
	
	public Student addStudent(Student student) {
		//students.add(student);
		if(studentRepository.findByEmail(student.getEmail()).isPresent()) {
			throw new IllegalStateException("email taken");
		}
		return studentRepository.save(student);
	}
	
	@Transactional
	public Student updateStudent(Student student, Long id) {
		Student existingStudent = studentRepository.findById(id)
			.orElseThrow(() -> new IllegalStateException("student with id: " + id + ", does not exists"));
		
		if(studentRepository.findByEmail(student.getEmail()).isPresent()) {
			throw new IllegalStateException("email taken");
		}
		
		if(student.getFirstName() != null && student.getLastName() != null
				&& student.getFirstName().length() > 0 && student.getLastName().length() > 0
				&& student.getEmail().contains("@")) {
			existingStudent.setFirstName(student.getFirstName());
			existingStudent.setLastName(student.getLastName());
			existingStudent.setEmail(student.getEmail());
		}
		return existingStudent;
	}
	
	public void removeStudent(Long id) {
		//students.removeIf(s -> s.getId() == id);
		if(! studentRepository.existsById(id)) {
			throw new IllegalStateException("student with id: " + id + ", does not exists");
		}
		studentRepository.deleteById(id);
	}
}
