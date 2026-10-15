package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Author extends Person {
    private final String authorId;
    private final Set<Book> books;

    public Author(String authorId, String name) {
        super(name);
        this.authorId = authorId;
        this.books = new HashSet<>();
    }

    public String getAuthorId() {
        return authorId;
    }

    public void newBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public Set<Book> getBooks() {
        return Collections.unmodifiableSet(books);
    }

    public void showBook() {
        System.out.println("Yazar: " + getName() + " - Kitap Sayısı: " + books.size());
    }

    @Override
    public void whoyouare() {
        System.out.println("Ben bir Yazarım, adım: " + getName());
    }



}
