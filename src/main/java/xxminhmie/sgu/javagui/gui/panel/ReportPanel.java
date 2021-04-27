package xxminhmie.sgu.javagui.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import xxminhmie.sgu.javagui.gui.common.AbstractButton;
import xxminhmie.sgu.javagui.gui.common.ThongKeTheoDoanhThu;
import xxminhmie.sgu.javagui.gui.modeltable.ReportModelData;
import xxminhmie.sgu.javagui.model.ProductModel;
import xxminhmie.sgu.javagui.model.ReportModel;
import xxminhmie.sgu.javagui.service.impl.ProductService;
import xxminhmie.sgu.javagui.service.impl.ReportService;

public class ReportPanel extends JPanel {
	ReportService service = new ReportService();
	JLabel mainLabel;
	JPanel mainPanel;
	JPanel infoPanel;

	JComboBox month;
	JComboBox quarter;
	JTextField year;
	AbstractButton submit;

	/*
	 * Table
	 */
	JPanel tbPanel;
	JScrollPane pane;
	public JTable table;
	ReportModelData model;

	List<ReportModel> list = new ArrayList<ReportModel>();

	/*
	 * constructor
	 */
	public ReportPanel() {
		setBackground(AbstractPanel.PanelBg);
		setPreferredSize(new Dimension(AbstractPanel.PanelWidth, AbstractPanel.PanelHeight));
		setLayout(new BorderLayout());

		mainLabel = new JLabel("Report");
		mainLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		add(mainLabel, BorderLayout.NORTH);

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		add(this.mainPanel, BorderLayout.CENTER);

		/*
		 * month combo box
		 */
		int x = 10;
		int y = 60;
		JLabel monthLabel = new JLabel("Month: ");
		monthLabel.setBounds(x, y, 60, 20);
		mainPanel.add(monthLabel);

		String[] monthSelection = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
		month = new JComboBox(monthSelection);
		month.setSelectedItem(null);
		month.setBounds(x + 60, y, 140, 20);

		mainPanel.add(month);

		/*
		 * quarter combo box
		 */

		JLabel quarterLabel = new JLabel("Quarter: ");
		quarterLabel.setBounds(x + 60 + 140 + 10, y, 60, 20);
		mainPanel.add(quarterLabel);

		String[] quarterSelection = { "1", "2", "3", "4" };
		quarter = new JComboBox(quarterSelection);
		quarter.setSelectedItem(null);
		quarter.setBounds(x + 60 + 140 + 60 + 10, y, 140, 20);

		mainPanel.add(quarter);

		/*
		 * year
		 */

		JLabel yearLabel = new JLabel("Year: ");
		yearLabel.setBounds(x + 60 + 140 + 60 + 140 + 20, y, 40, 20);
		mainPanel.add(yearLabel);

		year = new JTextField();
		year.setBounds(x + 60 + 140 + 60 + 140 + 40 + 20, y, 160, 20);

		mainPanel.add(year);

		/*
		 * Button
		 */
		submit = new AbstractButton(x + 60 + 140 + 60 + 140 + 60 + 160, 60);
		submit.setNameBtn("Go");
		mainPanel.add(submit);
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				submitHandle();
			}

		});
		year.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					submit.doClick();
				}
			}
		});

		/*
		 * table
		 */
		tbPanel = new JPanel();
		tbPanel.setBackground(Color.red);
		tbPanel.setBounds(20, 100, 700, 360);
		tbPanel.setLayout(null);
		mainPanel.add(tbPanel);

		/** INITIALIZE ZEBRA TABLE **/
		model = new ReportModelData(list);
		table = new JTable(model);

		table.setDefaultRenderer(Object.class, new Renderer());

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {

				}
			}
		});
		/*
		 * Scroll Pane
		 */
		pane = new JScrollPane(table);
		pane.setBounds(0, 0, 700, 360);
		tbPanel.add(pane);

	}

	public void submitHandle() {
		if (year.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Please insert year!");
			year.requestFocus();
			return;
		}
		int year = -1;
		try {
			year = Integer.parseInt(this.year.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Year is invalid!");
			this.year.requestFocus();
			return;
		}
		if (year <= 0) {
			JOptionPane.showMessageDialog(null, "Year is invalid!");
			this.year.requestFocus();
			return;
		}
		int month = -1;

		if (this.month.getSelectedIndex() >= 0) {
			month = Integer.parseInt((String) this.month.getSelectedItem());
		}

		int quarter = -1;
		if (this.quarter.getSelectedIndex() >= 0) {
			quarter = Integer.parseInt((String) this.quarter.getSelectedItem());
		}

		if (month < 0 && quarter < 0) {
			runYear(year);
		}
		if (month >= 0 && quarter < 0) {
			runMonth(month,year);
		}
		if (month < 0 && quarter >= 0) {
			runQuarter(quarter,year);
		}
		if (month > 0 && quarter >= 0) {
			this.month.setSelectedItem(null);
			runQuarter(quarter,year);
		}
	}

	public void runMonth(int month, int year) {
		this.list = service.reportMonth(month, year);
		if(list==null) {
			JOptionPane.showMessageDialog(null, "In this time, having no bill!");
			this.list = new ArrayList<>();
			this.model.loadData(table, this.list);
		}else {
			this.model.loadData(table, this.list);
		}
	}

	public void runQuarter(int quarter, int year) {
		switch (quarter) {
		case 1: {
			this.list = service.reportQuarter(1, 2, 3, year);
			if(list==null) {
				JOptionPane.showMessageDialog(null, "In this time, having no bill!");
				this.list = new ArrayList<>();
				this.model.loadData(table, this.list);
			}else {
				this.model.loadData(table, this.list);
			}
			break;
		}
		case 2: {
			this.list = service.reportQuarter(4, 5, 6, year);
			if(list==null) {
				JOptionPane.showMessageDialog(null, "In this time, having no bill!");
				this.list = new ArrayList<>();
				this.model.loadData(table, this.list);
			}else {
				this.model.loadData(table, this.list);
			}
			break;
		}
		case 3: {
			this.list = service.reportQuarter(7, 8, 9, year);
			if(list==null) {
				JOptionPane.showMessageDialog(null, "In this time, having no bill!");
				this.list = new ArrayList<>();
				this.model.loadData(table, this.list);
			}else {
				this.model.loadData(table, this.list);
			}
			break;
		}
		case 4: {
			this.list = service.reportQuarter(10, 11, 12, year);
			if(list==null) {
				JOptionPane.showMessageDialog(null, "In this time, having no bill!");
				this.list = new ArrayList<>();
				this.model.loadData(table, this.list);
			}else {
				this.model.loadData(table, this.list);
			}
			break;
		}

		}
	}

	public void runYear(int year) {
		this.list = service.reportYear(year);
		if(list==null) {
			JOptionPane.showMessageDialog(null, "In this time, having no bill!");
			this.list = new ArrayList<>();
			this.model.loadData(table, this.list);
		}else {
			this.model.loadData(table, this.list);
		}

	}
	
	
}
