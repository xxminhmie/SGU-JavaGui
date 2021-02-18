package xxminhmie.sgu.javagui.gui.common;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;

public class DeleteButton extends JButton{
	public DeleteButton() {
		this.setText("Delete");
		this.setBackground(new Color(178,129,131));
		this.setSize(new Dimension(100,40));
//		this.setBounds(100, 100, 100, 40);
		this.setOpaque(true);
		this.setBorderPainted(false);
		this.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        setBackground(new Color(173,41,41));
		        setOpaque(true);
				setBorderPainted(false);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
				setBackground(new Color(178,129,131));
				setOpaque(true);
				setBorderPainted(false);
		    
		    }});


	}

}
