package ProjectQLHieuThuoc;

import java.util.Scanner;

public class QLKhachHang {
    private DSKhachHang dskh;
    Scanner s = new Scanner(System.in);
    String fileName = "C:\\Users\\OS\\IdeaProjects\\exercise\\src\\ProjectQLHieuThuoc/input_DSKhachHang.txt";

    public QLKhachHang() {
        dskh = new DSKhachHang();
    }

    public void menu() {
        dskh.docFile(fileName);
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
                    dskh.them();
                    break;
                case 2:
                    dskh.sua();
                    break;
                case 3:
                    dskh.xoa();
                    break;
                case 4:
                    dskh.timKiem();
                    break;
                case 5:
                    dskh.xem();
                    break;
                case 6:
                    dskh.ghiFile(fileName);
                    System.out.println("Da luu thay doi va quay tro ve Menu chinh.\n");
                    return;
                default:
                    System.out.println("Lua chon khong hop le. Vui long nhap lai.\n");
                    continue;
            }

            // Hỏi người dùng có muốn tiếp tục chọn không
            if (select != 6) {
                System.out.print("Tiep tuc lua chon Menu? (y/n): ");
                String choice = s.nextLine().trim().toLowerCase();
                if (!choice.equals("y") && !choice.equals("yes") && !choice.equals("1")) {
                    dskh.ghiFile(fileName);
                    System.out.println("Da luu thay doi va quay tro ve Menu chinh.\n");
                    break;
                }
            }
        } while (true);
    }
}