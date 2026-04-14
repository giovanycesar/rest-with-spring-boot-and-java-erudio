package giovanycesar.com.github.data.dto;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class BookDTO extends RepresentationModel<BookDTO> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String author;
    private Date launch_date;
    private Double price;
    private String title;

    public BookDTO() {

    }

    public BookDTO(Long id, String author, Date launch_date, Double price, String title) {
        this.id = id;
        this.author = author;
        this.launch_date = launch_date;
        this.price = price;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(Date launch_date) {
        this.launch_date = launch_date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(id, bookDTO.id) && Objects.equals(author, bookDTO.author) &&
                Objects.equals(launch_date, bookDTO.launch_date) &&
                Objects.equals(price, bookDTO.price) && Objects.equals(title, bookDTO.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, author, launch_date, price, title);
    }
}
