package LibrarySystem.Service;

import LibrarySystem.Model.Book;
import LibrarySystem.Model.User;
import LibrarySystem.Repository.DataService;

public class LibraryService {
    private DataService dataService;

    public LibraryService(DataService dataService) {
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

    public boolean registerUser(String username, String password) {
        User newUser = new User(username, password);
        return dataService.addUser(newUser);
    }

    public boolean manageUser(String username) {
        User user = dataService.getUserByUsername(username);
        if (user != null) {
            return dataService.removeUser(user);
        }
        return false;
    }
}