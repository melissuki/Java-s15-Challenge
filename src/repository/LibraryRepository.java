package repository;

import model.*;

import java.util.*;



public class LibraryRepository {
    private Map<String, Book> books = new HashMap<>();
    private Map<String, Reader> readers = new HashMap<>();
    private Map<String, String> borrowedBooks = new HashMap<>(); //
    private Set<Category> categories = new HashSet<>();
    private List<Bill> bills = new ArrayList<>();


    //burası kitap işlemleri için
    public void addBook(Book book) {
        books.put(book.getBookId(), book);
        if(book.getCategory() != null) {
            categories.add(book.getCategory());
        }
    }

    public void removeBook(String bookId) {
        books.remove(bookId);
    }

    public Book getBookById(String bookId) {
        return books.get(bookId);
    }

    public Collection<Book> getAllBooks() {
        return Collections.unmodifiableCollection(books.values());
    }

    //burası üye işlemleri için

    public void addReader(Reader reader) {
        readers.put(reader.getMemberId(), reader);
    }

    public Reader getReaderById(String memberId) {
        return readers.get(memberId);
    }

    public Collection<Reader> getAllReaders() {
        return Collections.unmodifiableCollection(readers.values());
    }


    //burası ödünç işlemleri için

    public void addBorrowRecord(String bookId, String memberId) {
        borrowedBooks.put(bookId, memberId);
    }

    public void removeBorrowRecord(String bookId) {
        borrowedBooks.remove(bookId);
    }

    public String getBorrowerId(String bookId) {
        return borrowedBooks.get(bookId);
    }

    public Map<String, String> getBorrowedBooks() {
        return Collections.unmodifiableMap(borrowedBooks);
    }


    //burası fatura işlemleri için

    public void addBill(Bill bill) {
        bills.add(bill);
    }

    public List<Bill> getAllBills() {
        return Collections.unmodifiableList(bills);
    }


    //burası kategori işlemleri için

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }
}
