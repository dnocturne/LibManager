package lt.kvk.library.management.models;

public class Author {
	private String name;

	public Author(String name) {
		this.name = name;
	}

	// Getteriai ir setteriai su tikrinimu
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Name cannot be null or empty");
		}
		this.name = name;
	}
}
