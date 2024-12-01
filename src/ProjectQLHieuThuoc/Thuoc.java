package ProjectQLHieuThuoc;

import java.util.Scanner;

public class Thuoc implements INhap, IXuat{
    private int maThuoc;
    private String tenThuoc;
    private String loaiThuoc;
    private int soLuong;
    private double giaNhapThuoc;
    private double giaThuoc;
    private HangSX hangSX;


    public Thuoc(){}

    public Thuoc(int maThuoc, String tenThuoc, String loaiThuoc, int soLuong, double giaNhapThuoc, double giaThuoc, HangSX hangSX){
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.loaiThuoc = loaiThuoc;
        this.soLuong = soLuong;
        this.giaNhapThuoc = giaNhapThuoc;
        this.giaThuoc = giaThuoc;
        this.hangSX = hangSX;
    }

    public int getMaThuoc() {
        return maThuoc;
    }
    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }
    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public String getLoaiThuoc() {
        return loaiThuoc;
    }
    public void setLoaiThuoc(String loaiThuoc) {
        this.loaiThuoc = loaiThuoc;
    }

    public int getSoLuong() {
        return soLuong;
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaNhapThuoc() {
        return giaNhapThuoc;
    }

    public void setGiaNhapThuoc(double giaNhapThuoc) {
        this.giaNhapThuoc = giaNhapThuoc;
    }

    public double getGiaThuoc() {
        return giaThuoc;
    }
    public void setGiaThuoc(double giaThuoc) {
        this.giaThuoc = giaThuoc;
    }

    public HangSX getHangSX() {
        return hangSX;
    }
    public void setHangSX(HangSX hangSX) {
        this.hangSX = hangSX;
    }


    @Override
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma thuoc: ");
        maThuoc = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhap ten thuoc: ");
        tenThuoc = sc.nextLine();
        System.out.print("Nhap loai thuoc: ");
        loaiThuoc = sc.nextLine();
        System.out.print("Nhap so luong: ");
        soLuong = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhap gia nhap thuoc: ");
        giaNhapThuoc = sc.nextDouble();
        System.out.print("Nhap gia thuoc: ");
        giaThuoc = sc.nextDouble();
        System.out.println("\tNhap ten hang san xuat: ");
        hangSX.nhap();
        System.out.println("Nhap du lieu thanh cong!");
    }

    @Override
    public void xuat() {
        System.out.println("\tThong tin san pham");
        System.out.println("Ma thuoc: " + maThuoc);
        System.out.println("Ten thuoc: " + tenThuoc);
        System.out.println("Loai thuoc: " + loaiThuoc);
        System.out.println("So luong: " + soLuong);
        System.out.println("Gia nhap thuoc: " + giaNhapThuoc);
        System.out.println("Gia thuoc: " + giaThuoc);
        hangSX.xuat();
    }
    @Override
    public String toString() {
        return "Thuoc[" +
                "maThuoc=" + maThuoc +
                ", tenThuoc='" + tenThuoc + '\'' +
                ", loaiThuoc='" + loaiThuoc + '\'' +
                ", soLuong=" + soLuong +
                ", giaNhapThuoc=" + giaNhapThuoc +
                ", giaThuoc=" + giaThuoc +
                ", hangSX=" + hangSX +
                ']';
    }

    public void sua(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma san pham moi: ");
        this.setMaThuoc(sc.nextInt());
        System.out.println("Nhap ten san pham moi: ");
        this.setTenThuoc(sc.nextLine());
        System.out.println("Nhap loai san pham moi: ");
        this.setLoaiThuoc(sc.nextLine());
        System.out.println("Nhap so luong moi: ");
        this.setSoLuong(sc.nextInt());
        System.out.println("Nhap gia nhap moi: ");
        this.setGiaNhapThuoc(sc.nextDouble());
        System.out.println("Nhap gia thuoc moi: ");
        this.setGiaThuoc(sc.nextDouble());
        System.out.println("Nhap ten hang sx moi: ");
        hangSX.setTenHangSX(sc.nextLine());

        hangSX.sua();
    }
    
}
