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
- I learned to sort books two ways  by title books natural order and by price custom order I create.
- Comparable sorts by title  books automatically arrange A-Z using their compareTo method.
- Comparator sorts by price  I make special rules telling books how to order by price.
- I now know when to use each  Comparable for default sorting Comparator for special sorting needs.

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


#### 4. Fast book lookup with HashMap
- Menu option 9: "Find book by ID"
- Instantly finds books by ID without searching through all books


#### 5. Advanced search features
- Menu option 10: "Find books by year range" - finds books published between specific years
- Menu option 11: "Show most expensive books" - shows top N most expensive books


#### 6. CSV export feature
- Menu option 12: "Export to CSV"
- Creates new CSV files with all book data
- Files include timestamps to avoid overwriting


#### 7. Enhanced testing
- 9 comprehensive JUnit tests
- Tests validation, equals/hashCode, and edge cases
- Covers all new Stage 2 features

### Data Files
- `sample_10.csv` - 10 books for Stage 1 testing
- `dataset_1000.csv` - 1000 books for Stage 2 testing
