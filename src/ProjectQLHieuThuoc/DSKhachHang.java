package ProjectQLHieuThuoc;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
public class DSKhachHang extends SC
{
    private static KhachHang[] dsKhachHang = new KhachHang[0];
    private static int n =0;

    public DSKhachHang() {}

    public static void xemDSKH() {
        if (dsKhachHang.length == 0) {
            return;
        }
        System.out.println("\nDANH SÁCH KHÁCH HÀNG:");
        System.out.println(
                String.format(
                        "|%-15s|%-25s|%-5s|%-12s|%-20s|%-10s",
                        "MÃ KH",
                        "TÊN KH",
                        "TUỔI",
                        "SDT",
                        "ĐỊA CHỈ",
                        "GIỚI TÍNH"
                ));
        System.out.println("_".repeat(90));
        for(int i=0;i<dsKhachHang.length;i++) {
            dsKhachHang[i].xuat();
        }
    }

    public static KhachHang timKiemKhachHangTheoMa(int maKhachHang) {
        for(KhachHang khachHang : dsKhachHang) {
            if(khachHang.getMaKhachHang() == maKhachHang) {
git                return khachHang;
            }
        }
        return null;
    }

    public static void themKhachHang(KhachHang kh) {
        dsKhachHang = Arrays.copyOf(dsKhachHang, n + 1);
        dsKhachHang[n] = kh;
        n++;
    }

    public static boolean xoaKhachHang(int maKhachHang) {
        for(int i = 0; i < dsKhachHang.length; i++) {
            if(dsKhachHang[i].getMaKhachHang() == maKhachHang) {
                for(int j = i; j < dsKhachHang.length - 1; j++) {
                    dsKhachHang[j] = dsKhachHang[j + 1];
                }
                dsKhachHang = Arrays.copyOf(dsKhachHang, n - 1);
                n--;
                return true;
            }
        }
        return false;
    }

    public void themkh() {
        System.out.print("So luong khach hang can them la: ");
        int k = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < k; i++) {
            KhachHang kh = new KhachHang();
            kh.nhap();
            themKhachHang(kh);
        }
    }

    public void timkiemkh()
    {
        try {
            System.out.println("Nhap ma khach hang can tiem kiem: ");
            int maKH = Integer.parseInt(sc.nextLine());
            KhachHang kh = timKiemKhachHangTheoMa(maKH);
            if(kh != null) {
                kh.xuat();
            }
            else
            {
                System.out.println("----Khong tim thay khach hang----");
            }
        } catch (NumberFormatException e)
        {
            System.out.println("----Loi nhap ma khach hang----");
        }
    }
    public void suakh()
    {
        try {
            System.out.print("Nhap ma khach hang can sua: ");
            int maKH = Integer.parseInt(sc.nextLine());
            KhachHang kh = timKiemKhachHangTheoMa(maKH);
            if (kh != null) {
                kh.nhap();
            } else {
                System.out.println("----Khong tim thay khach hang----");
            }
        } catch (NumberFormatException e)
        {
            System.out.println("----Loi nhap ma khach hang----");
        }
    }
    public void xoa()
    {
        try {
            System.out.print("Nhap ma khach hang can xoa: ");
            int maKH = Integer.parseInt(sc.nextLine());
            KhachHang kh = timKiemKhachHangTheoMa(maKH);
            if (kh != null) {
                xoaKhachHang(maKH);
            } else {
                System.out.println("----Khong tim thay khach hang----");
            }
        } catch (NumberFormatException e)
        {
            System.out.println("----Loi nhap ma khach hang----");
        }
    }
    public void thongKe() {
        int nam = 0, nu = 0;
        int duoi18 = 0, tu18Den35 = 0, tren35 = 0;
    
        for (int i = 0; i < dsKhachHang.length; i++) {
            KhachHang kh = dsKhachHang[i];
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
        System.out.println("Tong so khach hang: " + dsKhachHang.length);
        System.out.println("So khach hang Nam: " + nam);
        System.out.println("So khach hang Nu: " + nu);
        System.out.println("So khach hang duoi 18 tuoi: " + duoi18);
        System.out.println("So khach hang tu 18 den 35 tuoi: " + tu18Den35);
        System.out.println("So khach hang tren 35 tuoi: " + tren35);
    }    
    public static void ghiFile(String tenFile) {
        try (FileWriter fw = new FileWriter(tenFile);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(dsKhachHang.length + "");
            bw.newLine();
            for (KhachHang kh : dsKhachHang) {
                bw.write(kh.getMaKhachHang() + ";"
                        + kh.getTenKhachHang() + ";"
                        + kh.getTuoi() + ";"
                        + kh.getSoDienThoai() + ";"
                        + kh.getDiaChi() + ";"
                        + kh.getGioiTinh());
                bw.newLine();
            }
            System.out.println("Ghi file khách hàng thành công!");
    
        } catch (IOException e) {
            System.out.println("Lỗi ghi file danh sách khách hàng: " + e.getMessage());
        }
    }
    public static void docFile(String tenFile) {
        try (FileReader fr = new FileReader(tenFile);
             BufferedReader br = new BufferedReader(fr)) {

            String line;
            // Đọc thông tin từng khách hàng
            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(";");
                    if (parts.length == 6) {
                        KhachHang kh = new KhachHang();
                        kh.setMaKhachHang(Integer.parseInt(parts[0]));
                        kh.setTenKhachHang(parts[1].trim());
                        kh.setTuoi(Integer.parseInt(parts[2].trim()));
                        kh.setSoDienThoai(parts[3].trim());
                        kh.setDiaChi(parts[4].trim());
                        kh.setGioiTinh(parts[5].trim());
        
                        themKhachHang(kh); // Thêm khách hàng vào mảng
                    }
                } catch (NumberFormatException e) 
                {
                    System.out.println("Lỗi đọc file danh sách khách hàng!");
                }
            }
            System.out.println("Dọc dữ liệu khách hàng thành công!");
    
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file khách hàng: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Lỗi đọc file khách hàng: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Lỗi định dạng dữ liệu khách hàng: " + e.getMessage());
        }
    }
    
}