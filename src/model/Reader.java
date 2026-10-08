package model;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person{
    private List<Book> books;

    public Reader(String name) {
        super(name);
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void borrowBook(Book book) {
        if(book.isStatus() && books.size() < 5) {
            books.add(book);
            book.setStatus(false);
            System.out.println(name + " adlı okuyucu '" + book.getName() + "' kitabını ödünç aldı.");
        }
        else {
            System.out.println("Kitap alınamadı! (Limit dolu veya kitap rafta değil)");
        }
    }

    public void returnBook(Book book) {
        if(books.contains(book)) {
            books.remove(book);
            book.setStatus(true);
            System.out.println(name + " adlı okuyucu '" + book.getName() + "' kitabını iade etti.");
        }
    }

    @Override
    public void whoyouare() {
        System.out.println("Ben bir Kütüphane Okuyucusuyum, adım: " + name);
    }



}
