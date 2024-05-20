package lt.kvk.library.management.models;

public abstract class LibraryItem {

	public abstract void borrowItem();

	public abstract void returnItem();

	public abstract boolean isBorrowed();
}
