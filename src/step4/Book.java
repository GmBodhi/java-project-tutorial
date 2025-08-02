package step4;

public class Book {
    public final String isbn;
    public String title;
    public String author;
    public BookStatus status;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.status = BookStatus.AVAILABLE;
    }

    public boolean isAvailable() {
        return status == BookStatus.AVAILABLE;
    }

    public boolean isBorrowed() {
        return status == BookStatus.BORROWED;
    }

    public String toString() {
        return "Book: " + title + " by " + author + " (" + isbn + ") - " + status;
    }

}
