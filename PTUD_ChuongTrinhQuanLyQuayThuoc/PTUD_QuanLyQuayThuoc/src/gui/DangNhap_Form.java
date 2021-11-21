package gui;

import dao.NhanVien_DAO;
import entity.NhanVien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DangNhap_Form extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1197763560953830596L;
	JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblTenDangNhap,lblMatKhau;
    JTextField txtTenDN;
    JPasswordField tfMatKhau;
    JCheckBox cbxHienThi;
    JButton btnDangNhap,btnThoat;
    public DangNhap_Form(){
        doShow();
    }
    public void doShow(){
        setSize(600,270);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Đăng Nhập Hệ Thống");

        //pnNorth
        pnNorth = new JPanel();
        JLabel lblTieuDe = new JLabel("ĐĂNG NHẬP");
        lblTieuDe.setForeground(Color.RED);
        lblTieuDe.setFont(new Font("arial",Font.BOLD,20));
        pnNorth.add(lblTieuDe);

        //pnCenter
        pnCenter = new JPanel();
        Box b,b1,b2,b3;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(350,110));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblTenDangNhap = new JLabel("Tên Đăng Nhập: "));
        b1.add(Box.createHorizontalStrut(30));
        b1.add(txtTenDN = new JTextField());
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblMatKhau = new JLabel("Mật Khẩu: "));
        b2.add(Box.createHorizontalStrut(30));
        b2.add(tfMatKhau = new JPasswordField());
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(Box.createHorizontalStrut(26));
        b3.add(cbxHienThi = new JCheckBox("Hiển Thị Mật Khẩu"));
        b.add(Box.createVerticalStrut(10));

        lblMatKhau.setPreferredSize(lblTenDangNhap.getPreferredSize());

        pnCenter.add(b);

        //pnSouth
        pnSouth = new JPanel();
        Box bc = Box.createHorizontalBox();
        bc.add(btnDangNhap = new JButton("Đăng Nhập"));
        bc.add(Box.createHorizontalStrut(90));
        bc.add(btnThoat = new JButton("Thoát"));

        pnSouth.add(bc);
        pnSouth.setPreferredSize(new Dimension(0,70));

        cbxHienThi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(String.valueOf(tfMatKhau.getPassword()));
                tfMatKhau.setText(String.valueOf(tfMatKhau.getPassword()));
            }
        });
        NhanVien_DAO nvDao = new NhanVien_DAO();
        NhanVien nv = nvDao.TimKiemMa("NV001");
        btnDangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtTenDN.getText().trim().equals("admin") && String.valueOf(tfMatKhau.getPassword()).trim().equals("admin"))
                {
                    JOptionPane.showMessageDialog(null,"Đăng Nhập Hệ Thống Thành Công!");
                    GD_Chinh gd = new GD_Chinh();
                    gd.nv = nv;
                    gd.setVisible(true);
                    setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null,"Sai Tên Đăng NHập Hoặc Mật Khẩu!");
                }
            }
        });


        this.setLayout(new BorderLayout());
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new DangNhap_Form().setVisible(true);
    }
}
