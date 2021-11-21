package gui;

import com.toedter.calendar.JDateChooser;
import dao.NhaCungCap_DAO;
import entity.KhachHang;
import entity.NhaCungCap;
import tableModel.KH_TableModel;
import tableModel.NCC_TableModel;

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

public class NhaCC_Form extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblMa,lblKhachHang,lblGioiTinh,lblNamSinh,lblEmail,lblSDT,lblCM,lbldiaChi;
    JTextField txtMa,txtKhachHang,txtEmail,txtSDT,txtCM,txtdiaChi;
    JComboBox<String> cbcGT;
    JDateChooser namSinh;
    public NhaCC_Form(){
        doShow();
    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("QUẢN LÝ NHÀ CUNG CẤP");
        lblTieuDe.setFont(new Font("arial", Font.BOLD,20));
        lblTieuDe.setForeground(Color.RED);
        pnTieuDe.add(lblTieuDe);
        pnNorth.add(pnTieuDe);

        //pnCenter
        pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        JPanel pnCenN = new JPanel();
        JPanel pnCenC = new JPanel();
        Box b,b1,b2,b3,b4;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(840,100));

        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMa = new JLabel("Mã Nhà Cung Cấp: "));
        b1.add(txtMa = new JTextField(30));
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblKhachHang = new JLabel("Tên Nhà Cung Cấp:    "));
        b1.add(txtKhachHang = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblEmail = new JLabel("Email:      "));
        b3.add(txtEmail = new JTextField(30));
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblSDT = new JLabel("Điện Thoại:     "));
        b3.add(txtSDT = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(lbldiaChi = new JLabel("Địa Chỉ:    "));
        b4.add(txtdiaChi = new JTextField());
        b.add(Box.createVerticalStrut(10));

        lblMa.setPreferredSize(lblKhachHang.getPreferredSize());
        lblEmail.setPreferredSize(lblKhachHang.getPreferredSize());
        lblSDT.setPreferredSize(lblKhachHang.getPreferredSize());
        lbldiaChi.setPreferredSize(lblKhachHang.getPreferredSize());



        JPanel pnCenS = new JPanel();
        JButton btnThem,btnXoa,btnSua,btnThoat,btnLuu;
        pnCenS.add(btnThem = new JButton("Thêm Nhà CC"));
        pnCenS.add(btnXoa = new JButton("Xóa Nhà CC"));
        pnCenS.add(btnSua = new JButton("Sửa Thông Tin"));
        pnCenS.add(btnLuu = new JButton("Lưu Thông Tin"));
        pnCenS.add(btnThoat = new JButton("Thoát"));

        pnCenN.add(b);
        pnCenter.add(pnCenN,BorderLayout.NORTH);
        pnCenter.add(pnCenC,BorderLayout.CENTER);
        pnCenter.add(pnCenS,BorderLayout.SOUTH);

        //pnSouth
        pnSouth = new JPanel();
        List<NhaCungCap> ls = new ArrayList<>();
        NhaCungCap_DAO nccDao = new NhaCungCap_DAO();
        NCC_TableModel model = new NCC_TableModel(nccDao.getLS());
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1) {
                    txtMa.setText(table.getValueAt(r,0).toString());
                    txtKhachHang.setText(table.getValueAt(r,1).toString());
                    txtSDT.setText(table.getValueAt(r,2).toString());
                    txtEmail.setText(table.getValueAt(r,3).toString());
                    txtdiaChi.setText(table.getValueAt(r,4).toString());
                    txtMa.setEditable(false);
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
        pnSouth.setBorder(new TitledBorder("Danh Sách Nhà Cung Cấp"));

        //Su kien Them
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMa.setEnabled(true);
                txtMa.setText("");
                txtKhachHang.setText("");
                txtdiaChi.setText("");
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
                int r = table.getSelectedRow();
                NhaCungCap nccSua = new NhaCungCap(txtMa.getText(),txtKhachHang.getText(),txtSDT.getText(),txtEmail.getText(),txtdiaChi.getText());
                if(r != -1){
                    int lc = JOptionPane.showConfirmDialog(null,"Bạn chắc chắn muốn sử thông tin?","Update",JOptionPane.YES_NO_OPTION);
                    if(lc == JOptionPane.YES_OPTION){
                        if(nccDao.updateNhaCC(table.getValueAt(r,0).toString(),nccSua)){
                            table.setModel(new NCC_TableModel(nccDao.getLS()));
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Bạn chưa chọn dòng cần sửa");
                }
            }
        });

        //Su kien luu
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean rs = true;
                for (NhaCungCap ncc1: nccDao.getLS()) {
                    if(ncc1.getTenNhaCC().equalsIgnoreCase(txtKhachHang.getText().trim())){
                        rs = false;
                    }
                }
                System.out.println(rs);
                if(rs == true) {
                    NhaCungCap ncc = new NhaCungCap(txtMa.getText(), txtKhachHang.getText(), txtSDT.getText(), txtEmail.getText(), txtdiaChi.getText());
                    if (nccDao.addNhaCungCap(ncc)) {
                        table.setModel(new NCC_TableModel(nccDao.getLS()));
                    }
                }else
                {
                    JOptionPane.showMessageDialog(null,"Tên nhà cung cấp đã tồn tại!");
                }
            }
        });
        //Sự kiện xóa
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                if(r != -1) {
                    int lc = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn xóa dòng này không","Delete",JOptionPane.YES_NO_OPTION);
                    if(lc == JOptionPane.YES_OPTION){
                        if(nccDao.deleteNCC(table.getValueAt(r,0).toString())){
                            table.setModel(new NCC_TableModel(nccDao.getLS()));
                        }
                    }
                }
            }
        });

        this.setLayout(new BorderLayout());
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);

    }
}

