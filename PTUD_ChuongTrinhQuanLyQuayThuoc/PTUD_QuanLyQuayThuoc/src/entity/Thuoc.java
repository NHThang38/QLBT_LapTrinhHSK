package entity;

import java.sql.Date;

public class Thuoc {
	private String maThuoc;
	private String tenThuoc;
	private Loaithuoc loaiThuoc;
	private String thanhPhan;
	private String congDung;
	private String donViTinh;
	private String xuatXu;
	private int soLuong;
	private double giaBan;
	private Date ngayHetHan;

	public Thuoc() {
		// TODO Auto-generated constructor stub
	}

	public Thuoc(String maThuoc, String tenThuoc, String thanhPhan, String congDung, String donViTinh, String xuatXu,
			int soLuong, double giaBan, Date ngayHetHan) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.thanhPhan = thanhPhan;
		this.congDung = congDung;
		this.donViTinh = donViTinh;
		this.xuatXu = xuatXu;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
		this.ngayHetHan = ngayHetHan;
	}

	public String getMaThuoc() {
		return maThuoc;
	}

	public void setMaThuoc(String maThuoc) {
		this.maThuoc = maThuoc;
	}

	public String getTenThuoc() {
		return tenThuoc;
	}

	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}

	public Loaithuoc getLoaiThuoc() {
		return loaiThuoc;
	}

	public void setLoaiThuoc(Loaithuoc loaiThuoc) {
		this.loaiThuoc = loaiThuoc;
	}

	public String getThanhPhan() {
		return thanhPhan;
	}

	public void setThanhPhan(String thanhPhan) {
		this.thanhPhan = thanhPhan;
	}

	public String getCongDung() {
		return congDung;
	}

	public void setCongDung(String congDung) {
		this.congDung = congDung;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public String getXuatXu() {
		return xuatXu;
	}

	public void setXuatXu(String xuatXu) {
		this.xuatXu = xuatXu;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}

	public Date getNgayHetHan() {
		return ngayHetHan;
	}

	public void setNgayHetHan(Date ngayHetHan) {
		this.ngayHetHan = ngayHetHan;
	}

	@Override
	public String toString() {
		return "Thuoc [maThuoc=" + maThuoc + ", tenThuoc=" + tenThuoc + ", loaiThuoc=" + loaiThuoc + ", thanhPhan="
				+ thanhPhan + ", congDung=" + congDung + ", donViTinh=" + donViTinh + ", xuatXu=" + xuatXu
				+ ", soLuong=" + soLuong + ", giaBan=" + giaBan + ", ngayHetHan=" + ngayHetHan + "]";
	}

	

}
