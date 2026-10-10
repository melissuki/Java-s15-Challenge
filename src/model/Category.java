package model;

public class Category {
    private String categoryId;
    private String name;

    public Category(String categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }
    public String getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category other = (Category) o;
        return categoryId.equals(other.categoryId);
    }

    @Override
    public int hashCode() {
        return categoryId.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}

