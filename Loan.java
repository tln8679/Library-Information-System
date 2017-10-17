
public class Loan {
	public Loan(String patronId, String bookId, String date) {
		super();
		this.patronId = patronId;
		this.bookId = bookId;
		this.date = date;
	}
	
	private String patronId;
	private String bookId;
	public String getPatronId() {
		return patronId;
	}

	public void setPatronId(String patronId) {
		this.patronId = patronId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	private String date;
	
	@Override
	public String toString() {
		return "Loan [Patron Id= " + patronId + ", Book Id= " + bookId + ", Date= " + date + "]";
	}

	
	
}
