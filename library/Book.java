package library;
public class Book {
    private int id;
    private String title;
    private String author;
    private int totalCopies;
    private int availableCopies;
    public Book(int id, String title, String author, int totalCopies) {
        if (totalCopies <= 0) {
            throw new IllegalArgumentException(
                    "Number of copies must be greater than 0."
            );
        }
        this.id = id;
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getTotalCopies() {
        return totalCopies;
    }
    public int getAvailableCopies() {
        return availableCopies;
    }
    public boolean isAvailable() {
        return availableCopies > 0;
    }
    public void borrowCopy() {
        if (!isAvailable()) {
            throw new LibraryException(
                    "No copies of this book are available."
            );
        }
        availableCopies--;
    }
    public void returnCopy() {
        if (availableCopies >= totalCopies) {
            throw new LibraryException(
                    "All copies of this book are already in the library."
            );
        }

        availableCopies++;
    }
    @Override
    public String toString() {
        return "Book ID: " + id +
                " | Title: " + title +
                " | Author: " + author +
                " | Total Copies: " + totalCopies +
                " | Available Copies: " + availableCopies;
    }
}