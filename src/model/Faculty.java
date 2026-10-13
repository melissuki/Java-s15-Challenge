package model;

public class Faculty extends MemberRecord{
    public Faculty(String memberId, String name) {
        super(memberId, "Faculty", name);
    }
    @Override
    public void whoyouare() {
        System.out.println("Ben bir akademisyen üyeyim, adım: " + getName() +
                " | Üye ID: " + memberId + " | Ödünçteki kitap: " + noBooksIssued + "/" + maxBookLimit);
    }

}
