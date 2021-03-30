package xxminhmie.sgu.javagui.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import xxminhmie.sgu.javagui.gui.common.DeleteButton;
import xxminhmie.sgu.javagui.gui.common.ResetButton;
import xxminhmie.sgu.javagui.gui.common.SaveButton;
import xxminhmie.sgu.javagui.gui.modeltable.SupplierModelData;
import xxminhmie.sgu.javagui.gui.validator.EmailValidator;
import xxminhmie.sgu.javagui.gui.validator.PhoneValidator;
import xxminhmie.sgu.javagui.gui.validator.WhiteSpaceValidator;
import xxminhmie.sgu.javagui.model.SupplierModel;
import xxminhmie.sgu.javagui.service.impl.SupplierService;

public class SupplierPanel extends JPanel{
	// COMMUNICATE WITH BACK END
	SupplierService service = new SupplierService();

	/******************************************************************
	 * 
	 * DECLARE COMPONENTS ON PANEL
	 * 
	 ******************************************************************/
	JLabel mainLabel;// "Customer Management",BorderLayout.NORTH
	JPanel mainPanel;// BorderLayout.CENTER
	JPanel infoPanel;// Contains list of text field
	// List of text field displays detail information of one customer hold by
	// selectedRow variable
	JTextField[] tfList;

	JPanel searchPanel;// Contains SEARCH text field
	JTextField search;

	JPanel btnPanel;// Contains 3 button: save, reset, delete
	SaveButton saveBtn;
	DeleteButton deleteBtn;
	ResetButton resetBtn;

	JPanel tbPanel;// Contains j table on scroll pane
	JScrollPane pane;
	JTable table;
	SupplierModelData model;

	SupplierModel selectedRow = new SupplierModel();// Customer is being selected from Table
	java.util.List<Long> idSelectedRowList = new java.util.ArrayList<Long>();// Contains list of customer's ID to delete
	int selectedRowIndex;
	Boolean flagReset = false;

	/******************************************************************
	 * 
	 * CONSTRUCTOR
	 * 
	 ******************************************************************/
	public SupplierPanel() {
		/**************************
		 * INITIALIZE BG PANEL include main label, main panel
		 **************************/
		this.setBackground(AbstractPanel.PanelBg);
		this.setPreferredSize(new Dimension(AbstractPanel.PanelWidth, AbstractPanel.PanelHeight));
		this.setLayout(new BorderLayout());

		/***************************
		 * MAIN LABEL OF BG PANEL
		 ***************************/
		this.mainLabel = new JLabel("Supplier Management");
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
		this.searchPanel.setBounds(0, 0, 1200, 40);
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
		this.infoPanel.setBounds(0, 40, 330 - 40, 220);
		this.infoPanel.setOpaque(true);
		this.infoPanel.setLayout(null);
		this.mainPanel.add(this.infoPanel, BorderLayout.WEST);

		String[] infoName = { "ID: ", "Name: ", "Phone: ", "Email: ", "Address: " };
		this.tfList = new JTextField[5];
		int x = 10;
		int y = 20;
		// length - 1 for j date chooser used to set birth
		// this for loop initialize label and text field, set position for both
		for (int i = 0; i < tfList.length; i++) {
			JLabel label = new JLabel(infoName[i]);
			label.setBounds(x, y, 100, 30);
			tfList[i] = new JTextField();
			tfList[i].setBounds(x + 80, y, 200, 20);
			infoPanel.add(label);
			infoPanel.add(tfList[i]);
			y += 30;
		}

		/** set read - only for ID text field **/
		tfList[0].setEditable(false);
		tfList[0].setForeground(new Color(108, 108, 108));

		/*****************************
		 * LIST BUTTON ON MAIN PANEL
		 *****************************/
		this.btnPanel = new JPanel();
		this.btnPanel.setBounds(300, 62, 200, 160);
		this.btnPanel.setLayout(null);
		this.mainPanel.add(this.btnPanel);

		/** SAVE BUTTON **/
		this.saveBtn = new SaveButton(20,0);
		this.btnPanel.add(this.saveBtn);
		this.saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveButtonClicked();
			}
		});

		/** DELETE BUTTON **/
		this.deleteBtn = new DeleteButton(20,30);
		this.btnPanel.add(this.deleteBtn);
		this.deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteButtonClicked();
			}
		});

		/** RESET BUTTON **/
		this.resetBtn = new ResetButton(20,60);
		this.btnPanel.add(this.resetBtn);
		this.resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetButtonClicked();
			}
		});

		/****************************************************************
		 * 
		 * TABLE ON MAIN PANEL
		 * 
		 ***************************************************************/
		this.tbPanel = new JPanel();
		this.tbPanel.setBackground(Color.red);
		this.tbPanel.setBounds(10, 260, 1000, 360);
		this.tbPanel.setLayout(null);
		this.mainPanel.add(this.tbPanel);

		/** INITIALIZE ZEBRA TABLE **/
		this.model = new SupplierModelData();
		this.table = new JTable(model);
		this.table.setDefaultRenderer(Object.class, new Renderer());

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
					selectedRow.setName((String) table.getModel().getValueAt(rowIndex, 1));
					selectedRow.setPhone((String) table.getModel().getValueAt(rowIndex, 2));
					selectedRow.setEmail((String) table.getModel().getValueAt(rowIndex, 3));
					selectedRow.setAddress((String) table.getModel().getValueAt(rowIndex, 4));

				}
				/** DISPLAY TO TEXT FIELD **/
				tfList[0].setText(selectedRow.getId().toString());
				tfList[1].setText(selectedRow.getName());
				tfList[2].setText(selectedRow.getPhone());
				tfList[3].setText(selectedRow.getEmail());
				tfList[4].setText(selectedRow.getAddress());
				getAllSelectedRow(table);
			}
		});
		/********************** INITIALIZE SCROLL PANE **************************/
		this.pane = new JScrollPane(this.table);
		this.pane.setBounds(0, 0, 1000, 360);
		/** ADD SCROLL PANE TO MAIN PANEL **/
		this.tbPanel.add(this.pane);

		/****************************************************************
		 * 
		 * TEXT FIELD KEY LISTENER
		 * 
		 */
		this.tfList[1].addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfList[1 + 1].requestFocus();
				}
			}

		});
		this.tfList[2].addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfList[2 + 1].requestFocus();
				}
			}

		});
		this.tfList[3].addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfList[3 + 1].requestFocus();
				}
			}

		});
		/****************************************************************
		 * 
		 * DOCUMENT LISTENER OF text field
		 * 
		 */
		// first name
//		this.tfList[1].getDocument().addDocumentListener(new DocumentListener() {
//
//			public void changedUpdate(DocumentEvent arg0) {
//			}
//
//			public void insertUpdate(DocumentEvent arg0) {
//				change(1, selectedRow.getFirstName());
//			}
//
//			public void removeUpdate(DocumentEvent arg0) {
//				change(1, selectedRow.getFirstName());
//
//			}
//		});
//		// last name
//		this.tfList[2].getDocument().addDocumentListener(new DocumentListener() {
//			public void changedUpdate(DocumentEvent arg0) {
//
//			}
//
//			public void insertUpdate(DocumentEvent arg0) {
//				change(2, selectedRow.getLastName());
//
//			}
//
//			public void removeUpdate(DocumentEvent arg0) {
//				change(2, selectedRow.getLastName());
//
//			}
//		});
//
//		// phone
//		this.tfList[3].getDocument().addDocumentListener(new DocumentListener() {
//			public void changedUpdate(DocumentEvent arg0) {
//			}
//
//			public void insertUpdate(DocumentEvent arg0) {
//				change(3, selectedRow.getPhone());
//			}
//
//			public void removeUpdate(DocumentEvent arg0) {
//				change(3, selectedRow.getPhone());
//			}
//		});
//		// email
//		this.tfList[4].getDocument().addDocumentListener(new DocumentListener() {
//
//			public void changedUpdate(DocumentEvent arg0) {
//			}
//
//			public void insertUpdate(DocumentEvent arg0) {
//				change(4, selectedRow.getEmail());
//			}
//
//			public void removeUpdate(DocumentEvent arg0) {
//				change(4, selectedRow.getEmail());
//			}
//		});
//		// address
//		this.tfList[5].getDocument().addDocumentListener(new DocumentListener() {
//
//			public void changedUpdate(DocumentEvent arg0) {
//			}
//
//			public void insertUpdate(DocumentEvent arg0) {
//				change(5, selectedRow.getAddress());
//			}
//
//			public void removeUpdate(DocumentEvent arg0) {
//				change(5, selectedRow.getAddress());
//			}
//		});
	}

	/******************************************************************
	 * 
	 * TEXT FIELD HANDLE
	 * 
	 ******************************************************************/

	public void change(int index, String str) {
		if (flagReset == false) {
			if (str != null && tfList[index] != null) {
				if (str.equals(tfList[index].getText()) == false) {
					tfList[index].setForeground(AbstractPanel.DocumentListener);
				} else {
					tfList[index].setForeground(Color.BLACK);
				}
			}
		} else {
			tfList[index].setForeground(Color.BLACK);
		}

	}

	/******************************************************************
	 * 
	 * SAVE BUTTON HANDLE
	 * 
	 ******************************************************************/
	protected int saveButtonClicked() {
		/*
		 * Get informations from text field
		 */

		// ID
		Long id = null;
		if (this.tfList[0].getText().equals("")) {
			// new
			id = -1L;
		} else {
			try {
				id = Long.parseLong(this.tfList[0].getText());
			} catch (java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "ID: " + e.getMessage());
			}
		}
		// FIRST NAME
		String firstName = null;
		if (this.tfList[1].getText().equals("") == false) {
			firstName = this.tfList[1].getText();
			firstName = WhiteSpaceValidator.validate(firstName);
		} else {
			JOptionPane.showMessageDialog(null, "First Name must be not null!");
			this.tfList[1].requestFocus();
			return -1;
		}
		// LAST NAME
		String lastName = null;
		if (this.tfList[2].getText().equals("") == false) {
			lastName = this.tfList[2].getText();
			lastName = WhiteSpaceValidator.validate(lastName);
		} else {
			JOptionPane.showMessageDialog(null, "Last Name must be not null!");
			this.tfList[2].requestFocus();
			return -1;
		}
		// PHONE
		String phone = null;
		if (this.tfList[3].getText().equals("") == false) {
			phone = this.tfList[3].getText();
			phone = WhiteSpaceValidator.validate(phone);
			PhoneValidator phoneValidator = new PhoneValidator();
			if (!phoneValidator.validate(phone)) {
				JOptionPane.showMessageDialog(null, "Invalid Phone! Please insert again!");
				this.tfList[3].requestFocus();
				return -1;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Phone must be not null!");
			this.tfList[3].requestFocus();
			return -1;
		}
		// GET EMAIL
		String email = null;
		if (this.tfList[4].getText().equals("") == false) {
			email = this.tfList[4].getText();
			email = WhiteSpaceValidator.validate(email);
			EmailValidator emailValidator = new EmailValidator();
			if (!emailValidator.validate(email)) {
				JOptionPane.showMessageDialog(null, "Invalid Email! Please insert again!");
				this.tfList[4].requestFocus();
				return -1;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Email must be not null!");
			this.tfList[4].requestFocus();
			return -1;
		}
		// GET ADDRESS
		String address = null;
		if (this.tfList[5].getText().equals("") == false) {
			address = this.tfList[5].getText();
		} else {
			JOptionPane.showMessageDialog(null, "Address must be not null!");
			this.tfList[5].requestFocus();
			return -1;
		}

		

		/*
		 * Confirm to save this changes
		 */
//		int click = JOptionPane.showConfirmDialog(null, "Do you want to save this changes?");
//		if (click == JOptionPane.YES_OPTION) {
//			boolean flag = false;
//			if (id > 0) {
//				/* UPDATE */
//				if (service.update(new SupplierModel(id, firstName, lastName, phone, email, dob, address)) != null) {
//					flag = true;
//				}
//			} else {
//				/* CREATE */
//				if (service.save(new SupplierModel(firstName, lastName, phone, email, dob, address)) != null) {
//					flag = true;
//				}
//				;
//			}
//			if (flag == true) {
//				this.model.loadData(this.table);
//				for (int i = 1; i < this.tfList.length; i++) {
//					this.tfList[i].setForeground(Color.BLACK);
//				}
//				table.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
//			}
//		}
//		if (click == JOptionPane.NO_OPTION) {
//
//		}
//		if (click == JOptionPane.CANCEL_OPTION) {
//
//		}
//		if (click == JOptionPane.CLOSED_OPTION) {
//		}
		return 0;

	}

	/******************************************************************
	 * 
	 * DELETE BUTTON HANDLE
	 * 
	 ******************************************************************/
	protected void deleteButtonClicked() {
		if (selectedRowIndex > 0) {
			int click = JOptionPane.showConfirmDialog(null, "Are you sure for delete this staff?");
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

	/******************************************************************
	 * 
	 * RESET BUTTON HANDLE
	 * 
	 ******************************************************************/
	protected void resetButtonClicked() {
		this.flagReset = true;
		for (int i = 0; i < this.tfList.length; i++) {
			this.tfList[i].setText("");
		}

	}

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

}

