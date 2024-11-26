import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
class khachHang
{
    private int makh;
    private String tenkh;
    private int tuoi;
    private long sdt;
    private String diaChi;
    private String gioiTinh;
    public khachHang() {makh=0;tenkh=null;tuoi=0;sdt=0;diaChi=null;gioiTinh=null;}
    public khachHang(int makh, String tenkh, int tuoi, long sdt,String diaChi, String gioiTinh)
    {
        this.makh = makh;
        this.tenkh = tenkh;
        this.tuoi = tuoi;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
    }
    public khachHang(khachHang d)
    {
        d.makh = makh;
        d.tenkh = tenkh;
        d.tuoi = tuoi;
        d.sdt = sdt;
        d.diaChi = diaChi;
        d.gioiTinh = gioiTinh;
    }
    public int getmakh()
    {
        return makh;
    }
    public void setmakh(int a)
    {
        makh = a;
    }
    public String gettenkh()
    {
        return tenkh;
    }
    public void settenkh(String ten)
    {
        tenkh = ten;
    }
    public int gettuoi()
    {
        return tuoi;
    }
    public void settuoi(int t)
    {
        tuoi = t;
    }
    public long getsdt()
    {
        return sdt;
    }
    public void setsdt(long s)
    {
        sdt = s;
    }
    public String getdiachi()
    {
        return diaChi;
    }
    public void setdiachi(String d)
    {
        diaChi = d;
    }
    public String getgioitinh()
    {
        return gioiTinh;
    }
    public void setgioitinh(String g)
    {
        gioiTinh = g;
    }
    public void nhap()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap ma khach hang: ");
        makh = Integer.parseInt(in.nextLine());
        System.out.print("Nhap ten khach hang: ");
        tenkh = in.nextLine();
        System.out.print("Nhap tuoi khach hang: ");
        tuoi = Integer.parseInt(in.nextLine());
        System.out.print("Nhap so dien thoai khach hang: ");
        sdt = Long.parseLong(in.nextLine());
        System.out.print("Nhap dia chi khach hang: ");
        diaChi = in.nextLine();
        System.out.print("Nhap gioi tinh khach hang: ");
        gioiTinh = in.nextLine();
    }
    public void xuat()
    {
        System.out.println("____________________________________________________________________________________");
        System.out.print("| Ma khach hang: "+makh);
        System.out.print("| Ten khach hang: "+tenkh);
        System.out.print("| Tuoi: "+tuoi);
        System.out.print("| SDT: "+sdt);
        System.out.print("| Dia chi: "+diaChi);
        System.out.println("| Gioi tinh: "+gioiTinh);
    }
    public void sua()
    {
        int select = 0;
        do {
            Scanner in = new Scanner(System.in);
            System.out.println("=========================");
            System.out.println("      | Chinh Sua |");
            System.out.println("=========================");
            System.out.println("1. Ma khach hang");
            System.out.println("2. Ten khach hang");
            System.out.println("3. Tuoi");
            System.out.println("4. SDT");
            System.out.println("5. Dia chi");
            System.out.println("6. Gioi tinh");
            System.out.println("7. Tat ca");
            System.out.println("8. Thoat");
            select = Integer.parseInt(in.nextLine());
            switch (select){
                case 1:
                    System.out.print("Nhap ma khach hang:");
                    int a = in.nextInt();
                    setmakh(a);
                    break;
                case 2:
                    System.out.print("Nhap ten khach hang:");
                    String ten = in.nextLine();
                    settenkh(ten);
                    break;
                case 3:
                    System.out.print("Nhap tuoi khach hang:");
                    int t = in.nextInt();
                    settuoi(t);
                    break;
                case 4:
                    System.out.print("Nhap so dien thoai khach hang:");
                    Long s = in.nextLong();
                    setsdt(s);
                    break;
                case 5:
                    System.out.print("Nhap dia chi khach hang:");
                    String d = in.nextLine();
                    setdiachi(d);
                    break;
                case 6:
                    System.out.print("Nhap gioi tinh khach hang:");
                    String g = in.nextLine();
                    setgioitinh(g);
                    break;
                case 7:
                    nhap();
                    break;
                case 8:
                    System.out.println("Da luu va thoat chinh sua");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }while (select != 8);
    }
    public String toString() {
        return "KhachHang[" +
                "MaKhachHang=" + makh +
                ", TenKhachHang='" + tenkh + '\'' +
                ", Tuoi='" + tuoi + '\'' +
                ", SDT=" + sdt +
                ", DiaChi=" + diaChi +
                ", GioiTinh=" + gioiTinh +
                ']';
    }
}

class DSKH
{
    private khachHang a[] = new khachHang[0];
    private int sl = 0;
    public void nhaptay()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("So luong khach hang can nhap la: ");
        int n = Integer.parseInt(in.nextLine());
        a = new khachHang[n];
        for(int i=0;i<n;i++)
        {
            a[i] = new khachHang();
            System.out.println("Nhap thong tin khach hang thu " + (sl + 1) + ":");
            a[i].nhap();
            sl++;
        }
    }
    public void xuattatca()
    {
        System.out.println("Danh sach thong tin khach hang");
        for(int i=0;i<sl;i++)
        {
            a[i].xuat();
        }
        System.out.println("\n");
    }
    public void themkh()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("So luong khach hang can them la: ");
        int k = Integer.parseInt(in.nextLine());
        a=Arrays.copyOf(a,sl+k);
        int tmp=sl;
        for(int i=tmp;i<tmp+k;i++)
        {
            a[i] = new khachHang();
            a[i].nhap();
            sl++;
        }
    }
    public int timkiem(int x)
    {
        for(int i=0;i<sl;i++)
        {
            if(a[i].getmakh() == x)
                return i;
        }
        return 0;
    }
    public void suakh()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap ma khach hang can sua: ");
        int i = Integer.parseInt(in.nextLine());
        a[timkiem(i)].sua();
    }
    public void xoa()
    {
        Scanner in = new Scanner(System.in);
        do 
        {
            System.out.println("Nhap ma khach hang can xoa: ");
            int x = Integer.parseInt(in.nextLine());
            if(timkiem(x) != 0 )
            {
                if(timkiem(x) == sl-1)
                {
                    a[sl-1]=null;
                    sl--;
                    return;
                }
                else
                {
                    for(int i = timkiem(x); i < sl; i++)
                    {
                        a[i]=a[i-1];
                        return;
                    }
                }
            }
            else 
            System.out.println("----Khong tim thay khach hang----");       
        }
        while(true);
    }
}

public class QLKhachHang
{
    public static void main(String[] args) throws Exception
    {
        DSKH ds = new DSKH();
        ds.nhaptay();
        ds.xuattatca();
        ds.themkh();
        ds.xuattatca();
        ds.suakh();
        ds.xuattatca();
        ds.xoa();
        ds.xuattatca();
    }
}