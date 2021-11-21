package tableModel;

import entity.KhachHang;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class KH_TableModel extends AbstractTableModel {

    private List<KhachHang> ds;
    String [] headers = {"Mã KH", "Tên KH", "Giới Tính","Năm Sinh","Điện Thoại","Email","Số CMND","Địa Chỉ"};

    public KH_TableModel(List<KhachHang> ds){
        super();
        this.ds = ds;
    }
    public String getColumnName(int column){
        return headers[column];
    }
    @Override
    public int getRowCount() {
        return ds.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        KhachHang kh = ds.get(rowIndex);
        switch (columnIndex){
            case 0:
                return kh.getMaKH();
            case 1:
                return kh.getTenKH();
            case 2:
                return kh.getGioiTinh();
            case 3:
                return kh.getNgaySinh();
            case 4:
                return kh.getDienThoai();
            case 5:
                return kh.getEmail();
            case 6:
                return kh.getCMND();
            case 7:
                return kh.getDiaChi();
            default:
                return kh;
        }


    }
}

