package ProjectQLHieuThuoc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HoaDon implements INhap, IXuat {
    private int maHoaDon;
    private String ngayThanhToan;
    private int soLuongThuoc;
    private KhachHang khachHang;
    private ArrayList<ChiTietHoaDon> chiTietHoaDons;

    // Constructor mặc định
    public HoaDon() {
        //this.thuoc = null;
        this.maHoaDon = 0;
        this.ngayThanhToan = "";
        this.soLuongThuoc = 0;
        this.khachHang = null;
        this.chiTietHoaDons = new ArrayList<>();
    }

    // Constructor đầy đủ tham số
    public HoaDon(int maHoaDon, String ngayThanhToan, int soLuongThuoc, KhachHang khachHang, ArrayList<ChiTietHoaDon> chiTietHoaDons) {
        this.maHoaDon = maHoaDon;
        this.ngayThanhToan = ngayThanhToan;
        this.soLuongThuoc = soLuongThuoc;
        this.khachHang = khachHang;
        this.chiTietHoaDons = chiTietHoaDons;
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

    public int getSoLuongThuoc() {
        return soLuongThuoc;
    }

    public void setSoLuongThuoc(int soLuongThuoc) {
        this.soLuongThuoc = soLuongThuoc;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public ArrayList<ChiTietHoaDon> getChiTietHoaDons() {
        return chiTietHoaDons;
    }

    public void setChiTietHoaDons(ArrayList<ChiTietHoaDon> chiTietHoaDons) {
        this.chiTietHoaDons = chiTietHoaDons;
    }

    public double tinhTongThanhTien() {
        double tongThanhTien = 0;
        for (ChiTietHoaDon ct : chiTietHoaDons) {
            tongThanhTien += ct.tinhThanhTien();
        }
        return tongThanhTien;
    }

    public boolean napChiTietHoaDons() {
        try {
            DSThuoc dsThuoc = new DSThuoc();
            dsThuoc.taiDanhSachTuFile("input_DSThuoc.txt");
            ArrayList<ChiTietHoaDon> dsChiTietHoaDons = ChiTietHoaDon.getDsChiTietHoaDon();
            if (dsChiTietHoaDons.isEmpty()) {
                System.out.println("Chi Tiet Hoa Don Tren File Khong Co Danh Sach!");
                return false;
            }
            for (ChiTietHoaDon ct : dsChiTietHoaDons) {
                if (ct.getMaHD() == this.getMaHoaDon()) {
                    this.chiTietHoaDons.add(ct);
                }
            }
            return true;
        }
        catch (Exception e) {
            System.out.println("Loi ghi chi tiet hoa don!");
            return false;
        }
    }

    public String toString() {
        return String.format(
                "%d|%s|%d|%d",
                this.getMaHoaDon(),
                this.getNgayThanhToan(),
                this.getSoLuongThuoc(),
                this.getKhachHang().getMaKhachHang()
        );
    }

    public String print() {
        return String.format(
                "|%-10s|%-15s|%-15s|%-15s",
                this.maHoaDon,
                this.ngayThanhToan,
                this.soLuongThuoc,
                this.khachHang.getMaKhachHang()
        );
    }

    @Override
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== NHAP THONG TIN HOA DON ===");

        // Nhập mã hóa đơn
        System.out.print("Nhap ma hoa don 6 so:(0 de tao ma moi ngau nhien)");
        int maHD = sc.nextInt();
        ArrayList<HoaDon> dsHoaDon = DSHoaDon.getDsHoaDon();
        boolean taoMaMoi = false;
        boolean nhapMaHD = false;
        if (maHD == 0) { taoMaMoi = true;}
        else {nhapMaHD = true;}

        while (taoMaMoi) {// tạo mã đơn hàng mới
            maHD++;
            if (dsHoaDon.isEmpty()) {break;}
            for (HoaDon hd : dsHoaDon) {
                if (hd.getMaHoaDon() != maHD) {
                    taoMaMoi = false;
                    break;
                }
            }
        }

        while (nhapMaHD) {// nhập mã đơn hàng tay
            for (HoaDon hd : dsHoaDon) {
                if (hd.getMaHoaDon() == maHD) {
                    System.out.println("Ma hoa don da ton tai!");
                    System.out.print("Nhap ma hoa don moi: ");
                    maHD = sc.nextInt();
                    break;
                }
            }
            nhapMaHD = false;
        }
        this.maHoaDon = maHD;
        sc.nextLine();

        // Nhập ngày thanh toán
        System.out.print("Nhap ngay thanh toan (dd/mm/yyyy): ");
        this.ngayThanhToan = sc.nextLine();

        // Tìm và nhập thông tin khách hàng từ file
        ArrayList<KhachHang> dsKH = DSKhachHang.getDskh();
        System.out.println("Nhap ma khach hang: ");
        int maKH = sc.nextInt();
        if (dsKH.isEmpty()) {
            this.khachHang.setMaKhachHang(maKH);
        }
        for (KhachHang kh : dsKH) {
            if (kh.getMaKhachHang() == maKH) {
                this.khachHang = kh;
                break;
            }
        }
        if (this.khachHang.getMaKhachHang() != maKH) {
            System.out.println("Khong tim thay khach hang!");
        }

        // Nhập số lượng mua
        System.out.print("Nhap so luong thuoc: ");
        this.soLuongThuoc = sc.nextInt();

        //nhập chi tiết hóa đơn
        System.out.println("\n--- CHI TIET HOA DON ---");
        for (int i = 0; i < this.soLuongThuoc; i++) {
            System.out.println("Thuoc " + (i + 1) + ": ");
            ChiTietHoaDon ct = new ChiTietHoaDon();
            ct.setMaHD(this.maHoaDon);
            ct.nhap();
            this.chiTietHoaDons.add(ct);
        }

    }

    @Override
    public void xuat() {
        System.out.println("\n========== HOA DON ==========");
        System.out.println("Ma hoa don: " + this.maHoaDon);
        System.out.println("Ngay thanh toan: " + this.ngayThanhToan);

        System.out.println("\n--- THONG TIN KHACH HANG ---");
        if (khachHang != null) {
            System.out.println("Ma KH: " + this.khachHang.getMaKhachHang());
            System.out.println("Ten KH: " + this.khachHang.getTenKhachHang());
            System.out.println("SDT: " + this.khachHang.getSoDienThoai());
        }

        System.out.println("\n--- THONG TIN THUOC ---");
        System.out.println(
                String.format(
                        "|%-10s|%-15s|%-15s|%-10s|%-5s|%-10s",
                        "Ma Thuoc",
                        "Ten Thuoc",
                        "Loai Thuoc",
                        "Don Gia",
                        "SL",
                        "Thanh Tien"
                )
        );
        int i = 1;
        for (ChiTietHoaDon ct : this.chiTietHoaDons) {
            System.out.println("-".repeat(75));
            System.out.print(i++);
            ct.xuat();
        }

        System.out.println("\nTong thanh tien: " + String.format("%,.0f", this.tinhTongThanhTien()));
        System.out.println("=".repeat(75));
    }

    public void sua() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== SUA THONG TIN HOA DON ===");

        // Sửa ngày thanh toán
        System.out.print("Nhap ngay thanh: " + this.ngayThanhToan +" (Enter de giu nguyen) |");
        String ngayMoi = sc.nextLine();
        if (!ngayMoi.isEmpty()) {
            this.ngayThanhToan = ngayMoi;
        }
        // sửa khách hàng
        System.out.print("Ma khach hang:" + this.getKhachHang().getMaKhachHang() + " (0 de giu nguyen)| ");
        int maKH = sc.nextInt();
        if (maKH > 0) {
            ArrayList<KhachHang> dsKH = DSKhachHang.getDskh();
            boolean timThayKH = false;
            for (KhachHang kh : dsKH) {
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
        }

        // Sửa chi tiết hóa đơn
        System.out.println("\n- CHI TIET HOA DON -");
        System.out.println("1. Xoa het chi tiet hoa don va nhap lai\n2. Sua tung hang\n3. Luu va thoat\nNhap lua chon:");
        int select = sc.nextInt();
        DSThuoc dsThuoc = new DSThuoc();// danh sách thuốc
        dsThuoc.taiDanhSachTuFile("input_DSKhachHang.txt");
        switch (select) {
            case 1:
                this.chiTietHoaDons.clear();
                System.out.print("Nhap so luong thuoc: ");
                this.soLuongThuoc = sc.nextInt();
                for (int i = 0; i < this.soLuongThuoc; i++) {
                    System.out.println("Thuoc " + (i + 1) + ": ");
                    ChiTietHoaDon ct = new ChiTietHoaDon();
                    ct.setMaHD(this.maHoaDon);
                    ct.nhap();
                    this.chiTietHoaDons.add(ct);
                }
            case 2:
                System.out.println("Sua chi tiet hoa don:");
                int i = 1;
                for (ChiTietHoaDon ct : this.chiTietHoaDons) {
                    System.out.println( i + " |Ma thuoc:" + ct.getThuoc().getMaThuoc()+ " (0 de giu nguyen)| ");
                    int maThuoc = sc.nextInt();
                    if (maThuoc > 0) {ct.setThuoc(dsThuoc.timKiemThuocTheoMa(maThuoc));}

                    System.out.println(i + " |So luong mua:" + ct.getSoLuongMua()+ " (0 de giu nguyen)| ");
                    int soLuong = sc.nextInt();
                    if (soLuong > 0) {ct.setSoLuongMua(soLuong);}
                }
        }

    }
}