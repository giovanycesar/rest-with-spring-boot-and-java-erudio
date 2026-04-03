package giovanycesar.com.github.services;

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
import static org.mockito.Mockito.when;

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
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }
}