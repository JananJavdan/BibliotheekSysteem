package LibrarySystem;

public interface DataService {
    Book searchBook(String title);
    boolean borrowBook(Book book);
    boolean returnBook(Book book);
    boolean addUser(User user);
    boolean removeUser(User user);
    boolean addBook(Book book);
    boolean removeBook(Book book);
}

