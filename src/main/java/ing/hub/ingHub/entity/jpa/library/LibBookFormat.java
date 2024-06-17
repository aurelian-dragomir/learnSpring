package ing.hub.ingHub.entity.jpa.library;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lib_book_format")
@NoArgsConstructor
@Getter
@Setter
public class LibBookFormat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String format;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private LibBook book;

    public LibBookFormat(String format) {
        this.format = format;
    }
}
