package entity;

import java.sql.Date;

public class HoaDonNhapHang {
    private String maHDNH;
    private NhaCungCap nhaCungCap;
    private NhanVien nhanVien;
    private Date ngayLapHD;
    private double tongTien;

    public HoaDonNhapHang() {
        // TODO Auto-generated constructor stub
    }

    public HoaDonNhapHang(String maHDNH, NhaCungCap nhaCungCap, NhanVien nhanVien, Date ngayLapHD) {
        super();
        this.maHDNH = maHDNH;
        this.nhaCungCap = nhaCungCap;
        this.nhanVien = nhanVien;
        this.ngayLapHD = ngayLapHD;
    }

    public String getMaHDNH() {
        return maHDNH;
    }

    public void setMaHDNH(String maHDNH) {
        this.maHDNH = maHDNH;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Date getNgayLapHD() {
        return ngayLapHD;
    }

    public void setNgayLapHD(Date ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "HoaDonBanHang [maHDNH=" + maHDNH + ", nhaCungCap=" + nhaCungCap + ", nhanVien=" + nhanVien
                + ", ngayLapHD=" + ngayLapHD + ", tongTien=" + tongTien + "]";
    }


}
