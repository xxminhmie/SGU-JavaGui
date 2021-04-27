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

import xxminhmie.sgu.javagui.gui.common.BirthdayCheck;
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
	StaffService service = new StaffService();

	JLabel mainLabel;
	JPanel mainPanel;
	JPanel infoPanel;

	JTextField[] infoTfList;
	JDateChooser dateChooser;

	JPanel searchPanel;
	JTextField search;

	JPanel btnPanel;
	SaveButton saveBtn;
	DeleteButton deleteBtn;
	ResetButton resetBtn;

	JPanel tbPanel;
	JScrollPane pane;
	JTable table;
	StaffModelData model;

	StaffModel selectedRow = new StaffModel();// Customer is being selected from Table
	java.util.List<Long> idSelectedRowList = new java.util.ArrayList<Long>();// Contains list of customer's ID to delete
	int selectedRowIndex;


	/*
	 * constructor
	 */
	public StaffPanel() {
		setBackground(AbstractPanel.PanelBg);
		setPreferredSize(new Dimension(AbstractPanel.PanelWidth, AbstractPanel.PanelHeight));
		setLayout(new BorderLayout());

		mainLabel = new JLabel("Staff Manager");
		mainLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		add(mainLabel, BorderLayout.NORTH);

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		add(this.mainPanel, BorderLayout.CENTER);

		/*
		 * Search
		 */
		searchPanel = new JPanel();
		searchPanel.setBounds(0, 0, 1200, 40);
		searchPanel.setLayout(null);
		mainPanel.add(searchPanel);

		search = new JTextField("Search...");
		searchPanel.add(this.search);
		search.setForeground(new Color(144, 144, 144));
		search.setBounds(10, 10, 300, 30);
		search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				search.selectAll();
				search.setForeground(Color.BLACK);
			}
		});

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
		 * Test field
		 */
		infoPanel = new JPanel();
		infoPanel.setBounds(0, 40, 330 * 2 - 40, 220);
		infoPanel.setOpaque(true);
		infoPanel.setLayout(null);
		mainPanel.add(infoPanel, BorderLayout.WEST);

		String[] infoName = { "ID: ", "First Name: ", "Last Name: ", "Phone: ", "Email: ", "Address: ", "DoB: " };
		infoTfList = new JTextField[6];
		int x = 10;
		int y = 20;
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
		/*
		 * J Date Chooser for date of birth
		 */
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

		/*
		 * Button
		 */
		btnPanel = new JPanel();
		btnPanel.setBounds(620, 55, 200, 160);
		btnPanel.setLayout(null);
		mainPanel.add(btnPanel);

		saveBtn = new SaveButton(20, 0);
		btnPanel.add(saveBtn);
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveButtonHandle();
			}
		});

		deleteBtn = new DeleteButton(20, 50);
		btnPanel.add(deleteBtn);
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

		/*
		 * table
		 */
		tbPanel = new JPanel();
		tbPanel.setBackground(Color.red);
		tbPanel.setBounds(10, 260, 700, 360);
		tbPanel.setLayout(null);
		mainPanel.add(tbPanel);

		/** INITIALIZE ZEBRA TABLE **/
		model = new StaffModelData();
		table = new JTable(model);

		table.setDefaultRenderer(Object.class, new Renderer());

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					selectedRowIndex = table.getSelectedRow();
					setSelectedStaffModel();
				}
				displayStaffToTextField();
				getAllSelectedRow(table);
			}
		});
		/*
		 * Scroll Pane
		 */
		pane = new JScrollPane(table);
		pane.setBounds(0, 0, 700, 360);
		tbPanel.add(pane);

		/*
		 * text field key listener
		 */
		infoTfList[1].addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					infoTfList[1 + 1].requestFocus();
				}
			}

		});
		infoTfList[2].addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					infoTfList[2 + 1].requestFocus();
				}
			}

		});
		infoTfList[3].addKeyListener(new KeyAdapter() {
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
	}

	/*
	 * text field handle
	 */
	public void change(int index, String str) {
		if (str != null && infoTfList[index] != null) {
			if (str.equals(infoTfList[index].getText()) == false) {
				infoTfList[index].setForeground(AbstractPanel.DocumentListener);
			} else {
				infoTfList[index].setForeground(Color.BLACK);
			}
		}
	}

	/*
	 * save button handle
	 */
	protected void saveButtonHandle() {
		Long id = -1L;
		if (!infoTfList[0].getText().isBlank()) {
			id = Long.parseLong(this.infoTfList[0].getText());
		}

		String firstName = "";
		if (!infoTfList[1].getText().isBlank()) {
			firstName = infoTfList[1].getText();
			firstName = WhiteSpaceValidator.validate(firstName);
		} else {
			JOptionPane.showMessageDialog(null, "First Name must be not null!");
			infoTfList[1].requestFocus();
			return;
		}

		String lastName = "";
		if (!infoTfList[2].getText().isBlank()) {
			lastName = infoTfList[2].getText();
			lastName = WhiteSpaceValidator.validate(lastName);
		} else {
			JOptionPane.showMessageDialog(null, "Last Name must be not null!");
			this.infoTfList[2].requestFocus();
			return;
		}

		String phone = "";
		if (!infoTfList[3].getText().isBlank()) {
			phone = infoTfList[3].getText();
			phone = WhiteSpaceValidator.validate(phone);
			PhoneValidator phoneValidator = new PhoneValidator();
			if (!phoneValidator.validate(phone)) {
				JOptionPane.showMessageDialog(null, "Invalid Phone! Please insert again!");
				infoTfList[3].requestFocus();
				return;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Phone must be not null!");
			infoTfList[3].requestFocus();
			return;
		}

		String email = "";
		if (!infoTfList[4].getText().isBlank()) {
			email = infoTfList[4].getText();
			email = WhiteSpaceValidator.validate(email);
			EmailValidator emailValidator = new EmailValidator();
			if (!emailValidator.validate(email)) {
				JOptionPane.showMessageDialog(null, "Invalid Email! Please insert again!");
				this.infoTfList[4].requestFocus();
				return;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Email must be not null!");
			infoTfList[4].requestFocus();
			return;
		}

		String address = "";
		if (!infoTfList[5].getText().isBlank()) {
			address = infoTfList[5].getText();
		} else {
			JOptionPane.showMessageDialog(null, "Address must be not null!");
			infoTfList[5].requestFocus();
			return;
		}

		Date dob;
		try {
			dob = new Date(this.dateChooser.getDate().getTime());
		} catch (java.lang.NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Invalid Date of Birth! Please insert or choose again!");
			return;
		}
		int age = BirthdayCheck.checkBirthday(dob);
		if(age<16 || age>100) {
			JOptionPane.showMessageDialog(null, "Invalid Date of Birth! Please insert or choose again!");
			return;
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
	}

	/*
	 * delete button handle
	 */
	protected void deleteButtonHandle() {
		if (selectedRowIndex >= 0) {
			int click = JOptionPane.showConfirmDialog(null, "Are you sure for delete this staff?");
			if (click == JOptionPane.YES_OPTION) {
				// https://stackoverflow.com/questions/2016654/classcastexception-when-casting-object-array-to-long-array
				service.delete(this.idSelectedRowList.toArray(new Long[this.idSelectedRowList.size()]));
				model.loadData(this.table);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You have not selected a value to delete!");
		}

	}

	/*
	 * reset button handle
	 */
	public void resetButtonClicked() {
		for (int i = 0; i < this.infoTfList.length; i++) {
			this.infoTfList[i].setText("");
		}
		this.dateChooser.setDate(null);

	}

	public void getAllSelectedRow(JTable entryTable) {
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

	public void displayStaffToTextField() {
		infoTfList[0].setText(selectedRow.getId().toString());
		infoTfList[1].setText(selectedRow.getFirstName());
		infoTfList[2].setText(selectedRow.getLastName());
		infoTfList[3].setText(selectedRow.getPhone());
		infoTfList[4].setText(selectedRow.getEmail());
		infoTfList[5].setText(selectedRow.getAddress());
		dateChooser.setDate(selectedRow.getDob());

	}

	public void setSelectedStaffModel() {
		selectedRow.setId((Long) table.getModel().getValueAt(selectedRowIndex, 0));
		selectedRow.setFirstName((String) table.getModel().getValueAt(selectedRowIndex, 1));
		selectedRow.setLastName((String) table.getModel().getValueAt(selectedRowIndex, 2));
		selectedRow.setPhone((String) table.getModel().getValueAt(selectedRowIndex, 3));
		selectedRow.setEmail((String) table.getModel().getValueAt(selectedRowIndex, 4));
		selectedRow.setAddress((String) table.getModel().getValueAt(selectedRowIndex, 5));
		selectedRow.setDob((java.sql.Date) table.getModel().getValueAt(selectedRowIndex, 6));

	}
	
}
