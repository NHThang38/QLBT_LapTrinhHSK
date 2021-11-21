package gui;

import com.toedter.calendar.JDateChooser;
import dao.ChucVu_DAO;
import dao.NhanVien_DAO;
import entity.ChucVu;
import entity.NhanVien;
import tableModel.NV_TableModel;

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

public class NhanVien_Form extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblMa,lblGioiTinh,lblNamSinh,lblEmail,lblSDT,lblCM,lbldiaChi,lblTen,lblChucVu,lblNgayVao;
    JTextField txtMa,txtKhachHang,txtEmail,txtSDT,txtCM,txtdiaChi,txtTen;
    JComboBox<String> cbcGT,cbcChucVu;
    JDateChooser namSinh,NgayVao;
    public NhanVien_Form(){
        doShow();
    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("QUẢN LÝ NHÂN VIÊN");
        lblTieuDe.setFont(new Font("arial", Font.BOLD,20));
        lblTieuDe.setForeground(Color.RED);
        pnTieuDe.add(lblTieuDe);
        pnNorth.add(pnTieuDe);

        //pnCenter
        pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        Box b,b1,b2,b3,b4,b5;
        JPanel pnCenN = new JPanel();
        JPanel pnCenC = new JPanel();
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(840,170));

        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMa = new JLabel("Mã Nhân Viên: "));
        b1.add(txtMa = new JTextField(30));
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblTen = new JLabel("Tên Nhân Viên:    "));
        b1.add(txtTen = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblGioiTinh = new JLabel("Giới Tính: "));
        cbcGT = new JComboBox<>();
        cbcGT.addItem("Nam");
        cbcGT.addItem("Nữ");
        cbcGT.setPreferredSize(new Dimension(313,20));
        b2.add(cbcGT);
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblNamSinh = new JLabel("Ngày Sinh:    "));
        namSinh = new JDateChooser();
        namSinh.setSize(new Dimension(30,20));
        namSinh.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        namSinh.setDateFormatString("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());
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

        b.add(b5 = Box.createHorizontalBox());
        b5.add(lblChucVu = new JLabel("Chức Vụ: "));
        cbcChucVu = new JComboBox<>();
//        ChucVu_DAO CvDao = new ChucVu_DAO();
        cbcChucVu.addItem("Nhân Viên Kinh Doanh");
        cbcChucVu.addItem("Nhân viên thu ngân");
        cbcChucVu.setPreferredSize(new Dimension(313,20));
        b5.add(cbcChucVu);
        b5.add(Box.createHorizontalStrut(20));
        b5.add(lblNgayVao = new JLabel("Ngày Vào Làm:    "));
        NgayVao = new JDateChooser();
        lblNgayVao.setSize(new Dimension(30,20));
        lblNgayVao.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        NgayVao.setDateFormatString("yyyy-MM-dd");
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = Date.valueOf(LocalDate.now());
            System.out.println("Date: " + date);
            NgayVao.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        b5.add(NgayVao);
        b.add(Box.createVerticalStrut(10));

        lblMa.setPreferredSize(lblTen.getPreferredSize());
        lblEmail.setPreferredSize(lblTen.getPreferredSize());
        lblCM.setPreferredSize(lblTen.getPreferredSize());
        lblGioiTinh.setPreferredSize(lblTen.getPreferredSize());
        lblNamSinh.setPreferredSize(lblTen.getPreferredSize());
        lblSDT.setPreferredSize(lblTen.getPreferredSize());
        lbldiaChi.setPreferredSize(lblTen.getPreferredSize());
        lblChucVu.setPreferredSize(lblTen.getPreferredSize());
        lblNgayVao.setPreferredSize(lblTen.getPreferredSize());


        JPanel pnCenS = new JPanel();
        JButton btnThem,btnXoa,btnSua,btnThoat,btnLuu;
        pnCenS.add(btnThem = new JButton("Thêm Nhân Viên"));
        pnCenS.add(btnXoa = new JButton("Xóa Nhân Viên"));
        pnCenS.add(btnSua = new JButton("Sửa Thông Tin"));
        pnCenS.add(btnLuu = new JButton("Lưu Thông Tin"));
        pnCenS.add(btnThoat = new JButton("Thoát"));

        pnCenN.add(b);
        pnCenter.add(pnCenN,BorderLayout.NORTH);
        pnCenter.add(pnCenC,BorderLayout.CENTER);
        pnCenter.add(pnCenS,BorderLayout.SOUTH);

        //pnSouth
        pnSouth = new JPanel();
        List<NhanVien> ls = new ArrayList<>();
        NhanVien_DAO nvDao = new NhanVien_DAO();
        NV_TableModel model = new NV_TableModel(nvDao.getLS());
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1) {
                    txtMa.setEditable(false);
                    txtMa.setText(table.getValueAt(r, 0).toString());
                    txtTen.setText(table.getValueAt(r, 1).toString());
                    if (table.getValueAt(r, 2).toString().equalsIgnoreCase("Nam"))
                        cbcGT.setSelectedItem("Nam");
                    else
                        cbcGT.setSelectedItem("Nữ");

                    namSinh.setDate(java.sql.Date.valueOf(table.getValueAt(r, 3).toString()));
                    txtSDT.setText(table.getValueAt(r, 4).toString());
                    txtEmail.setText(table.getValueAt(r, 5).toString());
                    txtCM.setText(table.getValueAt(r, 6).toString());
                    txtdiaChi.setText(table.getValueAt(r, 7).toString());

                    cbcChucVu.setSelectedItem(table.getValueAt(r, 8).toString());
                    NgayVao.setDate(java.sql.Date.valueOf(table.getValueAt(r, 9).toString()));
                    System.out.println(table.getValueAt(r, 9).toString());
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
        sc.setPreferredSize(new Dimension(850,280));

        txtMa.setEditable(false);
        pnSouth.add(sc);
        pnSouth.setBorder(new TitledBorder("Danh Sách Nhân Viên"));

        //Su kien Them
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMa.setEnabled(true);
                txtMa.setText("");
                txtTen.setText("");
                txtdiaChi.setText("");
                txtCM.setText("");
                txtEmail.setText("");
                txtSDT.setText("");
                txtMa.setEditable(false);
                txtTen.requestFocus();
            }
        });
        //Sự kiện xóa
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                if(r != -1) {
                    int tb = JOptionPane.showConfirmDialog(null,"Bạn chắc chắn muốn xóa dòng này?","Delete",
                            JOptionPane.YES_NO_OPTION);
                    if(tb == JOptionPane.YES_OPTION) {
                        String maX = txtMa.getText().trim();
                        System.out.println(maX);
                        if (nvDao.deleteNV(maX)) {
                            try {
                                table.setModel(new NV_TableModel(nvDao.getLS()));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        clearTextField();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Bạn chưa chọn dòng cần xóa!");
                }
            }
        });

        //Su Kien Sua
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dateTime = (String) formatter.format(namSinh.getDate());
                String ngayVao1 = (String) formatter.format(NgayVao.getDate());
                int r = table.getSelectedRow();
                System.out.println(table.getRowCount());
                txtMa.setEnabled(false);
                if(r != -1){
                    String maS = txtMa.getText().trim();
                    NhanVien nvSua = new NhanVien(maS, txtTen.getText(), cbcGT.getSelectedItem().toString(), java.sql.Date.valueOf(dateTime),
                            txtEmail.getText(), txtSDT.getText(),Integer.parseInt(txtCM.getText()),txtdiaChi.getText(),
                            java.sql.Date.valueOf(ngayVao1));
                    ChucVu_DAO cvDao = new ChucVu_DAO();
                    ChucVu cv = cvDao.TimKiemTen(cbcChucVu.getSelectedItem().toString());
                    nvSua.setChucVu(cv);
                    System.out.println(nvSua);
                    int lc = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa thông tin không ?","option",JOptionPane.YES_NO_OPTION);
                    if(lc == JOptionPane.YES_OPTION) {
                        if(nvDao.updateNhanVien(maS,nvSua)) {
                            try {
                                table.setModel(new NV_TableModel(nvDao.getLS()));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                    clearTextField();

                }else {
                    JOptionPane.showMessageDialog(null,"Bạn chưa chọn dòng cần sửa!");
                }
            }
        });

        //Su kien luu
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhanVien_DAO nvDao = new NhanVien_DAO();
                if(txtTen.getText().trim().equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(null,"Tên nhân viên không được rỗng");
                }else {
                    if (txtSDT.getText().trim().matches("\\d{10}")) {
                        if (txtCM.getText().trim().equalsIgnoreCase("")) {
                            JOptionPane.showMessageDialog(null, "Chưa nhập số CMND");
                        } else {
                            if (nvDao.TimKiemCM(Integer.parseInt(txtCM.getText().trim())) != null) {
                                JOptionPane.showMessageDialog(null, "Nhân viên đã tồn tại (Trùng số CMND)");
                                txtCM.requestFocus();
                            } else {
                                System.out.println("NamSinhNV" + namSinh.getDate());
                                String dateTime = (String) formatter.format(namSinh.getDate());
                                String ngayVao1 = (String) formatter.format(NgayVao.getDate());
                                NhanVien nv = new NhanVien(txtMa.getText().trim(), txtTen.getText().trim(),
                                        cbcGT.getSelectedItem().toString(),
                                        java.sql.Date.valueOf(dateTime),
                                        txtEmail.getText().trim(),
                                        txtSDT.getText().trim(),
                                        Integer.valueOf(txtCM.getText().trim()),
                                        txtdiaChi.getText().trim(),
                                        java.sql.Date.valueOf(ngayVao1));
                                ChucVu_DAO cvDao = new ChucVu_DAO();
                                ChucVu cv;
                                if (cvDao.TimKiemTen(cbcChucVu.getSelectedItem().toString()) != null) {
                                    cv = cvDao.TimKiemTen(cbcChucVu.getSelectedItem().toString());
                                    nv.setChucVu(cv);
                                    if (nvDao.addNhanVien(nv)) {
                                        try {
                                            table.setModel(new NV_TableModel(nvDao.getLS()));
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                    } else
                                        JOptionPane.showMessageDialog(null, "Bạn chưa nhập thông tin !");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Lỗi!");
                                }
                                clearTextField();
                                table.setModel(new NV_TableModel(nvDao.getLS()));
                                System.out.println(table.getRowCount());

                                // tableModel.fireTableDataChanged();
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Số điện thoại không được chứa chữ cái, số đt gồm 10 chữ số!");
                    }
                }

            }
        });


        this.setLayout(new BorderLayout());
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);

    }
    public void clearTextField(){
        txtMa.setText("");
        txtTen.setText("");
        txtCM.setText("");
        txtdiaChi.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        cbcChucVu.setSelectedItem("Nhân Viên Kinh Doanh");
        cbcGT.setSelectedItem("Nam");
        txtTen.requestFocus();
    }
}

