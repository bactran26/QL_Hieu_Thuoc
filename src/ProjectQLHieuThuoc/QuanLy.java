package ProjectQLHieuThuoc;

abstract class QuanLy extends SC {

    protected abstract void menu();

    protected boolean thoatMenu() {
        System.out.print("Bạn chắc muốn thoát và lưu danh sách? (y/n): ");
        String choice = sc.nextLine().trim().toLowerCase();
        if (!choice.equals("y") && !choice.equals("yes") && !choice.equals("1")) {
            return false;
        }
        return true;
    }


    protected int kiemtraInput() {
        while (true) {
            if (sc.hasNextInt()) {
                return sc.nextInt();
            }
            System.out.println("Không hợp lệ!Vui lòng nhập lại:");
            sc.nextLine();
        }
    }
}
