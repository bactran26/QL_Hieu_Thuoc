package ProjectQLHieuThuoc;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class DSHangSX {
    private ArrayList<HangSX> dshsx;
    private static int mahsx;

    public ArrayList<HangSX> getDshsx(){
        return dshsx;
    }

    public void setDshsx(ArrayList<HangSX> dshsx){
        this.dshsx = dshsx;
    }

    public DSHangSX(){
        dshsx = new ArrayList<>();
    }

    public int getMahsx(){
        return mahsx;
    }

    public static void setMahsx(int mahsx) {
        DSHangSX.mahsx = mahsx;
    }
    public void xem(){
        if (dshsx != null && !dshsx.isEmpty()){
            System.out.println("\nTHONG TIN HANG SAN XUAT");
            for (HangSX hsx : dshsx){
                hsx.xuat();
                System.out.println("------------------------------------------");
            }
            System.out.println("\n");
        }
        else {
            System.out.println("DANH SACH TRONG!\n");
        }
    }

    public void them(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nNHAP THONG TIN HANG SAN XUAT");
        HangSX hsx = null;
        hsx = new HangSX();
        int count;
        if (hsx != null){
            System.out.println("Nhap ma hang san xuat");
            do {
                count = 0;
                int mahsx = sc.nextInt();
                if (mahsx == 0) return;
                for (HangSX hang : dshsx){
                    if (hang.getMaHangSX() == mahsx){
                        count++;
                    }
                    if (count == 1){
                        System.out.println("Ma hang san xuat bi trung");
                        System.out.println("Vui long nhap lai ma khac hoac thoat");
                        break;
                    }
                }
                if (count == 0){
                    hsx.setMaHangSX(mahsx);
                }
            }while (count == 1);
            hsx.nhap();
            dshsx.add(hsx);
        }
    }

    public void sua(){
        Scanner sc = new Scanner(System.in);
        ArrayList<HangSX> dsHangTimThay = new ArrayList<>();

        // 1. Tìm hãng sản xuất cần sửa
        System.out.println("\n=== SUA THONG TIN HANG SAN XUAT ===");
        System.out.println("Nhap ten hang san xuat can sua: ");
        String tenHangSua = sc.nextLine();

        // Tìm và hiển thị các tên hãng trùng khớp
        for (HangSX hang : dshsx){
            if (hang.getTenHangSX().equalsIgnoreCase(tenHangSua)){
                System.out.println("\nThong tin hang san xuat hien tai");
                hang.xuat();
                dsHangTimThay.add(hang);
            }
        }
        // Kiểm tra kết quả tìm kiếm

        if (dsHangTimThay.isEmpty()){
            System.out.println("Khong tim thay hang nao");
            return;
        }

        HangSX hangCanSua;
        if (dsHangTimThay.size() == 1){
            hangCanSua = dsHangTimThay.get(0);
        }else{
            System.out.println("Co nhieu hang san xuat trung ten. Vui long nhap ma hang");
            System.out.println("Nhap ma hang san xuat: ");
            int maHang = sc.nextInt();
            hangCanSua = null;
            for (HangSX hang : dsHangTimThay){
                if (hang.getMaHangSX() == maHang){
                    hangCanSua = hang;
                    break;
                }
            }
            if (hangCanSua == null){
                System.out.println("Khong tim thay hang can sua: "+ hangCanSua);
                return;
            }
        }

        // 2. Thực hiện sửa thông tin
        HangSX hangMoi = new HangSX();
        System.out.println("\n=== NHAP THONG TIN HANG MOI ===");
        System.out.println("Nhap ma hang san xuat moi(nhap 0 de giu nguyen): ");
        while (true){
            int maMoi = sc.nextInt();
            sc.nextLine();
            if (maMoi == 0){
                hangMoi.setMaHangSX(hangCanSua.getMaHangSX());
                break;
            }
            boolean maTrung = false;
            for (HangSX h : dshsx){
                if (h.getMaHangSX() == maMoi && h != hangCanSua){
                    maTrung = true;
                    System.out.println("Ma hang san xuat da ton tai");
                    System.out.println("Vui long nhap lai ma hang hoac nhap 0 de giu nguyen ma cu");
                    break;
                }
            }
            if (!maTrung){
                hangMoi.setMaHangSX(maMoi);
                break;
            }
        }
        System.out.println("Nhap ten hang san xuat moi(enter de giu nguyen): ");
        String tenHangMoi = sc.nextLine();
        if (!tenHangMoi.isEmpty()){
            hangMoi.setTenHangSX(tenHangMoi);
        }else {
            hangMoi.setTenHangSX(hangCanSua.getTenHangSX());
        }

        System.out.println("Nhap dia chi moi(enter de giu nguyen): ");
        String diaChiMoi = sc.nextLine();
        if (!diaChiMoi.isEmpty()){
            hangMoi.setDiaChiHangSX(diaChiMoi);
        }else {
            hangMoi.setDiaChiHangSX(hangCanSua.getDiaChiHangSX());
        }

        System.out.println("Nhap so dien thoai moi(enter de giu nguyen): ");
        long sdtMoi = sc.nextLong();
        sc.nextLine();
        if (sdtMoi != 0){
            hangMoi.setSdtHangSX(sdtMoi);
        }else {
            hangMoi.setSdtHangSX(hangCanSua.getSdtHangSX());
        }
        // Nhập mã hãng sản xuất mới
        // 3. Cập nhật vào danh sách chính
        for (int i = 0; i < dshsx.size(); i++){
            if (dshsx.get(i).getMaHangSX() == hangCanSua.getMaHangSX()){
                dshsx.set(i, hangMoi);
                System.out.println("Da cap nhat hang san xuat thanh cong");
                return;
            }
        }
    }
    public void xoa(){
        Scanner sc = new Scanner(System.in);
        // Kiểm tra danh sách rỗng
        if (dshsx == null || dshsx.isEmpty()){
            System.out.println("Danh sach rong!");
            return;
        }
        // Tìm hãng cần xóa
        System.out.println("\n=== XOA HANG SAN XUAT ===");
        System.out.println("Nhap ten hang san xuat can xoa: ");
        String tenHangXoa = sc.nextLine();

        ArrayList<HangSX> dsHangTimThay = new ArrayList<>();

        // Tìm các hãng trùng tên
        for (HangSX hang : dshsx){
            if (hang != null && hang.getTenHangSX() != null && hang.getTenHangSX().equalsIgnoreCase(tenHangXoa)){
                System.out.println("\nThong tin hang san xuat: ");
                hang.xuat();
                dsHangTimThay.add(hang);
            }
        }

        // Kiem tra ket qua tim kiem
        if (dsHangTimThay.isEmpty()){
            System.out.println("Khong tim thay ten can xoa");
            return;
        }

        // Xử lý xóa
        HangSX hangCanXoa;
        if (dsHangTimThay.size() == 1){
            hangCanXoa = dsHangTimThay.get(0);
        }else {
            System.out.println("Co nhieu ten trung nhau. vui long nhap ma hang");
            System.out.println("Nhap ma hang can xoa: ");
            int maHang = sc.nextInt();
            hangCanXoa = null;
            for (HangSX hang : dsHangTimThay){
                if (hang.getMaHangSX() == maHang){
                    hangCanXoa = hang;
                    break;
                }
            }
            if (hangCanXoa == null){
                System.out.println("Khong tim thay hang can xoa" + maHang);
                return;
            }

        }

        // xac nhan xoa
        System.out.println("Ban co muon xoa hang san xuat nay?(Y/N)");
        sc.nextLine();
        String xacNhan = sc.nextLine();
        if (xacNhan.equalsIgnoreCase("Y")){
            dshsx.remove(hangCanXoa);
            System.out.println("Da xoa hang thanh cong!");
        }else {
            System.out.println("Da huy xoa hang san xuat");
        }
    }

    public void timKiem(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== TIM KIEM HANG SAN XUAT ===");
        System.out.println("1. Tim kiem theo ma");
        System.out.println("2. Tim kiem theo ten");
        System.out.println("Vui long nhap lua chon: ");
        int luaChon = sc.nextInt();
        sc.nextLine();
        switch (luaChon){
            case 1:
                System.out.println("Nhap ma hang san xuat can tim: ");
                int maTimKiem = sc.nextInt();

                boolean timThayMa = false;
                for (HangSX hang : dshsx){
                    if (hang.getMaHangSX() == maTimKiem){
                        System.out.println("\nThong tin hang san xuat");
                        hang.xuat();
                        timThayMa = true;
                        break;
                    }
                }

                if (!timThayMa){
                    System.out.println("Khong tim thay ma hang san xuat: " + maTimKiem);
                }
                break;
            case 2:
                System.out.println("Nhap ten hang san xuat can tim: ");
                String tenTimKiem = sc.nextLine();

                boolean tenTimThay = false;
                for (HangSX hang : dshsx){
                    if (hang.getTenHangSX().toLowerCase().contains(tenTimKiem.toLowerCase())){
                        if (!tenTimThay){
                            System.out.println("\nCac hang san xuat tim thay");
                        }
                        hang.xuat();
                        System.out.println("-------------------------------------------");
                        tenTimThay = true;
                    }
                }
                break;
            default:
                System.out.println("Lua chon khong hop le");
        }
    }
    public void ghiFile(String tenFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenFile))) {
            for (HangSX hsx : dshsx) {
                String line = String.format("%d;%s;%s;%d",
                        hsx.getMaHangSX(),
                        hsx.getTenHangSX(),
                        hsx.getDiaChiHangSX(),
                        hsx.getSdtHangSX());
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Ghi file thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void docFile(String tenFile) {
        dshsx.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 4) {
                    HangSX hsx = new HangSX(
                            Integer.parseInt(data[0]),     // mã hãng
                            data[1],                       // tên hãng
                            data[2],                       // địa chỉ
                            Long.parseLong(data[3])        // số điện thoại
                    );
                    dshsx.add(hsx);
                }
            }
            System.out.println("Doc file thanh cong!");
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Loi dinh dang so: " + e.getMessage());
        }
    }
}
