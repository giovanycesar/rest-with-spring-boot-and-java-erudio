package giovanycesar.com.github.services;

import giovanycesar.com.github.controllers.PersonController;
import giovanycesar.com.github.data.dto.PersonDTO;
import giovanycesar.com.github.exception.ResourceNotFoundException;
import giovanycesar.com.github.model.Person;
import giovanycesar.com.github.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.stereotype.Service;

import java.util.List;

import static giovanycesar.com.github.mapper.ObjectMapper.parseListObjects;
import static giovanycesar.com.github.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {

    // private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll() {
        logger.info("Finding all.");

        var people = parseListObjects(repository.findAll(), PersonDTO.class);

        people.forEach(this::addHateoasLinks);

        return people;
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one person.");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        var dto = parseObject(entity, PersonDTO.class);

        addHateoasLinks(dto);

        return dto;
    }


    public PersonDTO create(PersonDTO person) {
        logger.info("Creating one person");

        var entity = parseObject(person, Person.class);

        var dto = parseObject(repository.save(entity), PersonDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("Updating one person");

        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = parseObject(repository.save(entity), PersonDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    public void delete(Long id) {
        logger.info("Deleting one person.");

        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        repository.delete(entity);
    }

    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));

        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));

        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));

        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("update").withType("PUT"));

        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
