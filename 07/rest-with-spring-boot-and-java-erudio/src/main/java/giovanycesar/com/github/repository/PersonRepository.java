package giovanycesar.com.github.repository;

import giovanycesar.com.github.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
