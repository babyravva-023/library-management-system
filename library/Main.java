package library;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner =
            new Scanner(System.in);

    private static final LibraryService library =
            new LibraryService();


    public static void main(String[] args) {

        // Load some sample data

        loadSampleData();


        System.out.println();
        System.out.println("LIBRARY MANAGEMENT SYSTEM");


        while (true) {

            showMenu();


            int choice =
                    readInt("Enter your choice: ");


            try {

                switch (choice) {

                    case 1:
                        addBook();
                        break;

                    case 2:
                        showBooks();
                        break;

                    case 3:
                        searchBooks();
                        break;

                    case 4:
                        registerMember();
                        break;

                    case 5:
                        showMembers();
                        break;

                    case 6:
                        borrowBook();
                        break;

                    case 7:
                        returnBook();
                        break;

                    case 8:
                        showBorrowRecords();
                        break;

                    case 9:
                        library.printInventoryReport();
                        break;

                    case 10:
                        library.printActiveBorrowings();
                        break;

                    case 0:

                        System.out.println(
                                "Thank you for visiting this libaray."
                        );

                        scanner.close();

                        return;

                    default:

                        System.out.println(
                                "Invalid choice."
                        );
                }

            } catch (LibraryException
                     | IllegalArgumentException e) {

                System.out.println(
                        "ERROR: " + e.getMessage()
                );
            }
        }
    }


    // ==========================================
    // MENU
    // ==========================================

    private static void showMenu() {

        System.out.println();

        System.out.println("----****----MENU ----*****----");

        System.out.println("1. Add Book");

        System.out.println("2. View All Books");

        System.out.println("3. Search Book");

        System.out.println("4. Register Member");

        System.out.println("5. View Members");

        System.out.println("6. Borrow Book");

        System.out.println("7. Return Book");

        System.out.println("8. View Borrow Records");

        System.out.println("9. Inventory Report");

        System.out.println("10. Active Borrowings");

        System.out.println("0. Exit");

        System.out.println("---------***********----------");
    }


    // ==========================================
    // ADD BOOK
    // ==========================================

    private static void addBook() {

        System.out.println();

        System.out.println("--------- ADD BOOK ---------");


        String title =
                readText("Enter book title: ");


        String author =
                readText("Enter author name: ");


        int copies =
                readInt("Enter number of copies: ");


        Book book =
                library.addBook(
                        title,
                        author,
                        copies
                );


        System.out.println();

        System.out.println(
                "Book added successfully!"
        );

        System.out.println(book);
    }


    // ==========================================
    // SHOW BOOKS
    // ==========================================

    private static void showBooks() {

        System.out.println();

        System.out.println("--------- ALL BOOKS ---------");


        List<Book> books =
                library.getAllBooks();


        if (books.isEmpty()) {

            System.out.println(
                    "No books found."
            );

            return;
        }


        for (Book book : books) {

            System.out.println(book);
        }
    }


    // ==========================================
    // SEARCH BOOK
    // ==========================================

    private static void searchBooks() {

        System.out.println();

        System.out.println("--------- SEARCH BOOK ---------");


        String keyword =
                readText(
                        "Enter title or author: "
                );


        List<Book> result =
                library.searchBooks(keyword);


        if (result.isEmpty()) {

            System.out.println(
                    "No matching books found."
            );

            return;
        }


        for (Book book : result) {

            System.out.println(book);
        }
    }


    // ==========================================
    // REGISTER MEMBER
    // ==========================================

    private static void registerMember() {

        System.out.println();

        System.out.println(
                "--------- REGISTER MEMBER ---------"
        );


        String name =
                readText("Enter member name: ");


        String email =
                readText("Enter email: ");


        Member member =
                library.registerMember(
                        name,
                        email
                );


        System.out.println();

        System.out.println(
                "Member registered successfully!"
        );

        System.out.println(member);
    }


    // ==========================================
    // SHOW MEMBERS
    // ==========================================

    private static void showMembers() {

        System.out.println();

        System.out.println(
                "--------- ALL MEMBERS ---------"
        );


        List<Member> members =
                library.getAllMembers();


        if (members.isEmpty()) {

            System.out.println(
                    "No members found."
            );

            return;
        }


        for (Member member : members) {

            System.out.println(member);
        }
    }


    // ==========================================
    // BORROW BOOK
    // ==========================================

    private static void borrowBook() {

        System.out.println();

        System.out.println(
                "--------- BORROW BOOK ---------"
        );


        int bookId =
                readInt("Enter book ID: ");


        int memberId =
                readInt("Enter member ID: ");


        BorrowRecord record =
                library.borrowBook(
                        bookId,
                        memberId
                );


        System.out.println();

        System.out.println(
                "Book borrowed successfully!"
        );

        System.out.println(record);
    }


    // ==========================================
    // RETURN BOOK
    // ==========================================

    private static void returnBook() {

        System.out.println();

        System.out.println(
                "--------- RETURN BOOK ---------"
        );


        int recordId =
                readInt(
                        "Enter borrow record ID: "
                );


        library.returnBook(recordId);


        System.out.println();

        System.out.println(
                "Book returned successfully!"
        );
    }


    // ==========================================
    // SHOW BORROW RECORDS
    // ==========================================

    private static void showBorrowRecords() {

        System.out.println();

        System.out.println(
                "--------- BORROW RECORDS ---------"
        );


        List<BorrowRecord> records =
                library.getAllRecords();


        if (records.isEmpty()) {

            System.out.println(
                    "No borrow records found."
            );

            return;
        }


        for (BorrowRecord record : records) {

            System.out.println(record);
        }
    }


    // ==========================================
    // READ INTEGER
    // ==========================================

    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }


    // ==========================================
    // READ TEXT
    // ==========================================

    private static String readText(String message) {

        while (true) {

            System.out.print(message);

            String value =
                    scanner.nextLine().trim();


            if (!value.isEmpty()) {

                return value;
            }


            System.out.println(
                    "Input cannot be empty."
            );
        }
    }


    // ==========================================
    // SAMPLE DATA
    // ==========================================

    private static void loadSampleData() {

        library.addBook(
                "Clean Code",
                "Robert C. Martin",
                3
        );


        library.addBook(
                "Effective Java",
                "Joshua Bloch",
                2
        );


        library.addBook(
                "Head First Java",
                "Kathy Sierra",
                4
        );


        library.registerMember(
                "Rahul",
                "rahul@gmail.com"
        );


        library.registerMember(
                "Priya",
                "priya@gmail.com"
        );
    }
}