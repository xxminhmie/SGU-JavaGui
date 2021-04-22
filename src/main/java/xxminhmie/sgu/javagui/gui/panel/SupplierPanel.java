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

public class SupplierPanel extends JPanel {
	SupplierService service = new SupplierService();

	JLabel mainLabel;
	JPanel mainPanel;
	JPanel infoPanel;
	/*
	 * 
	 */
	JTextField[] tfList;

	JPanel searchPanel;
	JTextField search;

	JPanel btnPanel;
	SaveButton saveBtn;
	DeleteButton deleteBtn;
	ResetButton resetBtn;

	JPanel tbPanel;
	JScrollPane pane;
	public JTable table;
	SupplierModelData model;

	SupplierModel selectedRow = new SupplierModel();
	java.util.List<Long> idSelectedRowList = new java.util.ArrayList<Long>();
	int selectedRowIndex;
	Boolean flagReset = false;

	/*
	 * Constructor
	 */
	public SupplierPanel() {
		setBackground(AbstractPanel.PanelBg);
		setPreferredSize(new Dimension(AbstractPanel.PanelWidth, AbstractPanel.PanelHeight));
		setLayout(new BorderLayout());

		/*
		 * Main label
		 */
		mainLabel = new JLabel("Supplier Manager");
		mainLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		add(mainLabel, BorderLayout.NORTH);

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		add(this.mainPanel, BorderLayout.CENTER);

		/*
		 * search
		 */
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
		/*
		 * text field
		 */
		this.infoPanel = new JPanel();
		this.infoPanel.setBounds(0, 40, 330 - 40, 220);
		this.infoPanel.setOpaque(true);
		this.infoPanel.setLayout(null);
		this.mainPanel.add(this.infoPanel, BorderLayout.WEST);

		String[] infoName = { "ID: ", "Name: ", "Phone: ", "Email: ", "Address: " };
		this.tfList = new JTextField[5];
		int x = 10;
		int y = 20;

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

		/*
		 * button
		 */
		btnPanel = new JPanel();
		btnPanel.setBounds(300, 62, 200, 160);
		btnPanel.setLayout(null);
		mainPanel.add(this.btnPanel);

		saveBtn = new SaveButton(20, 0);
		btnPanel.add(this.saveBtn);
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveButtonClicked();
			}
		});
		deleteBtn = new DeleteButton(20, 30);
		btnPanel.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteButtonClicked();
			}
		});

		resetBtn = new ResetButton(20, 60);
		btnPanel.add(resetBtn);
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
		mainPanel.add(this.tbPanel);

		/** INITIALIZE ZEBRA TABLE **/
		model = new SupplierModelData();
		table = new JTable(model);

		table.setDefaultRenderer(Object.class, new Renderer());

		/** GET SELECTED VALUE TO DISPLAY ON TEXT FIELD **/
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					selectedRowIndex = table.getSelectedRow();
					/** GET MANUALLY ALL ROW **/
					setSelectedSupplierModel();
				}
				/** DISPLAY TO TEXT FIELD **/
				displaySupplierToTextField();
				getAllSelectedRow(table);
			}
		});

		pane = new JScrollPane(table);
		pane.setBounds(0, 0, 700, 360);
		/** ADD SCROLL PANE TO MAIN PANEL **/
		tbPanel.add(pane);

		/*
		 * text field key listener
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
		this.tfList[3].addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfList[3 + 1].requestFocus();
				}
			}
		});
	}

	/*
	 * save button handle
	 */
	protected void saveButtonClicked() {
		Long id = -1L;
		if (!tfList[0].getText().isBlank()) {
			id = Long.parseLong(this.tfList[0].getText());
		}

		String name = "";
		if (!tfList[1].getText().isBlank()) {
			name = tfList[1].getText();
			name = WhiteSpaceValidator.validate(name);
		} else {
			JOptionPane.showMessageDialog(null, "First Name must be not null!");
			tfList[1].requestFocus();
			return;
		}

		String phone = "";
		if (!tfList[2].getText().isBlank()) {
			phone = tfList[2].getText();
			phone = WhiteSpaceValidator.validate(phone);
			PhoneValidator phoneValidator = new PhoneValidator();
			if (!phoneValidator.validate(phone)) {
				JOptionPane.showMessageDialog(null, "Invalid Phone! Please insert again!");
				tfList[2].requestFocus();
				return;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Phone must be not null!");
			tfList[3].requestFocus();
			return;
		}

		String email = "";
		if (!tfList[3].getText().isBlank()) {
			email = tfList[3].getText();
			email = WhiteSpaceValidator.validate(email);
			EmailValidator emailValidator = new EmailValidator();
			if (!emailValidator.validate(email)) {
				JOptionPane.showMessageDialog(null, "Invalid Email! Please insert again!");
				tfList[3].requestFocus();
				return;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Email must be not null!");
			tfList[3].requestFocus();
			return;
		}

		String address = "";
		if (!tfList[4].getText().isBlank()) {
			address = tfList[4].getText();
		} else {
			JOptionPane.showMessageDialog(null, "Address must be not null!");
			tfList[4].requestFocus();
			return;
		}

		/*
		 * Confirm to save this changes
		 */
		Long savedId;
		int click = JOptionPane.showConfirmDialog(null, "Do you want to save this changes?");
		if (click == JOptionPane.YES_OPTION) {
			if (id > 0) {
				SupplierModel model = service.update(new SupplierModel(id, name, email, phone, address));
				savedId = model.getId();
			} else {
				SupplierModel model = service.save(new SupplierModel(name, email, phone, address));
				savedId = model.getId();
			}

			model.loadData(table);
			getGeneratedKeys(savedId);
			table.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);

		}
	}

	/*
	 * set selected row after adding
	 */
	public void getGeneratedKeys(Long id) {
		for (int i = 0; i < this.model.getRowCount(); i++) {
			if (id == (Long) this.model.getValueAt(i, 0)) {
				this.selectedRowIndex = i;
				return;
			}
		}
	}

	/*
	 * delete button handle
	 */
	protected void deleteButtonClicked() {
		if (selectedRowIndex > 0) {
			int click = JOptionPane.showConfirmDialog(null, "Are you sure for delete this supplier?");
			if (click == JOptionPane.YES_OPTION) {
				// https://stackoverflow.com/questions/2016654/classcastexception-when-casting-object-array-to-long-array
				service.delete(this.idSelectedRowList.toArray(new Long[this.idSelectedRowList.size()]));
				model.loadData(this.table);
				resetButtonClicked();
			}
		} else {
			JOptionPane.showMessageDialog(null, "You have not selected a value to delete!");
		}

	}

	/*
	 * reset button handle
	 */
	protected void resetButtonClicked() {
		this.flagReset = true;
		for (int i = 0; i < tfList.length; i++) {
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

	public void setSelectedSupplierModel() {
		selectedRow.setId((Long) table.getModel().getValueAt(selectedRowIndex, 0));
		selectedRow.setName((String) table.getModel().getValueAt(selectedRowIndex, 1));
		selectedRow.setPhone((String) table.getModel().getValueAt(selectedRowIndex, 2));
		selectedRow.setEmail((String) table.getModel().getValueAt(selectedRowIndex, 3));
		selectedRow.setAddress((String) table.getModel().getValueAt(selectedRowIndex, 4));
	}

	public void displaySupplierToTextField() {
		tfList[0].setText(selectedRow.getId().toString());
		tfList[1].setText(selectedRow.getName());
		tfList[2].setText(selectedRow.getPhone());
		tfList[3].setText(selectedRow.getEmail());
		tfList[4].setText(selectedRow.getAddress());

	}

}
