package service;

import model.*;
import repository.LibraryRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LibraryService {
    private LibraryRepository repository;
    private Map<String, Integer> memberBookCounts = new HashMap<>();

    public LibraryService(LibraryRepository repository) {
        this.repository = repository;
    }

    public void addBook(Book book) {
        repository.addBook(book);
        System.out.println("Başarıyla eklendi: " + book.getName());
    }

    public void updateBook(String bookId, String newName, double newPrice) {
        Book book = repository.getBookById(bookId);
        if (book != null) {
            book.setName(newName);
            book.setPrice(newPrice);
            System.out.println("Kitap başarıyla güncellendi: " + newName);
        } else {
            System.out.println("Hata: Güncellenecek kitap bulunamadı!");
        }
    }

    public void deleteBook(String bookId) {
        if (repository.getBookById(bookId) != null) {
            repository.removeBook(bookId);
            System.out.println("Kitap sistemden silindi.");
        } else {
            System.out.println("Hata: Silinecek kitap bulunamadı!");
        }
    }

    public void listBooksByAuthor(String authorName) {
        System.out.println("--- Yazar: " + authorName + " Kitapları ---");
        boolean found = false;
        for (Book book : repository.getAllBooks()) {
            if (book.getAuthor().getName().equalsIgnoreCase(authorName)) {
                book.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Bu yazara ait kitap bulunamadı.");
        }
    }

    public Book findBookById(String bookId) {
        return repository.getBookById(bookId);
    }

    public void searchBooks(String keyword) {
        System.out.println("--- Arama Sonuçları ('" + keyword + "') ---");
        for (Book book : repository.getAllBooks()) {
            if (book.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getBookId().equalsIgnoreCase(keyword) ||
                    book.getAuthor().getName().toLowerCase().contains(keyword.toLowerCase())) {
                book.display();
            }
        }
    }

    public void borrowBook(String memberId, String bookId) {
        MemberRecord member = repository.getMemberById(memberId);
        Book book = repository.getBookById(bookId);

        if (member == null) {
            System.out.println("Hata: Üye bulunamadı!");
            return;
        }
        if (book == null) {
            System.out.println("Hata: Kitap bulunamadı!");
            return;
        }

        int currentCount = memberBookCounts.getOrDefault(memberId, 0);
        if (currentCount >= 5) {
            System.out.println("Hata: 5 kitap limitinize ulaştınız! Daha fazla kitap alamazsınız.");
            return;
        }

        if (!book.isStatus()) {
            System.out.println("Bu kitap şu an başka bir üyede (rafta değil)!");
            return;
        }

        book.setStatus(false);
        memberBookCounts.put(memberId, currentCount + 1);

        String billId = "BİLL-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        Bill bill = new Bill(billId, member, book, book.getPrice(), "BORROW");
        repository.addBill(bill);

        System.out.println("Kitap başarıyla ödünç alındı!");
        bill.printBill();
    }

    public void returnBook(String memberId, String bookId) {
        MemberRecord member = repository.getMemberById(memberId);
        Book book = repository.getBookById(bookId);

        if (member == null || book == null) {
            System.out.println("Hata: Üye veya kitap bulunamadı!");
            return;
        }

        book.setStatus(true);

        int currentCount = memberBookCounts.getOrDefault(memberId, 1);
        memberBookCounts.put(memberId, currentCount - 1);

        String billId = "BİLL-RET-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        Bill bill = new Bill(billId, member, book, book.getPrice(), "RETURN");
        repository.addBill(bill);

        System.out.println("Kitap başarıyla iade alındı ve ücret iadesi yapıldı.");
        bill.printBill();
    }

    public Collection<Book> getAllBooks() {
        return repository.getAllBooks();
    }
}