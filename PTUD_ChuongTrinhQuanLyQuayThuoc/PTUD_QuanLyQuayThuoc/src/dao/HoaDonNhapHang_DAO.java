package dao;

import connection.MyConnection;
import entity.HoaDonNhapHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HoaDonNhapHang_DAO {
    private Connection con;

    public HoaDonNhapHang_DAO() {
        con = MyConnection.getInstance().getConnection();
    }

    public boolean addHDNH(HoaDonNhapHang hd) {
        try {
            PreparedStatement hdAdd = con.prepareStatement("INSERT INTO HoaDonNhapHang ([MANHACC],[MANV],[NGAYLAPHD]," +
                    "[TONGTIEN]) VALUES(?,?,?,?)");
            hdAdd.setString(1, hd.getNhaCungCap().getMaNhaCC());
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
            stmt = con.prepareStatement("SELECT MAX([MAHDNH]) FROM [dbo].[HoaDonNhapHang]");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            ma = rs.getString(1);
        }
        return ma;
    }
    public HoaDonNhapHang TimKiemMa(String ma){
        HoaDonNhapHang hdnh = null;
        NhaCungCap_DAO nccDao;
        NhanVien_DAO nvDao;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from HoaDonNhapHang where MAHDNH = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                nccDao = new NhaCungCap_DAO();
                nvDao = new NhanVien_DAO();
                hdnh = new HoaDonNhapHang(rs.getString(1),nccDao.TimKiemMa(rs.getString(2)),
                        nvDao.TimKiemMa(rs.getString(3)),rs.getDate(4));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return hdnh;
    }
    public boolean updateTongtien(String maHD,double tongTien) {
        String sql = "update HOADONNHAPHANG set TONGTIEN = ? where MAHDNH = ?";
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
