package model;

public abstract class MemberRecord extends Person {
    protected String memberId;
    protected String type;
    protected int noBooksIssued;
    protected final int maxBookLimit = 5;

    public MemberRecord(String memberId, String type, String name) {
        super(name);
        this.memberId = memberId;
        this.type = type;
        this.noBooksIssued = 0;
    }

    public boolean canBorrow() {
        return noBooksIssued < maxBookLimit;
    }

    public void incBookIssued() {
        if (noBooksIssued < maxBookLimit) {
            noBooksIssued++;
        }
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
}
