package GUI.Form;


import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInGUI extends JPanel {
	
	Color h1Color;
	Color fieldBColor;
	Color fieldFColor;
	Color checkboxFColor;
	Color rememberColor;
	Color buttonBColor;
	Color buttonFColor;
	Color buttonHoverBColor;
	
	public LogInGUI(){
		this.h1Color = new Color(62, 54, 104);
		this.fieldBColor = new Color(230, 230, 230);
		this.fieldFColor = new Color(144, 142, 155);
		this.checkboxFColor = new Color(148, 148, 148);
		this.buttonBColor = new Color(128, 128, 253);
		this.buttonFColor = new Color(237, 240, 255);
		
		this.createPanel();
	}
	public void createPanel() {
		this.setBounds(400, 140, 400, 400);
		this.setLayout(null);
		
		JLabel label = new JLabel("LOGIN", JLabel.CENTER);
		label.setSize(400,80);
		label.setFont(new Font("Helvetica", Font.BOLD, 26));
		label.setForeground(this.h1Color);
		add(label);
		
		
		JTextField userText = new JTextField(20);
		userText.setBounds(40,100,320,50);
		userText.setBackground(this.fieldBColor);
		userText.setForeground(this.fieldFColor);
		userText.setBorder(BorderFactory.createCompoundBorder(
		        userText.getBorder(), 
		        BorderFactory.createEmptyBorder(5,10,5,10)));
		add(userText);
		
		
		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(40, 160, 320, 50);
		passwordText.setBackground(this.fieldBColor);
		passwordText.setForeground(this.fieldFColor);
		passwordText.setBorder(BorderFactory.createCompoundBorder(
		        passwordText.getBorder(), 
		        BorderFactory.createEmptyBorder(5,10,5,10)));
		add(passwordText);
		
		
		JCheckBox checkBox = new JCheckBox("Rememeber Me");
		checkBox.setBounds(40, 210, 200, 30);
		checkBox.setForeground(this.checkboxFColor);
		checkBox.setFont(new Font("Helvetica", Font.PLAIN, 13));
		add(checkBox);
		
		JLabel forgotLabel = new JLabel("Forgot?",JLabel.RIGHT);
		forgotLabel.setForeground(this.buttonBColor);
		forgotLabel.setFont(new Font("Helvetica", Font.PLAIN, 13));
		forgotLabel.setBounds(0,210,360,30);
		add(forgotLabel);

		JButton loginButton = new JButton("Login");
		loginButton.setBounds(40, 250, 320, 50);
		loginButton.setBackground(this.buttonBColor);
		loginButton.setForeground(this.buttonFColor);
		loginButton.setOpaque(true);
		loginButton.setBorderPainted(false);

		this.add(loginButton);
		
		loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        loginButton.setBackground(new Color(103, 103, 207));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        loginButton.setBackground(new Color(128, 128, 253));
		    }
		});
		
		forgotLabel.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	forgotLabel.setText("<HTML><U>Forgot?</U></HTML>");
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	forgotLabel.setText("Forgot?");
		    }
		});
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(1200, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); // this will center your application
		
		frame.setLayout(null);

		JPanel login = new LogInGUI();
		frame.add(login);
		frame.setVisible(true);
		frame.setResizable(false);
		
	}
}
