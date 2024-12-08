package ProjectQLHieuThuoc;

import java.util.Scanner;

public class MenuHieuThuoc {
    public static void main(String[] argv){
        int select = 0;
        QLThuoc ql1 = new QLThuoc();
        QLHangSX ql2 = new QLHangSX();
        QLKhachHang ql3 = new QLKhachHang();
        QLHoaDon ql4 = new QLHoaDon();
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("=========================");
            System.out.println("\t MENU HIỆU THUỐC");
            System.out.println("=========================");
            System.out.println("1. QUẢN LÝ THUỐC");
            System.out.println("2. QUẢN LÝ HÃNG SẢN XUẤT");
            System.out.println("3. QUẢN LÝ KHÁCH HÀNG");
            System.out.println("4. QUẢN LÝ HÓA ĐƠN");
            System.out.println("5. LƯU VÀ THOÁT");
            System.out.print("NHẬP LỰA CHỌN: ");
            select = sc.nextInt();
            sc.nextLine();
            switch (select){
                case 1:
                    ql1.menu();
                    break;
                case 2:
                    ql2.menu();
                    break;
                case 3:
                    ql3.menu();
                    break;
                case 4:
                    ql4.menu();
                    break;
                case 5:
                    DSKhachHang.ghiFile( "..\\QL_Hieu_Thuoc\\out\\production\\exercise\\ProjectQLHieuThuoc\\input_DSKhachHang.txt");
                    DSHoaDon.ghiFile("..\\QL_Hieu_Thuoc\\out\\production\\exercise\\ProjectQLHieuThuoc\\input_DSHoaDon.txt");
                    System.out.println("ĐÃ LƯU VÀ THOÁT CHƯƠNG TRÌNH");
                    return;
                default:
                    System.out.println("LỰA CHỌN KHÔNG HỢP LỆ!");
                    break;
            }
        }while (select != 6);
    }
}