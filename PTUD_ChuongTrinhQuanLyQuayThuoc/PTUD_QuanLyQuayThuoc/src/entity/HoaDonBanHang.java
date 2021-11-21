package entity;

import java.sql.Date;

public class HoaDonBanHang {
	private String maHDBH;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	private Date ngayLapHD;
	private double tongTien;
	
	public HoaDonBanHang() {
		// TODO Auto-generated constructor stub
	}

	public HoaDonBanHang(String maHDBH, KhachHang khachHang, NhanVien nhanVien, Date ngayLapHD) {
		super();
		this.maHDBH = maHDBH;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.ngayLapHD = ngayLapHD;
	}

	public String getMaHDBH() {
		return maHDBH;
	}

	public void setMaHDBH(String maHDBH) {
		this.maHDBH = maHDBH;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
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
		return "HoaDonBanHang [maHDBH=" + maHDBH + ", khachHang=" + khachHang + ", nhanVien=" + nhanVien
				+ ", ngayLapHD=" + ngayLapHD + ", tongTien=" + tongTien + "]";
	}
	

}
