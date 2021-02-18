package JTable.CustomTableModel;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Test extends JFrame {
    public Test() {
        setBounds(100, 100, 500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTable table = new JTable(new ModelData());
        add(new JScrollPane(table));
        setVisible(true);
    }

    public static void main(String[] args) {
        new Test();
    }
}
