import java.util.ArrayList;
import java.util.Scanner;

// Book class
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }

    public void issue() { isIssued = true; }
    public void returnBook() { isIssued = false; }

    @Override
    public String toString() {
        return "Book ID: " + id + " | Title: " + title + " | Author: " + author + 
               (isIssued ? " [Issued]" : " [Available]");
    }
}

// User class
class User {
    private int id;
    private String name;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public ArrayList<Book> getBorrowedBooks() { return borrowedBooks; }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "User ID: " + id + " | Name: " + name + " | Borrowed Books: " + borrowedBooks.size();
    }
}

// Library class
class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void viewUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
        for (User u : users) {
            System.out.println(u);
        }
    }

    public void issueBook(int bookId, int userId) {
        Book book = findBook(bookId);
        User user = findUser(userId);

        if (book == null) {
            System.out.println("Book not found!");
            return;
        }
        if (user == null) {
            System.out.println("User not found!");
            return;
        }
        if (book.isIssued()) {
            System.out.println("Book is already issued!");
            return;
        }

        book.issue();
        user.borrowBook(book);
        System.out.println("✅ Book issued to " + user.getName());
    }

    public void returnBook(int bookId, int userId) {
        Book book = findBook(bookId);
        User user = findUser(userId);

        if (book == null || user == null) {
            System.out.println("Invalid book or user ID!");
            return;
        }
        if (!book.isIssued()) {
            System.out.println("Book was not issued!");
            return;
        }

        book.returnBook();
        user.returnBook(book);
        System.out.println("Book returned by " + user.getName());
    }

    private Book findBook(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    private User findUser(int id) {
        for (User u : users) {
            if (u.getId() == id) return u;
        }
        return null;
    }
}

// Main CLI
public class LibraryManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        System.out.println("===== Library Management System =====");

        // Adding some default data
        library.addBook(new Book(1, "The Alchemist", "Paulo Coelho"));
        library.addBook(new Book(2, "Java Programming", "James Gosling"));
        library.addUser(new User(1, "Alice"));
        library.addUser(new User(2, "Bob"));

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. View Books");
            System.out.println("2. View Users");
            System.out.println("3. Add Book");
            System.out.println("4. Add User");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    library.viewBooks();
                    break;
                case 2:
                    library.viewUsers();
                    break;
                case 3:
                    addBook();
                    break;
                case 4:
                    addUser();
                    break;
                case 5:
                    issueBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author Name: ");
        String author = scanner.nextLine();

        library.addBook(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    private static void addUser() {
        System.out.print("Enter User ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter User Name: ");
        String name = scanner.nextLine();

        library.addUser(new User(id, name));
        System.out.println("User added successfully!");
    }

    private static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int bookId = scanner.nextInt();
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();

        library.issueBook(bookId, userId);
    }

    private static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int bookId = scanner.nextInt();
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();

        library.returnBook(bookId, userId);
    }
}
