import java.util.*;

public class QuantumBookstoreSystem {
    private List<Book> inventory = new ArrayList<>();


    public User login(String username, String password) {
        if (username.equals("admin") && password.equals("admin123")) {
            return new User(username, "admin");
        } else {
            return new User(username, "user");
        }
    }

    public void start() {
        inventory.add(new PaperBook("P001", "Clean Code", 2010, 150, "Robert C. Martin", 10));
        inventory.add(new EBook("E001", "Algorithms Unlocked", 2015, 100, "Thomas Cormen", 20));
        inventory.add(new ShowCaseBook("D001", "Demo Design Patterns", 2000, 0, "Gamma","PDF"));

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Quantum bookstore: Welcome, please login");
            System.out.print("Username: ");
            String username = input.nextLine();
            System.out.print("Password: ");
            String password = input.nextLine();

            User currentUser = login(username, password);
            System.out.println("Quantum bookstore: Logged in as " + currentUser.getRole());

            if (currentUser.getRole().equals("admin")) {
                adminMenu(input);
            } else {
                userMenu(input);
            }
        }
    }

    private void adminMenu(Scanner input) {
        while (true) {
            System.out.println("\nQuantum bookstore Admin Menu:");
            System.out.println("1- Add Book");
            System.out.println("2- Remove Old Books (less than 10 years)");
            System.out.println("3- Display All Books");
            System.out.println("4- Remove Book by ISBN");
            System.out.println("5- Logout");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Please Choose Type of book \n" +
                            "1- Paper Book \n" +
                            "2- E Book \n" +
                            "3- Demo Book");
                    int type = input.nextInt();
                    input.nextLine();

                    System.out.println("Please Enter ISBN");
                    String id = input.nextLine();
                    System.out.println("Please Enter Title of Book");
                    String title = input.nextLine();
                    System.out.println("Please Enter Year");
                    int year = input.nextInt();


                    if (year > Calendar.getInstance().get(Calendar.YEAR)) {
                        System.out.println("Quantum bookstore: Invalid year.");
                        break;
                    }

                    System.out.println("Please Enter Price");
                    int price = input.nextInt();
                    if (price < 0) {
                        System.out.println("Quantum bookstore: Price can't be negative.");
                        break;
                    }

                    input.nextLine();
                    System.out.println("Please Enter Author Name");
                    String authorName = input.nextLine();

                    if (type == 1 || type == 2) {
                        System.out.println("Please Enter count");
                        int count = input.nextInt();
                        if (count < 0) {
                            System.out.println("Quantum bookstore: Count can't be negative.");
                            break;
                        }
                        input.nextLine();
                        Book n = (type == 1) ? new PaperBook(id, title, year, price, authorName, count) : new EBook(id, title, year, price, authorName, count);
                        inventory.add(n);
                    } else if (type == 3) {
                        System.out.println("Please Enter File Type");
                        String Ftype = input.nextLine();
                        Book n = new ShowCaseBook(id, title, year, price, authorName,Ftype);
                        inventory.add(n);
                    } else {
                        System.out.println("Error , Invalid number of choice ");
                        break;
                    }

                    System.out.println("Quantum bookstore: Book added.");
                    break;

                case 2:
                    removeOldBooks(10);
                    break;

                case 3:
                    showBooks();
                    break;

                case 4:
                    System.out.print("Enter ISBN to remove: ");
                    String removeISBN = input.nextLine();
                    Book bookToRemove = findBookByISBN(removeISBN);
                    if (bookToRemove != null) {
                        inventory.remove(bookToRemove);
                        System.out.println("Quantum bookstore: Book removed.");
                    } else {
                        System.out.println("Quantum bookstore: Book not found.");
                    }
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Quantum bookstore: Invalid choice.");
            }
        }
    }

    private void userMenu(Scanner input) {
        while (true) {
            System.out.println("\nQuantum bookstore User Menu:");
            System.out.println("1. Display Available Books");
            System.out.println("2. Buy Book by ISBN");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Logout");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    showBooks();
                    break;

                case 2:
                    System.out.print("Enter ISBN: ");
                    String isbn = input.nextLine();
                    System.out.print("Quantity: ");
                    int quantity = input.nextInt();
                    input.nextLine();

                    Book found = findBookByISBN(isbn);
                    if (found != null) {
                        if (found instanceof PaperBook) {
                            System.out.print("Enter address: ");
                            String address = input.nextLine();
                            ((PaperBook) found).buyPaper(quantity, address);
                        } else if (found instanceof EBook) {
                            System.out.print("Enter email: ");
                            String email = input.nextLine();
                            ((EBook) found).buyEBook(quantity, email);
                        } else if (found instanceof ShowCaseBook) {
                            ((ShowCaseBook) found).buyShowCaseBook();
                        } else {
                            System.out.println("Quantum bookstore: Unknown book type.");
                        }
                    } else {
                        System.out.println("Quantum bookstore: Book not found.");



                    }
                    break;

                case 3:
                    searchBooksByTitle(input);
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Quantum bookstore: Invalid choice.");
            }
        }
    }

    private void showBooks() {
        if (inventory.isEmpty()) {
            System.out.println("Quantum bookstore: Inventory is empty.");
            return;
        }

        for (Book b : inventory) {
            String type;
            if (b instanceof PaperBook) {
                type = "Paper Book";
            } else if (b instanceof EBook) {
                type = "EBook";
            } else if (b instanceof ShowCaseBook) {
                type = "Demo Book";
            } else {
                type = "Unknown";
            }
            System.out.println("[Type: " + type + "] " + b.Display());
        }
    }

    private void removeOldBooks(int Age) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        inventory.removeIf(b -> (currentYear - b.year) > Age);
        System.out.println("Quantum bookstore: Old books removed. (less than 10 years)");
    }

    private Book findBookByISBN(String isbn) {
        for (Book b : inventory) {
            if (b.ISBN.equals(isbn)) {
                return b;
            }
        }
        return null;
    }

    private void searchBooksByTitle(Scanner input) {
        System.out.print("Enter keyword to search in titles: ");
        String keyword = input.nextLine().toLowerCase();
        boolean found = false;

        for (Book b : inventory) {
            if (b.Title.toLowerCase().contains(keyword)) {
                String type = b instanceof PaperBook ? "Paper Book"
                        : b instanceof EBook ? "EBook"
                        : b instanceof ShowCaseBook ? "Demo Book" : "Unknown";
                System.out.println("[Type: " + type + "] " + b.Display());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Quantum bookstore: No books found with that keyword.");
        }
    }
}
