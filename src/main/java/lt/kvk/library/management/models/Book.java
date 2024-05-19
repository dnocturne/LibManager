package lt.kvk.library.management.models;

public class Book extends LibraryItem {
	private String title;
	private Author author;
	private BookGenre genre;

	public Book(String title, Author author, BookGenre genre) {
		this.title = title;
		this.author = author;
		this.genre = genre;
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
}
