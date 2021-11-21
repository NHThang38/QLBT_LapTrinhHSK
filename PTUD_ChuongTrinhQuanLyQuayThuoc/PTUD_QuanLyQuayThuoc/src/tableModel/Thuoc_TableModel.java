package tableModel;

import entity.NhanVien;
import entity.Thuoc;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class Thuoc_TableModel extends AbstractTableModel {

    private List<Thuoc> ds;
    String[] headers = {"Mã Thuốc", "Tên Thuốc", "Loại thuốc", "Công Dụng", "Thành Phần", "Xuất Xứ", "Số Lượng", "Đơn Vị Tính",
            "Đơn Giá", "Ngày Hết Hạn"};

    public Thuoc_TableModel(List<Thuoc> ds) {
        super();
        this.ds = ds;
    }

    public String getColumnName(int column) {
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
        Thuoc t = ds.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getMaThuoc();
            case 1:
                return t.getTenThuoc();
            case 2:
                return t.getLoaiThuoc().getTenThuoc();
            case 3:
                return t.getCongDung();
            case 4:
                return t.getThanhPhan();
            case 5:
                return t.getXuatXu();
            case 6:
                return t.getSoLuong();
            case 7:
                return t.getDonViTinh();
            case 8:
                return t.getGiaBan();
            case 9:
                return t.getNgayHetHan();
            default:
                return t;
        }
    }
}