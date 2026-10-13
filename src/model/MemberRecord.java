package model;

public abstract class MemberRecord {
    private final String memberId;
    private int noBooksIssued;
    private static final int MAX_BOOK_LIMIT = 5;

    public MemberRecord(String memberId) {
        if(memberId == null || memberId.isBlank()) {
            throw new IllegalArgumentException("Üye ID boş olamaz.");
        }
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

    public String getSummary() {
        return getMemberType() + " | Üye ID: " + memberId +
                " | Ödünçteki kitap: " + noBooksIssued + "/" + MAX_BOOK_LIMIT;
    }

    public abstract String getMemberType();
}
