package LibrarySystem;


import java.util.ArrayList;
import java.util.List;

public class DataBase implements DataService {
        private List<User> users;
        private List<Book> books;

        public DataBase() {
            this.users = new ArrayList<>();
            this.books = new ArrayList<>();
        }

    public List<Book> getBooks() {
        return books;
    }

    @Override
        public Book searchBook(String title) {
            for (Book book : books) {
                if (book.getTitle().equals(title)) {
                    return book;
                }
            }
            return null;
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

        @Override
        public boolean addUser(User user) {
            return users.add(user);
        }

        @Override
        public boolean removeUser(User user) {
            return users.remove(user);
        }

        @Override
        public boolean addBook(Book book) {
            return books.add(book);
        }

        @Override
        public boolean removeBook(Book book) {
            return books.remove(book);
        }

        public User getUserByUsername(String username) {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    return user;
                }
            }
            return null;
        }

        public Book getBookByTitle(String title) {
            for (Book book : books) {
                if (book.getTitle().equals(title)) {
                    return book;
                }
            }
            return null;
        }
    }

