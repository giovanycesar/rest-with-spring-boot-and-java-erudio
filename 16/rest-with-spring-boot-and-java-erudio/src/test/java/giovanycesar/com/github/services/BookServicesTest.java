package giovanycesar.com.github.services;

import giovanycesar.com.github.data.dto.BookDTO;
import giovanycesar.com.github.exception.RequiredObjectIsNullException;
import giovanycesar.com.github.model.Book;
import giovanycesar.com.github.repository.BookRepository;
import giovanycesar.com.github.unitetests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

    MockBook input;

    @InjectMocks
    private BookServices service;

    @Mock
    BookRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Book book = input.mockEntity(1);
        book.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(book));

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());


        assertTrue(result.getLinks().stream().anyMatch(link -> "self".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1/1")
                && "GET".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "findAll".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1")
                && "GET".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "create".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1")
                && "POST".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "update".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1")
                && "PUT".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "delete".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1/1")
                && "DELETE".equals(link.getType())));

        assertEquals("Some Author1", result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertEquals(25D, result.getPrice());
        assertEquals("Some Title1", result.getTitle());

    }

    @Test
    void create() {

        Book book = input.mockEntity(1);
        book.setId(1L);
        Book persisted = book;

        BookDTO dto = input.mockDTO(1);

        when(repository.save(book)).thenReturn(persisted);

        var result = service.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());


        assertTrue(result.getLinks().stream().anyMatch(link -> "self".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1/1")
                && "GET".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "findAll".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1")
                && "GET".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "create".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1")
                && "POST".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "update".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1")
                && "PUT".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "delete".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1/1")
                && "DELETE".equals(link.getType())));

        assertEquals("Some Author1", result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertEquals(25D, result.getPrice());
        assertEquals("Some Title1", result.getTitle());
    }

    @Test
    void testCreateWithNullBook(){
     Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {service.create(null);});

     String expectedMessage = "It is not allowed to be null";
     String actualMessage = exception.getMessage();

     assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {
        Book book = input.mockEntity(1);
        book.setId(1L);
        Book persisted = book;

        BookDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(book));

        when(repository.save(book)).thenReturn(persisted);

        var result = service.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());


        assertTrue(result.getLinks().stream().anyMatch(link -> "self".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1/1")
                && "GET".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "findAll".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1")
                && "GET".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "create".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1")
                && "POST".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "update".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1")
                && "PUT".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "delete".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1/1")
                && "DELETE".equals(link.getType())));

        assertEquals("Some Author1", result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertEquals(25D, result.getPrice());
        assertEquals("Some Title1", result.getTitle());
    }

    @Test
    void testUpdateWithNullBook(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {service.update(null);});

        String expectedMessage = "It is not allowed to be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete() {
        Book book = input.mockEntity(1);
        book.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(book));

        service.delete(1L);

        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Book.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll() {

        List<Book> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<BookDTO> books = service.findAll();

        assertNotNull(books);
        assertEquals(14, books.size());

        var bookOne = books.get(1);

        assertNotNull(bookOne);
        assertNotNull(bookOne.getId());
        assertNotNull(bookOne.getLinks());


        assertTrue(bookOne.getLinks().stream().anyMatch(link -> "self".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1/1")
                && "GET".equals(link.getType())));

        assertTrue(bookOne.getLinks().stream().anyMatch(link -> "findAll".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1")
                && "GET".equals(link.getType())));

        assertTrue(bookOne.getLinks().stream().anyMatch(link -> "create".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1")
                && "POST".equals(link.getType())));

        assertTrue(bookOne.getLinks().stream().anyMatch(link -> "update".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1")
                && "PUT".equals(link.getType())));

        assertTrue(bookOne.getLinks().stream().anyMatch(link -> "delete".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1/1")
                && "DELETE".equals(link.getType())));

        assertEquals("Some Author1", bookOne.getAuthor());
        assertNotNull(bookOne.getLaunchDate());
        assertEquals(25D, bookOne.getPrice());
        assertEquals("Some Title1", bookOne.getTitle());

        var bookFour = books.get(4);

        assertNotNull(bookFour);
        assertNotNull(bookFour.getId());
        assertNotNull(bookFour.getLinks());


        assertTrue(bookFour.getLinks().stream().anyMatch(link -> "self".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1/4")
                && "GET".equals(link.getType())));

        assertTrue(bookFour.getLinks().stream().anyMatch(link -> "findAll".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1")
                && "GET".equals(link.getType())));

        assertTrue(bookFour.getLinks().stream().anyMatch(link -> "create".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1")
                && "POST".equals(link.getType())));

        assertTrue(bookFour.getLinks().stream().anyMatch(link -> "update".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1")
                && "PUT".equals(link.getType())));

        assertTrue(bookFour.getLinks().stream().anyMatch(link -> "delete".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1/4")
                && "DELETE".equals(link.getType())));

        assertEquals("Some Author4", bookFour.getAuthor());
        assertNotNull(bookFour.getLaunchDate());
        assertEquals(25D, bookFour.getPrice());
        assertEquals("Some Title4", bookFour.getTitle());

        var bookSeven = books.get(7);

        assertNotNull(bookSeven);
        assertNotNull(bookSeven.getId());
        assertNotNull(bookSeven.getLinks());


        assertTrue(bookSeven.getLinks().stream().anyMatch(link -> "self".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1/7")
                && "GET".equals(link.getType())));

        assertTrue(bookSeven.getLinks().stream().anyMatch(link -> "findAll".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1")
                && "GET".equals(link.getType())));

        assertTrue(bookSeven.getLinks().stream().anyMatch(link -> "create".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1")
                && "POST".equals(link.getType())));

        assertTrue(bookSeven.getLinks().stream().anyMatch(link -> "update".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1")
                && "PUT".equals(link.getType())));

        assertTrue(bookSeven.getLinks().stream().anyMatch(link -> "delete".equals(link.getRel().value())
                && link.getHref().endsWith("/api/book/v1/7")
                && "DELETE".equals(link.getType())));

        assertEquals("Some Author7", bookSeven.getAuthor());
        assertNotNull(bookSeven.getLaunchDate());
        assertEquals(25D, bookSeven.getPrice());
        assertEquals("Some Title7", bookSeven.getTitle());
    }
}