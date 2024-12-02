package ProjectQLHieuThuoc;

import java.util.Scanner;

public class ThuocTay extends Thuoc{
    private String lieuLuong;
    private String cachSuDung;

    public ThuocTay(){}

    public ThuocTay(int maThuoc, String tenThuoc, String loaiThuoc, int soLuong, double giaNhapThuoc, double giaThuoc, HangSX hangSX, String lieuLuong, String cachSuDung){
        super(maThuoc, tenThuoc, loaiThuoc, soLuong, giaNhapThuoc, giaThuoc, hangSX);
        this.lieuLuong = lieuLuong;
        this.cachSuDung = cachSuDung;
    }

    public String getLieuLuong(){
        return lieuLuong;
    }

    public void setLieuLuong(String lieuLuong){
        this.lieuLuong = lieuLuong;
    }

    public String getCachSuDung(){
        return cachSuDung;
    }
    public void setCachSuDung(String cachSuDung){
        this.cachSuDung = cachSuDung;
    }

    @Override
    public void nhap(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap lieu luong: ");
        lieuLuong = sc.nextLine();
        System.out.println("Nhap huong dan su dung: ");
        cachSuDung = sc.nextLine();
        super.nhap();
    }

    @Override
    public void xuat(){
        System.out.println("\t Thong tin thuoc");
        System.out.println("Lieu luong: " + lieuLuong);
        System.out.println("Huong dan su dung: " + cachSuDung);
        super.xuat();
    }

    @Override
    public void sua(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap lieu luong moi: ");
        lieuLuong = sc.nextLine();
        System.out.println("Nhap cach su dung moi: ");
        cachSuDung = sc.nextLine();
        super.sua();
    }

}
