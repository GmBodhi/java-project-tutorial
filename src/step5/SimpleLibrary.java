package step5;

import java.util.*;

public class SimpleLibrary {
    public HashMap<String, Book> books;
    public HashMap<String, User> users;
    public HashMap<String, BorrowTransaction> transactions;
    public int transactionCounter;

    public SimpleLibrary() {
        this.books = new HashMap<>();
        this.users = new HashMap<>();
        this.transactions = new HashMap<>();
        this.transactionCounter = 1;
    }

    public void addBook(Book book) {
        books.put(book.isbn, book);
    }

    public void addUser(User user) {
        users.put(user.userId, user);
    }

    public boolean borrowBook(String isbn, String userId) {
        Book book = books.get(isbn);
        User user = users.get(userId);

        if (book == null || user == null) return false;
        if (!book.isAvailable() || !user.canBorrowMoreBooks()) return false;

        String transactionId = "T" + transactionCounter++;
        BorrowTransaction transaction = new BorrowTransaction(transactionId, isbn, userId);

        book.status = BookStatus.BORROWED;
        user.borrowBook(isbn);
        transactions.put(transactionId, transaction);
        
        return true;
    }

    public boolean returnBook(String isbn, String userId) {
        Book book = books.get(isbn);
        User user = users.get(userId);

        if (book == null || user == null) return false;
        if (book.isAvailable() || !user.hasBorrowedBook(isbn)) return false;

        for (BorrowTransaction t : transactions.values()) {
            if (t.bookIsbn.equals(isbn) && t.userId.equals(userId) && !t.isReturned) {
                book.status = BookStatus.AVAILABLE;
                user.returnBook(isbn);
                t.markAsReturned();
                return true;
            }
        }
        return false;
    }

    public void printStatus() {
        System.out.println("Books: " + books.size() + ", Users: " + users.size() + ", Transactions: " + transactions.size());
    }
}
