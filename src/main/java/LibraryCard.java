
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Library Card associated with the Student 
 */
public class LibraryCard {
    /**
     * Card id 
     */
    private int ID;

    /**
     * Issue Date of the Card
     */
    private Date IssueDate;

    /**
     * Expiry Date of the Card
     */
    private Date ExpiryDate;

    /**
     * Number of books borrowed
     */
    private List<Book> borrowed = new ArrayList<Book>();

    /**
     * Fine asscoaited with the card
     */
    private double fine;

    /**
     *  Details about the cardholder
     */
    private Student student;




    public LibraryCard(Student student, Date IssueDate, Date ExpiryDate, int ID) {
        this.student = student;
        this.IssueDate = IssueDate;
	   this.ExpiryDate = ExpiryDate;
	   this.ID = ID;
    }


    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }


    public Date getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(Date IssueDate) {
        this.IssueDate = IssueDate;
    }

    public Date getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(Date ExpiryDate) {
        this.ExpiryDate = ExpiryDate;
    }

    
    public List<Book> getBooks() {
        return borrowed;
    }

    

    /**
     * Issue a new book
     * @param Book: book to borrow 
     * @return true if the book is successfully borrowed, false otherwise
     */
    public boolean issueBook(Book book) {
        Date currentDate = new Date();
    
        // Check if the library card has expired
        if (currentDate.after(ExpiryDate)) {
            return false;
        }
    
        // Check if the card has any pending fines
        if (fine > 0.0) {
            return false;
        }
    
        // Check if the book is available
        if (!book.getStatus()) {
            return false;
        }
    
        // Check if the student has already borrowed the same book
        if (borrowed.contains(book)) {
            return false;
        }
    
        // Check if the student has reached the maximum limit of borrowed books
        if (borrowed.size() >= 3) { // You can adjust this limit based on your requirements
            return false;
        }
    
        // Issue the book and update its status
        borrowed.add(book);
        book.setStatus(false);
    
        // Update the due date for the book based on its demand
        int daysToBorrow = book.getDemand() == 1 ? 3 : 15; // You can adjust these values
        book.setDays(daysToBorrow);
    
        return true;
    }




}
