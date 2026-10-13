package model;

public class Student extends MemberRecord{
    public Student(String memberId) {
        super(memberId);
    }

    @Override
    public String getMemberType() {
        return "Öğrenci";
    }

}
