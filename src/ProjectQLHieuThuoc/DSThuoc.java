package ProjectQLHieuThuoc;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
//thoát chương trình khi không tìm thấy file
import java.io.FileNotFoundException;
import java.io.IOException;

public class DSThuoc {
    private ArrayList<Thuoc> dsThuoc;
    private static int maThuoctt;

    public ArrayList<Thuoc> getDsThuoc(){

        return dsThuoc;
    }

    public void setDsThuoc(ArrayList<Thuoc> dsThuoc){
        this.dsThuoc = dsThuoc;
    }

    public DSThuoc(){
        dsThuoc = new ArrayList<>();
    }

    public DSThuoc(ArrayList<Thuoc> dsThuoc){
        this.dsThuoc =dsThuoc;
    }

    public static int getMaThuoctt(){
        return maThuoctt;
    }

    public static void setMaThuoctt(int maThuoctt){
        DSThuoc.maThuoctt = maThuoctt;
    }
    public void xem(){
        if (dsThuoc == null || dsThuoc.isEmpty()) {
            System.out.println("\n=== DANH SACH THUOC TRONG ===");
            return;
        }
        System.out. println("\n=== DANH SACH THUOC ===");
        System.out.println("Tong so thuoc: "+ dsThuoc.size());
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("| %-4s | %-15s | %-12s | %-12s | %-12s | %-12s | %-15s |\n",
                "Ma", "Ten Thuoc", "Loai Thuoc", "So luong", " Gia nhap thuoc", "Gia thuoc", "Hang san xuat");
        System.out.println("----------------------------------------------------------------------");

        double tongGiaNhap = 0;
        double tongGiaBan = 0;

        for (Thuoc thuoc : dsThuoc) {
            String giaNhapFormatted = String.format("%.0f", thuoc.getGiaNhapThuoc());
            String giaBanFormatted = String.format("%.0f", thuoc.getGiaThuoc());
            System.out.printf("| %-4d | %-15s | %-12s | %-12d | %-12s | %-12s | %-15s |\n",
                    thuoc.getMaThuoc(),
                    truncateString(thuoc.getTenThuoc(), 15),
                    truncateString(thuoc.getLoaiThuoc(), 12),
                    thuoc.getSoLuong(),
                    giaNhapFormatted,
                    giaBanFormatted,
                    truncateString(thuoc.getHangSX().getTenHangSX(), 15));
            tongGiaNhap += (thuoc.getSoLuong() * thuoc.getGiaNhapThuoc());
            tongGiaBan += (thuoc.getSoLuong() * thuoc.getGiaThuoc());

        }
        System.out.printf("Tong so tien nhap thuoc: %,.0f VND\n", tongGiaNhap);
        System.out.printf("Tong so tien ban thuoc: %,.0f VND\n", tongGiaBan);
    }

    public String truncateString(String str, int maxlength){
            if (str == null || str.length() <= maxlength) {
                return str;
            }
            return str.substring(0, maxlength - 3) + "...";
        }

    public void them() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== THEM THUOC MOI ===");
        System.out.println("1. Them thuoc tay");
        System.out.println("2. Them thuoc dong y");
        System.out.print("Nhap lua chon: ");

        int luaChon = 0;
        try {
            luaChon = sc.nextInt();
            sc.nextLine(); // Xóa bộ nhớ đệm
        } catch (Exception e) {
            System.out.println("Lua chon khong hop le!");
            sc.nextLine(); // Xóa bộ nhớ đệm
            return;
        }

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

        // Khởi tạo hangSX trước khi nhập thông tin
        thuoc.setHangSX(new HangSX());

        System.out.println("Nhap ma thuoc (Nhap 0 de thoat): ");
        int count;
        do {
            count = 0;
            int maThuoc = sc.nextInt();
            sc.nextLine(); // Xóa bộ nhớ đệm

            if (maThuoc == 0) {
                System.out.println("Da huy them thuoc!");
                return;
            }

            for (Thuoc t : dsThuoc) {
                if (t.getMaThuoc() == maThuoc) {
                    count++;
                    break; // Thoát ngay khi tìm thấy mã trùng
                }
            }

            if (count == 1) {
                System.out.println("Ma thuoc nay da ton tai");
                System.out.println("Vui long nhap ma khac (Nhap 0 de thoat): ");
            } else {
                thuoc.setMaThuoc(maThuoc);
            }
        } while (count == 1);

        thuoc.nhap();

        if (luaChon == 1) {
            thuoc.setLoaiThuoc("Thuoc Tay");
            dsThuoc.add(thuoc);
            System.out.println("Them thuoc Tay thanh cong!");
        } else if (luaChon == 2) {
            thuoc.setLoaiThuoc("Thuoc dong y");
            dsThuoc.add(thuoc);
            System.out.println("Them thuoc Dong y thanh cong!");
        }
    }
    public void sua(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Thuoc> dsThuocTimThay = new ArrayList<>();

        // 1. tim thuoc can sua
        System.out.println("\n===SUA THONG TIN THUOC ===");
        System.out.println("Nhap ten thuoc can sua: ");
        String tenThuocSua = sc.nextLine();

        // Tim va hien thi cac ten thuoc trung khop
        for (Thuoc thuoc : dsThuoc){
            if (thuoc.getTenThuoc().equalsIgnoreCase(tenThuocSua)){
                System.out.println("\nThong tin thuoc hien tai");
                thuoc.xuat();
                dsThuocTimThay.add(thuoc);
            }
        }
        // kiem tra ket qua tim kiem
        if (dsThuocTimThay.isEmpty()) {
            System.out.println("Khong tim thay thuoc co ten " + tenThuocSua);
            return;
        }
        Thuoc thuocCanSua;
        if (dsThuocTimThay.size() == 1){
            thuocCanSua = dsThuocTimThay.get(0);
        }else {
            System.out.println("Co nhieu thuoc trung ten. Vui long chon ma thuoc can sua");
            System.out.println("Nhap ma thuoc: ");
            int maThuoc = sc.nextInt();
            thuocCanSua = null;
            for (Thuoc thuoc : dsThuocTimThay){
                if (thuoc.getMaThuoc() == maThuoc){
                    thuocCanSua = thuoc;
                    break;
                }
            }
            if (thuocCanSua == null){
                System.out.println("khong tim thay thuoc co ma: " + maThuoc);
                return;
            }
        }
        //2. Thuc hien sua thong tin
        sc.nextLine();

        //Chon loai thuoc moi
        System.out.println("\nChon loai thuoc moi");
        System.out.println("1. Thuoc Tay");
        System.out.println("2. Thuoc Dong y");

        int luaChon = sc.nextInt();
        Thuoc thuocMoi;
        switch (luaChon){
            case 1:
                thuocMoi = new ThuocTay();
                thuocMoi.setLoaiThuoc("Thuoc Tay");
                break;
            case 2:
                thuocMoi = new ThuocDongY();
                thuocMoi.setLoaiThuoc("Thuoc dong y");
                break;
            default:
                System.out.println("Lua chon khong hop le");
                return;
        }
        // Khởi tạo hangSX cho thuốc mới
        thuocMoi.setHangSX(new HangSX());
        // Nhap ma thuoc moi

        System.out.println("\nNhap ma thuoc moi: ");
        while(true){
            int maMoi = sc.nextInt();

            if (maMoi == 0){
                thuocMoi.setMaThuoc(thuocCanSua.getMaThuoc());
                break;
            }

            boolean maTrung = false;
            for (Thuoc t : dsThuoc){
                if (t.getMaThuoc() == maMoi && t != thuocCanSua){
                    maTrung = true;
                    System.out.println("Ma thuoc da ton tai!");
                    System.out.println("Vui long nhap ma khac hoac nhap 0 de giu nguyen ma cu");
                    break;
                }
            }
            if (!maTrung){
                thuocMoi.setMaThuoc(maMoi);
                break;
            }
        }
        System.out.println("\nNhap thong tin moi cho thuoc");
        sc.nextLine();
        thuocMoi.nhap();

        // cap nhat vao danh sach chinh
        for (int i = 0; i < dsThuoc.size(); i++) {
            if (dsThuoc.get(i).getMaThuoc() == thuocCanSua.getMaThuoc()){
                dsThuoc.set(i, thuocMoi);
                System.out.println("\nCap nhat thong tin thuoc thanh cong");
                return;
            }
        }
    }
    public void xoa() {
            Scanner sc = new Scanner(System.in);
            ArrayList<Thuoc> dsThuocTimThay = new ArrayList<>();

            System.out.println("\nXOA THONG TIN THUOC");
            System.out.println("Nhap ten thuoc muon xoa: ");
            String tenThuocXoa = sc.nextLine();

            // Tìm kiếm thuốc
            for (Thuoc thuoc : dsThuoc) {
                if (thuoc.getTenThuoc().equalsIgnoreCase(tenThuocXoa)) {
                    System.out.println("\nThong tin thuoc");
                    thuoc.xuat();
                    dsThuocTimThay.add(thuoc);
                }
            }

            // Kiểm tra kết quả tìm kiếm
            if (dsThuocTimThay.isEmpty()) {
                System.out.println("Khong tim thay ten thuoc: " + tenThuocXoa);
                return;
            }

            Thuoc thuocCanXoa;
            if (dsThuocTimThay.size() == 1) {
                thuocCanXoa = dsThuocTimThay.get(0);
                // Xác nhận xóa
                System.out.println("Ban co muon xoa thuoc nay khong? (Y/N): ");
                String xacNhan = sc.nextLine();
                if (!xacNhan.equalsIgnoreCase("Y")) {
                    System.out.println("Da huy xoa thuoc");
                    return;
                }
                // Thực hiện xóa nếu chỉ có 1 kết quả
                dsThuoc.remove(thuocCanXoa);
                System.out.println("\nDa xoa thanh cong");
                System.out.println("So luong thuoc con lai: " + dsThuoc.size());
            } else {
                // Xử lý khi có nhiều thuốc trùng tên
                System.out.println("Co nhieu thuoc trung ten. Vui long nhap ma thuoc de xoa");
                System.out.println("Nhap ma thuoc: ");
                int maThuoc = sc.nextInt();
                sc.nextLine(); // Đọc bỏ dòng new line

                // Tìm thuốc theo mã
                thuocCanXoa = null;
                for (Thuoc thuoc : dsThuocTimThay) {
                    if (thuoc.getMaThuoc() == maThuoc) {
                        thuocCanXoa = thuoc;
                        break;
                    }
                }

                if (thuocCanXoa == null) {
                    System.out.println("Khong tim thay ma thuoc: " + maThuoc);
                    return;
                }

                // Xác nhận xóa
                System.out.println("Ban co muon xoa thuoc nay khong? (Y/N): ");
                String xacNhan = sc.nextLine();
                if (!xacNhan.equalsIgnoreCase("Y")) {
                    System.out.println("Da huy xoa thuoc");
                    return;
                }

                // Thực hiện xóa
                dsThuoc.remove(thuocCanXoa);
                System.out.println("\nDa xoa thanh cong");
                System.out.println("So luong thuoc con lai: " + dsThuoc.size());
            }
            }
    public void timKiem() {
        Scanner sc = new Scanner(System.in);

        if (dsThuoc == null || dsThuoc.isEmpty()) {
            System.out.println("\n=== DANH SACH THUOC TRONG ===");
            return;
        }

        System.out.println("\n=== TIM KIEM THUOC ===");
        System.out.println("1. Tim kiem theo ten");
        System.out.println("2. Tim kiem theo ma");
        System.out.print("Nhap lua chon: ");

        int luaChon = 0;
        try {
            luaChon = sc.nextInt();
            sc.nextLine(); // Xóa bộ nhớ đệm
        } catch (Exception e) {
            System.out.println("Lua chon khong hop le!");
            return;
        }

        ArrayList<Thuoc> ketQua = new ArrayList<>();

        switch (luaChon) {
            case 1:
                System.out.println("Nhap ten thuoc can tim: ");
                String tenThuoc = sc.nextLine().trim();
                for (Thuoc thuoc : dsThuoc) {
                    if (thuoc.getTenThuoc().toLowerCase().contains(tenThuoc.toLowerCase())) {
                        ketQua.add(thuoc);
                    }
                }
                break;

            case 2:
                System.out.println("Nhap ma thuoc can tim: ");
                int maThuoc = sc.nextInt();
                for (Thuoc thuoc : dsThuoc) {
                    if (thuoc.getMaThuoc() == maThuoc) {
                        ketQua.add(thuoc);
                        break; // Vì mã thuốc là duy nhất
                    }
                }
                break;

            default:
                System.out.println("Lua chon khong hop le!");
                return;
        }

        // Hiển thị kết quả
        if (ketQua.isEmpty()) {
            System.out.println("Khong tim thay thuoc!");
            return;
        }

        System.out.println("\nDa tim thay " + ketQua.size() + " thuoc:");
        for (Thuoc thuoc : ketQua) {
            System.out.println("----------------------------------------");
            System.out.println("Ma thuoc: " + thuoc.getMaThuoc());
            System.out.println("Ten thuoc: " + thuoc.getTenThuoc());
            System.out.println("Loai thuoc: " + thuoc.getLoaiThuoc());
            System.out.println("So luong: " + thuoc.getSoLuong());
            System.out.println("Gia nhap: " + thuoc.getGiaNhapThuoc());
            System.out.println("Gia ban: " + thuoc.getGiaThuoc());
            System.out.println("Hang san xuat: " + thuoc.getHangSX().getTenHangSX());
        }
    }
    private String parseThuocToLine(Thuoc thuoc) {
        if (thuoc.getLoaiThuoc().equals("Thuoc Tay")) {
            ThuocTay tt = (ThuocTay) thuoc;
            return String.format("%d;%s;%s;%d;%.1f;%.1f;%d;%s;%s;%d",
                    tt.getMaThuoc(),
                    tt.getTenThuoc(),
                    tt.getLoaiThuoc(),
                    tt.getSoLuong(),
                    tt.getGiaNhapThuoc(),
                    tt.getGiaThuoc(),
                    tt.getHangSX().getMaHangSX(),
                    tt.getHangSX().getTenHangSX(),
                    tt.getHangSX().getDiaChiHangSX(),
                    tt.getHangSX().getSdtHangSX()
            );
        } else {
            ThuocDongY tdy = (ThuocDongY) thuoc;
            return String.format("%d;%s;%s;%d;%.1f;%.1f;%d;%s;%s;%d",
                    tdy.getMaThuoc(),
                    tdy.getTenThuoc(),
                    tdy.getLoaiThuoc(),
                    tdy.getSoLuong(),
                    tdy.getGiaNhapThuoc(),
                    tdy.getGiaThuoc(),
                    tdy.getHangSX().getMaHangSX(),
                    tdy.getHangSX().getTenHangSX(),
                    tdy.getHangSX().getDiaChiHangSX(),
                    tdy.getHangSX().getSdtHangSX()
            );
        }
    }

    public Thuoc parseLineToThuoc(String line) {
        try {
            String[] parts = line.split(";");
            if (parts.length == 10) {
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
                Thuoc thuoc;
                if (loaiThuoc.equals("Thuoc Tay")) {
                    thuoc = new ThuocTay();
                } else if (loaiThuoc.equals("Thuoc dong y")) {
                    thuoc = new ThuocDongY();
                } else {
                    return null;
                }

                thuoc.setMaThuoc(maThuoc);
                thuoc.setTenThuoc(tenThuoc);
                thuoc.setLoaiThuoc(loaiThuoc);
                thuoc.setSoLuong(soLuong);
                thuoc.setGiaNhapThuoc(giaNhap);
                thuoc.setGiaThuoc(giaBan);
                thuoc.setHangSX(hangSX);

                return thuoc;
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Loi doc dong du lieu: " + line);
            e.printStackTrace();
        }
        return null;
    }

    public void taiDanhSachTuFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Thuoc thuoc = parseLineToThuoc(line);
                if (thuoc != null) {
                    dsThuoc.add(thuoc);
                }
            }
            System.out.println("Da tai danh sach thuoc tu file: " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay tep tin: " + fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void xuatDanhSachRaFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Thuoc thuoc : dsThuoc) {
                writer.write(parseThuocToLine(thuoc));
                writer.newLine();
            }
            System.out.println("Da xuat danh sach ra tap tin: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Thuoc timKiemThuocTheoMa(int ma) {
        for (Thuoc thuoc : dsThuoc) {
            if (ma == thuoc.getMaThuoc()) {
                return thuoc;
            }
        }
        return null;
    }

}
