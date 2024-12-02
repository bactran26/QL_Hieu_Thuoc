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

public class DSHoaDon {
    private ArrayList<HoaDon> dsHoaDon;

    public DSHoaDon() {
        dsHoaDon = new ArrayList<>();
    }

    // Getters and Setters
    public ArrayList<HoaDon> getDsHoaDon() {
        return dsHoaDon;
    }

    public void setDsHoaDon(ArrayList<HoaDon> dsHoaDon) {
        this.dsHoaDon = dsHoaDon;
    }

    public void them() {
        Scanner sc = new Scanner(System.in);

        // Tải dữ liệu từ file
        DSKhachHang dsKH = new DSKhachHang();
        dsKH.docFile("C:\\Users\\OS\\IdeaProjects\\exercise\\src\\ProjectQLHieuThuoc/input_DSKhachHang.txt");

        DSThuoc dsThuoc = new DSThuoc();
        dsThuoc.taiDanhSachTuFile("C:\\Users\\OS\\IdeaProjects\\exercise\\src\\ProjectQLHieuThuoc/input_DSThuoc.txt");

        // Kiểm tra dữ liệu đã tải
        if (dsKH.getDskh().isEmpty()) {
            System.out.println("Danh sach khach hang trong!");
            return;
        }
        if (dsThuoc.getDsThuoc().isEmpty()) {
            System.out.println("Danh sach thuoc trong!");
            return;
        }

        System.out.println("\n=== THEM HOA DON MOI ===");
        HoaDon hoaDon = new HoaDon();

        // Nhập mã hóa đơn
        System.out.print("Nhap ma hoa don: ");
        int maHD;
        while (true) {
            maHD = sc.nextInt();
            boolean trung = false;
            for (HoaDon hd : dsHoaDon) {
                if (hd.getMaHoaDon() == maHD) {
                    System.out.print("Ma hoa don da ton tai! Nhap lai: ");
                    trung = true;
                    break;
                }
            }
            if (!trung) break;
        }
        hoaDon.setMaHoaDon(maHD);

        // Nhập ngày thanh toán
        sc.nextLine();
        System.out.print("Nhap ngay thanh toan (dd/MM/yyyy): ");
        hoaDon.setNgayThanhToan(sc.nextLine());

        // Hiển thị danh sách khách hàng
        System.out.println("\nDanh sach khach hang:");
        dsKH.xuattatca();

        // Chọn khách hàng
        System.out.print("Nhap ma khach hang: ");
        int maKH = sc.nextInt();
        KhachHang khachHang = null;
        for (KhachHang kh : dsKH.getDskh()) {
            if (kh.getMaKhachHang() == maKH) {
                khachHang = kh;
                break;
            }
        }
        if (khachHang == null) {
            System.out.println("Khong tim thay khach hang!");
            return;
        }
        hoaDon.setKhachHang(khachHang);

        // Hiển thị danh sách thuốc
        System.out.println("\nDanh sach thuoc:");
        dsThuoc.xem();

        // Chọn thuốc
        System.out.print("Nhap ma thuoc: ");
        int maThuoc = sc.nextInt();
        Thuoc thuoc = dsThuoc.timKiemThuocTheoMa(maThuoc);
        if (thuoc == null) {
            System.out.println("Khong tim thay thuoc!");
            return;
        }
        hoaDon.setThuoc(thuoc);

        // Nhập số lượng mua
        System.out.print("Nhap so luong mua: ");
        int soLuong = sc.nextInt();
        if (soLuong > thuoc.getSoLuong()) {
            System.out.println("So luong ton khong du!");
            return;
        }
        hoaDon.setSoLuongMua(soLuong);

        // Thêm vào danh sách
        dsHoaDon.add(hoaDon);
        System.out.println("Them hoa don thanh cong!");
    }

    public void xem() {
        if (dsHoaDon.isEmpty()) {
            System.out.println("\nDanh sach hoa don trong!");
            return;
        }

        System.out.println("\n=== DANH SACH HOA DON ===");
        for (HoaDon hd : dsHoaDon) {
            hd.xuat();
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

        for (int i = 0; i < dsHoaDon.size(); i++) {
            if (dsHoaDon.get(i).getMaHoaDon() == maHD) {
                System.out.println("\nThong tin hoa don can xoa:");
                dsHoaDon.get(i).xuat();

                System.out.print("\nBan co chac muon xoa? (Y/N): ");
                sc.nextLine();
                String confirm = sc.nextLine();
                if (confirm.equalsIgnoreCase("Y")) {
                    dsHoaDon.remove(i);
                    System.out.println("Xoa hoa don thanh cong!");
                } else {
                    System.out.println("Da huy xoa hoa don!");
                }
                return;
            }
        }
        System.out.println("Khong tim thay hoa don co ma " + maHD);
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

    public void docFile(String tenFile) {
        try (FileReader fr = new FileReader(tenFile);
             BufferedReader br = new BufferedReader(fr)) {

            // Xóa dữ liệu cũ
            dsHoaDon.clear();

            // Đọc số lượng hóa đơn
            int soLuong = Integer.parseInt(br.readLine());

            // Tải dữ liệu từ file khách hàng và thuốc
            DSKhachHang dsKH = new DSKhachHang();
            dsKH.docFile("C:\\Users\\OS\\IdeaProjects\\exercise\\src\\ProjectQLHieuThuoc/input_DSKhachHang.txt");

            DSThuoc dsThuoc = new DSThuoc();
            dsThuoc.taiDanhSachTuFile("C:\\Users\\OS\\IdeaProjects\\exercise\\src\\ProjectQLHieuThuoc/input_DSThuoc.txt");

            // Đọc thông tin từng hóa đơn
            for (int i = 0; i < soLuong; i++) {
                String line = br.readLine();
                String[] parts = line.split("\\|");

                if (parts.length == 6) {
                    HoaDon hd = new HoaDon();

                    // Đọc thông tin cơ bản
                    hd.setMaHoaDon(Integer.parseInt(parts[0]));
                    hd.setNgayThanhToan(parts[1]);

                    // Tìm và set thuốc
                    int maThuoc = Integer.parseInt(parts[2]);
                    Thuoc thuoc = dsThuoc.timKiemThuocTheoMa(maThuoc);
                    if (thuoc != null) {
                        hd.setThuoc(thuoc);
                    } else {
                        System.out.println("Không tìm thấy thuốc có mã " + maThuoc + " cho hóa đơn " + parts[0]);
                        continue;
                    }

                    // Set số lượng mua
                    hd.setSoLuongMua(Integer.parseInt(parts[3]));

                    // Tìm và set khách hàng
                    int maKH = Integer.parseInt(parts[4]);
                    KhachHang kh = null;
                    for (KhachHang k : dsKH.getDskh()) {
                        if (k.getMaKhachHang() == maKH) {
                            kh = k;
                            break;
                        }
                    }
                    if (kh != null) {
                        hd.setKhachHang(kh);
                    } else {
                        System.out.println("Không tìm thấy khách hàng có mã " + maKH + " cho hóa đơn " + parts[0]);
                        continue;
                    }

                    dsHoaDon.add(hd);
                }
            }
            System.out.println("Đọc file thành công!");

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
                // Format: maHD|ngayThanhToan|maThuoc|soLuongMua|maKH|thanhTien
                bw.write(String.format("%d|%s|%d|%d|%d|%.0f",
                        hd.getMaHoaDon(),
                        hd.getNgayThanhToan(),
                        hd.getThuoc().getMaThuoc(),
                        hd.getSoLuongMua(),
                        hd.getKhachHang().getMaKhachHang(),
                        hd.getThanhTien()));
                bw.newLine();
            }
            System.out.println("Ghi file thành công!");

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