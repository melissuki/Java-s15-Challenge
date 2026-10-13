package model;

public class Faculty extends MemberRecord{
    public Faculty(String memberId) {
        super(memberId);
    }

    @Override
    public String getMemberType() {
        return "Akademisyen";
    }

}
