package xxminhmie.sgu.javagui.gui.panel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import xxminhmie.sgu.javagui.gui.ApplicationGUI;

public class AbstractPanel{
	
	/******************************************************************
	 * SETTING WIDTH, HEIGHT, COLOR OF PANEL
	 ******************************************************************/
	public static final int PanelWidth = 1040;
	public static final int PanelHeight = ApplicationGUI.FrameHeight;
	public static final Color PanelBg = Color.WHITE;
	public static final Color DocumentListener = new Color(161, 161, 161);
	
	
	public AbstractPanel() {
//		this.setPreferredSize(new Dimension(PanelWidth, PanelHeight));
//		this.setBackground(PanelBg);
	}


}
