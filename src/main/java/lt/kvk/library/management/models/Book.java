package lt.kvk.library.management.models;

public class Book extends LibraryItem implements Borrowable {
	private final String title;
	private Author author;
	private BookGenre genre;
	private boolean borrowed;

	public Book(String title, Author author, BookGenre genre) {
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.borrowed = false; // Inicializacija
	}

	// Getteriai ir setteriai su tikrinimu
	public String getTitle() {
		return title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public BookGenre getGenre() {
		return genre;
	}

	public void setGenre(BookGenre genre) {
		this.genre = genre;
	}

	@Override
	public void borrowItem() {
		if (!borrowed) {
			borrowed = true;
			System.out.println(title + " buvo borrowed.");
		} else {
			System.out.println(title + " jau yra borrowed.");
		}
	}

	@Override
	public void returnItem() {
		if (borrowed) {
			borrowed = false;
			System.out.println(title + " buvo grąžintas.");
		} else {
			System.out.println(title + " nebuvo borrowed.");
		}
	}

	@Override
	public boolean isBorrowed() {
		return borrowed;
	}
}
