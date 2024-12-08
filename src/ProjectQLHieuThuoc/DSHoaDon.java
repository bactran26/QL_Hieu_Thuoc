package ProjectQLHieuThuoc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DSHoaDon extends SC {
    private static HoaDon[] dsHoaDon = new HoaDon[0];
    private static int n = 0;

    public DSHoaDon() {}

    // Getters and Setters
    public static HoaDon[] getDsHoaDon() {
        return dsHoaDon;
    }

    //method

    public static HoaDon timKiemHoaDonTheoMa(int maHoaDon) {
        if (n == 0) {
            return null;
        }
        for (HoaDon hd : dsHoaDon) {
            if (hd.getMaHoaDon() == maHoaDon) {
                return hd;
            }
        }
        return null;
    }

    public static void themHoaDon(HoaDon hd) {
        dsHoaDon = Arrays.copyOf(dsHoaDon, n + 1);
        dsHoaDon[n] = hd;
        n++;
    }

    public static int taoMaHoaDonNgauNhien() {
        int ma = 1;
        while (timKiemHoaDonTheoMa(ma) != null) {
            ma++;
        }
        return ma;
    }

    public void them() {
        System.out.println("\n=== TẠO HÓA ĐƠN ===");
        HoaDon hoaDon = new HoaDon();

        //Nhao hoa don
        hoaDon.nhap();

        System.out.println("\n=== THÔNG TIN HÓA ĐƠN MỚI ===");
        hoaDon.xuat();

        // Thêm vào danh sách
        themHoaDon(hoaDon);
    }

    public void xem() {
        if (dsHoaDon == null || n == 0) {
            System.out.println("Danh sách hóa đơn trống!");
            return;
        }
        System.out.println("\n=== XEM DANH SACH HOA DON ===");
        System.out.println("1. Chỉ xem thông tin cơ bản.");
        System.out.println("2. Xem toàn bộ chi tiết hóa đơn.");
        System.out.println("0. Thoát");
        System.out.print("Nhap lua chon: ");
        int choice = sc.nextInt();
        sc.nextLine();
        while (choice < 1 || choice > 2) {
            if (choice == 0) {
                return;
            }
            System.out.print("Lựa chọn không hợp lệ!\nVui lòng chọn lại:");
        }
        switch (choice) {
            case 1:
                System.out.println(
                        String.format(
                                "|%-15s|%-20s|%-15s|%-15s|%-15s",
                                "MÃ HÓA ĐƠN",
                                "NGÀY THANH TOÁN",
                                "SỐ LOẠI THUỐC",
                                "MÃ KHÁCH HÀNG",
                                "TỔNG THANH TOÁN"
                        ));
                System.out.println("-".repeat(70));
                for (HoaDon hd : dsHoaDon) {
                    System.out.println(
                            String.format(
                                    "|%-15d|%-20s|%-15d|%-15d|%-15.2f",
                                    hd.getMaHoaDon(),
                                    hd.getNgayThanhToan(),
                                    hd.getSoLuongMua(),
                                    hd.getKhachHang().getMaKhachHang(),
                                    hd.tinhTongTien()
                            ));
                }
                break;
            case 2:
                for (HoaDon hd : dsHoaDon) {
                    hd.xuat();
                    System.out.println("---------------------------");
                }
        }
    }

    public void sua() {
        if ( n == 0) {
            System.out.println("Danh sách hóa đơn trống!");
            return;
        }
        System.out.println("\n=== SUA HOA DON ===");
        System.out.print("Nhap ma hoa don can sua: ");
        int maHD = sc.nextInt();

        for (HoaDon hd : dsHoaDon) {
            if (hd.getMaHoaDon() == maHD) {
                System.out.println("\nThong tin hoa don hien tai:");
                hd.xuat();
                hd.sua();
                System.out.println("Sua hoa don thanh cong!");
                return;
            }
        }
        System.out.println("Khong tim thay hoa don co ma " + maHD);
    }

    public void xoa() {
        if ( n == 0) {
            System.out.println("Danh sách hóa đơn trống!");
            return;
        }
        System.out.println("\n=== XOA HOA DON ===");
        System.out.print("Nhap ma hoa don can xoa: ");
        int maHD = sc.nextInt();
        for (int i = 0; i < n; i++) {
            if (dsHoaDon[i].getMaHoaDon() == maHD) {
                System.out.println("\nThong tin hoa don trung khop:");
                dsHoaDon[i].xuat();
                System.out.print("Bạn chắc muốn xóa hóa đơn? (y/n): ");
                String choice = sc.nextLine().trim().toLowerCase();
                if (!choice.equals("y") && !choice.equals("yes") && !choice.equals("1")) {
                    for (int j = i; j < n - 1; j++) {
                        dsHoaDon[j] = dsHoaDon[j + 1];
                    }
                    dsHoaDon = Arrays.copyOf(dsHoaDon, n - 1);
                    n--;
                    System.out.println("Xoa hoa don thanh cong!");
                    return;
                }
            }
        }
    }

    public void timKiem() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== TIM KIEM HOA DON ===");
        System.out.println("1. Tim theo ma hoa don");
        System.out.println("2. Tim theo ma khach hang");
        System.out.println("3. Tim theo ngay thanh toan");
        System.out.print("Chon cach tim kiem: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Nhap ma hoa don: ");
                int maHD = sc.nextInt();
                for (HoaDon hd : dsHoaDon) {
                    if (hd.getMaHoaDon() == maHD) {
                        System.out.println("\nTim thay hoa don:");
                        hd.xuat();
                        return;
                    }
                }
                System.out.println("Khong tim thay hoa don co ma " + maHD);
                break;

            case 2:
                System.out.print("Nhap ma khach hang: ");
                int maKH = sc.nextInt();
                boolean found = false;
                for (HoaDon hd : dsHoaDon) {
                    if (hd.getKhachHang().getMaKhachHang() == maKH) {
                        if (!found) {
                            System.out.println("\nCac hoa don cua khach hang " + maKH + ":");
                            found = true;
                        }
                        hd.xuat();
                        System.out.println("---------------------------");
                    }
                }
                if (!found) {
                    System.out.println("Khong tim thay hoa don cua khach hang " + maKH);
                }
                break;

            case 3:
                System.out.print("Nhap ngay thanh toan (dd/MM/yyyy): ");
                String ngay = sc.nextLine();
                found = false;
                for (HoaDon hd : dsHoaDon) {
                    if (hd.getNgayThanhToan().equals(ngay)) {
                        if (!found) {
                            System.out.println("\nCac hoa don trong ngay " + ngay + ":");
                            found = true;
                        }
                        hd.xuat();
                        System.out.println("---------------------------");
                    }
                }
                if (!found) {
                    System.out.println("Khong tim thay hoa don trong ngay " + ngay);
                }
                break;

            default:
                System.out.println("Lua chon khong hop le!");
                break;
        }
    }

    public static void docFile(String tenFile) {
        try (FileReader fr = new FileReader(tenFile);
             BufferedReader br = new BufferedReader(fr)) {
            ChiTietHoaDon.docFile("..\\QL_Hieu_Thuoc\\out\\production\\exercise\\ProjectQLHieuThuoc\\input_ChiTietHoaDon.txt");

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    HoaDon hd = new HoaDon();
                    // Đọc thông tin cơ bản  format maHD|ngayThanhToan|soLuongThuoc|maKhachHang
                    hd.setMaHoaDon(Integer.parseInt(parts[0]));
                    hd.setNgayThanhToan(parts[1]);
                    hd.setSoLuongMua(Integer.parseInt(parts[2]));

                    // Tìm và set khách hàng
                    int maKH = Integer.parseInt(parts[3]);
                    KhachHang khachHang = DSKhachHang.timKiemKhachHangTheoMa(maKH);
                    if (khachHang != null) {
                        hd.setKhachHang(khachHang);
                    } else {
                        System.out.println("Mã đơn hàng:" + hd.getMaHoaDon() + " có mã khách hàng không tồn tại!");
                    }
                    themHoaDon(hd);
                }
            }
            ChiTietHoaDon[] dsChiTietHoaDon = ChiTietHoaDon.getDsChiTietHoaDon();
            if (dsChiTietHoaDon.length == 0) {
                return;
            }
            for (ChiTietHoaDon chitiet : dsChiTietHoaDon) {
                if (chitiet != null) {
                    HoaDon hd = timKiemHoaDonTheoMa(chitiet.getMaHD());
                    if (hd != null) {
                        hd.themChiTietHoaDon(chitiet);
                    }
                }
            }
            System.out.println("Đọc dữ liễu hóa đơn thành công!");
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file dữ liễu hóa đơn: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Lỗi đọc file dữ liễu hóa đơn: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Lỗi định dạng số dữ liễu hóa đơn: " + e.getMessage());
        }
    }

    public static void ghiFile(String tenFile) {
        try (FileWriter fw = new FileWriter(tenFile);
             BufferedWriter bw = new BufferedWriter(fw)) {

            // Ghi số lượng hóa đơn
            //bw.write(n);
            //bw.newLine();

            // Ghi thông tin từng hóa đơn
            ChiTietHoaDon.ghiFile("..\\QL_Hieu_Thuoc\\out\\production\\exercise\\ProjectQLHieuThuoc\\input_ChiTietHoaDon.txt");
            for (HoaDon hd : dsHoaDon) {
                // Format: maHD|ngayThanhToan|soLuongMua|maKH
                bw.write(hd.toString());
                bw.newLine();
            }

            System.out.println("Ghi file danh sách hóa đơn thành công!");

        } catch (IOException e) {
            System.out.println("Lỗi ghi file: " + e.getMessage());
        }
    }

    // Phương thức kiểm tra file tồn tại
    private boolean kiemTraFileTonTai(String tenFile) {
        File f = new File(tenFile);
        return f.exists() && !f.isDirectory();
    }

    // Phương thức lưu dữ liệu trước khi thoát chương trình
    public void luuDuLieu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nBạn có muốn lưu dữ liệu vào file không? (Y/N)");
        String luaChon = sc.nextLine();

        if (luaChon.equalsIgnoreCase("Y")) {
            System.out.print("Nhập tên file: ");
            String tenFile = sc.nextLine();

            if (kiemTraFileTonTai(tenFile)) {
                System.out.println("File đã tồn tại. Bạn có muốn ghi đè? (Y/N)");
                String xacNhan = sc.nextLine();
                if (!xacNhan.equalsIgnoreCase("Y")) {
                    System.out.println("Hủy lưu dữ liệu!");
                    return;
                }
            }

            ghiFile(tenFile);
        }
    }

    // Phương thức tải dữ liệu khi khởi động chương trình
    public void taiDuLieu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nBạn có muốn tải dữ liệu từ file không? (Y/N)");
        String luaChon = sc.nextLine();

        if (luaChon.equalsIgnoreCase("Y")) {
            System.out.print("Nhập tên file: ");
            String tenFile = sc.nextLine();

            if (!kiemTraFileTonTai(tenFile)) {
                System.out.println("File không tồn tại!");
                return;
            }

            docFile(tenFile);
        }
    }
}