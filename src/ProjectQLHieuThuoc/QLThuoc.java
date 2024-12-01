package ProjectQLHieuThuoc;

import java.util.Scanner;

public class QLThuoc {
    private DSThuoc dsThuoc;
    String fileName = "C:\\Users\\OS\\IdeaProjects\\exercise\\src\\ProjectQLHieuThuoc/input_DSThuoc.txt";
    String fileNameHSX = "C:\\Users\\OS\\IdeaProjects\\exercise\\src\\ProjectQLHieuThuoc/input_DSHangSX.txt";
    DSHangSX dsHangSX;
    public QLThuoc() {
        dsHangSX = new DSHangSX();
        dsHangSX.docFile(fileNameHSX);
        dsThuoc = new DSThuoc();
    }

    public void menu() {
        Scanner s = new Scanner(System.in);
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
                    dsThuoc.xuatDanhSachRaFile(fileName);
                    System.out.println("Da luu thay doi va quay tro ve Menu chinh.\n");
                    break;
                }
            }
        } while (true);
    }
}

