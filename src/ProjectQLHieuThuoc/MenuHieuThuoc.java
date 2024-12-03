package ProjectQLHieuThuoc;
import java.io.File;

import java.util.Scanner;

public class MenuHieuThuoc {
    public static void main(String[] argv){
        Scanner sc = new Scanner(System.in);
        QuanLy[] ql = new QuanLy[4];
        ql[0] = new QLThuoc();
        ql[1] = new QLHangSX();
        ql[2] = new QLKhachHang();
        ql[3] = new QLHoaDon();
        int select = 0;
        do {
            System.out.println("=========================");
            System.out.println("\t Menu Hiệu Thuốc");
            System.out.println("=========================");
            System.out.println("1. Quản Lý Thuốc");
            System.out.println("2. Quản Lý Hãng Sản Xuất");
            System.out.println("3. Quản Lý Khách Hàng");
            System.out.println("4. Quản Lý Hóa Đơn");
            System.out.println("5. Lưu và Thoát");
            System.out.print("Nhập lựa chọn: ");
            select = sc.nextInt();
            sc.nextLine();
            if (select > 0 && select < 5){
                ql[select-1].menu();
            } else if (select == 5) {
                System.out.println("Đã lưu và thoát chương trình!");
                return;
            } else {
                System.out.println("Lựa chọn không hợp lệ!\nVui lòng chọn lại!");
            }
        }while (true);
    }
}