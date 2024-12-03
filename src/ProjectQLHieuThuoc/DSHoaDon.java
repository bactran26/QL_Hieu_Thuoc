package ProjectQLHieuThuoc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DSHoaDon extends DanhSach{
    private static ArrayList<HoaDon> dsHoaDon;

    public DSHoaDon(String fileName) {
        dsHoaDon = new ArrayList<>();
        docFile(fileName);
    }

    // Getters and Setters
    public static ArrayList<HoaDon> getDsHoaDon() {
        return dsHoaDon;
    }

    public void them() {
        // Tải dữ liệu từ file
        ArrayList<KhachHang> dsKH = DSKhachHang.getDskh();

        DSThuoc dsThuoc = new DSThuoc();
        dsThuoc.taiDanhSachTuFile("..\\QL_Hieu_Thuoc\\out\\production\\exercise\\ProjectQLHieuThuoc\\input_DSThuoc.txt");

        // Kiểm tra dữ liệu đã tải
        if (dsKH.isEmpty()) {
            System.out.println("Danh sach khach hang trong!");
            return;
        }
        if (dsThuoc.getDsThuoc().isEmpty()) {
            System.out.println("Danh sach thuoc trong!");
            return;
        }

        //Nhap hoa don
        System.out.println("\n=== THEM HOA DON MOI ===");
        HoaDon hoaDon = new HoaDon();
        hoaDon.nhap();

        // Thêm vào danh sách
        dsHoaDon.add(hoaDon);
        System.out.println("Them hoa don thanh cong!");
    }

    public void xem() {
        if (dsHoaDon.isEmpty()) {
            System.out.println("\nDanh sách hóa đơn trống!");
            return;
        }

        System.out.println("\n=== DANH SÁCH HÓA ĐƠN ===");
        System.out.println(
                String.format(
                        "%-5s|%-10s|%-15s|%-15s|%-15s",
                        "STT",
                        "Mã Hóa Đơn",
                        "Ngày Thanh Toán",
                        "Số Lượng Thuốc",
                        "Mã Khách Hàng"
                )
        );
        int i =1;
        for (HoaDon hd : dsHoaDon) {
            System.out.print(String.format("%-5s|",i++ ));
            System.out.println(hd.print());
            System.out.println("---------------------------");
        }
    }

    public void sua() {
        Scanner sc = new Scanner(System.in);
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
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== XOA HOA DON ===");
        System.out.print("Nhap ma hoa don can xoa: ");
        int maHD = sc.nextInt();
        for (HoaDon hd : dsHoaDon) {
            if (hd.getMaHoaDon() == maHD) {
                System.out.println("\nThong tin hoa don hien tai:");
                hd.xuat();
                System.out.print("\nBan co chac muon xoa? (Y/N): ");
                sc.nextLine();
                String confirm = sc.nextLine();
                if (confirm.equalsIgnoreCase("Y")) {
                    for( ChiTietHoaDon ct : hd.getChiTietHoaDons()) {
                        ct.xoa();
                    }
                    dsHoaDon.remove(hd);
                    System.out.println("Xoa hoa don thanh cong!");
                }
                else {
                    System.out.println("Da huy xoa hoa don!");
                }
                return;
            }
        }
        System.out.println("Khong tim thay hoa don co ma " + maHD);
    }

    public void timKiem() {
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

    public void docFile(String tenFile) {
        try (FileReader fr = new FileReader(tenFile);
             BufferedReader br = new BufferedReader(fr)) {

            // Xóa dữ liệu cũ
            dsHoaDon.clear();

            // Đọc số lượng hóa đơn
            int soLuong = Integer.parseInt(br.readLine());

            // Tải dữ liệu từ file khách hàng và thuốc
            ArrayList<KhachHang> dsKhachHang = DSKhachHang.getDskh();

            // Đọc thông tin từng hóa đơn
            for (int i = 0; i < soLuong; i++) {
                String line = br.readLine();
                String[] parts = line.split("\\|");

                if (parts.length == 4) {
                    HoaDon hd = new HoaDon();
                    // Đọc thông tin cơ bản  format maHD|ngayThanhToan|soLuongThuoc|maKhachHang
                    hd.setMaHoaDon(Integer.parseInt(parts[0]));
                    hd.setNgayThanhToan(parts[1]);
                    hd.setSoLuongThuoc(Integer.parseInt(parts[2]));

                    // Tìm và set khách hàng
                    int maKH = Integer.parseInt(parts[3]);
                    for (KhachHang kh : dsKhachHang) {
                        if (kh.getMaKhachHang() == maKH) {
                            hd.setKhachHang(kh);
                            break;
                        }
                    }
                    if (hd.getKhachHang() == null) {
                        System.out.println("Khong tim thay khach hang co ma " + maKH);
                    }

                    // nạp chi tiết hóa đơn
                    hd.napChiTietHoaDons();
                    dsHoaDon.add(hd);
                }
            }
            System.out.println("Đọc dữ liễu hóa đơn thành công!");

        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Lỗi định dạng số: " + e.getMessage());
        }
    }

    public void ghiFile(String tenFile) {
        try (FileWriter fw = new FileWriter(tenFile);
             BufferedWriter bw = new BufferedWriter(fw)) {

            // Ghi số lượng hóa đơn
            bw.write(dsHoaDon.size() + "");
            bw.newLine();

            // Ghi thông tin từng hóa đơn
            for (HoaDon hd : dsHoaDon) {
                // Format: maHD|ngayThanhToan|soLuongMua|maKH
                bw.write(hd.toString());
                bw.newLine();
            }
            ChiTietHoaDon.ghiFile();
            System.out.println("Ghi file danh sách thành công!");

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