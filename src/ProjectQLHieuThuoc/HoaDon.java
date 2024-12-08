package ProjectQLHieuThuoc;

import java.util.Arrays;
import java.util.Scanner;

public class HoaDon extends SC implements INhap, IXuat {
    private int maHoaDon;
    private String ngayThanhToan;
    private int soLuongMua;
    private KhachHang khachHang;
    private ChiTietHoaDon[] dsMuaThuoc = new ChiTietHoaDon[0];

    // Constructor mặc định
    public HoaDon() {
        this.maHoaDon = 0;
        this.ngayThanhToan = "";
        this.khachHang = null;
        this.soLuongMua = 1;
    }

    // Constructor đầy đủ tham số
    public HoaDon(int maHoaDon, String ngayThanhToan, int soLuongMua, KhachHang khachHang, ChiTietHoaDon[] dsMuaThuoc) {
        this.maHoaDon = maHoaDon;
        this.ngayThanhToan = ngayThanhToan;
        this.soLuongMua = soLuongMua;
        this.khachHang = khachHang;
        this.dsMuaThuoc = dsMuaThuoc;
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

    //method

    public void themChiTietHoaDon(ChiTietHoaDon chitiet) {
        dsMuaThuoc = Arrays.copyOf(dsMuaThuoc, dsMuaThuoc.length + 1);
        dsMuaThuoc[dsMuaThuoc.length - 1] = chitiet;
    }

    public double tinhTongTien() {
        double thanhTien = 0;
        for (ChiTietHoaDon thuoc : dsMuaThuoc) {
            thanhTien += thuoc.tinhThanhTien();
        }
        return thanhTien;
    }

    public void sua() {
        System.out.println("\n=== SUA THONG TIN HOA DON ===");

        // Sửa ngày thanh toán
        System.out.print("Ngày thanh toán: " + this.ngayThanhToan +" (Enter de giu nguyen) |");
        String ngayMoi = sc.nextLine();
        if (!ngayMoi.isEmpty()) {
            this.ngayThanhToan = ngayMoi;
        }

        // sửa khách hàng
        DSKhachHang.xemDSKH();
        System.out.print("Ma khach hang:" + this.getKhachHang().getMaKhachHang() + " (0 de giu nguyen)| ");
        int maKH = sc.nextInt();
        if (maKH > 0) {
            while (true) {
                KhachHang kh = DSKhachHang.timKiemKhachHangTheoMa(maKH);
                if (kh != null) {
                    this.khachHang = kh;
                    break;
                }
                System.out.println("Khách hàng không tông tại!\nVui lòng nhập lại(0 để tạo mới):");
                maKH = sc.nextInt();
                if (maKH == 0) {
                    KhachHang khachHang = new KhachHang();
                    khachHang.nhap();
                    DSKhachHang.themKhachHang(khachHang);
                    this.khachHang = kh;
                    break;
                }
            }
        }

        // sua chi tiet hoa
        System.out.println("\n- CHI TIET HOA DON -");
        System.out.print("Bạn muốn tiếp tục sửa chi tiết hóa đơn chứ (Y/N)?");
        String choice = sc.nextLine().trim().toLowerCase();
        if (!choice.equals("y") && !choice.equals("yes") && !choice.equals("1")) {
            for (ChiTietHoaDon ct : dsMuaThuoc) {
                ct.sua();
            }
        }

    }

    @Override
    public void nhap() {
        System.out.println("\n=== NHAP THONG TIN HOA DON ===");

        // Nhập mã hóa đơn
        System.out.print("Nhập mã hóa đơn (0 để tạo mã tự động): ");
        int maHD;
        while (true) {
            maHD = sc.nextInt();
            if(maHD == 0) {
                this.maHoaDon = DSHoaDon.taoMaHoaDonNgauNhien();
                System.out.println("Mã hóa đơn là:" + this.maHoaDon);
                break;
            }
            if(DSHoaDon.timKiemHoaDonTheoMa(maHD) != null) {
                System.out.println("Hóa đơn đã tồn tại!\n Vui lòng nhập lại (0 để tạo mã tự động):");
            } else {
                break;
            }
        }//kiểm tra tồn tại hóa don
        sc.nextLine();

        // Nhập ngày thanh toán
        System.out.print("Nhap ngay thanh toan (dd/MM/yyyy): ");
        String ngayThanhToan = sc.nextLine();
        this.ngayThanhToan = ngayThanhToan;

        // Chọn khách hàng
        DSKhachHang.xemDSKH();
        System.out.print("Nhập mã khách hàng(0 để tạo khách hàng mới): ");
        int maKH = sc.nextInt();
        while (true) {
            if (maKH == 0) {
                KhachHang kh = new KhachHang();
                kh.nhap();
                DSKhachHang.themKhachHang(kh);
                this.khachHang = kh;
                break;
            }
            KhachHang khachHang = DSKhachHang.timKiemKhachHangTheoMa(maKH);
            if (khachHang != null) {
                this.khachHang = khachHang;
                break;
            }
            System.out.println("Mã khách hàng không tồn tại!\nVui lòng nhập lại.(0 để tạo khách hàng mới):");
            maKH = sc.nextInt();
        }

        // Nhập số lượng mua
        while (true) {
            System.out.print("Nhập số lượng loại thuốc của hóa đơn: ");
            this.soLuongMua = sc.nextInt();
            if (this.soLuongMua >= 0) {
                break;
            }
            System.out.println("Vui lòng nhập số lượng dương!");
        }
        DSThuoc.xemDSThuoc();
        for (int i = 0; i < soLuongMua; i++) {
            System.out.print(i+1 + "/.");
            ChiTietHoaDon ct = new ChiTietHoaDon();
            ct.nhap();
            if (ct.getThuoc() != null && ct.getSoLuongMua() != 0) {
                ct.setMaHD(this.maHoaDon);
                ChiTietHoaDon.themChiTietHoaDon(ct);
                this.themChiTietHoaDon(ct);
            }
        }
        this.soLuongMua = dsMuaThuoc.length;

    }

    @Override
    public void xuat() {
        System.out.println("\n========== HÓA ĐƠN ==========");
        System.out.println("Mã hóa đơn     : " + maHoaDon);
        System.out.println("Ngày thanh toán: " + ngayThanhToan);

        System.out.println("\n--- THÔNG TIN KHÁCH HÀNG ---");
        if (khachHang != null) {
            System.out.println("Mã khách hàng  : " + khachHang.getMaKhachHang());
            System.out.println("Tên khách hàng : " + khachHang.getTenKhachHang());
            System.out.println("SDT            : " + khachHang.getSoDienThoai());
        }

        System.out.println("\n--- THÔNG TIN THUỐC ---");
        System.out.println(
                String.format(
                        "|%-10s|%-15s|%-15s|%-10s|%-5s|%-15s",
                        "MÃ THUỐC",
                        "TÊN THUỐC",
                        "LOẠI THUỐC",
                        "ĐƠN GIÁ",
                        "SL",
                        "THÀNH TIỀN"
                ));
        for (ChiTietHoaDon ct : dsMuaThuoc) {
            ct.xuat();
        }

        System.out.println("\nTỒNG TIỀN: " + String.format("%,.0f", tinhTongTien()));
        System.out.println("============================");
    }

    @Override
    public String toString() {
        return String.format(
                "%d|%s|%d|%d",
                this.maHoaDon,
                this.ngayThanhToan,
                this.soLuongMua,
                this.khachHang.getMaKhachHang()
        );
    }
}