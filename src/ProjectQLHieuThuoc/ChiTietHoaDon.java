package ProjectQLHieuThuoc;

public class ChiTietHoaDon {
    private Thuoc thuoc;
    private int soLuongMua;

    public ChiTietHoaDon() {
        this.thuoc = new Thuoc();
    }

    public ChiTietHoaDon(Thuoc thuoc, int soLuongMua) {
        this.thuoc = thuoc;
        this.soLuongMua = soLuongMua;
    }

    public Thuoc getThuoc() {
        return thuoc;
    }

    public void setThuoc(Thuoc thuoc) {
        this.thuoc = thuoc;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    @Override
    public String toString() {
        return thuoc.getMaThuoc() + " " + thuoc.getTenThuoc() +
                "\nSo luong thuoc da mua: " + soLuongMua;
    }

    public double tinhTien() {
        if (this.thuoc != null) {
            return this.thuoc.getGiaThuoc() * this.soLuongMua;
        } else {
            return 0.0;
        }
    }
}