package ProjectQLHieuThuoc;

import java.util.Scanner;

public class QLHoaDon {
    private DSHoaDon dsHoaDon;
    Scanner s = new Scanner(System.in);
    String fileName = "C:\\Users\\OS\\IdeaProjects\\exercise\\src\\ProjectQLHieuThuoc/input_DSHoaDon.txt";
    String fileNameKhachHang = "C:\\Users\\OS\\IdeaProjects\\exercise\\src\\ProjectQLHieuThuoc/input_DSKhachHang.txt";
    String fileNameThuoc = "C:\\Users\\OS\\IdeaProjects\\exercise\\src\\ProjectQLHieuThuoc/input_DSThuoc.txt";

    public QLHoaDon() {
        dsHoaDon = new DSHoaDon();
    }

    public void menu() {
        dsHoaDon.docFile(fileName);
        int select;
        do {
            System.out.println("[=======================]");
            System.out.println("\tHOA DON");
            System.out.println("[=======================]");
            System.out.println("1. Them hoa don");
            System.out.println("2. Sua thong tin hoa don");
            System.out.println("3. Xoa hoa don");
            System.out.println("4. Tim kiem hoa don");
            System.out.println("5. Xem danh sach hoa don");
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
                    dsHoaDon.ghiFile(fileName);
                    System.out.println("Da luu thay doi va quay tro ve Menu chinh.\n");
                    break;
                }
            }
        } while (true);
    }
}