package xxminhmie.sgu.javagui.gui.common;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;

public class AbstractButton extends JButton{
	String nameBtn = "";
	
	public AbstractButton(int x, int y, int w, int h) {
		this.setBounds(x, y, w, h);
		init();
	}

	public AbstractButton(int x, int y) {
		this.setBounds(x, y, 100, 20);
		init();
	}
	public void init() {
		setBackground(new Color(130, 130, 130));
		setOpaque(true);
		setBorderPainted(false);
		setForeground(Color.WHITE);
		
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				setBackground(new Color(53, 53, 53));
				setOpaque(true);
				setBorderPainted(false);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				setBackground(new Color(130, 130, 130));
				setOpaque(true);
				setBorderPainted(false);

			}
		});

	}
	public void setNameBtn(String nameBtn) {
		this.nameBtn = nameBtn;
		setText(this.nameBtn);
	}
	
	
}
