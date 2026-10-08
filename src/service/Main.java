package service;
import model.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("**KÜTÜPHANE OTOMASYON TESTİ BAŞLIYOR**");

        Author author = new Author("A1", "Haruki Murakami");
        author.whoyouare();

        Book book1 = new StudyBooks("B1", "Norwegian Wood", author, 150.0, 1);
        book1.display();

        Reader reader = new Reader("Melis");
        reader.whoyouare();

        Librarian librarian = new Librarian("Ahmet Bey", "1234");
        librarian.whoyouare();

        System.out.println("**Kitap ödünç alma işlemi**");
        librarian.issueBook(reader, book1);

        System.out.println("**İade işlemi**");
        librarian.returnBook(reader,book1);

        System.out.println("**TEST BAŞARIYLA TAMAMLANDI**");
    }
}