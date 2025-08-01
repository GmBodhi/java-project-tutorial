package step5;

public class Book {
    private final String isbn;
    private String title;
    private String author;
    private BookStatus status;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.status = BookStatus.AVAILABLE;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public boolean isAvailable() {
        return status == BookStatus.AVAILABLE;
    }

    public boolean isBorrowed() {
        return status == BookStatus.BORROWED;
    }

    public void markAsAvailable() {
        this.status = BookStatus.AVAILABLE;
    }

    public void markAsBorrowed() {
        this.status = BookStatus.BORROWED;
    }

    public String toString() {
        return "Book: " + title + " by " + author + " (" + isbn + ") - " + status;
    }

}
