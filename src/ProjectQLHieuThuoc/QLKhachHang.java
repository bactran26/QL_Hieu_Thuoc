package ProjectQLHieuThuoc;

import java.util.Scanner;

public class QLKhachHang extends QuanLy {
    public QLKhachHang() {
        fileName = "..\\QL_Hieu_Thuoc\\out\\production\\exercise\\ProjectQLHieuThuoc\\input_DSKhachHang.txt";
        ds = new DSKhachHang(fileName);
    }

    public void menu() {
        int select;
        do {
            System.out.println("[=======================]");
            System.out.println("\tKHACH HANG");
            System.out.println("[=======================]");
            System.out.println("1. Them khach hang");
            System.out.println("2. Sua thong tin khach hang");
            System.out.println("3. Xoa khach hang");
            System.out.println("4. Tim kiem khach hang");
            System.out.println("5. Xem thong tin danh sach khach hang");
            System.out.println("6. Quay tro ve giao dien Menu chinh");
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