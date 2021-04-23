package xxminhmie.sgu.javagui.gui.panel.sub;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

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
import xxminhmie.sgu.javagui.gui.modeltable.DiscountDetailModelData;
import xxminhmie.sgu.javagui.gui.panel.AbstractPanel;
import xxminhmie.sgu.javagui.gui.panel.Renderer;
import xxminhmie.sgu.javagui.model.DiscountDetailModel;
import xxminhmie.sgu.javagui.model.DiscountModel;
import xxminhmie.sgu.javagui.model.PODetailModel;
import xxminhmie.sgu.javagui.model.SkuModel;
import xxminhmie.sgu.javagui.service.impl.DiscountDetailService;
import xxminhmie.sgu.javagui.service.impl.SkuService;

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
	public JTextField[] tfList = new JTextField[4];
	public JButton[] more = new JButton[1];

	/*
	 * Table
	 */
	JPanel tbPanel = new JPanel();
	JScrollPane pane;
	JTable table;
	DiscountDetailModelData model;

	DiscountDetailModel selectedRow = new DiscountDetailModel();
	java.util.Map<Long, Long> idSelectedRowList = new HashMap<Long, Long>();// (oiid, skuid)
	int selectedRowIndex;

	Long discountId;

	/*
	 * Button
	 */
	public JPanel btnPanel = new JPanel();
	AddButton addBtn;
	SaveButton saveBtn;
	ResetButton resetBtn;
	DeleteButton deleteBtn;

	/*
	 * 
	 */
	JFrame skuFrame;

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

		String[] infoName = { "Discount ID: ", "SKU ID: ", "Rate (%):  ", "Status: " };

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

		more[0] = new JButton("...");
		more[0].setBounds(20 + 100 + 160, 20 + 30 + 6, 40, 20);
		more[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moreSkuHandle();
			}
		});
		tfPanel.add(more[0]);

		/** set read - only for ID text field **/
		tfList[0].setEditable(false);
		tfList[1].setEditable(false);
		tfList[3].setEditable(false);

		Color color = new Color(108, 108, 108);
		tfList[0].setForeground(color);
		tfList[1].setForeground(color);
		tfList[3].setForeground(color);

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
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveButtonHandle();
			}
		});

		resetBtn = new ResetButton(20, 80, 120, 20);
		btnPanel.add(resetBtn);
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetButtonHandle();
			}
		});

		deleteBtn = new DeleteButton(20, 110, 120, 20);
		btnPanel.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteButtonHandle();
			}
		});

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
					Long discountid = (Long) entryTable.getValueAt(i, 0);
					Long skuid = (Long) entryTable.getValueAt(i, 1);
					idSelectedRowList.put(discountid, skuid);
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
		tfList[3].setText("Active");
	}

	/*
	 * 
	 */
	public void deleteButtonHandle() {
		if (selectedRowIndex >= 0) {
			int click = JOptionPane.showConfirmDialog(null, "Are you sure to delete this discount?");
			if (click == JOptionPane.YES_OPTION) {
				service.delete(idSelectedRowList);
//				model.loadData(this.table,this.discountId);
				loadDataByDiscountId(this.selectedRow.getDiscountId());
			}
		} else {
			JOptionPane.showMessageDialog(null, "You have not selected a value to delete!");
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
			skuId = Long.parseLong(tfList[1].getText());
		}

		int rate = 0;
		if (!tfList[2].getText().isBlank()) {
			try {
				rate = Integer.parseInt(tfList[2].getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				tfList[2].requestFocus();
				return;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Rate must be not null!");
			tfList[2].requestFocus();
			return;
		}

		if (rate > 100 || rate < 0) {
			JOptionPane.showMessageDialog(null, "Rate must be less than 100 and large than 0!");
			tfList[2].requestFocus();
			return;
		}

		String status = "Active";

		if (this.service.findOne(discountId, skuId) == null) {
			service.save(new DiscountDetailModel(discountId, skuId, rate, status));
		} else {
			service.update(new DiscountDetailModel(discountId, skuId, rate, status));

		}

		this.model.loadData(this.table, this.discountId);
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
	public void loadDataByDiscountId(Long discountId) {
		if (service.findListByDiscountId(discountId) != null) {
			model.loadData(this.table, discountId);
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
		selectedRow.setDiscountId((Long) table.getModel().getValueAt(selectedRowIndex, 0));
		selectedRow.setSkuId((Long) table.getModel().getValueAt(selectedRowIndex, 1));
		selectedRow.setRate((Integer) table.getModel().getValueAt(selectedRowIndex, 2));
		selectedRow.setStatus((String) table.getModel().getValueAt(selectedRowIndex, 3));
	}

	public void displayDetailToTextField() {
		tfList[0].setText(selectedRow.getDiscountId().toString());
		tfList[1].setText(selectedRow.getSkuId().toString());
		tfList[2].setText(String.valueOf(selectedRow.getRate()));
		tfList[3].setText(selectedRow.getStatus());
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
		skuFrame = new SubFrame(sku);
		skuFrame.setVisible(true);
	}

	public void doubleClickSkuHandle(Object id) {
		SkuService service = new SkuService();
		SkuModel model = service.findOne((Long) id);
		tfList[1].setText(model.getId().toString());

		skuFrame.dispose();
	}

}
