package model;

public class Librarian extends Person{
    private final String password;

    public Librarian(String name, String password) {
        super(name);
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Lütfen geçerli bir şifre giriniz.");
        }
        this.password = password;
    }
    public boolean verifyPassword(String input) {
        return password.equals(input);
    }

    @Override
    public void whoyouare() {
        System.out.println("Ben Kütüphaneciyim, adım: " + getName());
    }
}
