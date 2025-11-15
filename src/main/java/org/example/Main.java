package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    private List<Book> books = new ArrayList<>();
    private Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Main app = new Main();
        app.start();
    }

    public void start() throws Exception {
        System.out.println("Starting Library System...");
        loadBooks();
        showMenu();
    }

    public void loadBooks() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/java/org/example/sample_10.csv"));
        String line;
        reader.readLine(); // skip header

        int count = 0;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length >= 9) {
                Book book = new Book(
                        data[0], data[1], data[2], data[3],
                        Integer.parseInt(data[4]),
                        Double.parseDouble(data[5]),
                        Boolean.parseBoolean(data[6]),
                        LocalDate.parse(data[7]),
                        LocalDateTime.parse(data[8])
                );
                books.add(book);
                count++;
            }
        }
        reader.close();
        System.out.println(count + " books");
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. Show all books");
            System.out.println("2. Search by title");
            System.out.println("3. Sort by title");
            System.out.println("4. Sort by price");
            System.out.println("5. Show available books");
            System.out.println("6. Borrow a book");
            System.out.println("7. Return a book");
            System.out.println("8. Exit");
            System.out.print("Choose option: ");

            int choice = keyboard.nextInt();
            keyboard.nextLine();

            switch (choice) {
                case 1:
                    showAllBooks();
                    break;
                case 2:
                    searchByTitle();
                    break;
                case 3:
                    sortByTitle();
                    break;
                case 4:
                    sortByPrice();
                    break;
                case 5:
                    showAvailableBooks();
                    break;
                case 6:
                    borrowBook();
                    break;
                case 7:
                    returnBook();
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public void showAllBooks() {
        System.out.println("\nAll Books :");
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            String status = book.isAvailable() ? "Available" : "Borrowed";
            System.out.println((i+1) + ". " + book + " - " + status);
        }
    }

    public void searchByTitle() {
        System.out.print("Enter title to search: ");
        String search = keyboard.nextLine().toLowerCase();

        System.out.println("Search results:");
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(search)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found");
        }
    }

    public void sortByTitle() {
        Collections.sort(books);
        System.out.println("Books by title:");
        showAllBooks();
    }

    public void sortByPrice() {
        Collections.sort(books, new Comparator<Book>() {
            public int compare(Book b1, Book b2) {
                return Double.compare(b1.getPrice(), b2.getPrice());
            }
        });
        System.out.println("Books by price:");
        showAllBooks();
    }

    public void showAvailableBooks() {
        System.out.println("Available Books:");
        boolean found = false;
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books available");
        }
    }

    public void borrowBook() {
        showAllBooks();
        if (books.isEmpty()) return;

        System.out.print("Enter book number to borrow: ");
        int num = keyboard.nextInt();

        if (num > 0 && num <= books.size()) {
            Book book = books.get(num-1);
            if (book.isAvailable()) {
                book.borrowBook();
                System.out.println("You borrowed: " + book.getTitle());
            }
            else {
                System.out.println("Book is already borrowed");
            }
        }
        else {
            System.out.println("Invalid book number");
        }
    }

    public void returnBook() {
        System.out.println("Borrowed Books:");
        boolean found = false;
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (!book.isAvailable()) {
                System.out.println((i+1) + ". " + book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No borrowed books");
            return;
        }

        System.out.print("Enter book number to return: ");
        int num = keyboard.nextInt();

        if (num > 0 && num <= books.size()) {
            Book book = books.get(num-1);
            if (!book.isAvailable()) {
                book.returnBook();
                System.out.println("You returned: " + book.getTitle());
            } else {
                System.out.println("Book was not borrowed");
            }
        } else {
            System.out.println("Invalid book number");
        }
    }
}