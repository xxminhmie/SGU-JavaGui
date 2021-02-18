import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JTableDemo1 {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		String[][] data = {
				{
					"Le Ho Kim Minh", "02-11-2000", "0931746152", "minhmie@gmail.com"
				},
				{
					"Le Ho Kim Minh", "02-11-2000", "0931746152", "minhmie@gmail.com"
				},
				{
					"Le Ho Kim Minh", "02-11-2000", "0931746152", "minhmie@gmail.com"
				}
		};
		
		String[] columnNames = {
				"Fullname","DoB","Phone","Email"
		};
		
		JTable table = new JTable(data,columnNames);
		table.setBounds(20, 40, 200, 300);
		
		JScrollPane sp = new JScrollPane(table);
		f.add(sp);
		
		f.setSize(400, 200);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

}
