package step6;

import java.sql.*;
import java.util.*;

public class SimpleLibrary {
    public HashMap<String, Book> books;
    public HashMap<String, User> users;
    public HashMap<String, BorrowTransaction> transactions;
    public int transactionCounter;
    public boolean useDatabase;
    public Connection connection;

    public SimpleLibrary() {
        this.books = new HashMap<>();
        this.users = new HashMap<>();
        this.transactions = new HashMap<>();
        this.transactionCounter = 1;
        this.useDatabase = false;
        initializeDatabase();
    }

    public void initializeDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/library_db";
            String username = "root";
            String password = "pass";
            
            connection = DriverManager.getConnection(url, username, password);
            createTables();
            useDatabase = true;
            System.out.println("Database connected");
        } catch (Exception e) {
            System.out.println("Database not available: " + e.getMessage());
            useDatabase = false;
        }
    }

    public void createTables() throws SQLException {
        String[] sql = {
            "CREATE TABLE IF NOT EXISTS books (isbn VARCHAR(50) PRIMARY KEY, title VARCHAR(255), author VARCHAR(255), status VARCHAR(20))",
            "CREATE TABLE IF NOT EXISTS users (user_id VARCHAR(50) PRIMARY KEY, name VARCHAR(255))",
            "CREATE TABLE IF NOT EXISTS transactions (transaction_id VARCHAR(50) PRIMARY KEY, book_isbn VARCHAR(50), user_id VARCHAR(50), borrow_date VARCHAR(50), return_date VARCHAR(50), is_returned INT DEFAULT 0)"
        };
        
        for (String s : sql) {
            connection.createStatement().execute(s);
        }
    }

    public void addBook(Book book) {
        books.put(book.isbn, book);
        
        if (useDatabase) {
            try {
                String sql = "INSERT INTO books (isbn, title, author, status) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, book.isbn);
                stmt.setString(2, book.title);
                stmt.setString(3, book.author);
                stmt.setString(4, book.status.toString());
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }

    public void addUser(User user) {
        users.put(user.userId, user);
        
        if (useDatabase) {
            try {
                String sql = "INSERT INTO users (user_id, name) VALUES (?, ?)";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, user.userId);
                stmt.setString(2, user.name);
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
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
        
        if (useDatabase) {
            try {
                String updateBook = "UPDATE books SET status = 'BORROWED' WHERE isbn = ?";
                PreparedStatement stmt = connection.prepareStatement(updateBook);
                stmt.setString(1, isbn);
                stmt.executeUpdate();

                String insertTransaction = "INSERT INTO transactions (transaction_id, book_isbn, user_id, borrow_date, is_returned) VALUES (?, ?, ?, ?, 0)";
                stmt = connection.prepareStatement(insertTransaction);
                stmt.setString(1, transactionId);
                stmt.setString(2, isbn);
                stmt.setString(3, userId);
                stmt.setString(4, transaction.borrowDate.toString());
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
        
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
                
                if (useDatabase) {
                    try {
                        String updateBook = "UPDATE books SET status = 'AVAILABLE' WHERE isbn = ?";
                        PreparedStatement stmt = connection.prepareStatement(updateBook);
                        stmt.setString(1, isbn);
                        stmt.executeUpdate();

                        String updateTransaction = "UPDATE transactions SET return_date = ?, is_returned = 1 WHERE transaction_id = ?";
                        stmt = connection.prepareStatement(updateTransaction);
                        stmt.setString(1, t.returnDate.toString());
                        stmt.setString(2, t.transactionId);
                        stmt.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println("Database error: " + e.getMessage());
                    }
                }
                
                return true;
            }
        }
        return false;
    }

    public void printStatus() {
        String storage = useDatabase ? "Database" : "Memory";
        System.out.println("Storage: " + storage + " | Books: " + books.size() + ", Users: " + users.size() + ", Transactions: " + transactions.size());
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing database: " + e.getMessage());
            }
        }
    }
}
