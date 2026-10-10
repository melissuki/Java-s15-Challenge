package model;

public class Journals extends Book {
    public Journals(String bookId, String name, Author author, Category category, double price, int edition) {
        super(bookId, name, author, category, price, edition);
    }

    @Override
    public String getType() {
        return "Dergi";
    }
}
