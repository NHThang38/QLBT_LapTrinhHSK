package dao;

import connection.MyConnection;
import entity.HoaDonBanHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HoaDonBanHang_DAO {
    private Connection con;

    public HoaDonBanHang_DAO() {
        con = MyConnection.getInstance().getConnection();
    }

    public boolean addHDBH(HoaDonBanHang hd) {
        try {
            PreparedStatement hdAdd = con.prepareStatement("INSERT INTO HoaDonBanHang ([MAKH],[MANV],[NGAYLAPHD]," +
                    "[TONGTIEN]) VALUES(?,?,?,?)");
            hdAdd.setString(1, hd.getKhachHang().getMaKH());
            hdAdd.setString(2, hd.getNhanVien().getMaNV());
            hdAdd.setDate(3, hd.getNgayLapHD());
            hdAdd.setDouble(4, hd.getTongTien());
            int n = hdAdd.executeUpdate();
            if (n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public String getMa() throws SQLException {
        String ma = "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("SELECT MAX([MAHDBH]) FROM [dbo].[HoaDonBanHang]");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            ma = rs.getString(1);
        }
        return ma;
    }
    public HoaDonBanHang TimKiemMa(String ma){
        HoaDonBanHang hdbh = null;
        KhachHang_DAO khDao;
        NhanVien_DAO nvDao;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from HoaDonBanHang where MAHDBH = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                khDao = new KhachHang_DAO();
                nvDao = new NhanVien_DAO();
                hdbh = new HoaDonBanHang(rs.getString(1),khDao.TimKiemMa(rs.getString(2)),
                        nvDao.TimKiemMa(rs.getString(3)),rs.getDate(4));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return hdbh;
    }
    public boolean updateTongtien(String maHD,double tongTien) {
        String sql = "update HOADONBANHANG set TONGTIEN = ? where MAHDBH = ?";
        try {
            System.out.println("OK1");
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDouble(1,tongTien);
            stmt.setString(2,maHD);

            int n = stmt.executeUpdate();
            System.out.println("OK2");
            if(n > 0){
                System.out.println("OK3");
                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
