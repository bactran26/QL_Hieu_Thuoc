package ProjectQLHieuThuoc;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
public class DSKhachHang
{
    private KhachHang a[] = new KhachHang[0];
    private int sl = a.length;
    Scanner in = new Scanner(System.in);
    public int CheckMa(int x)
    {
        for(int i = 0; i < a.length; i++)
        {
            if(a[i].getMaKhachHang() == x)
            return i;
        }
        return -1;
    }
    public void themkh()
    {
        System.out.print("So luong khach hang can them la: ");
        int k = Integer.parseInt(in.nextLine());
        a=Arrays.copyOf(a,a.length+k);
        int tmp=a.length;
        for(int i = tmp; i < tmp+k; i++)
        {
            do
            {
                a[i] = new KhachHang();
                a[i].nhap();
                if (CheckMa(a[i].getMaKhachHang()) != -1) 
                {
                    System.out.println("----Ma khach hang bi trung. Vui long nhap lai----");  
                    System.out.print("\n"); 
                }
            }
            while (CheckMa(a[i].getMaKhachHang()) != -1);    
        }
    }
    public void xuattatca()
    {
        System.out.println("\n");
        System.out.println("\t====Danh sach khach hang====");
        for(int i=0;i<a.length;i++)
        {
            a[i].xuat();
        }
        System.out.println("\n");
    }
    public void timkiemkh()
    {
        try {
            System.out.println("Nhap ma khach hang can tiem kiem: ");
            int seach = Integer.parseInt(in.nextLine());
            int index = CheckMa(seach);
            if(index != -1)
            {
                a[index].xuat();
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
            int i = Integer.parseInt(in.nextLine());
            int index = CheckMa(i);
            if(index != -1)
            {
                int matam;
                do
                {
                    System.out.print("Nhap ma khach hang moi(Nhap -100 de giu nguyen): ");
                    matam = Integer.parseInt(in.nextLine());
                    if (matam == -100) 
                    {
                        break;    
                    }
                    if(CheckMa(matam) != -1)
                    {
                        System.out.println("----Ma khach hang bi trung. Vui long nhap lai----");
                    }
                    else
                    {
                        a[index].setMaKhachHang(matam);
                        break;
                    }
                }
                while (CheckMa(matam) != -1);
                a[index].sua();
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
    public void xoa()
    {
        try {
            do 
            {
                System.out.println("Nhap ma khach hang can xoa: ");
                int x = Integer.parseInt(in.nextLine());
                if(CheckMa(x) != -1 )
                {
                    if(CheckMa(x) == (a.length-1))
                    {
                        a[a.length-1]=null;
                        return;
                    }
                    else
                    {
                        for(int i = CheckMa(x); i < a.length; i++)
                        {
                            a[i]=a[i-1];
                        }
                    }
                }
                else 
                System.out.println("----Khong tim thay khach hang----");       
            }
            while(true);
        } catch (NumberFormatException e)
        {
            System.out.println("----Loi nhap ma khach hang----");
        }
    }
    public void thongKe() {
        int nam = 0, nu = 0;
        int duoi18 = 0, tu18Den35 = 0, tren35 = 0;
    
        for (int i = 0; i < a.length; i++) {
            KhachHang kh = a[i];
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
        System.out.println("Tong so khach hang: " + a.length);
        System.out.println("So khach hang Nam: " + nam);
        System.out.println("So khach hang Nu: " + nu);
        System.out.println("So khach hang duoi 18 tuoi: " + duoi18);
        System.out.println("So khach hang tu 18 den 35 tuoi: " + tu18Den35);
        System.out.println("So khach hang tren 35 tuoi: " + tren35);
    }    
    public void ghiFile(String tenFile) {
        try (FileWriter fw = new FileWriter(tenFile);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(a.length + "");
            bw.newLine();
            for (KhachHang kh : a) {
                bw.write(kh.getMaKhachHang() + ";"
                        + kh.getTenKhachHang() + ";"
                        + kh.getTuoi() + ";"
                        + kh.getSoDienThoai() + ";"
                        + kh.getDiaChi() + ";"
                        + kh.getGioiTinh());
                bw.newLine();
            }
            System.out.println("----Ghi file thanh cong!----");
    
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }
    public void docFile(String tenFile) {
        try (FileReader fr = new FileReader(tenFile);
             BufferedReader br = new BufferedReader(fr)) {
    
            // Đọc số lượng khách hàng từ file
            String line = br.readLine();
            if (line == null) {
                System.out.println("----File rong----");
                return;
            }
            
            int soLuong = Integer.parseInt(line.trim());
            a = new KhachHang[soLuong]; // Cấp phát mảng với kích thước đọc được
            sl = 0; // Đặt lại số lượng khách hàng hiện có
    
            // Đọc thông tin từng khách hàng
            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(";");
                    if (parts.length == 6) {
                        KhachHang kh = new KhachHang();
                        kh.setMaKhachHang(Integer.parseInt(parts[0].trim()));
                        kh.setTenKhachHang(parts[1].trim());
                        kh.setTuoi(Integer.parseInt(parts[2].trim()));
                        kh.setSoDienThoai(parts[3].trim());
                        kh.setDiaChi(parts[4].trim());
                        kh.setGioiTinh(parts[5].trim());
        
                        a[sl++] = kh; // Thêm khách hàng vào mảng
                    }
                } catch (NumberFormatException e) 
                {
                    System.out.println("----Loi khong doc duoc so nguyen----");
                }
            }
            System.out.println("----Doc file thanh cong!----");
    
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Loi dinh dang du lieu trong file: " + e.getMessage());
        }
    }
    
}