package dao;

import connection.MyConnection;
import entity.KhachHang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHang_DAO {
    private Connection con;

    public KhachHang_DAO() {
        con = MyConnection.getInstance().getConnection();
    }

    public ResultSet getResultSet(String StoreName) throws Exception {
        ResultSet rs = null;
        try {
            String callStore;
            callStore = "{Call " + StoreName + "}";
            CallableStatement cs = this.con.prepareCall(callStore);
            cs.executeQuery();
            rs = cs.getResultSet();
        } catch (Exception e) {
            throw new Exception("Error get Store " + e.getMessage());
        }
        return rs;
    }

    public List<KhachHang> getLS() {
        List<KhachHang> ds = new ArrayList<>();
        try {
            //PreparedStatement stmt = con.prepareStatement("select * from KhachHang");
            ResultSet rs = getResultSet("select_KH");
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8));
                ds.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    public boolean addKhachHang(KhachHang kh) {
        try {
            PreparedStatement khAdd = con.prepareStatement("INSERT INTO KhachHang ([TENKH],[GIOITINH],[NGAYSINH]," +
                    "[EMAIL],[DIENTHOAI],[CMND],[DIACHI]) VALUES(?,?,?,?,?,?,?)");
            khAdd.setString(1, kh.getTenKH());
            khAdd.setString(2, kh.getGioiTinh());
            khAdd.setDate(3, kh.getNgaySinh());
            khAdd.setString(4, kh.getEmail());
            khAdd.setString(5, kh.getDienThoai());
            khAdd.setInt(6, kh.getCMND());
            khAdd.setString(7, kh.getDiaChi());

            int n = khAdd.executeUpdate();
            if (n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteKH(String maKH) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from KhachHang where MAKH = ?");
            stmt.setString(1, maKH);
            int n = stmt.executeUpdate();
            if (n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateKhachHang(String maKH, KhachHang kh) {
        String sql = "update KhachHang set TENKH = ?, "
                + "GIOITINH = ?,NGAYSINH = ? ,EMAIL = ?,DIENTHOAI = ? ,CMND = ? ,DIACHI = ? where MAKH = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, kh.getTenKH());
            stmt.setString(2, kh.getGioiTinh());
            stmt.setDate(3, kh.getNgaySinh());
            stmt.setString(4, kh.getEmail());
            stmt.setString(5, kh.getDienThoai());
            stmt.setInt(6, kh.getCMND());
            stmt.setString(7, kh.getDiaChi());
            stmt.setString(8, maKH);

            int n = stmt.executeUpdate();
            if (n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public KhachHang TimKiemMa(String ma) {
        KhachHang kh = null;
        try {
            PreparedStatement stmt = con.prepareStatement("select * from KhachHang where MAKH = ?");
            stmt.setString(1, ma);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getDate(4), rs.getString(5), rs.getString(6),
                        rs.getInt(7), rs.getString(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kh;
    }

    public KhachHang TimKiemTen(String ten) {
        KhachHang kh = null;
        try {
            PreparedStatement stmt = con.prepareStatement("select * from KhachHang where TENKH = ?");
            stmt.setString(1, ten);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getDate(4), rs.getString(5), rs.getString(6),
                        rs.getInt(7), rs.getString(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kh;
    }
    public KhachHang TimKiemCM(int cm){
        KhachHang kh = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from KhachHang where CMND = ?");
            stmt.setInt(1,cm);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                kh = new KhachHang(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getDate(4),rs.getString(5), rs.getString(6),
                        rs.getInt(7),rs.getString(8));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return kh;
    }
    public KhachHang TimKiemSDT1(String soDT){
        String so = "%".concat(soDT).concat("%");
        System.out.println(so);
        KhachHang kh = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from KHACHHANG where DIENTHOAI LIKE ?");
            stmt.setString(1,so);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return kh;
    }
}