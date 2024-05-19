package lt.kvk.library.management.models;

public abstract class LibraryItem {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public abstract void borrowItem();

	public abstract void returnItem();

	public abstract boolean isBorrowed();
}
