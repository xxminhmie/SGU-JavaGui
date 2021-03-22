package xxminhmie.sgu.javagui.gui.sidebar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import xxminhmie.sgu.javagui.gui.ApplicationGUI;

public class SidebarContainer extends JPanel {
	private static final long serialVersionUID = 1L;

	protected static final int SidebarWidth = 160;
	protected static final Color SidebarBg = Color.BLACK;
	protected static final Color SidebarBgHover = new Color(53, 53, 53);
	protected static final Color SidebarFg = Color.WHITE;

	protected JButton[] listItem;
	
	public SidebarContainer(String[] nameList, String[] namePanel,JPanel content) {
		this.setBackground(SidebarBg);
		this.setPreferredSize(new Dimension(SidebarWidth, ApplicationGUI.FrameHeight));
		this.setLayout(new FlowLayout());
		this.listItem = new SidebarItem[nameList.length];
		for(int i=0; i<nameList.length; i++) {
			this.listItem[i] = new SidebarItem(nameList[i], namePanel[i],content);
			this.add(this.listItem[i]);
		}

	}
	

//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(new Dimension(1200, 700));
//		frame.setLocationRelativeTo(null); // this will center your application
//		frame.setLayout(new BorderLayout());
//
//		String[] nameList = {"Button 1","Button 2","Button 3"};
//		
//		JPanel sidebar = new SidebarContainer(nameList);
//		frame.add(sidebar, BorderLayout.WEST);
//
//		frame.setVisible(true);
//	}

}
