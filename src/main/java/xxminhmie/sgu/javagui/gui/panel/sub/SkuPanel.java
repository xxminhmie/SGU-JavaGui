package xxminhmie.sgu.javagui.gui.panel.sub;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
import xxminhmie.sgu.javagui.gui.common.MoneyFormat;
import xxminhmie.sgu.javagui.gui.common.ResetButton;
import xxminhmie.sgu.javagui.gui.common.SaveButton;
import xxminhmie.sgu.javagui.gui.modeltable.SkuModelData;
import xxminhmie.sgu.javagui.gui.panel.AbstractPanel;
import xxminhmie.sgu.javagui.gui.panel.Renderer;
import xxminhmie.sgu.javagui.image.MyImagePanel;
import xxminhmie.sgu.javagui.model.SkuModel;
import xxminhmie.sgu.javagui.service.impl.SkuService;

public class SkuPanel extends JPanel {
	SkuService skuService = new SkuService();
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
	JTextField[] tfList = new JTextField[8];
	MyImagePanel image = new MyImagePanel();
	String imagePath;
	JComboBox comboSize;
	JComboBox comboStatus;

	// Table
	JPanel tbPanel = new JPanel();
	JScrollPane pane;
	JTable table;
	SkuModelData model;

	SkuModel selectedRow = new SkuModel();
	java.util.List<Long> idSelectedRowList = new java.util.ArrayList<Long>();

	int selectedRowIndex;
	/*
	 * ProductId was being called
	 */
	Long productId;
	/*
	 * Button
	 */
	ChooseFileButton chooseFileBtn = new ChooseFileButton();

	JPanel btnPanel = new JPanel();
	AddButton addBtn;
	ResetButton resetBtn;
	DeleteButton deleteBtn;
	SaveButton saveBtn;

	public SkuPanel(Long productId) {
		this.productId = productId;// main label
		this.setBackground(AbstractPanel.PanelBg);
		this.setPreferredSize(new Dimension(AbstractPanel.PanelWidth, AbstractPanel.PanelHeight));
		this.setLayout(null);
		this.init();

	}

	public void init() {
		/*
		 * Main label
		 */
		mainLabel = new JLabel("SKU Manager - ProductID: " + this.productId);
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
				model.loadData(table, search.getText(), productId);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				model.loadData(table, search.getText(), productId);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				model.loadData(table, search.getText(), productId);
			}
		});

		/*
		 * Text Field
		 */
		this.tfPanel.setLayout(null);
		this.tfPanel.setBounds(0, 60, 520, 230);
		this.add(this.tfPanel);

		String[] infoName = { "SKU's ID: ", "Product's ID: ", "Color: ", "Size: ", "Quantity: ", "Price: ", "Import Price: ",
				"Status: ", "Image: " };

		String[] comboxSizeSelection = new String[11];
		for (int i = 35; i <= 45; i++) {
			int index = i - 35;
			comboxSizeSelection[index] = String.valueOf(i);
		}
		int x = 20;
		int y = 20;
		// this for loop initialize label and text field, set position for both
		for (int i = 0; i < 7; i++) {
			JLabel label = new JLabel(infoName[i]);
			label.setBounds(x, y, 100, 30);
			label.setAlignmentX(RIGHT_ALIGNMENT);
			this.tfPanel.add(label);
			if (i == 3) {
				comboSize = new JComboBox(comboxSizeSelection);
				comboSize.setSelectedItem(null);
				comboSize.setBounds(x + 80, y + 5, 160, 20);
				this.tfPanel.add(comboSize);
			} else {
				this.tfList[i] = new JTextField();
				this.tfList[i].setText("");
				this.tfList[i].setBounds(x + 80, y + 5, 160, 20);
				this.tfPanel.add(this.tfList[i]);
			}
			y += 30;

		}

		x = 280;
		y = 20;
		String[] comboStatusSelection = { "Actived", "Locked", "Out of stock", "Deleted" };
		for (int i = 7; i < this.tfList.length; i++) {
			JLabel label = new JLabel(infoName[i]);
			label.setBounds(x, y, 100, 30);
			this.tfPanel.add(label);
			comboStatus = new JComboBox(comboStatusSelection);
			comboStatus.setSelectedItem(null);
			comboStatus.setBounds(x + 80, y + 5, 160, 20);
			this.tfPanel.add(comboStatus);

			y += 30;
		}

		/*
		 * Image
		 */
		JLabel label = new JLabel(infoName[8]);
		label.setBounds(x, y, 100, 30);
		this.tfPanel.add(label);
		this.tfPanel.add(chooseFileBtn);

		chooseFileBtn.setBounds(x - 10, y + 30, 80, 16);
		chooseFileBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		chooseFileBtn.setBorder(null);
		imagePath = chooseFileBtn.getImagePath();

		chooseFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
//				chooseFileButtonHandle();
				JFileChooser fc = new JFileChooser();

				int result = fc.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
//					System.out.print(file.getName());
					imagePath = file.getName();
				}

				try {
					Runtime rt = Runtime.getRuntime();
					Process proc = rt.exec("cp /Users/lehokimminh/Downloads/Image/" + imagePath + " "
							+ "/Users/lehokimminh/workspace/SGU-JavaGui/src/main/java/xxminhmie/sgu/javagui/image");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (imagePath != null) {
					image.loadAndShowImage(imagePath);
					image.setBounds(280 + 80, 30 + 20, 160, 160);
					tfPanel.add(image);
					image.setVisible(true);
				}
				// first click
				tfPanel.repaint();

			}
		});
//		if(this.selectedRow.getImage() != null) {
//			this.image = new MyImage(this.selectedRow.getImage());
//		}else {
//			this.image = new MyImage("2.jpg");
//		}
//		this.image.setBounds(x + 80, 30 + 20, 160, 160);
//		this.tfPanel.add(this.image);
		/** set read - only for ID text field **/
		tfList[0].setEditable(false);
		tfList[0].setForeground(new Color(108, 108, 108));

		tfList[1].setEditable(false);
		tfList[1].setText(String.valueOf(this.productId));

		/*
		 * Button
		 */
		btnPanel.setBounds(520, 60, 500, 230);
		btnPanel.setLayout(null);
//		btnPanel.setBackground(Color.BLUE);
		add(btnPanel);

		addBtn = new AddButton(20, 20);
		btnPanel.add(addBtn);
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addButtonHandle();

			}
		});

		saveBtn = new SaveButton(20, 50);
		btnPanel.add(saveBtn);
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				saveButtonHandle();
			}
		});

		resetBtn = new ResetButton(20, 80);
		btnPanel.add(resetBtn);
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				resetButtonHandle();
			}
		});

		deleteBtn = new DeleteButton(20, 110);
		btnPanel.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteButtonHandle();

			}

		});

		/*
		 * Table
		 */
		model = new SkuModelData();
		table = new JTable(model);
		table.setDefaultRenderer(Object.class, new Renderer());

		model.setColumnWidth(table);

		/** GET SELECTED VALUE TO DISPLAY ON TEXT FIELD **/
		this.table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {

				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					selectedRowIndex = table.getSelectedRow();// get index of selected rowIndex
					productId = selectedRow.getProductId();

					setSelectedSkuModel();/** GET MANUALLY ALL ROW **/
				}
				displaySkuToTextField();
				getAllSelectedRow(table);
			}
		});
		/********************** INITIALIZE SCROLL PANE **************************/
		add(tbPanel);
		tbPanel.setBounds(0, 290, SubFrame.SUBFRAME_W, 500);
		tbPanel.setLayout(null);

		pane = new JScrollPane(this.table);
		pane.setBounds(20, 20, SubFrame.SUBFRAME_W - 70, 240);
		/** ADD SCROLL PANE TO MAIN PANEL **/
		tbPanel.add(this.pane);
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
					Long id = (Long) entryTable.getValueAt(i, 0);
					idSelectedRowList.add(id);
				}
			}
		}
	}

	/*
	 * Reset button handle
	 */
	public void resetButtonHandle() {

		for (int i = 0; i < this.tfList.length - 1; i++) {
			if (i == 3 || i == 7) {
				this.comboSize.setSelectedItem(null);
			} else {
				this.tfList[i].setText("");
			}
		}
		this.image.setVisible(false);
		this.imagePath = null;
	}

	/*
	 * Add button handle
	 */
	public void addButtonHandle() {
		this.tfList[0].setText("");
	}

	/*
	 * Add button handle
	 */
	public void deleteButtonHandle() {
		int click = JOptionPane.showConfirmDialog(null, "Are you sure to delete this product?");
		if (click == JOptionPane.YES_OPTION) {
			Long[] ids = new Long[1];
			ids[0] = Long.parseLong(this.tfList[0].getText());
			this.skuService.delete(ids);
		}
	}

	/*
	 * Save button handle
	 */
	public void saveButtonHandle() {
		int click = JOptionPane.showConfirmDialog(null, "Are you sure to save?");
		if (click == JOptionPane.YES_OPTION) {
			this.saveAction();
		}
	}

	public int saveAction() {
		Long id = null;
		if (this.tfList[0].getText().isBlank() == false) {
			try {
				id = Long.parseLong(tfList[0].getText());
			} catch (NumberFormatException e) {

			}
		}

		String color = "";
		if (this.tfList[2].getText().isBlank() == false) {
			color = this.tfList[2].getText();
		} else {
			JOptionPane.showMessageDialog(null, "Product's color must be not null!");
			tfList[2].requestFocus();
			return -1;
		}

		String size = "";
		if (comboSize.getSelectedItem() != null) {
			size = comboSize.getSelectedItem().toString();
		} else {
			JOptionPane.showMessageDialog(null, "Product's size must be not null!");
			comboSize.requestFocus();
			return -1;
		}
		int qty = 0;
		if (this.tfList[5].getText().isBlank() == false) {
			try {
				qty = Integer.parseInt(tfList[4].getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Product's quantity is invalid!");
				tfList[4].requestFocus();
				return -1;
			}
		}

		String price = "";
		if (this.tfList[5].getText().isBlank() == false) {
			try {
				int temp = Integer.parseInt(tfList[4].getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Product's price is invalid!");
				tfList[5].requestFocus();
				return -1;
			}
			price = tfList[5].getText();
		}
		String importPrice = "";
		if (this.tfList[6].getText().isBlank() == false) {
			try {
				int temp = Integer.parseInt(tfList[4].getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Product's price is invalid!");
				tfList[6].requestFocus();
				return -1;
			}
			importPrice = tfList[6].getText();
		}

		String status = "";
		if (comboStatus.getSelectedItem() != null) {
			status = comboStatus.getSelectedItem().toString();
			if (status.equals("Actived") == false) {
				JOptionPane.showMessageDialog(null, "Product's status must be 'actived' while adding!");
				tfList[7].requestFocus();
				return -1;
			}
		}

		if (this.skuService.findOneByColorSize(this.productId, color, size) != null) {
			JOptionPane.showMessageDialog(null, "This product is available!");
			return -1;
		}
		// Create new SKU
		Long savedId;
		if (id == null) {
			SkuModel model = skuService
					.save(new SkuModel(color, size, qty, price, importPrice, this.imagePath, status, this.productId));
//			SkuModel model = new SkuModel(color, size, qty, price, importPrice, this.imagePath, status, this.productId);
//			System.out.print(model.toString());
//			savedId = model.getId();
		} else {
			// Update product
			SkuModel model = skuService.update(
					new SkuModel(id, color, size, qty, price, importPrice, this.imagePath, status, this.productId));
			savedId = model.getId();
		}
		this.model.loadData(this.table, this.productId);
//		getGeneratedKeys(savedId);

//		table.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);

		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec("cp /Users/lehokimminh/Downloads/Image/" + this.imagePath + " "
					+ "/Users/lehokimminh/workspace/SGU-JavaGui/src/main/java/xxminhmie/sgu/javagui/image");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

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
	 * This method will be called by openSkuFrame method from Product Panel
	 */
	public void loadDataByProductId(Long productId) {
		if (this.skuService.findByProductId(productId) != null) {
			this.model.loadData(this.table, productId);
			this.productId = productId;
			this.selectedRowIndex = 0;
			this.setSelectedSkuModel();
			this.displaySkuToTextField();
			this.table.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
		} else {
			this.model.setData(null);
		}

	}

	public void setSelectedSkuModel() {
		selectedRow.setId((Long) table.getModel().getValueAt(selectedRowIndex, 0));
		selectedRow.setProductId((Long) table.getModel().getValueAt(selectedRowIndex, 1));
		selectedRow.setColor((String) table.getModel().getValueAt(selectedRowIndex, 2));
		selectedRow.setSize((String) table.getModel().getValueAt(selectedRowIndex, 3));
		selectedRow.setQuantity((Integer) table.getModel().getValueAt(selectedRowIndex, 4));
		selectedRow.setPrice((String) table.getModel().getValueAt(selectedRowIndex, 5));
		selectedRow.setImportPrice((String) table.getModel().getValueAt(selectedRowIndex, 6));
		selectedRow.setStatus((String) table.getModel().getValueAt(selectedRowIndex, 7));
		selectedRow.setImage((String) table.getModel().getValueAt(selectedRowIndex, 8));
	}

	public void displaySkuToTextField() {
		tfList[0].setText(selectedRow.getId().toString());
		tfList[1].setText(selectedRow.getProductId().toString());
		tfList[2].setText(selectedRow.getColor());
		for (int i = 35; i <= 45; i++) {// Size combo
			int index = i - 35;
			if (selectedRow.getSize().equals(comboSize.getItemAt(index))) {
				comboSize.setSelectedIndex(index);
				break;
			}
		} // Size combo
		tfList[4].setText(String.valueOf(selectedRow.getQuantity()));
		// format price display
//		if (selectedRow.getPrice() != null) {
//			tfList[5].setText(MoneyFormat.customFormat(Double.parseDouble(selectedRow.getPrice())));
//		}
//		if (selectedRow.getImportPrice() != null) {
//			tfList[6].setText(MoneyFormat.customFormat(Double.parseDouble(selectedRow.getImportPrice())));
//		} // format price display
		tfList[5].setText(selectedRow.getPrice());
		tfList[6].setText(selectedRow.getImportPrice());

		// Status combo
		for (int i = 0; i < 3; i++) {
			if (comboSize.getItemAt(i) != null && selectedRow.getStatus() != null) {
				if (selectedRow.getStatus().equals(comboSize.getItemAt(i).toString())) {
					comboSize.setSelectedIndex(i);
					break;
				}
			}

		} // Status combo
			// image
		if (selectedRow.getImage() != null) {
			image.loadAndShowImage(selectedRow.getImage());
			image.setBounds(280 + 80, 30 + 20, 160, 160);
			tfPanel.add(image);
			image.setVisible(true);
			imagePath = selectedRow.getImage();
		}
		// first click
		tfPanel.repaint();

//		loadProductInfo();
	}

}
