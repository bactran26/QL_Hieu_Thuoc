package projectQLSach;

public class Test {
    public static void main(String[] args){
        Ngay n1 = new Ngay(26,  7, 2003);
        Ngay n2 = new Ngay(15, 5, 2003);

        HangSanXuat h1 = new HangSanXuat("phuong nam", "Viet Nam");
        HangSanXuat h2 = new HangSanXuat("Bac Tran", "Viet Nam");

        BoPhim b1 = new BoPhim("Bo gia", 2003, h1, 50000, n1);
        BoPhim b2 = new BoPhim("Tay Choi", 2010, h2, 60000, n2);


        System.out.println("So sanh gia: " + b1.kiemTraGiaVeReHon(b2));
        System.out.println("Ten hang san xuat la: " + h1.getTenHangSanXuat());
    }
}
