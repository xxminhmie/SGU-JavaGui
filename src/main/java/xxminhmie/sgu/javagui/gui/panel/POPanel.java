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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	JButton[] more = new JButton[1];
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
	public AddButton confirmBtn;

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
	 * 
	 */
	static Long STAFF_CURRENT;
	JFrame staffFrame;
	PODetailPanel detail;

	/*
	 * Constructor
	 */
	public POPanel() {
		setBackground(AbstractPanel.PanelBg);
		setPreferredSize(new Dimension(AbstractPanel.PanelWidth, AbstractPanel.PanelHeight));
		setLayout(new BorderLayout());

		mainLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		add(mainLabel, BorderLayout.NORTH);

		/*
		 * Main Panel
		 */
		mainPanel.setLayout(null);
		add(mainPanel, BorderLayout.CENTER);

		/*
		 * PO Panel
		 */
		panel.setBounds(0, 0, AbstractPanel.PanelWidth, 700);
		panel.setLayout(null);
		mainPanel.add(panel);

		/*
		 * Search
		 */
		searchPanel = new JPanel();
		searchPanel.setBounds(0, 0, 300, 40);
		searchPanel.setLayout(null);
		panel.add(searchPanel);

		search = new JTextField("Search...");
		search.setForeground(new Color(144, 144, 144));
		search.setBounds(10, 10, 300, 30);

		/*
		 * Search's listener
		 */
		search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				search.selectAll();
				search.setForeground(Color.BLACK);
			}
		});
		searchPanel.add(search);

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
		tfPanel = new JPanel();
		tfPanel.setBounds(0, 30, 350, 240);
		tfPanel.setOpaque(true);
		tfPanel.setLayout(null);
		panel.add(tfPanel);

		String[] infoName = { "ID: ", "StaffID: ", "Created Date: ", "Total", "Status: " };
		int x = 10;
		int y = 20;
		// this for loop initialize label and text field, set position for both
		for (int i = 0; i < this.tfList.length; i++) {
			JLabel label = new JLabel(infoName[i]);
			this.tfPanel.add(label);
			label.setBounds(x, y, 100, 30);
			tfList[i] = new JTextField();
			tfList[i].setBounds(x + 90, y + 5, 190, 20);
			tfPanel.add(tfList[i]);
			y += 30;
		}
		/*
		 * more staff button
		 */
		int moreX = 10 + 80 + 200;
		int moreY = 20 + 30 + 5 + 30;
		more[0] = new JButton("...");
		more[0].setBounds(moreX, moreY, 40, 20);
		panel.add(more[0]);

		/*
		 * set read - only for ID text field
		 */
		tfList[0].setEditable(false);
		tfList[1].setEditable(false);
		tfList[2].setEditable(false);
		tfList[3].setEditable(false);
		tfList[4].setEditable(false);


		Color color = new Color(108, 108, 108);
		tfList[0].setForeground(color);
		tfList[1].setForeground(color);
		tfList[2].setForeground(color);
		tfList[3].setForeground(color);

		panel.add(this.tfPanel);

		/*
		 * more button add listener
		 */
		more[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moreBtnStaffHandle();
			}
		});

		/*
		 * Button panel
		 */
		btnPanel.setBounds(350, 60, 200, 200);
		btnPanel.setLayout(null);
		panel.add(btnPanel);

		addBtn = new AddButton(0, 0, 120, 20);
		addBtn.setNameBtn("Create New");

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

		deleteBtn = new DeleteButton(0, 130, 120, 20);
		deleteBtn.setNameBtn("Cancel");
		btnPanel.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteButtonHandle();
			}
		});
		confirmBtn = new AddButton(0, 160, 120, 20);
		btnPanel.add(confirmBtn);
		confirmBtn.setNameBtn("Confirm");
		confirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmButtonHandle();// po panel class
				detail.updateQtySkuAfterConfirm();
			}

		});
		/*
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
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			/* GET SELECTED VALUE TO DISPLAY ON TEXT FIELD **/
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int rowIndex = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (rowIndex >= 0 && col >= 0) {
					selectedRowIndex = rowIndex;
					setSelectedPOModel();
				}
				displayPOToTextField();
				getAllSelectedRow(table);
//				findAllDetailByProductId(selectedRow.getId());
			}

			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					menuItemDetailHandle();
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
	 * reset button handle
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

		Long staffId = -1L;
		if (!tfList[1].getText().isBlank()) {
			staffId = Long.valueOf(tfList[1].getText());
		} else {
			tfList[1].requestFocus();
			return;
		}

		String total = tfList[3].getText();

		java.sql.Date createdDate = GetCurrentDate.getDate();

		String status = null;
		if (!tfList[4].getText().isBlank()) {
			status = tfList[4].getText();
		}

		Long savedId;
		if (id < 0) {
			savedId = poService.save(new POModel(staffId, createdDate, total, status));
		} else {
			POModel model = poService.update(new POModel(id, staffId, createdDate, total, status));
			savedId = model.getId();
		}

		model.loadData(table);
		getGeneratedKeys(savedId);
		table.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
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
		tfList[2].setText(this.getCurrentDate());
		tfList[0].setText("");
		// TODO: nhan vien hien hanh id
//		tfList[1].setText();
	}

	/*
	 * Delete button handle
	 */
	public void deleteButtonHandle() {
		if (selectedRowIndex >= 0) {
			int click = JOptionPane.showConfirmDialog(null,
					"Are you sure for cancel this purchase order?");
			if (click == JOptionPane.YES_OPTION) {
				POModel model = this.selectedRow;
				model.setStatus("Canceled");
				poService.update(model);
				this.model.loadData(table);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You have not selected a value to cancel!");
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
		tfList[4].setText(selectedRow.getStatus());

		
	}

	public void menuItemDetailHandle() {
		openDetailFrame(this.selectedRow.getId());
	}

	/*
	 * Open SKU sub jframe
	 */
	public void openDetailFrame(Long poId) {
		detail = new PODetailPanel(poId);
		if (this.selectedRow.getStatus().equals("Confirmed")) {
			detail.tfList[3].setEditable(false);
			detail.btnPanel.setVisible(false);
		} else {

			detail.saveBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					detail.saveButtonHandle();
					model.loadData(table);
				}
			});
			detail.deleteBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					detail.deleteButtonHandle();
					model.loadData(table);
				}
			});
//			detail.confirmBtn.addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					confirmButtonHandle();// po panel class
//					detail.updateQtySkuAfterConfirm();
//				}
//			});
		}
		JFrame subFrame = new SubFrame(detail);
		/*
		 * If poId > 0 --> detail else add new PO --> null
		 */
		if (poId > 0) {
			detail.loadDataByPOId(poId);
			detail.tfList[0].setText(String.valueOf(poId));
		}
		subFrame.setVisible(true);
	}

	/*
	 * More staff button handle
	 */
	public void moreBtnStaffHandle() {
		Long staffId = -1L;
		if (this.tfList[1].getText().isBlank() == false) {
			staffId = Long.parseLong(tfList[1].getText());
		}

		StaffPanel staff = new StaffPanel();
		staff.setSelectedStaffModel();
		staff.displayStaffToTextField();
		staff.table.addMouseListener(new java.awt.event.MouseAdapter() {
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

		staffFrame = new SubFrame(staff);
		staffFrame.setVisible(true);
	}

	public void doubleClickStaffHandle(Object id) {
		STAFF_CURRENT = (Long) id;
		tfList[1].setText((String.valueOf(id)));
		staffFrame.dispose();
	}

	public String getCurrentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);

	}

	public void confirmButtonHandle() {
		if (selectedRowIndex > 0) {
			int click = JOptionPane.showConfirmDialog(null, "Are you sure to confirm this purchase order?");
			if (click == JOptionPane.YES_OPTION) {
				POModel updateModel = this.selectedRow;
				updateModel.setStatus("Confirmed");
				this.poService.update(updateModel);
				this.model.loadData(table);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You have not selected a value to confirm!");
		}
	}

}
