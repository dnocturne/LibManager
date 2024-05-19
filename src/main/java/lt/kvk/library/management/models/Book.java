package lt.kvk.library.management.models;

public class Book extends LibraryItem implements Borrowable {
	private String title;
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

	public void setTitle(String title) {
		if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("Title cannot be null or empty");
		}
		this.title = title;
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
			System.out.println(title + " has been borrowed.");
		} else {
			System.out.println(title + " is already borrowed.");
		}
	}

	@Override
	public void returnItem() {
		if (borrowed) {
			borrowed = false;
			System.out.println(title + " has been returned.");
		} else {
			System.out.println(title + " was not borrowed.");
		}
	}

	@Override
	public boolean isBorrowed() {
		return borrowed;
	}
}
