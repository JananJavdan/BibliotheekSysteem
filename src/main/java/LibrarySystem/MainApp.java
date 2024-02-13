package LibrarySystem;

import LibrarySystem.Model.Admin;
import LibrarySystem.Model.Book;
import LibrarySystem.Model.User;
import LibrarySystem.Repository.DataBase;
import LibrarySystem.Service.LibraryService;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataBase dataBase = new DataBase();
        LibraryService libraryService = new LibraryService(dataBase);

        boolean exitSystem = false;

        while (!exitSystem) {
            System.out.println("Welcome to the Library System!");
            System.out.println("Please choose your role:");
            System.out.println("1. User");
            System.out.println("2. Admin");
            System.out.println("3. Exit");

            int roleChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            switch (roleChoice) {
                case 1:
                    performUserActions(scanner, dataBase, libraryService);
                    break;
                case 2:
                    performAdminActions(scanner, dataBase, libraryService);
                    break;
                case 3:
                    exitSystem = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private static void performUserActions(Scanner scanner, DataBase dataBase, LibraryService libraryService) {
        boolean exitUser = false;

        while (!exitUser) {
            System.out.println("Enter your username:");
            String username = scanner.nextLine();
            System.out.println("Enter your password:");
            String password = scanner.nextLine();

            User loggedInUser = dataBase.getUserByUsername(username);
            if (loggedInUser != null && loggedInUser.getPassword().equals(password)) {
                System.out.println("Login successful.");
                while (true) {
                    System.out.println("Choose action:");
                    System.out.println("1. Search for a book by title");
                    System.out.println("2. Borrow a book");
                    System.out.println("3. Return a book");
                    System.out.println("4. Exit");

                    int userChoice = scanner.nextInt();
                    scanner.nextLine(); // consume newline character after inputting the number

                    switch (userChoice) {
                        case 1:
                            System.out.println("Enter the title of the book to search:");

                            System.out.println("- The Great Gatsby, F. Scott Fitzgerald");
                            System.out.println("- 1984, George Orwell");
                            System.out.println("- Pride and Prejudice, Jane Austen");
                            System.out.println("- The Catcher in the Rye, J.D. Salinger");
                            System.out.println("- Animal Farm, George Orwell");
                            System.out.println("- The Hobbit, J.R.R. Tolkien");

                            String title = scanner.nextLine();
                            Book bookByTitle = dataBase.getBookByTitle(title);
                            if (bookByTitle != null) {
                                System.out.println("Found book: " + bookByTitle.getTitle() + " - " + bookByTitle.getAuthor());
                            } else {
                                System.out.println("Book with this title not found.");
                            }
                            break;
                        case 2:
                            System.out.println("Enter the title of the book you want to borrow:");
                            String bookToBorrow = scanner.nextLine();
                            Book borrowedBook = dataBase.getBookByTitle(bookToBorrow);
                            if (borrowedBook != null && borrowedBook.isAvailable()) {
                                dataBase.borrowBook(borrowedBook);
                                System.out.println("Book successfully borrowed.");
                            } else {
                                System.out.println("The book is unavailable or not found.");
                            }
                            break;
                        case 3:
                            System.out.println("Enter the title of the book you want to return:");
                            String bookToReturn = scanner.nextLine();
                            Book returnedBook = dataBase.getBookByTitle(bookToReturn);
                            if (returnedBook != null && !returnedBook.isAvailable()) {
                                dataBase.returnBook(returnedBook);
                                System.out.println("Book successfully returned.");
                            } else {
                                System.out.println("The book was not borrowed by you or not found.");
                            }
                            break;
                        case 4:
                            exitUser = true; // Return to main menu
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }

                    if (exitUser) {
                        break; // Exit user loop if user chose to exit
                    }
                }
            } else {
                System.out.println("Login error. Check username and password.");
                System.out.println("Do you want to register? (Y/N)");
                String registerChoice = scanner.nextLine().toUpperCase();
                if (registerChoice.equals("Y")) {
                    System.out.println("Enter your new username:");
                    String newUsername = scanner.nextLine();
                    System.out.println("Enter your new password:");
                    String newPassword = scanner.nextLine();
                    User newUser = new User(newUsername, newPassword);
                    dataBase.addUser(newUser);
                    System.out.println("User successfully registered. Please login again.");
                } else {
                    exitUser = true; // Return to main menu
                }
            }
        }
    }

    private static void performAdminActions(Scanner scanner, DataBase dataBase, LibraryService libraryService) {
        boolean exitAdmin = false;

        while (!exitAdmin) {
            System.out.println("Enter admin username:");
            String adminUsername = scanner.nextLine();
            System.out.println("Enter admin password:");
            String adminPassword = scanner.nextLine();

            Admin loggedInAdmin = dataBase.getAdminByUsername(adminUsername);
            if (loggedInAdmin != null && loggedInAdmin.getPassword().equals(adminPassword)) {
                System.out.println("Admin login successful.");
                while (true) {
                    System.out.println("Choose action:");
                    System.out.println("1. Add a book");
                    System.out.println("2. Delete a book");
                    System.out.println("3. Edit book information");
                    System.out.println("4. Exit");

                    int adminChoice = scanner.nextInt();
                    scanner.nextLine(); // consume newline character after inputting the number

                    switch (adminChoice) {
                        case 1:
                            System.out.println("Enter the title of the book:");
                            String newBookTitle = scanner.nextLine();
                            System.out.println("Enter the author's name:");
                            String newBookAuthor = scanner.nextLine();
                            boolean added = loggedInAdmin.addBook(newBookTitle, newBookAuthor);
                            if (added) {
                                System.out.println("Book successfully added.");
                            } else {
                                System.out.println("Error adding book.");
                            }
                            break;
                        case 2:
                            System.out.println("Enter the title of the book to delete:");
                            String deleteBookTitle = scanner.nextLine();
                            boolean deleted = loggedInAdmin.deleteBook(deleteBookTitle);
                            if (deleted) {
                                System.out.println("Book successfully deleted.");
                            } else {
                                System.out.println("Error deleting book.");
                            }
                            break;
                        case 3:
                            System.out.println("Enter the title of the book to edit:");
                            String editBookTitle = scanner.nextLine();
                            System.out.println("Enter the new title of the book:");
                            String newTitle = scanner.nextLine();
                            System.out.println("Enter the new author of the book:");
                            String newAuthor = scanner.nextLine();
                            boolean edited = loggedInAdmin.editBookInformation(editBookTitle, newTitle, newAuthor);
                            if (edited) {
                                System.out.println("Book information successfully edited.");
                            } else {
                                System.out.println("Error editing book information.");
                            }
                            break;
                        case 4:
                            exitAdmin = true; // Return to main menu
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }

                    if (exitAdmin) {
                        break; // Exit admin loop if admin chose to exit
                    }
                }
            } else {
                System.out.println("Admin login error. Check username and password.");
                System.out.println("Do you want to register as admin? (Y/N)");
                String registerChoice = scanner.nextLine().toUpperCase();
                if (registerChoice.equals("Y")) {
                    System.out.println("Enter your new admin username:");
                    String newAdminUsername = scanner.nextLine();
                    System.out.println("Enter your new admin password:");
                    String newAdminPassword = scanner.nextLine();
                    Admin newAdmin = new Admin(newAdminUsername, newAdminPassword, dataBase);
                    dataBase.addAdmin(newAdmin);
                    System.out.println("Admin successfully registered. Please login again.");
                } else {
                    exitAdmin = true; // Return to main menu
                }
            }
        }
    }
}