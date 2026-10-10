package model;

public class Magazines extends Book {
    public Magazines(String bookId, String name, Author author, Category category, double price, int edition) {
        super(bookId, name, author, category, price, edition);
    }

    @Override
    public String getType() {
        return "Magazin";
    }
}
