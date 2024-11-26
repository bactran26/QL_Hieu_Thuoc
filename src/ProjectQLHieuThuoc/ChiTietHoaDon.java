package ProjectQLHieuThuoc;
import java.util.Scanner;

public class ChiTietHoaDon implements INhap, IXuat{
    private Thuoc thuoc;
    private int soLuongMua;
    private HoaDon maHoaDon;

    public ChiTietHoaDon() {};
    public ChiTietHoaDon(Thuoc thuoc, int soLuongMua, HoaDon maHoaDon) {
        this.thuoc = thuoc;
        this.soLuongMua = soLuongMua;
        this.maHoaDon = maHoaDon;
    }
    //get_set
    public Thuoc getThuoc() {
        return thuoc;
    }
    public void setThuoc(Thuoc thuoc) {
        this.thuoc = thuoc;
    }
    public int getSoLuongMua() {
        return soLuongMua;
    }
    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" +
                "maHoaDon=" + maHoaDon.getMaHoaDon() +
                ", maThuoc=" + thuoc.getMaThuoc() +
                ", thuoc=" + thuoc.getTenThuoc() +
                ", loaiThuoc=" + thuoc.getLoaiThuoc() +
                ", hangSX=" + thuoc.getHangSX().getTenHangSX() +
                ", giaThuoc=" + thuoc.getGiaThuoc() +
                ", soLuongMua=" + soLuongMua +
                '}';
    }
    @Override
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma hoa don: ");
        maHoaDon.setMaHoaDon(sc.nextInt());
        System.out.println("Nhap ma thuoc: ");
        thuoc.setMaThuoc(sc.nextInt());
        System.out.println("Nhap so luong mua: ");
        soLuongMua = sc.nextInt();
        sc.nextLine();
        System.out.println("Nhap thuoc thanh cong! ");

    }

    @Override
    public void xuat() {
        System.out.printf("%-10s %-20s %-15s %-20s %-10s %-10d\n",
                thuoc.getMaThuoc(),
                thuoc.getTenThuoc(),
                thuoc.getLoaiThuoc(),
                thuoc.getHangSX().getTenHangSX(),
                thuoc.getGiaThuoc(),
                soLuongMua);
    }

}
