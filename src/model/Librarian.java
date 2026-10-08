package model;

public class Librarian extends Person{
    private String password;

    public Librarian(String name, String password) {
        super(name);
        this.password = password;
    }

    public boolean verifyMember(MemberRecord member) {
        return member != null;
    }

    public void issueBook(Reader reader, Book book) {
        reader.borrowBook(book);
    }

    public void returnBook(Reader reader, Book book) {
        reader.returnBook(book);
    }

    @Override
    public void whoyouare() {
        System.out.println("Ben Kütüphaneciyim, adım: " + name);
    }
}
