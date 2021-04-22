package xxminhmie.sgu.javagui.gui.panel.sub;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

import xxminhmie.sgu.javagui.gui.common.AddButton;
import xxminhmie.sgu.javagui.gui.common.DeleteButton;
import xxminhmie.sgu.javagui.gui.common.ResetButton;
import xxminhmie.sgu.javagui.gui.modeltable.DiscountDetailModelData;
import xxminhmie.sgu.javagui.gui.modeltable.PODetailModelData;
import xxminhmie.sgu.javagui.gui.panel.AbstractPanel;
import xxminhmie.sgu.javagui.gui.panel.Renderer;
import xxminhmie.sgu.javagui.model.DiscountDetailModel;
import xxminhmie.sgu.javagui.model.PODetailModel;
import xxminhmie.sgu.javagui.service.impl.DiscountDetailService;
import xxminhmie.sgu.javagui.service.impl.PODetailService;

public class DiscountDetailPanel extends JPanel {
	DiscountDetailService service = new DiscountDetailService();

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

	/*
	 * Table
	 */
	JPanel tbPanel = new JPanel();
	JScrollPane pane;
	JTable table;
	DiscountDetailModelData model;

	DiscountDetailModel selectedRow = new DiscountDetailModel();
	java.util.List<Long> idSelectedRowList = new java.util.ArrayList<Long>();// Contains list of customer's ID to
																				// delete
	int selectedRowIndex;

	Long discountId;

	/*
	 * Button
	 */
	JPanel btnPanel = new JPanel();
	AddButton addBtn;
	ResetButton resetBtn;
	DeleteButton deleteBtn;

	public DiscountDetailPanel(Long discountId) {
		this.discountId = discountId;

		setBackground(AbstractPanel.PanelBg);
		setPreferredSize(new Dimension(AbstractPanel.PanelWidth, AbstractPanel.PanelHeight));
		setLayout(null);
		init();

	}

	protected void init() {
		/*
		 * Main label
		 */
		mainLabel = new JLabel("Discount Details Manager - Discount ID: " + this.discountId);
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

		String[] infoName = { "Discount ID: ", "SKU ID: ", "Rate:  ", "Status: "};

		int x = 20;
		int y = 20;
		// this for loop initialize label and text fiel}, set position for both
		for (int i = 0; i < tfList.length; i++) {
			JLabel label = new JLabel(infoName[i]);
			label.setBounds(x, y, 100, 30);
			label.setAlignmentX(RIGHT_ALIGNMENT);
			tfPanel.add(label);
			tfList[i] = new JTextField();
			tfList[i].setText("");
			tfList[i].setBounds(x + 100, y + 5, 160, 20);
			tfPanel.add(this.tfList[i]);
			y += 30;

		}

		/** set read - only for ID text field **/
		tfList[0].setEditable(false);
		tfList[1].setEditable(false);
		
		tfList[0].setForeground(new Color(108, 108, 108));

		/*
		 * Table
		 */
		model = new DiscountDetailModelData();
		table = new JTable(model);
		
		table.setDefaultRenderer(Object.class, new Renderer());
		model.setColumnWidth(table);

		this.table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					selectedRowIndex = table.getSelectedRow();
					discountId = selectedRow.getDiscountId();
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
		btnPanel.setBounds(290, 80, 500, 230);
		btnPanel.setLayout(null);
		add(btnPanel);

		addBtn = new AddButton(20, 20);
		btnPanel.add(addBtn);

		resetBtn = new ResetButton(20, 50);
		btnPanel.add(resetBtn);

		deleteBtn = new DeleteButton(20, 80);
		btnPanel.add(deleteBtn);

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
					Long id = (Long) entryTable.getValueAt(i, 0);
					idSelectedRowList.add(id);
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
		for (int i = 0; i < this.tfList.length; i++) {
			this.tfList[i].setText("");
		}
	}

	/*
	 * Save button handle
	 */
	public void saveButtonHandle() {
		Long discountId = -1L;
		if (!tfList[0].getText().isBlank()) {
			discountId = Long.parseLong(tfList[0].getText());
		}

		Long skuId = -1L;
		if (!tfList[1].getText().isBlank()) {
			discountId = Long.parseLong(tfList[1].getText());
		}

		int rate = 0;
		if (!tfList[2].getText().isBlank()) {
			try {
				rate = Integer.parseInt(tfList[2].getText());
			}catch(NumberFormatException e) {
				
			}
		} else {
			JOptionPane.showMessageDialog(null, "Product's color must be not null!");
			tfList[2].requestFocus();
			return;
		}

		String status = "";
		if (!tfList[3].getText().isBlank()) {
			status = tfList[3].getText();
		} else {
			JOptionPane.showMessageDialog(null, "Product's size must be not null!");
			tfList[3].requestFocus();
			return;
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
	 * This method will be called by openPoDetailFrame method from PO Panel
	 */
	public void loadDataByDiscountId(Long discountId) {
		if (this.service.findListByDiscountId(discountId) != null) {
			this.model.loadData(this.table, discountId);
			this.discountId = discountId;
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

}
