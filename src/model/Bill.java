package model;

import java.time.LocalDate;

public class Bill {
    private final String billId;
    private final Reader reader;
    private final Book book;
    private final double amount;
    private final LocalDate date;
    private final String transactionType;

    public Bill(String billId, Reader reader, Book book, double amount, String transactionType) {
        this.billId = billId;
        this.reader = reader;
        this.book = book;
        this.amount = amount;
        this.date = LocalDate.now();
        this.transactionType = transactionType;
    }

    public void printBill() {
        System.out.println("----------------------------------------");
        System.out.println("           KÜTÜPHANE FATURA FİŞİ        ");
        System.out.println("----------------------------------------");
        System.out.println("Fatura ID    : " + billId);
        System.out.println("İşlem Türü   : " + (transactionType.equals("BORROW") ? "Ödünç Alma" : "İade"));
        System.out.println("Üye          : " + reader.getName() + " (" + reader.getMemberId() + ")");
        System.out.println("Kitap        : " + book.getName());
        System.out.println("Tutar        : " + amount + " TL");
        System.out.println("Tarih        : " + date);
        System.out.println("----------------------------------------");
    }

    public String getBillId() { return billId; }
    public double getAmount() { return amount; }
    public String getTransactionType() { return transactionType; }
}