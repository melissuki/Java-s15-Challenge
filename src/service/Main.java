package service;

import model.*;
import repository.LibraryRepository;
import java.util.Scanner;
import java.util.UUID;

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

        MemberRecord member = new Student("M1", "Melis");

        repository.addBook(book1);
        repository.addBook(book2);
        repository.addMember(member);

        boolean running = true;
        while (running) {
            System.out.println("\n=== KÜTÜPHANE OTOMASYON SİSTEMİ ===");
            System.out.println("1. Yeni Kitap Ekle");
            System.out.println("2. Kitap Ara / Tümünü Listele");
            System.out.println("3. Kitap Güncelle");
            System.out.println("4. Kitap Sil");
            System.out.println("5. Yazara Göre Kitapları Listele");
            System.out.println("6. Kitap Ödünç Al");
            System.out.println("7. Kitap İade Et");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");

            String choiceStr = scanner.nextLine();
            int choice = -1;

            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Lütfen geçerli bir sayı giriniz!");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Kitap ID: ");
                    String bId = scanner.nextLine();
                    System.out.print("Kitap Adı: ");
                    String bName = scanner.nextLine();
                    System.out.print("Yazar Adı: ");
                    String aName = scanner.nextLine();
                    System.out.print("Fiyat: ");
                    double price = Double.parseDouble(scanner.nextLine());

                    Author newAuthor = new Author("A-" + UUID.randomUUID().toString().substring(0,4), aName);
                    Category newCat = new Category("C-Genel", "Genel");
                    Book newBook = new StudyBooks(bId, bName, newAuthor, newCat, price, 1);
                    service.addBook(newBook);
                    break;
                case 2:
                    System.out.print("Aranacak kelime (Tüm kitaplar için doğrudan Enter'a basın): ");
                    String keyword = scanner.nextLine();
                    service.searchBooks(keyword);
                    break;
                case 3:
                    System.out.print("Güncellenecek Kitap ID (Örn: B1): ");
                    String uId = scanner.nextLine();
                    System.out.print("Yeni Kitap Adı: ");
                    String uName = scanner.nextLine();
                    System.out.print("Yeni Fiyat: ");
                    double uPrice = Double.parseDouble(scanner.nextLine());
                    service.updateBook(uId, uName, uPrice);
                    break;
                case 4:
                    System.out.print("Silinecek Kitap ID: ");
                    String dId = scanner.nextLine();
                    service.deleteBook(dId);
                    break;
                case 5:
                    System.out.print("Yazar Adı (Örn: Haruki Murakami): ");
                    String authorName = scanner.nextLine();
                    service.listBooksByAuthor(authorName);
                    break;
                case 6:
                    System.out.print("Üye ID (Örn: M1): ");
                    String mIdBorrow = scanner.nextLine();
                    System.out.print("Kitap ID (Örn: B1): ");
                    String bIdBorrow = scanner.nextLine();
                    service.borrowBook(mIdBorrow, bIdBorrow);
                    break;
                case 7:
                    System.out.print("Üye ID (Örn: M1): ");
                    String mIdReturn = scanner.nextLine();
                    System.out.print("Kitap ID (Örn: B1): ");
                    String bIdReturn = scanner.nextLine();
                    service.returnBook(mIdReturn, bIdReturn);
                    break;
                case 0:
                    System.out.println("Sistemden çıkılıyor. İyi günler!");
                    running = false;
                    break;
                default:
                    System.out.println("Geçersiz seçim, lütfen menüdeki numaralardan birini giriniz.");
            }
        }
        scanner.close();
    }
}