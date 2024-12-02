package ProjectQLHieuThuoc;

import java.util.Scanner;

public class QLThuoc extends QuanLi {
    private DSThuoc dsThuoc;
    String fileName = "input_DSThuoc.txt";
    String fileNameHSX = "input_DSHangSX.txt";
    DSHangSX dsHangSX;
    public QLThuoc() {
        dsHangSX = new DSHangSX();
        dsHangSX.docFile(fileNameHSX);
        dsThuoc = new DSThuoc();
    }

    public void menu() {
        dsThuoc.taiDanhSachTuFile(fileName);
        int select;
        do {
            System.out.println("[======================]");
            System.out.println("\t THUOC");
            System.out.println("[======================]");
            System.out.println("1. Them thuoc moi");
            System.out.println("2. Sua thong tin thuoc");
            System.out.println("3. Xoa mot thuoc");
            System.out.println("4. Tim kiem thuoc");
            System.out.println("5. Xem danh sach thuoc");
            System.out.println("6. Quay tro ve giao dien Menu chinh");
            System.out.print("Chon: ");

            // Kiểm tra input hợp lệ
            select = kiemtraInput();// hàm được khai báo ở lớp trừu tượng
            sc.nextLine();

            switch (select) {
                case 1:
                    dsThuoc.them();
                    break;
                case 2:
                    dsThuoc.sua();
                    break;
                case 3:
                    dsThuoc.xoa();
                    break;
                case 4:
                    dsThuoc.timKiem();
                    break;
                case 5:
                    dsThuoc.xem();
                    break;
                case 6:
                    dsThuoc.xuatDanhSachRaFile(fileName);
                    thongbaoThoatMenu();
                    return;
                default:
                    System.out.println("Lua chon khong hop le. Vui long nhap lai.\n");
                    continue;
            }

            // Hỏi người dùng có muốn tiếp tục chọn không
            if (select != 6) {
                if (thoatMenu()) {// hàm được khai báo ở lớp trừu tượng
                    dsThuoc.xuatDanhSachRaFile(fileName);
                    thongbaoThoatMenu();// hàm được khai báo ở lớp trừu tượng
                    return;
                }
            }
        } while (true);
    }
}

