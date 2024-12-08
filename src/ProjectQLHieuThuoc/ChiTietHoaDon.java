package ProjectQLHieuThuoc;

import java.io.*;
import java.util.Arrays;

public class ChiTietHoaDon extends SC {
    private int maHD;
    private Thuoc thuoc;
    private double donGia;
    private int soLuongMua;
    private static ChiTietHoaDon[] dsChiTietHoaDon = new ChiTietHoaDon[0];
    private static int soLuongChiTietHoaDon = 0;

    // Constructor
    public ChiTietHoaDon() {
        this.maHD = 0;
        this.thuoc = null;
        this.donGia = 0.0;
        this.soLuongMua = 0;
    }

    public ChiTietHoaDon(int maHD, Thuoc thuoc,double donGia, int soLuongMua) {
        this.maHD = maHD;
        this.thuoc = thuoc;
        this.donGia = donGia;
        this.soLuongMua = soLuongMua;
    }

    //Get/Set
    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public Thuoc getThuoc() {
        return thuoc;
    }

    public void setThuoc(Thuoc thuoc) {
        this.thuoc = thuoc;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public static ChiTietHoaDon[] getDsChiTietHoaDon() {
        return dsChiTietHoaDon;
    }


    //method
    public static void themChiTietHoaDon(ChiTietHoaDon chitiet) {
        dsChiTietHoaDon = Arrays.copyOf(dsChiTietHoaDon, soLuongChiTietHoaDon + 1);
        dsChiTietHoaDon[soLuongChiTietHoaDon] = chitiet;
        soLuongChiTietHoaDon++;
    }

    public double tinhThanhTien() {
        if (this.thuoc != null) {
            return this.donGia * this.soLuongMua;
        } else {
            return 0.0;
        }
    }

    public void nhap() {
        System.out.print("Nhập mã thuốc:");
        int maThuoc = sc.nextInt();
        Thuoc thuoc = DSThuoc.timKiemThuocTheoMa(maThuoc);
        if (thuoc == null) {
            System.out.println("Mã thuốc không tồn tại!");
            return;
        }
        this.thuoc = thuoc;
        this.donGia = thuoc.getGiaThuoc();
        System.out.print("Nhập số lượng mua:");
        int soLuongMua = sc.nextInt();
        if (soLuongMua <= thuoc.getSoLuong()) {
            this.soLuongMua = soLuongMua;
            return;
        }
        System.out.println("Số lượng thuốc không đủ!");
    }

    public void xuat() {
        System.out.println(
                String.format(
                        "|%-10d|%-15s|%-15s|%-10.2f|%-5d|%-10.2f",
                        this.getThuoc().getMaThuoc(),
                        this.getThuoc().getTenThuoc(),
                        this.getThuoc().getLoaiThuoc(),
                        this.getDonGia(),
                        this.getSoLuongMua(),
                        this.tinhThanhTien()
                )
        );
    }

    public void sua() {
        System.out.println("Mã thuốc:" + this.getThuoc().getMaThuoc() + " (-1 de giu nguyen)| ");
        int maThuoc = sc.nextInt();
        if (maThuoc == -1) {
            Thuoc thuoc = DSThuoc.timKiemThuocTheoMa(maThuoc);
            if (thuoc == null) {
                System.out.println("Mã thuốc không tồn tại!");
                return;
            }
            this.thuoc = thuoc;
            this.donGia = thuoc.getGiaThuoc();
        }

        System.out.println("Số lượng mua:"+ this.getSoLuongMua() + " (-1 de giu nguyen)| ");
        int soLuongMua = sc.nextInt();
        if (soLuongMua == -1) {
            if (soLuongMua <= thuoc.getSoLuong()) {
                this.soLuongMua = soLuongMua;
                return;
            }
            System.out.println("Số lượng thuốc không đủ!");
        }
    }

    public static void docFile(String tenFile) {
        try (FileReader fr = new FileReader(tenFile);
             BufferedReader br = new BufferedReader(fr)) {

            // Đọc thông tin từng hang format maHD|maThuoc|donGia|soLuongMua
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    Thuoc thuoc = DSThuoc.timKiemThuocTheoMa(Integer.parseInt(parts[1]));
                    ChiTietHoaDon chitiet = new ChiTietHoaDon(Integer.parseInt(parts[0]), thuoc, Double.parseDouble(parts[2]), Integer.parseInt(parts[3]));
                    ChiTietHoaDon.themChiTietHoaDon(chitiet);
                }
            }
            System.out.println("Đọc dữ liệu chi tiết hóa đơn thành công!");
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file chi tiết hóa đơn: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Lỗi đọc file chi tiết hóa đơn: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Lỗi định dạng số chi tiết hóa đơn: " + e.getMessage());
        }
    }

    public static void ghiFile(String tenFile) {
        try (FileWriter fw = new FileWriter(tenFile);
             BufferedWriter bw = new BufferedWriter(fw)) {

            // Ghi thông tin từng hang
            for (ChiTietHoaDon hd : dsChiTietHoaDon ) {
                // Format: maHD|maThuoc|donGia|soLuong
                bw.write(hd.toString());
                bw.newLine();
            }
            System.out.println("Ghi file chi tiết hóa đơn thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi ghi file chi tiết hóa đơn: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return String.format(
                "%d|%d|%.2f|%d|",
                this.getMaHD(),
                this.getThuoc().getMaThuoc(),
                this.getDonGia(),
                this.getSoLuongMua()
        );
    }

}