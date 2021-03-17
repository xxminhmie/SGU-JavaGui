package xxminhmie.sgu.javagui.gui.common;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;

public class ResetButton extends JButton{
	public ResetButton() {
		this.setText("Reset");
		this.setBackground(new Color(130,130,130));
		this.setSize(new Dimension(100,40));
//		this.setBounds(100, 100, 100, 40);
		this.setOpaque(true);
		this.setBorderPainted(false);
		this.setForeground(Color.WHITE);
		this.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        setBackground(new Color(53,53,53));
		        setOpaque(true);
				setBorderPainted(false);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
				setBackground(new Color(130,130,130));
				setOpaque(true);
				setBorderPainted(false);
		    
		    }});


	}
}
