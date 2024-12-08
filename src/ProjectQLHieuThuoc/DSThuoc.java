package ProjectQLHieuThuoc;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DSThuoc {
    private static Thuoc[] dsThuoc ;
    private static int soLuongThuoc;
    private static int MAX_THUOC = 100; // Maximum number of medicines

    public static void xemDSThuoc() {
        if (soLuongThuoc == 0) {
            return;
        }
        System.out.println("\nDANH SÁCH THUỐC:");
        System.out.println(
                String.format(
                        "|%-15s|%-15s|%-15s|%-5s|%-15s|%-15s",
                        "MÃ THUỐC",
                        "TÊN THUỐC",
                        "LOẠI THUỐC",
                        "SL",
                        "ĐƠN GIÁ",
                        "HÃNG SX"
                ));
        System.out.println("_".repeat(90));
        for (int i=0;i<soLuongThuoc;i++) {
            dsThuoc[i].xem();
        }
    }

    public static Thuoc timKiemThuocTheoMa(int maThuoc) {
        for(Thuoc thuoc : dsThuoc) {
            if(thuoc.getMaThuoc() == maThuoc) {
                return thuoc;
            }
        }
        return null;
    }


    public Thuoc[] getDsThuoc() {
        return dsThuoc;
    }

    public void setDsThuoc(Thuoc[] dsThuoc) {
        this.dsThuoc = dsThuoc;
    }

    public DSThuoc() {
        dsThuoc = new Thuoc[MAX_THUOC];
        soLuongThuoc = 0;
    }

    public DSThuoc(Thuoc[] dsThuoc, int soLuongThuoc) {
        this.dsThuoc = dsThuoc;
        this.soLuongThuoc = soLuongThuoc;
    }

    public void xem() {
        if (soLuongThuoc == 0) {
            System.out.println("Danh sach thuoc trong!");
            return;
        }
        System.out.println("\n=== DANH SACH THUOC ===");
        System.out.println("------------------------------------------");
        System.out.println("Tong so luong thuoc: " + soLuongThuoc);
        System.out.println("------------------------------------------");
        System.out.printf("| %-4s |  %-15s |  %-12s |  %-4s |  %-12s |  %-12s |  %-12s |\n",
                "Ma thuoc", "Ten Thuoc", "Loai Thuoc", "So luong", "Gia nhap thuoc", "Gia thuoc", "Hang san xuat");
        System.out.println("---------------------------------------------------------------");

        for (int i = 0; i < soLuongThuoc; i++) {
            String giaNhapFormatted = String.format("%.0f", dsThuoc[i].getGiaNhapThuoc());
            String giaBanFormatted = String.format("%.0f", dsThuoc[i].getGiaThuoc());
            System.out.printf("| %-4s |  %-15s |  %-12s |  %-4s |  %-12s |  %-12s |  %-12s |\n",
                    dsThuoc[i].getMaThuoc(),
                    truncateString(dsThuoc[i].getTenThuoc(), 15),
                    truncateString(dsThuoc[i].getLoaiThuoc(), 12),
                    dsThuoc[i].getSoLuong(),
                    giaNhapFormatted,
                    giaBanFormatted,
                    truncateString(dsThuoc[i].getHangSX().getTenHangSX(), 12));
        }
    }

    public String truncateString(String str, int maxlength) {
        if (str == null || str.length() <= maxlength) {
            return str;
        }
        return str.substring(0, maxlength - 3) + "...";
    }

    public void them() {
        if (soLuongThuoc >= MAX_THUOC) {
            System.out.println("Danh sach thuoc da day!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== THEM THUOC MOI ===");
        System.out.println("1. Them thuoc tay");
        System.out.println("2. Them thuoc dong y");
        System.out.print("Nhap lua chon: ");

        int luaChon = sc.nextInt();
        sc.nextLine();

        Thuoc thuoc = null;
        switch (luaChon) {
            case 1:
                thuoc = new ThuocTay();
                break;
            case 2:
                thuoc = new ThuocDongY();
                break;
            default:
                System.out.println("Lua chon khong hop le!");
                return;
        }

        thuoc.setHangSX(new HangSX());

        System.out.println("Nhap ma thuoc (Nhap 0 de thoat): ");
        while(true) {
            int maThuoc = sc.nextInt();
            sc.nextLine();

            if (maThuoc == 0) {
                System.out.println("Da huy them thuoc!");
                return;
            }

            if (timKiemThuocTheoMa(maThuoc) != null) {
                System.out.println("Ma thuoc nay da ton tai");
                System.out.println("Vui long nhap ma khac (Nhap 0 de thoat): ");
            } else {
                thuoc.setMaThuoc(maThuoc);
                break;
            }
        }

        thuoc.nhap();

        if (luaChon == 1) {
            thuoc.setLoaiThuoc("Thuoc Tay");
            dsThuoc[soLuongThuoc++] = thuoc;
            xuatDanhSachRaFile("C:\\Users\\OS\\IdeaProjects\\exercise\\src\\ProjectQLHieuThuoc/input_DSThuoc.txt");
            System.out.println("Them thuoc Tay thanh cong!");
        } else if (luaChon == 2) {
            thuoc.setLoaiThuoc("Thuoc dong y");
            dsThuoc[soLuongThuoc++] = thuoc;
            xuatDanhSachRaFile("C:\\Users\\OS\\IdeaProjects\\exercise\\src\\ProjectQLHieuThuoc/input_DSThuoc.txt");
            System.out.println("Them thuoc Dong y thanh cong!");
        }
    }

    public void sua() {
        if (soLuongThuoc == 0) {
            System.out.println("Danh sach thuoc trong!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== SUA THONG TIN THUOC ===");
        System.out.println("Nhap ten thuoc can sua: ");
        String tenThuocSua = sc.nextLine();

        // Tìm và hiển thị tất cả thuốc có tên trùng khớp
        Thuoc thuocCanSua = null;
        int viTriSua = -1;
        int soThuocTrung = 0;

        // Tìm và hiển thị thuốc trùng tên
        for (int i = 0; i < soLuongThuoc; i++) {
            if (dsThuoc[i].getTenThuoc().equalsIgnoreCase(tenThuocSua)) {
                System.out.println("\n=== Thuoc " + (++soThuocTrung) + " ===");
                System.out.println("Vi tri: " + i);
                dsThuoc[i].xuat();
            }
        }

        // Xử lý dựa trên số lượng thuốc tìm thấy
        if (soThuocTrung == 0) {
            System.out.println("Khong tim thay thuoc co ten: " + tenThuocSua);
            return;
        } else if (soThuocTrung == 1) {
            // Nếu chỉ có 1 thuốc trùng tên, lấy thuốc đó
            for (int i = 0; i < soLuongThuoc; i++) {
                if (dsThuoc[i].getTenThuoc().equalsIgnoreCase(tenThuocSua)) {
                    thuocCanSua = dsThuoc[i];
                    viTriSua = i;
                    break;
                }
            }
        } else {
            // Nếu có nhiều thuốc trùng tên, yêu cầu người dùng chọn theo mã
            System.out.println("\nCo " + soThuocTrung + " thuoc trung ten.");
            System.out.println("Vui long nhap ma thuoc can sua: ");
            int maThuoc = sc.nextInt();
            sc.nextLine(); // Clear buffer

            for (int i = 0; i < soLuongThuoc; i++) {
                if (dsThuoc[i].getMaThuoc() == maThuoc) {
                    thuocCanSua = dsThuoc[i];
                    viTriSua = i;
                    break;
                }
            }

            if (thuocCanSua == null) {
                System.out.println("Khong tim thay thuoc co ma: " + maThuoc);
                return;
            }
        }

        // Bắt đầu quá trình sửa thông tin
        System.out.println("\n=== CHON LOAI THUOC MOI ===");
        System.out.println("1. Thuoc Tay");
        System.out.println("2. Thuoc Dong y");
        System.out.print("Nhap lua chon: ");

        int luaChon = sc.nextInt();
        sc.nextLine(); // Clear buffer

        Thuoc thuocMoi;
        switch (luaChon) {
            case 1:
                thuocMoi = new ThuocTay();
                thuocMoi.setLoaiThuoc("Thuoc Tay");
                break;
            case 2:
                thuocMoi = new ThuocDongY();
                thuocMoi.setLoaiThuoc("Thuoc dong y");
                break;
            default:
                System.out.println("Lua chon khong hop le!");
                return;
        }

        // Khởi tạo HangSX cho thuốc mới
        thuocMoi.setHangSX(new HangSX());

        // Nhập mã thuốc mới
        System.out.println("\nNhap ma thuoc moi (Nhap 0 de giu nguyen ma cu: " + thuocCanSua.getMaThuoc() + "): ");
        while (true) {
            int maMoi = sc.nextInt();
            sc.nextLine(); // Clear buffer

            if (maMoi == 0) {
                thuocMoi.setMaThuoc(thuocCanSua.getMaThuoc());
                break;
            }

            boolean maTrung = false;
            for (int i = 0; i < soLuongThuoc; i++) {
                if (i != viTriSua && dsThuoc[i].getMaThuoc() == maMoi) {
                    maTrung = true;
                    System.out.println("Ma thuoc da ton tai!");
                    System.out.println("Vui long nhap ma khac (Nhap 0 de giu nguyen ma cu): ");
                    break;
                }
            }

            if (!maTrung) {
                thuocMoi.setMaThuoc(maMoi);
                break;
            }
        }

        // Nhập thông tin mới
        System.out.println("\n=== NHAP THONG TIN MOI CHO THUOC ===");
        thuocMoi.nhap();

        // Cập nhật vào mảng chính
        dsThuoc[viTriSua] = thuocMoi;


        // Cập nhật file
        xuatDanhSachRaFile("C:\\Users\\OS\\IdeaProjects\\exercise\\src\\ProjectQLHieuThuoc/input_DSThuoc.txt");

        System.out.println("\nCap nhat thong tin thuoc thanh cong!");
    }

    public void xoa() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nXOA THONG TIN THUOC");
        System.out.println("Nhap ten thuoc muon xoa: ");
        String tenThuocXoa = sc.nextLine();

        // Array to store found medicines
        Thuoc[] dsThuocTimThay = new Thuoc[MAX_THUOC];
        int soLuongTimThay = 0;

        // Find matching medicines
        for (int i = 0; i < soLuongThuoc; i++) {
            if (dsThuoc[i].getTenThuoc().equalsIgnoreCase(tenThuocXoa)) {
                System.out.println("\nThong tin thuoc");
                dsThuoc[i].xuat();
                dsThuocTimThay[soLuongTimThay++] = dsThuoc[i];
            }
        }

        if (soLuongTimThay == 0) {
            System.out.println("Khong tim thay ten thuoc: " + tenThuocXoa);
            return;
        }

        int viTriXoa = -1;
        if (soLuongTimThay == 1) {
            // Find position in main array
            for (int i = 0; i < soLuongThuoc; i++) {
                if (dsThuoc[i].getMaThuoc() == dsThuocTimThay[0].getMaThuoc()) {
                    viTriXoa = i;
                    break;
                }
            }
        } else {
            System.out.println("Co nhieu thuoc trung ten. Vui long nhap ma thuoc de xoa");
            System.out.println("Nhap ma thuoc: ");
            int maThuoc = sc.nextInt();
            sc.nextLine();

            for (int i = 0; i < soLuongThuoc; i++) {
                if (dsThuoc[i].getMaThuoc() == maThuoc) {
                    viTriXoa = i;
                    break;
                }
            }

            if (viTriXoa == -1) {
                System.out.println("Khong tim thay ma thuoc: " + maThuoc);
                return;
            }
        }

        System.out.println("Ban co muon xoa thuoc nay khong? (Y/N): ");
        String xacNhan = sc.nextLine();
        if (!xacNhan.equalsIgnoreCase("Y")) {
            System.out.println("Da huy xoa thuoc");
            return;
        }

        // Shift elements to remove the medicine
        for (int i = viTriXoa; i < soLuongThuoc - 1; i++) {
            dsThuoc[i] = dsThuoc[i + 1];
        }
        soLuongThuoc--;

        System.out.println("\nDa xoa thanh cong");
        System.out.println("So luong thuoc con lai: " + soLuongThuoc);
    }

    public void timKiem() {
        Scanner sc = new Scanner(System.in);

        if (soLuongThuoc == 0) {
            System.out.println("\n=== DANH SACH THUOC TRONG ===");
            return;
        }

        System.out.println("\n=== TIM KIEM THUOC ===");
        System.out.println("1. Tim kiem theo ten");
        System.out.println("2. Tim kiem theo ma");
        System.out.print("Nhap lua chon: ");

        int luaChon = sc.nextInt();
        sc.nextLine();

        Thuoc[] ketQua = new Thuoc[MAX_THUOC];
        int soLuongKetQua = 0;

        switch (luaChon) {
            case 1:
                System.out.println("Nhap ten thuoc can tim: ");
                String tenThuoc = sc.nextLine().trim();
                for (int i = 0; i < soLuongThuoc; i++) {
                    if (dsThuoc[i].getTenThuoc().toLowerCase().contains(tenThuoc.toLowerCase())) {
                        ketQua[soLuongKetQua++] = dsThuoc[i];
                    }
                }
                break;

            case 2:
                System.out.println("Nhap ma thuoc can tim: ");
                int maThuoc = sc.nextInt();
                for (int i = 0; i < soLuongThuoc; i++) {
                    if (dsThuoc[i].getMaThuoc() == maThuoc) {
                        ketQua[soLuongKetQua++] = dsThuoc[i];
                        break; // Vì mã thuốc là duy nhất
                    }
                }
                break;

            default:
                System.out.println("Lua chon khong hop le!");
                return;
        }

        // Hiển thị kết quả
        if (soLuongKetQua == 0) {
            System.out.println("Khong tim thay thuoc!");
            return;
        }

        System.out.println("\nDa tim thay " + soLuongKetQua + " thuoc:");
        for (int i = 0; i < soLuongKetQua; i++) {
            System.out.println("----------------------------------------");
            System.out.println("Ma thuoc: " + ketQua[i].getMaThuoc());
            System.out.println("Ten thuoc: " + ketQua[i].getTenThuoc());
            System.out.println("Loai thuoc: " + ketQua[i].getLoaiThuoc());
            System.out.println("So luong: " + ketQua[i].getSoLuong());
            System.out.println("Gia nhap: " + ketQua[i].getGiaNhapThuoc());
            System.out.println("Gia ban: " + ketQua[i].getGiaThuoc());
            System.out.println("Hang san xuat: " + ketQua[i].getHangSX().getTenHangSX());
        }
    }

    public void thongKe() {
        Scanner sc = new Scanner(System.in);
        if (soLuongThuoc == 0) {
            System.out.println("\n=== DANH SACH THUOC TRONG ===");
            return;
        }

        System.out.println("\n=== THONG KE THUOC ===");

        // Thống kê cơ bản
        int tongSoThuoc = soLuongThuoc;
        int soThuocTay = 0;
        int soThuocDongY = 0;
        int soThuocSapHet = 0; // Số lượng < 10

        for (int i = 0; i < soLuongThuoc; i++) {
            // Phân loại thuốc
            if (dsThuoc[i].getLoaiThuoc().equals("Thuoc Tay")) {
                soThuocTay++;
            } else {
                soThuocDongY++;
            }

            // Kiểm tra số lượng
            if (dsThuoc[i].getSoLuong() < 10) {
                soThuocSapHet++;
            }
        }

        // Hiển thị thống kê
        System.out.println("\n=== THONG TIN TONG QUAT ===");
        System.out.println("Tong so loai thuoc: " + tongSoThuoc);
        System.out.println("So luong thuoc tay: " + soThuocTay);
        System.out.println("So luong thuoc dong y: " + soThuocDongY);
        System.out.println("So luong thuoc sap het (< 10): " + soThuocSapHet);

        // Thống kê thuốc sắp hết
        if (soThuocSapHet > 0) {
            System.out.println("\n=== DANH SACH THUOC SAP HET (<10) ===");
            System.out.printf("| %-4s | %-20s | %-12s | %-8s |\n",
                    "Ma", "Ten Thuoc", "Loai Thuoc", "So Luong");
            System.out.println("----------------------------------------------------");
            for (int i = 0; i < soLuongThuoc; i++) {
                if (dsThuoc[i].getSoLuong() < 10) {
                    System.out.printf("| %-4d | %-20s | %-12s | %-8d |\n",
                            dsThuoc[i].getMaThuoc(),
                            truncateString(dsThuoc[i].getTenThuoc(), 20),
                            dsThuoc[i].getLoaiThuoc(),
                            dsThuoc[i].getSoLuong());
                }
            }
        }
    }

    private String parseThuocToLine(Thuoc thuoc) {
        String baseInfo = String.format("%d;%s;%s;%d;%.1f;%.1f;%d;%s;%s;%d",
                thuoc.getMaThuoc(),
                thuoc.getTenThuoc(),
                thuoc.getLoaiThuoc(),
                thuoc.getSoLuong(),
                thuoc.getGiaNhapThuoc(),
                thuoc.getGiaThuoc(),
                thuoc.getHangSX().getMaHangSX(),
                thuoc.getHangSX().getTenHangSX(),
                thuoc.getHangSX().getDiaChiHangSX(),
                thuoc.getHangSX().getSdtHangSX()
        );

        if (thuoc instanceof ThuocTay) {
            ThuocTay tt = (ThuocTay) thuoc;
            return baseInfo + ";" + tt.getLieuLuong() + ";" + tt.getCachSuDung();
        } else {
            ThuocDongY tdy = (ThuocDongY) thuoc;
            return baseInfo + ";" + tdy.getThanhPhanThaoDuoc() + ";" + tdy.getCongDung();
        }
    }

    public Thuoc parseLineToThuoc(String line) {
        try {
            String[] parts = line.split(";");
            if (parts.length >= 12) {
                // Đọc thông tin cơ bản
                int maThuoc = Integer.parseInt(parts[0]);
                String tenThuoc = parts[1];
                String loaiThuoc = parts[2];
                int soLuong = Integer.parseInt(parts[3]);
                double giaNhap = Double.parseDouble(parts[4]);
                double giaBan = Double.parseDouble(parts[5]);

                // Đọc thông tin HangSX
                int maHangSX = Integer.parseInt(parts[6]);
                String tenHangSX = parts[7];
                String diaChiHangSX = parts[8];
                long sdtHangSX = Long.parseLong(parts[9]);

                // Tạo đối tượng HangSX
                HangSX hangSX = new HangSX(maHangSX, tenHangSX, diaChiHangSX, sdtHangSX);

                // Tạo đối tượng thuốc tương ứng
                if (loaiThuoc.equals("Thuoc Tay")) {
                    ThuocTay thuoc = new ThuocTay();
                    thuoc.setLieuLuong(parts[10]);
                    thuoc.setCachSuDung(parts[11]);
                    setBasicInfo(thuoc, maThuoc, tenThuoc, loaiThuoc, soLuong, giaNhap, giaBan, hangSX);
                    return thuoc;
                } else if (loaiThuoc.equals("Thuoc dong y")) {
                    ThuocDongY thuoc = new ThuocDongY();
                    thuoc.setThanhPhanThaoDuoc(parts[10]);
                    thuoc.setCongDung(parts[11]);
                    setBasicInfo(thuoc, maThuoc, tenThuoc, loaiThuoc, soLuong, giaNhap, giaBan, hangSX);
                    return thuoc;
                }
            }
        } catch (Exception e) {
            System.out.println("Loi doc dong du lieu: " + line);
            e.printStackTrace();
        }
        return null;
    }

    private void setBasicInfo(Thuoc thuoc, int maThuoc, String tenThuoc, String loaiThuoc,
                              int soLuong, double giaNhap, double giaBan, HangSX hangSX) {
        thuoc.setMaThuoc(maThuoc);
        thuoc.setTenThuoc(tenThuoc);
        thuoc.setLoaiThuoc(loaiThuoc);
        thuoc.setSoLuong(soLuong);
        thuoc.setGiaNhapThuoc(giaNhap);
        thuoc.setGiaThuoc(giaBan);
        thuoc.setHangSX(hangSX);
    }

    public void taiDanhSachTuFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            soLuongThuoc = 0; // Reset counter
            while ((line = reader.readLine()) != null && soLuongThuoc < MAX_THUOC) {
                Thuoc thuoc = parseLineToThuoc(line);
                if (thuoc != null) {
                    dsThuoc[soLuongThuoc++] = thuoc;
                }
            }
            System.out.println("Đọc dữ liệu danh sách thuốc thành công!");
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file danh sách thuốc!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void xuatDanhSachRaFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < soLuongThuoc; i++) {
                writer.write(parseThuocToLine(dsThuoc[i]));
                writer.newLine();
            }
            System.out.println("Da xuat danh sach thuoc ra tap tin: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }











    //tim kiem thuoc theo ma

}
