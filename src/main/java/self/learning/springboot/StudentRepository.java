package self.learning.springboot;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

//@Repository
//public interface StudentRepository extends JpaRepository<Student,Long>{
public interface StudentRepository extends CrudRepository<Student,Long>{

	//@Query("SELECT s FROM Student s WHERE s.email = ?1")
	Optional<Student> findByEmail(String email);
}
