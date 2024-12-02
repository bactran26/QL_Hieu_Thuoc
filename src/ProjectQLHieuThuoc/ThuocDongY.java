package ProjectQLHieuThuoc;

import java.util.Scanner;

public class ThuocDongY extends Thuoc{
    private String thanhPhanThaoDuoc;
    private String congDung;

    public ThuocDongY(){}

    public ThuocDongY(int maThuoc, String tenThuoc, String loaiThuoc, int soLuong, double giaNhapThuoc, double giaThuoc, HangSX hangSX, String thanhPhanThaoDuoc, String congDung){
        super(maThuoc, tenThuoc, loaiThuoc, soLuong, giaNhapThuoc, giaThuoc, hangSX);
        this.thanhPhanThaoDuoc = thanhPhanThaoDuoc;
        this.congDung = congDung;
    }

    public String getThanhPhanThaoDuoc(){
        return thanhPhanThaoDuoc;
    }

    public void setThanhPhanThaoDuoc(String thanhPhanThaoDuoc){
        this.thanhPhanThaoDuoc =thanhPhanThaoDuoc;
    }

    public String getCongDung(){
        return congDung;
    }

    public void setCongDung(String congDung){
        this.congDung = congDung;
    }

    @Override
    public void nhap(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap thanh phan thao duoc: ");
        thanhPhanThaoDuoc = sc.nextLine();
        System.out.println("Nhap cong dung: ");
        congDung = sc.nextLine();
        super.nhap();
    }
    @Override
    public void xuat(){
        System.out.println("\tThong tin thuoc");
        System.out.println("Thanh phan thao duoc: " + thanhPhanThaoDuoc);
        System.out.println("Cong dung: " + congDung);
        super.xuat();
    }

    @Override
    public void sua(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap thanh phan thao duoc moi: ");
        thanhPhanThaoDuoc = sc.nextLine();
        System.out.println("Nhap cong dung moi: ");
        congDung = sc.nextLine();
    }
}
