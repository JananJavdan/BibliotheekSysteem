package LibrarySystem.Service;

import LibrarySystem.Model.Admin;
import LibrarySystem.Model.Book;
import LibrarySystem.Repository.DataService;

public class CustomerService {
    private DataService dataService;

    public CustomerService(DataService dataService) {
        this.dataService = dataService;
    }

    // Methoden voor het werken met boeken
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
            // Edit book information
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            return true;
        }
        return false;
    }

    // Methoden voor het werken met de beheerder
    public boolean loginAsAdmin(String username, String password) {
        Admin admin = dataService.getAdminByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public boolean addBookAsAdmin(String username, String password, String title, String author) {
        // Check if logged in as admin
        if (loginAsAdmin(username, password)) {
            Book book = new Book(title, author);
            return dataService.addBook(book);
        }
        return false;
    }

    public boolean deleteBookAsAdmin(String username, String password, String title) {
        // Check if logged in as admin
        if (loginAsAdmin(username, password)) {
            Book book = dataService.getBookByTitle(title);
            if (book != null) {
                return dataService.removeBook(book);
            }
        }
        return false;
    }

    public boolean editBookInformationAsAdmin(String username, String password, String title, String newTitle, String newAuthor) {
        // Check if logged in as admin
        if (loginAsAdmin(username, password)) {
            Book book = dataService.getBookByTitle(title);
            if (book != null) {
                // Edit book information
                book.setTitle(newTitle);
                book.setAuthor(newAuthor);
                return true;
            }
        }
        return false;
    }
}