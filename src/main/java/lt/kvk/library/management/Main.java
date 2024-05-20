package lt.kvk.library.management;

import lt.kvk.library.management.models.Author;
import lt.kvk.library.management.models.Book;
import lt.kvk.library.management.models.BookGenre;
import lt.kvk.library.management.utils.FileUtils;

import java.io.IOException;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		Library library = new Library();

		try {
			// Pradinių duomenų skaitymas iš failų
			List<Book> books = FileUtils.readBooksFromFile();
			List<Author> authors = FileUtils.readAuthorsFromFile();

			// Pridėkime knygas ir autorius į biblioteką
			books.forEach(library::addBook);
			authors.forEach(library::addAuthor);

			// Parodyti visus skaitytus knygos duomenis
			for (Book book : books) {
				System.out.println("Knygos pavadinimas: " + book.getTitle());
				System.out.println("Autorius: " + book.getAuthor().getName());
				System.out.println("Žanras: " + book.getGenre().getGenreName());
				System.out.println();
			}

			// Naujos knygos ir autoriaus pridėjimas
			Author newAuthor = new Author("George Orwell");
			Book newBook = new Book("1984", newAuthor, BookGenre.SCI_FI); // Naudojame enum konstantą

			library.addBook(newBook);
			library.addAuthor(newAuthor);

			// Paieškos testas
			System.out.println("Ieškome '1984':");
			List<Book> searchResults = library.searchBooksByTitle("1984");
			for (Book book : searchResults) {
				System.out.println("Knygos pavadinimas: " + book.getTitle());
				System.out.println("Autorius: " + book.getAuthor().getName());
				System.out.println("Žanras: " + book.getGenre().getGenreName());
				System.out.println();
			}

			// Filtravimo testas
			System.out.println("Filtruojam knygas pagal žanrą SCI_FI:");
			List<Book> filterResults = library.filterBooksByGenre(BookGenre.SCI_FI);
			for (Book book : filterResults) {
				System.out.println("Knygos pavadinimas: " + book.getTitle());
				System.out.println("Autorius: " + book.getAuthor().getName());
				System.out.println("Žanras: " + book.getGenre().getGenreName());
				System.out.println();
			}

			// Borrowable testas
			Book bookToBorrow = library.searchBooksByTitle("1984").get(0);
			bookToBorrow.borrowItem();
			System.out.println("Ar '1984' yra borrowed? " + bookToBorrow.isBorrowed());
			bookToBorrow.returnItem();
			System.out.println("Ar '1984' yra borrowed? " + bookToBorrow.isBorrowed());

			// Įrašymas į failus
			FileUtils.writeBooksToFile(books);
			FileUtils.writeAuthorsToFile(authors);

		} catch (IOException e) {
			System.err.println("Klaida skaitant ar įrašant į failą: " + e.getMessage());
			e.printStackTrace();
		}
	}
}