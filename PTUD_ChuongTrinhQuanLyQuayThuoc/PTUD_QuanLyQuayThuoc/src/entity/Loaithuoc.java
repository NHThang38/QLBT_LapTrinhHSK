package entity;

public class Loaithuoc {
	private String maLoai;
	private String tenThuoc;
	
	public Loaithuoc() {
		// TODO Auto-generated constructor stub
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenThuoc() {
		return tenThuoc;
	}

	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}

	public Loaithuoc(String maLoai, String tenThuoc) {
		super();
		this.maLoai = maLoai;
		this.tenThuoc = tenThuoc;
	}

	@Override
	public String toString() {
		return "Loaithuoc [maLoai=" + maLoai + ", tenThuoc=" + tenThuoc + "]";
	}
	
	
}
