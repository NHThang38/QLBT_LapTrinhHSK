package entity;

import java.sql.Date;

public class NhanVien {
    private String maNV;
    private String tenNV;
    private String gioiTinh;
    private Date ngaySinh;
    private String email;
    private String dienThoai;
    private int CMND;
    private String diaChi;
    private ChucVu chucVu;
    private Date ngayVaoLam;

    public NhanVien() {
        // TODO Auto-generated constructor stub
    }

    public NhanVien(String maNV, String tenNV, String gioiTinh, Date ngaySinh, String email, String dienThoai,
                    int cMND, String diaChi, Date ngayVaoLam) {
        super();
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.dienThoai = dienThoai;
        CMND = cMND;
        this.diaChi = diaChi;
        this.ngayVaoLam = ngayVaoLam;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
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

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

    public Date getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }


    @Override
    public String toString() {
        return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh
                + ", email=" + email + ", dienThoai=" + dienThoai + ", CMND=" + CMND + ", diaChi=" + diaChi
                + ", chucVu=" + chucVu + ", ngayVaoLam=" + ngayVaoLam  + "]";
    }
}
