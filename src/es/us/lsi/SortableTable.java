package es.us.lsi;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Enumeration;
import java.util.Properties;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class SortableTable extends JPanel {

    private JTable table;
    private MyTableModel model;

    public SortableTable() {
        super(new GridLayout(1, 0));

        model = new MyTableModel();
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(600, 300));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public void setData(Properties props) {
        model.setData(props);
        table.repaint();
    }

    public Object getValueAt(int row, int col) {
        return model.getValueAt(row, col);
    }

    public int getRowCount() {
        return model.getRowCount();
    }

    class MyTableModel extends AbstractTableModel {

        private String[] columnNames = {"Property",
            "Value",
            "New value"};
        private Object[][] data;

        public MyTableModel() {
            setData(null);
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
        }

        public void setData(Properties props) {
            if (props != null) {
                data = new Object[props.size()][3];
                Enumeration e = props.propertyNames();
                int i = 0;
                while (e.hasMoreElements()) {
                    String key = (String) e.nextElement();
                    data[i][0] = key;
                    data[i][1] = props.getProperty(key);
                    data[i][2] = props.getProperty(key);;
                    i++;
                }
            } else {
                data = new Object[1][3];
                data[0][0] = "";
                data[0][1] = "";
                data[0][2] = "";
            }
        }
    }
}
