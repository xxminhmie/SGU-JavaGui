package xxminhmie.sgu.javagui.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import com.toedter.calendar.JDateChooser;

import xxminhmie.sgu.javagui.gui.ApplicationGUI;
import xxminhmie.sgu.javagui.gui.common.DeleteButton;
import xxminhmie.sgu.javagui.gui.common.ResetButton;
import xxminhmie.sgu.javagui.gui.common.SaveButton;
import xxminhmie.sgu.javagui.gui.modeltable.CustomerModelData;
import xxminhmie.sgu.javagui.gui.validator.EmailValidator;
import xxminhmie.sgu.javagui.gui.validator.PhoneValidator;
import xxminhmie.sgu.javagui.gui.validator.WhiteSpaceValidator;
import xxminhmie.sgu.javagui.model.CustomerModel;
import xxminhmie.sgu.javagui.service.impl.CustomerService;

public class CustomerPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	/******************************************************************
	 * SETTING WIDTH, HEIGHT, COLOR OF PANEL
	 ******************************************************************/
	public static final int PanelWidth = 1040;
	public static final int PanelHeight = ApplicationGUI.FrameHeight;
	public static final Color PanelBg = Color.WHITE;

	/******************************************************************
	 * COMMUNICATE WITH BACK END
	 ******************************************************************/
	CustomerService service = new CustomerService();

	/******************************************************************
	 * DECLARE COMPONENTS ON PANEL
	 ******************************************************************/
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

	CustomerModel selectedRow = new CustomerModel();// Customer is being selected from Table
	java.util.List<Long> idSelectedRowList = new java.util.ArrayList<Long>();// Contains list of customer's ID to delete
	int selectedRowIndex;
	Boolean enableSavebtn;

	/******************************************************************
	 * CONSTRUCTOR
	 ******************************************************************/
	public CustomerPanel() {
		/**************************
		 * INITIALIZE BG PANEL include main label, main panel
		 **************************/
		this.setBackground(PanelBg);
		this.setPreferredSize(new Dimension(PanelWidth, PanelHeight));
		this.setLayout(new BorderLayout());

		/***************************
		 * MAIN LABEL OF BG PANEL
		 ***************************/
		this.mainLabel = new JLabel("Customer Management");
		this.mainLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		this.add(this.mainLabel, BorderLayout.NORTH);

		/**************************
		 * MAIN PANEL INCLUDE text fields, buttons, table
		 **************************/
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(null);
		this.add(this.mainPanel, BorderLayout.CENTER);

		/*****************************
		 * SEARCH PANEL ON MAIN PANEL
		 *****************************/
		this.searchPanel = new JPanel();
		this.searchPanel.setBounds(0, 0, 330, 40);
		this.searchPanel.setLayout(null);
		this.mainPanel.add(this.searchPanel);

		this.search = new JTextField("Search...");
		this.search.setForeground(new Color(144, 144, 144));
		this.search.setBounds(10, 10, 300, 30);
		this.search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				search.selectAll();
				search.setForeground(Color.BLACK);
			}
		});
		this.searchPanel.add(this.search);

		this.search.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				model.loadData(table, search.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				model.loadData(table, search.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				model.loadData(table, search.getText());
			}

		});
		/**********************************
		 * LIST OF TEXT FIELD ON MAIN PANEL
		 **********************************/
		this.infoPanel = new JPanel();
		this.infoPanel.setBounds(0, 30, 330, 220);
		this.infoPanel.setOpaque(true);
		this.infoPanel.setLayout(null);
		this.mainPanel.add(this.infoPanel, BorderLayout.WEST);

		String[] infoName = { "ID: ", "Full Name: ", "Phone: ", "Email: ", "DoB: " };
		this.infoTfList = new JTextField[4];
		int x = 10;
		int y = 20;
		// length - 1 for j date chooser used to set birth
		// this for loop initialize label and text field, set position for both
		for (int i = 0; i < this.infoTfList.length; i++) {
			JLabel label = new JLabel(infoName[i]);
			label.setBounds(x, y, 100, 30);
			this.infoPanel.add(label);
			this.infoTfList[i] = new JTextField();
			this.infoTfList[i].setBounds(x + 100, y, 200, 30);
			y += 40;
			this.infoPanel.add(this.infoTfList[i]);
		}
		/** J Date Chooser for date of birth **/
		//https://www.codota.com/code/java/methods/com.toedter.calendar.JDateChooser/setDateFormatString
		JLabel label = new JLabel(infoName[4]);
		label.setBounds(x, y, 100, 30);
		this.infoPanel.add(label);
		this.dateChooser = new JDateChooser();
		dateChooser.setBounds(x + 100, y, 200, 30);
		dateChooser.getJCalendar().setBounds(0, 0, 600, 200);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		this.infoPanel.add(dateChooser);

		/** set read - only for ID text field **/
		this.infoTfList[0].setEditable(false);
		this.infoTfList[0].setForeground(new Color(108, 108, 108));

		/*****************************
		 * LIST BUTTON ON MAIN PANEL
		 *****************************/
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
		this.tbPanel.setBounds(10, 260, 1000, 360);
		this.tbPanel.setLayout(null);
		this.mainPanel.add(this.tbPanel);

		/** INITIALIZE ZEBRA TABLE **/
		this.model = new CustomerModelData();
		this.table = new JTable(model);
		this.table.setDefaultRenderer(Object.class, new Renderer());

		// cach 2
//		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
//					boolean hasFocus, int row, int column) {
//				JLabel label = 
//						(JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,column);
//				Color c = new Color(140, 140, 140);
//
//				label.setBackground(c);
//				return label;
//			}
//		});

		/** GET SELECTED VALUE TO DISPLAY ON TEXT FIELD **/
		this.table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {

					int rowIndex = table.getSelectedRow();// get index of selected rowIndex
					selectedRowIndex = rowIndex;
					/** GET MANUALLY ALL ROW **/
					selectedRow.setId((Long) table.getModel().getValueAt(rowIndex, 0));
					selectedRow.setFullName((String) table.getModel().getValueAt(rowIndex, 1));
					selectedRow.setPhone((String) table.getModel().getValueAt(rowIndex, 2));
					selectedRow.setEmail((String) table.getModel().getValueAt(rowIndex, 3));
					selectedRow.setDob((java.sql.Date) table.getModel().getValueAt(rowIndex, 4));

				}
				/** DISPLAY TO TEXT FIELD **/
				infoTfList[0].setText(selectedRow.getId().toString());
				infoTfList[1].setText(selectedRow.getFullName());
				infoTfList[2].setText(selectedRow.getPhone());
				infoTfList[3].setText(selectedRow.getEmail());
				dateChooser.setDate(selectedRow.getDob());
				getAllSelectedRow(table);
			}
		});
		/********************** INITIALIZE SCROLL PANE **************************/
		this.pane = new JScrollPane(this.table);
		this.pane.setBounds(0, 0, 1000, 360);
		/** ADD SCROLL PANE TO MAIN PANEL **/
		this.tbPanel.add(this.pane);

		/********************** bắt sự kiện text field **************************/
		// full name
		this.infoTfList[1].getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent arg0) {

			}

			public void insertUpdate(DocumentEvent arg0) {
				if (selectedRow.getFullName() != null && infoTfList[1] != null) {
					if (selectedRow.getFullName().equals(infoTfList[1].getText()) == false) {
						infoTfList[1].setForeground(Color.BLUE);
					} else {
						infoTfList[1].setForeground(Color.BLACK);
					}
				}

			}

			public void removeUpdate(DocumentEvent arg0) {
				if (selectedRow.getFullName() != null && infoTfList[1] != null) {
					if (selectedRow.getFullName().equals(infoTfList[1].getText()) == false) {
						infoTfList[1].setForeground(Color.BLUE);
					} else {
						infoTfList[1].setForeground(Color.BLACK);
					}
				}
			}
		});

		// phone
		this.infoTfList[2].getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent arg0) {
			}

			public void insertUpdate(DocumentEvent arg0) {
				if (selectedRow.getPhone() != null && infoTfList[2] != null) {
					if (selectedRow.getPhone().toLowerCase().equals(infoTfList[2].getText().toLowerCase()) == false) {
						infoTfList[2].setForeground(Color.BLUE);
					} else {
						infoTfList[2].setForeground(Color.BLACK);
					}
				}
			}

			public void removeUpdate(DocumentEvent arg0) {
				if (selectedRow.getPhone() != null && infoTfList[2] != null) {
					if (selectedRow.getPhone().toLowerCase().equals(infoTfList[2].getText().toLowerCase()) == false) {
						infoTfList[2].setForeground(Color.BLUE);
					} else {
						infoTfList[2].setForeground(Color.BLACK);
					}
				}
			}
		});
		// email
		this.infoTfList[3].getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent arg0) {
			}

			public void insertUpdate(DocumentEvent arg0) {
				if (selectedRow.getEmail() != null && infoTfList[3] != null) {
					if (selectedRow.getEmail().equals(infoTfList[3].getText()) == false) {
						infoTfList[3].setForeground(Color.BLUE);
					} else {
						infoTfList[3].setForeground(Color.BLACK);
					}
				}
			}

			public void removeUpdate(DocumentEvent arg0) {
				if (selectedRow.getEmail() != null && infoTfList[3] != null) {
					if (selectedRow.getEmail().equals(infoTfList[3].getText()) == false) {
						infoTfList[3].setForeground(Color.BLUE);
					} else {
						infoTfList[3].setForeground(Color.BLACK);
					}
				}
			}
		});
	}

	protected int saveButtonClicked() {
//**************** GET ID from text field
		Long id = null;
		if (this.infoTfList[0].getText().equals("")) {
			id = -1L;
		} else {
			try {
				id = Long.parseLong(this.infoTfList[0].getText());
			} catch (java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
//**************** GET FULL NAME from text field
		String fullName = null;
		if (this.infoTfList[1].getText().equals("") == false) {
			fullName = this.infoTfList[1].getText();
			fullName = WhiteSpaceValidator.validate(fullName);
		}

//**************** GET PHONE from text field
		String phone = null;
		if (this.infoTfList[2].getText().equals("") == false) {
			phone = this.infoTfList[2].getText();
			phone = WhiteSpaceValidator.validate(phone);
			PhoneValidator phoneValidator = new PhoneValidator();
			if (!phoneValidator.validate(phone)) {
				JOptionPane.showMessageDialog(null, "Invalid Phone");
				return -1;
			}
		}

//**************** GET EMAIL from text field
		String email = null;
		if (this.infoTfList[3].getText().equals("") == false) {
			email = this.infoTfList[3].getText();
			email = WhiteSpaceValidator.validate(email);
			EmailValidator emailValidator = new EmailValidator();
			if (!emailValidator.validate(email)) {
				JOptionPane.showMessageDialog(null, "Invalid Email");
				return -1;
			}
		}

//**************** GET EMAIL from date chooser
		Date dob = null;
		try {
			dob = new Date(this.dateChooser.getDate().getTime());
		} catch (java.lang.NullPointerException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return -1;
		}

//******************************* CONFIRM SAVE ************************************************
		int click = JOptionPane.showConfirmDialog(null, "Do you want to save this changes?");
		if (click == JOptionPane.YES_OPTION) {
			// (Long id,String fullName, String phone, String email,Date dob)
			boolean flag = false;
			/*------------------------------ UPDATE ----------------------------------*/
			if (id > 0) {
				if (service.update(new CustomerModel(id, fullName, phone, email, dob)) != null) {
					flag = true;
				}
			}
			/*------------------------------ CREATE NEW ----------------------------------*/
			else {
//				System.out.println(fullName.toString() + phone.toString() + email.toString() + dob.toString());
				if (service.save(new CustomerModel(fullName, phone, email, dob)) != null) {
					flag = true;
				}
				;
			}
			if (flag == true) {
				this.model.loadData(this.table);
				this.infoTfList[1].setForeground(Color.BLACK);
				this.infoTfList[2].setForeground(Color.BLACK);
				this.infoTfList[3].setForeground(Color.BLACK);
				table.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
			}
		}
		if (click == JOptionPane.NO_OPTION) {

		}
		if (click == JOptionPane.CANCEL_OPTION) {

		}
		if (click == JOptionPane.CLOSED_OPTION) {
		}

		return 0;

	}

	protected void deleteButtonClicked() {
		if (selectedRowIndex > 0) {
			int click = JOptionPane.showConfirmDialog(null, "Are you sure for delete this customer?");
			if (click == JOptionPane.YES_OPTION) {
				// https://stackoverflow.com/questions/2016654/classcastexception-when-casting-object-array-to-long-array
				this.service.delete(this.idSelectedRowList.toArray(new Long[this.idSelectedRowList.size()]));
				this.model.loadData(this.table);
			}
			if (click == JOptionPane.NO_OPTION) {

			}

			if (click == JOptionPane.CANCEL_OPTION) {

			}
			if (click == JOptionPane.CLOSED_OPTION) {
			}
		} else {
			JOptionPane.showMessageDialog(null, "You have not selected a value to delete!");
		}

	}

	protected void resetButtonClicked() {
		for (int i = 0; i < this.infoTfList.length; i++) {
			this.infoTfList[i].setText("");
		}
		this.dateChooser.setDate(null);

	}
//	protected int getChange() {
//		//selectedRow and list text field
//		if(selectedRow.getFullName().toLowerCase().equals(this.infoTfList[1].getText().toLowerCase())) {
//			return -1;
//		}
//		if(selectedRow.getEmail().equals(this.infoTfList[3].getText())) {
//			return -1;
//		}
//		if(selectedRow.getPhone().toLowerCase().equals(this.infoTfList[2].getText().toLowerCase())) {
//			return -1;
//		}
//		if(selectedRow.getDob().toString().toLowerCase().equals(this.dateChooser.getDate().toString().toLowerCase())) {
//			return -1;
//		}
//
//
//	}

//	protected CustomerModel textFieldToModel() {
//		
//	}
	public void getAllSelectedRow(JTable entryTable) {
//		AbstractTableModel model = (AbstractTableModel) entryTable.getModel();
		if (entryTable.getRowCount() > 0) {
			if (entryTable.getSelectedRowCount() > 0) {
				int selectedRow[] = entryTable.getSelectedRows();
				idSelectedRowList.clear();
				for (int i : selectedRow) {
					Long id = (Long) entryTable.getValueAt(i, 0);
					idSelectedRowList.add(id);
				}
			}
		}
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
		/*
		 * Disable Auto Focus
		 * https://stackoverflow.com/questions/34778965/how-to-remove-auto-focus-in-
		 * swing
		 * 
		 */
		frame.pack();
		frame.getContentPane().requestFocusInWindow();
		

	}

}
