package xxminhmie.sgu.javagui.gui.panel.sub;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import xxminhmie.sgu.javagui.gui.common.AddButton;
import xxminhmie.sgu.javagui.gui.common.ChooseFileButton;
import xxminhmie.sgu.javagui.gui.common.DeleteButton;
import xxminhmie.sgu.javagui.gui.common.ResetButton;
import xxminhmie.sgu.javagui.gui.common.SaveButton;
import xxminhmie.sgu.javagui.gui.modeltable.PODetailModelData;
import xxminhmie.sgu.javagui.gui.panel.AbstractPanel;
import xxminhmie.sgu.javagui.gui.panel.Renderer;
import xxminhmie.sgu.javagui.gui.panel.SupplierPanel;
import xxminhmie.sgu.javagui.model.PODetailModel;
import xxminhmie.sgu.javagui.model.POModel;
import xxminhmie.sgu.javagui.model.SkuModel;
import xxminhmie.sgu.javagui.model.SupplierModel;
import xxminhmie.sgu.javagui.service.impl.PODetailService;
import xxminhmie.sgu.javagui.service.impl.POService;
import xxminhmie.sgu.javagui.service.impl.SkuService;
import xxminhmie.sgu.javagui.service.impl.SupplierService;

public class PODetailPanel extends JPanel {
	PODetailService service = new PODetailService();
	JLabel mainLabel;
	/*
	 * Search
	 */
	JPanel searchPanel;
	JTextField search;

	/*
	 * Text field
	 */
	JPanel tfPanel = new JPanel();
	public JTextField[] tfList = new JTextField[6];
	JButton[] more = new JButton[2];

	/*
	 * Table
	 */
	JPanel tbPanel = new JPanel();
	JScrollPane pane;
	JTable table;
	PODetailModelData model;

	PODetailModel selectedRow = new PODetailModel();
	java.util.Map<Long, Long> idSelectedRowList = new HashMap<Long, Long>();// (oiid, skuid)

	int selectedRowIndex;

	/*
	 * POId was being called
	 */
	Long poId;
	Long skuId;

	/*
	 * Button
	 */
	public JPanel btnPanel = new JPanel();
	public AddButton addBtn;
	public SaveButton saveBtn;
	public ResetButton resetBtn;
	public DeleteButton deleteBtn;
	/*
	 * 
	 */
	JFrame skuFrame;
	JFrame supplierFrame;

	public PODetailPanel(Long poId) {
		this.poId = poId;
		this.setBackground(AbstractPanel.PanelBg);
		this.setPreferredSize(new Dimension(AbstractPanel.PanelWidth, AbstractPanel.PanelHeight));
		this.setLayout(null);
		this.init();

	}

	protected void init() {
		/*
		 * Main label
		 */
		mainLabel = new JLabel("Purchase Details Manager - PO ID: " + this.poId);
		mainLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
		mainLabel.setBounds(0, 0, AbstractPanel.PanelWidth, 20);
		add(mainLabel);
		/*
		 * Search
		 */
		searchPanel = new JPanel();
		searchPanel.setBounds(0, 20, AbstractPanel.PanelWidth, 40);
		searchPanel.setLayout(null);
		add(searchPanel);

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
		tfPanel.setLayout(null);
		tfPanel.setBounds(0, 60, AbstractPanel.PanelWidth, 230);
		add(tfPanel);

		String[] infoName = { "PO ID: ", "SKU ID: ", "Supplier ID: ", "Quantity: ", "Unit Price: ", "Sub-Total: " };

		int x = 20;
		int y = 20;
		// this for loop initialize label and text field, set position for both
		for (int i = 0; i < tfList.length; i++) {
			JLabel label = new JLabel(infoName[i]);
			label.setBounds(x, y, 100, 30);
			label.setAlignmentX(RIGHT_ALIGNMENT);
			tfPanel.add(label);

			tfList[i] = new JTextField();
			tfList[i].setText("");
			tfList[i].setBounds(x + 100, y + 5, 160, 20);
			tfPanel.add(tfList[i]);
			y += 30;
		}
		more[0] = new JButton("...");
		more[0].setBounds(20 + 100 + 160 + 4, 20 + 30 + 5, 40, 20);
		tfPanel.add(more[0]);
		more[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moreSkuHandle();
			}

		});

		more[1] = new JButton("...");
		more[1].setBounds(20 + 100 + 160 + 4, 20 + 30 + 5 + 30, 40, 20);
		tfPanel.add(more[1]);
		more[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moreSupplierHandle();
			}

		});

		/** set read - only for ID text field **/
		tfList[0].setEditable(false);
		tfList[1].setEditable(false);
		tfList[2].setEditable(false);
		tfList[4].setEditable(false);
		tfList[5].setEditable(false);

		Color color = new Color(108, 108, 108);
		tfList[0].setForeground(color);
		tfList[1].setForeground(color);
		tfList[2].setForeground(color);
		tfList[4].setForeground(color);
		tfList[5].setForeground(color);

		/*
		 * text field quantity
		 */
		tfList[3].getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				quantityChangeHandle(tfList[3].getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				quantityChangeHandle(tfList[3].getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				quantityChangeHandle(tfList[3].getText());
			}
		});

		/*
		 * Table
		 */
		model = new PODetailModelData();
		table = new JTable(model);
		table.setDefaultRenderer(Object.class, new Renderer());

		model.setColumnWidth(table);

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			/** GET SELECTED VALUE TO DISPLAY ON TEXT FIELD **/
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					selectedRowIndex = table.getSelectedRow();// get index of selected rowIndex
					/** GET MANUALLY ALL ROW **/
					setSelectedDetailModel();
					poId = selectedRow.getPoId();
				}
				/** DISPLAY TO TEXT FIELD **/
				displayDetailToTextField();
				getAllSelectedRow(table);
			}
		});

		tbPanel.setBounds(0, 290, SubFrame.SUBFRAME_W, 500);
		tbPanel.setLayout(null);
		add(tbPanel);

		pane = new JScrollPane(table);
		pane.setBounds(20, 20, SubFrame.SUBFRAME_W - 70, 240);
		tbPanel.add(pane);

		/*
		 * Button
		 */
		btnPanel.setBounds(320, 80, 500, 230);
		btnPanel.setLayout(null);
		add(btnPanel);

		addBtn = new AddButton(20, 20);
		btnPanel.add(addBtn);
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addButtonHandle();
			}
		});


		saveBtn = new SaveButton(20, 50);
		btnPanel.add(saveBtn);
//		saveBtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				saveButtonHandle();
//			}
//		});

		resetBtn = new ResetButton(20, 80);
		btnPanel.add(resetBtn);
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetButtonHandle();
			}
		});

		deleteBtn = new DeleteButton(20, 110);
		btnPanel.add(deleteBtn);
//		deleteBtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				deleteButtonHandle();
//			}
//		});

	}

	/*
	 * Table listener handle
	 */
	public void getAllSelectedRow(JTable entryTable) {
//		AbstractTableModel model = (AbstractTableModel) entryTable.getModel();
		if (entryTable.getRowCount() > 0) {
			if (entryTable.getSelectedRowCount() > 0) {
				int selectedRow[] = entryTable.getSelectedRows();
				idSelectedRowList.clear();
				for (int i : selectedRow) {
					Long poid = (Long) entryTable.getValueAt(i, 0);
					Long skuid = (Long) entryTable.getValueAt(i, 1);
					idSelectedRowList.put(poid, skuid);
				}
			}
		}
	}

//	public void loadData(String str) {
//		this.model.loadData(table, str);
//
//	}
	/*
	 * Reset button handle
	 */
	public void resetButtonHandle() {
		int click = JOptionPane.showConfirmDialog(null, "Are you sure to reset?");
		if (click == JOptionPane.YES_OPTION) {
			resetSku();
		}

	}

	public void resetSku() {
		for (int i = 1; i < this.tfList.length; i++) {
			this.tfList[i].setText("");
		}
	}

	/*
	 * Save button handle
	 */
	public void saveButtonHandle() {
		Long poId = -1L;
		if (!tfList[0].getText().isBlank()) {
			poId = Long.parseLong(tfList[0].getText());
		}

		Long skuId = -1L;
		if (!tfList[1].getText().isBlank()) {
			skuId = Long.parseLong(tfList[1].getText());
		}

		Long supplierId = -1L;
		if (!tfList[2].getText().isBlank()) {
			supplierId = Long.parseLong(tfList[2].getText());
		}

		int quantity = 0;
		if (!tfList[3].getText().isBlank()) {
			try {
				quantity = Integer.parseInt(tfList[3].getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Product's quantity is invalid!");
				tfList[3].requestFocus();
				return;
			}
		}
		String unitPrice = "";
		if (!tfList[4].getText().isBlank()) {
			try {
				unitPrice = tfList[4].getText();
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Product's price is invalid!");
				tfList[4].requestFocus();
				return;
			}
		}
		String subTotal = "";
		if (!tfList[5].getText().isBlank()) {
			subTotal = tfList[5].getText();
		}

		PODetailModel oldModel;

		if (service.findOne(poId, skuId) != null) {
			oldModel = service.findOne(poId, skuId);
			updateTotalPOAfterUpdate(oldModel, subTotal);
			service.update(new PODetailModel(poId, skuId, supplierId, quantity, unitPrice, subTotal));

		} else {
			service.save(new PODetailModel(poId, skuId, supplierId, quantity, unitPrice, subTotal));
			updateTotalPOAfterAdd(this.poId, subTotal);
		}

		model.loadData(table, poId);
		getGeneratedKeys(skuId);
		table.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);

		JOptionPane.showMessageDialog(null, "Save successfully!");

	}

	public void addButtonHandle() {
		this.resetSku();
	}

	public void deleteButtonHandle() {
		if (selectedRowIndex > 0) {
			int click = JOptionPane.showConfirmDialog(null,
					"Are you sure for delete this product in this purchase order?");
			if (click == JOptionPane.YES_OPTION) {
				updateTotalPOAfterDelete(idSelectedRowList);
				service.delete(idSelectedRowList);
				model.loadData(table, this.poId);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You have not selected a value to delete!");
		}
	}

	/*
	 * set selected row after adding
	 */
	public void getGeneratedKeys(Long id) {
		for (int i = 0; i < this.model.getRowCount(); i++) {
			if (id == (Long) this.model.getValueAt(i, 1)) {
				this.selectedRowIndex = i;
				return;
			}
		}
	}

	/*
	 * This method will be called by openPoDetailFrame method from PO Panel
	 */
	public void loadDataByPOId(Long poId) {
		if (this.service.findListByPOId(poId) != null) {
			this.model.loadData(this.table, poId);
			this.poId = poId;
			this.selectedRowIndex = 0;
			this.setSelectedDetailModel();
			this.displayDetailToTextField();
			this.table.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
		} else {
			this.model.setData(null);
		}

	}

	public void setSelectedDetailModel() {
		selectedRow.setPoId((Long) table.getModel().getValueAt(selectedRowIndex, 0));
		selectedRow.setSkuId((Long) table.getModel().getValueAt(selectedRowIndex, 1));
		selectedRow.setSupplierId((Long) table.getModel().getValueAt(selectedRowIndex, 2));
		selectedRow.setQuantity((Integer) table.getModel().getValueAt(selectedRowIndex, 3));
		selectedRow.setUnitPrice((String) table.getModel().getValueAt(selectedRowIndex, 4));
		selectedRow.setSubTotal((String) table.getModel().getValueAt(selectedRowIndex, 5));
	}

	public void displayDetailToTextField() {
		tfList[0].setText(selectedRow.getPoId().toString());
		tfList[1].setText(selectedRow.getSkuId().toString());
		tfList[2].setText(selectedRow.getSupplierId().toString());
		tfList[3].setText(String.valueOf(selectedRow.getQuantity()));
		tfList[4].setText(selectedRow.getUnitPrice());
		tfList[5].setText(selectedRow.getSubTotal());
	}

	public void quantityChangeHandle(String quantity) {
		int qty = -1;
		int price = -1;
		try {
			qty = Integer.parseInt(quantity);
			price = Integer.parseInt(tfList[4].getText());
		} catch (NumberFormatException e) {

		}
		if (qty > 0) {
			int sub = qty * price;
			tfList[5].setText(String.valueOf(sub));
		}
	}

	public void moreSkuHandle() {
		SkuPanel sku = new SkuPanel();
		sku.setSelectedSkuModel();
		sku.displaySkuToTextField();
		sku.table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					doubleClickSkuHandle(table.getValueAt(row, 0));

				}
			}
		});

		skuFrame = new SubFrame(sku);
		skuFrame.setVisible(true);
	}

	public void doubleClickSkuHandle(Object id) {
		SkuService service = new SkuService();
		SkuModel model = service.findOne((Long) id);
		tfList[1].setText(model.getId().toString());
		tfList[4].setText(model.getPrice());

		skuFrame.dispose();
	}

	public void moreSupplierHandle() {
		SupplierPanel supplier = new SupplierPanel();
		supplier.setSelectedSupplierModel();
		supplier.displaySupplierToTextField();
		supplier.table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					doubleClickSupplierHandle(table.getValueAt(row, 0));

				}
			}
		});

		supplierFrame = new SubFrame(supplier);
		supplierFrame.setVisible(true);

	}

	/*
	 * 
	 */
	public void doubleClickSupplierHandle(Object id) {
		SupplierService service = new SupplierService();
		SupplierModel model = service.findOne((Long) id);
		tfList[2].setText(model.getId().toString());

		supplierFrame.dispose();
	}

	/*
	 * 
	 */
	public void updateTotalPOAfterAdd(Long poid, String subTotal) {
		POService service = new POService();
		POModel model = service.findOne(poId);
		if (model != null) {
			int temp = Integer.parseInt(model.getTotal());
			int sub = Integer.parseInt(subTotal);
			int total = temp + sub;
			model.setTotal(String.valueOf(total));
		}
		service.update(model);
	}

	public void updateTotalPOAfterUpdate(PODetailModel old, String subTotal) {
		POService service = new POService();
		POModel model = service.findOne(poId);
		if (model != null) {
			int temp = Integer.parseInt(model.getTotal());
			int sub = Integer.parseInt(subTotal);

			int total = temp + sub - Integer.parseInt(old.getSubTotal());
			model.setTotal(String.valueOf(total));
		}
		service.update(model);
	}

	/*
	 * 
	 */
	public void updateTotalPOAfterDelete(Map<Long, Long> deletedMap) {
		Set<Long> set = deletedMap.keySet();
		int temp = 0;
		int sub = 0;
		int total = 0;
		POService service = new POService();
		POModel model = null;

		for (Long key : set) {
			model = service.findOne(key);
			if (model != null) {
				temp = Integer.parseInt(model.getTotal());
				PODetailModel detail = this.service.findOne(key, deletedMap.get(key));
				sub = Integer.parseInt(detail.getSubTotal());
				total = temp - sub;
				model.setTotal(String.valueOf(total));
			}
		}
		service.update(model);
	}

	/*
	 * 
	 */
	public void updateQtySkuAfterAdd(Long skuId, int quantity) {
		SkuService service = new SkuService();
		SkuModel model = service.findOne(skuId);
		if (model != null) {
			int temp = model.getQuantity();
			int avai = temp - quantity;
			System.out.println(avai);
			model.setQuantity(avai);
		}
		service.update(model);
	}

	/*
	 * 
	 */
	public void updateQtySkuAfterUpdate(Long skuId, int newQuantity) {
		SkuService service = new SkuService();
		SkuModel model = service.findOne(skuId);
		int oldQuantity = this.service.findOne(this.poId, skuId).getQuantity();
		if (model != null) {
			int skuQuantity = model.getQuantity();
			int avai = skuQuantity - newQuantity + oldQuantity;
			System.out.println(avai);
			model.setQuantity(avai);
		}
		service.update(model);
	}
	/*
	 * 
	 */

	public void updateQtySkuAfterConfirm() {
		SkuService service = new SkuService();
		ArrayList<PODetailModel> list = (ArrayList<PODetailModel>) this.service.findListByPOId(this.poId);
		if (list != null) {
			for(PODetailModel e : list) {
				int quantity = e.getQuantity();
				SkuModel sku = service.findOne(e.getSkuId());
				int available = sku.getQuantity() + quantity;
				sku.setQuantity(available);
				service.update(sku);
			}
		}
	}

}
