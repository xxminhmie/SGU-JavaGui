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

import javax.swing.JComboBox;
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

import xxminhmie.sgu.javagui.gui.modeltable.ProductModelData;
import xxminhmie.sgu.javagui.gui.validator.WhiteSpaceValidator;
import xxminhmie.sgu.javagui.model.ProductModel;
import xxminhmie.sgu.javagui.model.SkuModel;
import xxminhmie.sgu.javagui.service.impl.ProductService;
import xxminhmie.sgu.javagui.service.impl.SkuService;

public class ProductPanel extends JPanel {

	ProductService productService = new ProductService();
	SkuService skuService = new SkuService();

	/******************************************************************
	 * 
	 * Attributes
	 * 
	 ******************************************************************/
	JLabel mainLabel = new JLabel("Product Manager");// "Customer Management",BorderLayout.NORTH

	JPanel mainPanel = new JPanel();// BorderLayout.CENTER

	JPanel panel = new JPanel();
	SkuPanel skuPanel = new SkuPanel(this.skuService);

	/*
	 * Text field
	 */
	JPanel tfPanel;// Contains list of text field
	JTextField[] tfList;
	JTextArea text;
	JComboBox comboStatus;

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
	ProductModelData model;

	/*
	 * Click on table
	 */
	ProductModel proSelectedRow = new ProductModel();// Customer is being selected from Table
	java.util.List<Long> proIdSelectedRowList = new java.util.ArrayList<Long>();// Contains list of customer's ID to

	int selectedRowIndex;

	Boolean flagReset = false;

	/*
	 * Constructor
	 */
	public ProductPanel() {
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
		this.panel.setBounds(0, 0, 370, 700);
		this.panel.setLayout(null);
		this.mainPanel.add(this.panel);

		/*
		 * SKU Panel - Right
		 */
		this.skuPanel.setBounds(380, 30, 1000, 700);
		this.mainPanel.add(this.skuPanel);

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
				skuPanel.loadData(search.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				model.loadData(table, search.getText());
				skuPanel.loadData(search.getText());

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				model.loadData(table, search.getText());
				skuPanel.loadData(search.getText());

			}

		});
		/*
		 * Text field
		 */
		this.tfPanel = new JPanel();
		this.tfPanel.setBounds(0, 30, 310, 240);
		this.tfPanel.setOpaque(true);
		this.tfPanel.setLayout(null);
		this.panel.add(this.tfPanel);

		String[] infoName = { "ID: ", "Name: ", "Brand: ", "Description: ", "Status: " };
		String[] comboStatusSelection = { "Actived", "Locked" };
		this.tfList = new JTextField[5];
		int x = 10;
		int y = 20;
		// this for loop initialize label and text field, set position for both
		for (int i = 0; i < this.tfList.length; i++) {
			JLabel label = new JLabel(infoName[i]);
			this.tfPanel.add(label);
			if (i == 4) {
				label.setBounds(x, y+50, 100, 30);
				comboStatus = new JComboBox(comboStatusSelection);
				comboStatus.setSelectedItem(null);
				comboStatus.setBounds(x + 80, y + 5 + 50, 200, 20);
				this.tfPanel.add(comboStatus);
			} else {
				if (i == 3) {
					label.setBounds(x, y, 100, 30);
					text = new JTextArea();
					text.setBounds(x + 80 + 4, y + 5, 190, 70);
					text.setColumns(20);
					text.setLineWrap(true);
					text.setRows(5);
					text.setWrapStyleWord(true);
					this.tfPanel.add(text);

				} else {
					label.setBounds(x, y, 100, 30);

					tfList[i] = new JTextField();
					this.tfList[i].setBounds(x + 80, y + 5, 200, 20);
					this.tfPanel.add(this.tfList[i]);
				}

			}
			y += 30;

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
		this.model = new ProductModelData();
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
					proSelectedRow.setId((Long) table.getModel().getValueAt(rowIndex, 0));
					proSelectedRow.setName((String) table.getModel().getValueAt(rowIndex, 1));
					proSelectedRow.setBrand((String) table.getModel().getValueAt(rowIndex, 2));
					proSelectedRow.setDescription((String) table.getModel().getValueAt(rowIndex, 3));
					proSelectedRow.setStatus((String) table.getModel().getValueAt(rowIndex, 4));

				}
				/** DISPLAY TO TEXT FIELD **/
				tfList[0].setText(proSelectedRow.getId().toString());
				tfList[1].setText(proSelectedRow.getName());
				tfList[2].setText(proSelectedRow.getBrand());
//				tfList[3].setText(proSelectedRow.getDescription());
				text.setText(proSelectedRow.getDescription());
				if (proSelectedRow.getStatus().equals(comboStatus.getItemAt(0))) {
					comboStatus.setSelectedIndex(0);
				} else {
					comboStatus.setSelectedIndex(1);
				}

				getAllSelectedRow(table);

				findAllSkuByProductId(proSelectedRow.getId());
			}
		});

		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem menuItemLock = new JMenuItem("Lock product");
		popupMenu.add(menuItemLock);

		table.setComponentPopupMenu(popupMenu);

		menuItemLock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuItemLockHandle();
			}

		});

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
		this.skuPanel.resetBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				resetButtonHandle();
			}

		});
		/*
		 * button adding action listener
		 */
		this.skuPanel.addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addButtonHandle();
			}

		});
		this.skuPanel.tfList[1].getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				displayProductFromSku(Long.parseLong(skuPanel.tfList[1].getText()))	;			
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				displayProductFromSku(Long.parseLong(skuPanel.tfList[1].getText()))	;			
				
			}
			
		});
	}// END CONSTRUCTOR////////////////////////////////////

	/*
	 * button handle
	 */
	public void resetButtonHandle() {
//		this.flagReset = true;
		this.skuPanel.resetButtonHandle();

		for (int i = 0; i < this.tfList.length; i++) {
			if (i == 4) {
				comboStatus.setSelectedItem(null);
			} else {
				if (i == 3) {
					text.setText("");
				} else {
					this.tfList[i].setText("");

				}
			}

		}
	}

	public int addButtonHandle() {
		Long id = null;
		if (this.tfList[0].getText().equals("") == false) {
			try {
				id = Long.parseLong(tfList[0].getText());
			} catch (java.lang.NumberFormatException e) {
				System.out.println("ProductPanel class addButtonHandle method" + e.getMessage());
			}
		}

		String name = tfList[1].getText();
		if (name.isBlank()) {
			JOptionPane.showMessageDialog(null, "Product's name must be not null!");
			tfList[1].requestFocus();
			return -1;
		}
		name = WhiteSpaceValidator.validate(name);

		String brand = tfList[2].getText();
		if (brand.isBlank()) {
			JOptionPane.showMessageDialog(null, "Product's brand must be not null!");
			tfList[2].requestFocus();
			return -1;
		}
		brand = WhiteSpaceValidator.validate(brand);

		String des = WhiteSpaceValidator.validate(text.getText());

		String status = null;
		if (comboStatus.getSelectedItem() != null) {
			status = comboStatus.getSelectedItem().toString();
			if (status.equals("Locked")) {
				JOptionPane.showMessageDialog(null, "Product's status must be not locked while adding!");
				comboStatus.requestFocus();
				return -1;
			}
		}
		// Create new product
		Long savedId;
		if (id == null) {
			if (des != null) {
				savedId = productService.save(new ProductModel(name, brand, des));
			} else {
				savedId = productService.save(new ProductModel(name, brand));
			}
		} else {
			// Update product
			if (des != null) {
				ProductModel pro = productService.update(new ProductModel(id, name, brand, des));
				savedId = pro.getId();
			} else {
				ProductModel pro = productService.update(new ProductModel(id, name, brand));
				savedId = pro.getId();

			}
		}

		this.model.loadData(this.table);
		getGeneratedKeys(savedId);
		table.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);

		this.skuPanel.addButtonHandle(savedId);
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

	/*
	 * click on sku table, the row product auto selected
	 */
	public void displayProductFromSku(Long productId) {
		this.proSelectedRow = this.productService.findOne(productId);

		/** DISPLAY TO TEXT FIELD **/
		tfList[0].setText(proSelectedRow.getId().toString());
		tfList[1].setText(proSelectedRow.getName());
		tfList[2].setText(proSelectedRow.getBrand());
		text.setText(proSelectedRow.getDescription());
		if (proSelectedRow.getStatus().equals(comboStatus.getItemAt(0))) {
			comboStatus.setSelectedIndex(0);
		} else {
			comboStatus.setSelectedIndex(1);
		}
		getAllSelectedRow(table);
		int row = -1;
		for (int rowIndex = 0; rowIndex < this.model.getRowCount(); rowIndex++) {
			Long idAtRow = (Long) this.model.getValueAt(rowIndex, 0);

			if (idAtRow == productId) {
				row = rowIndex;
				break;
			}
		}
		if (row >= 0) {
			this.table.setRowSelectionInterval(row, row);
		}
	}

	/*
	 * delete multi row handle
	 */
	public void getAllSelectedRow(JTable entryTable) {
//			AbstractTableModel model = (AbstractTableModel) entryTable.getModel();
		if (entryTable.getRowCount() > 0) {
			if (entryTable.getSelectedRowCount() > 0) {
				int selectedRow[] = entryTable.getSelectedRows();
				proIdSelectedRowList.clear();
				for (int i : selectedRow) {
					Long id = (Long) entryTable.getValueAt(i, 0);
					proIdSelectedRowList.add(id);
				}
			}
		}
	}

	/*
	 * click on product table, find list of sku of this selected product
	 */
	public void findAllSkuByProductId(Long id) {
		this.skuPanel.loadData(id);
		this.skuPanel.tfList[1].setText(String.valueOf(id));
	}

	public void menuItemLockHandle() {
		List<SkuModel> list = skuService.findByProductId(this.proSelectedRow.getId());
		StringBuilder str = new StringBuilder(
				"Are you sure to lock this product?\nThis means list of stock keeping unit will be locked.\n");
		if (list.isEmpty() == false) {
			for (SkuModel e : list) {
				str.append("{SKUID: " + e.getId() + "}\n");
			}
		}

		int click = JOptionPane.showConfirmDialog(null, str);
		if (click == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "Click Yes");
		}
		if (click == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null, "Click No");
		}
		if (click == JOptionPane.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "Click Cancel");
		}
		if (click == JOptionPane.CLOSED_OPTION) {
			JOptionPane.showMessageDialog(null, "Click Close");
		}
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
