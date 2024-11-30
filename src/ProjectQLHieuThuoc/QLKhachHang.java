package ProjectQLHieuThuoc;

import java.util.Scanner;

public class QLKhachHang {
    private DSKH dskh;
    Scanner s = new Scanner(System.in);
    String fileName = "C:\\Users\\OS\\IdeaProjects\\exercise\\src\\ProjectQLHieuThuoc/input_DSKhachHang.txt";

    public QLKhachHang() {
        dskh = new DSKH();
    }
    public void menu() {
        dskh.docFile(fileName);
        int select;
        do {
            System.out.println("[=======================]");
            System.out.println("\tKHACH HANG");
            System.out.println("[=======================]");
            System.out.println("1. Nhap thong tin khach hang tu ban phim");
            System.out.println("2. Them khach hang");
            System.out.println("3. Sua thong tin khach hang");
            System.out.println("4. Xoa khach hang");
            System.out.println("5. Tim kiem khach hang");
            System.out.println("6. Xem thong tin danh sach khach hang");
            System.out.println("7. Thong ke danh sach khach hang");
            System.out.println("8. Quay tro ve giao dien Menu chinh");
            System.out.print("Chon: ");
            while (true) {
                if (s.hasNextInt()) {
                    select = s.nextInt();
                    break;
                }
                System.out.println("Khong hop le! Vui long nhap lai.");
                s.nextLine();
            }
            s.nextLine();
            switch (select) {
                case 1:
                    dskh.nhaptay();
                    break;
                case 2:
                    dskh.themkh();
                    break;
                case 3:
                    dskh.suakh();
                    break;
                case 4:
                    dskh.xoa();
                    break;
                case 5:
                    dskh.timKiem();
                    break;
                case 6:
                    dskh.xuattatca();
                    break;
                case 7:
                    dskh.thongKe();
                    break;
                case 8:
                    dskh.ghiFile(fileName);
                    System.out.println("Da luu thay doi va quay tro ve Menu chinh.\n");
                default:
                    System.out.println("Lua chon khong hop le. Vui long nhap lai.\n");
            }
        } while (select != 8);
    }
}