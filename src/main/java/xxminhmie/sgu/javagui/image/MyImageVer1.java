package xxminhmie.sgu.javagui.image;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/*
 * 	String path = "./src/main/java/xxminhmie/sgu/javagui/image/1.png";
 */
public class MyImageVer1 extends JLabel{
	
	StringBuilder path = new StringBuilder("./src/main/java/xxminhmie/sgu/javagui/image/");
	BufferedImage bufferedImage = null;
	Image dimg;
	ImageIcon imageIcon;
	int width = 160;
	int height = 160;
	
	public MyImageVer1(ImageIcon icon) {
        super();
        this.imageIcon = icon;
	}
	public MyImageVer1(String path) {
		this.path.append(path);
		System.out.println("path: "+this.path.toString());
		this.loadImage();
		repaint();
	}
	public MyImageVer1(String path, int width, int height) {
		this.path.append(path);
		this.width = width;
		this.height = height;
		this.loadImage();
	}
	public void loadImage() {
		try {
			bufferedImage = ImageIO.read(new File(path.toString()));
			dimg = bufferedImage.getScaledInstance(this.width, this.height, Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(dimg);
			
			this.setIcon(imageIcon);
			
		} catch (IOException e) {
			System.out.println("MyImage Exception: "+e.getMessage());
		}
	}
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
	
	

	
}