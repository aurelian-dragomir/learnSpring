package ing.hub.ingHub.jpa.many2many;

import ing.hub.ingHub.entity.jpa.many2many.Author;
import ing.hub.ingHub.entity.jpa.many2many.Book;
import ing.hub.ingHub.jpa.BaseJpaTest;
import ing.hub.ingHub.repository.jpa.many2many.AuthorRepository;
import ing.hub.ingHub.repository.jpa.many2many.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class Many2ManyTest extends BaseJpaTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testAddAuthorsWithBooks() {
        executeInTransaction(() -> {
            Author author = new Author("vasile");
            Book book = new Book("tuica");

            author.addBook(book);
            authorRepository.save(author);
            bookRepository.save(book);
            return null;
        });
    }

    @Test
    public void testAddBookForExistingAuthor() {
        Long id = executeInTransaction(() -> {
            Author author = new Author("vasile");
            authorRepository.save(author);
            return author.getId();
        });

        executeInTransaction(() -> {
            Book book = new Book("java");
            Author author = authorRepository.findById(id).get();
            book.addAuthor(author);
            bookRepository.save(book);

            return null;
        });
    }

    @Test
    public void testDeleteAuthor() {
        Long id = executeInTransaction(() -> {
            Author author = new Author("vasile");
            Book book = new Book("tuica");

            author.addBook(book);
            authorRepository.save(author);
            bookRepository.save(book);
            return author.getId();
        });

        executeInTransaction(() -> {
            Author author = authorRepository.findById(id).get();
            authorRepository.delete(author);
            bookRepository.deleteAll(author.getBooks());
            return null;
        });
    }

    @Test
    public void testDeleteBook() {
        Long id = executeInTransaction(() -> {
            Author author = new Author("vasile");
            Book book = new Book("tuica");

            author.addBook(book);
            authorRepository.save(author);
            bookRepository.save(book);
            return book.getId();
        });

        executeInTransaction(() -> {
            Book book = bookRepository.findById(id).get();
            Set<Author> authors = book.getAuthors();
            authors.forEach(a -> a.getBooks().remove(book));
            authorRepository.saveAll(authors);
            bookRepository.delete(book);

            return null;
        });
    }
}
