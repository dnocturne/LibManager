package lt.kvk.library.management.models;

public interface Borrowable {
	void borrowItem();

	void returnItem();

	boolean isBorrowed();
}
