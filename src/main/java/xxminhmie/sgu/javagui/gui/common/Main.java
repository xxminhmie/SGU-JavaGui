package xxminhmie.sgu.javagui.gui.common;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//copy file and move to specified folder
//cp ~/Desktop/Image/Titan/eren.jpg ~/Desktop/Image

public class Main {
	private JFrame j;
	private JMenu jmenu;
	private JMenuBar jbar;
	private JMenuItem jmi, jexit;
	private JPanel jpanel, jpanelbar;
	private JButton jpre, jnext;
	JLabel image;
	ImageIcon ic;
	Image img;

	Main() {
		j = new JFrame("Image Viewer");
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setLocationByPlatform(true);
		j.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		jpanel = new JPanel();
		jpanel.setLayout(new BorderLayout());
		image = new JLabel(" ");
		jpanel.add(image, BorderLayout.CENTER);

		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = c.gridy = 0;
		c.gridwidth = 2;
		// c.weightx=0.1;
		c.weighty = 0.1;
		c.ipady = 600;
		c.insets = new Insets(5, 5, 10, 5);
		// jpanel.setBackground(Color.BLACK);
		j.add(jpanel, c);

		jpanelbar = new JPanel();
		jpanelbar.setLayout(new GridBagLayout());
		jpanelbar.setBackground(Color.red);

		GridBagConstraints x = new GridBagConstraints();
		jpre = new JButton("Previous");
		x.gridx = 0;
		x.gridy = 0;
		x.gridwidth = 1;
		x.weightx = 0.1;
		// x.insets=new Insets(5,5,5,5);
		// x.fill=GridBagConstraints.NONE;
		jpanelbar.add(jpre, x);

		jnext = new JButton("Next");
		x.gridx = 1;
		jpanelbar.add(jnext, x);

		c.weightx = 0.1;
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 5);
		c.ipady = 150;
		j.add(jpanelbar, c);

		// Creating Menu
		jbar = new JMenuBar();
		jmenu = new JMenu("File");
		jmi = new JMenuItem("Open");
		JButton btn = new JButton("Click me");
		jmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fc = new JFileChooser();
				int result = fc.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					System.out.print(file.getName());
					try {
						image.setIcon(new ImageIcon(ImageIO.read(file)));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fc = new JFileChooser();
				int result = fc.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					System.out.print(file.getName());
					final Runtime rt = Runtime.getRuntime();
					try {
						rt.exec("cp ~/Desktop/Image/Titan/eren.jpg ~/Desktop/Image");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						image.setIcon(new ImageIcon(ImageIO.read(file)));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		jexit = new JMenuItem("Exit");
		jexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});
		jmenu.add(jmi);
		jmenu.add(jexit);
		jbar.add(jmenu);
		j.setJMenuBar(jbar);
		j.add(btn);

//      j.setSize(800, 600);
		j.pack();
		j.setResizable(true);
		j.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main();
			}
		});
	}
}