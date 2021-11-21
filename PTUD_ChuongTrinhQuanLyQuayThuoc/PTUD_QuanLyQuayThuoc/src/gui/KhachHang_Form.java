package gui;

import com.toedter.calendar.JDateChooser;
import dao.KhachHang_DAO;
import entity.KhachHang;
import tableModel.KH_TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class KhachHang_Form extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblMa,lblKhachHang,lblGioiTinh,lblNamSinh,lblEmail,lblSDT,lblCM,lbldiaChi;
    JTextField txtMa,txtKhachHang,txtEmail,txtSDT,txtCM,txtdiaChi;
    JComboBox<String> cbcGT;
    JDateChooser namSinh;
    public KhachHang_Form(){
        doShow();
    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("QUẢN LÝ KHÁCH HÀNG");
        lblTieuDe.setFont(new Font("arial", Font.BOLD,20));
        lblTieuDe.setForeground(Color.RED);
        pnTieuDe.add(lblTieuDe);
        pnNorth.add(pnTieuDe);

        //pnCenter
        pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        Box b, b1, b2, b3, b4;
        JPanel pnCenN = new JPanel();
        JPanel pnCenC = new JPanel();
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(840, 150));

        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMa = new JLabel("Mã Khách Hàng: "));
        b1.add(txtMa = new JTextField(30));
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblKhachHang = new JLabel("Tên Khách Hàng:    "));
        b1.add(txtKhachHang = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblGioiTinh = new JLabel("Giới Tính: "));
        cbcGT = new JComboBox<>();
        cbcGT.addItem("Nam");
        cbcGT.addItem("Nữ");
        cbcGT.setPreferredSize(new Dimension(300, 20));
        b2.add(cbcGT);
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblNamSinh = new JLabel("Ngày Sinh:    "));
        namSinh = new JDateChooser();
        namSinh.setSize(new Dimension(45, 20));
        namSinh.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        namSinh.setDateFormatString("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = Date.valueOf(LocalDate.now());
            System.out.println("Date: " + date);
            namSinh.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        b2.add(namSinh);
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblEmail = new JLabel("Email: "));
        b3.add(txtEmail = new JTextField(30));
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblSDT = new JLabel("Điện Thoại:    "));
        b3.add(txtSDT = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(lblCM = new JLabel("SỐ CMND: "));
        b4.add(txtCM = new JTextField(30));
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lbldiaChi = new JLabel("Địa Chỉ:    "));
        b4.add(txtdiaChi = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        lblMa.setPreferredSize(lblKhachHang.getPreferredSize());
        lblEmail.setPreferredSize(lblKhachHang.getPreferredSize());
        lblCM.setPreferredSize(lblKhachHang.getPreferredSize());
        lblGioiTinh.setPreferredSize(lblKhachHang.getPreferredSize());
        lblNamSinh.setPreferredSize(lblKhachHang.getPreferredSize());
        lblSDT.setPreferredSize(lblKhachHang.getPreferredSize());
        lbldiaChi.setPreferredSize(lblKhachHang.getPreferredSize());

        JPanel pnCenS = new JPanel();
        JButton btnThem,btnXoa,btnSua,btnThoat,btnLuu;
        pnCenS.add(btnThem = new JButton("Thêm Khách Hàng"));
        pnCenS.add(btnXoa = new JButton("Xóa Khách Hàng"));
        pnCenS.add(btnSua = new JButton("Sửa Thông Tin"));
        pnCenS.add(btnLuu = new JButton("Lưu Thông Tin"));
        pnCenS.add(btnThoat = new JButton("Thoát"));

        pnCenN.add(b);
        pnCenter.add(pnCenN,BorderLayout.NORTH);
        pnCenter.add(pnCenC,BorderLayout.CENTER);
        pnCenter.add(pnCenS,BorderLayout.SOUTH);

        //pnSouth
        pnSouth = new JPanel();
        List<KhachHang> ls = new ArrayList<>();
        KhachHang_DAO khDao = new KhachHang_DAO();
        KH_TableModel model = new KH_TableModel(khDao.getLS());
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    txtMa.setEditable(false);
                    txtMa.setText(table.getValueAt(r, 0).toString());
                    txtKhachHang.setText(table.getValueAt(r, 1).toString());
                    if (table.getValueAt(r, 2).toString().equalsIgnoreCase("Nam"))
                        cbcGT.setSelectedItem("Nam");
                    else
                        cbcGT.setSelectedItem("Nữ");
                    namSinh.setDate(java.sql.Date.valueOf(table.getValueAt(r, 3).toString()));
                    txtSDT.setText(table.getValueAt(r, 4).toString());
                    txtEmail.setText(table.getValueAt(r, 5).toString());
                    txtCM.setText(table.getValueAt(r, 6).toString());
                    txtdiaChi.setText(table.getValueAt(r, 7).toString());
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
        JScrollPane sc = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(850,300));

        txtMa.setEditable(false);
        pnSouth.add(sc);
        pnSouth.setBorder(new TitledBorder("Danh Sách Khách Hàng"));


        //Su kien Them
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMa.setEnabled(true);
                txtMa.setText("");
                txtKhachHang.setText("");
                txtdiaChi.setText("");
                txtCM.setText("");
                txtEmail.setText("");
                txtSDT.setText("");
                txtMa.setEditable(false);
                txtKhachHang.requestFocus();
            }
        });

        //Su Kien Sua
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dateTime = (String) formatter.format(namSinh.getDate());
                int r = table.getSelectedRow();
                txtMa.setEditable(false);
                if (r != -1) {
                    String maS = txtMa.getText().trim();
                    KhachHang khSua = new KhachHang(maS, txtKhachHang.getText(), cbcGT.getSelectedItem().toString(), Date.valueOf(dateTime),
                            txtEmail.getText(), txtSDT.getText(), Integer.parseInt(txtCM.getText()), txtdiaChi.getText());
                    System.out.println(khSua);
                    int lc = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa thông tin không ?","option",JOptionPane.YES_NO_OPTION);
                    if(lc == JOptionPane.YES_OPTION) {
                        if (khDao.updateKhachHang(maS, khSua)) {
                            try {
                                table.setModel(new KH_TableModel(khDao.getLS()));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng nào!");
                    }
                }
                cleartext();
            }
        });

        //Su kien luu
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KhachHang_DAO khDao = new KhachHang_DAO();
                if(txtKhachHang.getText().trim().equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(null,"Tên khách hàng không được rỗng");
                }else {
                    if (txtSDT.getText().trim().matches("\\d{10}")) {
                        if (txtCM.getText().trim().equalsIgnoreCase("")) {
                            JOptionPane.showMessageDialog(null, "Chưa nhập số CMND");
                        }else{
                            if (khDao.TimKiemCM(Integer.parseInt(txtCM.getText().trim())) != null) {
                                JOptionPane.showMessageDialog(null, "Khách hàng đã tồn tại (Trùng số CMND)");
                                txtCM.requestFocus();
                            } else {
                                System.out.println(namSinh.getDate());
                                String dateTime = (String) formatter.format(namSinh.getDate());
                                KhachHang kh = new KhachHang(txtMa.getText().trim(), txtKhachHang.getText().trim(),
                                        cbcGT.getSelectedItem().toString(),
                                        Date.valueOf(dateTime),
                                        txtEmail.getText().trim(),
                                        txtSDT.getText().trim(),
                                        Integer.valueOf(txtCM.getText().trim()),
                                        txtdiaChi.getText().trim());
                                if (khDao.addKhachHang(kh)) {
                                    try {
                                        table.setModel(new KH_TableModel(khDao.getLS()));
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Bạn chưa nhập thông tin !");
                                }
                                cleartext();
                                table.setModel(new KH_TableModel(khDao.getLS()));
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Số điện thoại không được chứa chữ cái, số đt gồm 10 chữ số!");
                    }
                }
            }
        });
        //Sự kiện xóa
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                if (r != -1) {
                    int tb = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa dòng này?", "Delete",
                            JOptionPane.YES_NO_OPTION);
                    if (tb == JOptionPane.YES_OPTION) {
                        String maX = txtMa.getText().trim();
                        System.out.println(maX);
                        if (khDao.deleteKH(maX)) {
                            try {
                                table.setModel(new KH_TableModel(khDao.getLS()));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        cleartext();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng cần xóa!");
                }
            }
        });
//        btnThoat = new JButton("Thoát ");
//        //Sự kiện thoát
//        btnThoat.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                removeAll();
//                TrangChu_Form tc = new TrangChu_Form();
//                add(tc);
//                validate();
//            }
//        });



        this.setLayout(new BorderLayout());
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);

    }
    public void  cleartext(){
        txtMa.setText("");
        txtKhachHang.setText("");
        txtCM.setText("");
        txtdiaChi.setText("");
        txtEmail.setText("");
    }
}

