package step5;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimpleLibrary {
    private HashMap<String, Book> books;
    private HashMap<String, User> users;
    private HashMap<String, BorrowTransaction> transactions;
    private int transactionCounter;
    private boolean useDatabase;
    private Connection connection;

    public SimpleLibrary() {
        this.books = new HashMap<>();
        this.users = new HashMap<>();
        this.transactions = new HashMap<>();
        this.transactionCounter = 1;
        this.useDatabase = false;
        initializeDatabase();
    }

    private void initializeDatabase() {
        try {
            // MySQL connection string - update these values for your MySQL setup
            String url = "jdbc:mysql://localhost:3306/library_db";
            String username = "root";  // Change to your MySQL username
            String password = "";      // Change to your MySQL password

            connection = DriverManager.getConnection(url, username, password);
            createTables();
            useDatabase = true;
            System.out.println("MySQL database connected successfully");
        } catch (SQLException e) {
            System.out.println("Database not available, using in-memory storage: " + e.getMessage());
            useDatabase = false;
        }
    }

    private void createTables() throws SQLException {
        String[] createStatements = {
                "CREATE TABLE IF NOT EXISTS books (isbn VARCHAR(50) PRIMARY KEY, title VARCHAR(255), author VARCHAR(255), status VARCHAR(20))",
                "CREATE TABLE IF NOT EXISTS users (user_id VARCHAR(50) PRIMARY KEY, name VARCHAR(255), borrowed_count INT DEFAULT 0)",
                "CREATE TABLE IF NOT EXISTS transactions (transaction_id VARCHAR(50) PRIMARY KEY, book_isbn VARCHAR(50), user_id VARCHAR(50), borrow_date VARCHAR(50), return_date VARCHAR(50), is_returned INT DEFAULT 0)"
        };

        for (String sql : createStatements) {
            connection.createStatement().execute(sql);
        }
    }

    public boolean addBook(Book book) {
        if (book == null) return false;

        if (useDatabase) {
            try {
                String sql = "INSERT INTO books (isbn, title, author, status) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, book.getIsbn());
                stmt.setString(2, book.getTitle());
                stmt.setString(3, book.getAuthor());
                stmt.setString(4, book.getStatus().toString());
                stmt.executeUpdate();
            } catch (SQLException e) {
                return false;
            }
        }

        books.put(book.getIsbn(), book);
        return true;
    }

    public boolean updateBook(String isbn, String newTitle, String newAuthor) {
        Book book = books.get(isbn);
        if (book == null) return false;

        book.setTitle(newTitle);
        book.setAuthor(newAuthor);

        if (useDatabase) {
            try {
                String sql = "UPDATE books SET title = ?, author = ? WHERE isbn = ?";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, newTitle);
                stmt.setString(2, newAuthor);
                stmt.setString(3, isbn);
                stmt.executeUpdate();
            } catch (SQLException e) {
                return false;
            }
        }

        return true;
    }

    public boolean removeBook(String isbn) {
        Book book = books.get(isbn);
        if (book == null) return false;
        if (book.getStatus() == BookStatus.BORROWED) return false;

        if (useDatabase) {
            try {
                String sql = "DELETE FROM books WHERE isbn = ?";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, isbn);
                stmt.executeUpdate();
            } catch (SQLException e) {
                return false;
            }
        }

        books.remove(isbn);
        return true;
    }

    public boolean addUser(User user) {
        if (user == null) return false;

        if (useDatabase) {
            try {
                String sql = "INSERT INTO users (user_id, name, borrowed_count) VALUES (?, ?, ?)";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, user.getUserId());
                stmt.setString(2, user.getName());
                stmt.setInt(3, user.getBorrowedBooksCount());
                stmt.executeUpdate();
            } catch (SQLException e) {
                return false;
            }
        }

        users.put(user.getUserId(), user);
        return true;
    }

    public boolean updateUser(String userId, String newName) {
        User user = users.get(userId);
        if (user == null) return false;

        user.setName(newName);

        if (useDatabase) {
            try {
                String sql = "UPDATE users SET name = ? WHERE user_id = ?";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, newName);
                stmt.setString(2, userId);
                stmt.executeUpdate();
            } catch (SQLException e) {
                return false;
            }
        }

        return true;
    }

    public boolean removeUser(String userId) {
        User user = users.get(userId);
        if (user == null) return false;
        if (user.getBorrowedBooksCount() > 0) return false;

        if (useDatabase) {
            try {
                String sql = "DELETE FROM users WHERE user_id = ?";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, userId);
                stmt.executeUpdate();
            } catch (SQLException e) {
                return false;
            }
        }

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

        if (useDatabase) {
            try {
                // Update book status
                String updateBookSql = "UPDATE books SET status = ? WHERE isbn = ?";
                PreparedStatement stmt = connection.prepareStatement(updateBookSql);
                stmt.setString(1, "BORROWED");
                stmt.setString(2, isbn);
                stmt.executeUpdate();

                // Update user borrowed count
                String updateUserSql = "UPDATE users SET borrowed_count = ? WHERE user_id = ?";
                stmt = connection.prepareStatement(updateUserSql);
                stmt.setInt(1, user.getBorrowedBooksCount());
                stmt.setString(2, userId);
                stmt.executeUpdate();

                // Insert transaction
                String insertTransactionSql = "INSERT INTO transactions (transaction_id, book_isbn, user_id, borrow_date, is_returned) VALUES (?, ?, ?, ?, ?)";
                stmt = connection.prepareStatement(insertTransactionSql);
                stmt.setString(1, transactionId);
                stmt.setString(2, isbn);
                stmt.setString(3, userId);
                stmt.setString(4, transaction.getBorrowDate().toString());
                stmt.setInt(5, 0);
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Database update failed: " + e.getMessage());
            }
        }

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

        if (useDatabase) {
            try {
                // Update book status
                String updateBookSql = "UPDATE books SET status = ? WHERE isbn = ?";
                PreparedStatement stmt = connection.prepareStatement(updateBookSql);
                stmt.setString(1, "AVAILABLE");
                stmt.setString(2, isbn);
                stmt.executeUpdate();

                // Update user borrowed count
                String updateUserSql = "UPDATE users SET borrowed_count = ? WHERE user_id = ?";
                stmt = connection.prepareStatement(updateUserSql);
                stmt.setInt(1, user.getBorrowedBooksCount());
                stmt.setString(2, userId);
                stmt.executeUpdate();

                // Update transaction
                String updateTransactionSql = "UPDATE transactions SET return_date = ?, is_returned = 1 WHERE transaction_id = ?";
                stmt = connection.prepareStatement(updateTransactionSql);
                stmt.setString(1, transaction.getReturnDate().toString());
                stmt.setString(2, transaction.getTransactionId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Database update failed: " + e.getMessage());
            }
        }

        return "Book returned successfully";
    }

    public Book getBook(String isbn) {
        return books.get(isbn);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public List<Book> getAllBooks() {
        if (useDatabase) {
            loadBooksFromDatabase();
        }
        return new ArrayList<>(books.values());
    }

    public List<User> getAllUsers() {
        if (useDatabase) {
            loadUsersFromDatabase();
        }
        return new ArrayList<>(users.values());
    }

    public List<BorrowTransaction> getAllTransactions() {
        if (useDatabase) {
            loadTransactionsFromDatabase();
        }
        return new ArrayList<>(transactions.values());
    }

    private void loadBooksFromDatabase() {
        try {
            String sql = "SELECT * FROM books";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            books.clear();
            while (rs.next()) {
                Book book = new Book(rs.getString("isbn"), rs.getString("title"), rs.getString("author"));
                book.setStatus(BookStatus.valueOf(rs.getString("status")));
                books.put(book.getIsbn(), book);
            }
        } catch (SQLException e) {
            System.out.println("Failed to load books from database: " + e.getMessage());
        }
    }

    private void loadUsersFromDatabase() {
        try {
            String sql = "SELECT * FROM users";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            users.clear();
            while (rs.next()) {
                User user = new User(rs.getString("user_id"), rs.getString("name"));
                users.put(user.getUserId(), user);
            }
        } catch (SQLException e) {
            System.out.println("Failed to load users from database: " + e.getMessage());
        }
    }

    private void loadTransactionsFromDatabase() {
        try {
            String sql = "SELECT * FROM transactions";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            transactions.clear();
            while (rs.next()) {
                BorrowTransaction transaction = new BorrowTransaction(
                        rs.getString("transaction_id"),
                        rs.getString("book_isbn"),
                        rs.getString("user_id")
                );
                if (rs.getInt("is_returned") == 1) {
                    transaction.markAsReturned();
                }
                transactions.put(transaction.getTransactionId(), transaction);
            }
        } catch (SQLException e) {
            System.out.println("Failed to load transactions from database: " + e.getMessage());
        }
    }

    public void printStatus() {
        System.out.println("\n=== Library Status ===");
        System.out.println("Storage: " + (useDatabase ? "Database" : "In-Memory"));
        System.out.println("Books: " + books.size());
        System.out.println("Users: " + users.size());
        System.out.println("Transactions: " + transactions.size());
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
