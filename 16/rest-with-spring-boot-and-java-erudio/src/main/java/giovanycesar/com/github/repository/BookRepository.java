package giovanycesar.com.github.repository;

import giovanycesar.com.github.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
