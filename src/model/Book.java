package model;

public abstract class Book {
    private final String bookId;
    private String name;
    private Author author;
    private Category category;
    private double price;
    private boolean status;
    private int edition;

    public Book(String bookId, String name, Author author, Category category, double price, int edition) {
        this.bookId = bookId;
        this.author = author;
        this.category = category;
        this.status = true;
        setName(name);
        setPrice(price);
        setEdition(edition);
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

    public int getEdition() {
        return edition;
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
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Lütfen bir isim giriniz.");
        }
        this.name = name;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Lütfen geçerli bir fiyat giriniz.");
        }
        this.price = price;
    }

    public void setEdition(int edition) {
        if (edition < 1) {
            throw new IllegalArgumentException("Lütfen geçerli bir sayı giriniz.");
        }
        this.edition = edition;
    }

    public abstract String getType();

    public void display() {
        System.out.println("[" + getType() + "] " + bookId + " - " + name +
                " | Yazar: " + author.getName() +
                " | Kategori: " + category.getName() +
                " | Fiyat: " + price + " TL" +
                " | Durum: " + (status ? "Rafta" : "Ödünçte"));
    }
}
