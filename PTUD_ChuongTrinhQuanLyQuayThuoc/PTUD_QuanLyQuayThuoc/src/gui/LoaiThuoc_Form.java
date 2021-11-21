package gui;

import com.toedter.calendar.JDateChooser;
import dao.LoaiThuoc_DAO;
import entity.Loaithuoc;
import entity.NhaCungCap;
import tableModel.LoaiThuoc_TableModel;
import tableModel.NCC_TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class LoaiThuoc_Form extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblMa,lblTen;
    JTextField txtMa,txtTen;
    public LoaiThuoc_Form(){
        doShow();
    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("QUẢN LÝ LOẠI THUỐC");
        lblTieuDe.setFont(new Font("arial", Font.BOLD,20));
        lblTieuDe.setForeground(Color.RED);
        pnTieuDe.add(lblTieuDe);
        pnNorth.add(pnTieuDe);

        //pnCenter
        pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        JPanel pnCenN = new JPanel();
        JPanel pnCenC = new JPanel();
        Box b,b1;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(840,70));

        b.add(Box.createVerticalStrut(30));
        b.add(b1 = Box.createHorizontalBox());

        b1.add(lblMa = new JLabel("Mã Loại Thuốc: "));
        b1.add(txtMa = new JTextField());
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblTen = new JLabel("Tên Loại Thuốc:    "));
        b1.add(txtTen = new JTextField());
        b.add(Box.createVerticalStrut(10));

        lblMa.setPreferredSize(lblTen.getPreferredSize());

        JPanel pnCenS = new JPanel();
        JButton btnThem,btnXoa,btnSua,btnThoat,btnLuu;
        pnCenS.add(btnThem = new JButton("Thêm Loại Thuốc"));
        pnCenS.add(btnXoa = new JButton("Xóa Loại Thuốc"));
        pnCenS.add(btnSua = new JButton("Sửa Thông Tin"));
        pnCenS.add(btnLuu = new JButton("Lưu Thông Tin"));
        pnCenS.add(btnThoat = new JButton("Thoát"));

        pnCenN.add(b);
        pnCenter.add(pnCenN,BorderLayout.NORTH);
        pnCenter.add(pnCenC,BorderLayout.CENTER);
        pnCenter.add(pnCenS,BorderLayout.SOUTH);


        txtMa.setEditable(false);
        //pnSouth
        pnSouth = new JPanel();
        List<Loaithuoc> ls = new ArrayList<>();
        LoaiThuoc_DAO ltDao = new LoaiThuoc_DAO();
        LoaiThuoc_TableModel model = new LoaiThuoc_TableModel(ltDao.getLS());
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    txtMa.setText(table.getValueAt(r,1).toString());
                    txtTen.setText(table.getValueAt(r,2).toString());
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

        pnSouth.add(sc);
        pnSouth.setBorder(new TitledBorder("Danh Sách Loại Thuốc"));

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMa.setText("");
                txtTen.setText("");
                txtTen.requestFocus();
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    String maX = txtMa.getText().trim();
                    LoaiThuoc_DAO ltDao = new LoaiThuoc_DAO();
                    if(ltDao.deleteLT(maX)){
                        JOptionPane.showMessageDialog(null,"Xóa thành công !");
                        table.setModel(new LoaiThuoc_TableModel(ltDao.getLS()));
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Bạn chưa chọn dòng cần xóa!");
                }
            }
        });
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtTen.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null,"Bạn chưa nhập tên loại thuốc!");
                    txtTen.requestFocus();
                }else{
                    Loaithuoc lt = new Loaithuoc(txtMa.getText().trim(),txtTen.getText().trim());
                    if(ltDao.addLoaiThuoc(lt)){
                        table.setModel(new LoaiThuoc_TableModel(ltDao.getLS()));
                        txtTen.setText("");
                    }else{
                        JOptionPane.showMessageDialog(null,"Lỗi!");
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


