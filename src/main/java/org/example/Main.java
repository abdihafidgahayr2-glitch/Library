package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    private List<Book> books = new ArrayList<>();
    private Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    public void start() {
        System.out.println("Starting Library System...");
        loadBooks();
        showMenu();
    }


    public void loadBooks() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/org/example/dataset_1000.csv"));
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
        catch (Exception e) {
            System.out.println("Error loading books file");
        }
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
            System.out.println("8. Find duplicates");
            System.out.println("9. Find book by ID ");
            System.out.println("10. Find books by year range");
            System.out.println("11. Show most expensive books (Stage 2)");
            System.out.println("12. Export to CSV file");
            System.out.println("13. Exit");
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
                    findDuplicates();
                    break;
                case 9:
                    findBookById();
                    break;
                case 10:
                    findBooksByYearRange();
                    break;
                case 11:
                    showMostExpensiveBooks();
                    break;
                case 12:
                    exportToCSV();
                    break;
                case 13:
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
    public void findDuplicates() {
        //HashSet removes duplicates
        Set<Book> uniqBooks = new HashSet<>(books);

        System.out.println("Duplicates");
        System.out.println("Total books loaded: " + books.size());
        System.out.println("Unique books found: " + uniqBooks.size());
        System.out.println("Duplicates removed: " + (books.size() - uniqBooks.size()));
    }

    public void findBookById() {
        //create HashMap key=bookId val=Book object
        Map<String, Book> bookMap = new HashMap<>();

        //fill the map with all books
        for (Book book : books) {
            bookMap.put(book.getBookId(), book);
        }

        System.out.print("Enter book ID to find: ");
        String id = keyboard.nextLine();

        //instant lookup no searching through all books
        Book found = bookMap.get(id);

        if (found != null) {
            System.out.println("Found: " + found);
        } else {
            System.out.println("Book " + id + " not found");
        }
    }

    public void findBooksByYearRange() {
        System.out.print("Enter start year: ");
        int startYear = keyboard.nextInt();
        System.out.print("Enter end year: ");
        int endYear = keyboard.nextInt();
        keyboard.nextLine();

        System.out.println("Books published " + startYear + " - " + endYear + ":");
        int count = 0;

        for (Book book : books) {
            if (book.getYear() >= startYear && book.getYear() <= endYear) {
                System.out.println(book);
                count++;
            }
        }

        System.out.println("Found " + count + " books");
    }

    public void showMostExpensiveBooks() {
        System.out.print("How many expensive books to show? ");
        int n = keyboard.nextInt();
        keyboard.nextLine();

        // Create sorted copy
        List<Book> sortedByPrice = new ArrayList<>(books);

        // Sort by price highest first
        Collections.sort(sortedByPrice, new Comparator<Book>() {
            public int compare(Book b1, Book b2) {
                // Compare b2 to b1 to get desc order highest first
                return Double.compare(b2.getPrice(), b1.getPrice());
            }
        });

        System.out.println("Top"+ n +"Most Expensive Books:");
        for (int i = 0; i < Math.min(n, sortedByPrice.size()); i++) {
            System.out.println((i+1) + ". " + sortedByPrice.get(i));
        }
    }

    public void exportToCSV() {
        try {
            String filename = "library_export.csv";
            PrintWriter writer = new PrintWriter(new FileWriter(filename));

            writer.println("bookId,title,author,genre,year,price,available,publishDate,lastUpdated");

            for (Book book : books) {
                writer.printf("%s,%s,%s,%s,%d,%.2f,%s,%s,%s%n",
                        book.getBookId(), book.getTitle(), book.getAuthor(), book.getGenre(),
                        book.getYear(), book.getPrice(), book.isAvailable(),
                        book.getPublishDate(), book.getLastUpdated());
            }

            writer.close();
            System.out.println("Exported " + books.size() + " books to " + filename);

        } catch (Exception e) {
            System.out.println("Error exporting");
        }
    }

}