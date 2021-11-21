package gui;

import com.toedter.calendar.JDateChooser;
import dao.LoaiThuoc_DAO;
import dao.Thuoc_DAO;
import entity.Loaithuoc;
import entity.Thuoc;
import tableModel.Thuoc_TableModel;

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

public class Thuoc_Form extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblMa,lblTen,lblLoaiThuoc,lblCongDung,lblThanhPhan,lblXuatXu,lblSoLuong,lblGiaBan,lblHanDung,lblDonViTinh;
    JTextField txtMa,txtTen,txtCongDung,txtThanhPhan,txtXuatXu,txtSoLuong,txtGia;
    JComboBox<String> cbcLoai,cbcDonVi;
    JDateChooser NgayHetHan;
    public Thuoc_Form(){
        doShow();
    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("QUẢN LÝ THUỐC");
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
        b1.add(lblMa = new JLabel("Mã Thuốc: "));
        b1.add(txtMa = new JTextField(30));
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblTen = new JLabel("Tên Thuốc:    "));
        b1.add(txtTen = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblLoaiThuoc = new JLabel("Loại Thuốc: "));
        cbcLoai = new JComboBox<>();
//        cbcLoai.addItem("Thần Kinh");
//        cbcLoai.addItem("Cảm Sốt");
        LoaiThuoc_DAO ltDao = new LoaiThuoc_DAO();
        for (Loaithuoc lt: ltDao.getLS()) {
            cbcLoai.addItem(lt.getTenThuoc());
        }
        cbcLoai.setPreferredSize(new Dimension(325,20));
        b2.add(cbcLoai);
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblCongDung = new JLabel("Công Dụng:    "));
        b2.add(txtCongDung = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblThanhPhan = new JLabel("Thành Phần: "));
        b3.add(txtThanhPhan = new JTextField(30));
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblXuatXu = new JLabel("Xuất Xứ:    "));
        b3.add(txtXuatXu = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(lblSoLuong = new JLabel("Số Lượng: "));
        b4.add(txtSoLuong = new JTextField(30));
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lblGiaBan = new JLabel("Giá Bán:    "));
        b4.add(txtGia = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b5 = Box.createHorizontalBox());
        b5.add(lblDonViTinh = new JLabel("Đơn Vị Tính: "));
        cbcDonVi = new JComboBox<>();
//        ChucVu_DAO CvDao = new ChucVu_DAO();
        cbcDonVi.addItem("Viên");
        cbcDonVi.addItem("Vỉ");
        cbcDonVi.addItem("Tuýp");
        cbcDonVi.addItem("Hộp");
        cbcDonVi.setPreferredSize(new Dimension(325,20));
        b5.add(cbcDonVi);
        b5.add(Box.createHorizontalStrut(20));
        b5.add(lblHanDung = new JLabel("Hạn Sử Dụng:    "));
        NgayHetHan = new JDateChooser();
        NgayHetHan.setDateFormatString("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = Date.valueOf(LocalDate.now());
            System.out.println("Date: " + date);
            NgayHetHan.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        b5.add(NgayHetHan);
        b.add(Box.createVerticalStrut(10));


        lblMa.setPreferredSize(lblHanDung.getPreferredSize());
        lblTen.setPreferredSize(lblHanDung.getPreferredSize());
        lblCongDung.setPreferredSize(lblHanDung.getPreferredSize());
        lblDonViTinh.setPreferredSize(lblHanDung.getPreferredSize());
        lblXuatXu.setPreferredSize(lblHanDung.getPreferredSize());
        lblSoLuong.setPreferredSize(lblHanDung.getPreferredSize());
        lblLoaiThuoc.setPreferredSize(lblHanDung.getPreferredSize());
        lblThanhPhan.setPreferredSize(lblHanDung.getPreferredSize());
        lblGiaBan.setPreferredSize(lblHanDung.getPreferredSize());


        JPanel pnCenS = new JPanel();
        JButton btnThem,btnXoa,btnSua,btnThoat,btnLuu;
        pnCenS.add(btnThem = new JButton("Thêm Thuốc"));
        pnCenS.add(btnXoa = new JButton("Xóa Thuốc"));
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
        List<Thuoc> ls = new ArrayList<>();
        Thuoc_DAO thuocDao = new Thuoc_DAO();
        Thuoc_TableModel model = new Thuoc_TableModel(thuocDao.getLS());
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    txtMa.setText(table.getValueAt(r,0).toString());
                    txtTen.setText(table.getValueAt(r,1).toString());
                    cbcLoai.setSelectedItem(table.getValueAt(r,2).toString());
                    txtCongDung.setText(table.getValueAt(r,3).toString());
                    txtThanhPhan.setText(table.getValueAt(r,4).toString());
                    txtXuatXu.setText(table.getValueAt(r,5).toString());
                    txtSoLuong.setText(table.getValueAt(r,6).toString());
                    txtGia.setText(table.getValueAt(r,8).toString());
                    cbcDonVi.setSelectedItem(table.getValueAt(r,7).toString());
                    NgayHetHan.setDate(Date.valueOf(table.getValueAt(r,9).toString()));
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

        pnSouth.add(sc);
        pnSouth.setBorder(new TitledBorder("Danh Sách Thuốc"));

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearText();
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    int lc = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn xóa!","update",JOptionPane.YES_NO_OPTION);
                    if(lc == JOptionPane.YES_OPTION) {
                        if (thuocDao.deleteThuoc(txtMa.getText().trim())) {
                            JOptionPane.showMessageDialog(null, "Xóa thành công!");
                            clearText();
                            table.setModel(new Thuoc_TableModel(thuocDao.getLS()));
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"bạn chưa chọn dòng cần xóa!");
                }
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                String dateTime = (String) formatter.format(NgayHetHan.getDate());
                if(r != -1){
                    int lc = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn sửa!","update",JOptionPane.YES_NO_OPTION);
                    if(lc == JOptionPane.YES_OPTION) {
                        String maS = txtMa.getText().trim();
                        Thuoc thuocS = new Thuoc(txtMa.getText(), txtTen.getText(), txtThanhPhan.getText(), txtCongDung.getText(), cbcDonVi.getSelectedItem().toString(),
                                txtXuatXu.getText(), Integer.parseInt(txtSoLuong.getText().trim()), Double.parseDouble(txtGia.getText().trim()),
                                Date.valueOf(dateTime));
                        Loaithuoc lt = ltDao.TimKiemTen(cbcLoai.getSelectedItem().toString());
                        thuocS.setLoaiThuoc(lt);
                        if (thuocDao.updateThuoc(maS, thuocS)) {
                            JOptionPane.showMessageDialog(null,"Sửa thành công!");
                            table.setModel(new Thuoc_TableModel(thuocDao.getLS()));
                            clearText();
                        }
                    }
                }
            }
        });

        btnLuu.addActionListener(new ActionListener() {
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
                        table.setModel(new Thuoc_TableModel(thuocDao.getLS()));
                        clearText();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Bạn chưa nhập tên thuốc!");
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

