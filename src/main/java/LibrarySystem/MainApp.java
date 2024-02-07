package LibrarySystem;

public class MainApp {

        public static void main(String[] args) {
            DataService dataService = new DataBase();
            CustomerService customerService = new CustomerService(dataService);
            LibraryService libraryService = new LibraryService(dataService);


        }
    }

