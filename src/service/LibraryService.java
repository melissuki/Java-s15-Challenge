package service;

import model.*;
import repository.LibraryRepository;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class LibraryService {
    private LibraryRepository repository;

    public LibraryService(LibraryRepository repository) {
        this.repository = repository;
    }

    public void addBook(Book book) {
        if (repository.getBookById(book.getBookId()) != null) {
            System.out.println("Hata: " + book.getBookId() + " ID'li bir kitap zaten var!");
            return;
        }
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
        if (repository.getBookById(bookId) == null) {
            System.out.println("Hata: Silinecek kitap bulunamadı!");
            return;
        }
        if (repository.getBorrowerId(bookId) != null) {
            System.out.println("Hata: Bu kitap şu an ödünçte, iade edilmeden silinemez!");
            return;
        }
        repository.removeBook(bookId);
        System.out.println("Kitap sistemden silindi.");
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

    public Category findOrCreateCategory(String categoryName) {
        for (Category category : repository.getCategories()) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        return new Category("C-" + categoryName.toUpperCase(), categoryName);
    }

    public void listCategories() {
        System.out.println("--- Kategoriler ---");
        for (Category category : repository.getCategories()) {
            System.out.println("- " + category.getName());
        }
    }

    public void listBooksByCategory(String categoryName) {
        System.out.println("--- Kategori: " + categoryName + " Kitapları ---");
        boolean found = false;
        for (Book book : repository.getAllBooks()) {
            if (book.getCategory().getName().equalsIgnoreCase(categoryName)) {
                book.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Bu kategoride kitap bulunamadı.");
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

        if (!member.canBorrow()) {
            System.out.println("Hata: 5 kitap limitinize ulaştınız! Daha fazla kitap alamazsınız.");
            return;
        }

        if (!book.isStatus()) {
            System.out.println("Bu kitap şu an başka bir üyede (rafta değil)!");
            return;
        }

        book.setStatus(false);
        member.incBookIssued();
        repository.addBorrowRecord(bookId, memberId);

        String billId = "BILL-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
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

        String borrowerId = repository.getBorrowerId(bookId);
        if (borrowerId == null || !borrowerId.equals(memberId)) {
            System.out.println("Hata: Bu kitap bu üyede değil, iade alınamaz!");
            return;
        }

        book.setStatus(true);
        member.decBookIssued();
        repository.removeBorrowRecord(bookId);

        String billId = "BILL-RET-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        Bill bill = new Bill(billId, member, book, book.getPrice(), "RETURN");
        repository.addBill(bill);

        System.out.println("Kitap başarıyla iade alındı ve ücret iadesi yapıldı.");
        bill.printBill();
    }

    public void listBorrowedBooks() {
        System.out.println("--- Ödünçteki Kitaplar ---");
        if (repository.getBorrowedBooks().isEmpty()) {
            System.out.println("Şu an ödünçte kitap yok.");
            return;
        }
        for (Map.Entry<String, String> entry : repository.getBorrowedBooks().entrySet()) {
            Book book = repository.getBookById(entry.getKey());
            MemberRecord member = repository.getMemberById(entry.getValue());
            System.out.println(book.getName() + " -> " + member.getName() + " (" + member.getMemberId() + ")");
        }
    }

    public void listMembers() {
        System.out.println("--- Üyeler ---");
        for (MemberRecord member : repository.getAllMembers()) {
            member.whoyouare();
        }
    }

    public void addMember(MemberRecord member) {
        if (repository.getMemberById(member.getMemberId()) != null) {
            System.out.println("Hata: " + member.getMemberId() + " ID'li bir üye zaten var!");
            return;
        }
        repository.addMember(member);
        System.out.println("Üye başarıyla eklendi: " + member.getName());
    }

    public Collection<Book> getAllBooks() {
        return repository.getAllBooks();
    }
}
