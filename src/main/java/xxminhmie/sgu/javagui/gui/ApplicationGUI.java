package xxminhmie.sgu.javagui.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import xxminhmie.sgu.javagui.gui.sidebar.SidebarContainer;

public class ApplicationGUI {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ApplicationGUI().displayGUI();
			}
		});
	}

	public static final int FrameHeight = 700;
	public static final int FrameWidth = 1200;

	protected JPanel mainContent;

	protected void displayGUI() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(FrameWidth, FrameHeight));
		frame.setLocationRelativeTo(null); // this will center your application
		frame.setLayout(new BorderLayout());

		this.mainContent = new JPanel();
		this.mainContent.setOpaque(true);
		this.mainContent.setBackground(Color.PINK);

		CardLayout card = new CardLayout();
		this.mainContent.setLayout(card);

//		JPanel content1 = new JPanel();
//		content1.setOpaque(true);
//		content1.setBackground(Color.RED);
//		content1.setPreferredSize(new Dimension(400,400));
//		this.mainContent.add(content1,"content1");

//		
//		JPanel content2 = new JPanel();
//		content2.setOpaque(true);
//		content2.setBackground(Color.BLUE);
//		this.mainContent.add(content2,"content2");

//		JPanel content3 = new JPanel();
//		content3.setOpaque(true);
//		content3.setBackground(Color.GREEN);
//		this.mainContent.add(content3,"content3");

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JLabel label1 = new JLabel("Content of Card 1 is visible now!");
		JLabel label2 = new JLabel("Content of Card 2 is visible now!");
		JLabel label3 = new JLabel("Content of Card 3 is visible now!");

		panel1.add(label1);
		panel2.add(label2);
		panel3.add(label3);

		mainContent.add(panel1, "link1");
		mainContent.add(panel2, "link2");
		mainContent.add(panel3, "link3");


		String[] nameList = { "Button 1", "Button 2", "Button 3" };

		String[] namePanel = { "link1", "link2", "link3" };

		JPanel sidebar = new SidebarContainer(nameList, namePanel, this.mainContent);
		frame.add(sidebar, BorderLayout.WEST);
		frame.add(this.mainContent, BorderLayout.CENTER);
		frame.setVisible(true);
	}

}
