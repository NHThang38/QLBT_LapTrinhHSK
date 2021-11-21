package gui;

import com.toedter.calendar.JDateChooser;
import dao.CT_HDBH_DAO;
import dao.HoaDonBanHang_DAO;
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
import tableModel.Thuoc_TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class BanHang_Form extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5430744024866983777L;
	JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblTienThuoc,lblTienNhan,lblTienThue,lblTienThoi,lblTongTien,lblTim,
            lblKhachHang,lblMaHD,lblST,lblNgayLap,lblNhanVien,lblDiaChi,lblGioiTinh;
    JTextField txttienThuoc,txtTienNhan,txtThue,txtTienThoi,txtTong,txtTim,
            txtKhachHang,txtSDT,txtMaHD,txtNhanVien,txtDiaChi;
    JButton btnHoaDonMoi,btnIn,btnThoat,btnTim,btnKhachHang;
    JComboBox cbcTim,cbcGT;
    JDateChooser NgayLap;
    String mahd = "";
    NhanVien nv;
    KhachHang kh;
    BanHang_Form bh;
    public BanHang_Form(){

    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnNorth_C = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("LẬP HÓA ĐƠN BÁN HÀNG");
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
        b2.add(btnKhachHang = new JButton("Khách Hàng"));
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblKhachHang = new JLabel("Khách Hàng: "));
        b3.add(txtKhachHang = new JTextField());
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblGioiTinh = new JLabel("Giới Tính: "));
        b3.add(cbcGT = new JComboBox<>());
        cbcGT.addItem("Nam");
        cbcGT.addItem("Nữ");
        cbcGT.setPreferredSize(new Dimension(100,20));
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
        lblKhachHang.setPreferredSize(lblNhanVien.getPreferredSize());
        lblST.setPreferredSize(lblNhanVien.getPreferredSize());

        JPanel pnThuoc = new JPanel();
        pnThuoc.setBorder(new TitledBorder("Danh Sách Thuốc"));
        pnThuoc.setLayout(new BorderLayout());
        JPanel pnThuoc_N = new JPanel();
        JPanel pnthuoc_C = new JPanel();
        Box t = Box.createHorizontalBox();
        t.setPreferredSize(new Dimension(400,25));
        t.add(lblTim = new JLabel("Tìm Thuốc Theo: "));
        t.add(cbcTim = new JComboBox<>());
        cbcTim.setPreferredSize(new Dimension(100,20));
        t.add(Box.createHorizontalStrut(10));
        t.add(txtTim = new JTextField());
        t.add(btnTim = new JButton("Tìm Thuốc"));

        List<Thuoc> ls = new ArrayList<>();
        Thuoc_DAO thuocDao = new Thuoc_DAO();
        Thuoc_TableModel model = new Thuoc_TableModel(thuocDao.getLS());
        JTable table = new JTable();
        table.setModel(model);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateTime = (String) formatter.format(NgayLap.getDate());

        JScrollPane sc = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(600,170));
        pnThuoc_N.add(t);
        pnthuoc_C.add(sc);

        pnThuoc.add(pnThuoc_N,BorderLayout.NORTH);
        pnThuoc.add(pnthuoc_C,BorderLayout.CENTER);
        pnThongTin.add(b);

        pnNorth.add(pnThuoc,BorderLayout.EAST);
        pnNorth.add(pnThongTin,BorderLayout.CENTER);
        txtMaHD.setEditable(false);
        if(nv != null){
            txtNhanVien.setEditable(false);
            txtNhanVien.setText(nv.getTenNV());
        }

        //pnCenter
        pnCenter = new JPanel();
        pnCenter.setBorder(new TitledBorder("Danh Sách Thuốc Đã Chọn"));
        List<CT_HoaDonBanHang> ls1 = new ArrayList<>();
        CT_HDBH_TableModel model1 = new CT_HDBH_TableModel(ls1);
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
        s1.add(lblTienNhan = new JLabel("Tiền Nhận: "));
        s1.add(txtTienNhan = new JTextField());
        s.add(Box.createVerticalStrut(7));

        s.add(s2 = Box.createHorizontalBox());
        s2.add(lblTienThue = new JLabel("Thuế GTGT: "));
        s2.add(txtThue = new JTextField());
        txtThue.setEditable(false);
        s2.add(Box.createHorizontalStrut(55));
        s2.add(lblTienThoi = new JLabel("Tiền Trả Lại: "));
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


        List<CT_HoaDonBanHang> list = new ArrayList<>();
        table.addMouseListener(new MouseListener() {
            double tongTien = 0;
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();

                if(r != -1){
                    if(kh != null) {
                        int sl = Integer.parseInt(JOptionPane.showInputDialog(null, "Nhập số lượng sản phẩm muốn mua: ",
                                "Nhập số lượng", JOptionPane.INFORMATION_MESSAGE));
                        HoaDonBanHang_DAO hdDao = new HoaDonBanHang_DAO();
                        HoaDonBanHang hd = null;
                        try {
                            hd = hdDao.TimKiemMa(hdDao.getMa());
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        if (sl > 0) {
                        	if(sl <= thuocDao.TimKiemMa(table.getValueAt(r, 0).toString()).getSoLuong()) {
                                try {
                                    mahd = hdDao.getMa();
                                    Thuoc t = thuocDao.TimKiemMa(table.getValueAt(r, 0).toString());
                                    CT_HoaDonBanHang ct = new CT_HoaDonBanHang(sl, t.getGiaBan());
                                    hd.setMaHDBH(mahd);
                                    ct.setHoaDonBanHang(hd);
                                    ct.setThuoc(t);
                                    CT_HDBH_DAO ctDao = new CT_HDBH_DAO();
                                    if(ctDao.TimKiemMaHD(mahd,t.getMaThuoc()) != null){
                                        if (thuocDao.updateSoLuong(table.getValueAt(r, 0).toString(), t.getSoLuong() - sl)) {
                                            table.setModel(new Thuoc_TableModel(thuocDao.getLS()));
                                            ctDao.updateSoLuong(mahd,t.getMaThuoc(),ctDao.TimKiemMaT(t.getMaThuoc()).getSoLuong()+sl);
//                                            list.add(ct);
                                            table1.setModel(new CT_HDBH_TableModel(ctDao.TimKiemHD(mahd)));
                                            tongTien += (double) ctDao.TimKiemMaT(t.getMaThuoc()).getSoLuong() * ct.getDonGia();
                                            txttienThuoc.setText(String.valueOf(tongTien));
                                            txtThue.setText("5%");
                                            txtTong.setText(String.valueOf(tongTien + tongTien * 0.05));
//                                        hdDao.TimKiemMa(mahd).setTongTien(tongTien + tongTien*0.05);
                                            hdDao.updateTongtien(mahd, tongTien + tongTien * 0.05);
                                        }
                                    }else {
                                        if (ctDao.addCTHDBH(ct)) {
                                            if (thuocDao.updateSoLuong(table.getValueAt(r, 0).toString(), t.getSoLuong() - sl)) {
                                                table.setModel(new Thuoc_TableModel(thuocDao.getLS()));
                                                list.add(ct);
                                                table1.setModel(new CT_HDBH_TableModel(list));
                                                tongTien += (double) ct.getSoLuong() * ct.getDonGia();
                                                txttienThuoc.setText(String.valueOf(tongTien));
                                                txtThue.setText("5%");
                                                txtTong.setText(String.valueOf(tongTien + tongTien * 0.05));
//                                        hdDao.TimKiemMa(mahd).setTongTien(tongTien + tongTien*0.05);
                                                hdDao.updateTongtien(mahd, tongTien + tongTien * 0.05);
                                            }
                                        }
                                    }
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }

                        	 }else {
                             	JOptionPane.showMessageDialog(null, "Không đủ số lượng!");
                             }
                        } else {
                            JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Bạn chưa chọn khách hàng!");
                    }
//                    CT_HoaDonBanHang cthd = new CT_HoaDonBanHang();
                }
                }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        btnKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DS_KhachHang_Form ds = new DS_KhachHang_Form();
                ds.setVisible(true);
                ds.banhang = bh;
            }
        });
        txtTienNhan.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(Double.parseDouble(txtTienNhan.getText().trim()) < Double.parseDouble(txtTong.getText().trim())){
                    JOptionPane.showMessageDialog(null,"Số tiền nhận phải lớn hơn số tiền háo đơn");
                }else{
                    txtTienThoi.setText(String.valueOf(Double.parseDouble(txtTienNhan.getText().trim()) - Double.parseDouble(txtTong.getText().trim())));
                }
            }
        });
        btnHoaDonMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txttienThuoc.setText("");
                txtTong.setText("");
                txtKhachHang.setText("");
                txtDiaChi.setText("");
                txtSDT.setText("");
                List<CT_HoaDonBanHang> ls = new ArrayList<>();
                table1.setModel(new CT_HDBH_TableModel(ls));
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
					String sql = "SELECT hd.MaHDBH,hd.NGAYLAPHD,nv.TENNV,kh.TENKH,kh.DIACHI,kh.DIENTHOAI,kh.EMAIL,kh.GIOITINH,\r\n" + 
							"t.TENTHUOC,lt.TENLOAI,t.CONGDUNG,t.DONVITINH,ct.SOLUONG,t.GIABAN,ct.SOLUONG*t.GIABAN AS ThanhTien\r\n" + 
							"FROM [dbo].[HoaDonBanHang] hd JOIN [dbo].[CT_HoaDonBanHang] ct ON hd.MaHDBH = ct.MaHDBH\r\n" + 
							"JOIN [dbo].[NhanVien] nv ON hd.MANV = nv.MANV\r\n" + 
							"JOIN [dbo].[KhachHang] kh ON hd.MAKH = kh.MAKH\r\n" + 
							"JOIN [dbo].[Thuoc] t ON ct.MAT = t.MAT\r\n" + 
							"JOIN [dbo].[LoaiThuoc] lt ON t.MALOAI = lt.MALOAI\r\n" + 
							"WHERE hd.MaHDBH = "+"'"+mahd+"'";
					JasperDesign jdesign = JRXmlLoader.load("D:\\PTUD_ChuongTrinhQuanLyQuayThuoc\\PTUD_QuanLyQuayThuoc\\src\\Report\\HoaDonBanHang.jrxml");
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

}
