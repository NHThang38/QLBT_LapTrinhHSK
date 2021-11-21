package gui;

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
import java.util.ArrayList;
import java.util.List;

public class TimKiemthuoc_Form extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -343354033986428930L;
	JPanel pnNorth,pnCenter;
    JTextField txtTenKh;
    JRadioButton rdTenKH,rdSDT;
    ButtonGroup btnGR;
    JButton btnTim,btnThoat;
    JComboBox cbcLoai;
    public TimKiemthuoc_Form(){
        doShow();
    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnNorth_N = new JPanel();
        JLabel lblTieuDe = new JLabel("TÌM KIẾM THÔNG TIN THUỐC");
        lblTieuDe.setFont(new Font("arial",Font.BOLD,20));
        lblTieuDe.setForeground(Color.RED);
        pnNorth_N.add(lblTieuDe);

        JPanel pnNorth_C = new JPanel();
        Box b,b1,b2;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(700,130));
        b.add(Box.createVerticalStrut(20));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(rdTenKH = new JRadioButton("Tên Thuốc"));
        b1.add(txtTenKh = new JTextField());
        b1.add(Box.createHorizontalStrut(30));
        b1.add(rdSDT = new JRadioButton("Chọn Loại Thuốc"));
        b1.add(cbcLoai = new JComboBox<>());
        LoaiThuoc_DAO ltDao = new LoaiThuoc_DAO();
        for (Loaithuoc lt: ltDao.getLS()) {
            cbcLoai.addItem(lt.getTenThuoc());
        }
        b.add(Box.createVerticalStrut(20));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(btnTim = new JButton("Tìm Kiếm"));
        b2.add(Box.createHorizontalStrut(100));
        b2.add(btnThoat = new JButton("Thoát"));
        b.add(Box.createVerticalStrut(60));

        btnGR = new ButtonGroup();
        btnGR.add(rdTenKH);
        btnGR.add(rdSDT);
        pnNorth_C.add(b);

        pnNorth.setLayout(new BorderLayout());
        pnNorth.add(pnNorth_N,BorderLayout.NORTH);
        pnNorth.add(pnNorth_C,BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        pnNorth_C.setBorder(new TitledBorder("Tìm Kiếm Thuốc Theo"));


        //pnCenter
        pnCenter = new JPanel();
        List<Thuoc> ls = new ArrayList<>();
        Thuoc_TableModel model = new Thuoc_TableModel(ls);
        JTable table = new JTable();
        table.setModel(model);
        JScrollPane sc = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(850,300));
        pnCenter.add(sc);
        pnCenter.setBorder(new TitledBorder("Kết Quả Tìm Kiếm"));

        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rdTenKH.isSelected()){
                    if(!txtTenKh.getText().trim().equals("")){
                        Thuoc_DAO thuocDao =new Thuoc_DAO();
                        if(thuocDao.TimKiemTen(txtTenKh.getText().trim()) != null){
                            List<Thuoc> ls = new ArrayList<>();
                            ls.add(thuocDao.TimKiemTen(txtTenKh.getText().trim()));
                            table.setModel(new Thuoc_TableModel(ls));
                        }else{
                            JOptionPane.showMessageDialog(null,"Không tìm thấy!");
                            table.setModel(new Thuoc_TableModel(ls));
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Nhập tên cần tìm!");
                    }
                }else{
                    if(!cbcLoai.getSelectedItem().toString().trim().equals("")){
                        Thuoc_DAO thuocDao =new Thuoc_DAO();
                        LoaiThuoc_DAO ltDao = new LoaiThuoc_DAO();
                        Loaithuoc lt = ltDao.TimKiemTen(cbcLoai.getSelectedItem().toString());
                        String maL = lt.getMaLoai();
                        if(thuocDao.TimKiemMaL(maL) != null){
                            table.setModel(new Thuoc_TableModel(thuocDao.TimKiemMaL(maL)));
                        }
                    }
                }
            }
        });


        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
    }
}
