package entity;

import java.sql.Date;

public class KhachHang {
    private String maKH;
    private String tenKH;
    private String gioiTinh;
    private Date ngaySinh;
    private String email;
    private String dienThoai;
    private int CMND;
    private String diaChi;

    public KhachHang() {
        // TODO Auto-generated constructor stub
    }

    public KhachHang(String maKH, String tenKH, String gioiTinh, Date ngaySinh, String email, String dienThoai,
                     int cMND, String diaChi) {
        super();
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.dienThoai = dienThoai;
        CMND = cMND;
        this.diaChi = diaChi;

    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public int getCMND() {
        return CMND;
    }

    public void setCMND(int cMND) {
        CMND = cMND;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }


    @Override
    public String toString() {
        return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh
                + ", email=" + email + ", dienThoai=" + dienThoai + ", CMND=" + CMND + ", diaChi=" + diaChi
               + "]";
    }



}
