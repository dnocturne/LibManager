package lt.kvk.library.management.models;

public enum BookGenre {
	FICTION("Fiction"),
	NON_FICTION("Non-fiction"),
	MYSTERY("Mystery"),
	SCI_FI("Science Fiction"),
	FANTASY("Fantasy");

	private final String genreName;

	BookGenre(String genreName) {
		this.genreName = genreName;
	}

	public String getGenreName() {
		return genreName;
	}
}
