package step6;

import java.util.Date;

public class BorrowTransaction {
    public String transactionId;
    public String bookIsbn;
    public String userId;
    public Date borrowDate;
    public Date returnDate;
    public boolean isReturned;

    public BorrowTransaction(String transactionId, String bookIsbn, String userId) {
        this.transactionId = transactionId;
        this.bookIsbn = bookIsbn;
        this.userId = userId;
        this.borrowDate = new Date();
        this.returnDate = null;
        this.isReturned = false;
    }

    public void markAsReturned() {
        this.returnDate = new Date();
        this.isReturned = true;
    }

    public String toString() {
        return "Book " + bookIsbn + " borrowed by " + userId + (isReturned ? " - Returned" : " - Active");
    }
}
