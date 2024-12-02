package ProjectQLHieuThuoc;

abstract class QuanLi extends SC {
    protected String fileName;

    protected abstract void menu();

    protected boolean thoatMenu() {
        System.out.print("Tiep tuc lua chon Menu? (y/n): ");
        String choice = sc.nextLine().trim().toLowerCase();
        if (!choice.equals("y") && !choice.equals("yes") && !choice.equals("1")) {
            return true;
        }
        return false;
    }

    protected void thongbaoThoatMenu() {
        System.out.println("Da luu thay doi va quay tro ve Menu chinh.\n");
    }

    protected int kiemtraInput() {
        while (true) {
            if (sc.hasNextInt()) {
                return sc.nextInt();
            }
            System.out.println("Khong hop le! Vui long nhap lai.");
            sc.nextLine();
        }
    }
}
