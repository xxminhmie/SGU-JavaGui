package xxminhmie.sgu.javagui.gui.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import xxminhmie.sgu.javagui.gui.common.AddButton;
import xxminhmie.sgu.javagui.gui.common.ChooseFileButton;
import xxminhmie.sgu.javagui.gui.common.DeleteButton;
import xxminhmie.sgu.javagui.gui.common.ResetButton;
import xxminhmie.sgu.javagui.gui.modeltable.PODetailModelData;
import xxminhmie.sgu.javagui.image.MyImagePanel;
import xxminhmie.sgu.javagui.model.PODetailModel;
import xxminhmie.sgu.javagui.service.impl.PODetailService;



public class PODetailPanel extends JPanel{
	PODetailService service;

	JPanel tfPanel = new JPanel();
	JTextField[] tfList = new JTextField[8];
	MyImagePanel image = new MyImagePanel();
	String imagePath;
	JComboBox comboSize;
	JComboBox comboStatus;

	// SKU
	JPanel tablePanel = new JPanel();
	JScrollPane pane;
	JTable table;
	PODetailModelData model;

	PODetailModel selectedRow = new PODetailModel();// Customer is being selected from Table
	java.util.List<Long> idSelectedRowList = new java.util.ArrayList<Long>();// Contains list of customer's ID to
																				// delete
	int selectedRowIndex;

	Long productId;
	// Button
	ChooseFileButton chooseFileBtn = new ChooseFileButton();

	JPanel btnPanel = new JPanel();
	AddButton addBtn;
	ResetButton resetBtn;
	DeleteButton deleteBtn;

	public PODetailPanel(PODetailService service) {
		this.service = service;
		this.setBackground(AbstractPanel.PanelBg);
		this.setPreferredSize(new Dimension(AbstractPanel.PanelWidth, AbstractPanel.PanelHeight));
		this.setLayout(null);
		this.init();

	}

	protected void init() {

		/**********************************
		 * TEXT FIELD
		 **********************************/
		this.tfPanel.setLayout(null);
		this.tfPanel.setBounds(0, 0, 520, 230);
		this.add(this.tfPanel);

		String[] infoName = { "SKUID: ", "ProductId: ", "Color: ", "Size: ", "Quantity: ", "Price: ", "Import Price: ",
				"Status: ", "Image: " };

		String[] comboxSizeSelection = new String[11];
		for (int i = 35; i <= 45; i++) {
			int index = i - 35;
			comboxSizeSelection[index] = String.valueOf(i);
		}
		int x = 0;
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

		// listener for combo box
//		c1.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if (e.getSource() == c1) {
//                    l1.setText(c1.getSelectedItem() + " duoc chon");
//                }
//            }
//        });

		x = 280;
		y = 20;
		String[] comboStatusSelection = { "Actived", "Locked", "Out of stock" };
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
//		chooseFileBtn.setMargin(new Insets(1,1,1,1));
		chooseFileBtn.setBorder(null);
		imagePath = chooseFileBtn.getImagePath();
//		if(this.selectedRow.getImage() != null) {
//			this.image = new MyImage(this.selectedRow.getImage());
//		}else {
//			this.image = new MyImage("2.jpg");
//		}
//		this.image.setBounds(x + 80, 30 + 20, 160, 160);
//		this.tfPanel.add(this.image);
		/** set read - only for ID text field **/
		this.tfList[0].setEditable(false);
		this.tfList[1].setEditable(false);
		this.tfList[0].setForeground(new Color(108, 108, 108));

		/*
		 * Table
		 */
		this.model = new PODetailModelData();
		this.table = new JTable(model);
		this.table.setDefaultRenderer(Object.class, new Renderer());

		model.setColumnWidth(table);

		/** GET SELECTED VALUE TO DISPLAY ON TEXT FIELD **/
		this.table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {

				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {

					int rowIndex = table.getSelectedRow();// get index of selected rowIndex
					selectedRowIndex = rowIndex;
					/** GET MANUALLY ALL ROW **/
//					selectedRow.setId((Long) table.getModel().getValueAt(rowIndex, 0));
//					selectedRow.setProductId((Long) table.getModel().getValueAt(rowIndex, 1));
//					selectedRow.setColor((String) table.getModel().getValueAt(rowIndex, 2));
//					selectedRow.setSize((String) table.getModel().getValueAt(rowIndex, 3));
//					selectedRow.setQuantity((Integer) table.getModel().getValueAt(rowIndex, 4));
//					selectedRow.setPrice((String) table.getModel().getValueAt(rowIndex, 5));
//					selectedRow.setImportPrice((String) table.getModel().getValueAt(rowIndex, 6));
//					selectedRow.setStatus((String) table.getModel().getValueAt(rowIndex, 7));
//					selectedRow.setImage((String) table.getModel().getValueAt(rowIndex, 8));

//					productId = selectedRow.getProductId();
				}
				/** DISPLAY TO TEXT FIELD **/
//				tfList[0].setText(selectedRow.getId().toString());
//				tfList[1].setText(selectedRow.getProductId().toString());
//				tfList[2].setText(selectedRow.getColor());
////				tfList[3].setText(selectedRow.getSize());
//				for (int i = 35; i <= 45; i++) {
//					int index = i - 35;
//					if (selectedRow.getSize().equals(comboSize.getItemAt(index))) {
//						comboSize.setSelectedIndex(index);
//						break;
//					}
//				}
//				tfList[4].setText(String.valueOf(selectedRow.getQuantity()));
//				if (selectedRow.getPrice() != null) {
//					tfList[5].setText(MoneyFormat.customFormat(Double.parseDouble(selectedRow.getPrice())));
//				}
//				if (selectedRow.getImportPrice() != null) {
//					tfList[6].setText(
//							MoneyFormat.customFormat(Double.parseDouble(selectedRow.getImportPrice())) + "vnd");
//				}
////				tfList[7].setText(selectedRow.getStatus());
//				for (int i = 0; i < 3; i++) {
//					if (comboSize.getItemAt(i) != null && selectedRow.getStatus() != null) {
//						if (selectedRow.getStatus().equals(comboSize.getItemAt(i).toString())) {
//							comboSize.setSelectedIndex(i);
//							break;
//						}
//					}
//
//				}
//				// image
//				if (selectedRow.getImage() != null) {
//					image.loadAndShowImage(selectedRow.getImage());
//					image.setBounds(280 + 80, 30 + 20, 160, 160);
//					tfPanel.add(image);
//					image.setVisible(true);
//					imagePath = selectedRow.getImage();
//				}
//				// first click
//				tfPanel.repaint();
//
//				getAllSelectedRow(table);

//				loadProductInfo();
			}
		});
		/********************** INITIALIZE SCROLL PANE **************************/
		this.add(tablePanel);
		tablePanel.setBounds(0, 230, 1000, 500);
		tablePanel.setLayout(null);

		this.pane = new JScrollPane(this.table);
		this.pane.setBounds(0, 20, 520, 340);
		/** ADD SCROLL PANE TO MAIN PANEL **/
		this.tablePanel.add(this.pane);

		// Button
		btnPanel.setBounds(520, 0, 200, 230);
		btnPanel.setLayout(null);
//		btnPanel.setBackground(Color.BLUE);
		this.add(btnPanel);

		addBtn = new AddButton(20, 20);
		btnPanel.add(addBtn);

		resetBtn = new ResetButton(20, 50);
		btnPanel.add(resetBtn);

		deleteBtn = new DeleteButton(20, 80);
		btnPanel.add(deleteBtn);

	}

	/*
	 * Table listerner handle
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

	public void loadData(Long id) {
		this.productId = id;
//		if (this.selectedRow.getId() != id) {
//			this.model.loadData(this.table, id);
//			this.resetSku();
//		}
	}

	public void loadData(String str) {
		this.model.loadData(table, str);

	}

	public void resetButtonHandle() {
		int click = JOptionPane.showConfirmDialog(null, "Are you sure to reset?");
		if (click == JOptionPane.YES_OPTION) {
			resetSku();
		}

	}

	public void resetSku() {
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
	 * Button handle
	 */
	public int addButtonHandle(Long productId) {
		Long id = null;
		if(this.tfList[0].getText().isBlank()==false) {
			try {
				id = Long.parseLong(tfList[0].getText());
			}catch(NumberFormatException e) {
				
			}
		}
		
		String color = null;
		if(this.tfList[2].getText().isBlank()==false) {
			color = this.tfList[2].getText();
		}else {
			JOptionPane.showMessageDialog(null, "Product's color must be not null!");
			tfList[2].requestFocus();
			return -1;
		}
		
		String size = null;
		if(comboSize.getSelectedItem()!=null){
			size = comboSize.getSelectedItem().toString();
		}else {
			JOptionPane.showMessageDialog(null, "Product's size must be not null!");
			comboSize.requestFocus();
			return -1;
		}
		int qty = 0;
		if(this.tfList[5].getText().isBlank()==false) {
			try {
				qty= Integer.parseInt(tfList[4].getText());
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Product's quantity is invalid!");
				tfList[4].requestFocus();
				return -1;
			}
		}
		
		String price = null;
		if(this.tfList[5].getText().isBlank()==false) {
			try {
				int temp = Integer.parseInt(tfList[4].getText());
				if(temp!=0 && temp<=1000) {
					price = tfList[4].getText() + "000";
				}
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Product's price is invalid!");
				tfList[5].requestFocus();
				return -1;
			}
		}
		
		String status = null;
		if(comboStatus.getSelectedItem()!=null) {
			status = comboStatus.getSelectedItem().toString();
			if(status.equals("Actived")==false) {
				JOptionPane.showMessageDialog(null, "Product's status must be 'actived' while adding!");
				tfList[5].requestFocus();
				return -1;
			}
		}
		
		
		
		return 0;

		
	}


}
