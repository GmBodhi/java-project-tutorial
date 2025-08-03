package step6;

import java.sql.*;
import java.util.*;

public class SimpleLibrary {
    public HashMap<String, Book> books = new HashMap<>();
    public HashMap<String, User> users = new HashMap<>();
    public HashMap<String, BorrowTransaction> transactions = new HashMap<>();
    public int transactionCounter = 1;
    public boolean useDatabase = false;
    public Connection connection;

    public SimpleLibrary() {
        initializeDatabase();
    }

    public void initializeDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "pass");
            createTables();
            useDatabase = true;
            loadDataFromDatabase();
        } catch (Exception e) {
            useDatabase = false;
        }
    }

    public void createTables() throws SQLException {
        String[] sql = {
                "CREATE TABLE IF NOT EXISTS books (isbn VARCHAR(50) PRIMARY KEY, title VARCHAR(255), author VARCHAR(255), status VARCHAR(20))",
                "CREATE TABLE IF NOT EXISTS users (user_id VARCHAR(50) PRIMARY KEY, name VARCHAR(255))",
                "CREATE TABLE IF NOT EXISTS transactions (transaction_id VARCHAR(50) PRIMARY KEY, book_isbn VARCHAR(50), user_id VARCHAR(50), borrow_date VARCHAR(50), return_date VARCHAR(50), is_returned INT DEFAULT 0)"
        };
        for (String s : sql) connection.createStatement().execute(s);
    }

    public void addBook(Book book) {
        books.put(book.isbn, book);
        if (useDatabase) {
            try {
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO books (isbn, title, author, status) VALUES (?, ?, ?, ?)");
                stmt.setString(1, book.isbn);
                stmt.setString(2, book.title);
                stmt.setString(3, book.author);
                stmt.setString(4, book.status.toString());
                stmt.executeUpdate();
            } catch (SQLException ignored) {}
        }
    }

    public void addUser(User user) {
        users.put(user.userId, user);
        if (useDatabase) {
            try {
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO users (user_id, name) VALUES (?, ?)");
                stmt.setString(1, user.userId);
                stmt.setString(2, user.name);
                stmt.executeUpdate();
            } catch (SQLException ignored) {}
        }
    }

    public boolean borrowBook(String isbn, String userId) {
        Book book = books.get(isbn);
        User user = users.get(userId);
        if (book == null || user == null || !book.isAvailable() || !user.canBorrowMoreBooks()) return false;

        String transactionId = "T" + transactionCounter++;
        BorrowTransaction transaction = new BorrowTransaction(transactionId, isbn, userId);

        book.status = BookStatus.BORROWED;
        user.borrowBook(isbn);
        transactions.put(transactionId, transaction);

        if (useDatabase) {
            try {
                connection.prepareStatement("UPDATE books SET status = 'BORROWED' WHERE isbn = '" + isbn + "'").executeUpdate();
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO transactions (transaction_id, book_isbn, user_id, borrow_date, is_returned) VALUES (?, ?, ?, ?, 0)");
                stmt.setString(1, transactionId);
                stmt.setString(2, isbn);
                stmt.setString(3, userId);
                stmt.setString(4, transaction.borrowDate.toString());
                stmt.executeUpdate();
            } catch (SQLException ignored) {}
        }
        return true;
    }

    public boolean returnBook(String isbn, String userId) {
        Book book = books.get(isbn);
        User user = users.get(userId);
        if (book == null || user == null || book.isAvailable() || !user.hasBorrowedBook(isbn)) return false;

        for (BorrowTransaction t : transactions.values()) {
            if (t.bookIsbn.equals(isbn) && t.userId.equals(userId) && !t.isReturned) {
                book.status = BookStatus.AVAILABLE;
                user.returnBook(isbn);
                t.markAsReturned();

                if (useDatabase) {
                    try {
                        connection.prepareStatement("UPDATE books SET status = 'AVAILABLE' WHERE isbn = '" + isbn + "'").executeUpdate();
                        PreparedStatement stmt = connection.prepareStatement("UPDATE transactions SET return_date = ?, is_returned = 1 WHERE transaction_id = ?");
                        stmt.setString(1, t.returnDate.toString());
                        stmt.setString(2, t.transactionId);
                        stmt.executeUpdate();
                    } catch (SQLException ignored) {}
                }
                return true;
            }
        }
        return false;
    }

    public void printStatus() {
        System.out.println("Storage: " + (useDatabase ? "Database" : "Memory") + " | Books: " + books.size() + ", Users: " + users.size() + ", Transactions: " + transactions.size());
    }

    public void close() {
        if (connection != null) {
            try { connection.close(); } catch (SQLException ignored) {}
        }
    }

    public void loadDataFromDatabase() {
        if (!useDatabase) return;
        try {
            // Load books
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM books");
            while (rs.next()) {
                Book book = new Book(rs.getString("isbn"), rs.getString("title"), rs.getString("author"));
                book.status = BookStatus.valueOf(rs.getString("status"));
                books.put(book.isbn, book);
            }

            // Load users
            rs = connection.createStatement().executeQuery("SELECT * FROM users");
            while (rs.next()) {
                users.put(rs.getString("user_id"), new User(rs.getString("user_id"), rs.getString("name")));
            }

            // Load user's borrowed books
            rs = connection.createStatement().executeQuery("SELECT user_id, book_isbn FROM transactions WHERE is_returned = 0");
            while (rs.next()) {
                User user = users.get(rs.getString("user_id"));
                if (user != null) user.borrowedBooks.add(rs.getString("book_isbn"));
            }

            // Load transactions
            rs = connection.createStatement().executeQuery("SELECT * FROM transactions");
            while (rs.next()) {
                BorrowTransaction transaction = new BorrowTransaction(rs.getString("transaction_id"), rs.getString("book_isbn"), rs.getString("user_id"));
                if (rs.getInt("is_returned") == 1) transaction.markAsReturned();
                transactions.put(transaction.transactionId, transaction);
            }

            // Update transaction counter
            int maxCounter = transactions.keySet().stream()
                    .filter(id -> id.startsWith("T"))
                    .mapToInt(id -> { try { return Integer.parseInt(id.substring(1)); } catch (NumberFormatException e) { return 0; } })
                    .max().orElse(0);
            transactionCounter = maxCounter + 1;

        } catch (SQLException ignored) {}
    }
}