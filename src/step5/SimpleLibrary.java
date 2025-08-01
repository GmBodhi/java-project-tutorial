package step5;

import java.util.*;

public class SimpleLibrary {
    private HashMap<String, Book> books;
    private HashMap<String, User> users;
    private HashMap<String, BorrowTransaction> transactions;
    private int transactionCounter;

    public SimpleLibrary() {
        this.books = new HashMap<>();
        this.users = new HashMap<>();
        this.transactions = new HashMap<>();
        this.transactionCounter = 1;
    }

    public boolean addBook(Book book) {
        if (book == null) return false;

        books.put(book.getIsbn(), book);
        return true;
    }

    public boolean updateBook(String isbn, String newTitle, String newAuthor) {
        Book book = books.get(isbn);
        if (book == null) return false;

        book.setTitle(newTitle);
        book.setAuthor(newAuthor);

        return true;
    }

    public boolean removeBook(String isbn) {
        Book book = books.get(isbn);
        if (book == null) return false;
        if (book.getStatus() == BookStatus.BORROWED) return false;

        books.remove(isbn);
        return true;
    }

    public boolean addUser(User user) {
        if (user == null) return false;

        users.put(user.getUserId(), user);
        return true;
    }

    public boolean updateUser(String userId, String newName) {
        User user = users.get(userId);
        if (user == null) return false;

        user.setName(newName);

        return true;
    }

    public boolean removeUser(String userId) {
        User user = users.get(userId);
        if (user == null) return false;
        if (user.getBorrowedBooksCount() > 0) return false;

        users.remove(userId);
        return true;
    }

    public String borrowBook(String isbn, String userId) {
        Book book = getBook(isbn);
        User user = getUser(userId);

        if (book == null) return "Book not found";
        if (user == null) return "User not found";
        if (!book.isAvailable()) return "Book is not available";
        if (!user.canBorrowMoreBooks()) return "User has reached maximum book limit";

        String transactionId = "T" + String.format("%03d", transactionCounter++);
        BorrowTransaction transaction = new BorrowTransaction(transactionId, isbn, userId);

        book.setStatus(BookStatus.BORROWED);
        user.borrowBook(isbn);

        transactions.put(transactionId, transaction);
        return "Success! Transaction ID: " + transactionId;
    }

    public String returnBook(String isbn, String userId) {
        Book book = getBook(isbn);
        User user = getUser(userId);

        if (book == null) return "Book not found";
        if (user == null) return "User not found";
        if (book.isAvailable()) return "Book is not currently borrowed";
        if (!user.hasBorrowedBook(isbn)) return "User has not borrowed this book";

        BorrowTransaction transaction = null;
        for (BorrowTransaction t : transactions.values()) {
            if (t.getBookIsbn().equals(isbn) && t.getUserId().equals(userId) && !t.isReturned()) {
                transaction = t;
                break;
            }
        }

        if (transaction == null) return "No active transaction found";

        book.setStatus(BookStatus.AVAILABLE);
        user.returnBook(isbn);
        transaction.markAsReturned();

        return "Book returned successfully";
    }

    public Book getBook(String isbn) {
        return books.get(isbn);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public List<BorrowTransaction> getAllTransactions() {
        return new ArrayList<>(transactions.values());
    }

    public void printStatus() {
        System.out.println("\n=== Library Status ===");
        System.out.println("Storage: " + "In-Memory");
        System.out.println("Books: " + books.size());
        System.out.println("Users: " + users.size());
        System.out.println("Transactions: " + transactions.size());
    }

}
