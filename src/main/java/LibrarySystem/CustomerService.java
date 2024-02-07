package LibrarySystem;

    public class CustomerService {
        private DataService dataService;

        public CustomerService(DataService dataService) {
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
// Edit book information
                book.setTitle(newTitle);
                book.setAuthor(newAuthor);
                return true;
            }
            return false;
        }
    }






