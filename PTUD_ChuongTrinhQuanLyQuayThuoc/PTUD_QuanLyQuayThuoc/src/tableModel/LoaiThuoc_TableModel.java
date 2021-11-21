package tableModel;
import entity.Loaithuoc;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LoaiThuoc_TableModel extends AbstractTableModel {

    private List<Loaithuoc> ds;
    String [] headers = {"STT","Mã Loại Thuốc", "Tên Loại Thuốc"};

    public LoaiThuoc_TableModel(List<Loaithuoc> ds){
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
        Loaithuoc lt = ds.get(rowIndex);
        switch (columnIndex){
            case 0:
                return rowIndex+1;
            case 1:
                return lt.getMaLoai();
            case 2:
                return lt.getTenThuoc();
            default:
                return lt;
        }


    }
}

