package projectQLSach;

public class BoPhim {
    private String boPhim;
    private int namSanXuat;
    private HangSanXuat hangSanXuat;
    private double giaVe;
    private Ngay ngayChieu;

    public BoPhim(){}

    public BoPhim(String boPhim, int namSanXuat, HangSanXuat hangSanXuat, double giaVe, Ngay ngayChieu){
        this.boPhim = boPhim;
        this. namSanXuat = namSanXuat;
        this.hangSanXuat = hangSanXuat;
        this.giaVe = giaVe;
        this.ngayChieu = ngayChieu;
    }

    public String getBoPhim(){
        return boPhim;
    }
    public void setBoPhim(String boPhim){
        this.boPhim = boPhim;
    }

    public int getNamSanXuat(){
        return namSanXuat;
    }
    public void setNamSanXuat(int namSanXuat){
        this.namSanXuat = namSanXuat;
    }

    public HangSanXuat getHangSanXuat(){
        return hangSanXuat;
    }
    public void setHangSanXuat(HangSanXuat hangSanXuat){
        this.hangSanXuat = hangSanXuat;
    }

    public double getGiaVe(){
        return giaVe;
    }
    public void setGiaVe(double giaVe){
        this.giaVe = giaVe;
    }

    public Ngay getNgayChieu(){
        return ngayChieu;
    }
    public void setNgayChieu(Ngay ngayChieu){
        this.ngayChieu = ngayChieu;
    }

    public boolean kiemTraGiaVeReHon(BoPhim boPhimKhac){
        return this.giaVe < boPhimKhac.giaVe;
    }

    public String layTenHangSanXuatPhim(){
        return this.hangSanXuat.getTenHangSanXuat();
    }

}
