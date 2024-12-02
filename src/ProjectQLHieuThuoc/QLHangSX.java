package ProjectQLHieuThuoc;

import java.util.Scanner;

public class QLHangSX extends QuanLi {
    private DSHangSX dshsx;
    String fileName = "input_DSHangSX.txt";

    public QLHangSX() {
        dshsx = new DSHangSX();
    }

    public void menu() {
        dshsx.docFile(fileName);
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
                    dshsx.them();
                    break;
                case 2:
                    dshsx.sua();
                    break;
                case 3:
                    dshsx.xoa();
                    break;
                case 4:
                    dshsx.timKiem();
                    break;
                case 5:
                    dshsx.xem();
                    break;
                case 6:
                    dshsx.ghiFile(fileName);
                    thongbaoThoatMenu();
                    return;
                default:
                    System.out.println("Lua chon khong hop le. Vui long nhap lai.\n");
                    continue;
            }

            // Hỏi người dùng có muốn tiếp tục chọn không
            if (select != 6) {
                if (thoatMenu()) {// hàm được khai báo ở lớp trừu tượng
                    dshsx.ghiFile(fileName);
                    thongbaoThoatMenu();// hàm được khai báo ở lớp trừu tượng
                    return;
                }
            }
        } while (true);
    }
}