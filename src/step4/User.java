package step4;

import java.util.ArrayList;
import java.util.List;

public class User {
    public static final int MAX_BOOKS_LIMIT = 3;

    private final String userId;
    private String name;
    private final List<String> borrowedBooks;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return "User";
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks);
    }

    public boolean canBorrowMoreBooks() {
        return borrowedBooks.size() < MAX_BOOKS_LIMIT;
    }

    public int getBorrowedBooksCount() {
        return borrowedBooks.size();
    }

    public int getRemainingBorrowLimit() {
        return MAX_BOOKS_LIMIT - borrowedBooks.size();
    }

    public boolean hasBorrowedBook(String isbn) {
        return borrowedBooks.contains(isbn);
    }

    public boolean borrowBook(String isbn) {
        if (canBorrowMoreBooks() && !hasBorrowedBook(isbn)) {
            borrowedBooks.add(isbn);
            return true;
        }
        return false;
    }

    public boolean returnBook(String isbn) {
        return borrowedBooks.remove(isbn);
    }

    public String toString() {
        return "User: " + name + " (" + userId + ") - Books: " + borrowedBooks.size() + "/" + MAX_BOOKS_LIMIT;
    }

}
