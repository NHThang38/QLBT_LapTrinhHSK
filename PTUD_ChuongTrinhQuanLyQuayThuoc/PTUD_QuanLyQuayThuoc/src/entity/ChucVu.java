package entity;

public class ChucVu {
    private String maChucVu;
    private String tenChucVu;
    private Double heSoLuong;

    public ChucVu() {
        // TODO Auto-generated constructor stub
    }

    public ChucVu(String maChucVu, String tenChucVu, Double heSoLuong) {
        super();
        this.maChucVu = maChucVu;
        this.tenChucVu = tenChucVu;
        this.heSoLuong = heSoLuong;
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    public Double getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(Double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    @Override
    public String toString() {
        return "ChucVu [maChucVu=" + maChucVu + ", tenChucVu=" + tenChucVu + ", heSoLuong=" + heSoLuong + "]";
    }

}

