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

	public void chooseFileButtonHandle() {
		JFileChooser fc = new JFileChooser();
		File file = fc.getSelectedFile();
		System.out.print(file.getName());
		imagePath = file.getName();

		int result = fc.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			Runtime rt = Runtime.getRuntime();
			try {
				Process proc = rt.exec("cp /Users/lehokimminh/Downloads/Image/" + file.getName() + " "
						+ "/Users/lehokimminh/workspace/SGU-JavaGui/src/main/java/xxminhmie/sgu/javagui/image");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//			try {
//				image.setIcon(new ImageIcon(ImageIO.read(file)));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
	}
}
