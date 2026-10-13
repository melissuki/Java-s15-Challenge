package model;

public class Reader extends Person{

    private final MemberRecord memberRecord;

    public Reader(String memberId, String name, MemberType type) {
        super(name);
        if(type == MemberType.FACULTY) {
            this.memberRecord = new Faculty(memberId);
        }
        else {
            this.memberRecord = new Student(memberId);
        }
    }

    public String getMemberId() {
        return memberRecord.getMemberId();
    }

    public String getMemberType() {
        return memberRecord.getMemberType();
    }

    public boolean canBorrow() {
        return memberRecord.canBorrow();
    }

    public void registerBorrow() {
        memberRecord.incBookIssued();
    }

    public void registerReturn() {
        memberRecord.decBookIssued();
    }

    @Override
    public void whoyouare() {
        System.out.println("Ben bir okuyucuyum, adım: " + getName() + " | " + memberRecord.getSummary());
    }

}
