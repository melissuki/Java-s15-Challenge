package model;

public class Student extends MemberRecord{
    public Student(String memberId, String name) {
        super(memberId, "Student", name);
    }
    @Override
    public void whoyouare() {
        System.out.println("Ben bir öğrenci üyeyim, adım: " + name +
                " | Üye ID: " + memberId + " | Ödünçteki kitap: " + noBooksIssued + "/" + maxBookLimit);
    }

}
