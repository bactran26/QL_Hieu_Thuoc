package ProjectQLHieuThuoc;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class DSKH
{
    private ArrayList<KhachHang> dskh;

    public DSKH(){
       dskh = new ArrayList<>();
    }

    public ArrayList<KhachHang> getDskh() {
        return dskh;
    }

    public void setDskh(ArrayList<KhachHang> dskh) {
        this.dskh = dskh;
    }

    public int KTmaTrung(int x)
    {
        int index=0;
        for(KhachHang kh : dskh){
            if (kh.getMaKhachHang() == x)
            {
                return index;
            }
            index++;
        }
        return -1;
    }

    public void nhaptay() {
        Scanner in = new Scanner(System.in);
        System.out.print("So luong khach hang can nhap la: ");
        int n = Integer.parseInt(in.nextLine());
        for (int i = 0; i < n; i++) 
        {
            KhachHang kh;
            do {
                System.out.println("Nhap thong tin khach hang thu " + (i + 1) + ":");
                kh = new KhachHang();
                kh.nhap();
                if (KTmaTrung(kh.getMaKhachHang()) != -1) 
                {
                    System.out.println("Ma khach hang da bi trung. Vui long nhap lai.");
                }
            } while (KTmaTrung(kh.getMaKhachHang()) != -1);
            dskh.add(kh);
        }
    }
    
    public void xuattatca()
    {
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
    public void themkh()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("So luong khach hang can them la: ");
        int n = Integer.parseInt(in.nextLine());
        for (int i = 0; i < n ; i++) 
        {
            KhachHang kh;
            do {
                System.out.println("Nhap thong tin khach hang can them thu " + (i + 1) + ":");
                kh = new KhachHang();
                kh.nhap();
                if (KTmaTrung(kh.getMaKhachHang()) != -1) 
                {
                    System.out.println("Ma khach hang da bi trung. Vui long nhap lai.");
                }
            } while (KTmaTrung(kh.getMaKhachHang()) != -1);
            dskh.add(kh);
        }
    }
    public void timKiem()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Nhap ma khach hang can tiem kiem: ");
        int seach = Integer.parseInt(in.nextLine());
        if(KTmaTrung(seach) != -1)
        {
            KhachHang kh = dskh.get(KTmaTrung(seach));
            kh.xuat();
        }
        else
        {
            System.out.println("----Khong tim thay khach hang----");
        }
        in.close();
    }
    public void suakh()
    {
        Scanner in = new Scanner(System.in);
        try 
        {
            System.out.print("Nhap ma khach hang can sua: ");
            int ma = Integer.parseInt(in.nextLine());
            int index = KTmaTrung(ma);
            if (index != -1) {
                KhachHang kh = dskh.get(index);
                kh.sua();
            } 
            else 
            {
                System.out.println("----Khong tim thay ma khach hang----");
            }
        } 
        catch (NumberFormatException e) 
        {
            System.out.println("----Ma khach hang phai la so nguyen----");
        }
    }
    public void xoa() {
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Nhap ma khach hang can xoa (hoac nhap -1 de thoat): ");
                int x = Integer.parseInt(in.nextLine());
                if (x == -1) {
                    System.out.println("Da huy thao tac xoa.");
                    return;
                }
                int index = KTmaTrung(x);
                if (index != -1) {
                    dskh.remove(index);
                    System.out.println("Khach hang da duoc xoa thanh cong.");
                    return;
                } else {
                    System.out.println("----Khong tim thay khach hang----");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap mot so nguyen hop le.");
            }
        }
    }

    public void thongKe() {
        int nam = 0, nu = 0;
        int duoi18 = 0, tu18Den35 = 0, tren35 = 0;
    
        for (KhachHang kh : dskh) {
            // Thống kê theo giới tính
            if (kh.getGioiTinh().equalsIgnoreCase("Nam")) {
                nam++;
            } else if (kh.getGioiTinh().equalsIgnoreCase("Nu")) {
                nu++;
            }
    
            // Thống kê theo độ tuổi
            int tuoi = kh.getTuoi();
            if (tuoi < 18) {
                duoi18++;
            } else if (tuoi >= 18 && tuoi <= 35) {
                tu18Den35++;
            } else {
                tren35++;
            }
        }
    
        // Xuất thống kê
        System.out.println("\n=== THONG KE KHACH HANG ===");
        System.out.println("Tong so khach hang: " + dskh.size());
        System.out.println("So khach hang Nam: " + nam);
        System.out.println("So khach hang Nu: " + nu);
        System.out.println("So khach hang duoi 18 tuoi: " + duoi18);
        System.out.println("So khach hang tu 18 den 35 tuoi: " + tu18Den35);
        System.out.println("So khach hang tren 35 tuoi: " + tren35);
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
            System.out.println("Ghi file thanh cong!");

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
            System.out.println("Doc file thanh cong!");

        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
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
