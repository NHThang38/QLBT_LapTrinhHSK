package gui;

import entity.NhanVien;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class GD_Chinh extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pnCneter;
    JLabel lblGiaoVien,lblTenGV,lblThanhVien,lblSV1,lbl1,lblSV2,lbl2,lblSV3;
    NhanVien nv;
    public GD_Chinh(){

        doShow();
    }
    public void doShow(){
        setSize(1200,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Quản Lý Quầy Thuốc");

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        //Menu chương trình
//        JPanel pnNorth = new JPanel();
        Font ftmn = new Font("arial",Font.BOLD,16);
        JMenuBar menuBar = new JMenuBar();

        JMenu mnTrangChu = new JMenu("Trang Chủ");
        mnTrangChu.setIcon(new ImageIcon(getClass().getResource("/images/house1.png")));
        mnTrangChu.setFont(ftmn);
        mnTrangChu.isSelected();


        JMenu mnHeThong = new JMenu("Hệ Thống");
        mnHeThong.setIcon(new ImageIcon(getClass().getResource("/images/system1.png")));
        mnHeThong.setFont(ftmn);
        JMenuItem mnThoat = new JMenuItem("Thoát");
        mnHeThong.add(mnThoat);
        mnThoat.setPreferredSize(new Dimension(150,30));

        JMenu mnQuanLy = new JMenu("Quản Lý");
        mnQuanLy.setIcon(new ImageIcon(getClass().getResource("/images/quanly1.png")));
        mnQuanLy.setFont(ftmn);
        JMenuItem mnThuoc,mnKhachHang,mnNhanVien,mnNhaCC,mnLoaiThuoc;
        mnQuanLy.add(mnThuoc = new JMenuItem("Thuốc"));
        mnQuanLy.add(mnKhachHang = new JMenuItem("Khách Hàng"));
        mnQuanLy.add(mnNhanVien = new JMenuItem("Nhân Viên"));
        mnQuanLy.add(mnNhaCC = new JMenuItem("Nhà Cung Cấp"));
        mnQuanLy.add(mnLoaiThuoc = new JMenuItem("Loại Thuốc"));

        mnThuoc.setPreferredSize(new Dimension(150,30));
        mnKhachHang.setPreferredSize(new Dimension(150,30));
        mnNhaCC.setPreferredSize(new Dimension(150,30));
        mnNhanVien.setPreferredSize(new Dimension(150,30));
        mnLoaiThuoc.setPreferredSize(new Dimension(150,30));






        JMenu mnXuLy = new JMenu("Xử Lý");
        mnXuLy.setIcon(new ImageIcon(getClass().getResource("/images/no-touch1.png")));
        mnXuLy.setFont(ftmn);
        JMenuItem mnBanThuoc,mnNhapThuoc;
        mnXuLy.add(mnBanThuoc = new JMenuItem("Bán Thuốc"));
        mnXuLy.add(mnNhapThuoc = new JMenuItem("Nhập Thuốc"));
        mnNhapThuoc.setPreferredSize(new Dimension(150,30));
        mnBanThuoc.setPreferredSize(new Dimension(150,30));


        JMenu mnThongKe = new JMenu("Thống Kê");
        JMenuItem mnThongke1,mnThongke2;
        mnThongKe.add(mnThongke1 = new JMenuItem("Thuốc Bán Trong Ngày"));
        mnThongKe.add(mnThongke2 = new JMenuItem("Thuốc Còn Trong Cửa Hàng"));
        mnThongke1.setPreferredSize(new Dimension(200,30));
        mnThongke2.setPreferredSize(new Dimension(200,30));
        mnThongKe.setIcon(new ImageIcon(getClass().getResource("/images/stats1.png")));
        mnThongKe.setFont(ftmn);


        JMenu mnTimKiem = new JMenu("Tìm Kiếm");
        mnTimKiem.setIcon(new ImageIcon(getClass().getResource("/images/loupe1.png")));
        mnTimKiem.setFont(ftmn);
        JMenuItem mnTimThuoc,mnTimKH,mnTimNV,mnTimNCC;
        mnTimKiem.add(mnTimThuoc = new JMenuItem("Thuốc"));
        mnTimKiem.add(mnTimKH = new JMenuItem("Khách Hàng"));
        mnTimKiem.add(mnTimNV = new JMenuItem("Nhân Viên"));
        mnTimKiem.add(mnTimNCC = new JMenuItem("Nhà Cung Cấp"));

        mnTimThuoc.setPreferredSize(new Dimension(150,30));
        mnTimKH.setPreferredSize(new Dimension(150,30));
        mnTimNCC.setPreferredSize(new Dimension(150,30));
        mnTimNV.setPreferredSize(new Dimension(150,30));


        JMenu mnTroGiup = new JMenu("Trợ Giúp");
        mnTroGiup.setIcon(new ImageIcon(getClass().getResource("/images/information1.png")));
        mnTroGiup.setFont(ftmn);


        menuBar.add(mnTrangChu);
        menuBar.add(mnHeThong);
        menuBar.add(mnQuanLy);
        menuBar.add(mnXuLy);
        menuBar.add(mnThongKe);
        menuBar.add(mnTimKiem);
        menuBar.add(mnTroGiup);

        //PnCneter
        pnCneter = new JPanel();
        JPanel pnCenterN = new JPanel();
        JPanel pnCenterC = new JPanel();
        JPanel pnCenterS = new JPanel();
        pnCneter.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("CHƯƠNG TRÌNH QUẢN LÝ QUẦY THUỐC");
        lblTieuDe.setFont(new Font("arial",Font.BOLD,24));
        lblTieuDe.setForeground(Color.RED);
        pnCenterN.setPreferredSize(new Dimension(1000,60));

        JLabel lbImage = new JLabel();
        lbImage.setIcon(new ImageIcon(getClass().getResource("/images/banner2.png")));

        pnCenterN.add(lblTieuDe);
        pnCenterC.add(lbImage);
        pnCneter.add(pnCenterN,BorderLayout.NORTH);
        pnCneter.add(pnCenterC,BorderLayout.CENTER);
//        pnCneter.setPreferredSize(new Dimension(1000,300));

        //pnSouth
        JPanel pnSouth = new JPanel();

       Box b,b1,b2,b3,b4;
       b = Box.createVerticalBox();
       b.add(b1 = Box.createHorizontalBox());
        b1.add(Box.createHorizontalStrut(200));
       b1.add(lblGiaoVien = new JLabel("Giáo Viên Hướng Dẫn: "));
       b1.add(Box.createHorizontalStrut(30));
       b1.add(lblTenGV = new JLabel("Phạm Thanh Hùng"));
       b.add(Box.createVerticalStrut(30));

       b.add(b2 = Box.createHorizontalBox());
        b2.add(Box.createHorizontalStrut(200));
       b2.add(lblThanhVien = new JLabel("Thành Viên Nhóm: "));
        b2.add(Box.createHorizontalStrut(30));
        b2.add(lblSV1 = new JLabel("Nguyễn Văn A"));
//       b2.add(lblSV1 = new JLabel("Châu Nguyễn Tuấn Kiệt"));
        b.add(Box.createVerticalStrut(20));

       b.add(b3 = Box.createHorizontalBox());
        b3.add(Box.createHorizontalStrut(200));
       b3.add(lbl1 = new JLabel("                               "));
        b3.add(Box.createHorizontalStrut(30));
        b3.add(lblSV2 = new JLabel("Nguyễn Văn B"));
//       b3.add(lblSV2 = new JLabel("Nguyễn Phan Huỳnh Đức"));
        b.add(Box.createVerticalStrut(20));

       b.add(b4 = Box.createHorizontalBox());
        b4.add(Box.createHorizontalStrut(200));
       b4.add(lbl2 = new JLabel("                               "));
        b4.add(Box.createHorizontalStrut(30));
        b4.add(lblSV3 = new JLabel("Nguyễn Văn C"));
//       b4.add(lblSV3 = new JLabel("Hồ Minh Phúc"));
        b.add(Box.createVerticalStrut(90));

       lblThanhVien.setPreferredSize(lblGiaoVien.getPreferredSize());
//       lbl1.setPreferredSize(lblGiaoVien.getPreferredSize());
//       lbl2.setPreferredSize(lblGiaoVien.getPreferredSize());

       Font ft = new Font("arial",Font.BOLD,17);
       lblGiaoVien.setFont(ft);
       lblTenGV.setFont(ft);
       lblThanhVien.setFont(ft);
       lbl1.setFont(ft);
       lbl2.setFont(ft);
       lblSV1.setFont(ft);
       lblSV2.setFont(ft);
       lblSV3.setFont(ft);

       pnCenterS.add(b);
        pnCneter.add(pnCenterS,BorderLayout.SOUTH);
        Font ft1 = new Font("arial",Font.ITALIC,16);
        JLabel lbs;
        pnSouth.add(lbs = new JLabel("Nhóm xx:                             Chương trình quản lý quầy thuốc"));
        lbs.setFont(ft1);
        pnSouth.setBackground(Color.ORANGE);
        pnSouth.setBorder(BorderFactory.createLineBorder(Color.GRAY));
       pnSouth.setPreferredSize(new Dimension(0,50));
        cp.add(menuBar,BorderLayout.NORTH);
        cp.add(pnCneter,BorderLayout.CENTER);
        cp.add(pnSouth,BorderLayout.SOUTH);

        mnKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KhachHang_Form kh = new KhachHang_Form();
                pnCneter.removeAll();
                pnCneter.add(kh);
                validate();
            }
        });
        mnNhanVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhanVien_Form nv = new NhanVien_Form();
                pnCneter.removeAll();
                pnCneter.add(nv);
                validate();
            }
        });
        mnNhaCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhaCC_Form ncc = new NhaCC_Form();
                pnCneter.removeAll();
                pnCneter.add(ncc);
                validate();
            }
        });

        mnLoaiThuoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoaiThuoc_Form lt = new LoaiThuoc_Form();
                pnCneter.removeAll();
                pnCneter.add(lt);
                validate();
            }
        });
        mnThuoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thuoc_Form thuoc = new Thuoc_Form();
                thuoc.doShow();
                pnCneter.removeAll();
                pnCneter.add(thuoc);
                validate();
            }
        });
        mnBanThuoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BanHang_Form banHang = new BanHang_Form();
                pnCneter.removeAll();
                banHang.nv = nv;
                banHang.bh = banHang;
                pnCneter.add(banHang);
                banHang.doShow();
                validate();
            }
        });
        mnNhapThuoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhapHang_Form nhapHang = new NhapHang_Form();
                pnCneter.removeAll();
                nhapHang.nv = nv;
                nhapHang.nh = nhapHang;
                pnCneter.add(nhapHang);
                nhapHang.doShow();
                validate();
            }
        });
        mnTimKH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimKiemKH_Form timKH = new TimKiemKH_Form();
                pnCneter.removeAll();
                pnCneter.add(timKH);
                timKH.doShow();
                validate();
            }
        });
        mnTimNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimKiemNV_Form timNV = new TimKiemNV_Form();
                pnCneter.removeAll();
                pnCneter.add(timNV);
                validate();
            }
        });
        mnTimNCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimKiemNCC_Form timNCC = new TimKiemNCC_Form();
                pnCneter.removeAll();
                pnCneter.add(timNCC);
                validate();
            }
        });
        mnTimThuoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimKiemthuoc_Form timThuoc = new TimKiemthuoc_Form();
                pnCneter.removeAll();
                pnCneter.add(timThuoc);
                validate();
            }
        });
        mnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lc = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn thoát chương trình không?","Xác nhận",JOptionPane.YES_NO_OPTION);
                if(lc == JOptionPane.YES_OPTION)
                    setVisible(false);
            }
        });
        mnThongke1.addActionListener(new ActionListener() {
        	private JasperPrint jprint;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyQuayThuoc;instance=SQLEXPRESS;user=sa;password=hung");
//					Connection con = MyConnection.getInstance().getConnection();
					String sql = "SELECT *\r\n" + 
							"FROM [dbo].[Thuoc] t WHERE t.MAT IN (SELECT ct.MAT FROM \r\n" + 
							"[dbo].[CT_HoaDonBanHang] ct JOIN [dbo].[HoaDonBanHang] \r\n" + 
							"hd ON ct.MaHDBH = hd.MaHDBH WHERE DAY(hd.NGAYLAPHD) = DAY(GETDATE()))";
					JasperDesign jdesign = JRXmlLoader.load("D:\\PTUD_ChuongTrinhQuanLyQuayThuoc\\PTUD_QuanLyQuayThuoc\\src\\Report\\ThongKeHangBan.jrxml");
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
        mnThongke2.addActionListener(new ActionListener() {
        	private JasperPrint jprint;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyQuayThuoc;instance=SQLEXPRESS;user=sa;password=hung");
//					Connection con = MyConnection.getInstance().getConnection();
					String sql = "SELECT t.MAT,t.TENTHUOC,lt.TENLOAI,t.CONGDUNG,t.DONVITINH,t.SOLUONG,t.GIABAN,t.GIABAN*t.SOLUONG AS THANHTIEN\r\n" + 
							"FROM [dbo].[Thuoc] t JOIN [dbo].[LoaiThuoc] lt ON t.MALOAI = lt.MALOAI";
					JasperDesign jdesign = JRXmlLoader.load("D:\\PTUD_ChuongTrinhQuanLyQuayThuoc\\PTUD_QuanLyQuayThuoc\\src\\Report\\ThongKeThuocCoTrongKho.jrxml");
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
    }

    public static void main(String[] args) {
        new GD_Chinh().setVisible(true);
    }
}
