package step6;

import java.util.Date;

public class BorrowTransaction {
    private String transactionId;
    private String bookIsbn;
    private String userId;
    private Date borrowDate;
    private Date returnDate;
    private boolean isReturned;

    public BorrowTransaction(String transactionId, String bookIsbn, String userId) {
        this.transactionId = transactionId;
        this.bookIsbn = bookIsbn;
        this.userId = userId;
        this.borrowDate = new Date();
        this.returnDate = null;
        this.isReturned = false;
    }


    public String getTransactionId() {
        return transactionId;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public String getUserId() {
        return userId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return isReturned;
    }


    public void markAsReturned() {
        this.returnDate = new Date();
        this.isReturned = true;
    }

    public String getStatus() {
        return isReturned ? "Returned" : "Active";
    }

    public String toString() {
        return "Transaction " + transactionId + ": Book " + bookIsbn + " borrowed by " + userId + " - " + getStatus();
    }

}
