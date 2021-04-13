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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import xxminhmie.sgu.javagui.gui.common.DeleteButton;
import xxminhmie.sgu.javagui.gui.common.ResetButton;
import xxminhmie.sgu.javagui.gui.common.SaveButton;
import xxminhmie.sgu.javagui.gui.modeltable.RoleModelData;
import xxminhmie.sgu.javagui.model.RoleModel;
import xxminhmie.sgu.javagui.service.impl.RoleService;

public class RolePanel extends JPanel {

	RoleService service = new RoleService();
	/*
	 * Main label
	 */
	JLabel mainLabel;
	JPanel mainPanel;
	/*
	 * Search
	 */
	JPanel searchPanel;
	JTextField search;
	/*
	 * Text field
	 */
	JPanel tfPanel;
	JTextArea text;
	JTextField[] tfList;
	/*
	 * Button
	 */
	JPanel btnPanel;
	SaveButton saveBtn;
	DeleteButton deleteBtn;
	ResetButton resetBtn;
	/*
	 * Table
	 */
	JPanel tbPanel;
	JScrollPane pane;
	JTable table;
	RoleModelData model;

	RoleModel selectedRow = new RoleModel();// Customer is being selected from Table
	java.util.List<Long> idSelectedRowList = new java.util.ArrayList<Long>();// Contains list of customer's ID to delete
	int selectedRowIndex;

	/*
	 * Constructor
	 */
	public RolePanel() {
		setBackground(AbstractPanel.PanelBg);
		setPreferredSize(new Dimension(AbstractPanel.PanelWidth, AbstractPanel.PanelHeight));
		setLayout(new BorderLayout());

		this.init();
	}

	public void init() {
		/*
		 * Main label
		 */
		mainLabel = new JLabel("Role Manager");
		mainLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		add(this.mainLabel, BorderLayout.NORTH);

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		add(this.mainPanel, BorderLayout.CENTER);

		/*
		 * Search panel
		 */
		searchPanel = new JPanel();
		searchPanel.setBounds(0, 0, 330, 40);
		searchPanel.setLayout(null);
		mainPanel.add(this.searchPanel);

		search = new JTextField("Search...");
		search.setForeground(new Color(144, 144, 144));
		search.setBounds(10, 10, 300, 30);
		/*
		 * Search listener
		 */
		search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				search.selectAll();
				search.setForeground(Color.BLACK);
			}
		});
		searchPanel.add(this.search);

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
		tfPanel.setBounds(0, 30, 300, 220);
		tfPanel.setOpaque(true);
		tfPanel.setLayout(null);
		mainPanel.add(tfPanel, BorderLayout.WEST);

		String[] infoName = { "Role ID: ", "Name: ", "Description: " };
		this.tfList = new JTextField[3];
		int x = 10;
		int y = 20;

		for (int i = 0; i < this.tfList.length; i++) {
			JLabel label = new JLabel(infoName[i]);
			label.setBounds(x, y, 80, 30);
			this.tfPanel.add(label);

			if (i == 2) {
				text = new JTextArea();
				text.setBounds(x + 100 + 4, y + 5, 190, 70);
				text.setColumns(20);
				text.setLineWrap(true);
				text.setRows(5);
				text.setWrapStyleWord(true);
				this.tfPanel.add(text);
			} else {
				this.tfList[i] = new JTextField();
				this.tfList[i].setBounds(x + 100, y, 200, 20);
				this.tfPanel.add(this.tfList[i]);
			}
			y += 30;

		}

		/** set read - only for ID text field **/
		tfList[0].setEditable(false);
		tfList[0].setForeground(new Color(108, 108, 108));

		/*
		 * Button panel
		 */
		btnPanel = new JPanel();
		btnPanel.setBounds(300, 50, 200, 160);
		btnPanel.setLayout(null);
		mainPanel.add(this.btnPanel);

		saveBtn = new SaveButton(20, 0);
		btnPanel.add(this.saveBtn);
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveButtonHandle();
			}
		});

		deleteBtn = new DeleteButton(20, 30);
		btnPanel.add(this.deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteButtonHandle();
			}
		});

		resetBtn = new ResetButton(20, 60);
		btnPanel.add(this.resetBtn);
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetButtonHandle();
			}
		});

		/********************** TABLE ON MAIN PANEL **************************/
		tbPanel = new JPanel();
		tbPanel.setBackground(Color.red);
		tbPanel.setBounds(10, 260, 700, 360);
		tbPanel.setLayout(null);
		mainPanel.add(this.tbPanel);

		/** INITIALIZE ZEBRA TABLE **/
		this.model = new RoleModelData();
		this.table = new JTable(model);
		this.table.setDefaultRenderer(Object.class, new Renderer());

		this.table.addMouseListener(new java.awt.event.MouseAdapter() {
			/** GET SELECTED VALUE TO DISPLAY ON TEXT FIELD **/
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					selectedRowIndex = table.getSelectedRow();
					/** GET MANUALLY ALL ROW **/
					setSelectedRoleModel();
				}
				/** DISPLAY TO TEXT FIELD **/
				displayRoleToTextField();

				getAllSelectedRow(table);
			}
		});
		/********************** INITIALIZE SCROLL PANE **************************/
		pane = new JScrollPane(this.table);
		pane.setBounds(0, 0, 700, 360);
		tbPanel.add(this.pane);

		/*
		 * Text field key listener
		 */
		this.tfList[1].addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfList[1 + 1].requestFocus();
				}
			}

		});
//		this.tfList[2].addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//					tfList[2 + 1].requestFocus();
//				}
//			}
//		});

	}// end method

	/*
	 * Save button handle
	 */
	public int saveButtonHandle() {
		Long id = null;
		if (this.tfList[0].getText().equals("") == false) {
			id = Long.parseLong(this.tfList[0].getText());
		}

		String name = null;
		if (this.tfList[1].getText().equals("") == false) {
			name = this.tfList[1].getText();
		} else {
			JOptionPane.showMessageDialog(null, "Name must be not null!");
			this.tfList[1].requestFocus();
			return -1;
		}

		String description = null;
		if (text.getText().equals("") == false) {
			description = text.getText();
		}

		int click = JOptionPane.showConfirmDialog(null, "Do you want to save this changes?");
		if (click == JOptionPane.YES_OPTION) {
			if (id != null) {// update
				service.update(new RoleModel(id, name, description));

			} else {
				service.save(new RoleModel(name, description));
			}
			this.model.loadData(this.table);

			table.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);

		}

		return 0;

	}

	/*
	 * Delete button handle
	 */
	protected void deleteButtonHandle() {
		if (selectedRowIndex > 0) {
			int click = JOptionPane.showConfirmDialog(null, "Are you sure for delete this customer?");
			if (click == JOptionPane.YES_OPTION) {
				// https://stackoverflow.com/questions/2016654/classcastexception-when-casting-object-array-to-long-array
				this.service.delete(this.idSelectedRowList.toArray(new Long[this.idSelectedRowList.size()]));
				this.model.loadData(this.table);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You have not selected a value to delete!");
		}

	}

	/*
	 * Reset Button Handle
	 */
	protected void resetButtonHandle() {
		for (int i = 0; i < this.tfList.length; i++) {
			if (i == 2) {
				text.setText("");
			} else {
				this.tfList[i].setText("");
			}
		}
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

	/*
	 * 
	 */
	public void setSelectedRoleModel() {
		selectedRow.setId((Long) table.getModel().getValueAt(selectedRowIndex, 0));
		selectedRow.setName((String) table.getModel().getValueAt(selectedRowIndex, 1));
		selectedRow.setDescription((String) table.getModel().getValueAt(selectedRowIndex, 2));
	}

	/*
	 * 
	 */
	public void displayRoleToTextField() {
		tfList[0].setText(selectedRow.getId().toString());
		tfList[1].setText(selectedRow.getName());
		text.setText(selectedRow.getDescription());

	}
}
