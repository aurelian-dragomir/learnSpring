package ing.hub.ingHub.jpa.library;

import ing.hub.ingHub.entity.jpa.library.LibAuthor;
import ing.hub.ingHub.entity.jpa.library.LibBook;
import ing.hub.ingHub.entity.jpa.library.LibBookDetail;
import ing.hub.ingHub.entity.jpa.library.LibBookFormat;
import ing.hub.ingHub.jpa.BaseJpaTest;
import ing.hub.ingHub.repository.jpa.library.LibAuthorRepository;
import ing.hub.ingHub.repository.jpa.library.LibBookDetailRepository;
import ing.hub.ingHub.repository.jpa.library.LibBookFormatRepository;
import ing.hub.ingHub.repository.jpa.library.LibBookRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public class LibraryTest extends BaseJpaTest {

    @Autowired
    private LibBookRepository bookRepository;

    @Autowired
    private LibAuthorRepository authorRepository;

    @Autowired
    private LibBookFormatRepository bookFormatRepository;

    @Autowired
    private LibBookDetailRepository bookDetailRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testSaveFullBook() {
        executeInTransaction(() -> {
            LibBook book = new LibBook("123", "java", LocalDate.now());
            LibBookDetail bookDetail = new LibBookDetail(book, "learning java");

            LibAuthor author = new LibAuthor("ion");
            LibBookFormat bookFormat = new LibBookFormat("pdf");

            book.addAuthor(author);
            book.addBookFormat(bookFormat);

            bookRepository.save(book);
            bookDetailRepository.save(bookDetail);

            return null;
        });
    }

    @Test
    public void testAddAuthorForExistingBook() {
        String bookId = executeInTransaction(() -> {
            LibBook book = new LibBook("123", "java", LocalDate.now());
            LibBookDetail bookDetail = new LibBookDetail(book, "learning java");

            LibAuthor author = new LibAuthor("ion");
            LibBookFormat bookFormat = new LibBookFormat("pdf");

            book.addAuthor(author);
            book.addBookFormat(bookFormat);

            bookRepository.save(book);
            bookDetailRepository.save(bookDetail);

            return book.getId();
        });

        executeInTransaction(() -> {

            LibBook book = bookRepository.findById(bookId).get();
            LibAuthor author = new LibAuthor("maria");
            book.addAuthor(author);

            bookRepository.save(book);

            return null;
        });
    }

    @Test
    public void testAddBookDetails() {
        String bookId = executeInTransaction(() -> {
            LibBook book = new LibBook("123", "java", LocalDate.now());

            LibAuthor author = new LibAuthor("ion");
            LibBookFormat bookFormat = new LibBookFormat("pdf");

            book.addAuthor(author);
            book.addBookFormat(bookFormat);

            bookRepository.save(book);

            return book.getId();
        });

        executeInTransaction(() -> {

            LibBook book = bookRepository.getReferenceById(bookId);
            LibBookDetail bookDetail = new LibBookDetail(book, "lala");

            bookDetailRepository.save(bookDetail);

            return null;
        });
    }

    @Test
    public void testAddNewFormatForBook() {
        String bookId = executeInTransaction(() -> {
            LibBook book = new LibBook("123", "java", LocalDate.now());
            LibBookDetail bookDetail = new LibBookDetail(book, "learning java");

            LibAuthor author = new LibAuthor("ion");
            LibBookFormat bookFormat = new LibBookFormat("pdf");

            book.addAuthor(author);
            book.addBookFormat(bookFormat);

            bookRepository.save(book);
            bookDetailRepository.save(bookDetail);

            return book.getId();
        });

        executeInTransaction(() -> {

            LibBook book = bookRepository.getReferenceById(bookId);
            LibBookFormat bookFormat = new LibBookFormat("epub");

            bookFormat.setBook(book);
            bookFormatRepository.save(bookFormat);

            return null;
        });
    }

    @Test
    public void testUpdateBook() {
        String bookId = executeInTransaction(() -> {
            LibBook book = new LibBook("123", "java", LocalDate.now());
            LibBookDetail bookDetail = new LibBookDetail(book, "learning java");

            LibAuthor author = new LibAuthor("ion");
            LibBookFormat bookFormat = new LibBookFormat("pdf");

            book.addAuthor(author);
            book.addBookFormat(bookFormat);

            bookRepository.save(book);
            bookDetailRepository.save(bookDetail);

            return book.getId();
        });

        executeInTransaction(() -> {

            LibBook book = bookRepository.getReferenceById(bookId);
            book.setTitle("new title");
            bookRepository.save(book);

            return null;
        });
    }

    @Test
    public void testDeleteBook() {
        String bookId = executeInTransaction(() -> {
            LibBook book = new LibBook("123", "java", LocalDate.now());
            LibBookDetail bookDetail = new LibBookDetail(book, "learning java");

            LibAuthor author = new LibAuthor("ion");
            LibBookFormat bookFormat = new LibBookFormat("pdf");

            book.addAuthor(author);
            book.addBookFormat(bookFormat);

            bookRepository.save(book);
            bookDetailRepository.save(bookDetail);

            return book.getId();
        });

        executeInTransaction(() -> {

            LibBook book = bookRepository.findById(bookId).get();
            LibBookDetail bookDetail = bookDetailRepository.findById(bookId).get();
            bookDetailRepository.delete(bookDetail);
            bookRepository.delete(book);

            return null;

        });
    }

    @Test
    public void testDeleteAuthor() {
        Long id = executeInTransaction(() -> {
            LibBook book = new LibBook(getBookIsbn(), "java", LocalDate.now());
            LibBookDetail bookDetail = new LibBookDetail(book, "learning java");

            LibAuthor author = new LibAuthor("ion");
            LibBookFormat bookFormat = new LibBookFormat("pdf");

            book.addAuthor(author);
            book.addBookFormat(bookFormat);

            entityManager.persist(book);
            entityManager.persist(bookDetail);

            return author.getId();
        });

        executeInTransaction(() -> {

            LibAuthor author = authorRepository.findById(id).get();
            Set<LibBook> books = author.getBooks();
            books.forEach(b -> b.getAuthors().remove(author));

            bookRepository.saveAll(books);
            authorRepository.delete(author);

            return null;

        });
    }

    @Test
    public void testDeleteBookDetails() {
        String id = executeInTransaction(() -> {
            LibBook book = new LibBook(getBookIsbn(), "java", LocalDate.now());
            LibBookDetail bookDetail = new LibBookDetail(book, "learning java");

            LibAuthor author = new LibAuthor("ion");
            LibBookFormat bookFormat = new LibBookFormat("pdf");

            book.addAuthor(author);
            book.addBookFormat(bookFormat);

            entityManager.persist(book);
            entityManager.persist(bookDetail);

            return book.getId();
        });

        executeInTransaction(() -> {

            LibBookDetail bookDetail = bookDetailRepository.findById(id).get();
            bookDetailRepository.delete(bookDetail);

            return null;

        });
    }

    @Test
    public void testDeleteBookFormat() {
        Long id = executeInTransaction(() -> {
            LibBook book = new LibBook(getBookIsbn(), "java", LocalDate.now());
            LibBookDetail bookDetail = new LibBookDetail(book, "learning java");

            LibAuthor author = new LibAuthor("ion");
            LibBookFormat bookFormat = new LibBookFormat("pdf");

            book.addAuthor(author);
            book.addBookFormat(bookFormat);

            entityManager.persist(book);
            entityManager.persist(bookDetail);

            return bookFormat.getId();
        });

        executeInTransaction(() -> {

           LibBookFormat bookFormat=bookFormatRepository.findById(id).get();
           bookFormatRepository.delete(bookFormat);

            return null;

        });
    }

    private String getBookIsbn() {
        return UUID.randomUUID().toString();
    }
}
