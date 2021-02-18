package xxminhmie.sgu.javagui.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class MyTable extends JPanel{
	JTable tb;
	DefaultTableModel tbModel;
	JScrollPane pane;
	
	public MyTable() {
		setLayout(new BorderLayout());
		
		tb = new JTable();
		tbModel = new DefaultTableModel();
		pane = new JScrollPane(tb);
		
		//Returns whether or not this table is always made large enough 
		//to fill the height of an enclosing viewport.
		tb.setFillsViewportHeight(true);
		tb.setFont(new Font("Segoe UI",0,16));
		tb.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,16));
		tb.setRowHeight(40);
		
		//color
		int bgColor = 235;
		int color = 0;
		tb.getTableHeader().setBackground(new Color(bgColor, bgColor, bgColor));
		tb.getTableHeader().setForeground(new Color(color,color,color));
		tb.setBackground(new Color(bgColor, bgColor, bgColor));
		tb.setForeground(new Color(color,color,color));
		
		tb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		add(pane,BorderLayout.CENTER);
	}
	
	public void setHeader(String[] headers) {
		tbModel.setColumnIdentifiers(headers);
		tb.setModel(tbModel);
	}
	public void setHeader(ArrayList<String> headers) {
		tbModel.setColumnIdentifiers(headers.toArray());
		tb.setModel(tbModel);
	}
}




/**
 * A JTable that draws a zebra striped background.
 */
//http://nadeausoftware.com/articles/2008/01/java_tip_how_add_zebra_background_stripes_jtable

class ZebraTable extends javax.swing.JTable{
	
	private java.awt.Color rowColors[] = new java.awt.Color[2];
	private boolean drawStripes = false;
	
	public ZebraTable() {
		
	}
	
	public ZebraTable(int numRows, int numCols) {
		super(numRows, numCols);
	}
	
	public ZebraTable(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
	}
	
	public ZebraTable(javax.swing.table.TableModel dataModel) {
		super(dataModel);
	}
	
	public ZebraTable(javax.swing.table.TableModel dataModel,
			javax.swing.table.TableColumnModel columnModel) {
		super(dataModel, columnModel);
	}
	
	public ZebraTable(javax.swing.table.TableModel dataModel,
			javax.swing.table.TableColumnModel columnModel,
			javax.swing.ListSelectionModel selectionModel) {
		super(dataModel, columnModel, selectionModel);
	}
	
//	public ZebraTable(java.util.Vector<?> rowData,
//			java.util.Vector<?> columnNames) {
//		super(rowData, columnNames);
//	}
	
	public void paintComponent(java.awt.Graphics g) {
		if(!(drawStripes = isOpaque())) {
			super.paintComponent(g);
			return;
		}
		
		//Paint zebra backgro9und stripes
//		updateZebraColors();
		final java.awt.Insets insets = getInsets();
		
	}
}











