package lt.kvk.library.management.utils;

import lt.kvk.library.management.models.Author;
import lt.kvk.library.management.models.Book;
import lt.kvk.library.management.models.BookGenre;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	public static final String BOOKS_FILE_PATH = "src/books.txt";
	public static final String AUTHORS_FILE_PATH = "src/authors.txt";

	public static List<Book> readBooksFromFile() throws IOException {
		List<Book> books = new ArrayList<>();
		try {
			List<String> lines = Files.readAllLines(Paths.get(BOOKS_FILE_PATH));

			for (String line : lines) {
				String[] parts = line.split(",");
				if (parts.length == 3) {
					Author author = new Author(parts[1].trim());
					BookGenre genre = getBookGenreByName(parts[2].trim());
					if (genre != null) {
						Book book = new Book(parts[0].trim(), author, genre);
						books.add(book);
					} else {
						System.err.println("Invalid genre: " + parts[2].trim());
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading file: " + BOOKS_FILE_PATH);
			throw e;
		}
		return books;
	}

	public static void writeBooksToFile(List<Book> books) throws IOException {
		List<String> lines = new ArrayList<>();
		for (Book book : books) {
			lines.add(book.getTitle() + "," + book.getAuthor().getName() + "," + book.getGenre().getGenreName());
		}
		try {
			Files.write(Paths.get(BOOKS_FILE_PATH), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			System.err.println("Error writing to file: " + BOOKS_FILE_PATH);
			throw e;
		}
	}

	public static void appendBooksToFile(List<Book> books) throws IOException {
		List<String> lines = new ArrayList<>();
		for (Book book : books) {
			lines.add(book.getTitle() + "," + book.getAuthor().getName() + "," + book.getGenre().getGenreName());
		}
		try {
			Files.write(Paths.get(BOOKS_FILE_PATH), lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.err.println("Error writing to file: " + BOOKS_FILE_PATH);
			throw e;
		}
	}

	public static List<Author> readAuthorsFromFile() throws IOException {
		List<Author> authors = new ArrayList<>();
		try {
			List<String> lines = Files.readAllLines(Paths.get(AUTHORS_FILE_PATH));

			for (String line : lines) {
				authors.add(new Author(line.trim()));
			}
		} catch (IOException e) {
			System.err.println("Error reading file: " + AUTHORS_FILE_PATH);
			throw e;
		}
		return authors;
	}

	public static void writeAuthorsToFile(List<Author> authors) throws IOException {
		List<String> lines = new ArrayList<>();
		for (Author author : authors) {
			lines.add(author.getName());
		}
		try {
			Files.write(Paths.get(AUTHORS_FILE_PATH), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			System.err.println("Error writing to file: " + AUTHORS_FILE_PATH);
			throw e;
		}
	}

	public static void appendAuthorsToFile(List<Author> authors) throws IOException {
		List<String> lines = new ArrayList<>();
		for (Author author : authors) {
			lines.add(author.getName());
		}
		try {
			Files.write(Paths.get(AUTHORS_FILE_PATH), lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.err.println("Error writing to file: " + AUTHORS_FILE_PATH);
			throw e;
		}
	}

	private static BookGenre getBookGenreByName(String name) {
		for (BookGenre genre : BookGenre.values()) {
			if (genre.getGenreName().equalsIgnoreCase(name)) {
				return genre;
			}
		}
		return null;
	}
}

