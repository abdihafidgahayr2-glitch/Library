package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @org.junit.jupiter.api.Test
    void testBookCreation() {
        System.out.println("Testing book creation");
        Book book = new Book("B001", "Harry Potter", "J.K. Rowling", "Fantasy",
                1997, 15.99, true,
                LocalDate.now(),
                LocalDateTime.now());

        assertEquals("B001", book.getBookId());
        assertEquals("Harry Potter", book.getTitle());
    }

    @org.junit.jupiter.api.Test
    void testCompareTo() {
        System.out.println("Testing book title comparison");
        Book book1 = new Book("B001", "Apple", "Author", "Fiction", 2020, 10.99, true, null, null);
        Book book2 = new Book("B002", "Banana", "Author", "Fiction", 2020, 10.99, true, null, null);

        assertTrue(book1.compareTo(book2) < 0);
    }

    @org.junit.jupiter.api.Test
    void testBorrowBook() {
        System.out.println("Testing borrow functionality");
        Book book = new Book("B001", "Test Book", "Author", "Fiction", 2020, 15.99, true, null, null);

        book.borrowBook();
        assertFalse(book.isAvailable());
    }

    @org.junit.jupiter.api.Test
    void testPriceComparator() {
        System.out.println("Testing price comparator");
        Book cheap = new Book("B001", "Book1", "Author", "Fiction", 2020, 5.99, true, null, null);
        Book expensive = new Book("B002", "Book2", "Author", "Fiction", 2020, 15.99, true, null, null);

        Comparator<Book> comparator = new Comparator<Book>() {
            public int compare(Book b1, Book b2) {
                return Double.compare(b1.getPrice(), b2.getPrice());
            }
        };

        assertTrue(comparator.compare(cheap, expensive) < 0);
    }
}