package ProjectQLHieuThuoc;

import java.util.Scanner;

public class QLHangSX {
    private DSHangSX dshsx;
    Scanner s = new Scanner(System.in);
    String fileName = "..\\QL_Hieu_Thuoc\\out\\production\\exercise\\ProjectQLHieuThuoc\\input_DSHangSX.txt";

    public QLHangSX() {
        dshsx = new DSHangSX();
        dshsx.docFile(fileName);
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
                    dshsx.ghiFile(fileName);
                    System.out.println("Da luu thay doi va quay tro ve Menu chinh.\n");
                    break;
                }
            }
        } while (true);
    }
}