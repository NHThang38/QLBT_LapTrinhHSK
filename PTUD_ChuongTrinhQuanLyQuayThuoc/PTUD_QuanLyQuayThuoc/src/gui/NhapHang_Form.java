package gui;

import com.toedter.calendar.JDateChooser;
import dao.CT_HDNH_DAO;
import dao.HoaDonNhapHang_DAO;
import dao.LoaiThuoc_DAO;
import dao.Thuoc_DAO;
import entity.*;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import tableModel.CT_HDBH_TableModel;
import tableModel.CT_HDNH_TableModel;
import tableModel.Thuoc_TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NhapHang_Form extends JPanel {
    JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblTienThuoc,lblTienNhan,lblTienThue,lblTienThoi,lblTongTien,
            lblMaHD,lblST,lblNgayLap,lblNhanVien,lblDiaChi,lblNhaCC,lblEmail,
            lblMa,lblTen,lblLoaiThuoc,lblCongDung,lblThanhPhan,lblXuatXu,lblSoLuong,lblGiaBan,lblHanDung,lblDonViTinh;
    JTextField txttienThuoc,txtTienNhan,txtThue,txtTienThoi,txtTong
            ,txtSDT,txtMaHD,txtNhanVien,txtDiaChi,txtNhaCC,txtMail
            ,txtCongDung,txtThanhPhan,txtXuatXu,txtSoLuong,txtGia,txtMa,txtTen;
    JButton btnHoaDonMoi,btnIn,btnThoat,btnNhaCC,btnNhapthuoc;
    JComboBox cbcLoai,cbcDonVi;
    JDateChooser NgayLap,NgayHetHan;
    String mahd = "";
    NhanVien nv;
    NhaCungCap ncc;
    NhapHang_Form nh;
    public NhapHang_Form(){

    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnNorth_C = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("LẬP HÓA ĐƠN NHẬP HÀNG");
        lblTieuDe.setFont(new Font("arial", Font.BOLD,18));
        lblTieuDe.setForeground(Color.RED);
        pnTieuDe.add(lblTieuDe);
        pnNorth.add(pnTieuDe,BorderLayout.NORTH);

        JPanel pnThongTin = new JPanel();
        pnThongTin.setBorder(new TitledBorder("Thông Tin Hóa Đơn"));
        Box b,b1,b2,b3,b4;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(500,210));
        b.add(Box.createVerticalStrut(30));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMaHD = new JLabel("Mã HD:"));
        b1.add(txtMaHD = new JTextField());
        txtMaHD.setEditable(false);
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblNgayLap = new JLabel("Ngày Lập HD:"));
        NgayLap = new JDateChooser();
//        lblNgayVao.setSize(new Dimension(30,20));
//        lblNgayVao.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        NgayLap.setDateFormatString("yyyy-MM-dd");
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = Date.valueOf(LocalDate.now());
            System.out.println("Date: " + date);
            NgayLap.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        b1.add(NgayLap);
        b.add(Box.createVerticalStrut(10));

        Box b11;
        b.add(b11 = Box.createHorizontalBox());
        b11.add(lblNhanVien = new JLabel("Nhân Viên Lập HD: "));
        b11.add(txtNhanVien = new JTextField());
        b.add(Box.createVerticalStrut(10));
        b.add(b2 = Box.createHorizontalBox());
        b2.add(btnNhaCC = new JButton("Nhà Cung Cấp"));
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblNhaCC = new JLabel("Nhà Cung Cấp: "));
        b3.add(txtNhaCC = new JTextField());
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblEmail = new JLabel("Email: "));
        b3.add(txtMail = new JTextField());

        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(lblST = new JLabel("Điện Thoại: "));
        b4.add(txtSDT = new JTextField());
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lblDiaChi = new JLabel("Địa Chỉ: "));
        b4.add(txtDiaChi = new JTextField());
        b.add(Box.createVerticalStrut(10));

        lblMaHD.setPreferredSize(lblNhanVien.getPreferredSize());
        lblNgayLap.setPreferredSize(lblNhanVien.getPreferredSize());
        lblNhaCC.setPreferredSize(lblNhanVien.getPreferredSize());
        lblST.setPreferredSize(lblNhanVien.getPreferredSize());

        JPanel pnThuoc = new JPanel();
        pnThuoc.setBorder(new TitledBorder("Nhập Thuốc"));
        pnThuoc.setLayout(new BorderLayout());
        Box bb,bb1,bb2,bb3,bb4,bb5,bb6;
        bb = Box.createVerticalBox();
        bb.setPreferredSize(new Dimension(650,170));

        bb.add(bb1 = Box.createHorizontalBox());
        bb1.add(lblMa = new JLabel("Mã Thuốc: "));
        bb1.add(txtMa = new JTextField(30));
        txtMa.setEditable(false);
        bb1.add(Box.createHorizontalStrut(20));
        bb1.add(lblTen = new JLabel("Tên Thuốc:    "));
        bb1.add(txtTen = new JTextField(30));
        bb.add(Box.createVerticalStrut(10));

        bb.add(bb2 = Box.createHorizontalBox());
        bb2.add(lblLoaiThuoc = new JLabel("Loại Thuốc: "));
        cbcLoai = new JComboBox<>();
        LoaiThuoc_DAO lDao = new LoaiThuoc_DAO();
        for (Loaithuoc l: lDao.getLS()) {
            cbcLoai.addItem(l.getTenThuoc());
        }
        cbcLoai.setPreferredSize(new Dimension(300,20));
        bb2.add(cbcLoai);
        bb2.add(Box.createHorizontalStrut(20));
        bb2.add(lblCongDung = new JLabel("Công Dụng:    "));
        bb2.add(txtCongDung = new JTextField(30));
        bb.add(Box.createVerticalStrut(10));

        bb.add(bb3 = Box.createHorizontalBox());
        bb3.add(lblThanhPhan = new JLabel("Thành Phần: "));
        bb3.add(txtThanhPhan = new JTextField(30));
        bb3.add(Box.createHorizontalStrut(20));
        bb3.add(lblXuatXu = new JLabel("Xuất Xứ:    "));
        bb3.add(txtXuatXu = new JTextField(30));
        bb.add(Box.createVerticalStrut(10));

        bb.add(bb4 = Box.createHorizontalBox());
        bb4.add(lblSoLuong = new JLabel("Số Lượng: "));
        bb4.add(txtSoLuong = new JTextField(30));
        bb4.add(Box.createHorizontalStrut(20));
        bb4.add(lblGiaBan = new JLabel("Giá Bán:    "));
        bb4.add(txtGia = new JTextField(30));
        bb.add(Box.createVerticalStrut(10));

        bb.add(bb5 = Box.createHorizontalBox());
        bb5.add(lblDonViTinh = new JLabel("Đơn Vị Tính: "));
        cbcDonVi = new JComboBox<>();
//        ChucVu_DAO CvDao = new ChucVu_DAO();
        cbcDonVi.addItem("Viên");
        cbcDonVi.addItem("Vỉ");
        cbcDonVi.addItem("Tuýp");
        cbcDonVi.addItem("Hộp");
        cbcDonVi.setPreferredSize(new Dimension(230,20));
        bb5.add(cbcDonVi);
        bb5.add(Box.createHorizontalStrut(20));
        bb5.add(lblHanDung = new JLabel("Hạn Sử Dụng:    "));
        NgayHetHan = new JDateChooser();
//        lblNgayVao.setSize(new Dimension(30,20));
//        lblNgayVao.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        NgayHetHan.setDateFormatString("yyyy-MM-dd");
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = Date.valueOf(LocalDate.now());
            System.out.println("Date: " + date);
            NgayHetHan.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        bb5.add(NgayHetHan);
        bb.add(Box.createVerticalStrut(10));

        bb.add(bb6 = Box.createHorizontalBox());
        bb6.add(btnNhapthuoc = new JButton("Nhập Thuốc"));

        lblMa.setPreferredSize(lblHanDung.getPreferredSize());
        lblTen.setPreferredSize(lblHanDung.getPreferredSize());
        lblCongDung.setPreferredSize(lblHanDung.getPreferredSize());
        lblDonViTinh.setPreferredSize(lblHanDung.getPreferredSize());
        lblXuatXu.setPreferredSize(lblHanDung.getPreferredSize());
        lblSoLuong.setPreferredSize(lblHanDung.getPreferredSize());
        lblLoaiThuoc.setPreferredSize(lblHanDung.getPreferredSize());
        lblThanhPhan.setPreferredSize(lblHanDung.getPreferredSize());
        lblGiaBan.setPreferredSize(lblHanDung.getPreferredSize());


        pnThuoc.add(bb);
        pnThongTin.add(b);

        pnNorth.add(pnThuoc,BorderLayout.EAST);
        pnNorth.add(pnThongTin,BorderLayout.CENTER);

        //pnCenter
        pnCenter = new JPanel();
        pnCenter.setBorder(new TitledBorder("Danh Sách Thuốc Đã Nhập"));
        List<Thuoc> ls1 = new ArrayList<>();
        Thuoc_TableModel model1 = new Thuoc_TableModel(ls1);
        JTable table1 = new JTable();
        table1.setModel(model1);
        JScrollPane sc1 = new JScrollPane(table1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc1.setPreferredSize(new Dimension(850,140));

        pnCenter.add(sc1);

        //pnSouth
        pnSouth = new JPanel();
        pnSouth.setLayout(new BorderLayout());
        Box s,s1,s2,s3;
        s = Box.createVerticalBox();
        s.setPreferredSize(new Dimension(1000,100));
        s.add(s1 = Box.createHorizontalBox());
        s1.add(lblTienThuoc = new JLabel("Tổng Tiền Thuốc: "));
        s1.add(txttienThuoc = new JTextField());
        txttienThuoc.setEditable(false);
        s1.add(Box.createHorizontalStrut(50));
        s1.add(lblTienNhan = new JLabel("          "));
        s1.add(txtTienNhan = new JTextField());
        txtTienNhan.setEditable(false);
        s.add(Box.createVerticalStrut(7));

        s.add(s2 = Box.createHorizontalBox());
        s2.add(lblTienThue = new JLabel("Thuế GTGT: "));
        s2.add(txtThue = new JTextField());
        txtThue.setEditable(false);
        s2.add(Box.createHorizontalStrut(50));
        s2.add(lblTienThoi = new JLabel("           "));
        s2.add(txtTienThoi = new JTextField());
        txtTienThoi.setEditable(false);
        s.add(Box.createVerticalStrut(10));

        s.add(s3 = Box.createHorizontalBox());
        s3.add(lblTongTien = new JLabel("Tổng Tiền HD: "));
        s3.add(txtTong = new JTextField());
        txtTong.setEditable(false);
        s3.add(Box.createHorizontalStrut(50));
        s3.add(btnHoaDonMoi = new JButton("Tạo Hóa Đơn Mới"));
        s.add(Box.createVerticalStrut(7));

        Box d;
        d = Box.createVerticalBox();
        d.setPreferredSize(new Dimension(100,30));
        d.add(btnIn = new JButton("In HD"));
        d.add(Box.createVerticalStrut(15));
        d.add(btnThoat = new JButton("Thoát"));

        pnSouth.add(s,BorderLayout.WEST);
        pnSouth.add(d,BorderLayout.EAST);
        pnSouth.setBorder(new TitledBorder("Chi tiết hóa đơn"));

        lblTienNhan.setPreferredSize(lblTienThuoc.getPreferredSize());
        lblTienThoi.setPreferredSize(lblTienThuoc.getPreferredSize());
        lblTienThue.setPreferredSize(lblTienThuoc.getPreferredSize());
        lblTongTien.setPreferredSize(lblTienThuoc.getPreferredSize());

        cbcLoai.setPreferredSize(new Dimension(260,20));
        cbcDonVi.setPreferredSize(new Dimension(225,20));

        if(nv != null){
            txtNhanVien.setEditable(false);
            txtNhanVien.setText(nv.getTenNV());
        }
        btnNhaCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DS_NhaCungCap_Form ds = new DS_NhaCungCap_Form();
                ds.setVisible(true);
                ds.nhapHang = nh;
            }
        });
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        LoaiThuoc_DAO ltDao = new LoaiThuoc_DAO();
        Thuoc_DAO thuocDao = new Thuoc_DAO();
        CT_HDNH_DAO ctDao = new CT_HDNH_DAO();
        HoaDonNhapHang_DAO hdDao = new HoaDonNhapHang_DAO();

        btnNhapthuoc.addActionListener(new ActionListener() {
            double tongTien = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                String dateTime = (String) formatter.format(NgayHetHan.getDate());
                if(!txtTen.getText().trim().equals("")){
                    Thuoc thuoc = new Thuoc(txtMa.getText(),txtTen.getText(),txtThanhPhan.getText(),txtCongDung.getText(),cbcDonVi.getSelectedItem().toString(),
                            txtXuatXu.getText(),Integer.parseInt(txtSoLuong.getText().trim()),Double.parseDouble(txtGia.getText().trim()),
                            Date.valueOf(dateTime));
                    Loaithuoc lt = ltDao.TimKiemTen(cbcLoai.getSelectedItem().toString());
                    thuoc.setLoaiThuoc(lt);
                    if(thuocDao.addThuoc(thuoc)){
                        try {
                            mahd = hdDao.getMa();
                            String mat = thuocDao.getMa();
                            Thuoc t = thuocDao.TimKiemMa(mat);
                            t.setLoaiThuoc(lt);
                            CT_HoaDonNhapHang ct = new CT_HoaDonNhapHang(Integer.parseInt(txtSoLuong.getText().trim()),Double.parseDouble(txtGia.getText().trim()));

                            HoaDonNhapHang hd = hdDao.TimKiemMa(mahd);
                            ct.setHoaDonNhapHang(hd);
                            ct.setThuoc(t);
                            System.out.println(t);
                            System.out.println(hd);
                            System.out.println(ct);
                            if(ctDao.addCTHDNH(ct)){
                                table1.setModel(new CT_HDNH_TableModel(ctDao.TimKiemHD(mahd)));
                                tongTien += (double) ct.getSoLuong() * ct.getDonGia();
                                txttienThuoc.setText(String.valueOf(tongTien));
                                txtThue.setText("5%");
                                txtTong.setText(String.valueOf(tongTien + tongTien * 0.05));
                                hdDao.updateTongtien(mahd, tongTien + tongTien * 0.05);
                                clearText();
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Bạn chưa nhập tên thuốc!");
                }
            }
        });

        btnHoaDonMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txttienThuoc.setText("");
                txtTong.setText("");
                txtNhaCC.setText("");
                txtDiaChi.setText("");
                txtSDT.setText("");
                txtMail.setText("");
                List<CT_HoaDonNhapHang> ls = new ArrayList<>();
                table1.setModel(new CT_HDNH_TableModel(ls));
            }
        });
        btnIn.addActionListener(new ActionListener() {
        	private JasperPrint jprint;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyQuayThuoc;instance=SQLEXPRESS;user=sa;password=hung");
//					Connection con = MyConnection.getInstance().getConnection();
					String sql = "SELECT hd.MaHDNH,hd.NGAYLAPHD,nv.TENNV,ncc.TENNHACC,ncc.SODT,ncc.EMAIL,ncc.DIACHI,t.TENTHUOC,t.CONGDUNG,t.DONVITINH,t.GIABAN,t.NGAYHETHAN,\r\n" + 
							"ct.SOLUONG,t.THANHPHAN,lt.TENLOAI\r\n" + 
							"FROM [dbo].[HoaDonNhapHang] hd JOIN [dbo].[CT_HoaDonNhapHang] ct  ON hd.MaHDNH = ct.MaHDNH\r\n" + 
							"JOIN [dbo].[NhanVien] nv ON hd.MANV = nv.MANV\r\n" + 
							"JOIN [dbo].[NhaCungCap] ncc ON hd.MANHACC = ncc.MANHACC\r\n" + 
							"JOIN [dbo].[Thuoc] t ON ct.MAT = t.MAT\r\n" + 
							"JOIN [dbo].[LoaiThuoc] lt ON t.MALOAI = lt.MALOAI\r\n" + 
							"WHERE hd.MaHDNH = "+"'"+mahd+"'";
					JasperDesign jdesign = JRXmlLoader.load("D:\\PTUD_ChuongTrinhQuanLyQuayThuoc\\PTUD_QuanLyQuayThuoc\\src\\Report\\HoaDonNhapHang.jrxml");
					JRDesignQuery updateQuery = new JRDesignQuery();
					updateQuery.setText(sql);
					jdesign.setQuery(updateQuery);

					Map<String, Object> parameters = new HashMap<String, Object>();
					JasperReport jreport = JasperCompileManager.compileReport(jdesign);
					JasperPrint jpasperPrint = JasperFillManager.fillReport(jreport, parameters,con);

					JasperViewer.viewReport(jpasperPrint,false);
				}catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, e2);
				}

			}
		});
        this.setLayout(new BorderLayout());
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);
    }
    public void clearText(){
        txtMa.setText("");
        txtTen.setText("");
        txtCongDung.setText("");
        txtGia.setText("");
        txtSoLuong.setText("");
        txtThanhPhan.setText("");
        txtXuatXu.setText("");
        cbcDonVi.setSelectedIndex(0);
        txtTen.requestFocus();
    }
}
