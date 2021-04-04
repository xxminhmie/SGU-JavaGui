package xxminhmie.sgu.javagui.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import xxminhmie.sgu.javagui.gui.modeltable.POModelData;
import xxminhmie.sgu.javagui.gui.validator.WhiteSpaceValidator;
import xxminhmie.sgu.javagui.model.POModel;
import xxminhmie.sgu.javagui.service.impl.POService;
import xxminhmie.sgu.javagui.service.impl.PODetailService;

public class POPanel extends JPanel {
	POService poService = new POService();
	PODetailService detailService = new PODetailService();

	/******************************************************************
	 * 
	 * Attributes
	 * 
	 ******************************************************************/
	JLabel mainLabel = new JLabel("Order Purchase Manager");// "Customer Management",BorderLayout.NORTH

	JPanel mainPanel = new JPanel();// BorderLayout.CENTER

	JPanel panel = new JPanel();
	PODetailPanel detailPanel = new PODetailPanel(this.detailService);// Contains list of text field

	/*
	 * Text field
	 */
	JPanel tfPanel;// Contains list of text field
	JTextField[] tfList = new JTextField[6];
	JComboBox comboStatus;
	
	JButton[] more = new JButton[3];

	/*
	 * Search
	 */
	JPanel searchPanel;// Contains SEARCH text field
	JTextField search;

	/*
	 * Table
	 */
	JPanel tbPanel;// Contains j table on scroll pane
	JScrollPane pane;
	JTable table;
	POModelData model;

	/*
	 * Click on table
	 */
	POModel selectedRow = new POModel();// Customer is being selected from Table
	java.util.List<Long> idSelectedRowList = new java.util.ArrayList<Long>();// Contains list of customer's ID to

	int selectedRowIndex;

	Boolean flagReset = false;

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
		 * Product Panel - Left
		 */
		this.panel.setBounds(0, 0, 380, 700);
		this.panel.setLayout(null);
		this.mainPanel.add(this.panel);

		/*
		 * SKU Panel - Right
		 */
		this.detailPanel.setBounds(380, 30, 1000, 700);
		this.mainPanel.add(this.detailPanel);

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
				detailPanel.loadData(search.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				model.loadData(table, search.getText());
				detailPanel.loadData(search.getText());

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				model.loadData(table, search.getText());
				detailPanel.loadData(search.getText());

			}

		});
		/*
		 * Text field
		 */
		this.tfPanel = new JPanel();
		this.tfPanel.setBounds(0, 30, 380, 240);
		this.tfPanel.setOpaque(true);
		this.tfPanel.setLayout(null);
		this.panel.add(this.tfPanel);

		String[] infoName = { "ID: ", "StaffID: ", "SupplierID: ", "Created Date: ", "Total", "Status: " };
		String[] comboStatusSelection = { "Pending ", "Confirmed", "Sent to Supplier", "Canceled" };
		int x = 10;
		int y = 20;
		// this for loop initialize label and text field, set position for both
		for (int i = 0; i < this.tfList.length; i++) {
			JLabel label = new JLabel(infoName[i]);
			this.tfPanel.add(label);
			if (i == 5) {
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
		int moreX = 10+80+200;
		int moreY = 20+30+5+30;
		for(int i=0; i<3; i++) {
			this.more[i] = new JButton("...");
			this.more[i].setBounds(moreX, moreY, 40, 20);
			this.panel.add(this.more[i]);
			moreY+=30;
		}
		/** set read - only for ID text field **/
		this.tfList[0].setEditable(false);
		this.tfList[0].setForeground(new Color(108, 108, 108));

		this.panel.add(this.tfPanel);

		/******************************************************************
		 * 
		 * Table panel
		 */
		this.tbPanel = new JPanel();
		this.tbPanel.setBounds(10, 260, 1000, 360);
		this.tbPanel.setLayout(null);

		/*
		 * Table
		 */
		this.model = new POModelData();
		this.table = new JTable(model);
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		this.table.setDefaultRenderer(Object.class, new Renderer());

		model.setColumnWidth(table);

		/*
		 * Table adding listener
		 */
		/** GET SELECTED VALUE TO DISPLAY ON TEXT FIELD **/
		this.table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int rowIndex = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (rowIndex >= 0 && col >= 0) {
					selectedRowIndex = rowIndex;
					/** GET MANUALLY ALL ROW **/
					selectedRow.setId((Long) table.getModel().getValueAt(rowIndex, 0));
					selectedRow.setStaffId((Long) table.getModel().getValueAt(rowIndex, 1));
					selectedRow.setSupplierId((Long) table.getModel().getValueAt(rowIndex, 2));
					selectedRow.setCreatedDate((java.sql.Date) table.getModel().getValueAt(rowIndex, 3));
					selectedRow.setTotal((String) table.getModel().getValueAt(rowIndex, 4));
					selectedRow.setStatus((String) table.getModel().getValueAt(rowIndex, 5));

				}
				/** DISPLAY TO TEXT FIELD **/
				tfList[0].setText(selectedRow.getId().toString());
				tfList[1].setText(selectedRow.getStaffId().toString());
				tfList[2].setText(selectedRow.getSupplierId().toString());
				tfList[3].setText(selectedRow.getCreatedDate().toString());
				tfList[4].setText(selectedRow.getTotal());
				for (int i = 0; i < 4; i++) {
					if (selectedRow.getStatus().equals(comboStatus.getItemAt(i))) {
						comboStatus.setSelectedIndex(i);
						break;
					}
				}
				getAllSelectedRow(table);

				findAllDetailByProductId(selectedRow.getId());
			}
		});

//		JPopupMenu popupMenu = new JPopupMenu();
//		JMenuItem menuItemLock = new JMenuItem("Lock product");
//		popupMenu.add(menuItemLock);
//
//		table.setComponentPopupMenu(popupMenu);
//
//		menuItemLock.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				menuItemLockHandle();
//			}
//
//		});

		/*
		 * scroll pane
		 */
		this.pane = new JScrollPane(this.table);
		this.pane.setBounds(0, 20, 360, 360);
		/** ADD SCROLL PANE TO MAIN PANEL **/
		this.tbPanel.add(this.pane);
		this.panel.add(this.tbPanel);

		/*
		 * reset button listener
		 */
//		this.detailPanel.resetBtn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				resetButtonHandle();
//			}
//
//		});
		/*
		 * reset button
		 */
		JPopupMenu popupMenuBtn = new JPopupMenu();
		JMenuItem menuItemResetAll = new JMenuItem("Reset All");
		JMenuItem menuItemResetDetail = new JMenuItem("Reset PO Detail");

		popupMenuBtn.add(menuItemResetAll);
		popupMenuBtn.add(menuItemResetDetail);

		this.detailPanel.resetBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				popupMenuBtn.show(e.getComponent(), e.getX(), e.getY());
			}
		});

		menuItemResetAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetAllHandle();
			}

		});
		menuItemResetDetail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetDetailHandle();
			}

		});
		
		/*
		 * button adding action listener
		 */
		this.detailPanel.addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addButtonHandle();
			}

		});
		this.detailPanel.tfList[1].getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				displayProductFromSku(Long.parseLong(detailPanel.tfList[1].getText()));
			}

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				displayProductFromSku(Long.parseLong(detailPanel.tfList[1].getText()));

			}

		});
		
		/*
		 * more button add listener
		 */
		this.more[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame();
				CustomerPanel p = new CustomerPanel();
				f.getContentPane().add(p);
				f.setSize(new Dimension(1000,500));
				f.setVisible(true);
				f.setLocationRelativeTo(null); // this will center your application

			}
			
		});
	}// END CONSTRUCTOR////////////////////////////////////

	/*
	 * button handle
	 */
	public void resetAllHandle() {
//		this.flagReset = true;
		this.detailPanel.resetButtonHandle();
		for (int i = 0; i < this.tfList.length; i++) {
			if(i==5) {
				comboStatus.setSelectedItem(null);
			}else {
				this.tfList[i].setText("");
			}
		}
		
	}
	public void resetDetailHandle() {
		this.detailPanel.resetButtonHandle();
	}

	public int addButtonHandle() {
//		Long id = null;
//		if (this.tfList[0].getText().equals("") == false) {
//			try {
//				id = Long.parseLong(tfList[0].getText());
//			} catch (java.lang.NumberFormatException e) {
//				System.out.println("ProductPanel class addButtonHandle method" + e.getMessage());
//			}
//		}
//
//		String name = tfList[1].getText();
//		if (name.isBlank()) {
//			JOptionPane.showMessageDialog(null, "Product's name must be not null!");
//			tfList[1].requestFocus();
//			return -1;
//		}
//		name = WhiteSpaceValidator.validate(name);
//
//		String brand = tfList[2].getText();
//		if (brand.isBlank()) {
//			JOptionPane.showMessageDialog(null, "Product's brand must be not null!");
//			tfList[2].requestFocus();
//			return -1;
//		}
//		brand = WhiteSpaceValidator.validate(brand);
//
//		String des = WhiteSpaceValidator.validate(text.getText());
//
//		String status = null;
//		if (comboStatus.getSelectedItem() != null) {
//			status = comboStatus.getSelectedItem().toString();
//			if (status.equals("Locked")) {
//				JOptionPane.showMessageDialog(null, "Product's status must be not locked while adding!");
//				comboStatus.requestFocus();
//				return -1;
//			}
//		}
//		// Create new product
//		Long savedId;
//		if (id == null) {
//			if (des != null) {
////				savedId = POService.save(new ProductModel(name, brand, des));
//			} else {
////				savedId = POService.save(new ProductModel(name, brand));
//			}
//		} else {
//			// Update product
//			if (des != null) {
////				ProductModel pro = POService.update(new ProductModel(id, name, brand, des));
////				savedId = pro.getId();
//			} else {
////				ProductModel pro = POService.update(new ProductModel(id, name, brand));
////				savedId = pro.getId();
//
//			}
//		}

//		this.model.loadData(this.table);
//		getGeneratedKeys(savedId);
//		table.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
//
//		this.detailPanel.addButtonHandle(savedId);
		return 0;
	}

	// set selected row after adding
	public void getGeneratedKeys(Long id) {
		for (int i = 0; i < this.model.getRowCount(); i++) {
			if (id == (Long) this.model.getValueAt(i, 0)) {
				this.selectedRowIndex = i;
				return;
			}
		}
	}

	/*
	 * text field handle
	 */
	public void change(int index, String str) {
//		if (flagReset == false) {
//			if (str != null && tfList[index] != null) {
//				if (str.equals(tfList[index].getText()) == false) {
//					tfList[index].setForeground(AbstractPanel.DocumentListener);
//				} else {
//					tfList[index].setForeground(Color.BLACK);
//				}
//			}
//		} else {
//			tfList[index].setForeground(Color.BLACK);
//		}

	}

	/*
	 * click on sku table, the row product auto selected
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
	 * delete multi row handle
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
	 * click on product table, find list of purchase detail of this selected purchase
	 */
	//TODO
	public void findAllDetailByProductId(Long id) {
		this.detailPanel.loadData(id.toString());
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
}
