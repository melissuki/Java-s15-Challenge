package model;

public abstract class Person {
    private String name;

    public Person(String name) {
        setName(name);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Lütfen bir isim giriniz.");
        }
        this.name = name;
    }
    public abstract void whoyouare();
}
