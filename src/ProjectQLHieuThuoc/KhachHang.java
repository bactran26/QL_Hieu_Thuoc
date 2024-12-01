package ProjectQLHieuThuoc;

import java.util.Scanner;

public class KhachHang implements INhap, IXuat {
    // Thuộc tính
    private int maKhachHang;
    private String tenKhachHang;
    private int tuoi;
    private String soDienThoai;
    private String diaChi;
    private String gioiTinh;

    // Constructor mặc định
    public KhachHang() {}

    // Constructor đầy đủ tham số
    public KhachHang(int     maKhachHang, String tenKhachHang, int tuoi,
                     String soDienThoai, String diaChi, String gioiTinh) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.tuoi = tuoi;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
    }

    // Các getter và setter
    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int  maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    // Phương thức nhập thông tin
    @Override
    public void nhap() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập mã khách hàng: ");
        this.maKhachHang = sc.nextInt();
        sc.nextLine();

        System.out.print("Nhập tên khách hàng: ");
        this.tenKhachHang = sc.nextLine();

        System.out.print("Nhập tuổi: ");
        this.tuoi = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập số điện thoại: ");
        this.soDienThoai = sc.nextLine();

        System.out.print("Nhập địa chỉ: ");
        this.diaChi = sc.nextLine();

        System.out.print("Nhập giới tính: ");
        this.gioiTinh = sc.nextLine();
    }

    // Phương thức xuất thông tin
    @Override
    public void xuat() {
        System.out.println("Thông tin khách hàng:");
        System.out.println("Mã khách hàng: " + maKhachHang);
        System.out.println("Tên khách hàng: " + tenKhachHang);
        System.out.println("Tuổi: " + tuoi);
        System.out.println("Số điện thoại: " + soDienThoai);
        System.out.println("Địa chỉ: " + diaChi);
        System.out.println("Giới tính: " + gioiTinh);
    }

        // Phương thức sửa thông tin
    @Override
    public String toString() {
        return "Ma so khach hang: " + maKhachHang +
                "\nHo va ten: " + tenKhachHang +
                "\nTuoi: " + tuoi +
                "\nSo dien thoai: " + soDienThoai +
                "\nDia chi: " + diaChi +
                "\nGioi tinh: " + gioiTinh;
    }

    public void sua(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma khach hang moi: ");
        this.setMaKhachHang(sc.nextInt());
        System.out.println("Nhap ten khach hang moi: ");
        this.setTenKhachHang(sc.nextLine());
        System.out.println("nhap tuoi moi: ");
        this.setTuoi(sc.nextInt());
        sc.nextLine();
        System.out.println("Nhap so dien thoai moi: ");
        this.setSoDienThoai(sc.nextLine());
        System.out.println("Nhap dia chi moi: ");
        this.setDiaChi(sc.nextLine());
        System.out.println("Nhap gioi tinh: ");
        this.setGioiTinh(sc.nextLine());
    }
}
