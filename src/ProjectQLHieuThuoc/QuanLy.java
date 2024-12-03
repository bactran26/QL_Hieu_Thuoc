package ProjectQLHieuThuoc;

abstract class QuanLy extends SC {
    protected String fileName;
    protected DanhSach ds;

    protected abstract void menu();

    protected boolean thoatMenu() {
        System.out.print("Bạn chắc muốn thoát và lưu danh sách? (y/n): ");
        String choice = sc.nextLine().trim().toLowerCase();
        if (!choice.equals("y") && !choice.equals("yes") && !choice.equals("1")) {
            try {
                ds.ghiFile( fileName);
                System.out.println("Đã lưu danh sách và về Menu chính.\n");
                return true;
            } catch (Exception e) {
                System.out.println("Lỗi lưu dữ liệu:" + e.getMessage());
                return false;
            }
        }
        return false;
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
