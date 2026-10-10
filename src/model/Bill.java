package model;

import java.time.LocalDate;

public class Bill {
    private String billId;
    private MemberRecord member;
    private Book book;
    private double amount;
    private LocalDate date;
    private String transactionType;

    public Bill(String billId, MemberRecord member, Book book, double amount, String transactionType) {
        this.billId = billId;
        this.member = member;
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
        System.out.println("Üye          : " + member.getName());
        System.out.println("Kitap        : " + book.getName());
        System.out.println("Tutar        : " + amount + " TL");
        System.out.println("Tarih        : " + date);
        System.out.println("----------------------------------------");
    }

    public String getBillId() { return billId; }
    public double getAmount() { return amount; }
    public String getTransactionType() { return transactionType; }
}