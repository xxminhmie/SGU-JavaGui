package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import GUI.Form.AForm;

public class ApplicationGUI extends JFrame {
	
	public static final int frameHeight = 700;
	public static final int frameWidth = 1200;
	
	public static final int sidebarWidth = 160;
	public static final Color sidebarBgColor = new Color(0,0,0);
	
	public static final int btnSidebarWidth = 150;
	public static final int btnSidebarHeight = 50;
	public static final Color btnSidebarBgColor = new Color(10,10,10);
	public static final Color btnSidebarBgColorHover = new Color(43,43,43);
	public static final Color btnSidebarFgColor = new Color(255,255,255);



	protected JPanel sidebar;
	protected Container container;
	protected CardLayout card;

	protected JButton aBtn;
	protected AForm aform;

	public ApplicationGUI() {
		setSize(new Dimension(frameWidth, frameHeight));
		this.sidebar = new JPanel();
		this.customizeSidebar();
		this.addBtnToSidebar(this.aBtn, "Button 1");
		this.container = new Container();
		this.customizeContent();
//		this.card = new CardLayout(1200,700);
//		setLayout(this.card);
		setLayout(new BorderLayout());
		
		
		this.aform = new AForm();
		this.addFormToContainer(this.aform);
		
		
		add(this.sidebar,BorderLayout.WEST);
		add(this.container,BorderLayout.CENTER);

	}
	protected void customizeSidebar() {
		this.sidebar.setLayout(new FlowLayout());
		this.sidebar.setPreferredSize(new Dimension(sidebarWidth,frameHeight));
		this.sidebar.setBackground(sidebarBgColor);
	}
	protected void customizeContent() {
//		this.sidebar.setLayout(new FlowLayout());
		this.container.setPreferredSize(new Dimension(200,frameHeight));
		this.container.setBackground(Color.red);
	}

	protected void addFormToContainer(JPanel p) {
		this.container.add(p);

	}

	protected void addBtnToSidebar(JButton btn,String name) {
		btn = new JButton(name);
		btn.setPreferredSize(new Dimension(btnSidebarWidth,btnSidebarHeight));
		btn.setBackground(btnSidebarBgColor);
		btn.setOpaque(true);
		btn.setBorderPainted(false);
		btn.setForeground(btnSidebarFgColor);
		
		this.sidebar.add(btn);
	}
//	protected void
	
	public static void main(String[] args) {
		ApplicationGUI a = new ApplicationGUI();
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a.setVisible(true);
		
	}

}
