package ProjectQLHieuThuoc;

import java.util.Scanner;
public class QLKhachHang
{
    public void menukh()
    {
        DSKH ds = new DSKH();
        int select = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("=========================");
            System.out.println("    Menu Khach Hang");
            System.out.println("=========================");
            System.out.println("1. Nhap n khach hang tu ban phim");
            System.out.println("2. Xuat danh sach khach hang");
            System.out.println("3. Them n khach hang tu ban phim");
            System.out.println("4. Chinh sua thong tin khach hang");
            System.out.println("5. Tim kiem khach hang theo ma");
            System.out.println("6. Xoa khach hang theo ma");
            System.out.println("7. Ghi danh sach khach hang vao file");
            System.out.println("8. Doc du lieu tu file");
            System.out.println("9. Thoat Menu");
            System.out.print("Nhap lua chon cua ban: ");
            select = sc.nextInt();
            sc.nextLine();
            switch (select){
                case 1:
                    ds.nhaptay();
                    break;
                case 2:
                    ds.xuattatca();
                    break;
                case 3:
                    ds.themkh();
                    break;
                case 4:
                    ds.suakh();
                    break;
                case 5:
                    ds.timkiemkh();
                    break;
                case 6:
                    ds.xoa();
                    break;
                case 7:
                    ds.ghifile();
                    break;
                case 8:
                    ds.docfile();
                    break;
                case 9:
                    System.out.println("Da thoat Menu, Xin chao va hen gap lai!!!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }while (select != 9);
        sc.close();
    }
}