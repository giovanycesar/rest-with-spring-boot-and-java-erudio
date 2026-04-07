package giovanycesar.com.github.services;

import giovanycesar.com.github.data.dto.PersonDTO;
import giovanycesar.com.github.exception.RequiredObjectIsNullException;
import giovanycesar.com.github.model.Person;
import giovanycesar.com.github.repository.PersonRepository;
import giovanycesar.com.github.unitetests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices service;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());


        assertTrue(result.getLinks().stream().anyMatch(link -> "self".equals(link.getRel().value())
                && link.getHref().endsWith("/api/person/v1/1")
                && "GET".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "findAll".equals(link.getRel().value())
                && link.getHref().endsWith("/api/person/v1")
                && "GET".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "create".equals(link.getRel().value())
                && link.getHref().endsWith("/api/person/v1")
                && "POST".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "update".equals(link.getRel().value())
                && link.getHref().endsWith("/api/person/v1")
                && "PUT".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "delete".equals(link.getRel().value())
                && link.getHref().endsWith("/api/person/v1/1")
                && "DELETE".equals(link.getType())));

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());

    }

    @Test
    void create() {

        Person person = input.mockEntity(1);
        person.setId(1L);
        Person persisted = person;

        PersonDTO dto = input.mockDTO(1);

        when(repository.save(person)).thenReturn(persisted);

        var result = service.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());


        assertTrue(result.getLinks().stream().anyMatch(link -> "self".equals(link.getRel().value())
                && link.getHref().endsWith("/api/person/v1/1")
                && "GET".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "findAll".equals(link.getRel().value())
                && link.getHref().endsWith("/api/person/v1")
                && "GET".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "create".equals(link.getRel().value())
                && link.getHref().endsWith("/api/person/v1")
                && "POST".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "update".equals(link.getRel().value())
                && link.getHref().endsWith("/api/person/v1")
                && "PUT".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "delete".equals(link.getRel().value())
                && link.getHref().endsWith("/api/person/v1/1")
                && "DELETE".equals(link.getType())));

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testCreateWithNullPerson(){
     Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {service.create(null);});

     String expectedMessage = "It is not allowed to be null";
     String actualMessage = exception.getMessage();

     assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        Person persisted = person;

        PersonDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        when(repository.save(person)).thenReturn(persisted);

        var result = service.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());


        assertTrue(result.getLinks().stream().anyMatch(link -> "self".equals(link.getRel().value())
                && link.getHref().endsWith("/api/person/v1/1")
                && "GET".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "findAll".equals(link.getRel().value())
                && link.getHref().endsWith("/api/person/v1")
                && "GET".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "create".equals(link.getRel().value())
                && link.getHref().endsWith("/api/person/v1")
                && "POST".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "update".equals(link.getRel().value())
                && link.getHref().endsWith("/api/person/v1")
                && "PUT".equals(link.getType())));

        assertTrue(result.getLinks().stream().anyMatch(link -> "delete".equals(link.getRel().value())
                && link.getHref().endsWith("/api/person/v1/1")
                && "DELETE".equals(link.getType())));

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testUpdateWithNullPerson(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {service.update(null);});

        String expectedMessage = "It is not allowed to be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        service.delete(1L);

        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Person.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll() {
    }
}