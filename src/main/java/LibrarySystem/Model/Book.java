package LibrarySystem.Model;

public class Book {
    private String title;
    private String author;
    private boolean availability;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.availability = true; // Initially, the book is available
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailable(boolean availability) {
        this.availability = availability;
    }

    // methods for setting a new title and author
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}