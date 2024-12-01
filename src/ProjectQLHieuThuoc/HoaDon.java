package ProjectQLHieuThuoc;

import java.util.Scanner;

public class HoaDon implements INhap, IXuat {
    private int maHoaDon;
    private String ngayThanhToan;
    private Thuoc thuoc;
    private int soLuongMua;
    private KhachHang khachHang;
    private double thanhTien;

    // Constructor mặc định
    public HoaDon() {
        this.thuoc = null;
        this.khachHang = null;
    }

    // Constructor đầy đủ tham số
    public HoaDon(int maHoaDon, String ngayThanhToan, Thuoc thuoc, int soLuongMua, KhachHang khachHang) {
        this.maHoaDon = maHoaDon;
        this.ngayThanhToan = ngayThanhToan;
        this.thuoc = thuoc;
        this.soLuongMua = soLuongMua;
        this.khachHang = khachHang;
        this.thanhTien = tinhThanhTien();
    }

    // Getters and Setters
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

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    private double tinhThanhTien() {
        if (thuoc != null) {
            return soLuongMua * thuoc.getGiaThuoc();
        }
        return 0;
    }

    @Override
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== NHAP THONG TIN HOA DON ===");

        // Nhập mã hóa đơn
        System.out.print("Nhap ma hoa don: ");
        this.maHoaDon = sc.nextInt();
        sc.nextLine();

        // Nhập ngày thanh toán
        System.out.print("Nhap ngay thanh toan (dd/MM/yyyy): ");
        this.ngayThanhToan = sc.nextLine();

        // Tìm và nhập thông tin khách hàng từ file
        System.out.print("Nhap ma khach hang: ");
        int maKH = sc.nextInt();
        DSKhachHang dsKH = new DSKhachHang();
        dsKH.docFile("C:\\Users\\OS\\IdeaProjects\\exercise\\src\\ProjectQLHieuThuoc/input_DSKhachHang.txt");
        boolean timThayKH = false;
        for (KhachHang kh : dsKH.getDskh()) {
            if (kh.getMaKhachHang() == maKH) {
                this.khachHang = kh;
                timThayKH = true;
                break;
            }
        }
        if (!timThayKH) {
            System.out.println("Khong tim thay khach hang!");
            return;
        }

        // Tìm và nhập thông tin thuốc từ file
        System.out.print("Nhap ma thuoc: ");
        int maThuoc = sc.nextInt();
        DSThuoc dsThuoc = new DSThuoc();
        dsThuoc.taiDanhSachTuFile("C:\\Users\\OS\\IdeaProjects\\exercise\\src\\ProjectQLHieuThuoc/input_DSKhachHang.txt");
        this.thuoc = dsThuoc.timKiemThuocTheoMa(maThuoc);
        if (this.thuoc == null) {
            System.out.println("Khong tim thay thuoc!");
            return;
        }

        // Nhập số lượng mua
        System.out.print("Nhap so luong mua: ");
        this.soLuongMua = sc.nextInt();

        // Kiểm tra số lượng tồn
        if (this.soLuongMua > thuoc.getSoLuong()) {
            System.out.println("So luong ton khong du!");
            return;
        }

        // Tính thành tiền
        this.thanhTien = tinhThanhTien();
    }

    @Override
    public void xuat() {
        System.out.println("\n========== HOA DON ==========");
        System.out.println("Ma hoa don: " + maHoaDon);
        System.out.println("Ngay thanh toan: " + ngayThanhToan);

        System.out.println("\n--- THONG TIN KHACH HANG ---");
        if (khachHang != null) {
            System.out.println("Ma KH: " + khachHang.getMaKhachHang());
            System.out.println("Ten KH: " + khachHang.getTenKhachHang());
            System.out.println("SDT: " + khachHang.getSoDienThoai());
        }

        System.out.println("\n--- THONG TIN THUOC ---");
        if (thuoc != null) {
            System.out.println("Ma thuoc: " + thuoc.getMaThuoc());
            System.out.println("Ten thuoc: " + thuoc.getTenThuoc());
            System.out.println("Gia thuoc: " + String.format("%,.0f", thuoc.getGiaThuoc()));
            System.out.println("So luong mua: " + soLuongMua);
        }

        System.out.println("\nThanh tien: " + String.format("%,.0f", thanhTien));
        System.out.println("============================");
    }

    public void sua() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== SUA THONG TIN HOA DON ===");

        // Sửa ngày thanh toán
        System.out.print("Nhap ngay thanh toan moi (Enter de giu nguyen): ");
        String ngayMoi = sc.nextLine();
        if (!ngayMoi.isEmpty()) {
            this.ngayThanhToan = ngayMoi;
        }

        // Sửa số lượng mua
        System.out.print("Nhap so luong mua moi (0 de giu nguyen): ");
        int soLuongMoi = sc.nextInt();
        if (soLuongMoi > 0) {
            if (soLuongMoi <= thuoc.getSoLuong()) {
                this.soLuongMua = soLuongMoi;
                this.thanhTien = tinhThanhTien();
            } else {
                System.out.println("So luong ton khong du!");
            }
        }
    }
}