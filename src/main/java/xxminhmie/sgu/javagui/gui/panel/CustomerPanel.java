package xxminhmie.sgu.javagui.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;

import xxminhmie.sgu.javagui.gui.ApplicationGUI;
import xxminhmie.sgu.javagui.gui.common.DeleteButton;
import xxminhmie.sgu.javagui.gui.common.ResetButton;
import xxminhmie.sgu.javagui.gui.common.SaveButton;
import xxminhmie.sgu.javagui.gui.table.CustomRenderer;
import xxminhmie.sgu.javagui.gui.table.CustomerModelData;
import xxminhmie.sgu.javagui.model.CustomerModel;
import xxminhmie.sgu.javagui.service.impl.CustomerService;

public class CustomerPanel extends JPanel {
	/**********************
	 * SETTING WIDTH, HEIGHT, COLOR OF PANEL
	 **************************/
	public static final int PanelWidth = 1040;
	public static final int PanelHeight = ApplicationGUI.FrameHeight;
	public static final Color PanelBg = Color.WHITE;

	/********************** DECLARE COMPONENT ON PANEL **************************/
	JLabel mainLabel;// "Customer Management",BorderLayout.NORTH
	JPanel mainPanel;// BorderLayout.CENTER
	JPanel infoPanel;// Contains list of text field
	// List of text field displays detail information of one customer hold by
	// selectedRow variable
	JTextField[] infoTfList;
	JDateChooser dateChooser;

	JPanel searchPanel;// Contains SEARCH text field
	JTextField search;

	JPanel btnPanel;// Contains 3 button: save, reset, delete
	SaveButton saveBtn;
	DeleteButton deleteBtn;
	ResetButton resetBtn;

	JPanel tbPanel;// Contains j table on scroll pane
	JScrollPane pane;
	JTable table;
	CustomerModelData model;

	CustomerModel selectedRow = new CustomerModel();// Customer is selected from Table
	int selectedRowIndex;
	Boolean enableSavebtn;

	public CustomerPanel() {
		/**********************
		 * INITIALIZE BG PANEL include main label, main panel
		 **************************/
		this.setBackground(PanelBg);
		this.setPreferredSize(new Dimension(PanelWidth, PanelHeight));
		this.setLayout(new BorderLayout());

		/********************** MAIN LABEL OF BG PANEL **************************/
		this.mainLabel = new JLabel("Customer Management");
		this.mainLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		this.add(this.mainLabel, BorderLayout.NORTH);

		/**********************
		 * MAIN PANEL INCLUDE text fields, buttons, table
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
		 * LIST OF TEXT FIELD ON MAIN PANEL
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
		// length - 1 for j date chooser used to set birth
		// this for loop initialize label and text field, set position for both
		for (int i = 0; i < this.infoTfList.length - 1; i++) {
			JLabel label = new JLabel(infoName[i]);
			label.setBounds(x, y, 100, 30);
			this.infoPanel.add(label);
			this.infoTfList[i] = new JTextField();
			this.infoTfList[i].setBounds(x + 100, y, 200, 30);
			y += 40;
			this.infoPanel.add(this.infoTfList[i]);
		}
		/** J Date Chooser for date of birth **/
		JLabel label = new JLabel(infoName[3]);
		label.setBounds(x, y, 100, 30);
		this.infoPanel.add(label);
		this.dateChooser = new JDateChooser();
		dateChooser.setBounds(x + 100, y, 200, 30);
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
		this.saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveButtonClicked();
			}
		});

		/** DELETE BUTTON **/
		this.deleteBtn = new DeleteButton();
		this.deleteBtn.setBounds(20, 50, 100, 40);
		this.btnPanel.add(this.deleteBtn);
		this.deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteButtonClicked();
			}
		});

		/** RESET BUTTON **/
		this.resetBtn = new ResetButton();
		this.resetBtn.setBounds(20, 100, 100, 40);
		this.btnPanel.add(this.resetBtn);
		this.resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetButtonClicked();
			}
		});

		/********************** TABLE ON MAIN PANEL **************************/
		this.tbPanel = new JPanel();
		this.tbPanel.setBackground(Color.red);
		this.tbPanel.setBounds(10, 250, 800, 200);
		this.tbPanel.setLayout(null);
		this.mainPanel.add(this.tbPanel);

		/** INITIALIZE ZEBRA TABLE **/
		this.model = new CustomerModelData();
//		this.table = new JTable(new CustomerModelData()) {
		this.table = new JTable(model) {
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
		//cach 2
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);
				Color c = new Color(140, 140, 140);

				label.setBackground(c);
				return label;
			}
		});

		/** GET SELECTED VALUE **/
		this.table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {

					int rowIndex = table.getSelectedRow();// get index of selected rowIndex
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

	// update customer
	protected void saveButtonClicked() {
		int click = JOptionPane.showConfirmDialog(null, "Are you are to save this changes?");
		if (click == JOptionPane.YES_OPTION) {
			Long id = Long.parseLong(this.infoTfList[0].getText());
			String fullName = this.infoTfList[1].getText();
			String email = this.infoTfList[2].getText();
			String phone = this.infoTfList[2].getText();
//			Date dob = new Date(this.dateChooser.getDate().getTime());
			long millis = System.currentTimeMillis();
			java.sql.Date dob = new java.sql.Date(millis);
			CustomerService service = new CustomerService();
			// public CustomerModel(Long id,String fullName, String phone, String email,
			// Date dob) {
			service.update(new CustomerModel(id, fullName, phone, email, dob));
			this.model.loadData(this.table);
		}
		if (click == JOptionPane.NO_OPTION) {
			infoTfList[0].setText(selectedRow.getId().toString());
			infoTfList[1].setText(selectedRow.getFullName());
			infoTfList[2].setText(selectedRow.getPhone());
			dateChooser.setDate(selectedRow.getDob());
		}
		if (click == JOptionPane.CANCEL_OPTION) {

		}
		if (click == JOptionPane.CLOSED_OPTION) {
		}
	}

	protected void deleteButtonClicked() {

	}

	protected void resetButtonClicked() {

	}

//	protected CustomerModel textFieldToModel() {
//		
//	}
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
