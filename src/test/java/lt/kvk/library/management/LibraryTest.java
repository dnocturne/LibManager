package lt.kvk.library.management;

import lt.kvk.library.management.models.Author;
import lt.kvk.library.management.models.Book;
import lt.kvk.library.management.models.BookGenre;
import lt.kvk.library.management.models.Borrowable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
	private Library library;

	@BeforeEach
	public void setUp() {
		library = new Library();
		Author author1 = new Author("J.K. Rowling");
		Author author2 = new Author("J.R.R. Tolkien");
		library.addBook(new Book("Harry Potter", author1, BookGenre.FICTION));
		library.addBook(new Book("The Hobbit", author2, BookGenre.FANTASY));
	}

	@Test
	public void testSearchBooksByTitle() {
		List<Book> results = library.searchBooksByTitle("Harry Potter");
		assertEquals(1, results.size());
		assertEquals("Harry Potter", results.get(0).getTitle());
	}

	@Test
	public void testSearchBooksByAuthor() {
		List<Book> results = library.searchBooksByAuthor("J.R.R. Tolkien");
		assertEquals(1, results.size());
		assertEquals("The Hobbit", results.get(0).getTitle());
	}

	@Test
	public void testFilterBooksByGenre() {
		List<Book> results = library.filterBooksByGenre(BookGenre.FANTASY);
		assertEquals(1, results.size());
		assertEquals("The Hobbit", results.get(0).getTitle());
	}

	@Test
	public void testBorrowable() {
		Book book = library.searchBooksByTitle("Harry Potter").get(0);
		assertFalse(book.isBorrowed());

		book.borrowItem();
		assertTrue(book.isBorrowed());

		book.returnItem();
		assertFalse(book.isBorrowed());
	}

	@Test
	public void testBorrowableInterface() {
		Borrowable borrowable = library.searchBooksByTitle("Harry Potter").get(0);
		assertFalse(borrowable.isBorrowed());

		borrowable.borrowItem();
		assertTrue(borrowable.isBorrowed());

		borrowable.returnItem();
		assertFalse(borrowable.isBorrowed());
	}
}