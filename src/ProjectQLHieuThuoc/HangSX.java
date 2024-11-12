package ProjectQLHieuThuoc;

import java.util.Scanner;

public class HangSX implements INhap, IXuat{
    private int maHangSX;
    private String tenHangSX;
    private String diaChiHangSX;
    private long sdtHangSX;

    public HangSX() {}

    public HangSX(int maHangSX, String tenHangSX, String diaChiHangSX, long sdtHangSX){
        this.maHangSX = maHangSX;
        this.tenHangSX = tenHangSX;
        this.diaChiHangSX = diaChiHangSX;
        this.sdtHangSX = sdtHangSX;
    }

    public int getMaHangSX() {
        return maHangSX;
    }
    public void setMaHangSX(int maHangSX) {
        this.maHangSX = maHangSX;
    }

    public String getTenHangSX() {
        return tenHangSX;
    }
    public void setTenHangSX(String tenHangSX) {
    }

    public String getDiaChiHangSX() {
        return diaChiHangSX;
    }
    public void setDiaChiHangSX(String diaChiHangSX) {
        this.diaChiHangSX = diaChiHangSX;
    }

    public long getSdtHangSX() {
        return sdtHangSX;
    }
    public void setSdtHangSX(long sdtHangSX) {
        this.sdtHangSX = sdtHangSX;
    }
    @Override
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma hang sx: ");
        maHangSX = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhap ten hang sx: ");
        tenHangSX = sc.nextLine();
        System.out.print("Nhap dia chi hang sx: ");
        diaChiHangSX = sc.nextLine();
        System.out.print("Nhap sdt hang sx: ");
        sdtHangSX = sc.nextLong();
    }

    @Override
    public void xuat() {
        System.out.println("Ma hang sx: " + maHangSX);
        System.out.println("Ten hang sx: " + tenHangSX);
        System.out.println("Dia chi hang sx: " + diaChiHangSX);
        System.out.println("Sdt hang sx: " + sdtHangSX);
    }

    public void sua(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\t Thay doi thong tin hang sx");
        System.out.print("Nhap ma hang sx moi: ");
        this.setMaHangSX(sc.nextInt());
        System.out.print("Nhap ten hang sx moi: ");
        this.setTenHangSX(sc.nextLine());
        System.out.print("Nhap dia chi hang sx moi: ");
        this.setDiaChiHangSX(sc.nextLine());
        System.out.print("Nhap sdt hang sx moi: ");
        this.setSdtHangSX(sc.nextLong());
    }
}
