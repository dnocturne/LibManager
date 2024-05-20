package lt.kvk.library.management;

import lt.kvk.library.management.models.Author;
import lt.kvk.library.management.models.Book;
import lt.kvk.library.management.models.BookGenre;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
	private final List<Book> books = new ArrayList<>();
	private final List<Author> authors = new ArrayList<>();

	public void addBook(Book book) {
		books.add(book);
	}

	public void addAuthor(Author author) {
		authors.add(author);
	}

	public List<Book> searchBooksByTitle(String title) {
		return books.stream()
				.filter(book -> book.getTitle().equalsIgnoreCase(title))
				.collect(Collectors.toList());
	}

	public List<Book> searchBooksByAuthor(String authorName) {
		return books.stream()
				.filter(book -> book.getAuthor().getName().equalsIgnoreCase(authorName))
				.collect(Collectors.toList());
	}

	public List<Book> filterBooksByGenre(BookGenre genre) {
		return books.stream()
				.filter(book -> book.getGenre() == genre)
				.collect(Collectors.toList());
	}

	public List<Book> sortBooksByTitle() {
		return books.stream()
				.sorted(Comparator.comparing(Book::getTitle))
				.collect(Collectors.toList());
	}

	public List<Book> sortBooksByAuthor() {
		return books.stream()
				.sorted(Comparator.comparing(book -> book.getAuthor().getName()))
				.collect(Collectors.toList());
	}
}
