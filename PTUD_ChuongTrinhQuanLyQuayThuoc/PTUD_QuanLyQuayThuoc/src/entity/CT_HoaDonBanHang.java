package entity;

public class CT_HoaDonBanHang {
	private HoaDonBanHang hoaDonBanHang;
	private Thuoc thuoc;
	private int soLuong;
	private double donGia;
	
	public CT_HoaDonBanHang() {
		// TODO Auto-generated constructor stub
	}

	public CT_HoaDonBanHang(int soLuong, double donGia) {
		super();
		this.soLuong = soLuong;
		this.donGia = donGia;
	}

	public HoaDonBanHang getHoaDonBanHang() {
		return hoaDonBanHang;
	}

	public void setHoaDonBanHang(HoaDonBanHang hoaDonBanHang) {
		this.hoaDonBanHang = hoaDonBanHang;
	}

	public Thuoc getThuoc() {
		return thuoc;
	}

	public void setThuoc(Thuoc thuoc) {
		this.thuoc = thuoc;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	@Override
	public String toString() {
		return "CT_HoaDonBanHang [hoaDonBanHang=" + hoaDonBanHang + ", thuoc=" + thuoc + ", soLuong=" + soLuong
				+ ", donGia=" + donGia + "]";
	}
	
}
