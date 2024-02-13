package LibrarySystem.Repository;
import LibrarySystem.Model.Admin;
import LibrarySystem.Model.Book;
import LibrarySystem.Model.User;

import java.util.ArrayList;
import java.util.List;


public class DataBase implements DataService {
    private List<User> users;
    private List<Admin> admins;
    private List<Book> books;

    public DataBase() {
        this.users = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.books = new ArrayList<>();
        initializeBooks(); // Call method to initialize books
    }

    // Method to initialize books
    private void initializeBooks() {
        books.add(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling"));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee"));
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        books.add(new Book("1984", "George Orwell"));
        books.add(new Book("Pride and Prejudice", "Jane Austen"));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger"));
        books.add(new Book("Animal Farm", "George Orwell"));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien"));
        books.add(new Book("Lord of the Flies", "William Golding"));
        books.add(new Book("Brave New World", "Aldous Huxley"));
    }

    @Override
    public boolean addBook(Book book) {
        return books.add(book);
    }

    @Override
    public Book getBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public boolean removeBook(Book book) {
        return books.remove(book);
    }

    @Override
    public boolean addUser(User user) {
        return users.add(user);
    }

    @Override
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean removeUser(User user) {
        return users.remove(user);
    }

    @Override
    public boolean borrowBook(Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            return true;
        }
        return false;
    }

    @Override
    public boolean returnBook(Book book) {
        if (!book.isAvailable()) {
            book.setAvailable(true);
            return true;
        }
        return false;
    }


    public boolean addAdmin(Admin admin) {
        return admins.add(admin);
    }

    public Admin getAdminByUsername(String username) {
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username)) {
                return admin;
            }
        }
        return null;
    }

    public boolean removeAdmin(Admin admin) {
        return admins.remove(admin);
    }
}
