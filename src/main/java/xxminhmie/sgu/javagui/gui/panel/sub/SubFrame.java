package xxminhmie.sgu.javagui.gui.panel.sub;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SubFrame extends JFrame {
	public static final int SUBFRAME_W = 800;
	public static final int SUBFRAME_H = 600;

	
	public SubFrame(JPanel mainPanel) {
		getContentPane().add(mainPanel);
		setSize(new Dimension(SUBFRAME_W,SUBFRAME_H));
		setLocationRelativeTo(null); // this will center your application
	}

}
