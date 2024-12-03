package ProjectQLHieuThuoc;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class DSKhachHang extends DanhSach{
    private static ArrayList<KhachHang> dskh;

    public DSKhachHang(String fileName) {
       dskh = new ArrayList<>();
       docFile(fileName);
    }

    public static ArrayList<KhachHang> getDskh() {
        return dskh;
    }


    public void xem(){
        if (dskh != null && !dskh.isEmpty()){
            System.out.println("\n=== THONG TIN KHACH HANG ===");
            for (KhachHang kh : dskh){
                kh.xuat();
            }
            System.out.println("\n");
        }else {
            System.out.println("Danh sach trong!");
        }
    }

    public void them(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== THONG TIN KHACH HANG ===");
        KhachHang khachHang = new KhachHang();
        int count;
        System.out.println("Nhap ma khach hang: ");
        do {
            count = 0;
            int maKh = sc.nextInt();
            if (maKh == 0) return;
            for (KhachHang kh : dskh) {
                if (kh.getMaKhachHang() == maKh) {
                    count++;
                    break;
                }
            }
            if (count == 1){
                System.out.println("Ma khach hang da bi trung");
                System.out.println("vui long nhao lai ma khach hoac thoat");
            }
            if (count == 0){
                khachHang.setMaKhachHang(maKh);
            }
        }while (count == 1);
        khachHang.nhap();
        dskh.add(khachHang);
    }

    public void sua(){
        //1. tim khach hang can sua
        Scanner sc = new Scanner(System.in);
        ArrayList<KhachHang> dskhTimThay = new ArrayList<>();
        System.out.println("\n=== SUA THONG TIN KHACH HANG ===");
        System.out.println("Nhap ten khach hang can sua: ");
        String tenKhachHangSua = sc.nextLine();

        // tim va hien thi cac ten khach hang trung khop
        for (KhachHang kh : dskh){
            if (kh.getTenKhachHang().equalsIgnoreCase(tenKhachHangSua)){
                System.out.println("\nThong tin khach hang hien tai");
                kh.xuat();
                dskhTimThay.add(kh);
            }
        }

        //Kiem tra ket qua tim kiem
        if (dskhTimThay.isEmpty()){
            System.out.println("Khong tim thay khach hang: " + tenKhachHangSua);
            return;
        }

        KhachHang khCanSua;
        if (dskhTimThay.size() == 1){
            khCanSua = dskhTimThay.get(0);
        }else {
            System.out.println("Co nhieu ten khach hang trung nhau. Vui long nhap ma khach hang");
            System.out.println("Nhap ma khach hang: ");
            int maKh = sc.nextInt();
            khCanSua = null;
            for (KhachHang kh : dskhTimThay){
                if (kh.getMaKhachHang() == maKh){
                    khCanSua = kh;
                    break;
                }
            }
            if (khCanSua == null){
                System.out.println("Khong tim thay khach hang can sua: " + khCanSua);
                return;
            }
        }

        //2. thuc hiem sua thong tin
        KhachHang khachHangMoi = new KhachHang();
        System.out.println("\n=== NHAP THONG TIN KHACH HANG MOI ===");
        System.out.println("Nhap ma khach hang moi (Nhap 0 de giu nguyen): ");
        while (true){
            int maMoi = sc.nextInt();
            sc.nextLine();
            if (maMoi == 0) {
                khachHangMoi.setMaKhachHang(khCanSua.getMaKhachHang());
                break;
            }
            boolean maTrung = false;
            for (KhachHang kh : dskh){
                if (kh.getMaKhachHang() == maMoi && kh != khCanSua){
                    maTrung = true;
                    System.out.println("Ma khach hang da ton tai");
                    System.out.println("Vui long nhap mac khach hang khac hoac nhap 0 de giu ma cu");
                    break;
                }
            }
            if (!maTrung){
                khachHangMoi.setMaKhachHang(maMoi);
                break;
            }
        }
        System.out.println("\nNhap thong tin moi cho khach hang");
        khachHangMoi.nhap();

        // cap nhat vao danh sach chinh
        for (int i = 0; i < dskh.size(); i++) {
            if (dskh.get(i).getMaKhachHang() == khCanSua.getMaKhachHang()){
                dskh.set(i, khachHangMoi);
                return;
            }
        }
    }

    public void xoa(){
        Scanner sc = new Scanner(System.in);
        // Kiem tra danh sach rong
        if (dskh == null || dskh.isEmpty()){
            System.out.println("Danh sach rong!");
            return;
        }

        // Tim hang can xoa
        System.out.println("\n=== XOA THONG TIN KHACH HANG ===");
        System.out.println("Nhap ten khach hang can xoa: ");
        String tenCanXoa = sc.nextLine();

        ArrayList<KhachHang> dsKhTimThay = new ArrayList<>();

        // tim cac khach hang trung ten
        for (KhachHang kh : dskh){
            if (kh.getTenKhachHang().equalsIgnoreCase(tenCanXoa)){
                System.out.println("\nThong tin cac khach hang hien tai");
                kh.xuat();
                dsKhTimThay.add(kh);
            }
        }

        // Kiem tra ket qua tim kiem
        if (dsKhTimThay.isEmpty()){
            System.out.println("Khong tim thay khach hang nao");
            return;
        }

        // xu ly xoa
        KhachHang khCanXoa;
        if (dsKhTimThay.size() == 1){
            khCanXoa = dsKhTimThay.get(0);
        }else {
            System.out.println("Co nhieu ten khach hang trung nhau. Vui long nhap ma khach hang");
            System.out.println("Nhap ma khac hang: ");
            int maKh = sc.nextInt();
            khCanXoa = null;
            for (KhachHang kh : dsKhTimThay){
                if (kh.getMaKhachHang() == maKh){
                    khCanXoa = kh;
                    break;
                }
            }
            if (khCanXoa == null){
                System.out.println("Khong tim thay hang can xoa: " + maKh);
                return;
            }
        }

        // xac nhan xoa
        System.out.println("Ban co muon xoa khach hang nay khong?(Y/N)");
        sc.nextLine();
        String xacNhan = sc.nextLine();
        if (xacNhan.equalsIgnoreCase("Y")){
            dskh.remove(khCanXoa);
            System.out.println("Da xoa khach hang thanh cong!");
        }
        else {
            System.out.println("Da huy xoa khach hang");
        }
    }

    public void timKiem(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== TIM KIEM KHACH HANG === ");
        System.out.println("1. Tim kiem theo ten");
        System.out.println("2. Tim kiem theo ma");
        System.out.println("Vui long nhap lua chon: ");
        int luaChon = sc.nextInt();
        sc.nextLine();
        switch (luaChon){
            case 1:
                System.out.println("Nhap ten khach hang can tim: ");
                String tenTimKiem = sc.nextLine();

                boolean timThayTen =  false;
                for (KhachHang kh : dskh){
                    if (kh.getTenKhachHang().toLowerCase().contains(tenTimKiem.toLowerCase())){
                        if (!timThayTen){
                            System.out.println("\nCac khach hang tim thay:");
                        }
                        kh.xuat();
                        timThayTen = true;
                    }
                }
                break;
            case 2:
                System.out.println("Nhap ma khach hang can tim: ");
                int maTimKiem = sc.nextInt();
                sc.nextLine();

                boolean maTimThay = false;
                for (KhachHang kh : dskh){
                    if (kh.getMaKhachHang() == maTimKiem){
                        System.out.println("\nThong tin khach hang tim thay");
                        kh.xuat();
                        maTimThay = true;
                        break;
                    }
                }
                if (!maTimThay){
                    System.out.println("khong tim thay ma khach hang" + maTimKiem);
                }
                break;
            default:
                System.out.println("Lua chon khong hop le!");
        }
    }

    public void ghiFile(String tenFile) {
        try (FileWriter fw = new FileWriter(tenFile);
             BufferedWriter bw = new BufferedWriter(fw)) {

            // Ghi số lượng khách hàng
            bw.write(dskh.size() + "");
            bw.newLine();

            // Ghi thông tin từng khách hàng
            for (KhachHang kh : dskh) {
                // Ghi theo định dạng: maKH|tenKH|tuoi|sdt|diaChi|gioiTinh
                bw.write(kh.getMaKhachHang() + ";"
                        + kh.getTenKhachHang() + ";"
                        + kh.getTuoi() + ";"
                        + kh.getSoDienThoai() + ";"
                        + kh.getDiaChi() + ";"
                        + kh.getGioiTinh());
                bw.newLine();
            }
            System.out.println("lưu danh sách khách hàng thành công!");

        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void docFile(String tenFile) {
        try (FileReader fr = new FileReader(tenFile);
             BufferedReader br = new BufferedReader(fr)) {

            // Xóa dữ liệu cũ
            dskh.clear();

            String line;
            // Đọc từng dòng trong file
            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(";");

                    if (parts.length == 6) {
                        KhachHang kh = new KhachHang();
                        kh.setMaKhachHang(Integer.parseInt(parts[0].trim())); // thêm trim() để loại bỏ khoảng trắng
                        kh.setTenKhachHang(parts[1].trim());
                        kh.setTuoi(Integer.parseInt(parts[2].trim()));
                        kh.setSoDienThoai(parts[3].trim());
                        kh.setDiaChi(parts[4].trim());
                        kh.setGioiTinh(parts[5].trim());

                        dskh.add(kh);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Loi doc dong du lieu: " + line);
                    System.out.println("Chi tiet loi: " + e.getMessage());
                    continue; // Bỏ qua dòng lỗi và đọc dòng tiếp theo
                }
            }
            System.out.println("Doc file khach hang thanh cong!");

        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file khach hang: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Loi doc file khach hang: " + e.getMessage());
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
        System.out.println("\nBan co muon luu du lieu vao file khong? (Y/N)");
        String luaChon = sc.nextLine();

        if (luaChon.equalsIgnoreCase("Y")) {
            System.out.print("Nhap ten file: ");
            String tenFile = sc.nextLine();

            if (kiemTraFileTonTai(tenFile)) {
                System.out.println("File da ton tai. Ban co muon ghi de? (Y/N)");
                String xacNhan = sc.nextLine();
                if (!xacNhan.equalsIgnoreCase("Y")) {
                    System.out.println("Huy luu du lieu!");
                    return;
                }
            }

            ghiFile(tenFile);
        }
    }

    // Phương thức tải dữ liệu khi khởi động chương trình
    public void taiDuLieu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nBan co muon tai du lieu tu file khong? (Y/N)");
        String luaChon = sc.nextLine();

        if (luaChon.equalsIgnoreCase("Y")) {
            System.out.print("Nhap ten file: ");
            String tenFile = sc.nextLine();

            if (!kiemTraFileTonTai(tenFile)) {
                System.out.println("File khong ton tai!");
                return;
            }

            docFile(tenFile);
        }
    }
}
