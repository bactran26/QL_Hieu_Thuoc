package ProjectQLHieuThuoc;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ChiTietHoaDon implements INhap, IXuat{
    private int maHD;
    private Thuoc thuoc;
    private int soLuongMua;
    private static ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<>();

    public ChiTietHoaDon() {
        this.thuoc = null;
        this.soLuongMua = 0;
        this.maHD = 0;
        dsChiTietHoaDon.add(this);
    }

    public ChiTietHoaDon(int maHD, Thuoc thuoc, int soLuongMua) {
        this.thuoc = thuoc;
        this.soLuongMua = soLuongMua;
        this.maHD = maHD;
        dsChiTietHoaDon.add(this);
    }

    public Thuoc getThuoc() {
        return thuoc;
    }

    public void setThuoc(Thuoc thuoc) {
        this.thuoc = thuoc;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public static ArrayList<ChiTietHoaDon> getDsChiTietHoaDon() {
        if (dsChiTietHoaDon.isEmpty()) {
            dsChiTietHoaDon = docFile();
        }
        return dsChiTietHoaDon;
    }

    public double tinhThanhTien() {
        if (thuoc != null) {
            return soLuongMua * thuoc.getGiaThuoc() ;
        }
        return 0;
    }

    public void xoa() {
        dsChiTietHoaDon.remove(this);
    }

    public static ArrayList<ChiTietHoaDon> docFile() {
        ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<>();
        try (FileReader fr = new FileReader("..\\QL_Hieu_Thuoc\\out\\production\\exercise\\ProjectQLHieuThuoc\\input_ChiTietHoaDon.txt");
             BufferedReader br = new BufferedReader(fr)) {

            // Tải dữ liệu thuốc
            DSThuoc dsThuoc = new DSThuoc();
            dsThuoc.taiDanhSachTuFile("..\\QL_Hieu_Thuoc\\out\\production\\exercise\\ProjectQLHieuThuoc\\input_DSThuoc.txt");

            // Đọc thông tin từng hang
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    Thuoc thuoc = dsThuoc.timKiemThuocTheoMa(Integer.parseInt(parts[1]));
                    ChiTietHoaDon chitiet = new ChiTietHoaDon(Integer.parseInt(parts[0]), thuoc, Integer.parseInt(parts[2]));
                    dsChiTietHoaDon.add(chitiet);
                }
            }
            return dsChiTietHoaDon;

        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file: " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
            return null;
        } catch (NumberFormatException e) {
            System.out.println("Lỗi định dạng số: " + e.getMessage());
            return null;
        }
    }

    public static void ghiFile() {
        try (FileWriter fw = new FileWriter("input_ChiTietHoaDon.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {

            // Ghi thông tin từng hang
            for (ChiTietHoaDon hd : dsChiTietHoaDon ) {
                // Format: maHD|ngayThanhToan|soLuongMua|maKH
                bw.write(hd.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi ghi file chi tiet hoa don: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return String.format(
                "%d|%d|%d",
                this.getMaHD(),
                this.getThuoc().getMaThuoc(),
                this.getSoLuongMua()
        );
    }

    @Override
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        DSThuoc dsThuoc = new DSThuoc();
        dsThuoc.taiDanhSachTuFile("input_DSThuoc.txt");
        // Kiểm tra dữ liệu đã tải
        if (dsThuoc.getDsThuoc().isEmpty()) {
            System.out.println("Danh sach thuoc trong!");
            return;
        }

        System.out.print("Nhap ma thuoc: ");
        int maThuoc = sc.nextInt();
        Thuoc thuoc = dsThuoc.timKiemThuocTheoMa(maThuoc);
        if (thuoc == null) {
            System.out.println("Khong tim thay thuoc!");
            return;
        }
        this.thuoc = thuoc;

        System.out.print("Nhap so luong mua: ");
        int soLuong = sc.nextInt();
        if (soLuong > thuoc.getSoLuong()) {
            System.out.println("So luong ton khong du!");
            return;
        }
        this.soLuongMua = soLuong;
    }

    @Override
    public void xuat() {
        System.out.println(
                String.format(
                        "|%-10d|%-15s|%-15s|%-10.2f|%-5d|%-10.2f",
                        this.getThuoc().getMaThuoc(),
                        this.getThuoc().getTenThuoc(),
                        this.getThuoc().getLoaiThuoc(),
                        this.getThuoc().getGiaThuoc(),
                        this.getSoLuongMua(),
                        this.tinhThanhTien()
                )
        );
    }
}