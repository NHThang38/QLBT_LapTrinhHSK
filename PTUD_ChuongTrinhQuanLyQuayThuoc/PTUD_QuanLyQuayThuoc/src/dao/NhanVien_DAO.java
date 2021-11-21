package dao;

import connection.MyConnection;
import entity.ChucVu;
import entity.KhachHang;
import entity.NhanVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVien_DAO {
    private Connection con;

    public NhanVien_DAO() {
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
    public List<NhanVien> getLS() {
        List<NhanVien> ds = new ArrayList<>();
        ChucVu_DAO cvDao;
        try {
            //PreparedStatement stmt = con.prepareStatement("select * from KhachHang");
            ResultSet rs = getResultSet("select_NV");
            while(rs.next()){
                NhanVien nv =new NhanVien(rs.getString(1),rs.getString(3),rs.getString(4),rs.getDate(5),
                        rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9),
                        rs.getDate(10));
                cvDao = new ChucVu_DAO();
                System.out.println(rs.getString(2));
                ChucVu cv = null;
                cv = cvDao.TimKiemMa(rs.getString(2));
                System.out.println(cv);
                nv.setChucVu(cv);
                ds.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public boolean addNhanVien(NhanVien nv) {
        try {
            PreparedStatement nvAdd = con.prepareStatement("INSERT INTO NhanVien ([MACV],[TENNV],[GIOITINH],[NGAYSINH],[EMAIL]," +
                    "[DIENTHOAI],[CMND],[DIACHI],[NGAYVAOLAM]) VALUES(?,?,?,?,?,?,?,?,?)");
            nvAdd.setString(1,nv.getChucVu().getMaChucVu());
            nvAdd.setString(2,nv.getTenNV());
            nvAdd.setString(3,nv.getGioiTinh());
            nvAdd.setDate(4,nv.getNgaySinh());
            nvAdd.setString(5,nv.getEmail());
            nvAdd.setString(6,nv.getDienThoai());
            nvAdd.setInt(7,nv.getCMND());
            nvAdd.setString(8,nv.getDiaChi());
            nvAdd.setDate(9,nv.getNgayVaoLam());

            int n = nvAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteNV(String maNV) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from NhanVien where MANV = ?");
            stmt.setString(1, maNV);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateNhanVien(String maNV, NhanVien nv) {
        String sql = "update NhanVien set MACV = ? ,TENNV = ?, "
                + "GIOITINH = ?,NGAYSINH = ? ,EMAIL = ?,DIENTHOAI = ? ,CMND = ? ,DIACHI = ?,NGAYVAOLAM = ?  where MANV = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nv.getChucVu().getMaChucVu());
            stmt.setString(2,nv.getTenNV());
            stmt.setString(3,nv.getGioiTinh());
            stmt.setDate(4,nv.getNgaySinh());
            stmt.setString(5,nv.getEmail());
            stmt.setString(6,nv.getDienThoai());
            stmt.setInt(7,nv.getCMND());
            stmt.setString(8,nv.getDiaChi());
            stmt.setDate(9,nv.getNgayVaoLam());
            stmt.setString(10, maNV);

            int n = stmt.executeUpdate();
            if(n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public NhanVien TimKiemMa(String ma){
        NhanVien nv = null;
        ChucVu_DAO cvDao;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from NHANVIEN where MANV = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                nv = new NhanVien(rs.getString(1),rs.getString(3),rs.getString(4),rs.getDate(5),
                        rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9),
                        rs.getDate(10));
                cvDao = new ChucVu_DAO();
                System.out.println(rs.getString(2));
                ChucVu cv = null;
                cv = cvDao.TimKiemMa(rs.getString(2));
                System.out.println(cv);
                nv.setChucVu(cv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return nv;
    }
    public NhanVien TimKiemTen(String ten){
        NhanVien nv = null;
        ChucVu_DAO cvDao;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from NHANVIEN where TENNV = ?");
            stmt.setString(1,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                nv = new NhanVien(rs.getString(1),rs.getString(3),rs.getString(4),rs.getDate(5),
                        rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9),
                        rs.getDate(10));
                cvDao = new ChucVu_DAO();
                System.out.println(rs.getString(2));
                ChucVu cv = null;
                cv = cvDao.TimKiemMa(rs.getString(2));
                System.out.println(cv);
                nv.setChucVu(cv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return nv;
    }
    public NhanVien TimKiemCM(int cm){
        NhanVien nv = null;
        ChucVu_DAO cvDao;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from NHANVIEN where CMND = ?");
            stmt.setInt(1,cm);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                nv = new NhanVien(rs.getString(1),rs.getString(3),rs.getString(4),rs.getDate(5),
                        rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9),
                        rs.getDate(10));
                cvDao = new ChucVu_DAO();
                System.out.println(rs.getString(2));
                ChucVu cv = null;
                cv = cvDao.TimKiemMa(rs.getString(2));
                System.out.println(cv);
                nv.setChucVu(cv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return nv;
    }
    public NhanVien TimKiemSDT1(String soDT){
        String so = "%".concat(soDT).concat("%");
        System.out.println(so);
        NhanVien nv = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from NHANVIEN where DIENTHOAI LIKE ?");
            stmt.setString(1,so);
            ResultSet rs = stmt.executeQuery();
            ChucVu_DAO cvDao;
            while(rs.next()) {
                nv =new NhanVien(rs.getString(1),rs.getString(3),rs.getString(4),rs.getDate(5),
                        rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9),
                        rs.getDate(10));
                cvDao = new ChucVu_DAO();
                System.out.println(rs.getString(2));
                ChucVu cv = null;
                cv = cvDao.TimKiemMa(rs.getString(2));
                System.out.println(cv);
                nv.setChucVu(cv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return nv;
    }
}
