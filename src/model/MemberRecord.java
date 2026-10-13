package model;

public abstract class MemberRecord extends Person {
    private final String memberId;
    private int noBooksIssued;
    private static final int MAX_BOOK_LIMIT = 5;

    public MemberRecord(String memberId, String name) {
        super(name);
        this.memberId = memberId;
        this.noBooksIssued = 0;
    }

    public boolean canBorrow() {
        return noBooksIssued < MAX_BOOK_LIMIT;
    }

    public void incBookIssued() {
        if (noBooksIssued >= MAX_BOOK_LIMIT) {
            throw new IllegalStateException("Kitap limitine ulaşıldı: " + MAX_BOOK_LIMIT);
        }
        noBooksIssued++;
    }

    public void decBookIssued() {
        if(noBooksIssued > 0) {
            noBooksIssued--;
        }
    }

    public String getMemberId() {
        return memberId;
    }

    public int getNoBooksIssued() {
        return noBooksIssued;
    }

    public int getMaxBookLimit() {
        return MAX_BOOK_LIMIT;
    }

    @Override
    public void whoyouare() {
        System.out.println("Ben bir " + getMemberType() + " üyeyim, adım: " + getName() +
                " | Üye ID: " + memberId + " | Ödünçteki kitap: " + noBooksIssued + "/" + MAX_BOOK_LIMIT);
    }

    public abstract String getMemberType();
}
