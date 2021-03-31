package xxminhmie.sgu.javagui.gui.sidebar;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import xxminhmie.sgu.javagui.gui.ApplicationGUI;

public class SidebarItem extends JButton implements MouseListener{
	private static final long serialVersionUID = 1L;
	protected static final int SidebarItemHeight = 50;

	public SidebarItem(String name, String panelName,JPanel content) {
		this.setText(name);
		this.setPreferredSize(new Dimension(SidebarContainer.SidebarWidth, SidebarItemHeight));
		this.setBorderPainted(false);
	    this.setOpaque(true);
		this.setBackground(SidebarContainer.SidebarBg);
		this.setForeground(SidebarContainer.SidebarFg);
		this.addMouseListener (this);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) (content.getLayout());
//				System.out.println("Clicked " + panelName);
				cardLayout.show(content,(String) panelName);
				
			}
			
		});
	}
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	    this.setOpaque(true);
		this.setBackground(SidebarContainer.SidebarBgHover);
	}

	@Override
	public void mouseExited(MouseEvent e) {
	    this.setOpaque(true);
		this.setBackground(SidebarContainer.SidebarBg);
	}

	

	
}
