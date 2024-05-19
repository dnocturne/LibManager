package lt.kvk.library.management.models;

public class User {
	private String name;
	private String email;

	public User(String name, String email) {
		this.name = name;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null || !email.contains("@")) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}
}
