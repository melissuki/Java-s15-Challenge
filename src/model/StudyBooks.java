package model;

public class StudyBooks extends Book {
    public StudyBooks(String bookId, String name, Author author, Category category, double price, int edition) {
        super(bookId, name, author, category, price, edition);
    }
    @Override
    public String getType() {
        return "Ders Kitabı";
    }
}

