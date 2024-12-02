package ProjectQLHieuThuoc;

import java.util.Scanner;

public class QLHoaDon extends QuanLi{ // có lớp trừu tượng
    private DSHoaDon dsHoaDon;

    public QLHoaDon() {
        fileName = "..\\QL_Hieu_Thuoc\\out\\production\\exercise\\ProjectQLHieuThuoc\\input_DSHoaDon.txt";
        dsHoaDon = new DSHoaDon(fileName);
    }

    public void menu() {
        int select;
        do {
            System.out.println("[=======================]");
            System.out.println("\tQUAN LI HOA DON");
            System.out.println("[=======================]");
            System.out.println("1. Them hoa don");
            System.out.println("2. Sua thong tin hoa don");
            System.out.println("3. Xoa hoa don");
            System.out.println("4. Tim kiem hoa don");
            System.out.println("5. Xem danh sach hoa don");
            System.out.println("6. Quay tro ve giao dien Menu chinh");
            System.out.print("Chon: ");

            // Kiểm tra input hợp lệ
            select = kiemtraInput();// hàm được khai báo ở lớp trừu tượng
            sc.nextLine();

            switch (select) {
                case 1:
                    dsHoaDon.them();
                    break;
                case 2:
                    dsHoaDon.sua();
                    break;
                case 3:
                    dsHoaDon.xoa();
                    break;
                case 4:
                    dsHoaDon.timKiem();
                    break;
                case 5:
                    dsHoaDon.xem();
                    break;
                case 6:
                    dsHoaDon.ghiFile(fileName);
                    thongbaoThoatMenu();
                    return;
                default:
                    System.out.println("Lua chon khong hop le. Vui long nhap lai.\n");
                    continue;
            }

            // Hỏi người dùng có muốn tiếp tục chọn không
            if (select != 6) {
                if (thoatMenu()) {// hàm được khai báo ở lớp trừu tượng
                    dsHoaDon.ghiFile(fileName);
                    thongbaoThoatMenu();// hàm được khai báo ở lớp trừu tượng
                    return;
                }
            }
        } while (true);
    }
}