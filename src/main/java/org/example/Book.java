package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Book implements Comparable<Book> {
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private int year;
    private double price;
    private boolean available;
    private LocalDate publishDate;
    private LocalDateTime lastUpdated;

    public Book() {
    }

    public Book(String bookId, String title, String author, String genre,
                int year, double price, boolean available,
                LocalDate publishDate, LocalDateTime lastUpdated) {

        // VALIDATION:
        if (bookId == null || bookId.trim().isEmpty()) {
            throw new IllegalArgumentException("Book ID cannot be empty");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (year < 1450 || year > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Invalid year: " + year);
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative: " + price);
        }

        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.price = price;
        this.available = available;
        this.publishDate = publishDate;
        this.lastUpdated = lastUpdated;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    // methods
    public void borrowBook() {
        available = false;
        lastUpdated = LocalDateTime.now();
    }

    public void returnBook() {
        available = true;
        lastUpdated = LocalDateTime.now();
    }


    @Override
    public boolean equals(Object o) {
        //Check if o is actually a Book object
        if (!(o instanceof Book))
            return false;

        //Convert o to a Book object
        Book other = (Book) o;

        //Compare book ids casesensitive comparison
        return bookId.equals(other.bookId);
    }

    @Override
    public int hashCode() {
        //Create hash code only on bookId
        return Objects.hash(bookId);
    }


    // Natural order by title
    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return bookId + " - " + title + " by " + author + " (" + year + ") - €" + price;
    }
}