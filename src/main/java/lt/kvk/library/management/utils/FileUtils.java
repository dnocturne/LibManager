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

	public static List<Book> readBooksFromFile(String filePath) throws IOException {
		List<Book> books = new ArrayList<>();
		List<String> lines = Files.readAllLines(Paths.get(filePath));

		for (String line : lines) {
			String[] parts = line.split(",");
			if (parts.length == 3) {
				Author author = new Author(parts[1].trim());
				BookGenre genre = BookGenre.valueOf(parts[2].trim().toUpperCase());
				Book book = new Book(parts[0].trim(), author, genre);
				books.add(book);
			}
		}
		return books;
	}

	public static void writeBooksToFile(List<Book> books, String filePath) throws IOException {
		List<String> lines = new ArrayList<>();
		for (Book book : books) {
			lines.add(book.getTitle() + "," + book.getAuthor().getName() + "," + book.getGenre().getGenreName());
		}
		Files.write(Paths.get(filePath), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}

	public static List<Author> readAuthorsFromFile(String filePath) throws IOException {
		List<Author> authors = new ArrayList<>();
		List<String> lines = Files.readAllLines(Paths.get(filePath));

		for (String line : lines) {
			authors.add(new Author(line.trim()));
		}
		return authors;
	}

	public static void writeAuthorsToFile(List<Author> authors, String filePath) throws IOException {
		List<String> lines = new ArrayList<>();
		for (Author author : authors) {
			lines.add(author.getName());
		}
		Files.write(Paths.get(filePath), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}
}
