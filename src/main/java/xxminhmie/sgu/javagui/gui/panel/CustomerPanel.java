package xxminhmie.sgu.javagui.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;

import xxminhmie.sgu.javagui.gui.ApplicationGUI;
import xxminhmie.sgu.javagui.gui.common.DeleteButton;
import xxminhmie.sgu.javagui.gui.common.ResetButton;
import xxminhmie.sgu.javagui.gui.common.SaveButton;
import xxminhmie.sgu.javagui.gui.table.CustomRenderer;
import xxminhmie.sgu.javagui.gui.table.CustomerModelData;
import xxminhmie.sgu.javagui.model.CustomerModel;

public class CustomerPanel extends JPanel {
	/**********************
	 * SETTING WIDTH, HEIGHT, COLOR OF PANEL
	 **************************/
	public static final int PanelWidth = 1040;
	public static final int PanelHeight = ApplicationGUI.FrameHeight;
	public static final Color PanelBg = Color.WHITE;

	/********************** DECLARE COMPONENT ON PANEL **************************/
	protected JLabel mainLabel;// Customer Management
	protected JPanel mainPanel;
	protected JPanel infoPanel;// Contain list text field
	protected JTextField[] infoTfList;// List text field display information of customer
	JDateChooser dateChooser;

	protected JPanel searchPanel;// Contain SEARCH text field
	protected JTextField search;

	protected JPanel btnPanel;// Contain 3 button: save, reset, delete
	protected SaveButton saveBtn;
	protected DeleteButton deleteBtn;
	protected ResetButton resetBtn;

	protected JPanel tbPanel;// Contain table on scroll pane
	JScrollPane pane;
	JTable table;

	protected CustomerModel selectedRow = new CustomerModel();// Customer is selected from Table

	public CustomerPanel() {
		/********************** INITIALIZE PANEL **************************/
		this.setBackground(PanelBg);
		this.setPreferredSize(new Dimension(PanelWidth, PanelHeight));
		this.setLayout(new BorderLayout());

		/********************** LABEL OF PANEL **************************/
		this.mainLabel = new JLabel("Customer Management");
		this.mainLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		this.add(this.mainLabel, BorderLayout.NORTH);

		/**********************
		 * MAIN PANEL INCLUDE OF text field, button, table
		 **************************/
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(null);
		this.add(this.mainPanel, BorderLayout.CENTER);

		/********************** SEARCH PANEL ON MAIN PANEL **************************/
		this.searchPanel = new JPanel();
		this.searchPanel.setBounds(0, 0, 330, 40);
		this.searchPanel.setLayout(null);
		this.mainPanel.add(this.searchPanel);

		this.search = new JTextField();
		this.search.setBounds(10, 10, 300, 30);
		this.searchPanel.add(this.search);

		/**********************
		 * LIST TEXT FIELD ON MAIN PANEL
		 **************************/
		this.infoPanel = new JPanel();
		this.infoPanel.setBounds(0, 30, 330, 200);
		this.infoPanel.setOpaque(true);
		this.infoPanel.setLayout(null);
		this.mainPanel.add(this.infoPanel, BorderLayout.WEST);

		String[] infoName = { "Full Name: ", "Phone: ", "Email: ", "DoB: " };
		this.infoTfList = new JTextField[4];
		int x = 10;
		int y = 20;
		for (int i = 0; i < this.infoTfList.length - 1; i++) {
			JLabel label = new JLabel(infoName[i]);
			label.setBounds(x, y, 100, 30);
			this.infoPanel.add(label);
			this.infoTfList[i] = new JTextField();
			this.infoTfList[i].setBounds(x + 100, y, 200, 30);
			y += 40;
			this.infoPanel.add(this.infoTfList[i]);
		}
		JLabel label = new JLabel(infoName[3]);
		label.setBounds(x, y, 100, 30);
		this.infoPanel.add(label);
		this.dateChooser = new JDateChooser();
		dateChooser.setBounds(x + 100, y, 200, 30);
//	    dateChooser.getJCalendar().setPreferredSize(new Dimension(600, 200));
		dateChooser.getJCalendar().setBounds(0, 0, 600, 200);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		this.infoPanel.add(dateChooser);

		/********************** LIST BUTTON ON MAIN PANEL **************************/
		this.btnPanel = new JPanel();
		this.btnPanel.setBounds(330, 55, 200, 160);
		this.btnPanel.setLayout(null);
		this.mainPanel.add(this.btnPanel);

		/** SAVE BUTTON **/
		this.saveBtn = new SaveButton();
		this.saveBtn.setBounds(20, 0, 100, 40);
		this.btnPanel.add(this.saveBtn);
//		this.saveBtn.addActionListener();

		/** DELETE BUTTON **/
		this.deleteBtn = new DeleteButton();
		this.deleteBtn.setBounds(20, 50, 100, 40);
		this.btnPanel.add(this.deleteBtn);

		/** RESET BUTTON **/
		this.resetBtn = new ResetButton();
		this.resetBtn.setBounds(20, 100, 100, 40);
		this.btnPanel.add(this.resetBtn);

		/********************** TABLE ON MAIN PANEL **************************/
		this.tbPanel = new JPanel();
		this.tbPanel.setBackground(Color.red);
		this.tbPanel.setBounds(10, 250, 800, 200);
		this.tbPanel.setLayout(null);
		this.mainPanel.add(this.tbPanel);

		/** INITIALIZE ZEBRA TABLE **/
		this.table = new JTable(new CustomerModelData()) {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				// Alternate row color
				if (!isRowSelected(row))
					c.setBackground(row % 2 == 0 ? new Color(230, 230, 230) : getBackground());
				return c;
			}
		};
		/** ROLL OVER ROW **/
		this.table.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				if (row > -1) {
					// easiest way:
					table.clearSelection();
					table.setRowSelectionInterval(row, row);
				} else {
					table.setOpaque(true);
					table.setSelectionBackground(new Color(140, 140, 140));
				}
			}
		});
		/** GET SELECTED VALUE **/
		this.table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					int rowIndex = table.getSelectedRow();// get index of selected row
					/** GET MANUALLY ALL ROW **/
					selectedRow.setId((Long) table.getModel().getValueAt(rowIndex, 0));
					selectedRow.setFullName((String) table.getModel().getValueAt(rowIndex, 1));
					selectedRow.setPhone((String) table.getModel().getValueAt(rowIndex, 2));
					selectedRow.setDob((java.sql.Date) table.getModel().getValueAt(rowIndex, 3));
				}
				/** DISPLAY TO TEXT FIELD **/
				infoTfList[0].setText(selectedRow.getId().toString());
				infoTfList[1].setText(selectedRow.getFullName());
				infoTfList[2].setText(selectedRow.getPhone());
				dateChooser.setDate(selectedRow.getDob());

			}
		});
		/********************** INITIALIZE SCROLL PANE **************************/
		this.pane = new JScrollPane(this.table);
		this.pane.setBounds(0, 0, 800, 200);
		/** ADD SCROLL PANE TO MAIN PANEL **/
		this.tbPanel.add(this.pane);
		
		/********************** bắt sự kiện text field **************************/
//		this.infoTfList[0].getDocument().addDocumentListener(new DocumentListener() {
//
//			public void changedUpdate(DocumentEvent arg0) {
//
//			}
//
//			public void insertUpdate(DocumentEvent arg0) {
//				System.out.println("IT WORKS");
////				panel.setPrice(panel.countTotalPrice(TabPanel.this));
//			}
//
//			public void removeUpdate(DocumentEvent arg0) {
//				System.out.println("IT WORKS");
////				panel.setPrice(panel.countTotalPrice(TabPanel.this));
//			}
//		});

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(1040, 700));
		frame.setLocationRelativeTo(null); // this will center your application
		frame.setLayout(new BorderLayout());

		CustomerPanel pp = new CustomerPanel();

		frame.add(pp);
		frame.setVisible(true);

	}
}
