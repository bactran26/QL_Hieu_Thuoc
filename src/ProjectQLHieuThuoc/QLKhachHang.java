package ProjectQLHieuThuoc;

import java.util.Scanner;

public class QLKhachHang {
    private DSKhachHang dskh;
    Scanner s = new Scanner(System.in);
    String fileName = "C:\\Danh\\Code\\Code Visual Studio\\Java\\TEST_Project\\ProjectQLHieuThuoc\\input_DSKhachHang.txt";

    public QLKhachHang() {
        dskh = new DSKhachHang();
    }

    public void menu() {
        dskh.docFile(fileName);
        int select;
        do {
            System.out.println("\n");
            System.out.println("[=======================]");
            System.out.println("      KHACH HANG");
            System.out.println("[=======================]");
            System.out.println("1. Nhap them khach hang tu ban phim");
            System.out.println("2. Sua thong tin khach hang");
            System.out.println("3. Xoa khach hang");
            System.out.println("4. Tim kiem khach hang");
            System.out.println("5. Xem thong tin danh sach khach hang");
            System.out.println("6. Thong ke danh sach khach hang");
            System.out.println("7. Quay tro ve giao dien Menu chinh");
            System.out.print("Chon: ");
            select = Integer.parseInt(s.nextLine());
            switch (select) {
                case 1:
                    dskh.themkh();
                    break;
                case 2:
                    dskh.suakh();
                    break;
                case 3:
                    dskh.xoa();
                    break;
                case 4:
                    dskh.timkiemkh();
                    break;
                case 5:
                    dskh.xuattatca();
                    break;
                case 6:
                    dskh.thongKe();
                    break;
                case 7:
                    dskh.ghiFile(fileName);
                    System.out.println("Da luu thay doi va quay tro ve Menu chinh.\n");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long nhap lai.\n");
                    break;
            }
        } while (select != 7);
    }
}