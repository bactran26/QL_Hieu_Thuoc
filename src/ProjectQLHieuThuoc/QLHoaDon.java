package ProjectQLHieuThuoc;

import java.util.Scanner;

public class QLHoaDon extends QuanLy{ // có lớp trừu tượng
    public QLHoaDon() {
        fileName = "..\\QL_Hieu_Thuoc\\out\\production\\exercise\\ProjectQLHieuThuoc\\input_DSHoaDon.txt";
        ds = new DSHoaDon(fileName);
    }
    public void menu() {
        int select;
        do {
            System.out.println("[=======================]");
            System.out.println("\tQUẢN LÝ HÓA ĐƠN");
            System.out.println("[=======================]");
            System.out.println("1. Thêm hóa đơn");
            System.out.println("2. Sửa thông tin hóa đơn");
            System.out.println("3. Xóa hóa đơn");
            System.out.println("4. Tìm kiếm hóa đơn");
            System.out.println("5. Xem danh sách hóa đơn");
            System.out.println("6. Về Menu chính");
            System.out.print("Chon: ");

            // Kiểm tra input hợp lệ
            select = kiemtraInput();// hàm được khai báo ở lớp trừu tượng
            sc.nextLine();

            switch (select) {
                case 1:
                    ds.them();
                    break;
                case 2:
                    ds.sua();
                    break;
                case 3:
                    ds.xoa();
                    break;
                case 4:
                    ds.timKiem();
                    break;
                case 5:
                    ds.xem();
                    break;
                case 6:
                    if (thoatMenu()) {
                        return;
                    }
                    break;
                default:
                    System.out.println("Không hợp lệ!Vui lòng nhập lại:\n");
            }
        } while (true);
    }
}