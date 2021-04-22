package xxminhmie.sgu.javagui.image;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class MyImagePanel extends JPanel{
	private BufferedImage image;
	
	int width;
	int height;
	
	public MyImagePanel() {
		this.width = 160;
		this.height = 160;
	}
	/*
	 * This constructor for account panel class
	 */
	public MyImagePanel(int width, int height) {
		this.width = width;
		this.height = height;
				
	}

	void setImage(BufferedImage image) {
		this.image = resize(image,this.width,this.height);
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0, null);
		}
	}
	
	public void loadAndShowImage(String path) {
		try {
			BufferedImage image = ImageIO.read(new File("./src/main/java/xxminhmie/sgu/javagui/image/"+path));
			this.setImage(image);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
	
	
}
