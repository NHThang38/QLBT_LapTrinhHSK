package entity;

public class NhaCungCap {
    private String maNhaCC;
    private String tenNhaCC;
    private String soDT;
    private String eMail;
    private String diaChi;


    public NhaCungCap() {
        // TODO Auto-generated constructor stub
    }


    public NhaCungCap(String maNhaCC, String tenNhaCC, String soDT, String eMail, String diaChi) {
        super();
        this.maNhaCC = maNhaCC;
        this.tenNhaCC = tenNhaCC;
        this.soDT = soDT;
        this.eMail = eMail;
        this.diaChi = diaChi;
    }


    public String getMaNhaCC() {
        return maNhaCC;
    }


    public void setMaNhaCC(String maNhaCC) {
        this.maNhaCC = maNhaCC;
    }


    public String getTenNhaCC() {
        return tenNhaCC;
    }


    public void setTenNhaCC(String tenNhaCC) {
        this.tenNhaCC = tenNhaCC;
    }


    public String getSoDT() {
        return soDT;
    }


    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }


    public String geteMail() {
        return eMail;
    }


    public void seteMail(String eMail) {
        this.eMail = eMail;
    }


    public String getDiaChi() {
        return diaChi;
    }


    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }


    @Override
    public String toString() {
        return "NhaCungCap [maNhaCC=" + maNhaCC + ", tenNhaCC=" + tenNhaCC + ", soDT=" + soDT + ", eMail=" + eMail
                + ", diaChi=" + diaChi  + "]";
    }


}