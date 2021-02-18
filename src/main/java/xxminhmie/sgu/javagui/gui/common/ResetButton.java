package xxminhmie.sgu.javagui.gui.common;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;

public class ResetButton extends JButton{
	public ResetButton() {
		this.setText("Reset");
		this.setBackground(new Color(182,182,182));
		this.setSize(new Dimension(100,40));
//		this.setBounds(100, 100, 100, 40);
		this.setOpaque(true);
		this.setBorderPainted(false);
		this.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        setBackground(new Color(116,116,116));
		        setOpaque(true);
				setBorderPainted(false);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
				setBackground(new Color(182,182,182));
				setOpaque(true);
				setBorderPainted(false);
		    
		    }});


	}
}
