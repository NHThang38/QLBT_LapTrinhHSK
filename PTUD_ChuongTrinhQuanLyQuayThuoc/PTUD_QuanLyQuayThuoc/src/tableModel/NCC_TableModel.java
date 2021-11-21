package tableModel;
import entity.NhaCungCap;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class NCC_TableModel extends AbstractTableModel {

    private List<NhaCungCap> ds;
    String [] headers = {"Mã NCC", "Tên NCC", "Số Điện Thoại","Email","Địa Chỉ"};

    public NCC_TableModel(List<NhaCungCap> ds){
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
        NhaCungCap ncc = ds.get(rowIndex);
        switch (columnIndex){
            case 0:
                return ncc.getMaNhaCC();
            case 1:
                return ncc.getTenNhaCC();
            case 2:
                return ncc.getSoDT();
            case 3:
                return ncc.geteMail();
            case 4:
                return ncc.getDiaChi();
            default:
                return ncc;
        }


    }
}
