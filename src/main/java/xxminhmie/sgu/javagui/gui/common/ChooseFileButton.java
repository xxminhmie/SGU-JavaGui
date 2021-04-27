package xxminhmie.sgu.javagui.gui.common;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class ChooseFileButton extends JButton {
	String imagePath = "";
	int result;
	public ChooseFileButton() {
		this.setText("Choose File");
		this.setBackground(new Color(130, 130, 130));
//		this.setBounds(100, 100, 100, 40);
		this.setOpaque(true);
		this.setBorderPainted(false);
		this.setForeground(Color.WHITE);
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
		
//		this.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				chooseFileButtonHandle();
//
//			}
//		});
		
	}

	public String getImagePath() {
		return this.imagePath;
	}

	
}
