package self.learning.springboot;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "STUDENTS")
public class Student {

	@Id
//	@SequenceGenerator(
//			name = "student_id_generator",
//			sequenceName = "student_id_generator",
//			allocationSize = 1)
//	@GeneratedValue(
//			strategy = GenerationType.SEQUENCE,
//			generator = "student_id_generator")
	@GeneratedValue
	private Long id;
	@NotBlank(message = "First Name shouldn't be null")
	private String firstName;
	@NotBlank(message = "Last Name shouldn't be null")
	private String lastName;
	@Email(message = "Invalid email address")
	private String email;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate DOB;
	@Transient
	private String age;
	
	public Student() {
		
	}
	
	public Student(Long id, String firstName, String lastName, String email, LocalDate DOB) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.DOB = DOB;
	}

	public Student(String firstName, String lastName, String email, LocalDate DOB) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.DOB = DOB;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getDOB() {
		return DOB;
	}

	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}

	public String getAge() {
		return Period.between(this.DOB, LocalDate.now()).getYears() +" years "
				+ Period.between(this.DOB, LocalDate.now()).getMonths() +" months "
				+ Period.between(this.DOB, LocalDate.now()).getDays() + " days";
	}
	
	public void setAge(String age) {
		this.age = age;
	}
}
