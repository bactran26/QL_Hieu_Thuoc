package ProjectQLHieuThuoc;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
public class DSKH
{
    private KhachHang a[] = new KhachHang[0];
    private int sl = a.length;
    public void nhaptay()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("So luong khach hang can nhap la: ");
        int n = Integer.parseInt(in.nextLine());
        a=Arrays.copyOf(a,sl+n);
        for(int i=0;i<n;i++)
        {
            a[i] = new KhachHang();
            System.out.println("Nhap thong tin khach hang thu " + (sl + 1) + ":");
            a[i].nhap();
            sl++;
        }
        in.close();
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
            a[i] = new KhachHang();
            a[i].nhap();
            sl++;
        }
        in.close();
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
    public void timkiemkh()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Nhap ma khach hang can tiem kiem: ");
        int seach = Integer.parseInt(in.nextLine());
        if(timkiem(seach) != 0)
        {
            a[timkiem(seach)].xuat();
        }
        else
        {
            System.out.println("----Khong tim thay khach hang----");
        }
        in.close();
    }
    public void suakh()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Nhap ma khach hang can sua: ");
        int i = Integer.parseInt(in.nextLine());
        a[timkiem(i)].sua();
        in.close();
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
    public void taofile() throws Exception
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Nhap ten file can tao: ");
        String namef = in.nextLine();
        try
        {
            File f = new File(namef);
            if (f.createNewFile()) 
            {
                System.out.println("Da tao File: " + f.getName());
            }
            else 
            {
                System.out.println("File da ton tai.");
            }
        } 
        catch (IOException e) 
        {
            System.out.println("----Da xay ra loi----");
            e.printStackTrace();
        }
        finally
        {
            in.close();
        }
    }
    public void docfile()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Nhap ten file can doc: ");
        String namef = in.nextLine();
        try 
        {
            File myObj = new File(namef);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              System.out.println(data);
            }
            myReader.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("----Khong tim thay file----");
            e.printStackTrace();
        }
        finally
        {
            in.close();
        }
    }
    public void ghifile()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Nhap ten file can ghi: ");
        String namef = in.nextLine();
        try 
        {
            FileWriter myWriter = new FileWriter(namef);
            for(int i=0;i<sl;i++)
            {
                myWriter.write("____________________________________________________________________________________\n");
                myWriter.write("| Ma khach hang: "+a[i].getmakh());
                myWriter.write("| Ten khach hang: "+a[i].gettenkh());
                myWriter.write("| Tuoi: "+a[i].gettuoi());
                myWriter.write("| SDT: "+a[i].getsdt());
                myWriter.write("| Dia chi: "+a[i].getdiachi());
                myWriter.write("| Gioi tinh: "+a[i].getgioitinh());
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } 
        catch (IOException e) 
        {
            System.out.println("----Da xay ra loi----");
            e.printStackTrace();
        }
        finally
        {
            in.close();
        }
    }
}
