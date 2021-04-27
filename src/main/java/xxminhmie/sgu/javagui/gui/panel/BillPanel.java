package xxminhmie.sgu.javagui.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

import xxminhmie.sgu.javagui.gui.LogInGUI;
import xxminhmie.sgu.javagui.gui.common.AddButton;
import xxminhmie.sgu.javagui.gui.common.DeleteButton;
import xxminhmie.sgu.javagui.gui.common.GetCurrentDate;
import xxminhmie.sgu.javagui.gui.common.ResetButton;
import xxminhmie.sgu.javagui.gui.common.SaveButton;
import xxminhmie.sgu.javagui.gui.modeltable.BillModelData;
import xxminhmie.sgu.javagui.gui.panel.sub.BillDetailPanel;
import xxminhmie.sgu.javagui.gui.panel.sub.SkuPanel;
import xxminhmie.sgu.javagui.gui.panel.sub.SubFrame;
import xxminhmie.sgu.javagui.model.BillModel;
import xxminhmie.sgu.javagui.service.impl.BillService;

public class BillPanel extends JPanel {
	BillService service = new BillService();
	JLabel mainLabel = new JLabel("Bill Manager");
	JPanel mainPanel = new JPanel();
	JPanel panel = new JPanel();
	/*
	 * Text field
	 */
	JPanel tfPanel;
	JTextField[] tfList;
	JButton more;
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
	BillModelData model;
	/*
	 * Handle clicking on table
	 */
	BillModel selectedRow = new BillModel();
	java.util.List<Long> idSelectedRowList = new java.util.ArrayList<Long>();

	int selectedRowIndex = -1;
	SubFrame frame;

	/*
	 * Constructor
	 */
	public BillPanel() {
		setBackground(AbstractPanel.PanelBg);
		setPreferredSize(new Dimension(AbstractPanel.PanelWidth, AbstractPanel.PanelHeight));
		setLayout(new BorderLayout());

		mainLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		add(mainLabel, BorderLayout.NORTH);

		/*
		 * Main Panel
		 */
		mainPanel.setLayout(null);
		add(this.mainPanel, BorderLayout.CENTER);

		/*
		 * Product Panel
		 */
		panel.setBounds(0, 0, AbstractPanel.PanelWidth, 700);
		panel.setLayout(null);
		mainPanel.add(this.panel);

		/*
		 * Search
		 */
		searchPanel = new JPanel();
		searchPanel.setBounds(0, 0, 300, 40);
		searchPanel.setLayout(null);
		panel.add(searchPanel);

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
		tfPanel.setBounds(0, 30, 330, 240);
		tfPanel.setOpaque(true);
		tfPanel.setLayout(null);
		this.panel.add(this.tfPanel);

		String[] infoName = { "BillID: ", "StaffID: ", "CustomerID: ", "Created Date: ", "Total: " };
		this.tfList = new JTextField[5];
		int x = 10;
		int y = 20;
		Color color = new Color(108, 108, 108);

		// this for loop initialize label and text field, set position for both
		for (int i = 0; i < tfList.length; i++) {
			JLabel label = new JLabel(infoName[i]);
			tfPanel.add(label);
			label.setBounds(x, y, 100, 30);
			tfList[i] = new JTextField();
			tfList[i].setBounds(x + 80, y + 5, 200, 20);
			tfPanel.add(tfList[i]);
			y += 30;
			/*
			 * set read - only for ID text field
			 */
			tfList[i].setEditable(false);
			tfList[i].setForeground(color);
		}

		more = new JButton("...");
		more.setBounds(x + 200 + 80, 20 + 30 + 30 + 6, 40, 20);
		tfPanel.add(more);
		more.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moreCustomerHandle();
			}
		});


		panel.add(tfPanel);

		/*
		 * Button panel
		 */
		btnPanel.setBounds(340, 50, 200, 200);
		btnPanel.setLayout(null);
		panel.add(btnPanel);

		addBtn = new AddButton(0, 10, 120, 20);
		addBtn.setNameBtn("Create");
		addBtn.setBorder(null);
		btnPanel.add(addBtn);
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addButtonHandle();
			}
		});

		saveBtn = new SaveButton(0, 40, 120, 20);
		btnPanel.add(saveBtn);
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveButtonHandle();
			}
		});

		resetBtn = new ResetButton(0, 70, 120, 20);
		btnPanel.add(resetBtn);
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetButtonHandle();
			}
		});

//		addSkuBtn = new AddButton(0, 100, 120, 20);
//		addSkuBtn.setNameBtn("Details");
//		btnPanel.add(addSkuBtn);
//		addSkuBtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				detailsButtonHandle();
//			}
//
//		});

//		deleteBtn = new DeleteButton(0, 130, 120, 20);
//		btnPanel.add(deleteBtn);
//		deleteBtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				deleteButtonHandle();
//			}
//		});
		/*
		 * Table panel
		 */
		tbPanel = new JPanel();
		tbPanel.setBounds(10, 260, 1000, 360);
		tbPanel.setLayout(null);

		/*
		 * Table
		 */
		model = new BillModelData();
		table = new JTable(model);
		table.setDefaultRenderer(Object.class, new Renderer());

		model.setColumnWidth(table);

		/*
		 * Table's listener
		 */
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int rowIndex = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (rowIndex >= 0 && col >= 0) {
					selectedRowIndex = rowIndex;
					setSelectedBillModel();
				}
				displayBillToTextField();
				getAllSelectedRow(table);
			}

			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					doubleClickBillHandle();
				}
			}
		});

		/*
		 * scroll pane
		 */
		pane = new JScrollPane(table);
		pane.setBounds(0, 20, 700, 360);

		tbPanel.add(pane);
		panel.add(tbPanel);

	}// END CONSTRUCTOR////////////////////////////////////

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
		Long id = -1L;
		if (!tfList[0].getText().isBlank()) {
			id = Long.parseLong(tfList[0].getText());
		}

		Long staffId = LogInGUI.ACCOUNT_LOGIN.getId();

		Long customerId = -1l;
		if (!tfList[2].getText().isBlank()) {
			customerId = Long.parseLong(tfList[2].getText());
		}

		java.sql.Date createdDate = GetCurrentDate.getDate();

		Long savedId;
		if (id < 0) {
			BillModel model = service.save(new BillModel(staffId, customerId, createdDate));
			savedId = model.getId();
		} else {
			service.update(new BillModel(id, staffId, customerId, createdDate));
			savedId = id;
		}

		this.model.loadData(this.table);
		getGeneratedKeys(savedId);
		table.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
	}

	/*
	 * 
	 */
	public void detailsButtonHandle() {
		if (this.selectedRowIndex < 0) {
			JOptionPane.showMessageDialog(null, "Please select one product row on table to view details!");
		} else {
//			SkuService service = new SkuService();
//			if(service.findByProductId(this.selectedRow.getId())==null) {
//				openSkuFrame(-1L);
//			}else {
			openBillDetailFrame(this.selectedRow.getId());
//			}
		}
	}

	/*
	 * add new product button handle
	 */
	public void addButtonHandle() {
		this.tfList[0].setText("");
		tfList[1].setText(LogInGUI.ACCOUNT_LOGIN.getId().toString());

		tfList[3].setText(GetCurrentDate.getDate().toString());
	}

	/*
	 * delete button handle
	 */
	public void deleteButtonHandle() {
		if (selectedRowIndex >= 0) {
			int click = JOptionPane.showConfirmDialog(null, "Are you sure to delete this discount?");
			if (click == JOptionPane.YES_OPTION) {
				service.delete(this.idSelectedRowList.toArray(new Long[this.idSelectedRowList.size()]));
				model.loadData(this.table);
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

	/*
	 * 
	 */
	public void openBillDetailFrame(Long productId) {
		SkuPanel skuPanel = new SkuPanel(productId);
		JFrame subFrame = new SubFrame(skuPanel);

		if (productId > 0) {
			skuPanel.loadDataByProductId(productId);
		}
		subFrame.setVisible(true);

	}

	/*
	 * 
	 */
	public void setSelectedBillModel() {
		selectedRow.setId((Long) table.getModel().getValueAt(selectedRowIndex, 0));
		selectedRow.setStaffId((Long) table.getModel().getValueAt(selectedRowIndex, 1));
		selectedRow.setCustomerId((Long) table.getModel().getValueAt(selectedRowIndex, 2));
		selectedRow.setCreatedDate((java.sql.Date) table.getModel().getValueAt(selectedRowIndex, 3));
		selectedRow.setTotal((String) table.getModel().getValueAt(selectedRowIndex, 4));
	}

	public void displayBillToTextField() {
		tfList[0].setText(String.valueOf(selectedRow.getId()));
		tfList[1].setText(String.valueOf(selectedRow.getStaffId()));
		tfList[2].setText(String.valueOf(selectedRow.getCustomerId()));
		tfList[3].setText(String.valueOf(selectedRow.getCreatedDate()));
		tfList[4].setText(selectedRow.getTotal());

	}

	public void doubleClickBillHandle() {
		openDetailFrame(selectedRow.getId());
	}

	public void openDetailFrame(Long billId) {
		BillDetailPanel detail = new BillDetailPanel(billId);

		JFrame subFrame = new SubFrame(detail);

		if (billId > 0) {
			detail.loadDataByBillId(billId);
			detail.tfList[0].setText(String.valueOf(billId));
		}
		detail.saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				detail.saveButtonHandle();
				model.loadData(table);
			}
		});

		subFrame.setVisible(true);
	}
	public void moreCustomerHandle() {
		Long customerId = -1L;
		if (this.tfList[1].getText().isBlank() == false) {
			customerId = Long.parseLong(tfList[1].getText());
		}

		CustomerPanel customer = new CustomerPanel();
		customer.setSelectedCustomerModel();
		customer.displayCustomerToTextField();
		customer.table.addMouseListener(new java.awt.event.MouseAdapter() {
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

		frame = new SubFrame(customer);
		frame.setVisible(true);
	}

	public void doubleClickStaffHandle(Object id) {
//		STAFF_CURRENT = (Long) id;
		tfList[2].setText((String.valueOf(id)));
		frame.dispose();
	}
}
