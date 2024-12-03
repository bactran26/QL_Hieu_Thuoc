package ProjectQLHieuThuoc;

import java.util.Scanner;

public class MenuHieuThuoc {
    public static void main(String[] argv){
        int select = 0;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("=========================");
            System.out.println("\t MenuHieuThuoc");
            System.out.println("=========================");
            System.out.println("1. Quan ly thuoc");
            System.out.println("2. Quan ly hang san xuat");
            System.out.println("3. Quan ly khach hang");
            System.out.println("4. Quan ly hoa don");
            System.out.println("5. Luu thay doi va thoat");
            System.out.print("Nhap lua chon: ");
            select = sc.nextInt();
            sc.nextLine();
            switch (select){
                case 1:
                    QLThuoc ql1 = new QLThuoc();
                    ql1.menu();
                    break;
                case 2:
                    QLHangSX ql2 = new QLHangSX();
                    ql2.menu();
                    break;
                case 3:
                    QLKhachHang ql3 = new QLKhachHang();
                    ql3.menu();
                    break;
                case 4:
                    QLHoaDon ql4 = new QLHoaDon();
                    ql4.menu();
                    break;
                case 5:
                    System.out.println("Da lu va thoat chuong trinh");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
                    break;
            }
        }while (select != 6);
    }
}