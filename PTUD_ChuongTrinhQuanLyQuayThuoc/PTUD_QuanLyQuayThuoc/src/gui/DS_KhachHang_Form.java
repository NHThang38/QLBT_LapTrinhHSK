package gui;

import dao.HoaDonBanHang_DAO;
import dao.KhachHang_DAO;
import entity.HoaDonBanHang;
import entity.KhachHang;
import tableModel.KH_TableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DS_KhachHang_Form extends JFrame {
    BanHang_Form banhang;
    KhachHang kh;
    public DS_KhachHang_Form(){
        doShow();
    }
    public void doShow(){
        setSize(700,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Danh Sách Khách Hàng");
        Container cp =getContentPane();

        //pnNorth
        JPanel pnNorth = new JPanel();
        JLabel lbltieuDe = new JLabel("DANH SÁCH KHÁCH HÀNG");
        pnNorth.add(lbltieuDe);

        //pnCenter
        JPanel pnCenter = new JPanel();
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
        sc.setPreferredSize(new Dimension(650,170));
        pnCenter.add(sc);

        //South
        JPanel pnSouth = new JPanel();
        JButton btnLapHD = new JButton("Lập Hóa Đơn");
        pnSouth.add(btnLapHD);


        btnLapHD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    kh = khDao.TimKiemMa(table.getValueAt(r,0).toString());

                    banhang.kh = kh;
                    banhang.txtKhachHang.setText(kh.getTenKH());
                    banhang.txtSDT.setText(kh.getDienThoai());
                    banhang.txtDiaChi.setText(kh.getDiaChi());
                    HoaDonBanHang hd = new HoaDonBanHang("", kh, banhang.nv, Date.valueOf(LocalDate.now()));
                    HoaDonBanHang_DAO hdDao = new HoaDonBanHang_DAO();
                    if (hdDao.addHDBH(hd)) {
                        setVisible(false);
                    }

                }
            }
        });

        cp.setLayout(new BorderLayout());
        cp.add(pnNorth, BorderLayout.NORTH);
        cp.add(pnCenter, BorderLayout.CENTER);
        cp.add(pnSouth, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new DS_KhachHang_Form().setVisible(true);
    }
}
