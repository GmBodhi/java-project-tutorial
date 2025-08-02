package step6;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LibraryGUI extends JFrame {
    private final SimpleLibrary library;
    private JTextField isbnField, titleField, authorField;
    private JTextField userIdField, nameField;
    private JTextField borrowIsbnField, borrowUserIdField;
    private JTextField returnIsbnField, returnUserIdField;
    private JTextArea statusArea;
    private DefaultTableModel booksModel, usersModel, transactionsModel;

    public LibraryGUI() {
        library = new SimpleLibrary();
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Simple Library Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Books", createBooksTab());
        tabs.addTab("Users", createUsersTab());
        tabs.addTab("Transactions", createTransactionsTab());

        add(tabs);
        refreshTables();
    }

    private JPanel createBooksTab() {
        JPanel panel = new JPanel(new BorderLayout());

        // Add form at top
        JPanel form = createFormPanel("Add Book", new String[]{"ISBN:", "Title:", "Author:"},
                new JTextField[]{isbnField = new JTextField(), titleField = new JTextField(), authorField = new JTextField()},
                this::addBook);
        panel.add(form, BorderLayout.NORTH);

        // Add table in center
        booksModel = new DefaultTableModel(new String[]{"ISBN", "Title", "Author", "Status"}, 0);
        JTable table = new JTable(booksModel);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Add status at bottom
        statusArea = new JTextArea(3, 0);
        statusArea.setEditable(false);
        statusArea.setBorder(BorderFactory.createTitledBorder("Status"));
        panel.add(statusArea, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createUsersTab() {
        JPanel panel = new JPanel(new BorderLayout());

        // Add form at top
        JPanel form = createFormPanel("Add User", new String[]{"User ID:", "Name:"},
                new JTextField[]{userIdField = new JTextField(), nameField = new JTextField()},
                this::addUser);
        panel.add(form, BorderLayout.NORTH);

        // Add table in center
        usersModel = new DefaultTableModel(new String[]{"User ID", "Name", "Books Borrowed"}, 0);
        JTable table = new JTable(usersModel);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createTransactionsTab() {
        JPanel panel = new JPanel(new BorderLayout());

        // Add borrow/return forms at top
        JPanel forms = new JPanel(new GridLayout(1, 2, 10, 0));

        forms.add(createFormPanel("Borrow Book", new String[]{"ISBN:", "User ID:"},
                new JTextField[]{borrowIsbnField = new JTextField(), borrowUserIdField = new JTextField()},
                this::borrowBook));
        forms.add(createFormPanel("Return Book", new String[]{"ISBN:", "User ID:"},
                new JTextField[]{returnIsbnField = new JTextField(), returnUserIdField = new JTextField()},
                this::returnBook));
        panel.add(forms, BorderLayout.NORTH);

        // Add table in center
        transactionsModel = new DefaultTableModel(new String[]{"ID", "Book", "User", "Status"}, 0);
        JTable table = new JTable(transactionsModel);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createFormPanel(String title, String[] labels, JTextField[] fields, Runnable action) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));

        JPanel form = new JPanel(new GridLayout(labels.length + 1, 2, 5, 5));
        for (int i = 0; i < labels.length; i++) {
            form.add(new JLabel(labels[i]));
            form.add(fields[i]);
        }

        JButton button = new JButton(title);
        button.addActionListener(e -> {
            action.run();
            refreshTables();
        });
        form.add(button);
        form.add(new JLabel(""));

        panel.add(form, BorderLayout.CENTER);
        return panel;
    }


    private void addBook() {
        if (validateFields(isbnField, titleField, authorField)) {
            Book book = new Book(isbnField.getText().trim(), titleField.getText().trim(), authorField.getText().trim());
            library.addBook(book);
            updateStatus("Book added: " + book.toString());
            clearFields(isbnField, titleField, authorField);
        }
    }

    private void addUser() {
        if (validateFields(userIdField, nameField)) {
            User user = new User(userIdField.getText().trim(), nameField.getText().trim());
            library.addUser(user);
            updateStatus("User added: " + user.toString());
            clearFields(userIdField, nameField);
        }
    }

    private void borrowBook() {
        if (validateFields(borrowIsbnField, borrowUserIdField)) {
            boolean success = library.borrowBook(borrowIsbnField.getText().trim(), borrowUserIdField.getText().trim());
            updateStatus(success ? "Book borrowed successfully!" : "Borrow failed!");
            clearFields(borrowIsbnField, borrowUserIdField);
        }
    }

    private void returnBook() {
        if (validateFields(returnIsbnField, returnUserIdField)) {
            boolean success = library.returnBook(returnIsbnField.getText().trim(), returnUserIdField.getText().trim());
            updateStatus(success ? "Book returned successfully!" : "Return failed!");
            clearFields(returnIsbnField, returnUserIdField);
        }
    }

    private boolean validateFields(JTextField... fields) {
        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                updateStatus("Please fill all fields!");
                return false;
            }
        }
        return true;
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) field.setText("");
    }

    private void updateStatus(String message) {
        if (statusArea != null)
            statusArea.setText(message + "\n" + (statusArea.getText().length() > 200 ? "" : statusArea.getText()));
    }

    private void refreshTables() {
        if (booksModel != null) {
            booksModel.setRowCount(0);
            for (Book book : library.books.values()) {
                booksModel.addRow(new Object[]{book.isbn, book.title, book.author, book.status});
            }
        }

        if (usersModel != null) {
            usersModel.setRowCount(0);
            for (User user : library.users.values()) {
                usersModel.addRow(new Object[]{user.userId, user.name, user.borrowedBooks.size() + "/" + User.MAX_BOOKS_LIMIT});
            }
        }

        if (transactionsModel != null) {
            transactionsModel.setRowCount(0);
            for (BorrowTransaction t : library.transactions.values()) {
                transactionsModel.addRow(new Object[]{t.transactionId, t.bookIsbn, t.userId, t.isReturned ? "Returned" : "Active"});
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryGUI().setVisible(true));
    }
}
