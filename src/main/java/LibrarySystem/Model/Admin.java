package LibrarySystem.Model;

import LibrarySystem.Model.Book;
import LibrarySystem.Repository.DataService;
import LibrarySystem.Model.User;

public class Admin extends User {
    private DataService dataService;

    public Admin(String username, String password, DataService dataService) {
        super(username, password);
        this.dataService = dataService;
    }

    public boolean addBook(String title, String author) {
        Book book = new Book(title, author);
        return dataService.addBook(book);
    }

    public boolean deleteBook(String title) {
        Book book = dataService.getBookByTitle(title);
        if (book != null) {
            return dataService.removeBook(book);
        }
        return false;
    }

    public boolean editBookInformation(String title, String newTitle, String newAuthor) {
        Book book = dataService.getBookByTitle(title);
        if (book != null) {
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            return true;
        }
        return false;
    }
}