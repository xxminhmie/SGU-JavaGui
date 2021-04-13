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
import xxminhmie.sgu.javagui.gui.common.ResetButton;
import xxminhmie.sgu.javagui.gui.common.SaveButton;
import xxminhmie.sgu.javagui.gui.modeltable.ProductModelData;
import xxminhmie.sgu.javagui.gui.panel.sub.SkuPanel;
import xxminhmie.sgu.javagui.gui.panel.sub.SubFrame;
import xxminhmie.sgu.javagui.gui.validator.WhiteSpaceValidator;
import xxminhmie.sgu.javagui.model.ProductModel;
import xxminhmie.sgu.javagui.model.SkuModel;
import xxminhmie.sgu.javagui.service.impl.ProductService;
import xxminhmie.sgu.javagui.service.impl.SkuService;

public class DiscountPanel extends JPanel {

	ProductService service = new ProductService();
	JLabel mainLabel = new JLabel("Bill Manager");
	JPanel mainPanel = new JPanel();
	JPanel panel = new JPanel();
	/*
	 * Text field
	 */
	JPanel tfPanel;
	JTextField[] tfList;
	JTextArea text;
	JComboBox comboStatus;
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
	AddButton addSkuBtn;
	AddButton addBtn;
	DeleteButton deleteBtn;
	/*
	 * Table
	 */
	JPanel tbPanel;
	JScrollPane pane;
	JTable table;
	ProductModelData model;
	/*
	 * Handle clicking on table
	 */
	ProductModel selectedRow = new ProductModel();// Product is being selected from Table
	java.util.List<Long> idSelectedRowList = new java.util.ArrayList<Long>();// Contains list of product's ID to
	int selectedRowIndex = -1;

	/*
	 * Constructor
	 */
	public DiscountPanel() {
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
		 * Product Panel
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
		this.searchPanel.add(this.search);

		/*
		 * Search's listener
		 */
		this.search.addMouseListener(new MouseAdapter() {
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
		tfPanel.setBounds(0, 30, 310, 240);
		tfPanel.setOpaque(true);
		tfPanel.setLayout(null);
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
				label.setBounds(x, y + 50, 100, 30);
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
		/*
		 * set read - only for ID text field
		 */
		tfList[0].setEditable(false);
		tfList[0].setForeground(new Color(108, 108, 108));

		panel.add(this.tfPanel);
		/*
		 * Button panel
		 */
		btnPanel.setBounds(320, 60, 200, 200);
		btnPanel.setLayout(null);
		panel.add(btnPanel);

		addBtn = new AddButton(0, 10, 120, 20);
		addBtn.setNameBtn("New Product");
		addBtn.setBorder(null);
		btnPanel.add(addBtn);
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addButtonHandle();
			}

		});

		saveBtn = new SaveButton(0, 40, 120, 20);
		btnPanel.add(saveBtn);
		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				saveButtonHandle();
			}

		});
		resetBtn = new ResetButton(0, 70, 120, 20);
		btnPanel.add(resetBtn);
		resetBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				resetButtonHandle();
			}

		});

		addSkuBtn = new AddButton(0, 100, 120, 20);
		addSkuBtn.setNameBtn("New SKUs");
		btnPanel.add(addSkuBtn);
		addSkuBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addSkuButtonHandle();
			}

		});

		deleteBtn = new DeleteButton(0, 130, 120, 20);
		btnPanel.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
		model = new ProductModelData();
		table = new JTable(model);
		table.setDefaultRenderer(Object.class, new Renderer());

		model.setColumnWidth(table);

		/*
		 * Table's listener
		 */
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			/** GET SELECTED VALUE TO DISPLAY ON TEXT FIELD **/
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int rowIndex = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (rowIndex >= 0 && col >= 0) {
					selectedRowIndex = rowIndex;
					/** GET MANUALLY ALL ROW **/
					setSelectedProductModel();
				}
				/** DISPLAY TO TEXT FIELD **/
				displayProductToTextField();
				/** **/
				getAllSelectedRow(table);
//				findAllSkuByProductId(selectedRow.getId());
			}
		});

		/*
		 * Right click on table's row
		 */
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem menuItemDetail = new JMenuItem("Detail");
		JMenuItem menuItemLock = new JMenuItem("Lock");

		popupMenu.add(menuItemDetail);
		popupMenu.add(menuItemLock);

		table.setComponentPopupMenu(popupMenu);

		menuItemLock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuItemLockHandle();
			}

		});
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

	}// END CONSTRUCTOR////////////////////////////////////

	/*
	 * Reset button handle
	 */
	public void resetButtonHandle() {
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

	/*
	 * Save button handle
	 */
	public void saveButtonHandle() {
		int click = JOptionPane.showConfirmDialog(null, "Are you sure to save this changes?");
		if (click == JOptionPane.YES_OPTION) {
			this.saveAction();
		}
	}

	public int saveAction() {
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
				savedId = service.save(new ProductModel(name, brand, des));
			} else {
				savedId = service.save(new ProductModel(name, brand));
			}
		} else {
			// Update product
			if (des != null) {
				ProductModel pro = service.update(new ProductModel(id, name, brand, des));
				savedId = pro.getId();
			} else {
				ProductModel pro = service.update(new ProductModel(id, name, brand));
				savedId = pro.getId();

			}
		}

		this.model.loadData(this.table);
		getGeneratedKeys(savedId);
		table.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
		return 0;
	}

	/*
	 * add new SKUs button handle
	 */
	public void addSkuButtonHandle() {
		if (this.selectedRowIndex < 0) {
			JOptionPane.showMessageDialog(null, "Please select one product row on table to view details!");
		} else {
//			SkuService service = new SkuService();
//			if(service.findByProductId(this.selectedRow.getId())==null) {
//				openSkuFrame(-1L);
//			}else {
			openSkuFrame(this.selectedRow.getId());
//			}
		}
	}

	/*
	 * add new product button handle
	 */
	public void addButtonHandle() {
		this.tfList[0].setText("");
	}

	/*
	 * delete button handle
	 */
	public void deleteButtonHandle() {
		int click = JOptionPane.showConfirmDialog(null, "Are you sure to delete this product?");
		if (click == JOptionPane.YES_OPTION) {
//			this.service.delete(this.selectedRowList.toArray(a));
			service.delete(this.idSelectedRowList.toArray(new Long[this.idSelectedRowList.size()]));
			model.loadData(this.table);
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

	public void menuItemLockHandle() {
		SkuService service = new SkuService();
		List<SkuModel> list = service.findByProductId(this.selectedRow.getId());
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

	public void menuItemDetailHandle() {
		openSkuFrame(this.selectedRow.getId());
	}

	/*
	 * Open SKU sub jframe
	 */
	public void openSkuFrame(Long productId) {
		SkuPanel skuPanel = new SkuPanel(productId);
		JFrame subFrame = new SubFrame(skuPanel);

		/*
		 * If productId > 0 -- detail else add new SKU -- null
		 */
		if (productId > 0) {
			skuPanel.loadDataByProductId(productId);
		}
		subFrame.setVisible(true);

	}

	/*
	 * 
	 */
	public void setSelectedProductModel() {
		selectedRow.setId((Long) table.getModel().getValueAt(selectedRowIndex, 0));
		selectedRow.setName((String) table.getModel().getValueAt(selectedRowIndex, 1));
		selectedRow.setBrand((String) table.getModel().getValueAt(selectedRowIndex, 2));
		selectedRow.setDescription((String) table.getModel().getValueAt(selectedRowIndex, 3));
		selectedRow.setStatus((String) table.getModel().getValueAt(selectedRowIndex, 4));
	}

	/*
	 * 
	 */
	public void displayProductToTextField() {
		tfList[0].setText(selectedRow.getId().toString());
		tfList[1].setText(selectedRow.getName());
		tfList[2].setText(selectedRow.getBrand());
		// Description
		text.setText(selectedRow.getDescription());
		if (selectedRow.getStatus().equals(comboStatus.getItemAt(0))) {
			comboStatus.setSelectedIndex(0);
		} else {
			comboStatus.setSelectedIndex(1);
		}

	}

}
