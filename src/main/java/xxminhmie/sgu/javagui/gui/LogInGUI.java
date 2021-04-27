package xxminhmie.sgu.javagui.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import xxminhmie.sgu.javagui.model.AccountModel;
import xxminhmie.sgu.javagui.service.impl.AccountService;

public class LogInGUI extends JFrame {

	public static AccountModel ACCOUNT_LOGIN = new AccountModel();
	AccountService service = new AccountService();
	Color h1Color;
	Color fieldBColor;
	Color fieldFColor;
	Color checkboxFColor;
	Color rememberColor;
	Color buttonBColor;
	Color buttonFColor;
	Color buttonHoverBColor;

	JLabel label;
	JTextField userText;
	JPasswordField passwordText;
	JCheckBox checkBox;

	public LogInGUI() {
		this.h1Color = new Color(62, 54, 104);
		this.fieldBColor = new Color(230, 230, 230);
		this.fieldFColor = new Color(144, 142, 155);
		this.checkboxFColor = new Color(148, 148, 148);
		this.buttonBColor = new Color(128, 128, 253);
		this.buttonFColor = new Color(237, 240, 255);
		this.createPanel();
	}

	public void createPanel() {
//		this.setSize(1200, 700);
		this.setSize(400, 400);
//		this.setBounds(400, 140, 400, 400);
		this.setLayout(null);
		this.setLocationRelativeTo(null); // this will center your application
		this.setResizable(false);

		/*
		 * LABEL
		 */
		this.label = new JLabel("LOGIN", JLabel.CENTER);
		label.setSize(400, 80);
		label.setFont(new Font("Helvetica", Font.BOLD, 26));
		label.setForeground(this.h1Color);
		add(label);

		/*
		 * USER NAME TEXT FIELD
		 */
		this.userText = new JTextField(20);
		userText.setBounds(40, 100, 320, 50);
		userText.setBackground(this.fieldBColor);
		userText.setForeground(this.fieldFColor);
		userText.setBorder(BorderFactory.createCompoundBorder(userText.getBorder(),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		userText.requestFocus();
		add(userText);

		/*
		 * PASSWORD TEXT FIELD
		 */
		this.passwordText = new JPasswordField(20);
		passwordText.setBounds(40, 160, 320, 50);
		passwordText.setBackground(this.fieldBColor);
		passwordText.setForeground(this.fieldFColor);
		passwordText.setBorder(BorderFactory.createCompoundBorder(passwordText.getBorder(),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		add(passwordText);

		userText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					passwordText.requestFocus();
				}

			}
		});

		/*
		 * REMEMBER ME CHECK BOX
		 */
		this.checkBox = new JCheckBox("Rememeber Me");
		checkBox.setBounds(40, 210, 200, 30);
		checkBox.setForeground(this.checkboxFColor);
		checkBox.setFont(new Font("Helvetica", Font.PLAIN, 13));
		add(checkBox);

		/*
		 * FORGOT PASSWORD LABEL
		 */
		JLabel forgotLabel = new JLabel("Forgot?", JLabel.RIGHT);
		forgotLabel.setForeground(this.buttonBColor);
		forgotLabel.setFont(new Font("Helvetica", Font.PLAIN, 13));
		forgotLabel.setBounds(0, 210, 360, 30);
		add(forgotLabel);

		/*
		 * LOGIN BUTTON
		 */
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
		passwordText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginButton.doClick();
				}

			}
		});
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Clicked");
//				System.out.println(checkLogin());
				checkLogin();
			}

		});
	}

	public void checkLogin() {
		String username = userText.getText();
		String password = String.valueOf(passwordText.getPassword());

		if (username.isBlank()) {
			return;
		}
		if (password.isBlank()) {
			return;
		}

		List<AccountModel> list = service.findAll();
		Boolean flag = false;
		for (AccountModel e : list) {
			if (username.equals(e.getUsername()) && password.equals(e.getPassword())) {
				flag = true;
				ACCOUNT_LOGIN = e;
				ApplicationGUI app = new ApplicationGUI();
				app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				app.setVisible(true);
				this.dispose();
			}
		}
		if (flag == false) {
			JOptionPane.showMessageDialog(null, "Username or password is invalid!");

		}

	}

}
