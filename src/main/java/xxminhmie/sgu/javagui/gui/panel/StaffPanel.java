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
import java.sql.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JDateChooser;

import xxminhmie.sgu.javagui.gui.common.DeleteButton;
import xxminhmie.sgu.javagui.gui.common.ResetButton;
import xxminhmie.sgu.javagui.gui.common.SaveButton;
import xxminhmie.sgu.javagui.gui.modeltable.StaffModelData;
import xxminhmie.sgu.javagui.gui.validator.EmailValidator;
import xxminhmie.sgu.javagui.gui.validator.PhoneValidator;
import xxminhmie.sgu.javagui.gui.validator.WhiteSpaceValidator;
import xxminhmie.sgu.javagui.model.StaffModel;
import xxminhmie.sgu.javagui.service.impl.StaffService;

public class StaffPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	// COMMUNICATE WITH BACK END
	StaffService service = new StaffService();

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
	StaffModelData model;

	StaffModel selectedRow = new StaffModel();// Customer is being selected from Table
	java.util.List<Long> idSelectedRowList = new java.util.ArrayList<Long>();// Contains list of customer's ID to delete
	int selectedRowIndex;
	Boolean flagReset = false;

	/******************************************************************
	 * 
	 * CONSTRUCTOR
	 * 
	 ******************************************************************/
	public StaffPanel() {
		/**************************
		 * INITIALIZE BG PANEL include main label, main panel
		 **************************/
		this.setBackground(AbstractPanel.PanelBg);
		this.setPreferredSize(new Dimension(AbstractPanel.PanelWidth, AbstractPanel.PanelHeight));
		this.setLayout(new BorderLayout());

		/***************************
		 * MAIN LABEL OF BG PANEL
		 ***************************/
		this.mainLabel = new JLabel("Staff Manager");
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
		this.infoPanel.setBounds(0, 40, 330 * 2 - 40, 220);
		this.infoPanel.setOpaque(true);
		this.infoPanel.setLayout(null);
		this.mainPanel.add(this.infoPanel, BorderLayout.WEST);

		String[] infoName = { "ID: ", "First Name: ", "Last Name: ", "Phone: ", "Email: ", "Address: ", "DoB: " };
		this.infoTfList = new JTextField[6];
		int x = 10;
		int y = 20;
		// length - 1 for j date chooser used to set birth
		// this for loop initialize label and text field, set position for both
		for (int i = 0; i <= 2; i++) {
			JLabel label = new JLabel(infoName[i]);
			label.setBounds(x, y, 100, 30);
			this.infoTfList[i] = new JTextField();
			this.infoTfList[i].setBounds(x + 80, y, 200, 20);
			this.infoPanel.add(label);
			y += 30;
			this.infoPanel.add(this.infoTfList[i]);
		}
		x = 330;
		y = 20;
		for (int i = 3; i < this.infoTfList.length; i++) {
			JLabel label = new JLabel(infoName[i]);
			label.setBounds(x, y, 100, 30);
			this.infoTfList[i] = new JTextField();
			this.infoTfList[i].setBounds(x + 80, y, 200, 20);
			this.infoPanel.add(label);
			y += 30;
			this.infoPanel.add(this.infoTfList[i]);
		}
		/** J Date Chooser for date of birth **/
		// https://www.codota.com/code/java/methods/com.toedter.calendar.JDateChooser/setDateFormatString
		JLabel label = new JLabel(infoName[6]);
		label.setBounds(x, y, 100, 30);
		this.infoPanel.add(label);
		this.dateChooser = new JDateChooser();
		dateChooser.setBounds(x + 80, y, 200, 30);
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
		this.btnPanel.setBounds(620, 55, 200, 160);
		this.btnPanel.setLayout(null);
		this.mainPanel.add(this.btnPanel);

		/** SAVE BUTTON **/
		this.saveBtn = new SaveButton(20,0);
//		this.saveBtn.setBounds(20, 0, 100, 40);
		this.btnPanel.add(this.saveBtn);
		this.saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveButtonClicked();
			}
		});

		/** DELETE BUTTON **/
		this.deleteBtn = new DeleteButton(20,50);
//		this.deleteBtn.setBounds(20, 50, 100, 40);
		this.btnPanel.add(this.deleteBtn);
		this.deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteButtonClicked();
			}
		});

		/** RESET BUTTON **/
		this.resetBtn = new ResetButton(20,100);
//		this.resetBtn.setBounds(20, 100, 100, 40);
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
		this.tbPanel.setBounds(10, 260, 700, 360);
		this.tbPanel.setLayout(null);
		this.mainPanel.add(this.tbPanel);

		/** INITIALIZE ZEBRA TABLE **/
		this.model = new StaffModelData();
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
					selectedRow.setFirstName((String) table.getModel().getValueAt(rowIndex, 1));
					selectedRow.setLastName((String) table.getModel().getValueAt(rowIndex, 2));
					selectedRow.setPhone((String) table.getModel().getValueAt(rowIndex, 3));
					selectedRow.setEmail((String) table.getModel().getValueAt(rowIndex, 4));
					selectedRow.setAddress((String) table.getModel().getValueAt(rowIndex, 5));
					selectedRow.setDob((java.sql.Date) table.getModel().getValueAt(rowIndex, 6));

				}
				/** DISPLAY TO TEXT FIELD **/
				infoTfList[0].setText(selectedRow.getId().toString());
				infoTfList[1].setText(selectedRow.getFirstName());
				infoTfList[2].setText(selectedRow.getLastName());
				infoTfList[3].setText(selectedRow.getPhone());
				infoTfList[4].setText(selectedRow.getEmail());
				infoTfList[5].setText(selectedRow.getAddress());
				dateChooser.setDate(selectedRow.getDob());
				getAllSelectedRow(table);
			}
		});
		/********************** INITIALIZE SCROLL PANE **************************/
		this.pane = new JScrollPane(this.table);
		this.pane.setBounds(0, 0, 700, 360);
		/** ADD SCROLL PANE TO MAIN PANEL **/
		this.tbPanel.add(this.pane);

		/****************************************************************
		 * 
		 * TEXT FIELD KEY LISTENER
		 * 
		 */
		this.infoTfList[1].addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					infoTfList[1 + 1].requestFocus();
				}
			}

		});
		this.infoTfList[2].addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					infoTfList[2 + 1].requestFocus();
				}
			}

		});
		this.infoTfList[3].addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					infoTfList[3 + 1].requestFocus();
				}
			}

		});
		/*
		 * https://stackoverflow.com/questions/20945380/how-to-set-focus-on-jdatechooser
		 * -when-frame-is-loaded-in-swing
		 */
		this.infoTfList[4].addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					dateChooser.getDateEditor().getUiComponent().requestFocus();
				}
			}

		});
		this.dateChooser.getDateEditor().getUiComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					saveBtn.doClick();
				}
			}

		});
		/****************************************************************
		 * 
		 * DOCUMENT LISTENER OF text field
		 * 
		 */
		// first name
		this.infoTfList[1].getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent arg0) {
			}

			public void insertUpdate(DocumentEvent arg0) {
				change(1, selectedRow.getFirstName());
			}

			public void removeUpdate(DocumentEvent arg0) {
				change(1, selectedRow.getFirstName());

			}
		});
		// last name
		this.infoTfList[2].getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent arg0) {

			}

			public void insertUpdate(DocumentEvent arg0) {
				change(2, selectedRow.getLastName());

			}

			public void removeUpdate(DocumentEvent arg0) {
				change(2, selectedRow.getLastName());

			}
		});

		// phone
		this.infoTfList[3].getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent arg0) {
			}

			public void insertUpdate(DocumentEvent arg0) {
				change(3, selectedRow.getPhone());
			}

			public void removeUpdate(DocumentEvent arg0) {
				change(3, selectedRow.getPhone());
			}
		});
		// email
		this.infoTfList[4].getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent arg0) {
			}

			public void insertUpdate(DocumentEvent arg0) {
				change(4, selectedRow.getEmail());
			}

			public void removeUpdate(DocumentEvent arg0) {
				change(4, selectedRow.getEmail());
			}
		});
		// address
		this.infoTfList[5].getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent arg0) {
			}

			public void insertUpdate(DocumentEvent arg0) {
				change(5, selectedRow.getAddress());
			}

			public void removeUpdate(DocumentEvent arg0) {
				change(5, selectedRow.getAddress());
			}
		});
	}

	/******************************************************************
	 * 
	 * TEXT FIELD HANDLE
	 * 
	 ******************************************************************/

	public void change(int index, String str) {
		if (flagReset == false) {
			if (str != null && infoTfList[index] != null) {
				if (str.equals(infoTfList[index].getText()) == false) {
					infoTfList[index].setForeground(AbstractPanel.DocumentListener);
				} else {
					infoTfList[index].setForeground(Color.BLACK);
				}
			}
		} else {
			infoTfList[index].setForeground(Color.BLACK);
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
		if (this.infoTfList[0].getText().equals("")) {
			// new
			id = -1L;
		} else {
			try {
				id = Long.parseLong(this.infoTfList[0].getText());
			} catch (java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "ID: " + e.getMessage());
			}
		}
		// FIRST NAME
		String firstName = null;
		if (this.infoTfList[1].getText().equals("") == false) {
			firstName = this.infoTfList[1].getText();
			firstName = WhiteSpaceValidator.validate(firstName);
		} else {
			JOptionPane.showMessageDialog(null, "First Name must be not null!");
			this.infoTfList[1].requestFocus();
			return -1;
		}
		// LAST NAME
		String lastName = null;
		if (this.infoTfList[2].getText().equals("") == false) {
			lastName = this.infoTfList[2].getText();
			lastName = WhiteSpaceValidator.validate(lastName);
		} else {
			JOptionPane.showMessageDialog(null, "Last Name must be not null!");
			this.infoTfList[2].requestFocus();
			return -1;
		}
		// PHONE
		String phone = null;
		if (this.infoTfList[3].getText().equals("") == false) {
			phone = this.infoTfList[3].getText();
			phone = WhiteSpaceValidator.validate(phone);
			PhoneValidator phoneValidator = new PhoneValidator();
			if (!phoneValidator.validate(phone)) {
				JOptionPane.showMessageDialog(null, "Invalid Phone! Please insert again!");
				this.infoTfList[3].requestFocus();
				return -1;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Phone must be not null!");
			this.infoTfList[3].requestFocus();
			return -1;
		}
		// GET EMAIL
		String email = null;
		if (this.infoTfList[4].getText().equals("") == false) {
			email = this.infoTfList[4].getText();
			email = WhiteSpaceValidator.validate(email);
			EmailValidator emailValidator = new EmailValidator();
			if (!emailValidator.validate(email)) {
				JOptionPane.showMessageDialog(null, "Invalid Email! Please insert again!");
				this.infoTfList[4].requestFocus();
				return -1;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Email must be not null!");
			this.infoTfList[4].requestFocus();
			return -1;
		}
		// GET ADDRESS
		String address = null;
		if (this.infoTfList[5].getText().equals("") == false) {
			address = this.infoTfList[5].getText();
		} else {
			JOptionPane.showMessageDialog(null, "Address must be not null!");
			this.infoTfList[5].requestFocus();
			return -1;
		}

		// GET DOB from date chooser
		Date dob = null;
		try {
			dob = new Date(this.dateChooser.getDate().getTime());
		} catch (java.lang.NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Invalid Date of Birth! Please insert or choose again!");
			return -1;
		}

		/*
		 * Confirm to save this changes
		 */
		int click = JOptionPane.showConfirmDialog(null, "Do you want to save this changes?");
		if (click == JOptionPane.YES_OPTION) {
			boolean flag = false;
			if (id > 0) {
				/* UPDATE */
				if (service.update(new StaffModel(id, firstName, lastName, phone, email, dob, address)) != null) {
					flag = true;
				}
			} else {
				/* CREATE */
				if (service.save(new StaffModel(firstName, lastName, phone, email, dob, address)) != null) {
					flag = true;
				}
				;
			}
			if (flag == true) {
				this.model.loadData(this.table);
				for (int i = 1; i < this.infoTfList.length; i++) {
					this.infoTfList[i].setForeground(Color.BLACK);
				}
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
		for (int i = 0; i < this.infoTfList.length; i++) {
			this.infoTfList[i].setText("");
		}
		this.dateChooser.setDate(null);

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
