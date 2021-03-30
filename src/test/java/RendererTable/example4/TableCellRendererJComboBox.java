package RendererTable.example4;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class TableCellRendererJComboBox extends JFrame {
	  protected JTable table;

	  public static void main(String[] args) {
	    TableCellRendererJComboBox stt = new TableCellRendererJComboBox();
	    stt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    stt.setSize(400, 200);
	    stt.setVisible(true);
	  }

	  public TableCellRendererJComboBox() {
	    Container pane = getContentPane();
	    pane.setLayout(new BorderLayout());
	    TableValues tv = new TableValues();
	    table = new JTable(tv);
	    TableColumnModel tcm = table.getColumnModel();
	    TableColumn tc = tcm.getColumn(TableValues.GENDER);
	    tc.setCellRenderer(new GenderRenderer());
	    JScrollPane jsp = new JScrollPane(table);
	    pane.add(jsp, BorderLayout.CENTER);
	  }

	}