package xxminhmie.sgu.javagui.gui.panel.sub;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
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
import xxminhmie.sgu.javagui.gui.common.DeleteButton;
import xxminhmie.sgu.javagui.gui.common.ResetButton;
import xxminhmie.sgu.javagui.gui.common.SaveButton;
import xxminhmie.sgu.javagui.gui.modeltable.BillDetailModelData;
import xxminhmie.sgu.javagui.gui.panel.AbstractPanel;
import xxminhmie.sgu.javagui.gui.panel.Renderer;
import xxminhmie.sgu.javagui.model.BillDetailModel;
import xxminhmie.sgu.javagui.model.BillModel;
import xxminhmie.sgu.javagui.model.DiscountDetailModel;
import xxminhmie.sgu.javagui.model.SkuModel;
import xxminhmie.sgu.javagui.pdf.PDFExporter;
import xxminhmie.sgu.javagui.service.impl.BillDetailService;
import xxminhmie.sgu.javagui.service.impl.BillService;
import xxminhmie.sgu.javagui.service.impl.DiscountDetailService;
import xxminhmie.sgu.javagui.service.impl.SkuService;

public class BillDetailPanel extends JPanel {
	BillDetailService service = new BillDetailService();

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
	public JTextField[] tfList = new JTextField[5];
	JTextField rate;
	JButton[] more = new JButton[1];

	/*
	 * Table
	 */
	JPanel tbPanel = new JPanel();
	JScrollPane pane;
	JTable table;
	BillDetailModelData model;

	BillDetailModel selectedRow = new BillDetailModel();
	java.util.Map<Long, Long> idSelectedRowList = new HashMap<Long, Long>();// (billid, skuid)
	int selectedRowIndex;

	Long billId;
	Long skuId;

	/*
	 * Button
	 */
	public JPanel btnPanel = new JPanel();
	AddButton addBtn;
	public SaveButton saveBtn;
	ResetButton resetBtn;
	ResetButton pdfBtn;

//	DeleteButton deleteBtn;

	JFrame subFrame;

	public BillDetailPanel(Long billId) {
		this.billId = billId;
		setBackground(AbstractPanel.PanelBg);
		setPreferredSize(new Dimension(AbstractPanel.PanelWidth, AbstractPanel.PanelHeight));
		setLayout(null);
		init();
	}

	public BillDetailPanel() {
		setBackground(AbstractPanel.PanelBg);
		setPreferredSize(new Dimension(AbstractPanel.PanelWidth, AbstractPanel.PanelHeight));
		setLayout(null);
		init();
	}

	protected void init() {
		/*
		 * Main label
		 */
		mainLabel = new JLabel("Bill Details Manager - Bill ID: " + this.billId);
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
		tfPanel.setLayout(null);
		tfPanel.setBounds(0, 60, AbstractPanel.PanelWidth, 230);
		add(tfPanel);

		String[] infoName = { "Bill ID: ", "SKU ID: ", "Discount ID:  ", "Quantity: ", "Sub-Total: " };

		int x = 20;
		int y = 20;

		for (int i = 0; i < tfList.length; i++) {
			JLabel label = new JLabel(infoName[i]);
			label.setBounds(x, y, 100, 30);
			label.setAlignmentX(RIGHT_ALIGNMENT);
			tfPanel.add(label);
			tfList[i] = new JTextField();
			tfList[i].setText("");
			if (i == 2) {
				tfList[i].setBounds(x + 100, y + 5, 60, 20);
				rate = new JTextField();
				rate.setBounds(x + 100 + 70, y + 5, 60, 20);
				JLabel percent = new JLabel("(%)");
				percent.setBounds(x + 100 + 60 + 60 + 10, y + 5, 20, 20);
				tfPanel.add(rate);
				tfPanel.add(percent);
				rate.setEditable(false);
			} else {
				tfList[i].setBounds(x + 100, y + 5, 160, 20);

			}
			tfPanel.add(this.tfList[i]);
			y += 30;
		}
		more[0] = new JButton("...");
		more[0].setBounds(20 + 100 + 160, 20 + 30, 40, 20);
		more[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moreSkuHandle();
			}
		});
		tfPanel.add(more[0]);

//		more[1] = new JButton("...");
//		more[1].setBounds(20 + 100 + 160, 20 + 30 + 30, 40, 20);
//		tfPanel.add(more[1]);
//		more[1].addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				moreDiscountHandle();
//			}
//		});

		/** set read - only for ID text field **/
		tfList[0].setEditable(false);
		tfList[1].setEditable(false);
		tfList[2].setEditable(false);
		tfList[4].setEditable(false);
		rate.setEditable(false);

		Color color = new Color(108, 108, 108);
		tfList[0].setForeground(color);
		tfList[1].setForeground(color);
		tfList[2].setForeground(color);
		tfList[4].setForeground(color);
		rate.setForeground(color);

		this.tfList[3].getDocument().addDocumentListener(new DocumentListener() {
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
		model = new BillDetailModelData();
		table = new JTable(model);

		table.setDefaultRenderer(Object.class, new Renderer());
		model.setColumnWidth(table);

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					selectedRowIndex = table.getSelectedRow();
					billId = selectedRow.getBillId();
					setSelectedDetailModel();
				}
				displayDetailToTextField();
				getAllSelectedRow(table);
			}
		});

		add(tbPanel);
		tbPanel.setBounds(0, 290, SubFrame.SUBFRAME_W, 500);
		tbPanel.setLayout(null);

		/*
		 * Scroll pane
		 */
		pane = new JScrollPane(table);
		pane.setBounds(20, 20, SubFrame.SUBFRAME_W - 70, 240);

		tbPanel.add(pane);

		/*
		 * Button
		 */
		btnPanel.setBounds(320, 60 + 6, 500, 230);
		btnPanel.setLayout(null);
		add(btnPanel);

		addBtn = new AddButton(20, 20, 120, 20);
		addBtn.setNameBtn("Create");
		btnPanel.add(addBtn);
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addButtonHandle();
			}
		});

		saveBtn = new SaveButton(20, 50, 120, 20);
		btnPanel.add(saveBtn);
//		saveBtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				saveButtonHandle();
//			}
//		});

		resetBtn = new ResetButton(20, 80, 120, 20);
		btnPanel.add(resetBtn);
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetButtonHandle();
			}
		});

		pdfBtn = new ResetButton(20, 110, 120, 20);
		pdfBtn.setNameBtn("Export PDF");
		btnPanel.add(pdfBtn);
		pdfBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exportPDFHandle();
			}
		});

//		deleteBtn = new DeleteButton(20, 110, 120, 20);
//		btnPanel.add(deleteBtn);
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
		if (entryTable.getRowCount() > 0) {
			if (entryTable.getSelectedRowCount() > 0) {
				int selectedRow[] = entryTable.getSelectedRows();
				idSelectedRowList.clear();
				for (int i : selectedRow) {
					Long billid = (Long) entryTable.getValueAt(i, 0);
					Long skuid = (Long) entryTable.getValueAt(i, 1);
					idSelectedRowList.put(billid, skuid);
				}
			}
		}
	}

	/*
	 * Reset button handle
	 */
	public void resetButtonHandle() {
		for (int i = 1; i < this.tfList.length; i++) {
			this.tfList[i].setText("");
		}
	}

	/*
	 * 
	 */
	public void addButtonHandle() {
		this.resetButtonHandle();
	}

	/*
	 * 
	 */
	public void deleteButtonHandle() {
		if (selectedRowIndex >= 0) {
			int click = JOptionPane.showConfirmDialog(null, "Are you sure to delete this sku?");
			if (click == JOptionPane.YES_OPTION) {
				service.delete(idSelectedRowList);
				model.loadData(this.table, this.billId);
				loadDataByBillId(this.selectedRow.getBillId());
			}
		} else {
			JOptionPane.showMessageDialog(null, "You have not selected a value to delete!");
		}

	}

	/*
	 * Save button handle
	 */
	public void saveButtonHandle() {
		Long billId = -1L;
		if (!tfList[0].getText().isBlank()) {
			billId = Long.parseLong(tfList[0].getText());
		}

		Long skuId = -1L;
		if (!tfList[1].getText().isBlank()) {
			skuId = Long.parseLong(tfList[1].getText());
		}
		Long discountId = -1L;
		if (!tfList[2].getText().isBlank()) {
			discountId = Long.parseLong(tfList[2].getText());
		}

		int quantity = 0;
		if (!tfList[3].getText().isBlank()) {
			try {
				quantity = Integer.parseInt(tfList[3].getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				tfList[3].requestFocus();
				return;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Quantity must be not null!");
			tfList[3].requestFocus();
			return;
		}

		if (discountId < 0) {
			discountId = 0L;
		}
		String subTotal = tfList[4].getText();

		BillDetailModel oldModel;
		if (this.service.findOne(billId, skuId) == null) {
			updateTotalBillAfterAdd(billId, subTotal);
			service.save(new BillDetailModel(billId, skuId, discountId, quantity, subTotal));
		} else {
			oldModel = service.findOne(billId, skuId);
			updateTotalBillAfterUpdate(oldModel, subTotal);
			service.update(new BillDetailModel(billId, skuId, discountId, quantity, subTotal));
		}

		this.model.loadData(this.table, this.billId);
		getGeneratedKeys(skuId);

		table.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
		setSelectedDetailModel();
		displayDetailToTextField();
		JOptionPane.showMessageDialog(null, "Save successfully");

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
	public void loadDataByBillId(Long billId) {
		if (service.findListByBillId(billId) != null) {
			model.loadData(this.table, billId);
			this.billId = billId;
			this.selectedRowIndex = 0;
			this.setSelectedDetailModel();
			this.displayDetailToTextField();
			this.table.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
		} else {
			this.model.setData(null);
		}

	}

	public void setSelectedDetailModel() {
		selectedRow.setBillId((Long) table.getModel().getValueAt(selectedRowIndex, 0));
		selectedRow.setSkuId((Long) table.getModel().getValueAt(selectedRowIndex, 1));
		selectedRow.setDiscountId((Long) table.getModel().getValueAt(selectedRowIndex, 2));
		selectedRow.setQuantity((Integer) table.getModel().getValueAt(selectedRowIndex, 3));
		selectedRow.setSubTotal((String) table.getModel().getValueAt(selectedRowIndex, 4));
	}

	public void displayDetailToTextField() {
		tfList[0].setText(selectedRow.getBillId().toString());
		tfList[1].setText(selectedRow.getSkuId().toString());
		tfList[2].setText(selectedRow.getDiscountId().toString());
		tfList[3].setText(String.valueOf(selectedRow.getQuantity()));
		tfList[4].setText(selectedRow.getSubTotal());
		rate.setText(String.valueOf(getRateDiscount(selectedRow.getDiscountId(), selectedRow.getSkuId())));
	}

	public void moreSkuHandle() {
		SkuPanel sku = new SkuPanel();
		sku.loadDataByStatus("Active");
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
		subFrame = new SubFrame(sku);
		subFrame.setVisible(true);
	}

	public void doubleClickSkuHandle(Object id) {
		SkuService service = new SkuService();
		SkuModel model = service.findOne((Long) id);
		tfList[1].setText(model.getId().toString());

		DiscountDetailModel discountAvai = this.findDiscountBySkuId(model.getId());
		if (discountAvai != null) {
			tfList[2].setText(String.valueOf(discountAvai.getDiscountId()));
			rate.setText(String.valueOf(getRateDiscount(discountAvai.getDiscountId(), model.getId())));
			if (!tfList[3].getText().isBlank()) {
				quantityChangeHandle(tfList[3].getText());

			}
		} else {
			tfList[2].setText(String.valueOf(""));

		}
		subFrame.dispose();
	}

	public DiscountDetailModel findDiscountBySkuId(Long skuId) {
		DiscountDetailService service = new DiscountDetailService();
		List<DiscountDetailModel> list = service.findAll();

		DiscountDetailModel result = null;
		if (list != null) {
			for (DiscountDetailModel e : list) {
				if (e.getSkuId().toString().equals(String.valueOf(skuId)) && e.getStatus().equals("Active")) {
					result = e;
				}
			}
		}
		return result;
	}

	public void quantityChangeHandle(String qty) {
		if (qty.isBlank()) {
			tfList[4].setText("");
			return;
		}
		int quantity = Integer.parseInt(qty);
		Long skuId = -1L;
		if (!tfList[1].getText().isBlank()) {
			skuId = Long.parseLong(this.tfList[1].getText());
		} else {
			return;
		}

		SkuService skuService = new SkuService();
		int skuAvailable = 0;
		SkuModel sku;
		int subTotal = 0;

		if (skuId > 0) {
			sku = skuService.findOne(skuId);
			skuAvailable = sku.getQuantity();

			if (quantity > skuAvailable || quantity < 0) {
				JOptionPane.showMessageDialog(null, "Quantity is invalid!");
				tfList[3].requestFocus();
				return;
			} else {
				subTotal = Integer.parseInt(sku.getPrice()) * quantity;
				int discountPrice = 0;
				if (!rate.getText().isBlank()) {
					discountPrice = Integer.parseInt(rate.getText());
				}
				subTotal = subTotal - discountPrice;
			}
		}

		tfList[4].setText(String.valueOf(subTotal));
	}

	/*
	 * 
	 */
	public void updateTotalBillAfterAdd(Long billId, String subTotal) {
		BillService service = new BillService();
		BillModel model = service.findOne(billId);
		if (model != null) {
			int temp = Integer.parseInt(model.getTotal());
			int sub = Integer.parseInt(subTotal);
			int total = temp + sub;
			model.setTotal(String.valueOf(total));
		}
		service.update(model);
	}

	public void updateTotalBillAfterUpdate(BillDetailModel old, String subTotal) {
		BillService service = new BillService();
		BillModel model = service.findOne(old.getBillId());
		if (model != null) {
			int temp = Integer.parseInt(model.getTotal());
			int sub = Integer.parseInt(subTotal);

			int total = temp + sub - Integer.parseInt(old.getSubTotal());
			System.out.println(total);
			model.setTotal(String.valueOf(total));
		}
		service.update(model);
	}

	public int getRateDiscount(Long discountId, Long skuId) {
		DiscountDetailService service = new DiscountDetailService();
		DiscountDetailModel model = service.findOne(discountId, skuId);
		if (model == null) {
			return 0;
		}
		return model.getRate();
	}
	
	public void exportPDFHandle() {
		PDFExporter.export(this.billId);
		if (Desktop.isDesktopSupported()) {
			try {
				File myFile = new File(PDFExporter.PDF_PATH);
				Desktop.getDesktop().open(myFile);
			} catch (IOException ex) {
				// no application registered for PDFs
			}

		}
	}

}
