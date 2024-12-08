package ProjectQLHieuThuoc;

import java.util.Scanner;
public class KhachHang
{
    // Thuộc tính
    private int maKhachHang;
    private String tenKhachHang;
    private int tuoi;
    private String soDienThoai;
    private String diaChi;
    private String gioiTinh;
    Scanner in = new Scanner(System.in);

    // Constructor mặc định
    public KhachHang() {}

    // Constructor đầy đủ tham số
    public KhachHang(int maKhachHang, String tenKhachHang, int tuoi,String soDienThoai, String diaChi, String gioiTinh) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.tuoi = tuoi;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
    }
    public KhachHang(KhachHang d)
    {
        d.maKhachHang = maKhachHang;
        d.tenKhachHang = tenKhachHang;
        d.tuoi = tuoi;
        d.soDienThoai = soDienThoai;
        d.diaChi = diaChi;
        d.gioiTinh = gioiTinh;
    }

    //get-set
    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int  maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void nhap()
    {
        try 
        {
            System.out.print("Nhap ma khach hang: ");
            maKhachHang = Integer.parseInt(in.nextLine());
            System.out.print("Nhap ten khach hang: ");
            tenKhachHang = in.nextLine();
            System.out.print("Nhap tuoi khach hang: ");
            tuoi = Integer.parseInt(in.nextLine());
            if (tuoi < 0 || tuoi > 120) {
                System.out.println("Loi: Tuoi khong hop le!");
                return;
            }
            System.out.print("Nhap so dien thoai khach hang: ");
            soDienThoai = in.nextLine();
            System.out.print("Nhap dia chi khach hang: ");
            diaChi = in.nextLine();
            System.out.print("Nhap gioi tinh khach hang: ");
            gioiTinh = in.nextLine();
            System.out.println("\n");
        } 
        catch (NumberFormatException e) 
        {
            System.out.println("Loi: Du lieu nhap khong hop le. Vui long thu lai.");
        } 
    }
    public void xuat()
    {
        System.out.println(
                String.format(
                        "|%-15s|%-25s|%-5s|%-12s|%-20s|%-10s",
                        this.maKhachHang,
                        this.tenKhachHang,
                        this.tuoi,
                        this.soDienThoai,
                        this.diaChi,
                        this.gioiTinh
                ));
    }
    public void sua()
    {
        int select = 0;
        do {
            System.out.println("\n");
            System.out.println("=========================");
            System.out.println("     | Chinh Sua |");
            System.out.println("=========================");
            System.out.println("1. Ten khach hang");
            System.out.println("2. Tuoi");
            System.out.println("3. SDT");
            System.out.println("4. Dia chi");
            System.out.println("5. Gioi tinh");
            System.out.println("6. Thoat");
            select = Integer.parseInt(in.nextLine());
            switch (select){
                case 1:
                    System.out.print("Nhap ten khach hang moi:");
                    String ten = in.nextLine();
                    setTenKhachHang(ten);
                    break;
                case 2:
                    System.out.print("Nhap tuoi khach hang moi:");
                    int t = Integer.parseInt(in.nextLine());
                    setTuoi(t);
                    break;
                case 3:
                    System.out.print("Nhap so dien thoai khach hang moi:");
                    String s = in.nextLine();
                    setSoDienThoai(s);
                    break;
                case 4:
                    System.out.print("Nhap dia chi khach hang moi:");
                    String d = in.nextLine();
                    setDiaChi(d);
                    break;
                case 5:
                    System.out.print("Nhap gioi tinh khach hang moi:");
                    String g = in.nextLine();
                    setGioiTinh(g);
                    break;
                case 6:
                    System.out.println("Da luu va thoat chinh sua");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
                    break;
            }
        }while (select != 6);
    }
    public String toString() {
        return "KhachHang[" +
                "MaKhachHang=" + maKhachHang +
                ", TenKhachHang='" + tenKhachHang + '\'' +
                ", Tuoi='" + tuoi + '\'' +
                ", SDT=" + soDienThoai +
                ", DiaChi=" + diaChi +
                ", GioiTinh=" + gioiTinh +
                ']';
    }
}
