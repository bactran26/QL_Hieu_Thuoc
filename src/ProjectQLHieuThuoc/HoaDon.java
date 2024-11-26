package ProjectQLHieuThuoc;
import java.util.Scanner;

public class HoaDon implements INhap, IXuat{
    protected int maHoaDon;
    private String ngayThanhToan;
    private int soLuongThuoc;
    private static int soLuongHoaDon = 0;

    public HoaDon() {
        soLuongHoaDon++;
    }
    public HoaDon(int maHoaDon, String ngayThanhToan, int soLuong) {
        this.maHoaDon = maHoaDon;
        this.ngayThanhToan = ngayThanhToan;
        this.soLuongThuoc = soLuong;
        soLuongHoaDon++;
    }

    //get_set
    public static int getSoLuongHoaDon() {
        return soLuongHoaDon;
    }
    public int getMaHoaDon() {
        return maHoaDon;
    }
    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
    public String getNgayThanhToan() {
        return ngayThanhToan;
    }
    public void setNgayThanhToan(String ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }
    public int getSoLuong() {
        return soLuongThuoc;
    }
    public void setSoLuong(int soLuongThuoc) {
        this.soLuongThuoc = soLuongThuoc;
    }

    @Override
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma hoa don: ");
        maHoaDon = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhap ngay thanh toan: ");
        ngayThanhToan = sc.nextLine();
        System.out.print("Nhap so luong: ");
        soLuongThuoc = sc.nextInt();
        sc.nextLine();
        System.out.println("Tao hoa don thanh cong!");
    }

    @Override
    public void xuat() {
        System.out.println("Thong tin hoa don:");
        System.out.println("+Ma hoa don: " + maHoaDon);
        System.out.println("+Ngay thanh toan: " + ngayThanhToan);
        System.out.println("+So luong: " + soLuongThuoc);
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "maHoaDon=" + maHoaDon +
                ", ngayThanhToan='" + ngayThanhToan + '\'' +
                ", soLuong=" + soLuongThuoc +
                '}';
    }


}
