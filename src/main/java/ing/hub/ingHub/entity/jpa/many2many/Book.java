package ing.hub.ingHub.entity.jpa.many2many;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = "authors")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "books")
    private Set<Author> authors = new LinkedHashSet<>();

    public Book(String title) {
        this.title = title;
    }

    public void addAuthor(Author author) {
        author.addBook(this);
    }

    public void removeAuthor(Author author) {
        author.removeBook(this);
    }
}
