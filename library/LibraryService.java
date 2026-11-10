package library;
import java.util.ArrayList;
import java.util.List;
public class LibraryService {
    // Lists to store data
    private final List<Book> books = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<BorrowRecord> records = new ArrayList<>();
    // ID counters
    private int nextBookId = 1;
    private int nextMemberId = 1;
    private int nextRecordId = 1;
    public Book addBook(
            String title,
            String author,
            int copies) {
        if (title == null || title.isBlank()) {
            throw new LibraryException(
                    "Book title cannot be empty."
            );
        }
        if (author == null || author.isBlank()) {
            throw new LibraryException(
                    "Author name cannot be empty."
            );
        }
        Book book = new Book(
                nextBookId,
                title,
                author,
                copies
        );
        books.add(book);
        nextBookId++;
        return book;
    }
    public List<Book> getAllBooks() {

        return List.copyOf(books);
    }
    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        throw new LibraryException(
                "Book with ID " + id + " not found."
        );
    }
    public List<Book> searchBooks(String keyword) {
        List<Book> result = new ArrayList<>();
        keyword = keyword.toLowerCase();
        for (Book book : books) {
            String title =
                    book.getTitle().toLowerCase();
            String author =
                    book.getAuthor().toLowerCase();
            if (title.contains(keyword)
                    || author.contains(keyword)) {
                result.add(book);
            }
        }
        return result;
    }
    public Member registerMember(
            String name,
            String email) {

        if (name == null || name.isBlank()) {

            throw new LibraryException(
                    "Member name cannot be empty."
            );
        }

        if (email == null || !email.contains("@")) {

            throw new LibraryException(
                    "Please enter a valid email."
            );
        }

        Member member = new Member(
                nextMemberId,
                name,
                email
        );

        members.add(member);

        nextMemberId++;

        return member;
    }


    // View all members

    public List<Member> getAllMembers() {

        return List.copyOf(members);
    }


    // Find member by ID

    public Member findMemberById(int id) {

        for (Member member : members) {

            if (member.getId() == id) {

                return member;
            }
        }

        throw new LibraryException(
                "Member with ID " + id + " not found."
        );
    }


    // ==========================================
    // BORROW BOOK
    // ==========================================

    public BorrowRecord borrowBook(
            int bookId,
            int memberId) {

        // Find book

        Book book = findBookById(bookId);

        // Check member

        findMemberById(memberId);


        // Check whether same member already
        // has the same book

        for (BorrowRecord record : records) {

            if (record.getBookId() == bookId
                    && record.getMemberId() == memberId
                    && !record.isReturned()) {

                throw new LibraryException(
                        "This member already has this book."
                );
            }
        }


        // Decrease available copies

        book.borrowCopy();


        // Create borrowing record

        BorrowRecord record = new BorrowRecord(
                nextRecordId,
                bookId,
                memberId
        );

        records.add(record);

        nextRecordId++;

        return record;
    }


    // ==========================================
    // RETURN BOOK
    // ==========================================

    public void returnBook(int recordId) {

        BorrowRecord record =
                findRecordById(recordId);


        if (record.isReturned()) {

            throw new LibraryException(
                    "This book has already been returned."
            );
        }


        // Find book

        Book book =
                findBookById(record.getBookId());


        // Increase available copies

        book.returnCopy();


        // Update record

        record.markReturned();
    }


    // ==========================================
    // BORROW RECORD OPERATIONS
    // ==========================================

    public BorrowRecord findRecordById(int id) {

        for (BorrowRecord record : records) {

            if (record.getId() == id) {

                return record;
            }
        }

        throw new LibraryException(
                "Borrow record not found."
        );
    }


    // View all records

    public List<BorrowRecord> getAllRecords() {

        return List.copyOf(records);
    }


    // View active borrowings

    public List<BorrowRecord> getActiveBorrowings() {

        List<BorrowRecord> activeRecords =
                new ArrayList<>();

        for (BorrowRecord record : records) {

            if (!record.isReturned()) {

                activeRecords.add(record);
            }
        }

        return activeRecords;
    }


    // ==========================================
    // INVENTORY REPORT
    // ==========================================

    public void printInventoryReport() {

        System.out.println();
        System.out.println("       INVENTORY REPORT");

        if (books.isEmpty()) {

            System.out.println(
                    "No books available."
            );

            return;
        }


        int totalCopies = 0;

        int availableCopies = 0;


        for (Book book : books) {

            System.out.println(book);

            totalCopies +=
                    book.getTotalCopies();

            availableCopies +=
                    book.getAvailableCopies();
        }


        int borrowedCopies =
                totalCopies - availableCopies;


        System.out.println("----*********-----");

        System.out.println(
                "Total Copies     : "
                        + totalCopies
        );

        System.out.println(
                "Available Copies : "
                        + availableCopies
        );

        System.out.println(
                "Borrowed Copies  : "
                        + borrowedCopies
        );
    }


    // ==========================================
    // ACTIVE BORROWINGS
    // ==========================================

    public void printActiveBorrowings() {

        System.out.println();
        System.out.println("       ACTIVE BORROWINGS");


        List<BorrowRecord> active =
                getActiveBorrowings();


        if (active.isEmpty()) {

            System.out.println(
                    "No active borrowings."
            );

            return;
        }


        for (BorrowRecord record : active) {

            System.out.println(record);
        }
    }
}