package gui;

import dao.NhaCungCap_DAO;
import entity.NhaCungCap;
import tableModel.NCC_TableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TimKiemNCC_Form extends JPanel {
    JPanel pnNorth,pnCenter;
    JTextField txtTenKh,txtSDT;
    JRadioButton rdTenKH,rdSDT;
    ButtonGroup btnGR;
    JButton btnTim,btnThoat;
    public TimKiemNCC_Form(){
        doShow();
    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnNorth_N = new JPanel();
        JLabel lblTieuDe = new JLabel("TÌM KIẾM THÔNG TIN NHÀ CUNG CẤP");
        lblTieuDe.setFont(new Font("arial",Font.BOLD,20));
        lblTieuDe.setForeground(Color.RED);
        pnNorth_N.add(lblTieuDe);

        JPanel pnNorth_C = new JPanel();
        Box b,b1,b2;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(700,130));
        b.add(Box.createVerticalStrut(20));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(rdTenKH = new JRadioButton("Tên Nhà Cung Cấp"));
        b1.add(txtTenKh = new JTextField());
        b1.add(Box.createHorizontalStrut(30));
        b1.add(rdSDT = new JRadioButton("SĐT Nhà Cung Cấp"));
        b1.add(txtSDT = new JTextField());
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
        pnNorth_C.setBorder(new TitledBorder("Tìm Kiếm Nhà CC Theo"));


        //pnCenter
        pnCenter = new JPanel();
        List<NhaCungCap> ls = new ArrayList<>();
        NhaCungCap_DAO nccDao = new NhaCungCap_DAO();
        NCC_TableModel model = new NCC_TableModel(nccDao.getLS());
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
                        NhaCungCap_DAO nccDao =new NhaCungCap_DAO();
                        if(nccDao.TimKiemTen(txtTenKh.getText().trim()) != null){
                            List<NhaCungCap> ls = new ArrayList<>();
                            ls.add(nccDao.TimKiemTen(txtTenKh.getText().trim()));
                            table.setModel(new NCC_TableModel(ls));
                        }else{
                            JOptionPane.showMessageDialog(null,"Không tìm thấy!");
                            table.setModel(new NCC_TableModel(ls));
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Nhập tên cần tìm!");
                    }
                }else{
                    if(!txtSDT.getText().trim().equals("")){
                        NhaCungCap_DAO nccDao =new NhaCungCap_DAO();
                        if(nccDao.TimKiemSDT1(txtSDT.getText().trim()) != null){
                            List<NhaCungCap> ls = new ArrayList<>();
                            ls.add(nccDao.TimKiemSDT1(txtSDT.getText().trim()));
                            table.setModel(new NCC_TableModel(ls));
                        }else{
                            JOptionPane.showMessageDialog(null,"Không tìm thấy!");
                            table.setModel(new NCC_TableModel(ls));
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Nhập tên cần tìm!");
                    }
                }
            }
        });

        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
    }
}
