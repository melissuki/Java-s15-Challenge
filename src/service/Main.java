package service;

import model.*;
import repository.LibraryRepository;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryRepository repository = new LibraryRepository();
        LibraryService service = new LibraryService(repository);
        Scanner scanner = new Scanner(System.in);

        Author author1 = new Author("A1", "Haruki Murakami");
        Category cat1 = new Category("C1", "Roman");
        Book book1 = new StudyBooks("B1", "Norwegian Wood", author1, cat1, 150.0, 1);

        Author author2 = new Author("A2", "Yoko Ogawa");
        Book book2 = new StudyBooks("B2", "Revenge", author2, cat1, 120.0, 1);

        Reader reader1 = new Reader("M1", "Melis", MemberType.STUDENT);
        Reader reader2 = new Reader("M2", "Ayşe", MemberType.FACULTY);

        repository.addBook(book1);
        author1.newBook(book1);
        repository.addBook(book2);
        author2.newBook(book2);
        repository.addReader(reader1);
        repository.addReader(reader2);

        Librarian librarian = new Librarian("Admin", "1234");

        boolean running = true;
        while (running) {
            System.out.println("\n=== KÜTÜPHANE OTOMASYON SİSTEMİ ===");
            System.out.println("1. Kütüphaneci Girişi");
            System.out.println("2. Okuyucu Girişi");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    if (librarianLogin(scanner, librarian)) {
                        librarianMenu(scanner, service);
                    }
                    break;
                case "2":
                    Reader reader = readerLogin(scanner, service);
                    if (reader != null) {
                        readerMenu(scanner, service, reader);
                    }
                    break;
                case "0":
                    System.out.println("Sistemden çıkılıyor. İyi günler!");
                    running = false;
                    break;
                default:
                    System.out.println("Geçersiz seçim, lütfen menüdeki numaralardan birini giriniz.");
            }
        }
        scanner.close();
    }

    private static boolean librarianLogin(Scanner scanner, Librarian librarian) {
        for (int attempt = 1; attempt <= 3; attempt++) {
            System.out.print("Şifre: ");
            String input = scanner.nextLine();
            if (librarian.verifyPassword(input)) {
                librarian.whoyouare();
                return true;
            }
            System.out.println("Hatalı şifre! Kalan hak: " + (3 - attempt));
        }
        System.out.println("Giriş başarısız. Ana menüye dönülüyor.");
        return false;
    }

    private static Reader readerLogin(Scanner scanner, LibraryService service) {
        System.out.print("Üye ID: ");
        String memberId = scanner.nextLine();
        Reader reader = service.findReaderById(memberId);
        if (reader == null) {
            System.out.println("Bu ID'ye sahip bir üye bulunamadı.");
            return null;
        }
        reader.whoyouare();
        return reader;
    }

    private static void librarianMenu(Scanner scanner, LibraryService service) {
        boolean active = true;
        while (active) {
            System.out.println("\n--- KÜTÜPHANECİ MENÜSÜ ---");
            System.out.println("1. Yeni Kitap Ekle");
            System.out.println("2. Kitap Ara / Tümünü Listele");
            System.out.println("3. Kitap Güncelle");
            System.out.println("4. Kitap Sil");
            System.out.println("5. Yazara Göre Listele");
            System.out.println("6. Kategoriye Göre Listele");
            System.out.println("7. Ödünçteki Kitaplar (Kimde?)");
            System.out.println("8. Okuyucuları Listele");
            System.out.println("9. Yeni Okuyucu Ekle");
            System.out.println("0. Çıkış Yap");
            System.out.print("Seçiminiz: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Kitap ID: ");
                    String bId = scanner.nextLine();
                    System.out.print("Kitap Adı: ");
                    String bName = scanner.nextLine();
                    System.out.print("Yazar Adı: ");
                    String aName = scanner.nextLine();
                    System.out.print("Kategori Adı: ");
                    String cName = scanner.nextLine();
                    double price = readDouble(scanner, "Fiyat: ");
                    System.out.print("Tür (1: Ders Kitabı, 2: Dergi, 3: Magazin): ");
                    String type = scanner.nextLine();

                    try {
                        Author newAuthor = service.findOrCreateAuthor(aName);
                        Category category = service.findOrCreateCategory(cName);
                        Book newBook;
                        if (type.equals("2")) {
                            newBook = new Journals(bId, bName, newAuthor, category, price, 1);
                        } else if (type.equals("3")) {
                            newBook = new Magazines(bId, bName, newAuthor, category, price, 1);
                        } else {
                            newBook = new StudyBooks(bId, bName, newAuthor, category, price, 1);
                        }
                        service.addBook(newBook);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Hata: " + e.getMessage() + " Kitap eklenmedi.");
                    }
                    break;
                case "2":
                    System.out.print("Aranacak kelime (Tüm kitaplar için doğrudan Enter'a basın): ");
                    String keyword = scanner.nextLine();
                    service.searchBooks(keyword);
                    break;
                case "3":
                    System.out.print("Güncellenecek Kitap ID (Örn: B1): ");
                    String uId = scanner.nextLine();
                    System.out.print("Yeni Kitap Adı: ");
                    String uName = scanner.nextLine();
                    double uPrice = readDouble(scanner, "Yeni Fiyat: ");
                    service.updateBook(uId, uName, uPrice);
                    break;
                case "4":
                    System.out.print("Silinecek Kitap ID: ");
                    String dId = scanner.nextLine();
                    service.deleteBook(dId);
                    break;
                case "5":
                    System.out.print("Yazar Adı (Örn: Haruki Murakami): ");
                    String authorName = scanner.nextLine();
                    service.listBooksByAuthor(authorName);
                    break;
                case "6":
                    service.listCategories();
                    System.out.print("Kategori Adı (Örn: Roman): ");
                    String categoryName = scanner.nextLine();
                    service.listBooksByCategory(categoryName);
                    break;
                case "7":
                    service.listBorrowedBooks();
                    break;
                case "8":
                    service.listReaders();
                    break;
                case "9":
                    System.out.print("Üye ID (Örn: M3): ");
                    String newMemberId = scanner.nextLine();
                    System.out.print("Üye Adı: ");
                    String newMemberName = scanner.nextLine();
                    System.out.print("Üye Tipi (1: Öğrenci, 2: Akademisyen): ");
                    String memberType = scanner.nextLine();

                    MemberType mType = memberType.equals("2") ? MemberType.FACULTY : MemberType.STUDENT;
                    try {
                        service.addReader(new Reader(newMemberId, newMemberName, mType));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Hata: " + e.getMessage());
                    }
                    break;
                case "0":
                    System.out.println("Kütüphaneci oturumu kapatıldı.");
                    active = false;
                    break;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    private static void readerMenu(Scanner scanner, LibraryService service, Reader reader) {
        boolean active = true;
        while (active) {
            System.out.println("\n--- OKUYUCU MENÜSÜ (" + reader.getName() + ") ---");
            System.out.println("1. Kitap Ara / Tümünü Listele");
            System.out.println("2. Yazara Göre Listele");
            System.out.println("3. Kategoriye Göre Listele");
            System.out.println("4. Kitap Ödünç Al");
            System.out.println("5. Kitap İade Et");
            System.out.println("6. Üzerimdeki Kitaplar");
            System.out.println("7. Profilim");
            System.out.println("0. Çıkış Yap");
            System.out.print("Seçiminiz: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Aranacak kelime (Tüm kitaplar için doğrudan Enter'a basın): ");
                    String keyword = scanner.nextLine();
                    service.searchBooks(keyword);
                    break;
                case "2":
                    System.out.print("Yazar Adı (Örn: Haruki Murakami): ");
                    String authorName = scanner.nextLine();
                    service.listBooksByAuthor(authorName);
                    break;
                case "3":
                    service.listCategories();
                    System.out.print("Kategori Adı (Örn: Roman): ");
                    String categoryName = scanner.nextLine();
                    service.listBooksByCategory(categoryName);
                    break;
                case "4":
                    System.out.print("Kitap ID: ");
                    String borrowId = scanner.nextLine();
                    service.borrowBook(reader.getMemberId(), borrowId);
                    break;
                case "5":
                    System.out.print("Kitap ID: ");
                    String returnId = scanner.nextLine();
                    service.returnBook(reader.getMemberId(), returnId);
                    break;
                case "6":
                    service.listBooksOfReader(reader.getMemberId());
                    break;
                case "7":
                    reader.whoyouare();
                    break;
                case "0":
                    System.out.println("Okuyucu oturumu kapatıldı.");
                    active = false;
                    break;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    private static double readDouble(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lütfen geçerli bir sayı giriniz!");
            }
        }
    }
}