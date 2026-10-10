package model;

public abstract class Book {
    protected String bookId;
    protected String name;
    protected Author author;
    protected Category category;
    protected double price;
    protected boolean status;
    protected int edition;

    public Book(String bookId, String name, Author author, Category category, double price, int edition) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.category = category;
        this.price = price;
        this.status = true;
        this.edition = edition;
    }

    public String getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void display() {
        System.out.println("Kitap: " + name + " | Yazar: " + author.getName() + " | Fiyat: " + price + " TL");
    }
}
