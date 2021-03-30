package JTable;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SimpleTableDemo extends JPanel {
	private boolean DEBUG = false;

	public SimpleTableDemo() {
		super(new GridLayout(1, 0));

		String[] columnNames = { "First Name", "Last Name", "Sport", "# of Years", "Vegetarian" };

		Object[][] data = { { "Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(true) },
				{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
				{ "Sue", "Black", "Knitting", new Integer(2), new Boolean(false) },
				{ "Jane", "White", "Speed reading", new Integer(20), new Boolean(true) },
				{ "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };
		
		final JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500,700));
		table.setFillsViewportHeight(true);
		
		if(DEBUG) {
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					printDebugData(table);
				}
			});
		}
		
		JScrollPane sp = new JScrollPane(table);
		this.add(sp);
	}
	
	private void printDebugData(JTable table) {
		int numRows = table.getRowCount();
		int numCols = table.getColumnCount();
		javax.swing.table.TableModel model = table.getModel();
		
		System.out.println("Value of data: ");
		for(int i=0; i<numRows; i++) {
			System.out.print("     row  " + i + ":");
			for(int j=0; j<numCols; j++) {
				System.out.print("   "+ model.getValueAt(i, j));
			}
			System.out.println();
		}
		System.out.println("-------------------------- ");
		
	}
	private static void createAndShowGUI() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SimpleTableDemo newContentPane = new SimpleTableDemo();
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	
	
	
	
	
	
	

}
