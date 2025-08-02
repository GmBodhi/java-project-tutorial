package step6;

public class Book {
    public final String isbn;
    public final String title;
    public final String author;
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

    public String toString() {
        return title + " by " + author;
    }
}
