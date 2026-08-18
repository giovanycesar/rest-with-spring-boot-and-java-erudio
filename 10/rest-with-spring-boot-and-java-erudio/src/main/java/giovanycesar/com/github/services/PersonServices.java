package giovanycesar.com.github.services;

import giovanycesar.com.github.data.dto.v1.PersonDTO;
import giovanycesar.com.github.data.dto.v2.PersonDTOV2;
import giovanycesar.com.github.exception.ResourceNotFoundException;
import static giovanycesar.com.github.mapper.ObjectMapper.parseListObjects;
import static giovanycesar.com.github.mapper.ObjectMapper.parseObject;

import giovanycesar.com.github.mapper.custom.PersonMapper;
import giovanycesar.com.github.model.Person;
import giovanycesar.com.github.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServices {

    // private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;

    public List<PersonDTO> findAll() {
        logger.info("Finding all.");

        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public List<PersonDTOV2> findAllV2() {
        logger.info("Finding all.");

        return parseListObjects(repository.findAll(), PersonDTOV2.class);
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one person.");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTOV2 findByIdV2(Long id) {
        logger.info("Finding one person V2.");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        return parseObject(entity, PersonDTOV2.class);
    }


    public PersonDTO create(PersonDTO person) {
        logger.info("Creating one person");

        var entity = parseObject(person, Person.class);

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTOV2 createV2(PersonDTOV2 person) {
        logger.info("Creating one person V2");

        var entity = parseObject(person, Person.class);

        return parseObject(repository.save(entity), PersonDTOV2.class);
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("Updating one person");

        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTOV2 updateV2(PersonDTOV2 person) {
        logger.info("Updating one person V2");

        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setBirthDate(person.getBirthDate());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDTOV2.class);
    }

    public void delete(Long id) {
        logger.info("Deleting one person.");

        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        repository.delete(entity);
    }
}
