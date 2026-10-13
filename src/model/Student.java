package model;

public class Student extends MemberRecord{
    public Student(String memberId, String name) {
        super(memberId, name);
    }

    @Override
    public String getMemberType() {
        return "Öğrenci";
    }

}
