package model;

import java.util.ArrayList;
import java.util.List;

public class Author extends Person {
    private String authorId;
    private List<Book> books;

    public Author(String authorId, String name) {
        super(name);
        this.authorId = authorId;
        this.books = new ArrayList<>();
    }

    public String getAuthorId() {
        return authorId;
    }

    public void newBook(Book book) {
        books.add(book);
    }

    public void showBook() {
        System.out.println("Yazar: " + name + " - Kitap Sayısı: " + books.size());
    }

    @Override
    public void whoyouare() {
        System.out.println("Ben bir Yazarım, adım: " + name);
    }



}
