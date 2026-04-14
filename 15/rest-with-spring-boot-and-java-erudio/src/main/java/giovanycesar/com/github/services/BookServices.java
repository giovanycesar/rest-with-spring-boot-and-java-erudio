package giovanycesar.com.github.services;

import giovanycesar.com.github.controllers.BookController;
import giovanycesar.com.github.controllers.PersonController;
import giovanycesar.com.github.data.dto.BookDTO;
import giovanycesar.com.github.data.dto.PersonDTO;
import giovanycesar.com.github.exception.RequiredObjectIsNullException;
import giovanycesar.com.github.exception.ResourceNotFoundException;
import giovanycesar.com.github.model.Book;
import giovanycesar.com.github.model.Person;
import giovanycesar.com.github.repository.BookRepository;
import giovanycesar.com.github.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static giovanycesar.com.github.mapper.ObjectMapper.parseListObjects;
import static giovanycesar.com.github.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    private Logger logger = LoggerFactory.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repository;

    public List<BookDTO> findAll() {
        logger.info("Finding all.");

        var books = parseListObjects(repository.findAll(), BookDTO.class);

        books.forEach(this::addHateoasLinks);

        return books;
    }

    public BookDTO findById(Long id) {
        logger.info("Finding one book.");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        var dto = parseObject(entity, BookDTO.class);

        addHateoasLinks(dto);

        return dto;
    }


    public BookDTO create(BookDTO book) {

        if (book == null) {throw new RequiredObjectIsNullException();}

        logger.info("Creating one book");

        var entity = parseObject(book, Book.class);

        var dto = parseObject(repository.save(entity), BookDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    public BookDTO update(BookDTO book) {

        if (book == null) {throw new RequiredObjectIsNullException();}

        logger.info("Updating one book");

        Book entity = repository.findById(book.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        entity.setAuthor(book.getAuthor());
        entity.setTitle(book.getTitle());
        entity.setPrice(book.getPrice());
        entity.setLaunch_date(book.getLaunch_date());

        var dto = parseObject(repository.save(entity), BookDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    public void delete(Long id) {
        logger.info("Deleting one book.");

        Book entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        repository.delete(entity);
    }

    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));

        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));

        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));

        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("update").withType("PUT"));

        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
