package dao;

import connection.MyConnection;
import entity.Loaithuoc;
import entity.Thuoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Thuoc_DAO {
    private Connection con;

    public Thuoc_DAO() {
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

    public List<Thuoc> getLS() {
        List<Thuoc> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_T");
            while (rs.next()) {
                Thuoc thuoc = new Thuoc(rs.getString(1), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8),rs.getDouble(9),rs.getDate(10));
                LoaiThuoc_DAO ltDao =new LoaiThuoc_DAO();
                Loaithuoc lt = ltDao.TimKiemMa(rs.getString(2));
                thuoc.setLoaiThuoc(lt);
                ds.add(thuoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    public boolean addThuoc(Thuoc thuoc) {
        try {
            PreparedStatement thuocAdd = con.prepareStatement("INSERT INTO Thuoc ([MALOAI],[TENTHUOC],[THANHPHAN]," +
                    "[CONGDUNG],[DONVITINH],[XUATXU],[SOLUONG],[GIABAN],[NGAYHETHAN]) VALUES(?,?,?,?,?,?,?,?,?)");
            thuocAdd.setString(1, thuoc.getLoaiThuoc().getMaLoai());
            thuocAdd.setString(2, thuoc.getTenThuoc());
            thuocAdd.setString(3, thuoc.getThanhPhan());
            thuocAdd.setString(4, thuoc.getCongDung());
            thuocAdd.setString(5, thuoc.getDonViTinh());
            thuocAdd.setString(6, thuoc.getXuatXu());
            thuocAdd.setInt(7, thuoc.getSoLuong());
            thuocAdd.setDouble(8, thuoc.getGiaBan());
            thuocAdd.setDate(9, thuoc.getNgayHetHan());

            int n = thuocAdd.executeUpdate();
            if (n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteThuoc(String maThuoc) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from THuoc where MAT = ?");
            stmt.setString(1, maThuoc);
            int n = stmt.executeUpdate();
            if (n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateThuoc(String maThuoc, Thuoc thuoc) {
        String sql = "update Thuoc set [MALOAI] = ?,[TENTHUOC] = ?,[THANHPHAN] = ?,[CONGDUNG] = ?,[DONVITINH] = ?,[XUATXU] = ?,[SOLUONG] = ?,[GIABAN] = ?,[NGAYHETHAN] =?  where MAT = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, thuoc.getLoaiThuoc().getMaLoai());
            stmt.setString(2, thuoc.getTenThuoc());
            stmt.setString(3, thuoc.getThanhPhan());
            stmt.setString(4, thuoc.getCongDung());
            stmt.setString(5, thuoc.getDonViTinh());
            stmt.setString(6, thuoc.getXuatXu());
            stmt.setInt(7, thuoc.getSoLuong());
            stmt.setDouble(8, thuoc.getGiaBan());
            stmt.setDate(9, thuoc.getNgayHetHan());
            stmt.setString(10, maThuoc);

            int n = stmt.executeUpdate();
            if (n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Thuoc TimKiemMa(String ma) {
        Thuoc thuoc = null;
        try {
            PreparedStatement stmt = con.prepareStatement("select * from Thuoc where MAT = ?");
            stmt.setString(1, ma);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                thuoc = new Thuoc(rs.getString(1), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8),rs.getDouble(9),rs.getDate(10));
                LoaiThuoc_DAO ltDao =new LoaiThuoc_DAO();
                Loaithuoc lt = ltDao.TimKiemMa(rs.getString(2));
                thuoc.setLoaiThuoc(lt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return thuoc;
    }

    public Thuoc TimKiemTen(String ten) {
        Thuoc thuoc = null;
        try {
            PreparedStatement stmt = con.prepareStatement("select * from Thuoc where TENTHUOC = ?");
            stmt.setString(1, ten);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                thuoc = new Thuoc(rs.getString(1), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8),rs.getDouble(9),rs.getDate(10));
                LoaiThuoc_DAO ltDao =new LoaiThuoc_DAO();
                Loaithuoc lt = ltDao.TimKiemMa(rs.getString(2));
                thuoc.setLoaiThuoc(lt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return thuoc;
    }
    public List<Thuoc> TimKiemMaL(String maL) {
        List<Thuoc> ls =new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("select * from Thuoc where MALOAI = ?");
            stmt.setString(1, maL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Thuoc thuoc = new Thuoc(rs.getString(1), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8),rs.getDouble(9),rs.getDate(10));
                LoaiThuoc_DAO ltDao =new LoaiThuoc_DAO();
                Loaithuoc lt = ltDao.TimKiemMa(rs.getString(2));
                thuoc.setLoaiThuoc(lt);
                ls.add(thuoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }
    public boolean updateSoLuong(String maT,int soLuong) {
        String sql = "update THUOC set SOLUONG = ? where MAT = ?";
        try {
            System.out.println("OK1");
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,soLuong);
            stmt.setString(2,maT);

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
    public String getMa() throws SQLException {
        String ma = "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("SELECT MAX([MAT]) FROM [dbo].[Thuoc]");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            ma = rs.getString(1);
        }
        return ma;
    }
}
