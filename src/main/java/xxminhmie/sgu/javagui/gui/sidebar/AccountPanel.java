package xxminhmie.sgu.javagui.gui.sidebar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import xxminhmie.sgu.javagui.gui.LogInGUI;
import xxminhmie.sgu.javagui.image.MyImagePanel;

public class AccountPanel extends JPanel {
	JLabel hi;
	MyImagePanel image;
	JLabel logout;
	
	JLabel profile;

	
	public AccountPanel() {
		this.setPreferredSize(new Dimension(SidebarContainer.SidebarWidth, 160));
		this.setBackground(Color.BLACK);
		this.setLayout(null);
		
		hi = new JLabel("Hi "+LogInGUI.ACCOUNT_LOGIN.getUsername());
		hi.setForeground(Color.WHITE);
		hi.setFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
		hi.setBounds(20, 4, 160, 20);
		add(this.hi);
		
		this.image = new MyImagePanel(100,100);
		this.image.loadAndShowImage("account-icon.png");
		this.image.setBounds(40, 30, 100, 100);
		
		this.add(this.image);
		
		logout = new JLabel("Logout");
		logout.setForeground(Color.WHITE);
		logout.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,12));
		logout.setBounds(100, 130, 50, 20);
		add(this.logout);
		
		profile = new JLabel("Profile");
		profile.setForeground(Color.WHITE);
		profile.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,12));
		profile.setBounds(20, 130, 50, 20);
		add(this.profile);
		/*
		 * This listener was written on ApplicationGUI frame
		 */
//		logout.addMouseListener(new java.awt.event.MouseAdapter() {
//			public void mouseEntered(java.awt.event.MouseEvent evt) {
//				logout.setText("<HTML><U>Logout</U></HTML>");
//			}
//
//			public void mouseExited(java.awt.event.MouseEvent evt) {
//				logout.setText("Logout");
//			}
//			
//		});

	}

	public JLabel getLogoutLabel() {
		return this.logout;
	}
	public JLabel getProfileLabel() {
		return this.profile;
	}
	
	

}
