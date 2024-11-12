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
            System.out.println("3. Quan ly nhan su");
            System.out.println("4. Quan ly khac hang");
            System.out.println("5. Quan ly hoa don");
            select = sc.nextInt();
            sc.nextLine();
            switch (select){
                case 1:
                    QLThuoc ql1 = new QLThuoc();
                    ql1.menu();
                    break;
                case 2:
                    QLHangSX = new QLHangSX();
                    ql2.menu();
                    break;
                case 3:
                    QLNhanSu ql3 = new QLHangSX();
                    ql3.menu();
                    break;
                case 4:
                    QLKhachHang ql4 = new QLKhachHang();
                    ql4.menu();
                    break;
                case 5:
                    QLHoaDon ql1 = new QLThuoc();
                    ql5.menu();
                    break;
                case 6:
                    System.out.println("Da lu va thoat chuong trinh");
                    ql6.menu();
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }while (select != 6);
    }
}
