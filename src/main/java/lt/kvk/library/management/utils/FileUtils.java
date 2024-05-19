package lt.kvk.library.management.utils;

import lt.kvk.library.management.models.Author;
import lt.kvk.library.management.models.Book;
import lt.kvk.library.management.models.BookGenre;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	public static List<Book> readBooksFromFile(String filePath) throws IOException {
		List<Book> books = new ArrayList<>();
		try {
			List<String> lines = Files.readAllLines(Paths.get(filePath));

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
		} catch (NoSuchFileException e) {
			System.err.println("File not found: " + filePath);
			throw e;
		} catch (IOException e) {
			System.err.println("Error reading file: " + filePath);
			throw e;
		}
		return books;
	}

	public static void writeBooksToFile(List<Book> books, String filePath) throws IOException {
		List<String> lines = new ArrayList<>();
		for (Book book : books) {
			lines.add(book.getTitle() + "," + book.getAuthor().getName() + "," + book.getGenre().getGenreName());
		}
		try {
			Files.write(Paths.get(filePath), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			System.err.println("Error writing to file: " + filePath);
			throw e;
		}
	}

	public static List<Author> readAuthorsFromFile(String filePath) throws IOException {
		List<Author> authors = new ArrayList<>();
		try {
			List<String> lines = Files.readAllLines(Paths.get(filePath));

			for (String line : lines) {
				authors.add(new Author(line.trim()));
			}
		} catch (NoSuchFileException e) {
			System.err.println("File not found: " + filePath);
			throw e;
		} catch (IOException e) {
			System.err.println("Error reading file: " + filePath);
			throw e;
		}
		return authors;
	}

	public static void writeAuthorsToFile(List<Author> authors, String filePath) throws IOException {
		List<String> lines = new ArrayList<>();
		for (Author author : authors) {
			lines.add(author.getName());
		}
		try {
			Files.write(Paths.get(filePath), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			System.err.println("Error writing to file: " + filePath);
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

