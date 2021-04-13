package xxminhmie.sgu.javagui.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import xxminhmie.sgu.javagui.gui.common.AddButton;
import xxminhmie.sgu.javagui.gui.common.DeleteButton;
import xxminhmie.sgu.javagui.gui.common.GetCurrentDate;
import xxminhmie.sgu.javagui.gui.common.ResetButton;
import xxminhmie.sgu.javagui.gui.common.SaveButton;
import xxminhmie.sgu.javagui.gui.modeltable.POModelData;
import xxminhmie.sgu.javagui.gui.panel.sub.PODetailPanel;
import xxminhmie.sgu.javagui.gui.panel.sub.SkuPanel;
import xxminhmie.sgu.javagui.gui.panel.sub.SubFrame;
import xxminhmie.sgu.javagui.gui.validator.WhiteSpaceValidator;
import xxminhmie.sgu.javagui.model.POModel;
import xxminhmie.sgu.javagui.service.impl.POService;
import xxminhmie.sgu.javagui.service.impl.PODetailService;

public class POPanel extends JPanel {
	POService poService = new POService();
	JLabel mainLabel = new JLabel("Purchase Order Manager");
	JPanel mainPanel = new JPanel();
	JPanel panel = new JPanel();
	/*
	 * Text field
	 */
	JPanel tfPanel;
	JTextField[] tfList = new JTextField[5];
	JComboBox comboStatus;
	JButton[] more = new JButton[3];
	/*
	 * Search
	 */
	JPanel searchPanel;
	JTextField search;
	/*
	 * Button
	 */
	JPanel btnPanel = new JPanel();
	ResetButton resetBtn;
	SaveButton saveBtn;
	AddButton addDetailBtn;
	DeleteButton deleteBtn;
	AddButton addBtn;

	/*
	 * Table
	 */
	JPanel tbPanel;
	JScrollPane pane;
	JTable table;
	POModelData model;

	/*
	 * Handle clicking on table
	 */
	POModel selectedRow = new POModel();
	java.util.List<Long> idSelectedRowList = new java.util.ArrayList<Long>();
	int selectedRowIndex;

	/*
	 * Constructor
	 */
	public POPanel() {
		this.setBackground(AbstractPanel.PanelBg);
		this.setPreferredSize(new Dimension(AbstractPanel.PanelWidth, AbstractPanel.PanelHeight));
		this.setLayout(new BorderLayout());

		this.mainLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		this.add(this.mainLabel, BorderLayout.NORTH);

		/*
		 * Main Panel
		 */
		this.mainPanel.setLayout(null);
		this.add(this.mainPanel, BorderLayout.CENTER);

		/*
		 * PO Panel
		 */
		this.panel.setBounds(0, 0, AbstractPanel.PanelWidth, 700);
		this.panel.setLayout(null);
		this.mainPanel.add(this.panel);

		/*
		 * Search
		 */
		this.searchPanel = new JPanel();
		this.searchPanel.setBounds(0, 0, 300, 40);
		this.searchPanel.setLayout(null);
		this.panel.add(this.searchPanel);

		this.search = new JTextField("Search...");
		this.search.setForeground(new Color(144, 144, 144));
		this.search.setBounds(10, 10, 300, 30);

		/*
		 * Search's listener
		 */
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
		 * Text field
		 */
		this.tfPanel = new JPanel();
		this.tfPanel.setBounds(0, 30, 350, 240);
		this.tfPanel.setOpaque(true);
		this.tfPanel.setLayout(null);
		this.panel.add(this.tfPanel);

		String[] infoName = { "ID: ", "StaffID: ", "Created Date: ", "Total", "Status: " };
		String[] comboStatusSelection = { "Pending ", "Confirmed", "Sending", "Canceled" };
		int x = 10;
		int y = 20;
		// this for loop initialize label and text field, set position for both
		for (int i = 0; i < this.tfList.length; i++) {
			JLabel label = new JLabel(infoName[i]);
			this.tfPanel.add(label);
			if (i == 4) {
				label.setBounds(x, y, 100, 30);
				comboStatus = new JComboBox(comboStatusSelection);
				comboStatus.setSelectedItem(null);
				comboStatus.setBounds(x + 90, y + 5, 190, 20);
				this.tfPanel.add(comboStatus);
			} else {
				label.setBounds(x, y, 100, 30);
				tfList[i] = new JTextField();
				this.tfList[i].setBounds(x + 90, y + 5, 190, 20);
				this.tfPanel.add(this.tfList[i]);
			}
			y += 30;
		}
		int moreX = 10 + 80 + 200;
		int moreY = 20 + 30 + 5 + 30;
		for (int i = 0; i < 3; i++) {
			more[i] = new JButton("...");
			more[i].setBounds(moreX, moreY, 40, 20);
			this.panel.add(more[i]);
			moreY += 30;
		}
		/*
		 * set read - only for ID text field
		 */
		tfList[0].setEditable(false);
		tfList[0].setForeground(new Color(108, 108, 108));
		panel.add(this.tfPanel);

		/*
		 * Button panel
		 */
		btnPanel.setBounds(450, 60, 200, 200);
		btnPanel.setLayout(null);
		panel.add(btnPanel);

		addBtn = new AddButton(0, 0, 120, 20);
		btnPanel.add(addBtn);
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addButtonHandle();
			}

		});

		saveBtn = new SaveButton(0, 30, 120, 20);
		btnPanel.add(saveBtn);
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveButtonHandle();
			}

		});
		resetBtn = new ResetButton(0, 60, 120, 20);
		btnPanel.add(resetBtn);
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetButtonHandle();
			}

		});

		addDetailBtn = new AddButton(0, 90, 120, 20);
		addDetailBtn.setNameBtn("Add Details");
		btnPanel.add(addDetailBtn);
		addDetailBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addDetailButtonHandle();
			}

		});

		deleteBtn = new DeleteButton(0, 120, 120, 20);
		btnPanel.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteButtonHandle();
			}

		});

		/******************************************************************
		 * 
		 * Table panel
		 */
		tbPanel = new JPanel();
		tbPanel.setBounds(10, 260, 1000, 360);
		tbPanel.setLayout(null);

		/*
		 * Table
		 */
		model = new POModelData();
		table = new JTable(model);
		table.setDefaultRenderer(Object.class, new Renderer());
		model.setColumnWidth(table);

		/*
		 * Table adding listener
		 */
		this.table.addMouseListener(new java.awt.event.MouseAdapter() {
			/** GET SELECTED VALUE TO DISPLAY ON TEXT FIELD **/
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int rowIndex = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (rowIndex >= 0 && col >= 0) {
					selectedRowIndex = rowIndex;
					/** GET MANUALLY ALL ROW **/
					setSelectedPOModel();
				}
				/** DISPLAY TO TEXT FIELD **/
				displayPOToTextField();

				/**  **/
				getAllSelectedRow(table);
//				findAllDetailByProductId(selectedRow.getId());
			}
		});

		/*
		 * Right click on table's row
		 */
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem menuItemDetail = new JMenuItem("Detail");
//		JMenuItem menuItemLock = new JMenuItem("Confirm");

		popupMenu.add(menuItemDetail);
//		popupMenu.add(menuItemLock);

		table.setComponentPopupMenu(popupMenu);

//		menuItemLock.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				menuItemLockHandle();
//			}
//
//		});
		menuItemDetail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuItemDetailHandle();
			}

		});

		/*
		 * scroll pane
		 */
		pane = new JScrollPane(table);
		pane.setBounds(0, 20, 700, 360);
		/** ADD SCROLL PANE TO MAIN PANEL **/
		tbPanel.add(pane);
		panel.add(tbPanel);

		/*
		 * more button add listener
		 */
		this.more[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame();
				CustomerPanel p = new CustomerPanel();
				f.getContentPane().add(p);
				f.setSize(new Dimension(1000, 500));
				f.setVisible(true);
				f.setLocationRelativeTo(null); // this will center your application

			}

		});
	}// END CONSTRUCTOR////////////////////////////////////

	/*
	 * reset button handle
	 */
	public void resetButtonHandle() {
		for (int i = 0; i < this.tfList.length; i++) {
			if (i == 5) {
				comboStatus.setSelectedItem(null);
			} else {
				this.tfList[i].setText("");
			}
		}

	}

	/*
	 * Save button handle
	 */
	public int saveButtonHandle() {
		Long id = null;
		if (this.tfList[0].getText().equals("") == false) {
			try {
				id = Long.parseLong(tfList[0].getText());
			} catch (java.lang.NumberFormatException e) {

			}
		}
		

		Long staffId = null;
		if (tfList[1].getText().equals("") == false) {
			staffId = Long.valueOf(tfList[1].getText());
		} else {
			tfList[1].requestFocus();
			return -1;
		}

		Long supplierId = null;
		if (tfList[2].getText().equals("") == false) {
			supplierId = Long.valueOf(tfList[2].getText());
		} else {
			tfList[1].requestFocus();
			return -1;
		}

		java.sql.Date createdDate = GetCurrentDate.getDate();
		System.out.print(createdDate);

//		String status = null;
//		if (comboStatus.getSelectedItem() != null) {
//			status = comboStatus.getSelectedItem().toString();
//			if (status.equals("Locked")) {
//				JOptionPane.showMessageDialog(null, "Product's status must be not locked while adding!");
//				comboStatus.requestFocus();
//				return -1;
//			}
//			if (status.equals("Locked")) {
//				JOptionPane.showMessageDialog(null, "Product's status must be not locked while adding!");
//				comboStatus.requestFocus();
//				return -1;
//			}
//		}
		// Create new purchase order
		// String[] infoName = { "ID: ", "StaffID: ", "SupplierID: ", "Created Date: ",
				// "Total", "Status: " };
		Long savedId;
		if (id == null) {
			savedId = poService.save(new POModel(staffId, createdDate));

		} else {
			// Update product
			POModel model = poService.update(new POModel(id, staffId, createdDate, "Pending"));
			savedId = model.getId();
		}

		this.model.loadData(this.table);
		getGeneratedKeys(savedId);
		table.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
//		this.detailPanel.addButtonHandle(savedId);
		return 0;
	}

	/*
	 * Add detail button handle
	 */
	public void addDetailButtonHandle() {
		openDetailFrame(this.selectedRow.getId());

	}

	/*
	 * Add button handle
	 */
	public void addButtonHandle() {
		tfList[0].setText("");
		// TODO: nhan vien hien hanh id
//		tfList[1].setText();
	}

	/*
	 * Delete button handle
	 */
	public void deleteButtonHandle() {

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
	 * click on PO detail table, the row product auto selected
	 */
	public void displayProductFromSku(Long productId) {
//		this.proSelectedRow = this.poService.findOne(productId);

		/** DISPLAY TO TEXT FIELD **/
//		tfList[0].setText(selectedRow.getId().toString());
//		tfList[1].setText(selectedRow.getName());
//		tfList[2].setText(proSelectedRow.getBrand());
//		text.setText(proSelectedRow.getDescription());
//		if (proSelectedRow.getStatus().equals(comboStatus.getItemAt(0))) {
//			comboStatus.setSelectedIndex(0);
//		} else {
//			comboStatus.setSelectedIndex(1);
//		}
//		getAllSelectedRow(table);
//		int row = -1;
//		for (int rowIndex = 0; rowIndex < this.model.getRowCount(); rowIndex++) {
//			Long idAtRow = (Long) this.model.getValueAt(rowIndex, 0);
//
//			if (idAtRow == productId) {
//				row = rowIndex;
//				break;
//			}
//		}
//		if (row >= 0) {
//			this.table.setRowSelectionInterval(row, row);
//		}
	}

	/*
	 * delete multiple row handle
	 */
	public void getAllSelectedRow(JTable entryTable) {
//			AbstractTableModel model = (AbstractTableModel) entryTable.getModel();
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
	 * click on product table, find list of purchase detail of this selected
	 * purchase
	 */
	// TODO
	public void findAllDetailByProductId(Long id) {
//		this.detailPanel.loadData(id.toString());
//		this.detailPanel.tfList[0].setText(String.valueOf());
	}

	public void menuItemLockHandle() {
//		List<SkuModel> list = PODetailService.findByProductId(this.proSelectedRow.getId());
//		StringBuilder str = new StringBuilder(
//				"Are you sure to lock this product?\nThis means list of stock keeping unit will be locked.\n");
//		if (list.isEmpty() == false) {
//			for (SkuModel e : list) {
//				str.append("{SKUID: " + e.getId() + "}\n");
//			}
//		}
//
//		int click = JOptionPane.showConfirmDialog(null, str);
//		if (click == JOptionPane.YES_OPTION) {
//			JOptionPane.showMessageDialog(null, "Click Yes");
//		}
//		if (click == JOptionPane.NO_OPTION) {
//			JOptionPane.showMessageDialog(null, "Click No");
//		}
//		if (click == JOptionPane.CANCEL_OPTION) {
//			JOptionPane.showMessageDialog(null, "Click Cancel");
//		}
//		if (click == JOptionPane.CLOSED_OPTION) {
//			JOptionPane.showMessageDialog(null, "Click Close");
//		}
	}

	public void setSelectedPOModel() {
		selectedRow.setId((Long) table.getModel().getValueAt(selectedRowIndex, 0));
		selectedRow.setStaffId((Long) table.getModel().getValueAt(selectedRowIndex, 1));
		selectedRow.setCreatedDate((java.sql.Date) table.getModel().getValueAt(selectedRowIndex, 2));
		selectedRow.setTotal((String) table.getModel().getValueAt(selectedRowIndex, 3));
		selectedRow.setStatus((String) table.getModel().getValueAt(selectedRowIndex, 4));
	}

	public void displayPOToTextField() {
		tfList[0].setText(selectedRow.getId().toString());
		tfList[1].setText(selectedRow.getStaffId().toString());
		tfList[2].setText(selectedRow.getCreatedDate().toString());
		tfList[3].setText(selectedRow.getTotal());
		for (int i = 0; i < 4; i++) {
			if (selectedRow.getStatus().toString().toLowerCase().equals(comboStatus.getItemAt(i).toString().toLowerCase())) {
				
//				comboStatus.setSelectedIndex(i);
				break;
			}
			System.out.print(selectedRow.getStatus().toString().toLowerCase().equals(comboStatus.getItemAt(i).toString().toLowerCase()));

//			System.out.println(comboStatus.getItemAt(i));
		}
	}

	public void menuItemDetailHandle() {
		openDetailFrame(this.selectedRow.getId());
	}

	/*
	 * Open SKU sub jframe
	 */
	public void openDetailFrame(Long poId) {
		PODetailPanel detail = new PODetailPanel(poId);
		JFrame subFrame = new SubFrame(detail);
		/*
		 * If poId > 0 --> detail else add new PO --> null
		 */
		if (poId > 0) {
			detail.loadDataByPOId(poId);
		}
		subFrame.setVisible(true);

	}

}
