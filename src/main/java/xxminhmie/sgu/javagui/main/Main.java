package xxminhmie.sgu.javagui.main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import xxminhmie.sgu.javagui.gui.ApplicationGUI;
import xxminhmie.sgu.javagui.gui.LogInGUI;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					JFrame login = new LogInGUI();
					ApplicationGUI app = new ApplicationGUI();

					app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					app.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
