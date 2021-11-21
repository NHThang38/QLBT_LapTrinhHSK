package dao;

import connection.MyConnection;
import entity.ChucVu;
import entity.Loaithuoc;
import entity.NhanVien;
import gui.LoaiThuoc_Form;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoaiThuoc_DAO {
    private Connection con;

    public LoaiThuoc_DAO() {
        con = MyConnection.getInstance().getConnection();
    }
    public ResultSet getResultSet(String StoreName)throws Exception {
        ResultSet rs = null;
        try {
            String callStore;
            callStore = "{Call " + StoreName +"}";
            CallableStatement cs = this.con.prepareCall(callStore);
            cs.executeQuery();
            rs = cs.getResultSet();
        } catch (Exception e) {
            throw new Exception("Error get Store " + e.getMessage());
        }
        return rs;
    }
    public List<Loaithuoc> getLS() {
        List<Loaithuoc> ds = new ArrayList<>();
        try {
            //PreparedStatement stmt = con.prepareStatement("select * from KhachHang");
            ResultSet rs = getResultSet("select_LT");
            while(rs.next()){
                Loaithuoc lt =new Loaithuoc(rs.getString(1),rs.getString(2));
                ds.add(lt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public boolean addLoaiThuoc(Loaithuoc lt) {
        try {
            PreparedStatement nvAdd = con.prepareStatement("INSERT INTO LOAITHUOC ([TENLOAI]) VALUES(?)");
            nvAdd.setString(1,lt.getTenThuoc());
            int n = nvAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteLT(String maLoai) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from LoaiThuoc where MALOAI = ?");
            stmt.setString(1, maLoai);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateLoaiThuoc(String maLoai, Loaithuoc lt) {
        String sql = "update LoaiThuoc set MaLoai = ? ,TENLOAI = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,lt.getMaLoai());
            stmt.setString(2,lt.getTenThuoc());

            int n = stmt.executeUpdate();
            if(n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public Loaithuoc TimKiemMa(String ma){
        Loaithuoc lt = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LOAITHUOC where MALOAI = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                lt = new Loaithuoc(rs.getString(1),rs.getString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lt;
    }
    public Loaithuoc TimKiemTen(String ten){
        Loaithuoc lt = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LOAITHUOC where TENLOAI = ?");
            stmt.setString(1,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                lt = new Loaithuoc(rs.getString(1),rs.getString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lt;
    }
}
