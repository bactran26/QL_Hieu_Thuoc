package ProjectQLHieuThuoc;

import java.util.Scanner;

public class QLThuoc extends QuanLy {
    private DSThuoc dsThuoc;
    String fileName = "input_DSThuoc.txt";
    String fileNameHSX = "input_DSHangSX.txt";
    DSHangSX dsHangSX;
    public QLThuoc() {
        fileName = "..\\QL_Hieu_Thuoc\\out\\production\\exercise\\ProjectQLHieuThuoc\\input_DSHoaDon.txt";
        ds = new DSHoaDon(fileName);
        dsHangSX = new DSHangSX();
        dsHangSX.docFile(fileNameHSX);
        dsThuoc = new DSThuoc();
    }

    public void menu() {
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

