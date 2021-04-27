package xxminhmie.sgu.javagui.gui.panel;
//TODO: kiểm tra ngày sinh hợp lệ

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
import xxminhmie.sgu.javagui.gui.common.BirthdayCheck;
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
	CustomerService service = new CustomerService();
	
	JLabel mainLabel;// "Customer Management",BorderLayout.NORTH
	JPanel mainPanel;// BorderLayout.CENTER
	JPanel tfPanel;// Contains list of text field
	// List of text field displays detail information of one customer hold by
	// selectedRow variable
	JTextField[] tfList;
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
	Boolean flagReset = false;

	public CustomerPanel() {

		setBackground(AbstractPanel.PanelBg);
		setPreferredSize(new Dimension(AbstractPanel.PanelWidth, AbstractPanel.PanelHeight));
		setLayout(new BorderLayout());

		mainLabel = new JLabel("Customer Manager");
		mainLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		add(mainLabel, BorderLayout.NORTH);

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		add(mainPanel, BorderLayout.CENTER);

	
		searchPanel = new JPanel();
		searchPanel.setBounds(0, 0, 330, 40);
		searchPanel.setLayout(null);
		mainPanel.add(searchPanel);

		search = new JTextField("Search...");
		search.setForeground(new Color(144, 144, 144));
		search.setBounds(10, 10, 300, 30);
		search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				search.selectAll();
				search.setForeground(Color.BLACK);
			}
		});
		searchPanel.add(search);

		search.getDocument().addDocumentListener(new DocumentListener() {
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
		/*
		 * Text field
		 */
		tfPanel = new JPanel();
		tfPanel.setBounds(0, 30, 330, 220);
		tfPanel.setOpaque(true);
		tfPanel.setLayout(null);
		mainPanel.add(tfPanel, BorderLayout.WEST);

		String[] infoName = { "ID: ", "First Name: ", "Last Name: ", "Phone: ", "Email: ", "DoB: " };
		tfList = new JTextField[5];
		int x = 10;
		int y = 20;
		
		for (int i = 0; i < tfList.length; i++) {
			JLabel label = new JLabel(infoName[i]);
			label.setBounds(x, y, 100, 30);
			tfPanel.add(label);
			tfList[i] = new JTextField();
			tfList[i].setBounds(x + 100, y, 200, 20);
			y += 30;
			tfPanel.add(this.tfList[i]);
		}
		/*
		 * date chooser
		 */
		JLabel label = new JLabel(infoName[5]);
		label.setBounds(x, y, 100, 30);
		tfPanel.add(label);
		dateChooser = new JDateChooser();
		dateChooser.setBounds(x + 100, y, 200, 30);
		dateChooser.getJCalendar().setBounds(0, 0, 600, 200);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		tfPanel.add(dateChooser);

		/** set read - only for ID text field **/
		tfList[0].setEditable(false);
		tfList[0].setForeground(new Color(108, 108, 108));

		/*
		 * Button
		 */
		btnPanel = new JPanel();
		btnPanel.setBounds(330, 55, 200, 160);
		btnPanel.setLayout(null);
		mainPanel.add(btnPanel);

		saveBtn = new SaveButton(20, 0);
		btnPanel.add(this.saveBtn);
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveButtonClicked();
			}
		});

		deleteBtn = new DeleteButton(20, 50);
		btnPanel.add(this.deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteButtonHandle();
			}
		});

		resetBtn = new ResetButton(20, 100);
		btnPanel.add(this.resetBtn);
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetButtonClicked();
			}
		});

		/********************** TABLE ON MAIN PANEL **************************/
		this.tbPanel = new JPanel();
		this.tbPanel.setBounds(10, 260, 700, 360);
		this.tbPanel.setLayout(null);
		this.mainPanel.add(this.tbPanel);

		/** INITIALIZE ZEBRA TABLE **/
		this.model = new CustomerModelData();
		table = new JTable(model);
		table.setDefaultRenderer(Object.class, new Renderer());

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					selectedRowIndex = table.getSelectedRow();
					setSelectedCustomerModel();
				}
				displayCustomerToTextField();
				getAllSelectedRow(table);
			}
		});
		/********************** INITIALIZE SCROLL PANE **************************/
		this.pane = new JScrollPane(this.table);
		this.pane.setBounds(0, 0, 700, 360);
		/** ADD SCROLL PANE TO MAIN PANEL **/
		this.tbPanel.add(this.pane);

		 /* 
		 * key listener
		 */ 
		tfList[1].addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfList[1 + 1].requestFocus();
				}
			}
		});
		tfList[2].addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfList[2 + 1].requestFocus();
				}
			}
		});
		tfList[3].addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfList[3 + 1].requestFocus();
				}
			}
		});
		/*
		 * https://stackoverflow.com/questions/20945380/how-to-set-focus-on-jdatechooser
		 * -when-frame-is-loaded-in-swing
		 */
		tfList[4].addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					dateChooser.getDateEditor().getUiComponent().requestFocus();
				}
			}
		});
		dateChooser.getDateEditor().getUiComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					saveBtn.doClick();
				}
			}
		});
	}//end constructor

	/*
	 * Save button handle
	 */
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

		// GET DOB from date chooser
		Date dob = null;
		try {
			dob = new Date(this.dateChooser.getDate().getTime());
		} catch (java.lang.NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Invalid Date of Birth! Please insert or choose again!");
			return -1;
		}
		
		int age = BirthdayCheck.checkBirthday(dob);
		if(age<16 || age>100) {
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
				if (service.update(new CustomerModel(id, firstName, lastName, phone, email, dob)) != null) {
					flag = true;
				}
			} else {
				/* CREATE */
				if (service.save(new CustomerModel(firstName, lastName, phone, email, dob)) != null) {
					flag = true;
				}
				;
			}
			if (flag == true) {
				this.model.loadData(this.table);
				for (int i = 1; i < this.tfList.length; i++) {
					this.tfList[i].setForeground(Color.BLACK);
				}
				table.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
			}
		}

		return 0;

	}

	/*
	 * 
	 */
	protected void deleteButtonHandle() {
		if (selectedRowIndex >= 0) {
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
		this.flagReset = true;
		for (int i = 0; i < this.tfList.length; i++) {
			this.tfList[i].setText("");
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

	public void setSelectedCustomerModel() {
		selectedRow.setId((Long) table.getModel().getValueAt(selectedRowIndex, 0));
		selectedRow.setFirstName((String) table.getModel().getValueAt(selectedRowIndex, 1));
		selectedRow.setLastName((String) table.getModel().getValueAt(selectedRowIndex, 2));
		selectedRow.setPhone((String) table.getModel().getValueAt(selectedRowIndex, 3));
		selectedRow.setEmail((String) table.getModel().getValueAt(selectedRowIndex, 4));
		selectedRow.setDob((java.sql.Date) table.getModel().getValueAt(selectedRowIndex, 5));
	}
	public void displayCustomerToTextField() {
		tfList[0].setText(selectedRow.getId().toString());
		tfList[1].setText(selectedRow.getFirstName());
		tfList[2].setText(selectedRow.getLastName());
		tfList[3].setText(selectedRow.getPhone());
		tfList[4].setText(selectedRow.getEmail());
		dateChooser.setDate(selectedRow.getDob());
	}

}
