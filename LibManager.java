import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.PrintWriter;
import java.io.IOException;

public class LibManager {
	private ArrayList<Book> bookList = new ArrayList<Book>();
	private ArrayList<Patron> patronList = new ArrayList<Patron>();
	private ArrayList<Loan> loanList = new ArrayList<Loan>();
	private String[] menuOptions;

	public static void main(String[] args) {
		LibManager lm = new LibManager();
		lm.execute();
	}

	public LibManager() {
		bookList = readBooks("Resources/books.txt");
		patronList = readPatrons("Resources/patrons.txt");
		loanList = readLoans("Resources/loans.txt");

		menuOptions = new String[] { "Add Book", "Add Patron", "List Books", "List Patrons", "List By Author",
				"List By Year", "Lend Book", "Return Book", "Show Borrowers", "Show Borrowed Books", "Remove Book", "Remove Patron",
				"Show Overdue Books", "Exit" };
	}

	private void execute() {
		while (true) {
			int opt = getMenuOption();
			switch (opt) {
			case 1:
				addBook();
				break;
			case 2:
				addPatron();
				break;
			case 3:
				listBooks();
				break;
			case 4:
				listPatrons();
				break;
			case 5:
				listByAuthor();
				break;
			case 6:
				listByYear();
				break;
			case 7:
				lendBookToPatron();
				break;
			case 8:
				returnBook();
				break;
			case 9:
				showBorrowers();
				break;
			case 10:
				showBorrowedBooks();
				break;
			case 11:
				removeBook();
				break;
			case 12:
				removePatron();
				break;
			case 13:
				showOverdueBooks();
				break;
			case 14:
				exitProgram();
				break;
			default:
				System.out.println("No such option");
			}
		}

	}
	

	private int getMenuOption() {

		System.out.println("Select one of the following options");
		for (int i = 0; i < menuOptions.length; i++) {
			System.out.println("\t" + (i + 1) + ". " + menuOptions[i]);
		}
		
		Scanner s = new Scanner(System.in);
		int choice = s.nextInt();
		
		return choice;
	}

	/* MAKE NO CHANGES ABOVE THIS LINE */
	/* COMPLETE ALL THE CODE STUBS BELOW */
	private String getDateTime() {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = new Date();
	    return dateFormat.format(date);
	}
	private String getDueDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOfOrder = new Date();
		int noOfDays = 14; //i.e two weeks
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateOfOrder);            
		calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
		Date dueDate = calendar.getTime();
		return dateFormat.format(dueDate);
	}

	private void exitProgram() {
		//TO DO Save the file
		System.out.println("Exiting..");
		
		final String BOOKSFILENAME = "Resources/books.txt";
		final String PATRONSFILENAME = "Resources/patrons.txt";
		final String LOANSFILENAME = "Resources/loans.txt";
		
		PrintWriter textStream;
		
//SAVES BOOKS
		try {
			textStream = new PrintWriter(BOOKSFILENAME);
			for (int i=0;i<bookList.size();i++) {
				
				String data = bookList.get(i).getIdentifier()+ " ; "+ bookList.get(i).getAuthor()+ " ; "+bookList.get(i).getTitle()+
						" ; "+bookList.get(i).getPublicationDate()+ "\n";
				textStream.print(data);
			}
			textStream.close();
		} catch (IOException e) {

			e.printStackTrace();
		} 
//SAVES PATRONS		
		try {
			textStream = new PrintWriter(PATRONSFILENAME);
			for (int i=0;i<patronList.size();i++) {
				
				String data = patronList.get(i).getUniqueId()+ "\t" +patronList.get(i).getName()+ "\n";
				
				textStream.print(data);
			}
			
			textStream.close();

		} catch (IOException e) {

			e.printStackTrace();

		} 
//SAVES LOANS		
		try {
			textStream = new PrintWriter(LOANSFILENAME);
			for (int i=0;i<loanList.size();i++) {
				String data = loanList.get(i).getBookId()+ ","+ loanList.get(i).getPatronId()+ "," +loanList.get(i).getDate()+ "\n";
				
				textStream.print(data);
			}
			

			
			textStream.close();


		} catch (IOException e) {

			e.printStackTrace();

		} 
		
		
		System.exit(0);
	
	}
	private ArrayList<Book> readBooks(String filename) {
		File inFile = new File(filename);
	    Scanner s = null;
	    ArrayList<Book> books = new ArrayList<Book>();
		try {
			FileInputStream fis = new FileInputStream(inFile);
			s = new Scanner(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	   while (s.hasNext()){
		   String[] tokens = s.nextLine().split(";");
		  
		   String id = tokens[0].trim();
		   String title = tokens [1].trim();
		   String author = tokens [2].trim();
		   String publication = tokens [3].trim();
		   
		   Book allBooks = new Book(id,title,author,publication);
		   books.add(allBooks);
	   }
	    
		return books;
	}

	private ArrayList<Patron> readPatrons(String filename) {
		File inFile = new File(filename);
	    Scanner spat = null;
	    ArrayList<Patron> patrons = new ArrayList<Patron>();
		try {
			FileInputStream fis = new FileInputStream(inFile);
			spat = new Scanner(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	   while (spat.hasNext()){
		   String[] tokens = spat.nextLine().split("\t");
		  
		   String id = tokens[0].trim();
		   String name = tokens [1].trim();		
		   //System.out.println(id+ name);
		   Patron allPatrons = new Patron(id,name);
		   patrons.add(allPatrons);
	   }
		return patrons;
	
	}

	private ArrayList<Loan> readLoans(String filename) {
		File inFile = new File(filename);
	    Scanner spat = null;
	    ArrayList<Loan> loans = new ArrayList<Loan>();
		try {
			FileInputStream fis = new FileInputStream(inFile);
			spat = new Scanner(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	   while (spat.hasNext()){
		   String[] tokens = spat.nextLine().split(",");
		  
		   String bookId = tokens[0].trim();
		   String patronId = tokens [1].trim();	
		   String date = tokens[2];
		   //System.out.println(id+ name);
		   Loan allLoans = new Loan(patronId, bookId, date);
		   loans.add(allLoans);
		   
	   }
	   
	    //System.out.println(loans.get(0).getBookId());
		return loans;
	}

	private Book locateBook(String bookId) {
		for (int i=0; i< bookList.size(); i++) {
			String bId = bookList.get(i).getIdentifier();
			  if (bId.equals(bookId)) {						  
				  return bookList.get(i);
			  }
		}
		return null;
	}

	private Patron locatePatron(String patronId) {
		for (int i=0; i< patronList.size(); i++) {
			String pId = patronList.get(i).getUniqueId();
			  if (pId.equals(patronId)) {			
				  return patronList.get(i);
			  }
		}
		return null;
		
	}

	private void showBorrowedBooks() {
		Scanner scanIt = new Scanner(System.in);
		System.out.println("Enter the patron ID: ");
		String patronId = scanIt.nextLine();
		for (int i=0; i< loanList.size(); i++) {
			for (int j=0; j<bookList.size(); j++) {
				if (loanList.get(i).getPatronId().equals(patronId)) {
					if(loanList.get(i).getBookId().equals(bookList.get(j).getIdentifier())) {
						System.out.println(bookList.get(j));
					}
				}
			}
	}
			}


	private void showBorrowers() {
		Scanner scanIt = new Scanner(System.in);
		System.out.println("Enter the book ID: ");
		String bookId = scanIt.nextLine();
		for (int i=0; i< loanList.size(); i++) {
			for (int j=0; j<patronList.size(); j++) {
				if (loanList.get(i).getBookId().equals(bookId)) {
					if(loanList.get(i).getPatronId().equals(patronList.get(j).getUniqueId())){
						System.out.println(patronList.get(j).getName());
					}
				}
					}
			
				}
			}
	

	private void showOverdueBooks() {
		for (int i=0; i< loanList.size(); i++) {
			for (int j=0; j<bookList.size(); j++) {
				if (bookList.get(j).getIdentifier().equals(loanList.get(i).getBookId())) {
					//date2 > date1, returns greater than 0
					if (loanList.get(i).getDate().compareTo(getDateTime()) > 0) {
						System.out.println(bookList.get(j));}
			
				}
			}
		}
	}

	private void lendBookToPatron() {
		boolean checkedOut = false;
		System.out.println("Enter the patron ID: ");
		Scanner scanIt = new Scanner(System.in);
		String patronId = scanIt.nextLine();
		Patron currentPatron = locatePatron(patronId);
		
		
		System.out.println("Enter the book ID: ");
		String bookId = scanIt.nextLine();
		Book bookWanted = locateBook(bookId);
		
			
		for(int i = 0; i < loanList.size(); i++) {
			if (bookWanted == null) {
				System.out.println("You entered an invalid book identity number.");
				break;}
			
			if (currentPatron == null) {
				System.out.println("You entered an invalid patron identity number.");
				break;}
			
			if(loanList.get(i).getBookId().equals(bookWanted.getIdentifier())){
				System.out.println("This book is already checked out.\n" );
				checkedOut = true;
			}
		}
		if (checkedOut == false && bookWanted != null && currentPatron != null){
			Loan newLoan = new Loan(currentPatron.getUniqueId(), bookWanted.getIdentifier(), getDueDate());
			   loanList.add(newLoan);
			   //System.out.println(loanList);
		}
		
	}

	private void returnBook() {
		Scanner scanIt = new Scanner(System.in);
		System.out.println("Enter the book ID: ");
		String bookId = scanIt.nextLine();
		Book bookReturning = locateBook(bookId);
		for(int i = 0; i < loanList.size(); i++) {
			if(loanList.get(i).getBookId().equals(bookReturning.getIdentifier())){
				loanList.remove(i);
				//System.out.println(loanList );
				
			}
		
	}}

	private void listByYear() {
		Scanner scanIt = new Scanner(System.in);
		System.out.println("Enter the maximum year: ");
		int maxYear = scanIt.nextInt();
		
		System.out.println("Enter the minimum year: ");
		int minYear = scanIt.nextInt();
		
		for (int i =0; i < bookList.size(); i++) {
			int year = Integer.parseInt(bookList.get(i).getPublicationDate());
			if (year <= maxYear && year >= minYear ) {
				System.out.println(bookList.get(i));
			}
		}
	}

	private void listByAuthor() {
		Scanner scanIt = new Scanner(System.in);
		System.out.println("Enter the author: ");
		String author = scanIt.nextLine();
		for (int i =0; i < bookList.size(); i++) {
			if (bookList.get(i).getAuthor().equals(author)) {
				System.out.println(bookList.get(i));
			}
		}
	}

	private void listPatrons() {
		for (int i =0; i < patronList.size(); i++) {
			System.out.println(patronList.get(i).getUniqueId()+":"+patronList.get(i).getName());
		}
	}

	private void listBooks() {
		for (int i =0; i < bookList.size(); i++) {
			System.out.println(bookList.get(i));}

	}

	private void addPatron() {
		Scanner scanIt = new Scanner(System.in);
		System.out.println("Enter patron's name: ");
		String newPatron = scanIt.nextLine();
		
		int max = 0;
		for(int i = 0; i< patronList.size()-1;i++) {
			String maxId= patronList.get(i).getUniqueId();
			String[] tokens = maxId.split("P");
			//System.out.println(tokens[1]);
			if(max < Integer.parseInt(tokens[1])) {
				max = Integer.parseInt(tokens[1]);
			}
			}
		String newId = "P"+(max+1);
		Patron newPat = new Patron(newId,newPatron);
		patronList.add(newPat);
		//System.out.println(patronList);
	}

	private void addBook() {
		Scanner scanIt = new Scanner(System.in);
		System.out.println("Enter the author: ");
		String newAuthor = scanIt.nextLine();
		
		System.out.println("Enter the book title: ");
		String newTitle = scanIt.nextLine();
		
		System.out.println("Enter the publication year: ");
		String newYear= scanIt.nextLine();
		
		
		int max = 0;
		for(int i = 0; i< bookList.size();i++) {
			String maxId= bookList.get(i).getIdentifier();
			String[] tokens = maxId.split("B");
			//System.out.println(tokens[1]);
			if(max < Integer.parseInt(tokens[1])) {
				max = Integer.parseInt(tokens[1]);
			}
			}
		String newId = "B" + (max+1);
		
		Book newBook = new Book(newId, newTitle, newAuthor, newYear);
		bookList.add(newBook);
		//System.out.println(bookList);
		

	}

	private void removePatron() {
		Scanner scanIt = new Scanner(System.in);
		System.out.println("Enter the patron ID: ");
		String patronId = scanIt.nextLine();

		patronList.remove(locatePatron(patronId));
		//System.out.println(patronList);

	}

	private void removeBook() {
		Scanner scanIt = new Scanner(System.in);
		System.out.println("Enter the book ID: ");
		String bookId = scanIt.nextLine();

		bookList.remove(locateBook(bookId));
		//System.out.println(bookList);
		

	}

}