package xxminhmie.sgu.javagui.main;

import javax.swing.JFrame;

import xxminhmie.sgu.javagui.gui.LogInGUI;

public class Main {
	public static void main(String[] args) {
		JFrame login = new LogInGUI();


		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setVisible(true);

	}

}
