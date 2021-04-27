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
import java.sql.Date;
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

import com.toedter.calendar.JDateChooser;

import xxminhmie.sgu.javagui.gui.common.AddButton;
import xxminhmie.sgu.javagui.gui.common.BetweenTwoDate;
import xxminhmie.sgu.javagui.gui.common.DeleteButton;
import xxminhmie.sgu.javagui.gui.common.GetCurrentDate;
import xxminhmie.sgu.javagui.gui.common.ResetButton;
import xxminhmie.sgu.javagui.gui.common.SaveButton;
import xxminhmie.sgu.javagui.gui.modeltable.DiscountModelData;
import xxminhmie.sgu.javagui.gui.panel.sub.DiscountDetailPanel;
import xxminhmie.sgu.javagui.gui.panel.sub.SubFrame;
import xxminhmie.sgu.javagui.gui.validator.WhiteSpaceValidator;
import xxminhmie.sgu.javagui.model.DiscountDetailModel;
import xxminhmie.sgu.javagui.model.DiscountModel;
import xxminhmie.sgu.javagui.service.impl.DiscountDetailService;
import xxminhmie.sgu.javagui.service.impl.DiscountService;

public class DiscountPanel extends JPanel {

	DiscountService service = new DiscountService();
	JLabel mainLabel = new JLabel("Discount Manager");
	JPanel mainPanel = new JPanel();
	JPanel panel = new JPanel();
	/*
	 * Text field
	 */
	JPanel tfPanel;
	JTextField[] tfList;
	JDateChooser startDate;
	JDateChooser endDate;
	JTextArea text;
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
	public JTable table;
	DiscountModelData model;
	/*
	 * Handle clicking on table
	 */
	DiscountModel selectedRow = new DiscountModel();// Product is being selected from Table
	java.util.List<Long> idSelectedRowList = new java.util.ArrayList<Long>();// Contains list of product's ID to
	int selectedRowIndex = -1;

	/*
	 * 
	 */

	DiscountDetailPanel detail;

	/*
	 * Constructor
	 */
	public DiscountPanel() {
		setBackground(AbstractPanel.PanelBg);
		setPreferredSize(new Dimension(AbstractPanel.PanelWidth, AbstractPanel.PanelHeight));
		setLayout(new BorderLayout());

		mainLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		add(mainLabel, BorderLayout.NORTH);

		/*
		 * Check date discount out
		 */
		this.checkDateDiscount();

		/*
		 * Main Panel
		 */
		mainPanel.setLayout(null);
		add(mainPanel, BorderLayout.CENTER);

		/*
		 * Product Panel
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
		searchPanel.add(search);

		/*
		 * Search's listener
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
		tfPanel = new JPanel();
		tfPanel.setBounds(0, 30, 310, 260);
		tfPanel.setOpaque(true);
		tfPanel.setLayout(null);
		panel.add(tfPanel);

		String[] infoName = { "ID: ", "Name: ", "Start Date: ", "End Date:", "Description: ", "Status: " };
		this.tfList = new JTextField[6];
		int x = 10;
		int y = 20;
		// this for loop initialize label and text field, set position for both
		for (int i = 0; i < tfList.length; i++) {
			JLabel label = new JLabel(infoName[i]);
			tfPanel.add(label);
			label.setBounds(x, y, 100, 30);

			switch (i) {
			case 2: {
				label.setBounds(x, y, 100, 30);
				startDate = new JDateChooser();
				startDate.setBounds(x + 80, 80, 200, 30);
				startDate.getJCalendar().setBounds(0, 0, 600, 200);
				startDate.setDateFormatString("yyyy-MM-dd");
				tfPanel.add(startDate);
				break;
			}
			case 3: {
				label.setBounds(x, y, 100, 30);
				endDate = new JDateChooser();
				endDate.setBounds(x + 80, 110, 200, 30);
				endDate.getJCalendar().setBounds(0, 0, 600, 200);
				endDate.setDateFormatString("yyyy-MM-dd");
				tfPanel.add(endDate);
				break;
			}
			case 4: {
				label.setBounds(x, y, 100, 30);
				text = new JTextArea();
				text.setBounds(x + 80 + 4, y + 5, 190, 70);
				text.setColumns(20);
				text.setLineWrap(true);
				text.setRows(5);
				text.setWrapStyleWord(true);
				tfPanel.add(text);
				break;
			}
			case 5: {
				label.setBounds(x, y + 50, 100, 30);
				tfList[i] = new JTextField();
				tfList[i].setBounds(x + 80, y + 5 + 50, 200, 20);
				tfPanel.add(tfList[i]);
				break;
			}
			default: {
				tfList[i] = new JTextField();
				tfList[i].setBounds(x + 80, y + 5, 200, 20);
				tfPanel.add(tfList[i]);
				break;
			}
			}
			y += 30;
		}
		/*
		 * set read - only for ID text field
		 */
		tfList[0].setEditable(false);
		tfList[5].setEditable(false);

		tfList[0].setForeground(new Color(108, 108, 108));
		tfList[5].setForeground(new Color(108, 108, 108));

		panel.add(tfPanel);

		/*
		 * Button panel
		 */
		btnPanel.setBounds(320, 60, 200, 200);
		btnPanel.setLayout(null);
		panel.add(btnPanel);

		addBtn = new AddButton(0, 10, 120, 20);
		addBtn.setNameBtn("New Discount");
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

		addSkuBtn = new AddButton(0, 100, 120, 20);
		addSkuBtn.setNameBtn("Details");
		btnPanel.add(addSkuBtn);
		addSkuBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				detailsButtonHandle();
			}
		});

		deleteBtn = new DeleteButton(0, 130, 120, 20);
		btnPanel.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteButtonHandle();
			}
		});

		/*
		 * Table panel
		 */
		tbPanel = new JPanel();
		tbPanel.setBounds(10, 280, 1000, 360);
		tbPanel.setLayout(null);

		/*
		 * Table
		 */
		model = new DiscountModelData();
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
					setSelectedDiscountModel();
				}
				/** DISPLAY TO TEXT FIELD **/
				displayDiscountToTextField();
				/** **/
				getAllSelectedRow(table);
			}

			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					tableDoubleClickedHandle();
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
			switch (i) {
			case 2: {
				startDate.setDate(null);
				break;
			}
			case 3: {
				endDate.setDate(null);
				break;
			}
			case 4: {
				text.setText("");
				break;

			}
			default: {
				tfList[i].setText("");
				break;

			}
			}
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

		String name = "";
		if (!tfList[1].getText().isBlank()) {
			name = tfList[1].getText();
			name = WhiteSpaceValidator.validate(name);
		} else {
			JOptionPane.showMessageDialog(null, "Product's name must be not null!");
			tfList[1].requestFocus();
			return;
		}

		Date startDate = null;
		if (this.startDate.getDate().getTime() != 0) {
			try {
				startDate = new Date(this.startDate.getDate().getTime());
			} catch (java.lang.NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Invalid start date! Please insert or choose again!");
				return;
			}
		}

		Date endDate = null;
		if (this.endDate.getDate().getTime() != 0) {
			try {
				endDate = new Date(this.endDate.getDate().getTime());
			} catch (java.lang.NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Invalid end date! Please insert or choose again!");
				return;
			}
		}

		/*
		 * check data
		 */

		long between = BetweenTwoDate.betweenTwoDate(startDate.toString(), endDate.toString());
		if (between < 0) {
			JOptionPane.showMessageDialog(null, "Invalid date! End date must be after start date!");
			this.endDate.requestFocus();
			return;
		}

		String des = "";
		if (!text.getText().isBlank()) {
			des = WhiteSpaceValidator.validate(text.getText());
		}

		String status = "";
		if (!tfList[5].getText().isBlank()) {
			status = tfList[5].getText();
			WhiteSpaceValidator.validate(status);
		}
		int click = JOptionPane.showConfirmDialog(null, "Are you sure to save this changes?");
		if (click == JOptionPane.YES_OPTION) {

			Long savedId;
			if (id < 0) {
				DiscountModel model = service.save(new DiscountModel(name, startDate, endDate, des, status));
				savedId = model.getId();
			} else {
				DiscountModel model = service.update(new DiscountModel(id, name, startDate, endDate, des, status));
				savedId = model.getId();
			}

			this.model.loadData(this.table);
			getGeneratedKeys(savedId);

			table.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
			setSelectedDiscountModel();
			displayDiscountToTextField();
		}
	}

	public void addButtonHandle() {
		tfList[0].setText("");
		tfList[1].setText("");
		startDate.setDate(GetCurrentDate.getDate());
		endDate.setDate(null);
		tfList[5].setText("Active");
		tfList[1].requestFocus();
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
	 * 
	 */
	public void setSelectedDiscountModel() {
		selectedRow.setId((Long) table.getModel().getValueAt(selectedRowIndex, 0));
		selectedRow.setName((String) table.getModel().getValueAt(selectedRowIndex, 1));
		selectedRow.setStartDate((java.sql.Date) table.getModel().getValueAt(selectedRowIndex, 2));
		selectedRow.setEndDate((java.sql.Date) table.getModel().getValueAt(selectedRowIndex, 3));
		selectedRow.setDescription((String) table.getModel().getValueAt(selectedRowIndex, 4));
		selectedRow.setStatus((String) table.getModel().getValueAt(selectedRowIndex, 5));
	}

	public void displayDiscountToTextField() {
		tfList[0].setText(selectedRow.getId().toString());
		tfList[1].setText(selectedRow.getName());
		startDate.setDate(selectedRow.getStartDate());
		endDate.setDate(selectedRow.getEndDate());
		text.setText(selectedRow.getDescription());
		tfList[5].setText(selectedRow.getStatus());
	}

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

	/*
	 * Open discount detail
	 */
	public void openDetailFrame(Long discountId) {
		detail = new DiscountDetailPanel(discountId);
		JFrame subFrame = new SubFrame(detail);

		if (discountId > 0) {
			detail.loadDataByDiscountId(discountId);
			detail.tfList[0].setText(String.valueOf(discountId));
		}

		if (this.selectedRow.getStatus().equals("Active") == false) {
			detail.btnPanel.setVisible(false);
			detail.more[0].setVisible(false);
		}
		subFrame.setVisible(true);

	}

	public void tableDoubleClickedHandle() {
		openDetailFrame(selectedRow.getId());
	}

	public void checkDateDiscount() {
		List<DiscountModel> list = service.findAll();
		for (DiscountModel e : list) {
			String currentDate = GetCurrentDate.getCurrentDate();
			long between = BetweenTwoDate.betweenTwoDate(currentDate, e.getEndDate().toString());
			if (between < 0) {
				DiscountDetailService detailService = new DiscountDetailService();
				detailService.updateStatus(e.getId(), "Expired");

				DiscountModel newModel = this.service.findOne(e.getId());
				newModel.setStatus("Expired");
				this.service.update(newModel);
			}
		}
	}

	public void detailsButtonHandle() {
		if (this.selectedRowIndex < 0) {
			JOptionPane.showMessageDialog(null, "You have not selected a row to handle!");
		} else {
			openDetailFrame(this.selectedRow.getId());
		}
	}

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
	
	public void loadDataByStatus(String status) {
		this.model.loadData(table, status);
	}
}
