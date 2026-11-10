package library;
import java.time.LocalDate;
public class BorrowRecord {
    private int id;
    private int bookId;
    private int memberId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean returned;
    public BorrowRecord(int id, int bookId, int memberId) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = LocalDate.now();
        this.returned = false;
    }
    public int getId() {
        return id;
    }
    public int getBookId() {
        return bookId;
    }
    public int getMemberId() {
        return memberId;
    }
    public LocalDate getBorrowDate() {
        return borrowDate;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public boolean isReturned() {
        return returned;
    }
    public void markReturned() {
        if (returned) {
            throw new LibraryException(
                    "This book has already been returned."
            );
        }
        returned = true;
        returnDate = LocalDate.now();
    }
    @Override
    public String toString() {
        String returnStatus;
        if (returned) {
            returnStatus = "Yes";
        } else {
            returnStatus = "No";
        }
        String date;
        if (returnDate == null) {
            date = "-";
        } else {
            date = returnDate.toString();
        }
        return "Record ID: " + id +
                " | Book ID: " + bookId +
                " | Member ID: " + memberId +
                " | Borrow Date: " + borrowDate +
                " | Returned: " + returnStatus +
                " | Return Date: " + date;
    }
}