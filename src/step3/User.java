package step3;

import java.util.*;

public class User {
    public static final int MAX_BOOKS_LIMIT = 3;

    public final String userId;
    public String name;
    public final List<String> borrowedBooks;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public boolean canBorrowMoreBooks() {
        return borrowedBooks.size() < MAX_BOOKS_LIMIT;
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
