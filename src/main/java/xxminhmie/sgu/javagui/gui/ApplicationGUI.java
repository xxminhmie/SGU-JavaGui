package xxminhmie.sgu.javagui.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import xxminhmie.sgu.javagui.gui.panel.AccountPanel;
import xxminhmie.sgu.javagui.gui.panel.BillPanel;
import xxminhmie.sgu.javagui.gui.panel.CustomerPanel;
import xxminhmie.sgu.javagui.gui.panel.DiscountPanel;
import xxminhmie.sgu.javagui.gui.panel.POPanel;
import xxminhmie.sgu.javagui.gui.panel.ProductPanel;
import xxminhmie.sgu.javagui.gui.panel.RolePanel;
import xxminhmie.sgu.javagui.gui.panel.StaffPanel;
import xxminhmie.sgu.javagui.gui.panel.SupplierPanel;
import xxminhmie.sgu.javagui.gui.sidebar.SidebarContainer;
import xxminhmie.sgu.javagui.service.impl.SidebarService;

public class ApplicationGUI extends JFrame {
//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				new ApplicationGUI().displayGUI();
//			}
//		});
//	}

	public static final int FrameHeight = 700;
	public static final int FrameWidth = 1200;

	protected JPanel mainContent;
	JPanel sidebar;

	SidebarService service = new SidebarService();

	public ApplicationGUI() {
		this.displayGUI();
	}

	protected void displayGUI() {
		this.setSize(new Dimension(FrameWidth, FrameHeight));
		this.setLocationRelativeTo(null); // this will center your application
		this.setLayout(new BorderLayout());

		this.mainContent = new JPanel();
		this.mainContent.setOpaque(true);
		this.mainContent.setBackground(Color.PINK);

		CardLayout card = new CardLayout();
		this.mainContent.setLayout(card);

		JPanel panel1 = new ProductPanel();
		JPanel panel2 = new BillPanel();
		JPanel panel3 = new POPanel();
		JPanel panel4 = new SupplierPanel();
		JPanel panel5 = new DiscountPanel();
		JPanel panel6 = new StaffPanel();
		JPanel panel7 = new CustomerPanel();
		JPanel panel8 = new AccountPanel();
		JPanel panel9 = new RolePanel();

		mainContent.add(panel1, "link1");
		mainContent.add(panel2, "link2");
		mainContent.add(panel3, "link3");
		mainContent.add(panel4, "link4");
		mainContent.add(panel5, "link5");
		mainContent.add(panel6, "link6");
		mainContent.add(panel7, "link7");
		mainContent.add(panel8, "link8");
		mainContent.add(panel9, "link9");

		String[] nameList = { service.findAll().get(0).getName().toString(),
				service.findAll().get(1).getName().toString(), service.findAll().get(2).getName().toString(),
				service.findAll().get(3).getName().toString(), service.findAll().get(4).getName().toString(),
				service.findAll().get(5).getName().toString(), service.findAll().get(6).getName().toString(),
				service.findAll().get(7).getName().toString(), service.findAll().get(8).getName().toString(),

		};

		String[] namePanel = { "link1", "link2", "link3", "link4", "link5","link6","link7","link8","link9" };

		this.sidebar = new SidebarContainer(nameList, namePanel, this.mainContent);
		this.add(sidebar, BorderLayout.WEST);
		this.add(this.mainContent, BorderLayout.CENTER);

		panel1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}
		});
	}

}
