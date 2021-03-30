package JTable;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class ColumnModelSample {
	public static void main(String[] args) {
		final Object rows[][] = { 
				{"one", "ichi - \u4E00"},
				{"one", "ichi - \u4E00"},
				{"one", "ichi - \u4E00"},
				{"one", "ichi - \u4E00"},
				{"one", "ichi - \u4E00"},
				{"one", "ichi - \u4E00"},
				{"one", "ichi - \u4E00"},
				{"one", "ichi - \u4E00"},
				{"one", "ichi - \u4E00"},
		};
		final Object headers[] = {"English", "Japanese"};
		
		Runnable runner = new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Scrollless Table");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JTable table = new JTable(rows, headers);
				
				TableColumnModelListener tableColumnModelListener = 
						new TableColumnModelListener() {

							@Override
							public void columnAdded(TableColumnModelEvent e) {
								System.out.println("Added");
							}

							@Override
							public void columnRemoved(TableColumnModelEvent e) {
								System.out.println("Removed");
								
							}

							@Override
							public void columnMoved(TableColumnModelEvent e) {
								System.out.println("Moved");
								
							}

							@Override
							public void columnMarginChanged(ChangeEvent e) {
								System.out.println("Margin");
								
							}

							@Override
							public void columnSelectionChanged(ListSelectionEvent e) {
								System.out.println("Selection Changed");
								
							}	
				};
				
				TableColumnModel columnModel = table.getColumnModel();
				columnModel.addColumnModelListener(tableColumnModelListener);
				
				columnModel.setColumnMargin(12);
				
				TableColumn column = new TableColumn(1);
				columnModel.addColumn(column);
				
				JScrollPane pane = new JScrollPane(table);
				frame.add(pane, BorderLayout.CENTER);
				frame.setSize(300, 150); 
				frame.setVisible(true);
			}
		};
		EventQueue.invokeLater(runner);
	}

}
