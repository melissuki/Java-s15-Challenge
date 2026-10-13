package model;

public class Faculty extends MemberRecord{
    public Faculty(String memberId, String name) {
        super(memberId, name);
    }

    @Override
    public String getMemberType() {
        return "Akademisyen";
    }

}
