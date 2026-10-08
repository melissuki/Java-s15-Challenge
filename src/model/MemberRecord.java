package model;

public abstract class MemberRecord {
    protected String memberId;
    protected String type;
    protected int noBooksIssued;
    protected final int maxBookLimit = 5;
    protected String name;

    public MemberRecord(String memberId, String type, String name) {
        this.memberId = memberId;
        this.type = type;
        this.name = name;
        this.noBooksIssued = 0;
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

    public String getName() {
        return name;
    }
}
