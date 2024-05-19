package lt.kvk.library.management;

import lt.kvk.library.management.models.Author;
import lt.kvk.library.management.models.Book;
import lt.kvk.library.management.models.BookGenre;
import lt.kvk.library.management.utils.FileUtils;

import java.io.IOException;
import java.util.List;

class Main {
	public static void main(String[] args) {
		Library library = new Library();
		String booksFilePath = "src/books.txt";
		String authorsFilePath = "src/authors.txt";

		try {
			// Pradinių duomenų skaitymas iš failų
			List<Book> books = FileUtils.readBooksFromFile(booksFilePath);
			List<Author> authors = FileUtils.readAuthorsFromFile(authorsFilePath);

			// Pridėkime knygas ir autorius į biblioteką
			books.forEach(library::addBook);
			authors.forEach(library::addAuthor);

			// Parodyti visus skaitytus knygos duomenis
			for (Book book : books) {
				System.out.println("Book title: " + book.getTitle());
				System.out.println("Author: " + book.getAuthor().getName());
				System.out.println("Genre: " + book.getGenre().getGenreName());
				System.out.println();
			}

			// Naujos knygos ir autoriaus pridėjimas
			Author newAuthor = new Author("George Orwell");
			Book newBook = new Book("1984", newAuthor, BookGenre.SCI_FI);

			library.addBook(newBook);
			library.addAuthor(newAuthor);

			// Paieškos testas
			System.out.println("Search for '1984':");
			List<Book> searchResults = library.searchBooksByTitle("1984");
			for (Book book : searchResults) {
				System.out.println("Book title: " + book.getTitle());
				System.out.println("Author: " + book.getAuthor().getName());
				System.out.println("Genre: " + book.getGenre().getGenreName());
				System.out.println();
			}

			// Filtravimo testas
			System.out.println("Filter books by genre SCI_FI:");
			List<Book> filterResults = library.filterBooksByGenre(BookGenre.SCI_FI);
			for (Book book : filterResults) {
				System.out.println("Book title: " + book.getTitle());
				System.out.println("Author: " + book.getAuthor().getName());
				System.out.println("Genre: " + book.getGenre().getGenreName());
				System.out.println();
			}

			// Įrašymas į failus
			FileUtils.writeBooksToFile(books, booksFilePath);
			FileUtils.writeAuthorsToFile(authors, authorsFilePath);

		} catch (IOException e) {
			System.err.println("Error reading or writing to file: " + e.getMessage());
			e.printStackTrace();
		}
	}
}}