package ProjectQLHieuThuoc;

import java.util.Scanner;

public class QLHangSX extends QuanLy {
    public QLHangSX() {
        fileName = "..\\QL_Hieu_Thuoc\\out\\production\\exercise\\ProjectQLHieuThuoc\\input_DSHangSX.txt";
        ds = new DSHoaDon(fileName);
    }

    public void menu() {
        int select;
        do {
            System.out.println("[=======================]");
            System.out.println("\tHANG SAN XUAT");
            System.out.println("[=======================]");
            System.out.println("1. Them hang san xuat");
            System.out.println("2. Sua thong tin hang san xuat");
            System.out.println("3. Xoa hang san xuat");
            System.out.println("4. Tim kiem hang san xuat");
            System.out.println("5. Xem danh sach hang san xuat");
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