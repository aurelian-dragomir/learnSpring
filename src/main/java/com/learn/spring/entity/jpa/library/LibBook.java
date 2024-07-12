package ing.hub.ingHub.entity.jpa.library;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "lib_book")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "title")
public class LibBook {

    @Id
    private String id;

    private String title;

    private LocalDate publishingDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<LibBookFormat> bookFormats = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "lib_book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<LibAuthor> authors = new LinkedHashSet<>();

    public LibBook(String id, String title, LocalDate publishingDate) {
        this.id = id;
        this.title = title;
        this.publishingDate = publishingDate;
    }

    public void addBookFormat(LibBookFormat bookFormat) {
        bookFormats.add(bookFormat);
        bookFormat.setBook(this);
    }

    public void addAuthor(LibAuthor author) {
        authors.add(author);
        author.getBooks().add(this);
    }
}
