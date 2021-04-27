package xxminhmie.sgu.javagui.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import xxminhmie.sgu.javagui.gui.common.AddButton;
import xxminhmie.sgu.javagui.gui.common.DeleteButton;
import xxminhmie.sgu.javagui.gui.common.ResetButton;
import xxminhmie.sgu.javagui.gui.common.SaveButton;
import xxminhmie.sgu.javagui.gui.modeltable.AccountModelData;
import xxminhmie.sgu.javagui.gui.panel.sub.ChangePassword;
import xxminhmie.sgu.javagui.gui.panel.sub.SubFrame;
import xxminhmie.sgu.javagui.model.AccountModel;
import xxminhmie.sgu.javagui.model.ProductModel;
import xxminhmie.sgu.javagui.service.impl.AccountService;

public class AccountPanel extends JPanel {
	AccountService service = new AccountService();
	/*
	 * Main label
	 */
	JLabel mainLabel;
	JPanel mainPanel;
	/*
	 * Text field
	 */
	JPanel tfPanel;
	JTextField[] tfList;
	JButton[] more = new JButton[2];
	/*
	 * Search
	 */
	JPanel searchPanel;
	JTextField search;
	/*
	 * Button panel
	 */
	JPanel btnPanel;
	AddButton addBtn;
	SaveButton saveBtn;
	DeleteButton deleteBtn;
	ResetButton resetBtn;

	/*
	 * Table
	 */
	JPanel tbPanel;
	JScrollPane pane;
	JTable table;
	AccountModelData model;

	AccountModel selectedRow = new AccountModel();// Customer is being selected from Table
	java.util.List<Long> idSelectedRowList = new java.util.ArrayList<Long>();// Contains list of customer's ID to delete
	int selectedRowIndex = -1;

	SubFrame changeFrame;
	SubFrame moreRoleFrame;
	SubFrame moreStaffFrame;

	/*
	 * Constructor
	 */
	public AccountPanel() {
		setBackground(AbstractPanel.PanelBg);
		setPreferredSize(new Dimension(AbstractPanel.PanelWidth, AbstractPanel.PanelHeight));
		setLayout(new BorderLayout());
		init();
	}

	public void init() {
		/*
		 * Main label
		 */
		mainLabel = new JLabel("Account Manager");
		mainLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		add(mainLabel, BorderLayout.NORTH);

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		add(mainPanel, BorderLayout.CENTER);
		/*
		 * Search
		 */
		searchPanel = new JPanel();
		searchPanel.setBounds(0, 0, 1200, 40);
		searchPanel.setLayout(null);
		mainPanel.add(this.searchPanel);

		search = new JTextField("Search...");
		search.setForeground(new Color(144, 144, 144));
		search.setBounds(10, 10, 300, 30);
		searchPanel.add(search);
		/*
		 * Search listener
		 */
		search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				search.selectAll();
				search.setForeground(Color.BLACK);
			}
		});

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
		 * Text field
		 */
		tfPanel = new JPanel();
		tfPanel.setBounds(0, 40, 330, 220);
		tfPanel.setOpaque(true);
		tfPanel.setLayout(null);
		mainPanel.add(this.tfPanel, BorderLayout.WEST);

		String[] infoName = { "Account ID: ", "Role ID: ", "StaffID: ", "Username: " };
		tfList = new JTextField[4];

		int x = 10;
		int y = 20;
		for (int i = 0; i < tfList.length; i++) {
			JLabel label = new JLabel(infoName[i]);
			label.setBounds(x, y, 100, 30);
			tfList[i] = new JTextField();
			tfList[i].setBounds(x + 80, y, 200, 20);
			tfPanel.add(label);
			tfPanel.add(tfList[i]);
			y += 30;
		}

		more[0] = new JButton("...");
		more[0].setBounds(100 + 190, 20 + 30, 40, 20);
		more[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moreRoleHandle();
			}
		});
		tfPanel.add(more[0]);

		more[1] = new JButton("...");
		more[1].setBounds(100 + 190, 20 + 30 + 30, 40, 20);
		more[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moreStaffHandle();
			}
		});
		tfPanel.add(more[1]);

		/** set read - only for ID text field **/
		tfList[0].setEditable(false);
		tfList[1].setEditable(false);
		tfList[2].setEditable(false);

		tfList[0].setForeground(new Color(108, 108, 108));

		/*
		 * Button panel
		 */
		btnPanel = new JPanel();
		btnPanel.setBounds(330, 62, 200, 160);
		btnPanel.setLayout(null);
		mainPanel.add(btnPanel);

		addBtn = new AddButton(20, 0, 120, 20);
		addBtn.setNameBtn("Create");
		btnPanel.add(addBtn);
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addButtonHandle();
			}
		});

		saveBtn = new SaveButton(20, 30, 120, 20);
		btnPanel.add(this.saveBtn);
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveButtonHandle();
			}
		});

		deleteBtn = new DeleteButton(20, 60, 120, 20);
		btnPanel.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteButtonHandle();
			}
		});

		resetBtn = new ResetButton(20, 90, 120, 20);
		btnPanel.add(this.resetBtn);
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetButtonHandle();
			}
		});

		

		/*
		 * Table
		 */
		tbPanel = new JPanel();
		tbPanel.setBounds(10, 260, 700, 360);
		tbPanel.setLayout(null);
		mainPanel.add(tbPanel);

		/** INITIALIZE ZEBRA TABLE **/
		model = new AccountModelData();
		table = new JTable(model);
		table.setDefaultRenderer(Object.class, new Renderer());

		this.table.addMouseListener(new java.awt.event.MouseAdapter() {
			/** GET SELECTED VALUE TO DISPLAY ON TEXT FIELD **/
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					selectedRowIndex = table.getSelectedRow();
					/** GET MANUALLY ALL ROW **/
					setSelectedAccountModel();
				}
				/** DISPLAY TO TEXT FIELD **/
				displayProductToTextField();
				getAllSelectedRow(table);
			}
		});
		/********************** INITIALIZE SCROLL PANE **************************/
		pane = new JScrollPane(table);
		pane.setBounds(0, 0, 700, 360);
		/** ADD SCROLL PANE TO MAIN PANEL **/
		tbPanel.add(pane);

		/*
		 * Text field key listener
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
					saveBtn.doClick();
				}
			}
		});
	}

	/*
	 * Add button handle
	 */
	public void addButtonHandle() {
		this.tfList[0].setText("");
	}

	/*
	 * Save button handle
	 */
	protected int saveButtonHandle() {
		Long id = null;
		if (this.tfList[0].getText().equals("") == false) {
			id = Long.parseLong(this.tfList[0].getText());
		}
		Long roleId = null;
		if (this.tfList[1].getText().equals("") == false) {
			roleId = Long.parseLong(this.tfList[1].getText());
		}
		Long staffId = null;
		if (this.tfList[2].getText().equals("") == false) {
			staffId = Long.parseLong(this.tfList[2].getText());
		}

		String username = "";
		if (this.tfList[3].getText().equals("") == false) {
			username = this.tfList[3].getText();
		} else {
			JOptionPane.showMessageDialog(null, "Username must be not null!");
			this.tfList[3].requestFocus();
			return -1;
		}

		String password = "";
		
		/*
		 * Confirm to save this changes
		 */
		int click = JOptionPane.showConfirmDialog(null, "Do you want to save this changes?");
		if (click == JOptionPane.YES_OPTION) {
			Long savedId = null;
			if (id != null) {
				AccountModel model = service.update(new AccountModel(id, roleId, staffId, username, password));
				savedId = model.getId();
			} else {
				savedId = service.save(new AccountModel(roleId, staffId, username, password));
			}
			model.loadData(this.table);
			getGeneratedKeys(savedId);
			table.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);

		}
		return 0;

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
	 * Delete button handle
	 */
	protected void deleteButtonHandle() {
		if (selectedRowIndex >= 0) {
			int click = JOptionPane.showConfirmDialog(null, "Are you sure for delete this account?");
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
	 * Reset button handle
	 */
	protected void resetButtonHandle() {
		for (int i = 0; i < this.tfList.length; i++) {
			this.tfList[i].setText("");
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

	public void setSelectedAccountModel() {
		selectedRow.setId((Long) table.getModel().getValueAt(selectedRowIndex, 0));
		selectedRow.setRoleId((Long) table.getModel().getValueAt(selectedRowIndex, 1));
		selectedRow.setStaffId((Long) table.getModel().getValueAt(selectedRowIndex, 2));
		selectedRow.setUsername((String) table.getModel().getValueAt(selectedRowIndex, 3));

	}

	public void displayProductToTextField() {
		tfList[0].setText(selectedRow.getId().toString());
		tfList[1].setText(selectedRow.getRoleId().toString());
		tfList[2].setText(selectedRow.getStaffId().toString());
		tfList[3].setText(selectedRow.getUsername());
	}

	public void moreRoleHandle() {
		RolePanel panel = new RolePanel();
		this.moreRoleFrame = new SubFrame(panel);

		panel.table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					doubleClickRoleHandle(table.getValueAt(row, 0));
				}
			}
		});

		moreRoleFrame.setVisible(true);

	}

	public void doubleClickRoleHandle(Object id) {
		tfList[1].setText((String.valueOf(id)));
		moreRoleFrame.dispose();
	}

	public void moreStaffHandle() {
		StaffPanel panel = new StaffPanel();
		this.moreStaffFrame = new SubFrame(panel);

		panel.table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					doubleClickStaffHandle(table.getValueAt(row, 0));
				}
			}
		});
		moreStaffFrame.setVisible(true);
	}

	public void doubleClickStaffHandle(Object id) {
		tfList[2].setText((String.valueOf(id)));
		moreStaffFrame.dispose();
	}
}
