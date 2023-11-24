package self.learning.springboot;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			Student alif = new Student("Alif", "Laam Meem", "alif.lm@test.com", LocalDate.of(1990, Month.JANUARY, 19));
			Student ba = new Student("Baa", "Bee Boo", "baa.lm@test.com", LocalDate.of(1990, Month.DECEMBER, 1));
			Student ta = new Student("Taa", "Tee Too", "taa.lm@test.com", LocalDate.of(2000, Month.JULY, 9));
			Student sa = new Student("Saa", "See Saw", "saa.lm@test.com", LocalDate.of(2010, Month.APRIL, 2));
			
			repository.saveAll(List.of(alif,ba,ta,sa));
		};
	}
}
