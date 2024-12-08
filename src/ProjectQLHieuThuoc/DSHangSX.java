package ProjectQLHieuThuoc;

import java.util.Scanner;
import java.io.*;

public class DSHangSX {
    private HangSX[] dshsx;
    private int soLuong;  // To keep track of actual number of elements
    private static int mahsx;
    private static final int KICH_THUOC_MAC_DINH = 100;  // Default size for array

    public HangSX[] getDshsx() {
        return dshsx;
    }

    public void setDshsx(HangSX[] dshsx) {
        this.dshsx = dshsx;
        this.soLuong = dshsx.length;
    }

    public DSHangSX() {
        dshsx = new HangSX[KICH_THUOC_MAC_DINH];
        soLuong = 0;
    }

    public int getMahsx() {
        return mahsx;
    }

    public static void setMahsx(int mahsx) {
        DSHangSX.mahsx = mahsx;
    }

    public void xem() {
        if (soLuong > 0) {
            System.out.println("\nTHONG TIN HANG SAN XUAT");
            for (int i = 0; i < soLuong; i++) {
                dshsx[i].xuat();
                System.out.println("------------------------------------------");
            }
            System.out.println("\n");
        } else {
            System.out.println("DANH SACH TRONG!\n");
        }
    }

    public void them() {
        if (soLuong >= dshsx.length) {
            // Array is full, need to expand
            HangSX[] newArray = new HangSX[dshsx.length * 2];
            System.arraycopy(dshsx, 0, newArray, 0, dshsx.length);
            dshsx = newArray;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("\nNHAP THONG TIN HANG SAN XUAT");
        HangSX hsx = new HangSX();

        System.out.println("Nhap ma hang san xuat (Nhap 0 de thoat): ");
        while (true) {
            int mahsx = sc.nextInt();
            if (mahsx == 0) {
                return;
            }

            boolean maTrung = false;
            for (int i = 0; i < soLuong; i++) {
                if (dshsx[i].getMaHangSX() == mahsx) {
                    maTrung = true;
                    System.out.println("Ma hang san xuat bi trung");
                    System.out.println("Vui long nhap lai ma khac hoac nhap 0 de thoat");
                    break;
                }
            }

            if (!maTrung) {
                hsx.setMaHangSX(mahsx);
                break;
            }
        }

        hsx.nhap();
        dshsx[soLuong++] = hsx;
        ghiFile("input_DSHangSX.txt");
        System.out.println("Da them hang san xuat va cap nhat vao file!");
    }

    public void sua() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== SUA THONG TIN HANG SAN XUAT ===");
        System.out.println("Nhap ten hang san xuat can sua: ");
        String tenHangSua = sc.nextLine();

        // Tim hang san xuat can sua
        int count = 0;
        int[] viTriTimThay = new int[soLuong];

        for (int i = 0; i < soLuong; i++) {
            if (dshsx[i].getTenHangSX().equalsIgnoreCase(tenHangSua)) {
                System.out.println("\nThong tin hang san xuat hien tai");
                dshsx[i].xuat();
                viTriTimThay[count++] = i;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay hang nao");
            return;
        }

        int viTriSua;
        if (count == 1) {
            viTriSua = viTriTimThay[0];
        } else {
            System.out.println("Co nhieu hang san xuat trung ten. Vui long nhap ma hang");
            System.out.println("Nhap ma hang san xuat: ");
            int maHang = sc.nextInt();
            viTriSua = -1;
            for (int i = 0; i < count; i++) {
                if (dshsx[viTriTimThay[i]].getMaHangSX() == maHang) {
                    viTriSua = viTriTimThay[i];
                    break;
                }
            }
            if (viTriSua == -1) {
                System.out.println("Khong tim thay hang can sua");
                return;
            }
        }

        // Cap nhat thong tin
        System.out.println("\n=== CAP NHAT THONG TIN HANG SAN XUAT ===");
        System.out.println("Nhap ma hang moi (nhap 0 de giu nguyen): ");
        int maMoi = sc.nextInt();
        sc.nextLine();

        if (maMoi != 0) {
            boolean maTrung = false;
            for (int i = 0; i < soLuong; i++) {
                if (dshsx[i].getMaHangSX() == maMoi) {
                    System.out.println("Ma hang san xuat da ton tai!");
                    maTrung = true;
                    break;
                }
            }
            if (!maTrung) {
                dshsx[viTriSua].setMaHangSX(maMoi);
            }
        }

        System.out.println("Nhap ten hang moi (Enter de giu nguyen): ");
        String tenMoi = sc.nextLine();
        if (!tenMoi.isEmpty()) {
            dshsx[viTriSua].setTenHangSX(tenMoi);
        }

        System.out.println("Nhap dia chi moi (Enter de giu nguyen): ");
        String diaChiMoi = sc.nextLine();
        if (!diaChiMoi.isEmpty()) {
            dshsx[viTriSua].setDiaChiHangSX(diaChiMoi);
        }

        System.out.println("Nhap so dien thoai moi (Nhap 0 de giu nguyen): ");
        long sdtMoi = sc.nextLong();
        if (sdtMoi != 0) {
            dshsx[viTriSua].setSdtHangSX(sdtMoi);
        }

        ghiFile("input_DSHangSX.txt");
        System.out.println("Da cap nhat thong tin hang san xuat va luu vao file!");
    }

    public void xoa() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== XOA HANG SAN XUAT ===");
        System.out.println("Nhap ten hang san xuat can xoa: ");
        String tenHangXoa = sc.nextLine();

        int count = 0;
        int[] viTriTimThay = new int[soLuong];

        for (int i = 0; i < soLuong; i++) {
            if (dshsx[i] != null && dshsx[i].getTenHangSX() != null &&
                    dshsx[i].getTenHangSX().equalsIgnoreCase(tenHangXoa)) {
                System.out.println("\nThong tin hang san xuat: ");
                dshsx[i].xuat();
                viTriTimThay[count++] = i;
            }
        }

        if (count == 0) {
            System.out.println("Khong tim thay ten can xoa");
            return;
        }

        int viTriXoa;
        if (count == 1) {
            viTriXoa = viTriTimThay[0];
        } else {
            System.out.println("Co nhieu ten trung nhau. vui long nhap ma hang");
            System.out.println("Nhap ma hang can xoa: ");
            int maHang = sc.nextInt();
            viTriXoa = -1;
            for (int i = 0; i < count; i++) {
                if (dshsx[viTriTimThay[i]].getMaHangSX() == maHang) {
                    viTriXoa = viTriTimThay[i];
                    break;
                }
            }
            if (viTriXoa == -1) {
                System.out.println("Khong tim thay hang can xoa: " + maHang);
                return;
            }
        }

        System.out.println("Ban co muon xoa hang san xuat nay?(Y/N)");
        sc.nextLine();
        String xacNhan = sc.nextLine();
        if (xacNhan.equalsIgnoreCase("Y")) {
            // Di chuyen cac phan tu sau len truoc
            for (int i = viTriXoa; i < soLuong - 1; i++) {
                dshsx[i] = dshsx[i + 1];
            }
            dshsx[soLuong - 1] = null;
            soLuong--;
            System.out.println("Da xoa hang thanh cong!");
        } else {
            System.out.println("Da huy xoa hang san xuat");
        }
    }

    public void timKiem() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== TIM KIEM HANG SAN XUAT ===");
        System.out.println("1. Tim kiem theo ma");
        System.out.println("2. Tim kiem theo ten");
        System.out.println("Vui long nhap lua chon: ");
        int luaChon = sc.nextInt();
        sc.nextLine();

        switch (luaChon) {
            case 1:
                System.out.println("Nhap ma hang san xuat can tim: ");
                int maTimKiem = sc.nextInt();

                boolean timThayMa = false;
                for (int i = 0; i < soLuong; i++) {
                    if (dshsx[i].getMaHangSX() == maTimKiem) {
                        System.out.println("\nThong tin hang san xuat");
                        dshsx[i].xuat();
                        timThayMa = true;
                        break;
                    }
                }

                if (!timThayMa) {
                    System.out.println("Khong tim thay ma hang san xuat: " + maTimKiem);
                }
                break;

            case 2:
                System.out.println("Nhap ten hang san xuat can tim: ");
                String tenTimKiem = sc.nextLine();

                boolean tenTimThay = false;
                for (int i = 0; i < soLuong; i++) {
                    if (dshsx[i].getTenHangSX().toLowerCase().contains(tenTimKiem.toLowerCase())) {
                        if (!tenTimThay) {
                            System.out.println("\nCac hang san xuat tim thay");
                        }
                        dshsx[i].xuat();
                        System.out.println("-------------------------------------------");
                        tenTimThay = true;
                    }
                }
                break;

            default:
                System.out.println("Lua chon khong hop le");
        }
    }

    public void ghiFile(String tenFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenFile))) {
            for (int i = 0; i < soLuong; i++) {
                String line = String.format("%d;%s;%s;%d",
                        dshsx[i].getMaHangSX(),
                        dshsx[i].getTenHangSX(),
                        dshsx[i].getDiaChiHangSX(),
                        dshsx[i].getSdtHangSX());
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Ghi file HSX thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi ghi file HSX: " + e.getMessage());
        }
    }

    public void docFile(String tenFile) {
        soLuong = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 4) {
                    if (soLuong >= dshsx.length) {
                        // Expand array if needed
                        HangSX[] newArray = new HangSX[dshsx.length * 2];
                        System.arraycopy(dshsx, 0, newArray, 0, dshsx.length);
                        dshsx = newArray;
                    }

                    dshsx[soLuong++] = new HangSX(
                            Integer.parseInt(data[0]),     // mã hãng
                            data[1],                       // tên hãng
                            data[2],                       // địa chỉ
                            Long.parseLong(data[3])        // số điện thoại
                    );
                }
            }
            System.out.println("Doc file HSX thanh cong!");
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file HSX: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Loi doc file HSX: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Loi dinh dang so HSX: " + e.getMessage());
        }
    }
}