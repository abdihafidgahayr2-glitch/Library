# Library System - Stage 1
## What I Built
### A library program that stores books and lets users:

- View all books

- Search books by title

- Sort books by title or price

- See available books

- Borrow and return books

### How It Works
- Loads 10 books from sample_10.csv file

- Uses ArrayList to store all books

- Books sort by title (normal way) or price (special way)

- Easy menu system with options 1-8

### Error Handling
- Checks if file exists

- Makes sure book numbers are valid (1, 2, 3...)

- Handles empty book lists

- Won't crash on bad input

### Testing
- 4 JUnit tests

- Tests book creation and sorting

- Tests both title and price sorting

- Tests borrow/return functions

### What I Learned
- How to sort books two different ways  by title normal way and by price special way

- How to read different data types from files  converting text to numbers dates and true/false values

- How to make a complete Book class with all needed fields validation and business methods

- How to build a full menu system that handles user choices without crashing

# Stage 2 

### What I Added:
1. equals() and hashCode()methods in Book class
    - Books with same ID are considered equal
    - Helps HashSet remove duplicates


2. Duplicate detection feature
    - Menu option 8: Find duplicates
    - Shows total books vs unique books
    - Counts how many duplicates were found


3. Validation in Book constructor
    - Checks for empty titles, negative prices
    - Validates year ranges
    - Shows error messages for bad data



