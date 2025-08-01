package step6;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class LibraryGUI extends JFrame {
    private SimpleLibrary library;
    private JTabbedPane tabbedPane;
    
    // Book management components
    private JTable booksTable;
    private DefaultTableModel booksTableModel;
    
    // User management components
    private JTable usersTable;
    private DefaultTableModel usersTableModel;
    
    // Transaction components
    private JTextField borrowIsbnField, borrowUserIdField;
    private JTextField returnIsbnField, returnUserIdField;
    private JTable transactionsTable;
    private DefaultTableModel transactionsTableModel;
    private JTextArea statusArea;

    public LibraryGUI() {
        library = new SimpleLibrary();
        initializeGUI();
        setupEventHandlers();
        refreshAllTables();
    }

    private void initializeGUI() {
        setTitle("Simple Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        
        // Create tabs
        tabbedPane.addTab("Books", createBooksPanel());
        tabbedPane.addTab("Users", createUsersPanel());
        tabbedPane.addTab("Transactions", createTransactionsPanel());
        
        add(tabbedPane);
    }

    private JPanel createBooksPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBookButton = new JButton("Add Book");
        JButton updateBookButton = new JButton("Update Book");
        JButton removeBookButton = new JButton("Remove Book");
        JButton refreshBooksButton = new JButton("Refresh");
        
        buttonPanel.add(addBookButton);
        buttonPanel.add(updateBookButton);
        buttonPanel.add(removeBookButton);
        buttonPanel.add(refreshBooksButton);
        
        // Table panel
        String[] bookColumns = {"ISBN", "Title", "Author", "Status"};
        booksTableModel = new DefaultTableModel(bookColumns, 0);
        booksTable = new JTable(booksTableModel);
        booksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane booksScrollPane = new JScrollPane(booksTable);
        booksScrollPane.setBorder(BorderFactory.createTitledBorder("Books (Double-click to update)"));
        
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(booksScrollPane, BorderLayout.CENTER);
        
        // Event handlers for book operations
        addBookButton.addActionListener(e -> showAddBookDialog());
        updateBookButton.addActionListener(e -> showUpdateBookDialog());
        removeBookButton.addActionListener(e -> showRemoveBookDialog());
        refreshBooksButton.addActionListener(e -> refreshBooksTable());
        
        // Double-click to update
        booksTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    showUpdateBookDialog();
                }
            }
        });
        
        return panel;
    }

    private JPanel createUsersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addUserButton = new JButton("Add User");
        JButton updateUserButton = new JButton("Update User");
        JButton removeUserButton = new JButton("Remove User");
        JButton refreshUsersButton = new JButton("Refresh");
        
        buttonPanel.add(addUserButton);
        buttonPanel.add(updateUserButton);
        buttonPanel.add(removeUserButton);
        buttonPanel.add(refreshUsersButton);
        
        // Table panel
        String[] userColumns = {"User ID", "Name", "Books Borrowed"};
        usersTableModel = new DefaultTableModel(userColumns, 0);
        usersTable = new JTable(usersTableModel);
        usersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane usersScrollPane = new JScrollPane(usersTable);
        usersScrollPane.setBorder(BorderFactory.createTitledBorder("Users (Double-click to update)"));
        
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(usersScrollPane, BorderLayout.CENTER);
        
        // Event handlers for user operations
        addUserButton.addActionListener(e -> showAddUserDialog());
        updateUserButton.addActionListener(e -> showUpdateUserDialog());
        removeUserButton.addActionListener(e -> showRemoveUserDialog());
        refreshUsersButton.addActionListener(e -> refreshUsersTable());
        
        // Double-click to update
        usersTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    showUpdateUserDialog();
                }
            }
        });
        
        return panel;
    }

    private JPanel createTransactionsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Operations panel
        JPanel operationsPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        
        // Borrow panel
        JPanel borrowPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        borrowPanel.setBorder(BorderFactory.createTitledBorder("Borrow Book"));
        borrowPanel.add(new JLabel("ISBN:"));
        borrowIsbnField = new JTextField();
        borrowPanel.add(borrowIsbnField);
        JButton borrowButton = new JButton("Borrow");
        borrowPanel.add(borrowButton);
        borrowPanel.add(new JLabel("User ID:"));
        borrowUserIdField = new JTextField();
        borrowPanel.add(borrowUserIdField);
        borrowPanel.add(new JLabel(""));
        
        // Return panel
        JPanel returnPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        returnPanel.setBorder(BorderFactory.createTitledBorder("Return Book"));
        returnPanel.add(new JLabel("ISBN:"));
        returnIsbnField = new JTextField();
        returnPanel.add(returnIsbnField);
        JButton returnButton = new JButton("Return");
        returnPanel.add(returnButton);
        returnPanel.add(new JLabel("User ID:"));
        returnUserIdField = new JTextField();
        returnPanel.add(returnUserIdField);
        returnPanel.add(new JLabel(""));
        
        // Status panel
        statusArea = new JTextArea(3, 50);
        statusArea.setEditable(false);
        statusArea.setBackground(getBackground());
        JScrollPane statusScrollPane = new JScrollPane(statusArea);
        statusScrollPane.setBorder(BorderFactory.createTitledBorder("Status"));
        
        operationsPanel.add(borrowPanel);
        operationsPanel.add(returnPanel);
        operationsPanel.add(statusScrollPane);
        
        // Table panel
        String[] transactionColumns = {"Transaction ID", "Book ISBN", "User ID", "Borrow Date", "Status"};
        transactionsTableModel = new DefaultTableModel(transactionColumns, 0);
        transactionsTable = new JTable(transactionsTableModel);
        JScrollPane transactionsScrollPane = new JScrollPane(transactionsTable);
        transactionsScrollPane.setBorder(BorderFactory.createTitledBorder("Transactions"));
        
        panel.add(operationsPanel, BorderLayout.NORTH);
        panel.add(transactionsScrollPane, BorderLayout.CENTER);
        
        // Event handlers for transaction operations
        borrowButton.addActionListener(e -> borrowBook());
        returnButton.addActionListener(e -> returnBook());
        
        return panel;
    }

    private void setupEventHandlers() {
        // Event handlers are now set up in individual panel creation methods
    }

    // Book Dialog Methods
    private void showAddBookDialog() {
        JDialog dialog = new JDialog(this, "Add New Book", true);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridLayout(4, 2, 10, 10));
        
        JTextField isbnField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        
        dialog.add(new JLabel("ISBN:"));
        dialog.add(isbnField);
        dialog.add(new JLabel("Title:"));
        dialog.add(titleField);
        dialog.add(new JLabel("Author:"));
        dialog.add(authorField);
        
        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");
        
        addButton.addActionListener(e -> {
            String isbn = isbnField.getText().trim();
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            
            if (isbn.isEmpty() || title.isEmpty() || author.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Book book = new Book(isbn, title, author);
            if (library.addBook(book)) {
                JOptionPane.showMessageDialog(dialog, "Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshBooksTable();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to add book! ISBN might already exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        dialog.add(addButton);
        dialog.add(cancelButton);
        dialog.setVisible(true);
    }

    private void showUpdateBookDialog() {
        int selectedRow = booksTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a book to update!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String currentIsbn = (String) booksTableModel.getValueAt(selectedRow, 0);
        String currentTitle = (String) booksTableModel.getValueAt(selectedRow, 1);
        String currentAuthor = (String) booksTableModel.getValueAt(selectedRow, 2);
        
        JDialog dialog = new JDialog(this, "Update Book", true);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridLayout(4, 2, 10, 10));
        
        JTextField isbnField = new JTextField(currentIsbn);
        isbnField.setEditable(false); // ISBN cannot be changed
        JTextField titleField = new JTextField(currentTitle);
        JTextField authorField = new JTextField(currentAuthor);
        
        dialog.add(new JLabel("ISBN (readonly):"));
        dialog.add(isbnField);
        dialog.add(new JLabel("Title:"));
        dialog.add(titleField);
        dialog.add(new JLabel("Author:"));
        dialog.add(authorField);
        
        JButton updateButton = new JButton("Update");
        JButton cancelButton = new JButton("Cancel");
        
        updateButton.addActionListener(e -> {
            String newTitle = titleField.getText().trim();
            String newAuthor = authorField.getText().trim();
            
            if (newTitle.isEmpty() || newAuthor.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (library.updateBook(currentIsbn, newTitle, newAuthor)) {
                JOptionPane.showMessageDialog(dialog, "Book updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshBooksTable();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to update book!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        dialog.add(updateButton);
        dialog.add(cancelButton);
        dialog.setVisible(true);
    }

    private void showRemoveBookDialog() {
        int selectedRow = booksTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a book to remove!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String isbn = (String) booksTableModel.getValueAt(selectedRow, 0);
        String title = (String) booksTableModel.getValueAt(selectedRow, 1);
        String status = (String) booksTableModel.getValueAt(selectedRow, 3);
        
        if ("BORROWED".equals(status)) {
            JOptionPane.showMessageDialog(this, "Cannot remove a borrowed book!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int result = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to remove this book?\n\nISBN: " + isbn + "\nTitle: " + title,
                "Confirm Removal", JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            if (library.removeBook(isbn)) {
                JOptionPane.showMessageDialog(this, "Book removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshBooksTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to remove book!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // User Dialog Methods
    private void showAddUserDialog() {
        JDialog dialog = new JDialog(this, "Add New User", true);
        dialog.setSize(350, 150);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridLayout(3, 2, 10, 10));
        
        JTextField userIdField = new JTextField();
        JTextField nameField = new JTextField();
        
        dialog.add(new JLabel("User ID:"));
        dialog.add(userIdField);
        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        
        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");
        
        addButton.addActionListener(e -> {
            String userId = userIdField.getText().trim();
            String name = nameField.getText().trim();
            
            if (userId.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            User user = new User(userId, name);
            if (library.addUser(user)) {
                JOptionPane.showMessageDialog(dialog, "User added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshUsersTable();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to add user! User ID might already exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        dialog.add(addButton);
        dialog.add(cancelButton);
        dialog.setVisible(true);
    }

    private void showUpdateUserDialog() {
        int selectedRow = usersTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to update!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String currentUserId = (String) usersTableModel.getValueAt(selectedRow, 0);
        String currentName = (String) usersTableModel.getValueAt(selectedRow, 1);
        
        JDialog dialog = new JDialog(this, "Update User", true);
        dialog.setSize(350, 150);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridLayout(3, 2, 10, 10));
        
        JTextField userIdField = new JTextField(currentUserId);
        userIdField.setEditable(false); // User ID cannot be changed
        JTextField nameField = new JTextField(currentName);
        
        dialog.add(new JLabel("User ID (readonly):"));
        dialog.add(userIdField);
        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        
        JButton updateButton = new JButton("Update");
        JButton cancelButton = new JButton("Cancel");
        
        updateButton.addActionListener(e -> {
            String newName = nameField.getText().trim();
            
            if (newName.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please enter a name!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (library.updateUser(currentUserId, newName)) {
                JOptionPane.showMessageDialog(dialog, "User updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshUsersTable();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to update user!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        dialog.add(updateButton);
        dialog.add(cancelButton);
        dialog.setVisible(true);
    }

    private void showRemoveUserDialog() {
        int selectedRow = usersTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to remove!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String userId = (String) usersTableModel.getValueAt(selectedRow, 0);
        String name = (String) usersTableModel.getValueAt(selectedRow, 1);
        Integer borrowedCount = (Integer) usersTableModel.getValueAt(selectedRow, 2);
        
        if (borrowedCount > 0) {
            JOptionPane.showMessageDialog(this, "Cannot remove user with borrowed books!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int result = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to remove this user?\n\nUser ID: " + userId + "\nName: " + name,
                "Confirm Removal", JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            if (library.removeUser(userId)) {
                JOptionPane.showMessageDialog(this, "User removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshUsersTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to remove user!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void borrowBook() {
        String isbn = borrowIsbnField.getText().trim();
        String userId = borrowUserIdField.getText().trim();
        
        if (isbn.isEmpty() || userId.isEmpty()) {
            statusArea.setText("Please fill both ISBN and User ID fields!");
            return;
        }
        
        String result = library.borrowBook(isbn, userId);
        statusArea.setText("Borrow Result: " + result);
        
        borrowIsbnField.setText("");
        borrowUserIdField.setText("");
        refreshAllTables();
    }

    private void returnBook() {
        String isbn = returnIsbnField.getText().trim();
        String userId = returnUserIdField.getText().trim();
        
        if (isbn.isEmpty() || userId.isEmpty()) {
            statusArea.setText("Please fill both ISBN and User ID fields!");
            return;
        }
        
        String result = library.returnBook(isbn, userId);
        statusArea.setText("Return Result: " + result);
        
        returnIsbnField.setText("");
        returnUserIdField.setText("");
        refreshAllTables();
    }

    private void refreshBooksTable() {
        booksTableModel.setRowCount(0);
        List<Book> books = library.getAllBooks();
        for (Book book : books) {
            Object[] row = {book.getIsbn(), book.getTitle(), book.getAuthor(), book.getStatus()};
            booksTableModel.addRow(row);
        }
    }

    private void refreshUsersTable() {
        usersTableModel.setRowCount(0);
        List<User> users = library.getAllUsers();
        for (User user : users) {
            Object[] row = {user.getUserId(), user.getName(), user.getBorrowedBooksCount()};
            usersTableModel.addRow(row);
        }
    }

    private void refreshTransactionsTable() {
        transactionsTableModel.setRowCount(0);
        List<BorrowTransaction> transactions = library.getAllTransactions();
        for (BorrowTransaction transaction : transactions) {
            Object[] row = {
                transaction.getTransactionId(),
                transaction.getBookIsbn(),
                transaction.getUserId(),
                transaction.getBorrowDate().toString(),
                transaction.getStatus()
            };
            transactionsTableModel.addRow(row);
        }
    }

    private void refreshAllTables() {
        refreshBooksTable();
        refreshUsersTable();
        refreshTransactionsTable();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new LibraryGUI().setVisible(true);
        });
    }
}
