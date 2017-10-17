
public class Book {
	private String identifier;
	private String title;
	private String author;
	private String publicationDate;
	
	public Book(String identifier, String title, String author, String publicationDate) {
		super();
		this.identifier = identifier;
		this.title = title;
		this.author = author;
		this.publicationDate = publicationDate;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getPublicationDate() {
		return publicationDate;
	}
	
	@Override
	public String toString() {
		return "Book [Identifier= " + identifier + ", Title= " + title + ", Author= " + author + ", Publication Date= "
				+ publicationDate + "]";
	}
	
}
