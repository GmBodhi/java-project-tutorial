# Java OOP Library Management System - Comprehensive Session Guide

## Overview
This guide provides a step-by-step approach to building a complete Library Management System in Java, progressing from basic concepts to a full GUI application. Each step introduces new Object-Oriented Programming concepts and builds upon the previous steps. **Every line of code is thoroughly documented and explained.**

## Session Structure
**Duration:** 4-5 hours  
**Target Audience:** Intermediate Java developers learning OOP  
**Prerequisites:** Basic Java syntax, variables, methods, and basic IDE usage

---

## Step 1: Project Setup and Basic Structure
**Duration:** 15 minutes  
**Concepts:** Project organization, packages, main method

### What We'll Build
- Create the basic project structure
- Set up packages for organized code
- Create the main entry point

### Implementation

#### File Structure
```
src/
└── step1/
    └── Main.java
```

#### Complete Code with Line-by-Line Documentation

**Main.java**
```java
package step1;                          // Line 1: Package declaration - organizes code into logical groups
                                        // Line 2: Empty line for readability

public class Main {                     // Line 3: Public class declaration - must match filename
    public static void main(String[] args) {   // Line 4: Main method - entry point for Java applications
                                        //         'public' - accessible from anywhere
                                        //         'static' - belongs to class, not instance
                                        //         'void' - returns nothing
                                        //         'String[] args' - command line arguments
        
    }                                   // Line 5: Closing brace for main method
}                                       // Line 6: Closing brace for Main class
```

### Key Learning Points
- **Package declaration:** Must be first non-comment line, defines namespace
- **Class naming:** Must match filename exactly (case-sensitive)
- **Main method signature:** Exact signature required for JVM to find entry point
- **Code organization:** Proper indentation and bracing style

### Session Discussion Points
- Why do we use packages? (Namespace management, avoiding name conflicts)
- What happens if main method signature is wrong?
- How does the JVM find and load our class?
- Best practices for package naming

---

## Step 2: Core Domain Objects - Book and Enum
**Duration:** 45 minutes  
**Concepts:** Classes, encapsulation, enums, constructors, getters/setters, toString()

### What We'll Build
- Create a `BookStatus` enum for type safety
- Create a `Book` class with proper encapsulation
- Demonstrate object creation and manipulation

### Implementation Files

#### Complete Code with Line-by-Line Documentation

**BookStatus.java**
```java
package step2;                          // Line 1: Package declaration for step2

public enum BookStatus {                // Line 2: Enum declaration - defines a fixed set of constants
    AVAILABLE,                          // Line 3: First enum constant - book can be borrowed
    BORROWED,                           // Line 4: Second enum constant - book is currently borrowed
}                                       // Line 5: Closing brace for enum
```

**Book.java**
```java
package step2;                          // Line 1: Package declaration for step2

public class Book {                     // Line 2: Public class - accessible from other packages
    private final String isbn;          // Line 3: Private final field - immutable identifier
                                        //         'private' - only accessible within this class
                                        //         'final' - cannot be changed after initialization
    private String title;               // Line 4: Private mutable field for book title
    private String author;              // Line 5: Private mutable field for author name
    private BookStatus status;          // Line 6: Private field using our custom enum

    public Book(String isbn, String title, String author) {    // Line 7: Public constructor
                                        //         Takes 3 parameters to initialize core book data
        this.isbn = isbn;               // Line 8: Initialize final field - must be done in constructor
                                        //         'this.' refers to current object instance
        this.title = title;             // Line 9: Initialize title field
        this.author = author;           // Line 10: Initialize author field
        this.status = BookStatus.AVAILABLE;    // Line 11: Set default status to AVAILABLE
                                        //          Using enum constant for type safety
    }                                   // Line 12: End of constructor

    public String getIsbn() {           // Line 13: Getter method for ISBN
                                        //          'public' - accessible from outside class
                                        //          Returns String - same type as field
        return isbn;                    // Line 14: Return the ISBN value
    }                                   // Line 15: End of getter method

    public String getTitle() {          // Line 16: Getter method for title
        return title;                   // Line 17: Return current title value
    }                                   // Line 18: End of getter

    public void setTitle(String title) { // Line 19: Setter method for title
                                        //          'void' - doesn't return anything
                                        //          Parameter shadows field name
        this.title = title;             // Line 20: Assign parameter to field
                                        //          'this.' disambiguates between parameter and field
    }                                   // Line 21: End of setter

    public String getAuthor() {         // Line 22: Getter method for author
        return author;                  // Line 23: Return current author value
    }                                   // Line 24: End of getter

    public void setAuthor(String author) {  // Line 25: Setter method for author
        this.author = author;           // Line 26: Assign parameter to instance field
    }                                   // Line 27: End of setter

    public BookStatus getStatus() {     // Line 28: Getter for status - returns enum type
        return status;                  // Line 29: Return current status enum value
    }                                   // Line 30: End of getter

    public void setStatus(BookStatus status) {  // Line 31: Setter for status - accepts enum type
        this.status = status;           // Line 32: Assign enum parameter to field
    }                                   // Line 33: End of setter

    public boolean isAvailable() {      // Line 34: Business logic method - returns boolean
                                        //          Method name clearly indicates what it checks
        return status == BookStatus.AVAILABLE;  // Line 35: Compare enum values using ==
                                        //                  Returns true if book is available
    }                                   // Line 36: End of business method

    public boolean isBorrowed() {       // Line 37: Another business logic method
        return status == BookStatus.BORROWED;   // Line 38: Check if status equals BORROWED
    }                                   // Line 39: End of business method

    public void markAsAvailable() {     // Line 40: State change method - no parameters needed
        this.status = BookStatus.AVAILABLE;     // Line 41: Change status to AVAILABLE
    }                                   // Line 42: End of state change method

    public void markAsBorrowed() {      // Line 43: State change method for borrowing
        this.status = BookStatus.BORROWED;      // Line 44: Change status to BORROWED
    }                                   // Line 45: End of state change method

    public String toString() {          // Line 46: Override Object's toString() method
                                        //          Called automatically when object is printed
        return "Book: " + title + " by " + author + " (" + isbn + ") - " + status;
                                        // Line 47: Concatenate all relevant fields into readable string
                                        //          Includes title, author, ISBN, and current status
    }                                   // Line 48: End of toString method

}                                       // Line 49: End of Book class
```

**Main.java (Updated)**
```java
package step2;                          // Line 1: Package declaration for step2

public class Main {                     // Line 2: Main class declaration
    public static void main(String[] args) {   // Line 3: Main method signature
        // Demonstrate Book creation and usage
        Book book1 = new Book("978-0134685991", "Effective Java", "Joshua Bloch");
                                        // Line 4: Create new Book object
                                        //         Constructor called with 3 arguments
                                        //         Object reference stored in book1 variable
        
        System.out.println(book1);      // Line 5: Print book object - calls toString() automatically
        
        System.out.println("Is available: " + book1.isAvailable());
                                        // Line 6: Test business logic method
                                        //         Should print "true" since default status is AVAILABLE
        
        book1.markAsBorrowed();         // Line 7: Change book status using state change method
        System.out.println("After borrowing: " + book1);
                                        // Line 8: Print book again to see status change
        
        System.out.println("Is borrowed: " + book1.isBorrowed());
                                        // Line 9: Test that status change worked
                                        //         Should print "true" now
        
        // Demonstrate encapsulation
        System.out.println("ISBN: " + book1.getIsbn());
                                        // Line 10: Access private field through getter
                                        //          Direct access to book1.isbn would fail
        
        book1.setTitle("Effective Java 3rd Edition");
                                        // Line 11: Modify mutable field through setter
        System.out.println("Updated book: " + book1);
                                        // Line 12: Verify title change took effect
    }                                   // Line 13: End of main method
}                                       // Line 14: End of Main class
```

### Key OOP Concepts Explained

#### 1. **Encapsulation in Detail**
- **Private fields:** Data hidden from outside access, controlled through methods
- **Final keyword:** Prevents modification after initialization (immutable)
- **Getter/Setter pattern:** Controlled access to private data
- **Why encapsulation matters:** Data integrity, validation opportunities, flexibility

#### 2. **Constructor Design**
- **Purpose:** Initialize object state when created
- **Parameter validation:** Could add null checks, format validation
- **Default values:** Setting reasonable defaults (AVAILABLE status)
- **this keyword:** Distinguishes between parameters and instance fields

#### 3. **Method Design Patterns**
- **Getters:** Simple field access, same return type as field
- **Setters:** Void return type, parameter for new value
- **Business methods:** Logic-based methods (isAvailable, isBorrowed)
- **State change methods:** Methods that modify object state
- **toString():** Standard Java practice for object representation

#### 4. **Enum Usage**
- **Type safety:** Compiler prevents invalid values
- **Readability:** AVAILABLE vs magic numbers or strings
- **Maintainability:** Easy to add new statuses later
- **Performance:** Enums are efficient in Java

### Hands-On Exercises
1. **Create multiple books:** Different genres, authors, ISBNs
2. **Test encapsulation:** Try accessing private fields directly (should fail to compile)
3. **Enum operations:** Change status and observe toString() output
4. **Validation:** Add null checks to constructor and setters
5. **Business logic:** Add methods like `canBeBorrowed()` or `getDaysOverdue()`

### Session Discussion Points
- **Why make ISBN final but title/author mutable?** ISBN is unique identifier, title/author might need corrections
- **When to use enums vs constants?** Enums for related constants, better type safety
- **Benefits of encapsulation in real applications:** Security, validation, maintainability
- **How toString() helps with debugging:** Easy object inspection, logging

---

## Step 3: User Management and Collections
**Duration:** 45 minutes  
**Concepts:** Collections (ArrayList), business rules, defensive copying, constants

### What We'll Build
- Create a `User` class with borrowing capabilities
- Implement business rules (borrowing limits)
- Use collections to track borrowed books
- Demonstrate proper collection handling

### Implementation Files

#### Complete Code with Line-by-Line Documentation

**User.java**
```java
package step3;                          // Line 1: Package declaration for step3

import java.util.*;                     // Line 2: Import all java.util classes
                                        //         Includes ArrayList, List interface

public class User {                     // Line 3: Public class declaration
    public static final int MAX_BOOKS_LIMIT = 3;   // Line 4: Class constant
                                        //         'public static final' - accessible constant
                                        //         Used across all User instances
                                        //         Final prevents modification

    private final String userId;        // Line 5: Immutable user identifier
    private String name;                // Line 6: Mutable user name field
    private final List<String> borrowedBooks;   // Line 7: Collection to store borrowed book ISBNs
                                        //         'final' - reference can't change, but contents can
                                        //         List interface for flexibility

    public User(String userId, String name) {  // Line 8: Constructor with 2 parameters
        this.userId = userId;           // Line 9: Initialize immutable user ID
        this.name = name;               // Line 10: Initialize user name
        this.borrowedBooks = new ArrayList<>();    // Line 11: Initialize empty list
                                        //          ArrayList implementation of List interface
                                        //          Diamond operator <> infers type from declaration
    }                                   // Line 12: End of constructor

    public String getUserId() {         // Line 13: Getter for user ID
        return userId;                  // Line 14: Return immutable identifier
    }                                   // Line 15: End of getter

    public String getName() {           // Line 16: Getter for user name
        return name;                    // Line 17: Return current name value
    }                                   // Line 18: End of getter

    public String getRole() {           // Line 19: Business method - could be overridden in subclasses
        return "User";                  // Line 20: Default role for base User class
    }                                   // Line 21: End of method

    public void setName(String name) {  // Line 22: Setter for mutable name field
        this.name = name;               // Line 23: Update name field
    }                                   // Line 24: End of setter

    public List<String> getBorrowedBooks() {    // Line 25: Getter for borrowed books collection
        return new ArrayList<>(borrowedBooks);  // Line 26: DEFENSIVE COPY
                                        //          Create new ArrayList from existing one
                                        //          Prevents external modification of internal state
                                        //          Caller gets snapshot, not reference to actual list
    }                                   // Line 27: End of defensive getter

    public boolean canBorrowMoreBooks() {   // Line 28: Business rule method
        return borrowedBooks.size() < MAX_BOOKS_LIMIT; // Line 29: Check against class constant
                                        //                      size() returns current number of elements
                                        //                      Returns true if under limit
    }                                   // Line 30: End of business rule method

    public int getBorrowedBooksCount() { // Line 31: Convenience method for count
        return borrowedBooks.size();    // Line 32: Delegate to ArrayList's size method
    }                                   // Line 33: End of convenience method

    public int getRemainingBorrowLimit() {  // Line 34: Calculated business method
        return MAX_BOOKS_LIMIT - borrowedBooks.size();  // Line 35: Math operation
                                        //                        Subtract current count from limit
    }                                   // Line 36: End of calculated method

    public boolean hasBorrowedBook(String isbn) {   // Line 37: Check if specific book is borrowed
        return borrowedBooks.contains(isbn);        // Line 38: Use ArrayList's contains method
                                        //                      Returns true if ISBN is in list
    }                                   // Line 39: End of check method

    public boolean borrowBook(String isbn) {    // Line 40: Business operation method
        if (canBorrowMoreBooks() && !hasBorrowedBook(isbn)) {   // Line 41: Compound validation
                                        //          Check both conditions must be true:
                                        //          1. User can borrow more (under limit)
                                        //          2. User hasn't already borrowed this book (!not operator)
            borrowedBooks.add(isbn);    // Line 42: Add ISBN to collection
                                        //          ArrayList's add method appends to end
            return true;                // Line 43: Return success indicator
        }                               // Line 44: End of if block
        return false;                   // Line 45: Return failure indicator
                                        //          Execution reaches here if validation failed
    }                                   // Line 46: End of borrow method

    public boolean returnBook(String isbn) {    // Line 47: Return book operation
        return borrowedBooks.remove(isbn);      // Line 48: ArrayList's remove method
                                        //          Removes first occurrence of ISBN
                                        //          Returns true if element was found and removed
                                        //          Returns false if ISBN wasn't in list
    }                                   // Line 49: End of return method

    public String toString() {          // Line 50: Override Object's toString method
        return "User: " + name + " (" + userId + ") - Books: " + borrowedBooks.size() + "/" + MAX_BOOKS_LIMIT;
                                        // Line 51: Build descriptive string
                                        //          Shows name, ID, and borrowing status (current/max)
    }                                   // Line 52: End of toString method

}                                       // Line 53: End of User class
```

**Main.java (Updated)**
```java
package step3;                          // Line 1: Package declaration for step3

public class Main {                     // Line 2: Main class declaration
    public static void main(String[] args) {   // Line 3: Main method signature
        // Create sample books
        Book book1 = new Book("978-0134685991", "Effective Java", "Joshua Bloch");
                                        // Line 4: Create first book object
        Book book2 = new Book("978-0596009205", "Head First Design Patterns", "Freeman");
                                        // Line 5: Create second book object
        Book book3 = new Book("978-0321356680", "Effective C++", "Scott Meyers");
                                        // Line 6: Create third book object
        Book book4 = new Book("978-0132350884", "Clean Code", "Robert Martin");
                                        // Line 7: Create fourth book object

        // Create a user
        User user1 = new User("U001", "Alice Johnson");
                                        // Line 8: Create user with ID and name
        System.out.println("Created user: " + user1);
                                        // Line 9: Print user info using toString()

        // Test borrowing books
        System.out.println("\n--- Testing Book Borrowing ---");
                                        // Line 10: Print section header (\n creates new line)
        
        System.out.println("Can borrow more books: " + user1.canBorrowMoreBooks());
                                        // Line 11: Check initial borrowing capacity
        
        // Borrow first book
        boolean result1 = user1.borrowBook(book1.getIsbn());
                                        // Line 12: Attempt to borrow book1
                                        //          Store result for checking
        System.out.println("Borrow book1 result: " + result1);
                                        // Line 13: Display result of borrowing attempt
        System.out.println("User after borrowing book1: " + user1);
                                        // Line 14: Show updated user state
        
        // Borrow second book
        boolean result2 = user1.borrowBook(book2.getIsbn());
                                        // Line 15: Attempt to borrow book2
        System.out.println("Borrow book2 result: " + result2);
                                        // Line 16: Display result
        System.out.println("User after borrowing book2: " + user1);
                                        // Line 17: Show updated state
        
        // Borrow third book (should reach limit)
        boolean result3 = user1.borrowBook(book3.getIsbn());
                                        // Line 18: Attempt to borrow book3 (reaches limit)
        System.out.println("Borrow book3 result: " + result3);
                                        // Line 19: Display result
        System.out.println("User after borrowing book3: " + user1);
                                        // Line 20: Show state at limit
        
        // Try to borrow fourth book (should fail - over limit)
        boolean result4 = user1.borrowBook(book4.getIsbn());
                                        // Line 21: Attempt to borrow book4 (should fail)
        System.out.println("Borrow book4 result (should be false): " + result4);
                                        // Line 22: Display result - expecting false
        
        // Test duplicate borrowing (should fail)
        boolean duplicate = user1.borrowBook(book1.getIsbn());
                                        // Line 23: Try to borrow same book again
        System.out.println("Duplicate borrow result (should be false): " + duplicate);
                                        // Line 24: Display result - expecting false
        
        // Test defensive copying
        System.out.println("\n--- Testing Defensive Copying ---");
                                        // Line 25: Section header for defensive copy test
        
        java.util.List<String> borrowedList = user1.getBorrowedBooks();
                                        // Line 26: Get copy of borrowed books list
        System.out.println("Borrowed books: " + borrowedList);
                                        // Line 27: Display borrowed books
        
        // Try to modify the returned list (should not affect user's internal list)
        borrowedList.add("FAKE-ISBN");  // Line 28: Add fake ISBN to returned list
        System.out.println("After adding to returned list: " + borrowedList);
                                        // Line 29: Show modified external list
        System.out.println("User's actual borrowed books: " + user1.getBorrowedBooks());
                                        // Line 30: Show user's list is unchanged (defensive copy worked)
        
        // Test returning books
        System.out.println("\n--- Testing Book Returning ---");
                                        // Line 31: Section header for return testing
        
        boolean returnResult = user1.returnBook(book2.getIsbn());
                                        // Line 32: Return book2
        System.out.println("Return book2 result: " + returnResult);
                                        // Line 33: Display return result
        System.out.println("User after returning book2: " + user1);
                                        // Line 34: Show updated state
        
        // Try to return book not borrowed
        boolean badReturn = user1.returnBook("NON-EXISTENT-ISBN");
                                        // Line 35: Try to return book not borrowed
        System.out.println("Return non-existent book result (should be false): " + badReturn);
                                        // Line 36: Display result - expecting false
        
        // Show remaining capacity
        System.out.println("Remaining borrow limit: " + user1.getRemainingBorrowLimit());
                                        // Line 37: Show how many more books can be borrowed
    }                                   // Line 38: End of main method
}                                       // Line 39: End of Main class
```

### Key OOP Concepts Explained

#### 1. **Business Rules Implementation**
- **Class constants:** `public static final` for shared rules across all instances
- **Validation methods:** `canBorrowMoreBooks()` encapsulates business logic
- **Rule enforcement:** Methods check rules before allowing state changes
- **Error handling:** Return boolean vs throwing exceptions - design choice

#### 2. **Collection Management**
- **Interface vs Implementation:** Declare as `List`, instantiate as `ArrayList`
- **Final reference:** List reference can't change, but contents can
- **Collection methods:** `add()`, `remove()`, `contains()`, `size()`
- **Type safety:** Generics `<String>` prevent wrong types

#### 3. **Defensive Copying**
- **Problem:** Returning direct reference allows external modification
- **Solution:** Return new collection with same contents
- **Cost:** Performance overhead of copying
- **Benefit:** Encapsulation integrity maintained

#### 4. **State Validation**
- **Compound conditions:** Multiple checks with logical operators
- **Early return:** Return immediately when conditions aren't met
- **Atomic operations:** Borrow operation is all-or-nothing
- **State consistency:** Object remains in valid state regardless of operation success

### Hands-On Exercises
1. **Test business rules:** Try borrowing 4+ books
2. **Test defensive copying:** Modify returned list, verify original unchanged
3. **Edge cases:** Empty strings, null values, duplicate operations
4. **Extend functionality:** Add `borrowMultipleBooks()` method
5. **Different collections:** Try using `LinkedList` instead of `ArrayList`

### Session Discussion Points
- **Why use defensive copying?** Prevents external modification of internal state
- **ArrayList vs other collection types:** Performance characteristics, use cases
- **When to return boolean vs throw exceptions:** Design philosophy, error handling strategy
- **How business rules protect data integrity:** Validation prevents invalid states

---

## Step 4: Transaction Management and Relationships
**Duration:** 45 minutes  
**Concepts:** Object relationships, date handling, transaction patterns, immutable objects

### What We'll Build
- Create `BorrowTransaction` class to track borrowing history
- Implement proper date handling for audit trails
- Establish object relationships between entities
- Demonstrate transaction lifecycle management

### Implementation Files

#### Complete Code with Line-by-Line Documentation

**BorrowTransaction.java**
```java
package step4;                          // Line 1: Package declaration for step4

import java.util.*;                     // Line 2: Import java.util for Date class

public class BorrowTransaction {        // Line 3: Public class for transaction management
    private String transactionId;       // Line 4: Unique identifier for this transaction
    private String bookIsbn;            // Line 5: Reference to book (by ISBN)
                                        //         Using ISBN instead of Book object reference
                                        //         Avoids circular dependencies, enables serialization
    private String userId;              // Line 6: Reference to user (by user ID)
                                        //         Same pattern as bookIsbn
    private Date borrowDate;            // Line 7: When book was borrowed
                                        //         Date object captures specific moment in time
    private Date returnDate;            // Line 8: When book was returned (null until returned)
                                        //         Nullable field - indicates current state
    private boolean isReturned;         // Line 9: Boolean flag for quick status check
                                        //         Redundant with returnDate != null, but convenient

    public BorrowTransaction(String transactionId, String bookIsbn, String userId) {
                                        // Line 10: Constructor for new transaction
                                        //          Takes identifiers for transaction, book, and user
        this.transactionId = transactionId;    // Line 11: Set unique transaction identifier
        this.bookIsbn = bookIsbn;       // Line 12: Store book reference (ISBN)
        this.userId = userId;           // Line 13: Store user reference (user ID)
        this.borrowDate = new Date();   // Line 14: Capture current timestamp
                                        //          new Date() creates Date with current system time
                                        //          Automatically records when transaction was created
        this.returnDate = null;         // Line 15: Initialize as null (not returned yet)
                                        //          null indicates book is still borrowed
        this.isReturned = false;        // Line 16: Initialize as false (not returned yet)
                                        //          Boolean flag for quick status checks
    }                                   // Line 17: End of constructor

    // Getter methods for accessing transaction data
    public String getTransactionId() {  // Line 18: Getter for transaction ID
        return transactionId;           // Line 19: Return the unique identifier
    }                                   // Line 20: End of getter

    public String getBookIsbn() {       // Line 21: Getter for book ISBN
        return bookIsbn;                // Line 22: Return the book identifier
    }                                   // Line 23: End of getter

    public String getUserId() {         // Line 24: Getter for user ID
        return userId;                  // Line 25: Return the user identifier
    }                                   // Line 26: End of getter

    public Date getBorrowDate() {       // Line 27: Getter for borrow timestamp
        return borrowDate;              // Line 28: Return when book was borrowed
                                        //          Note: This returns direct reference to mutable Date
                                        //          In production, should return defensive copy
    }                                   // Line 29: End of getter

    public Date getReturnDate() {       // Line 30: Getter for return timestamp
        return returnDate;              // Line 31: Return when book was returned (or null)
    }                                   // Line 32: End of getter

    public boolean isReturned() {       // Line 33: Status check method
        return isReturned;              // Line 34: Return current return status
    }                                   // Line 35: End of status method

    // Business methods for transaction lifecycle
    public void markAsReturned() {      // Line 36: Method to complete transaction
                                        //          Called when book is returned to library
        this.returnDate = new Date();   // Line 37: Capture current timestamp as return time
                                        //          new Date() gets current system time
        this.isReturned = true;         // Line 38: Update boolean flag to true
                                        //          Both fields now indicate transaction is complete
    }                                   // Line 39: End of state change method

    public String getStatus() {         // Line 40: Business method for readable status
        return isReturned ? "Returned" : "Active";  // Line 41: Ternary operator
                                        //          condition ? valueIfTrue : valueIfFalse
                                        //          Returns "Returned" if isReturned is true
                                        //          Returns "Active" if isReturned is false
    }                                   // Line 42: End of status method

    public String toString() {          // Line 43: Override Object's toString method
        return "Transaction " + transactionId + ": Book " + bookIsbn + 
               " borrowed by " + userId + " - " + getStatus();
                                        // Line 44-45: Build descriptive string
                                        //             Includes all key identifiers and current status
                                        //             Uses getStatus() method for consistent status text
    }                                   // Line 46: End of toString method

}                                       // Line 47: End of BorrowTransaction class
```

**Updated Main.java**
```java
package step4;                          // Line 1: Package declaration for step4

public class Main {                     // Line 2: Main class declaration
    public static void main(String[] args) {   // Line 3: Main method signature
        // Create sample data
        Book book1 = new Book("978-0134685991", "Effective Java", "Joshua Bloch");
                                        // Line 4: Create book object for testing
        User user1 = new User("U001", "Alice Johnson");
                                        // Line 5: Create user object for testing
        
        System.out.println("=== Transaction Management Demo ===");
                                        // Line 6: Print demo section header
        
        // Create a borrow transaction
        BorrowTransaction transaction = new BorrowTransaction("T001", book1.getIsbn(), user1.getUserId());
                                        // Line 7: Create new transaction object
                                        //         Pass transaction ID, book ISBN, and user ID
                                        //         Constructor automatically captures current timestamp
        
        System.out.println("Created transaction: " + transaction);
                                        // Line 8: Display newly created transaction
                                        //         Should show "Active" status
        
        // Simulate some time passing (in real app, this would be days/weeks)
        try {
            Thread.sleep(1000);         // Line 9: Sleep for 1 second (1000 milliseconds)
                                        //         Simulates time passing between borrow and return
        } catch (InterruptedException e) {  // Line 10: Handle potential interrupt exception
            // Ignore interruption      // Line 11: Comment explaining exception handling
        }                               // Line 12: End of try-catch block
        
        // Display transaction details
        System.out.println("\nTransaction Details:");  // Line 13: Print details header
        System.out.println("Transaction ID: " + transaction.getTransactionId());
                                        // Line 14: Show transaction identifier
        System.out.println("Book ISBN: " + transaction.getBookIsbn());
                                        // Line 15: Show which book was borrowed
        System.out.println("User ID: " + transaction.getUserId());
                                        // Line 16: Show which user borrowed the book
        System.out.println("Borrow Date: " + transaction.getBorrowDate());
                                        // Line 17: Show when book was borrowed (timestamp)
        System.out.println("Return Date: " + transaction.getReturnDate());
                                        // Line 18: Show return date (should be null initially)
        System.out.println("Status: " + transaction.getStatus());
                                        // Line 19: Show current status (should be "Active")
        System.out.println("Is Returned: " + transaction.isReturned());
                                        // Line 20: Show boolean status (should be false)
        
        // Mark transaction as returned
        System.out.println("\n--- Returning Book ---");
                                        // Line 21: Print return section header
        transaction.markAsReturned();   // Line 22: Call method to complete transaction
                                        //          This sets return date and updates status
        
        System.out.println("Transaction after return: " + transaction);
                                        // Line 23: Display updated transaction
                                        //          Should now show "Returned" status
        
        // Show updated details
        System.out.println("\nUpdated Transaction Details:");
                                        // Line 24: Print updated details header
        System.out.println("Return Date: " + transaction.getReturnDate());
                                        // Line 25: Show return timestamp (should now have value)
        System.out.println("Status: " + transaction.getStatus());
                                        // Line 26: Show status (should be "Returned")
        System.out.println("Is Returned: " + transaction.isReturned());
                                        // Line 27: Show boolean flag (should be true)
        
        // Demonstrate multiple transactions
        System.out.println("\n=== Multiple Transactions Demo ===");
                                        // Line 28: Print multiple transactions section
        
        Book book2 = new Book("978-0596009205", "Head First Design Patterns", "Freeman");
                                        // Line 29: Create second book for demo
        User user2 = new User("U002", "Bob Smith");
                                        // Line 30: Create second user for demo
        
        // Create multiple transactions to show relationships
        BorrowTransaction t1 = new BorrowTransaction("T002", book1.getIsbn(), user2.getUserId());
                                        // Line 31: User2 borrows book1
        BorrowTransaction t2 = new BorrowTransaction("T003", book2.getIsbn(), user1.getUserId());
                                        // Line 32: User1 borrows book2
        BorrowTransaction t3 = new BorrowTransaction("T004", book2.getIsbn(), user2.getUserId());
                                        // Line 33: User2 borrows book2 (after user1 returns it)
        
        System.out.println("Active transactions:");
                                        // Line 34: Print active transactions header
        System.out.println(t1);         // Line 35: Display transaction 1
        System.out.println(t2);         // Line 36: Display transaction 2  
        System.out.println(t3);         // Line 37: Display transaction 3
        
        // Return some books
        t1.markAsReturned();            // Line 38: Return book in transaction 1
        t2.markAsReturned();            // Line 39: Return book in transaction 2
        
        System.out.println("\nAfter some returns:");
                                        // Line 40: Print after returns header
        System.out.println(t1);         // Line 41: Show transaction 1 (returned)
        System.out.println(t2);         // Line 42: Show transaction 2 (returned)
        System.out.println(t3);         // Line 43: Show transaction 3 (still active)
        
        // Demonstrate object relationships
        System.out.println("\n=== Object Relationships Demo ===");
                                        // Line 44: Print relationships section header
        
        // Show how transactions link books and users
        System.out.println("Book '" + book1.getTitle() + "' (ISBN: " + book1.getIsbn() + ")");
        System.out.println("  - Originally borrowed by " + user1.getName() + " (Transaction: " + transaction.getTransactionId() + ")");
        System.out.println("  - Later borrowed by " + user2.getName() + " (Transaction: " + t1.getTransactionId() + ")");
                                        // Lines 45-47: Show how one book can have multiple transactions
                                        //               over time with different users
        
        System.out.println("\nUser '" + user1.getName() + "' (ID: " + user1.getUserId() + ")");
        System.out.println("  - Borrowed '" + book1.getTitle() + "' (Transaction: " + transaction.getTransactionId() + ")");
        System.out.println("  - Borrowed '" + book2.getTitle() + "' (Transaction: " + t2.getTransactionId() + ")");
                                        // Lines 48-50: Show how one user can have multiple transactions
                                        //               with different books
    }                                   // Line 51: End of main method
}                                       // Line 52: End of Main class
```

### Key OOP Concepts Explained

#### 1. **Object Relationships**
- **Association:** Transaction associates Book and User without owning them
- **Reference by ID:** Using ISBN/UserID instead of object references
- **Loose coupling:** Objects can exist independently
- **Audit trail:** Transactions record historical relationships

#### 2. **State Management**
- **Immutable creation:** Transaction details set once at creation
- **State transitions:** `markAsReturned()` changes state atomically  
- **Redundant data:** Both `returnDate` and `isReturned` track same information
- **Consistency:** Both fields updated together to maintain consistency

#### 3. **Temporal Data Handling**
- **Timestamp capture:** `new Date()` records exact moment
- **Nullable dates:** `returnDate` is null until book returned
- **Date immutability:** Date objects can be modified (design flaw in Java)
- **Defensive copying:** Should copy Date objects in getters (not shown here)

#### 4. **Transaction Patterns**
- **Unique identifiers:** Each transaction has unique ID
- **Lifecycle management:** Created → Active → Returned
- **Audit information:** Who, what, when recorded permanently
- **Historical record:** Transactions never deleted, only marked complete

### Hands-On Exercises
1. **Create transaction chains:** Multiple transactions for same book over time
2. **Date manipulation:** Create transactions with specific dates
3. **Relationship mapping:** Track which user has which books currently
4. **State validation:** Ensure transactions can't be "un-returned"
5. **Defensive copying:** Fix Date getter methods to return copies

### Session Discussion Points
- **Why separate transactions from books/users?** Historical record, audit trail, different lifecycle
- **Benefits of immutable transaction IDs:** Uniqueness, referential integrity
- **Date vs LocalDateTime considerations:** Java 8+ alternatives, timezone handling
- **How transactions enable audit trails:** Complete borrowing history, compliance, analytics

---

## Step 5: System Integration and Data Persistence
**Duration:** 60 minutes  
**Concepts:** System design, HashMap for fast lookup, database integration, CRUD operations, transaction coordination

### What We'll Build
- Create `SimpleLibrary` class as the system coordinator
- Implement CRUD operations for all entities
- Add optional database persistence with SQLite
- Demonstrate complete system workflows

### Implementation Files

#### Complete Code with Line-by-Line Documentation

**SimpleLibrary.java**
```java
package step5;                          // Line 1: Package declaration for step5

import java.util.*;                     // Line 2: Import collections framework
import java.sql.*;                      // Line 3: Import JDBC classes for database access

public class SimpleLibrary {            // Line 4: Main system coordinator class
    // In-memory storage for quick access
    private HashMap<String, Book> books;            // Line 5: Fast book lookup by ISBN
                                        //         HashMap provides O(1) average lookup time
                                        //         Key: ISBN (String), Value: Book object
    private HashMap<String, User> users;            // Line 6: Fast user lookup by user ID
                                        //         Key: User ID (String), Value: User object
    private HashMap<String, BorrowTransaction> transactions;    // Line 7: Transaction lookup by ID
                                        //         Key: Transaction ID, Value: Transaction object
    
    // System state and configuration
    private int transactionCounter;                 // Line 8: Counter for generating unique transaction IDs
                                        //         Incremented each time new transaction created
    private boolean useDatabase;                    // Line 9: Flag indicating if database is available
                                        //         Falls back to in-memory only if database fails
    private Connection connection;                  // Line 10: JDBC connection object for database
                                        //          null if database not available

    public SimpleLibrary() {            // Line 11: Constructor - initializes system
        this.books = new HashMap<>();   // Line 12: Initialize empty book storage
                                        //          HashMap chosen for fast ISBN-based lookups
        this.users = new HashMap<>();   // Line 13: Initialize empty user storage
        this.transactions = new HashMap<>();        // Line 14: Initialize empty transaction storage
        this.transactionCounter = 1;    // Line 15: Start transaction counter at 1
                                        //          First transaction will be T001
        this.useDatabase = false;       // Line 16: Default to no database until connection succeeds
        initializeDatabase();           // Line 17: Attempt to establish database connection
                                        //          If successful, creates tables and sets useDatabase=true
    }                                   // Line 18: End of constructor

    private void initializeDatabase() { // Line 19: Private helper method for database setup
        try {                           // Line 20: Try block for exception handling
            connection = DriverManager.getConnection("jdbc:sqlite:library.db");
                                        // Line 21: Create SQLite database connection
                                        //          "jdbc:sqlite:" is SQLite JDBC URL prefix
                                        //          "library.db" is database file name
                                        //          File created automatically if doesn't exist
            createTables();             // Line 22: Call method to create database tables
            useDatabase = true;         // Line 23: Set flag to indicate database is available
            System.out.println("Database connected successfully");
                                        // Line 24: Confirmation message for successful connection
        } catch (SQLException e) {      // Line 25: Catch any SQL-related exceptions
            System.out.println("Database not available, using in-memory storage");
                                        // Line 26: Inform user that system falls back to memory-only
            useDatabase = false;        // Line 27: Ensure flag indicates no database
                                        //          System continues to work without persistence
        }                               // Line 28: End of try-catch block
    }                                   // Line 29: End of database initialization method

    private void createTables() throws SQLException {   // Line 30: Method to create database schema
                                        //              throws SQLException - caller must handle exceptions
        String[] createStatements = {   // Line 31: Array of SQL CREATE TABLE statements
            "CREATE TABLE IF NOT EXISTS books (isbn TEXT PRIMARY KEY, title TEXT, author TEXT, status TEXT)",
                                        // Line 32: Books table - ISBN as primary key
                                        //          IF NOT EXISTS prevents error if table already exists
            "CREATE TABLE IF NOT EXISTS users (user_id TEXT PRIMARY KEY, name TEXT, borrowed_count INTEGER DEFAULT 0)",
                                        // Line 33: Users table - user_id as primary key
                                        //          borrowed_count tracks current number of borrowed books
            "CREATE TABLE IF NOT EXISTS transactions (transaction_id TEXT PRIMARY KEY, book_isbn TEXT, user_id TEXT, borrow_date TEXT, return_date TEXT, is_returned INTEGER DEFAULT 0)"
                                        // Line 34: Transactions table - transaction_id as primary key
                                        //          Foreign keys reference books and users tables
                                        //          is_returned stored as INTEGER (0=false, 1=true)
        };                              // Line 35: End of SQL statements array
        
        for (String sql : createStatements) {  // Line 36: Loop through each CREATE statement
            connection.createStatement().execute(sql);  // Line 37: Execute SQL statement
                                        //          createStatement() creates Statement object
                                        //          execute() runs the SQL command
        }                               // Line 38: End of loop
    }                                   // Line 39: End of table creation method

    // Book management methods (CRUD operations)
    public boolean addBook(Book book) { // Line 40: Create operation for books
        if (book == null) return false; // Line 41: Null check - defensive programming
                                        //          Return false for invalid input
        
        if (useDatabase) {              // Line 42: If database available, persist the book
            try {                       // Line 43: Try block for database operation
                String sql = "INSERT INTO books (isbn, title, author, status) VALUES (?, ?, ?, ?)";
                                        // Line 44: Parameterized SQL INSERT statement
                                        //          ? placeholders prevent SQL injection
                PreparedStatement stmt = connection.prepareStatement(sql);
                                        // Line 45: Create prepared statement with SQL
                                        //          PreparedStatement allows parameter binding
                stmt.setString(1, book.getIsbn());     // Line 46: Bind ISBN to first parameter
                stmt.setString(2, book.getTitle());    // Line 47: Bind title to second parameter
                stmt.setString(3, book.getAuthor());   // Line 48: Bind author to third parameter
                stmt.setString(4, book.getStatus().toString()); // Line 49: Bind status enum as string
                stmt.executeUpdate();   // Line 50: Execute the INSERT statement
                                        //          executeUpdate() for INSERT/UPDATE/DELETE
            } catch (SQLException e) {  // Line 51: Catch database errors
                return false;           // Line 52: Return failure if database operation fails
                                        //          In-memory operation still proceeds below
            }                           // Line 53: End of try-catch block
        }                               // Line 54: End of database persistence block
        
        books.put(book.getIsbn(), book);    // Line 55: Store in memory regardless of database success
                                        //              HashMap's put() method adds/replaces entry
        return true;                    // Line 56: Return success
    }                                   // Line 57: End of addBook method

    public boolean updateBook(String isbn, String newTitle, String newAuthor) {
                                        // Line 58: Update operation for books
                                        //          Takes ISBN to identify book, new values to set
        Book book = books.get(isbn);    // Line 59: Look up book in memory by ISBN
        if (book == null) return false; // Line 60: Return failure if book not found
        
        book.setTitle(newTitle);        // Line 61: Update book object in memory
        book.setAuthor(newAuthor);      // Line 62: Update author in memory
        
        if (useDatabase) {              // Line 63: If database available, persist changes
            try {                       // Line 64: Try block for database operation
                String sql = "UPDATE books SET title = ?, author = ? WHERE isbn = ?";
                                        // Line 65: Parameterized UPDATE statement
                                        //          WHERE clause specifies which book to update
                PreparedStatement stmt = connection.prepareStatement(sql);
                                        // Line 66: Create prepared statement
                stmt.setString(1, newTitle);   // Line 67: Bind new title
                stmt.setString(2, newAuthor);  // Line 68: Bind new author
                stmt.setString(3, isbn);       // Line 69: Bind ISBN for WHERE clause
                stmt.executeUpdate();   // Line 70: Execute UPDATE statement
            } catch (SQLException e) {  // Line 71: Catch database errors
                return false;           // Line 72: Return failure if database update fails
            }                           // Line 73: End of try-catch
        }                               // Line 74: End of database update block
        
        return true;                    // Line 75: Return success
    }                                   // Line 76: End of updateBook method

    public boolean removeBook(String isbn) {    // Line 77: Delete operation for books
        Book book = books.get(isbn);    // Line 78: Look up book to check if exists
        if (book == null) return false; // Line 79: Return failure if book not found
        if (book.getStatus() == BookStatus.BORROWED) return false;
                                        // Line 80: Business rule: can't delete borrowed books
                                        //          Prevents data integrity issues
        
        if (useDatabase) {              // Line 81: If database available, delete from database
            try {                       // Line 82: Try block for database operation
                String sql = "DELETE FROM books WHERE isbn = ?";
                                        // Line 83: DELETE statement with WHERE clause
                PreparedStatement stmt = connection.prepareStatement(sql);
                                        // Line 84: Create prepared statement
                stmt.setString(1, isbn);        // Line 85: Bind ISBN parameter
                stmt.executeUpdate();   // Line 86: Execute DELETE statement
            } catch (SQLException e) {  // Line 87: Catch database errors
                return false;           // Line 88: Return failure if database delete fails
            }                           // Line 89: End of try-catch
        }                               // Line 90: End of database delete block
        
        books.remove(isbn);             // Line 91: Remove from memory storage
                                        //          HashMap's remove() method deletes entry
        return true;                    // Line 92: Return success
    }                                   // Line 93: End of removeBook method

    // User management methods (similar CRUD pattern)
    public boolean addUser(User user) { // Line 94: Create operation for users
        if (user == null) return false; // Line 95: Null check
        
        if (useDatabase) {              // Line 96: Database persistence if available
            try {                       // Line 97: Try block
                String sql = "INSERT INTO users (user_id, name, borrowed_count) VALUES (?, ?, ?)";
                                        // Line 98: INSERT statement for users table
                PreparedStatement stmt = connection.prepareStatement(sql);
                                        // Line 99: Create prepared statement
                stmt.setString(1, user.getUserId());   // Line 100: Bind user ID
                stmt.setString(2, user.getName());     // Line 101: Bind user name
                stmt.setInt(3, user.getBorrowedBooksCount());   // Line 102: Bind borrowed count
                stmt.executeUpdate();   // Line 103: Execute INSERT
            } catch (SQLException e) {  // Line 104: Catch database errors
                return false;           // Line 105: Return failure
            }                           // Line 106: End try-catch
        }                               // Line 107: End database block
        
        users.put(user.getUserId(), user);  // Line 108: Store in memory
        return true;                    // Line 109: Return success
    }                                   // Line 110: End addUser method

    public boolean updateUser(String userId, String newName) {  // Line 111: Update user
        User user = users.get(userId);  // Line 112: Look up user
        if (user == null) return false; // Line 113: Check if exists
        
        user.setName(newName);          // Line 114: Update in memory
        
        if (useDatabase) {              // Line 115: Database persistence
            try {                       // Line 116: Try block
                String sql = "UPDATE users SET name = ? WHERE user_id = ?";
                                        // Line 117: UPDATE statement
                PreparedStatement stmt = connection.prepareStatement(sql);
                                        // Line 118: Prepared statement
                stmt.setString(1, newName);    // Line 119: Bind new name
                stmt.setString(2, userId);     // Line 120: Bind user ID
                stmt.executeUpdate();   // Line 121: Execute UPDATE
            } catch (SQLException e) {  // Line 122: Catch errors
                return false;           // Line 123: Return failure
            }                           // Line 124: End try-catch
        }                               // Line 125: End database block
        
        return true;                    // Line 126: Return success
    }                                   // Line 127: End updateUser method

    public boolean removeUser(String userId) {  // Line 128: Delete user
        User user = users.get(userId);  // Line 129: Look up user
        if (user == null) return false; // Line 130: Check exists
        if (user.getBorrowedBooksCount() > 0) return false;
                                        // Line 131: Business rule: can't delete users with borrowed books
        
        if (useDatabase) {              // Line 132: Database deletion
            try {                       // Line 133: Try block
                String sql = "DELETE FROM users WHERE user_id = ?";
                                        // Line 134: DELETE statement
                PreparedStatement stmt = connection.prepareStatement(sql);
                                        // Line 135: Prepared statement
                stmt.setString(1, userId);     // Line 136: Bind user ID
                stmt.executeUpdate();   // Line 137: Execute DELETE
            } catch (SQLException e) {  // Line 138: Catch errors
                return false;           // Line 139: Return failure
            }                           // Line 140: End try-catch
        }                               // Line 141: End database block
        
        users.remove(userId);           // Line 142: Remove from memory
        return true;                    // Line 143: Return success
    }                                   // Line 144: End removeUser method

    // Core business operations - these coordinate multiple objects
    public String borrowBook(String isbn, String userId) {     // Line 145: Complex business operation
                                        //          Returns String message instead of boolean
                                        //          Allows detailed error messages
        Book book = getBook(isbn);      // Line 146: Get book object from storage
        User user = getUser(userId);    // Line 147: Get user object from storage

        // Validation phase - check all preconditions
        if (book == null) return "Book not found";     // Line 148: Validate book exists
        if (user == null) return "User not found";     // Line 149: Validate user exists
        if (!book.isAvailable()) return "Book is not available";       // Line 150: Check book status
        if (!user.canBorrowMoreBooks()) return "User has reached maximum book limit";
                                        // Line 151: Check user's borrowing limit

        // All validations passed - proceed with transaction
        String transactionId = "T" + String.format("%03d", transactionCounter++);
                                        // Line 152: Generate unique transaction ID
                                        //          String.format("%03d", n) formats number with leading zeros
                                        //          transactionCounter++ increments after using value
        BorrowTransaction transaction = new BorrowTransaction(transactionId, isbn, userId);
                                        // Line 153: Create new transaction object
                                        //          Constructor automatically sets borrow date

        // Update object states
        book.setStatus(BookStatus.BORROWED);    // Line 154: Mark book as borrowed
        user.borrowBook(isbn);          // Line 155: Add book to user's borrowed list
                                        //          User.borrowBook() handles the list management

        // Database persistence for all changes
        if (useDatabase) {              // Line 156: If database available, persist all changes
            try {                       // Line 157: Try block for database operations
                // Update book status in database
                String updateBookSql = "UPDATE books SET status = ? WHERE isbn = ?";
                                        // Line 158: SQL to update book status
                PreparedStatement stmt = connection.prepareStatement(updateBookSql);
                                        // Line 159: Prepare statement
                stmt.setString(1, "BORROWED");  // Line 160: Set status to BORROWED
                stmt.setString(2, isbn);        // Line 161: Specify which book
                stmt.executeUpdate();   // Line 162: Execute book update

                // Update user borrowed count in database
                String updateUserSql = "UPDATE users SET borrowed_count = ? WHERE user_id = ?";
                                        // Line 163: SQL to update user's borrowed count
                stmt = connection.prepareStatement(updateUserSql);
                                        // Line 164: Reuse stmt variable for new statement
                stmt.setInt(1, user.getBorrowedBooksCount());   // Line 165: Set new count
                stmt.setString(2, userId);      // Line 166: Specify which user
                stmt.executeUpdate();   // Line 167: Execute user update

                // Insert transaction record
                String insertTransactionSql = "INSERT INTO transactions (transaction_id, book_isbn, user_id, borrow_date, is_returned) VALUES (?, ?, ?, ?, ?)";
                                        // Line 168: SQL to insert new transaction
                stmt = connection.prepareStatement(insertTransactionSql);
                                        // Line 169: Prepare transaction insert
                stmt.setString(1, transactionId);      // Line 170: Bind transaction ID
                stmt.setString(2, isbn);        // Line 171: Bind book ISBN
                stmt.setString(3, userId);      // Line 172: Bind user ID
                stmt.setString(4, transaction.getBorrowDate().toString());
                                        // Line 173: Bind borrow date as string
                stmt.setInt(5, 0);              // Line 174: Set is_returned to 0 (false)
                stmt.executeUpdate();   // Line 175: Execute transaction insert
            } catch (SQLException e) {  // Line 176: Catch any database errors
                System.out.println("Database update failed: " + e.getMessage());
                                        // Line 177: Log error but continue
                                        //          Operation succeeds in memory even if database fails
            }                           // Line 178: End try-catch
        }                               // Line 179: End database block

        transactions.put(transactionId, transaction);  // Line 180: Store transaction in memory
        return "Success! Transaction ID: " + transactionId;    // Line 181: Return success message
    }                                   // Line 182: End borrowBook method

    public String returnBook(String isbn, String userId) {     // Line 183: Return book operation
        Book book = getBook(isbn);      // Line 184: Get book object
        User user = getUser(userId);    // Line 185: Get user object

        // Validation phase
        if (book == null) return "Book not found";     // Line 186: Validate book exists
        if (user == null) return "User not found";     // Line 187: Validate user exists
        if (book.isAvailable()) return "Book is not currently borrowed";
                                        // Line 188: Check book is actually borrowed
        if (!user.hasBorrowedBook(isbn)) return "User has not borrowed this book";
                                        // Line 189: Verify user has this book

        // Find the active transaction for this book and user
        BorrowTransaction transaction = null;   // Line 190: Variable to hold found transaction
        for (BorrowTransaction t : transactions.values()) {     // Line 191: Loop through all transactions
            if (t.getBookIsbn().equals(isbn) && t.getUserId().equals(userId) && !t.isReturned()) {
                                        // Line 192: Check if transaction matches:
                                        //          - Same book ISBN
                                        //          - Same user ID  
                                        //          - Not already returned
                transaction = t;        // Line 193: Found the active transaction
                break;                  // Line 194: Exit loop early
            }                           // Line 195: End if condition
        }                               // Line 196: End for loop

        if (transaction == null) return "No active transaction found";
                                        // Line 197: Return error if no matching transaction

        // Update object states
        book.setStatus(BookStatus.AVAILABLE);   // Line 198: Mark book as available again
        user.returnBook(isbn);          // Line 199: Remove book from user's borrowed list
        transaction.markAsReturned();   // Line 200: Mark transaction as complete
                                        //          This sets return date and isReturned flag

        // Database persistence for all changes
        if (useDatabase) {              // Line 201: Database updates if available
            try {                       // Line 202: Try block
                // Update book status
                String updateBookSql = "UPDATE books SET status = ? WHERE isbn = ?";
                                        // Line 203: SQL to mark book available
                PreparedStatement stmt = connection.prepareStatement(updateBookSql);
                                        // Line 204: Prepare statement
                stmt.setString(1, "AVAILABLE");        // Line 205: Set status to AVAILABLE
                stmt.setString(2, isbn);        // Line 206: Specify book
                stmt.executeUpdate();   // Line 207: Execute update

                // Update user borrowed count
                String updateUserSql = "UPDATE users SET borrowed_count = ? WHERE user_id = ?";
                                        // Line 208: SQL to update user count
                stmt = connection.prepareStatement(updateUserSql);
                                        // Line 209: Prepare statement
                stmt.setInt(1, user.getBorrowedBooksCount());   // Line 210: Set new count
                stmt.setString(2, userId);      // Line 211: Specify user
                stmt.executeUpdate();   // Line 212: Execute update

                // Update transaction
                String updateTransactionSql = "UPDATE transactions SET return_date = ?, is_returned = 1 WHERE transaction_id = ?";
                                        // Line 213: SQL to mark transaction returned
                stmt = connection.prepareStatement(updateTransactionSql);
                                        // Line 214: Prepare statement
                stmt.setString(1, transaction.getReturnDate().toString());
                                        // Line 215: Set return date
                stmt.setString(2, transaction.getTransactionId());
                                        // Line 216: Specify transaction
                stmt.executeUpdate();   // Line 217: Execute update
            } catch (SQLException e) {  // Line 218: Catch database errors
                System.out.println("Database update failed: " + e.getMessage());
                                        // Line 219: Log error
            }                           // Line 220: End try-catch
        }                               // Line 221: End database block

        return "Book returned successfully";    // Line 222: Return success message
    }                                   // Line 223: End returnBook method

    // Read operations (getters)
    public Book getBook(String isbn) {  // Line 224: Get single book by ISBN
        return books.get(isbn);         // Line 225: HashMap lookup - O(1) average time
    }                                   // Line 226: End getter

    public User getUser(String userId) { // Line 227: Get single user by ID
        return users.get(userId);       // Line 228: HashMap lookup
    }                                   // Line 229: End getter

    public List<Book> getAllBooks() {   // Line 230: Get all books as list
        if (useDatabase) {              // Line 231: If database available, refresh from database
            loadBooksFromDatabase();    // Line 232: Reload data to ensure consistency
        }                               // Line 233: End database refresh
        return new ArrayList<>(books.values());    // Line 234: Return defensive copy
                                        //          books.values() gets all Book objects
                                        //          new ArrayList<>() creates copy
    }                                   // Line 235: End getAllBooks

    public List<User> getAllUsers() {   // Line 236: Get all users as list
        if (useDatabase) {              // Line 237: Database refresh if available
            loadUsersFromDatabase();    // Line 238: Reload users from database
        }                               // Line 239: End database refresh
        return new ArrayList<>(users.values());   // Line 240: Return defensive copy
    }                                   // Line 241: End getAllUsers

    public List<BorrowTransaction> getAllTransactions() {      // Line 242: Get all transactions
        if (useDatabase) {              // Line 243: Database refresh if available
            loadTransactionsFromDatabase();     // Line 244: Reload transactions
        }                               // Line 245: End database refresh
        return new ArrayList<>(transactions.values());        // Line 246: Return defensive copy
    }                                   // Line 247: End getAllTransactions

    // Database loading methods - sync memory with database
    private void loadBooksFromDatabase() {      // Line 248: Private method to load books from database
        try {                           // Line 249: Try block
            String sql = "SELECT * FROM books";        // Line 250: SQL to get all books
            Statement stmt = connection.createStatement();     // Line 251: Create statement
            ResultSet rs = stmt.executeQuery(sql);     // Line 252: Execute query, get results
            
            books.clear();              // Line 253: Clear memory storage
                                        //          Replace with database contents
            while (rs.next()) {         // Line 254: Loop through result rows
                                        //          rs.next() moves to next row, returns false when done
                Book book = new Book(rs.getString("isbn"), rs.getString("title"), rs.getString("author"));
                                        // Line 255: Create Book object from database row
                                        //          rs.getString("column") gets string value from column
                book.setStatus(BookStatus.valueOf(rs.getString("status")));
                                        // Line 256: Convert string back to enum
                                        //          BookStatus.valueOf() parses string to enum constant
                books.put(book.getIsbn(), book);       // Line 257: Store in memory HashMap
            }                           // Line 258: End while loop
        } catch (SQLException e) {      // Line 259: Catch database errors
            System.out.println("Failed to load books from database: " + e.getMessage());
                                        // Line 260: Log error message
        }                               // Line 261: End try-catch
    }                                   // Line 262: End loadBooksFromDatabase

    private void loadUsersFromDatabase() {      // Line 263: Load users from database
        try {                           // Line 264: Try block
            String sql = "SELECT * FROM users";        // Line 265: SQL to get all users
            Statement stmt = connection.createStatement();     // Line 266: Create statement
            ResultSet rs = stmt.executeQuery(sql);     // Line 267: Execute query
            
            users.clear();              // Line 268: Clear memory storage
            while (rs.next()) {         // Line 269: Loop through results
                User user = new User(rs.getString("user_id"), rs.getString("name"));
                                        // Line 270: Create User object from database
                users.put(user.getUserId(), user);     // Line 271: Store in memory
            }                           // Line 272: End while loop
        } catch (SQLException e) {      // Line 273: Catch errors
            System.out.println("Failed to load users from database: " + e.getMessage());
                                        // Line 274: Log error
        }                               // Line 275: End try-catch
    }                                   // Line 276: End loadUsersFromDatabase

    private void loadTransactionsFromDatabase() {      // Line 277: Load transactions from database
        try {                           // Line 278: Try block
            String sql = "SELECT * FROM transactions";         // Line 279: SQL to get all transactions
            Statement stmt = connection.createStatement();     // Line 280: Create statement
            ResultSet rs = stmt.executeQuery(sql);     // Line 281: Execute query
            
            transactions.clear();       // Line 282: Clear memory storage
            while (rs.next()) {         // Line 283: Loop through results
                BorrowTransaction transaction = new BorrowTransaction(
                    rs.getString("transaction_id"),    // Line 284: Get transaction ID
                    rs.getString("book_isbn"),         // Line 285: Get book ISBN
                    rs.getString("user_id")            // Line 286: Get user ID
                );                      // Line 287: End constructor call
                if (rs.getInt("is_returned") == 1) {   // Line 288: Check if returned
                                        //          Database stores boolean as INTEGER (0/1)
                    transaction.markAsReturned();      // Line 289: Mark as returned if needed
                }                       // Line 290: End if
                transactions.put(transaction.getTransactionId(), transaction);
                                        // Line 291: Store in memory HashMap
            }                           // Line 292: End while loop
        } catch (SQLException e) {      // Line 293: Catch errors
            System.out.println("Failed to load transactions from database: " + e.getMessage());
                                        // Line 294: Log error
        }                               // Line 295: End try-catch
    }                                   // Line 296: End loadTransactionsFromDatabase

    // System information and maintenance
    public void printStatus() {         // Line 297: Method to display system status
        System.out.println("\n=== Library Status ===");      // Line 298: Print header
        System.out.println("Storage: " + (useDatabase ? "Database" : "In-Memory"));
                                        // Line 299: Show storage type
        System.out.println("Books: " + books.size());         // Line 300: Show book count
        System.out.println("Users: " + users.size());         // Line 301: Show user count
        System.out.println("Transactions: " + transactions.size());   // Line 302: Show transaction count
    }                                   // Line 303: End printStatus

    public void close() {               // Line 304: Cleanup method for system shutdown
        if (connection != null) {       // Line 305: Check if database connection exists
            try {                       // Line 306: Try block
                connection.close();     // Line 307: Close database connection
                                        //          Releases database resources
            } catch (SQLException e) {  // Line 308: Catch close errors
                System.out.println("Error closing database: " + e.getMessage());
                                        // Line 309: Log close error
            }                           // Line 310: End try-catch
        }                               // Line 311: End if
    }                                   // Line 312: End close method
}                                       // Line 313: End SimpleLibrary class
```

### Key OOP Concepts Explained

#### 1. **System Architecture**
- **Facade pattern:** SimpleLibrary provides unified interface to entire system
- **Coordinator pattern:** Central class manages interactions between entities
- **Data access abstraction:** Same interface whether using database or memory
- **Separation of concerns:** Business logic separate from data persistence

#### 2. **CRUD Pattern Implementation**
- **Consistent interface:** All entities have add/get/update/remove methods
- **Return value strategy:** Boolean for simple operations, String for complex ones
- **Error handling:** Graceful degradation when database unavailable
- **Data validation:** Business rules enforced before state changes

#### 3. **Business Logic Coordination**
- **Transaction management:** Multiple object updates in single operation
- **Validation phase:** All checks before any state changes
- **Atomic operations:** Either all changes succeed or none do
- **State consistency:** Objects kept in valid state throughout operation

#### 4. **Database Integration**
- **Optional persistence:** System works with or without database
- **Connection management:** Single connection reused throughout application
- **SQL injection prevention:** Parameterized queries with prepared statements
- **Error isolation:** Database failures don't crash application

### Hands-On Exercises
1. **CRUD operations:** Test all create, read, update, delete operations
2. **Business workflows:** Complete borrow/return cycles
3. **Database persistence:** Stop/restart application, verify data survives
4. **Error scenarios:** Test with invalid data, database disconnection
5. **Performance testing:** Load many books/users, measure operation speed

### Session Discussion Points
- **HashMap vs ArrayList for lookups:** O(1) vs O(n) performance characteristics
- **Why coordinate business logic in one place?** Consistency, validation, transaction management
- **Benefits of database abstraction:** Flexibility, testing, graceful degradation
- **Error handling strategies:** Exceptions vs return codes, fail-fast vs graceful degradation

---

## Step 6: GUI Implementation with Swing
**Duration:** 75 minutes  
**Concepts:** MVC pattern, event handling, GUI design, user experience, component organization

### What We'll Build
- Create complete GUI with `LibraryGUI` class
- Implement tabbed interface for different operations
- Add event handlers for all user interactions
- Create modal dialog boxes for data entry
- Implement table-based data display with real-time updates

### Implementation Files

#### Complete Code with Line-by-Line Documentation

**LibraryGUI.java**
```java
package step6;                          // Line 1: Package declaration for step6

import javax.swing.*;                   // Line 2: Import Swing GUI components
import javax.swing.table.DefaultTableModel;    // Line 3: Import table model for data display
import java.awt.*;                      // Line 4: Import AWT layout and event classes
import java.awt.event.MouseAdapter;     // Line 5: Import mouse event handling
import java.awt.event.MouseEvent;       // Line 6: Import mouse event class
import java.util.List;                  // Line 7: Import List interface

public class LibraryGUI extends JFrame {        // Line 8: Main GUI class extends JFrame
                                        //         JFrame provides window with title bar, borders
                                        //         'extends' establishes IS-A relationship
    private SimpleLibrary library;      // Line 9: Reference to business logic layer
                                        //         GUI delegates all business operations to this object
    private JTabbedPane tabbedPane;     // Line 10: Container for multiple tabs
                                        //          Organizes different functional areas
    
    // Book management GUI components
    private JTable booksTable;          // Line 11: Table widget to display book data
    private DefaultTableModel booksTableModel;  // Line 12: Data model for books table
                                        //                  Manages table data and structure
    
    // User management GUI components  
    private JTable usersTable;          // Line 13: Table widget for user data
    private DefaultTableModel usersTableModel;  // Line 14: Data model for users table
    
    // Transaction GUI components
    private JTextField borrowIsbnField, borrowUserIdField;   // Line 15: Input fields for borrowing
    private JTextField returnIsbnField, returnUserIdField;   // Line 16: Input fields for returning
    private JTable transactionsTable;   // Line 17: Table for transaction history
    private DefaultTableModel transactionsTableModel;       // Line 18: Transaction table data model
    private JTextArea statusArea;       // Line 19: Multi-line text area for status messages

    public LibraryGUI() {               // Line 20: Constructor - initializes entire GUI
        library = new SimpleLibrary();  // Line 21: Create business logic layer
                                        //          All data operations delegated to this object
        initializeGUI();                // Line 22: Set up window structure and components
        setupEventHandlers();           // Line 23: Attach event listeners to components
        refreshAllTables();             // Line 24: Load initial data into tables
    }                                   // Line 25: End constructor

    private void initializeGUI() {      // Line 26: Private method to create GUI structure
        setTitle("Simple Library Management System");       // Line 27: Set window title
                                        //          Appears in title bar and taskbar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // Line 28: Define close behavior
                                        //          EXIT_ON_CLOSE terminates application when window closed
        setSize(900, 700);              // Line 29: Set window dimensions (width, height in pixels)
        setLocationRelativeTo(null);    // Line 30: Center window on screen
                                        //          null parameter centers relative to screen

        tabbedPane = new JTabbedPane(); // Line 31: Create tabbed pane container
                                        //          Allows multiple panels in same window space
        
        // Create and add tabs for different functional areas
        tabbedPane.addTab("Books", createBooksPanel());     // Line 32: Add books management tab
                                        //          First parameter is tab title, second is panel content
        tabbedPane.addTab("Users", createUsersPanel());     // Line 33: Add users management tab
        tabbedPane.addTab("Transactions", createTransactionsPanel()); // Line 34: Add transactions tab
        
        add(tabbedPane);                // Line 35: Add tabbed pane to frame's content area
                                        //          JFrame uses BorderLayout by default, this goes to CENTER
    }                                   // Line 36: End initializeGUI method

    private JPanel createBooksPanel() { // Line 37: Create panel for book management operations
        JPanel panel = new JPanel(new BorderLayout());     // Line 38: Create panel with BorderLayout
                                        //          BorderLayout divides area into 5 regions: NORTH, SOUTH, EAST, WEST, CENTER
        
        // Create button panel for book operations
        JPanel buttonPanel = new JPanel(new FlowLayout()); // Line 39: Panel for buttons with FlowLayout
                                        //          FlowLayout arranges components left-to-right, wrapping as needed
        JButton addBookButton = new JButton("Add Book");    // Line 40: Button for adding new books
        JButton updateBookButton = new JButton("Update Book");      // Line 41: Button for editing existing books
        JButton removeBookButton = new JButton("Remove Book");      // Line 42: Button for deleting books
        JButton refreshBooksButton = new JButton("Refresh");        // Line 43: Button to reload table data
        
        // Add buttons to button panel
        buttonPanel.add(addBookButton); // Line 44: Add button to flow layout panel
        buttonPanel.add(updateBookButton);      // Line 45: Buttons arranged left to right
        buttonPanel.add(removeBookButton);      // Line 46: in order they're added
        buttonPanel.add(refreshBooksButton);    // Line 47: Flow layout handles positioning
        
        // Create table for displaying book data
        String[] bookColumns = {"ISBN", "Title", "Author", "Status"};   // Line 48: Define column headers
                                        //          Array of strings becomes table column names
        booksTableModel = new DefaultTableModel(bookColumns, 0);       // Line 49: Create table data model
                                        //          First parameter: column names
                                        //          Second parameter: initial row count (0 = empty)
        booksTable = new JTable(booksTableModel);      // Line 50: Create table widget with data model
        booksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                                        // Line 51: Allow only one row to be selected at a time
                                        //          Prevents confusion in update/delete operations
        JScrollPane booksScrollPane = new JScrollPane(booksTable);     // Line 52: Add scroll capability to table
                                        //          Scroll pane shows scrollbars when content exceeds visible area
        booksScrollPane.setBorder(BorderFactory.createTitledBorder("Books (Double-click to update)"));
                                        // Line 53: Add border with title around scroll pane
                                        //          Provides visual grouping and user instruction
        
        // Arrange components in border layout
        panel.add(buttonPanel, BorderLayout.NORTH);     // Line 54: Buttons at top of panel
        panel.add(booksScrollPane, BorderLayout.CENTER);        // Line 55: Table takes remaining space
        
        // Attach event handlers to buttons
        addBookButton.addActionListener(e -> showAddBookDialog());     // Line 56: Lambda expression for event handling
                                        //          e -> method() is shorthand for ActionListener
                                        //          Called when button clicked
        updateBookButton.addActionListener(e -> showUpdateBookDialog());       // Line 57: Update button handler
        removeBookButton.addActionListener(e -> showRemoveBookDialog());       // Line 58: Remove button handler
        refreshBooksButton.addActionListener(e -> refreshBooksTable());        // Line 59: Refresh button handler
        
        // Add double-click handler to table
        booksTable.addMouseListener(new MouseAdapter() {       // Line 60: MouseAdapter handles mouse events
                                        //          MouseAdapter provides empty implementations of MouseListener methods
            public void mouseClicked(MouseEvent e) {   // Line 61: Override mouseClicked method
                if (e.getClickCount() == 2) {          // Line 62: Check for double-click
                                        //          getClickCount() returns number of consecutive clicks
                    showUpdateBookDialog();     // Line 63: Double-click triggers update dialog
                }                       // Line 64: End double-click check
            }                           // Line 65: End mouseClicked method
        });                             // Line 66: End MouseAdapter anonymous class
        
        return panel;                   // Line 67: Return completed books panel
    }                                   // Line 68: End createBooksPanel method

    private JPanel createUsersPanel() { // Line 69: Create panel for user management (similar structure to books)
        JPanel panel = new JPanel(new BorderLayout());     // Line 70: BorderLayout panel
        
        // Button panel for user operations
        JPanel buttonPanel = new JPanel(new FlowLayout()); // Line 71: Flow layout for buttons
        JButton addUserButton = new JButton("Add User");    // Line 72: Add user button
        JButton updateUserButton = new JButton("Update User");      // Line 73: Update user button
        JButton removeUserButton = new JButton("Remove User");      // Line 74: Remove user button
        JButton refreshUsersButton = new JButton("Refresh");        // Line 75: Refresh button
        
        buttonPanel.add(addUserButton); // Line 76: Add buttons to panel
        buttonPanel.add(updateUserButton);      // Line 77: Same pattern as books panel
        buttonPanel.add(removeUserButton);      // Line 78: Consistent UI design
        buttonPanel.add(refreshUsersButton);    // Line 79: across all tabs
        
        // Table for displaying user data
        String[] userColumns = {"User ID", "Name", "Books Borrowed"};   // Line 80: User table columns
        usersTableModel = new DefaultTableModel(userColumns, 0);       // Line 81: User table model
        usersTable = new JTable(usersTableModel);      // Line 82: User table widget
        usersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);      // Line 83: Single selection mode
        JScrollPane usersScrollPane = new JScrollPane(usersTable);     // Line 84: Scrollable table
        usersScrollPane.setBorder(BorderFactory.createTitledBorder("Users (Double-click to update)"));
                                        // Line 85: Titled border with instructions
        
        panel.add(buttonPanel, BorderLayout.NORTH);     // Line 86: Buttons at top
        panel.add(usersScrollPane, BorderLayout.CENTER);        // Line 87: Table in center
        
        // Event handlers for user operations (same pattern as books)
        addUserButton.addActionListener(e -> showAddUserDialog());     // Line 88: Add user handler
        updateUserButton.addActionListener(e -> showUpdateUserDialog());       // Line 89: Update user handler
        removeUserButton.addActionListener(e -> showRemoveUserDialog());       // Line 90: Remove user handler
        refreshUsersButton.addActionListener(e -> refreshUsersTable());        // Line 91: Refresh handler
        
        // Double-click handler for users table
        usersTable.addMouseListener(new MouseAdapter() {       // Line 92: Mouse adapter for table
            public void mouseClicked(MouseEvent e) {   // Line 93: Mouse click handler
                if (e.getClickCount() == 2) {          // Line 94: Check for double-click
                    showUpdateUserDialog();     // Line 95: Show update dialog on double-click
                }                       // Line 96: End double-click check
            }                           // Line 97: End mouseClicked
        });                             // Line 98: End MouseAdapter
        
        return panel;                   // Line 99: Return completed users panel
    }                                   // Line 100: End createUsersPanel

    private JPanel createTransactionsPanel() {  // Line 101: Create transactions panel with different layout
        JPanel panel = new JPanel(new BorderLayout());     // Line 102: Main panel with BorderLayout
        
        // Operations panel for borrow/return functionality
        JPanel operationsPanel = new JPanel(new GridLayout(3, 1, 5, 5));
                                        // Line 103: Grid layout: 3 rows, 1 column, 5px gaps
                                        //          GridLayout creates equal-sized cells
        
        // Borrow book panel
        JPanel borrowPanel = new JPanel(new GridLayout(2, 3, 5, 5));
                                        // Line 104: 2x3 grid for borrow controls
        borrowPanel.setBorder(BorderFactory.createTitledBorder("Borrow Book"));
                                        // Line 105: Titled border for visual grouping
        borrowPanel.add(new JLabel("ISBN:"));   // Line 106: Label for ISBN input
        borrowIsbnField = new JTextField();     // Line 107: Text field for ISBN entry
        borrowPanel.add(borrowIsbnField);       // Line 108: Add field to grid
        JButton borrowButton = new JButton("Borrow");       // Line 109: Button to execute borrow
        borrowPanel.add(borrowButton);          // Line 110: Add button to grid
        borrowPanel.add(new JLabel("User ID:"));        // Line 111: Label for user ID
        borrowUserIdField = new JTextField();   // Line 112: Text field for user ID
        borrowPanel.add(borrowUserIdField);     // Line 113: Add field to grid
        borrowPanel.add(new JLabel(""));        // Line 114: Empty label for grid alignment
                                        //          GridLayout requires all cells to be filled
        
        // Return book panel (similar structure to borrow panel)
        JPanel returnPanel = new JPanel(new GridLayout(2, 3, 5, 5));
                                        // Line 115: 2x3 grid for return controls
        returnPanel.setBorder(BorderFactory.createTitledBorder("Return Book"));
                                        // Line 116: Titled border
        returnPanel.add(new JLabel("ISBN:"));   // Line 117: ISBN label
        returnIsbnField = new JTextField();     // Line 118: ISBN input field
        returnPanel.add(returnIsbnField);       // Line 119: Add to grid
        JButton returnButton = new JButton("Return");       // Line 120: Return button
        returnPanel.add(returnButton);          // Line 121: Add to grid
        returnPanel.add(new JLabel("User ID:"));        // Line 122: User ID label
        returnUserIdField = new JTextField();   // Line 123: User ID input field
        returnPanel.add(returnUserIdField);     // Line 124: Add to grid
        returnPanel.add(new JLabel(""));        // Line 125: Empty cell for alignment
        
        // Status area for operation feedback
        statusArea = new JTextArea(3, 50);      // Line 126: Multi-line text area
                                        //          3 rows, 50 columns (approximate sizing)
        statusArea.setEditable(false);          // Line 127: Make read-only
                                        //          Users can't type in status area
        statusArea.setBackground(getBackground());      // Line 128: Match panel background color
                                        //          Makes it look like part of the form, not an input field
        JScrollPane statusScrollPane = new JScrollPane(statusArea);
                                        // Line 129: Add scrolling to status area
        statusScrollPane.setBorder(BorderFactory.createTitledBorder("Status"));
                                        // Line 130: Titled border around status area
        
        // Add sub-panels to operations panel
        operationsPanel.add(borrowPanel);       // Line 131: First row: borrow controls
        operationsPanel.add(returnPanel);       // Line 132: Second row: return controls
        operationsPanel.add(statusScrollPane);  // Line 133: Third row: status display
        
        // Transaction history table
        String[] transactionColumns = {"Transaction ID", "Book ISBN", "User ID", "Borrow Date", "Status"};
                                        // Line 134: Column headers for transaction table
        transactionsTableModel = new DefaultTableModel(transactionColumns, 0);
                                        // Line 135: Create table model
        transactionsTable = new JTable(transactionsTableModel);
                                        // Line 136: Create table widget
        JScrollPane transactionsScrollPane = new JScrollPane(transactionsTable);
                                        // Line 137: Make table scrollable
        transactionsScrollPane.setBorder(BorderFactory.createTitledBorder("Transactions"));
                                        // Line 138: Title the table area
        
        // Arrange components in main panel
        panel.add(operationsPanel, BorderLayout.NORTH);         // Line 139: Operations at top
        panel.add(transactionsScrollPane, BorderLayout.CENTER); // Line 140: Table takes remaining space
        
        // Event handlers for transaction operations
        borrowButton.addActionListener(e -> borrowBook());      // Line 141: Borrow button handler
        returnButton.addActionListener(e -> returnBook());      // Line 142: Return button handler
        
        return panel;                   // Line 143: Return completed transactions panel
    }                                   // Line 144: End createTransactionsPanel

    private void setupEventHandlers() { // Line 145: Method for additional event handler setup
        // Event handlers are now set up in individual panel creation methods
                                        // Line 146: Comment explaining current architecture
                                        //          This method kept for consistency but not currently used
    }                                   // Line 147: End setupEventHandlers

    // Book Dialog Methods - Modal dialogs for book operations
    private void showAddBookDialog() {  // Line 148: Method to show add book dialog
        JDialog dialog = new JDialog(this, "Add New Book", true);
                                        // Line 149: Create modal dialog
                                        //          'this' makes it child of main window
                                        //          'true' makes it modal (blocks interaction with parent)
        dialog.setSize(400, 200);       // Line 150: Set dialog size
        dialog.setLocationRelativeTo(this);     // Line 151: Center relative to main window
        dialog.setLayout(new GridLayout(4, 2, 10, 10));
                                        // Line 152: 4x2 grid layout with 10px gaps
        
        // Create input fields
        JTextField isbnField = new JTextField();        // Line 153: ISBN input field
        JTextField titleField = new JTextField();       // Line 154: Title input field
        JTextField authorField = new JTextField();      // Line 155: Author input field
        
        // Add labels and fields to dialog
        dialog.add(new JLabel("ISBN:"));        // Line 156: ISBN label
        dialog.add(isbnField);          // Line 157: ISBN field
        dialog.add(new JLabel("Title:"));       // Line 158: Title label
        dialog.add(titleField);         // Line 159: Title field
        dialog.add(new JLabel("Author:"));      // Line 160: Author label
        dialog.add(authorField);        // Line 161: Author field
        
        // Create action buttons
        JButton addButton = new JButton("Add");         // Line 162: Add button
        JButton cancelButton = new JButton("Cancel");   // Line 163: Cancel button
        
        // Add button event handler
        addButton.addActionListener(e -> {      // Line 164: Lambda for add button
            String isbn = isbnField.getText().trim();   // Line 165: Get ISBN, remove whitespace
            String title = titleField.getText().trim(); // Line 166: Get title, remove whitespace
            String author = authorField.getText().trim();       // Line 167: Get author, remove whitespace
            
            // Validate input
            if (isbn.isEmpty() || title.isEmpty() || author.isEmpty()) {
                                        // Line 168: Check for empty fields
                JOptionPane.showMessageDialog(dialog, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                                        // Line 169: Show error message dialog
                                        //          JOptionPane provides standard dialogs
                return;                 // Line 170: Exit handler without closing dialog
            }                           // Line 171: End validation
            
            // Attempt to add book
            Book book = new Book(isbn, title, author);  // Line 172: Create new book object
            if (library.addBook(book)) { // Line 173: Try to add book to library
                JOptionPane.showMessageDialog(dialog, "Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        // Line 174: Show success message
                refreshBooksTable();    // Line 175: Update table to show new book
                dialog.dispose();       // Line 176: Close dialog
            } else {                    // Line 177: Add operation failed
                JOptionPane.showMessageDialog(dialog, "Failed to add book! ISBN might already exist.", "Error", JOptionPane.ERROR_MESSAGE);
                                        // Line 178: Show error message
                                        //          Most likely cause is duplicate ISBN
            }                           // Line 179: End add result check
        });                             // Line 180: End add button handler
        
        // Cancel button handler
        cancelButton.addActionListener(e -> dialog.dispose());
                                        // Line 181: Close dialog without saving
        
        // Add buttons to dialog
        dialog.add(addButton);          // Line 182: Add button to grid
        dialog.add(cancelButton);       // Line 183: Cancel button to grid
        dialog.setVisible(true);        // Line 184: Show dialog to user
                                        //          This call blocks until dialog is closed (modal)
    }                                   // Line 185: End showAddBookDialog

    private void showUpdateBookDialog() {       // Line 186: Method to show update book dialog
        int selectedRow = booksTable.getSelectedRow();
                                        // Line 187: Get index of selected table row
                                        //          Returns -1 if no row selected
        if (selectedRow == -1) {        // Line 188: Check if row selected
            JOptionPane.showMessageDialog(this, "Please select a book to update!", "No Selection", JOptionPane.WARNING_MESSAGE);
                                        // Line 189: Show warning if no selection
            return;                     // Line 190: Exit method
        }                               // Line 191: End selection check
        
        // Get current values from selected row
        String currentIsbn = (String) booksTableModel.getValueAt(selectedRow, 0);
                                        // Line 192: Get ISBN from column 0 of selected row
        String currentTitle = (String) booksTableModel.getValueAt(selectedRow, 1);
                                        // Line 193: Get title from column 1
        String currentAuthor = (String) booksTableModel.getValueAt(selectedRow, 2);
                                        // Line 194: Get author from column 2
        
        // Create dialog (similar structure to add dialog)
        JDialog dialog = new JDialog(this, "Update Book", true);
                                        // Line 195: Modal dialog for updating
        dialog.setSize(400, 200);       // Line 196: Set size
        dialog.setLocationRelativeTo(this);     // Line 197: Center on parent
        dialog.setLayout(new GridLayout(4, 2, 10, 10));
                                        // Line 198: Grid layout
        
        // Create fields pre-populated with current values
        JTextField isbnField = new JTextField(currentIsbn);
                                        // Line 199: ISBN field with current value
        isbnField.setEditable(false);   // Line 200: Make ISBN read-only
                                        //          ISBN is unique identifier, shouldn't change
        JTextField titleField = new JTextField(currentTitle);
                                        // Line 201: Title field with current value
        JTextField authorField = new JTextField(currentAuthor);
                                        // Line 202: Author field with current value
        
        // Add labeled fields to dialog
        dialog.add(new JLabel("ISBN (readonly):"));
                                        // Line 203: Label indicates field is read-only
        dialog.add(isbnField);          // Line 204: Add ISBN field
        dialog.add(new JLabel("Title:"));       // Line 205: Title label
        dialog.add(titleField);         // Line 206: Title field
        dialog.add(new JLabel("Author:"));      // Line 207: Author label
        dialog.add(authorField);        // Line 208: Author field
        
        JButton updateButton = new JButton("Update");       // Line 209: Update button
        JButton cancelButton = new JButton("Cancel");       // Line 210: Cancel button
        
        // Update button handler
        updateButton.addActionListener(e -> {       // Line 211: Lambda for update
            String newTitle = titleField.getText().trim();
                                        // Line 212: Get new title value
            String newAuthor = authorField.getText().trim();
                                        // Line 213: Get new author value
            
            // Validate input
            if (newTitle.isEmpty() || newAuthor.isEmpty()) {
                                        // Line 214: Check for empty fields
                JOptionPane.showMessageDialog(dialog, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                                        // Line 215: Show validation error
                return;                 // Line 216: Exit without updating
            }                           // Line 217: End validation
            
            // Attempt update
            if (library.updateBook(currentIsbn, newTitle, newAuthor)) {
                                        // Line 218: Call library's update method
                JOptionPane.showMessageDialog(dialog, "Book updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        // Line 219: Show success message
                refreshBooksTable();    // Line 220: Refresh table to show changes
                dialog.dispose();       // Line 221: Close dialog
            } else {                    // Line 222: Update failed
                JOptionPane.showMessageDialog(dialog, "Failed to update book!", "Error", JOptionPane.ERROR_MESSAGE);
                                        // Line 223: Show error message
            }                           // Line 224: End update result check
        });                             // Line 225: End update button handler
        
        cancelButton.addActionListener(e -> dialog.dispose());
                                        // Line 226: Cancel closes dialog without saving
        
        dialog.add(updateButton);       // Line 227: Add update button
        dialog.add(cancelButton);       // Line 228: Add cancel button
        dialog.setVisible(true);        // Line 229: Show modal dialog
    }                                   // Line 230: End showUpdateBookDialog

    private void showRemoveBookDialog() {       // Line 231: Method for book removal confirmation
        int selectedRow = booksTable.getSelectedRow();
                                        // Line 232: Get selected row index
        if (selectedRow == -1) {        // Line 233: Check if row selected
            JOptionPane.showMessageDialog(this, "Please select a book to remove!", "No Selection", JOptionPane.WARNING_MESSAGE);
                                        // Line 234: Warning for no selection
            return;                     // Line 235: Exit method
        }                               // Line 236: End selection check
        
        // Get book details for confirmation
        String isbn = (String) booksTableModel.getValueAt(selectedRow, 0);
                                        // Line 237: Get ISBN of selected book
        String title = (String) booksTableModel.getValueAt(selectedRow, 1);
                                        // Line 238: Get title for display
        String status = (String) booksTableModel.getValueAt(selectedRow, 3);
                                        // Line 239: Get status to check if borrowed
        
        // Business rule check: can't remove borrowed books
        if ("BORROWED".equals(status)) { // Line 240: Check if book is borrowed
            JOptionPane.showMessageDialog(this, "Cannot remove a borrowed book!", "Error", JOptionPane.ERROR_MESSAGE);
                                        // Line 241: Show error for borrowed book
            return;                     // Line 242: Exit without removing
        }                               // Line 243: End status check
        
        // Confirmation dialog
        int result = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to remove this book?\n\nISBN: " + isbn + "\nTitle: " + title,
                "Confirm Removal", JOptionPane.YES_NO_OPTION);
                                        // Line 244-246: Show confirmation dialog with book details
                                        //               YES_NO_OPTION provides Yes/No buttons
        
        if (result == JOptionPane.YES_OPTION) {         // Line 247: Check if user confirmed
            if (library.removeBook(isbn)) {     // Line 248: Attempt to remove book
                JOptionPane.showMessageDialog(this, "Book removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        // Line 249: Show success message
                refreshBooksTable();    // Line 250: Update table
            } else {                    // Line 251: Remove failed
                JOptionPane.showMessageDialog(this, "Failed to remove book!", "Error", JOptionPane.ERROR_MESSAGE);
                                        // Line 252: Show error message
            }                           // Line 253: End remove result check
        }                               // Line 254: End confirmation check
    }                                   // Line 255: End showRemoveBookDialog

    // User Dialog Methods (similar patterns to book dialogs, but simpler)
    private void showAddUserDialog() {  // Line 256: Add user dialog method
        JDialog dialog = new JDialog(this, "Add New User", true);
                                        // Line 257: Modal dialog for adding user
        dialog.setSize(350, 150);       // Line 258: Smaller than book dialog (fewer fields)
        dialog.setLocationRelativeTo(this);     // Line 259: Center on parent
        dialog.setLayout(new GridLayout(3, 2, 10, 10));
                                        // Line 260: 3x2 grid (2 fields + buttons)
        
        JTextField userIdField = new JTextField();      // Line 261: User ID input
        JTextField nameField = new JTextField();        // Line 262: Name input
        
        dialog.add(new JLabel("User ID:"));     // Line 263: User ID label
        dialog.add(userIdField);        // Line 264: User ID field
        dialog.add(new JLabel("Name:"));        // Line 265: Name label
        dialog.add(nameField);          // Line 266: Name field
        
        JButton addButton = new JButton("Add");         // Line 267: Add button
        JButton cancelButton = new JButton("Cancel");   // Line 268: Cancel button
        
        addButton.addActionListener(e -> {      // Line 269: Add button handler
            String userId = userIdField.getText().trim();       // Line 270: Get user ID
            String name = nameField.getText().trim();   // Line 271: Get name
            
            if (userId.isEmpty() || name.isEmpty()) {   // Line 272: Validate input
                JOptionPane.showMessageDialog(dialog, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                                        // Line 273: Show validation error
                return;                 // Line 274: Exit handler
            }                           // Line 275: End validation
            
            User user = new User(userId, name); // Line 276: Create user object
            if (library.addUser(user)) { // Line 277: Add to library
                JOptionPane.showMessageDialog(dialog, "User added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        // Line 278: Success message
                refreshUsersTable();    // Line 279: Update table
                dialog.dispose();       // Line 280: Close dialog
            } else {                    // Line 281: Add failed
                JOptionPane.showMessageDialog(dialog, "Failed to add user! User ID might already exist.", "Error", JOptionPane.ERROR_MESSAGE);
                                        // Line 282: Error message
            }                           // Line 283: End add result check
        });                             // Line 284: End add handler
        
        cancelButton.addActionListener(e -> dialog.dispose());
                                        // Line 285: Cancel handler
        
        dialog.add(addButton);          // Line 286: Add buttons to dialog
        dialog.add(cancelButton);       // Line 287: 
        dialog.setVisible(true);        // Line 288: Show dialog
    }                                   // Line 289: End showAddUserDialog

    private void showUpdateUserDialog() {       // Line 290: Update user dialog (similar to update book)
        int selectedRow = usersTable.getSelectedRow();
                                        // Line 291: Get selected row
        if (selectedRow == -1) {        // Line 292: Check selection
            JOptionPane.showMessageDialog(this, "Please select a user to update!", "No Selection", JOptionPane.WARNING_MESSAGE);
                                        // Line 293: No selection warning
            return;                     // Line 294: Exit
        }                               // Line 295: End check
        
        String currentUserId = (String) usersTableModel.getValueAt(selectedRow, 0);
                                        // Line 296: Get current user ID
        String currentName = (String) usersTableModel.getValueAt(selectedRow, 1);
                                        // Line 297: Get current name
        
        JDialog dialog = new JDialog(this, "Update User", true);
                                        // Line 298: Modal update dialog
        dialog.setSize(350, 150);       // Line 299: Set size
        dialog.setLocationRelativeTo(this);     // Line 300: Center
        dialog.setLayout(new GridLayout(3, 2, 10, 10));
                                        // Line 301: Grid layout
        
        JTextField userIdField = new JTextField(currentUserId);
                                        // Line 302: Pre-populate user ID
        userIdField.setEditable(false); // Line 303: Make read-only (like ISBN)
        JTextField nameField = new JTextField(currentName);
                                        // Line 304: Pre-populate name
        
        dialog.add(new JLabel("User ID (readonly):"));
                                        // Line 305: Read-only label
        dialog.add(userIdField);        // Line 306: User ID field
        dialog.add(new JLabel("Name:"));        // Line 307: Name label
        dialog.add(nameField);          // Line 308: Name field
        
        JButton updateButton = new JButton("Update");   // Line 309: Update button
        JButton cancelButton = new JButton("Cancel");   // Line 310: Cancel button
        
        updateButton.addActionListener(e -> {   // Line 311: Update handler
            String newName = nameField.getText().trim();
                                        // Line 312: Get new name
            
            if (newName.isEmpty()) {    // Line 313: Validate name
                JOptionPane.showMessageDialog(dialog, "Please enter a name!", "Error", JOptionPane.ERROR_MESSAGE);
                                        // Line 314: Validation error
                return;                 // Line 315: Exit
            }                           // Line 316: End validation
            
            if (library.updateUser(currentUserId, newName)) {
                                        // Line 317: Attempt update
                JOptionPane.showMessageDialog(dialog, "User updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        // Line 318: Success message
                refreshUsersTable();    // Line 319: Refresh table
                dialog.dispose();       // Line 320: Close dialog
            } else {                    // Line 321: Update failed
                JOptionPane.showMessageDialog(dialog, "Failed to update user!", "Error", JOptionPane.ERROR_MESSAGE);
                                        // Line 322: Error message
            }                           // Line 323: End update check
        });                             // Line 324: End update handler
        
        cancelButton.addActionListener(e -> dialog.dispose());
                                        // Line 325: Cancel handler
        
        dialog.add(updateButton);       // Line 326: Add buttons
        dialog.add(cancelButton);       // Line 327:
        dialog.setVisible(true);        // Line 328: Show dialog
    }                                   // Line 329: End showUpdateUserDialog

    private void showRemoveUserDialog() {       // Line 330: Remove user dialog
        int selectedRow = usersTable.getSelectedRow();
                                        // Line 331: Get selection
        if (selectedRow == -1) {        // Line 332: Check selection
            JOptionPane.showMessageDialog(this, "Please select a user to remove!", "No Selection", JOptionPane.WARNING_MESSAGE);
                                        // Line 333: No selection warning
            return;                     // Line 334: Exit
        }                               // Line 335: End check
        
        String userId = (String) usersTableModel.getValueAt(selectedRow, 0);
                                        // Line 336: Get user ID
        String name = (String) usersTableModel.getValueAt(selectedRow, 1);
                                        // Line 337: Get name for display
        Integer borrowedCount = (Integer) usersTableModel.getValueAt(selectedRow, 2);
                                        // Line 338: Get borrowed books count
        
        if (borrowedCount > 0) {        // Line 339: Business rule: can't remove users with borrowed books
            JOptionPane.showMessageDialog(this, "Cannot remove user with borrowed books!", "Error", JOptionPane.ERROR_MESSAGE);
                                        // Line 340: Business rule error
            return;                     // Line 341: Exit
        }                               // Line 342: End business rule check
        
        int result = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to remove this user?\n\nUser ID: " + userId + "\nName: " + name,
                "Confirm Removal", JOptionPane.YES_NO_OPTION);
                                        // Line 343-345: Confirmation dialog
        
        if (result == JOptionPane.YES_OPTION) {         // Line 346: Check confirmation
            if (library.removeUser(userId)) {   // Line 347: Attempt removal
                JOptionPane.showMessageDialog(this, "User removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        // Line 348: Success message
                refreshUsersTable();    // Line 349: Refresh table
            } else {                    // Line 350: Remove failed
                JOptionPane.showMessageDialog(this, "Failed to remove user!", "Error", JOptionPane.ERROR_MESSAGE);
                                        // Line 351: Error message
            }                           // Line 352: End remove check
        }                               // Line 353: End confirmation check
    }                                   // Line 354: End showRemoveUserDialog

    // Transaction operation methods
    private void borrowBook() {         // Line 355: Method to handle book borrowing from GUI
        String isbn = borrowIsbnField.getText().trim();
                                        // Line 356: Get ISBN from input field
        String userId = borrowUserIdField.getText().trim();
                                        // Line 357: Get user ID from input field
        
        if (isbn.isEmpty() || userId.isEmpty()) {       // Line 358: Validate both fields filled
            statusArea.setText("Please fill both ISBN and User ID fields!");
                                        // Line 359: Show error in status area
            return;                     // Line 360: Exit method
        }                               // Line 361: End validation
        
        String result = library.borrowBook(isbn, userId);
                                        // Line 362: Call library's borrow method
                                        //          Returns descriptive message
        statusArea.setText("Borrow Result: " + result);
                                        // Line 363: Display result in status area
        
        // Clear input fields after operation
        borrowIsbnField.setText("");    // Line 364: Clear ISBN field
        borrowUserIdField.setText("");  // Line 365: Clear user ID field
        refreshAllTables();             // Line 366: Update all tables to reflect changes
                                        //          Borrow operation affects books, users, and transactions
    }                                   // Line 367: End borrowBook method

    private void returnBook() {         // Line 368: Method to handle book returning from GUI
        String isbn = returnIsbnField.getText().trim();
                                        // Line 369: Get ISBN from return field
        String userId = returnUserIdField.getText().trim();
                                        // Line 370: Get user ID from return field
        
        if (isbn.isEmpty() || userId.isEmpty()) {       // Line 371: Validate input
            statusArea.setText("Please fill both ISBN and User ID fields!");
                                        // Line 372: Show validation error
            return;                     // Line 373: Exit method
        }                               // Line 374: End validation
        
        String result = library.returnBook(isbn, userId);
                                        // Line 375: Call library's return method
        statusArea.setText("Return Result: " + result);
                                        // Line 376: Display result
        
        // Clear input fields
        returnIsbnField.setText("");    // Line 377: Clear ISBN field
        returnUserIdField.setText("");  // Line 378: Clear user ID field
        refreshAllTables();             // Line 379: Update all tables
    }                                   // Line 380: End returnBook method

    // Table refresh methods - sync GUI with business logic layer
    private void refreshBooksTable() {  // Line 381: Refresh books table from library data
        booksTableModel.setRowCount(0); // Line 382: Clear all existing rows
                                        //          setRowCount(0) removes all data but keeps columns
        List<Book> books = library.getAllBooks();      // Line 383: Get current books from library
        for (Book book : books) {       // Line 384: Loop through each book
            Object[] row = {book.getIsbn(), book.getTitle(), book.getAuthor(), book.getStatus()};
                                        // Line 385: Create array with book data for table row
                                        //          Order matches column definitions
            booksTableModel.addRow(row); // Line 386: Add row to table model
                                        //          Table automatically updates display
        }                               // Line 387: End book loop
    }                                   // Line 388: End refreshBooksTable

    private void refreshUsersTable() {  // Line 389: Refresh users table
        usersTableModel.setRowCount(0); // Line 390: Clear existing rows
        List<User> users = library.getAllUsers();      // Line 391: Get users from library
        for (User user : users) {       // Line 392: Loop through users
            Object[] row = {user.getUserId(), user.getName(), user.getBorrowedBooksCount()};
                                        // Line 393: Create row data array
            usersTableModel.addRow(row); // Line 394: Add to table
        }                               // Line 395: End user loop
    }                                   // Line 396: End refreshUsersTable

    private void refreshTransactionsTable() {   // Line 397: Refresh transactions table
        transactionsTableModel.setRowCount(0); // Line 398: Clear existing rows
        List<BorrowTransaction> transactions = library.getAllTransactions();
                                        // Line 399: Get transactions from library
        for (BorrowTransaction transaction : transactions) {
                                        // Line 400: Loop through transactions
            Object[] row = {
                transaction.getTransactionId(),         // Line 401: Transaction ID
                transaction.getBookIsbn(),              // Line 402: Book ISBN
                transaction.getUserId(),                // Line 403: User ID
                transaction.getBorrowDate().toString(), // Line 404: Borrow date as string
                transaction.getStatus()                 // Line 405: Current status
            };                          // Line 406: End row array
            transactionsTableModel.addRow(row);        // Line 407: Add to table
        }                               // Line 408: End transaction loop
    }                                   // Line 409: End refreshTransactionsTable

    private void refreshAllTables() {   // Line 410: Convenience method to refresh all tables
        refreshBooksTable();            // Line 411: Refresh books
        refreshUsersTable();            // Line 412: Refresh users
        refreshTransactionsTable();     // Line 413: Refresh transactions
                                        //          Called after operations that might affect multiple tables
    }                                   // Line 414: End refreshAllTables

    // Main method to launch the application
    public static void main(String[] args) {   // Line 415: Application entry point
        SwingUtilities.invokeLater(() -> {     // Line 416: Execute GUI creation on Event Dispatch Thread
                                        //          SwingUtilities.invokeLater ensures thread safety
                                        //          Lambda expression for Runnable
            try {                       // Line 417: Try block for look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
                                        // Line 418: Set native system look and feel
                                        //          Makes app look like native OS applications
            } catch (Exception e) {     // Line 419: Catch any look and feel exceptions
                e.printStackTrace();    // Line 420: Print error but continue
                                        //          App still works with default look and feel
            }                           // Line 421: End try-catch
            new LibraryGUI().setVisible(true);         // Line 422: Create and show main window
                                        //          new LibraryGUI() calls constructor
                                        //          setVisible(true) makes window appear
        });                             // Line 423: End lambda expression
    }                                   // Line 424: End main method
}                                       // Line 425: End LibraryGUI class
```

### Key OOP Concepts Explained

#### 1. **MVC Architecture Pattern**
- **Model:** `SimpleLibrary` class contains all business logic and data
- **View:** GUI components (tables, dialogs, panels) display data to user
- **Controller:** Event handlers respond to user actions and coordinate between model and view
- **Separation of concerns:** GUI never directly manipulates business objects

#### 2. **Component Organization and Composition**
- **Composite pattern:** Main window contains tabbed pane, which contains panels
- **Delegation:** Each panel handles its own layout and components
- **Reusable methods:** Common patterns (dialog creation, table refresh) extracted to methods
- **Consistent design:** Similar operations follow same UI patterns

#### 3. **Event-Driven Programming**
- **Observer pattern:** Components register listeners for user events
- **Lambda expressions:** Concise event handler syntax (`e -> method()`)
- **Event delegation:** GUI components delegate business operations to model layer
- **Asynchronous handling:** Events processed on Event Dispatch Thread

#### 4. **State Synchronization**
- **Refresh pattern:** GUI tables updated after any data modification
- **Defensive copying:** Tables display snapshots of data, not direct references
- **Consistency maintenance:** All related displays updated together
- **Error feedback:** Status messages inform user of operation results

### Hands-On Exercises
1. **Interface exploration:** Navigate all tabs, try all buttons and operations
2. **CRUD operations via GUI:** Add, edit, delete books and users through interface
3. **Business rule testing:** Try invalid operations (remove borrowed book, exceed limits)
4. **Event handling:** Test keyboard shortcuts, double-click behaviors
5. **Error handling:** Test with invalid input, verify error messages

### Session Discussion Points
- **Separation of GUI and business logic:** Why delegate to SimpleLibrary instead of direct manipulation?
- **Event-driven vs procedural programming:** How events change program flow
- **User experience considerations:** Validation, feedback, error messages, intuitive design
- **Data binding and synchronization:** Keeping GUI consistent with underlying data

---

## Complete Session Flow and Implementation Strategy

### Opening Session (15 minutes)
1. **Project overview and architecture discussion**
   - What we're building: Complete library management system
   - Why OOP approach: Maintainability, scalability, real-world applicability
   - Architecture preview: Show final application running

2. **Development environment setup**
   - IDE configuration and project structure
   - Java version and dependency requirements
   - Database setup (SQLite) - optional but recommended

### Step-by-Step Implementation (4+ hours)

#### Implementation Strategy for Each Step:
1. **Live coding:** Type every line while explaining
2. **Concept introduction:** Explain OOP principle before implementing
3. **Testing:** Run and test code after each major addition
4. **Discussion:** Why we made each design choice
5. **Exercises:** Hands-on practice for participants

#### Detailed Step Progression:

**Step 1 (15 minutes):** Foundation
- Create project structure and packages
- Discuss package organization and naming conventions
- Implement basic Main class and run first program

**Step 2 (45 minutes):** Core Objects
- Introduce encapsulation with Book class
- Line-by-line explanation of each field, constructor, method
- Implement BookStatus enum and explain type safety
- Testing: Create Book objects, test all methods
- Discussion: Why final fields, getter/setter patterns, toString() importance

**Step 3 (45 minutes):** Collections and Business Rules
- Implement User class with ArrayList for borrowed books
- Explain defensive copying and its importance
- Implement business rules (borrowing limits)
- Testing: Create users, test borrowing limits, verify defensive copying
- Discussion: Collection choices, business rule enforcement

**Step 4 (45 minutes):** Relationships and History
- Create BorrowTransaction class
- Explain object relationships through IDs vs direct references
- Implement date handling and state transitions
- Testing: Create transaction chains, test state changes
- Discussion: Audit trails, temporal data, relationship design

**Step 5 (60 minutes):** System Integration
- Implement SimpleLibrary as system coordinator
- Build CRUD operations with consistent patterns
- Add database integration with fallback to memory
- Implement complex business operations (borrow/return)
- Testing: Full system workflows, database persistence
- Discussion: System architecture, CRUD patterns, error handling

**Step 6 (75 minutes):** User Interface
- Create GUI structure with tabbed interface
- Implement table-based data display
- Build modal dialogs for data entry
- Add event handlers for all operations
- Connect GUI to business logic layer
- Testing: Complete application workflows through GUI
- Discussion: MVC pattern, event-driven programming, user experience

### Testing and Validation Throughout
- **Unit-level testing:** Test each class and method as created
- **Integration testing:** Verify classes work together correctly
- **User acceptance testing:** Complete workflows through GUI
- **Error condition testing:** Invalid input, edge cases, business rule violations

### Wrap-up Session (20 minutes)
1. **Architecture review:** How all components fit together
2. **OOP principles demonstrated:** Encapsulation, composition, abstraction, single responsibility
3. **Design patterns used:** MVC, CRUD, Factory, Observer
4. **Extension opportunities:** Inheritance hierarchies, additional features, advanced patterns
5. **Best practices summary:** Key takeaways for future OOP development

---

## Advanced Concepts and Extensions

### Next-Level OOP Features
1. **Inheritance Hierarchy:**
   ```java
   abstract class LibraryUser {
       protected String userId;
       protected String name;
       protected abstract int getMaxBorrowLimit();
   }
   
   class Student extends LibraryUser {
       public int getMaxBorrowLimit() { return 3; }
   }
   
   class Faculty extends LibraryUser {
       public int getMaxBorrowLimit() { return 10; }
   }
   ```

2. **Interface Implementation:**
   ```java
   interface Searchable {
       List<Book> searchByTitle(String title);
       List<Book> searchByAuthor(String author);
   }
   
   class SimpleLibrary implements Searchable {
       // Implementation of search methods
   }
   ```

3. **Design Patterns:**
   - **Factory Pattern:** BookFactory for creating different book types
   - **Observer Pattern:** GUI components observe library changes
   - **Command Pattern:** Undoable operations
   - **Singleton Pattern:** Database connection management

### Production-Ready Enhancements
1. **Exception Handling:**
   ```java
   public class LibraryException extends Exception {
       public LibraryException(String message) {
           super(message);
       }
   }
   
   public void borrowBook(String isbn, String userId) throws LibraryException {
       // Throw specific exceptions instead of returning error strings
   }
   ```

2. **Logging and Monitoring:**
   ```java
   private static final Logger logger = LoggerFactory.getLogger(SimpleLibrary.class);
   
   public boolean addBook(Book book) {
       logger.info("Adding book: {}", book.getIsbn());
       // Implementation with logging
   }
   ```

3. **Configuration Management:**
   ```java
   public class LibraryConfig {
       private static final Properties config = new Properties();
       
       static {
           // Load from properties file
       }
       
       public static int getMaxBorrowLimit() {
           return Integer.parseInt(config.getProperty("max.borrow.limit", "3"));
       }
   }
   ```

4. **Unit Testing with JUnit:**
   ```java
   @Test
   public void testBookBorrowing() {
       SimpleLibrary library = new SimpleLibrary();
       Book book = new Book("123", "Test Book", "Test Author");
       User user = new User("U001", "Test User");
       
       library.addBook(book);
       library.addUser(user);
       
       String result = library.borrowBook("123", "U001");
       assertTrue(result.contains("Success"));
       assertEquals(BookStatus.BORROWED, book.getStatus());
   }
   ```

### Session Success Metrics

#### Knowledge Assessment
Participants should demonstrate understanding of:
1. **Core OOP Principles:** Can explain and implement encapsulation, abstraction, composition
2. **Design Patterns:** Recognize and apply MVC, CRUD, Factory patterns
3. **System Architecture:** Understand layered design and separation of concerns
4. **Error Handling:** Implement appropriate validation and error responses
5. **GUI Development:** Create responsive user interfaces with proper event handling

#### Practical Skills
Participants should be able to:
1. **Design class hierarchies** with appropriate relationships
2. **Implement business rules** within object methods
3. **Create CRUD operations** with proper validation
4. **Build user interfaces** that separate presentation from logic
5. **Handle persistence** with database integration
6. **Debug object-oriented programs** using proper techniques

#### Code Quality Standards
All code should demonstrate:
- **Proper encapsulation** with private fields and public methods
- **Clear naming conventions** for classes, methods, and variables
- **Consistent error handling** throughout the application
- **Appropriate use of collections** and data structures
- **Clean separation** between business logic and presentation
- **Comprehensive testing** of all major functionality

This comprehensive guide provides everything needed to conduct a thorough, educational session on Java OOP development, with complete code documentation and practical exercises that reinforce learning objectives.

<function_calls>
<invoke name="TodoWrite">
<parameter name="todos">[{"id": "1", "content": "Create comprehensive line-by-line code documentation for all steps", "status": "completed", "priority": "high"}]