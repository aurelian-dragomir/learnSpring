package ing.hub.ingHub.entity.jpa.library;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lib_book_detail")
@NoArgsConstructor
@Getter
@Setter
public class LibBookDetail {

    @Id
    private String id;

    @Lob
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private LibBook book;

    public LibBookDetail(LibBook book, String description) {
        this.description = description;
        this.book = book;
        this.id = book.getId();
    }
}
