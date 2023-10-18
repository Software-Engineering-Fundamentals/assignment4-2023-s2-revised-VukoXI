

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;

/**
 *  Implement and test {Programme.addStudent } that respects the considtion given the assignment specification
 * NOTE: You are expected to verify that the constraints to borrow a new book from a library
 *
 * Each test criteria must be in an independent test method .
 *
 * Initialize the test object with "setting" method.
 */

 public class IssueBook {

    private LibraryCard libraryCard;
    private Student student;

    @BeforeEach
    public void setUp() {
        // create the student
        student = new Student("stefan", 3948934);

        // create the library card with a future expiry date
        Date currentDate = new Date();
        Date futureDate = new Date(currentDate.getTime() + (1000L * 60 * 60 * 24 * 30)); // 30 days in the future
        libraryCard = new LibraryCard(student, currentDate, futureDate, 1);
    }

    @Test
    public void issueBook_ThreeBooksBorrowed_Successfully() {
        // Issue a few books to the library card
        for (int i = 1; i <= 3; i++) {
            Book book = new Book(i, "Book " + i, 0);
            libraryCard.issueBook(book);
        }

        //  check the size of the 'borrowed' list to get the number of books borrowed
        int numberOfBooksBorrowed = libraryCard.getBooks().size();

        // Verify that the number of books borrowed is 3
        assertEquals(3, numberOfBooksBorrowed);
    }
  
    @Test
    public void issueBook_FailsWhenExceedingMaximumLimit() {
        // Create 4 books to reach the maximum limit
        for (int i = 1; i <= 4; i++) {
            Book book = new Book(i, "Book " + i, 0);
            libraryCard.issueBook(book);
        }

        // Attempt to issue a fifth book
        Book fifthBook = new Book(5, "Book 5", 0);
        boolean result = libraryCard.issueBook(fifthBook);

        // Verify that the issuance fails due to exceeding the limit
        assertFalse(result);
    }

    @Test
    public void issueBook_SecondIssueFailsForSameBook() {
        // Create a book
        Book book = new Book(1, "Book 1", 1);
    
        // Issue the same book twice
        boolean firstIssue = libraryCard.issueBook(book);
        boolean secondIssue = libraryCard.issueBook(book);
    
        // Verify that the first issue is successful, but the second one fails
        assertTrue(firstIssue);
        assertFalse(secondIssue);
    }

    @Test
    public void issueBook_FailsWithExpiredLibraryCard() {
        // Set the library card's expiry date to the past
        Date pastDate = new Date(libraryCard.getExpiryDate().getTime() - (1000L * 60 * 60 * 24 * 30)); // 30 days in the past
        libraryCard.setExpiryDate(pastDate);

        // Create a book
        Book book = new Book(1, "Book 1", 0);

        // Attempt to issue the book with an expired card
        boolean result = libraryCard.issueBook(book);

        // Verify that it fails due to an expired card
        assertFalse(result);
    }

    @Test
    public void issueBook_FailsWhenBookNotAvailable() {
        // Create a book that's marked as not available
        Book book = new Book(1, "Book 1", 0);
        book.setStatus(false);
    
        // Attempt to issue the book
        boolean result = libraryCard.issueBook(book);
    
        // Verify that the issuance fails due to the book not being available
        assertFalse(result);
    }

    @Test
    public void testIssueBookWithPendingFine() {
        // Set a fine on the library card
        libraryCard.setFine(10.0);

        // Create a book
        Book book = new Book(1, "Book 1", 0);

        // Attempt to issue the book with a pending fine
        boolean result = libraryCard.issueBook(book);

        // Verify that the issuance fails due to a pending fine
        assertFalse(result);
    }
    

    @Test
    public void issueBook_HighDemandBookIssuedForThreeDays() {
        // Create a high-demand book
        Book highDemandBook = new Book(1, "High Demand Book", 1);
    
        // Issue the high-demand book
        boolean result = libraryCard.issueBook(highDemandBook);
    
        // Verify that the high-demand book was issued for 3 days
        assertTrue(result);
        assertEquals(3, highDemandBook.days());
    }
    
    @Test
    public void issueBook_LowDemandBookIssuedForFifteenDays() {
        // Create a low-demand book
        Book lowDemandBook = new Book(2, "Low Demand Book", 0);
    
        // Issue the low-demand book
        boolean result = libraryCard.issueBook(lowDemandBook);
    
        // Verify that the low-demand book was issued for 15 days
        assertTrue(result);
        assertEquals(15, lowDemandBook.days());
    }

 }

