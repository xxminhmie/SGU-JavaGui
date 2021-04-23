package xxminhmie.sgu.javagui.gui.panel.sub;

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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import xxminhmie.sgu.javagui.gui.ApplicationGUI;
import xxminhmie.sgu.javagui.model.AccountModel;
import xxminhmie.sgu.javagui.service.impl.AccountService;

public class ChangePassword extends JPanel {
	AccountService service = new AccountService();
	Color h1Color;
	Color fieldBColor;
	Color fieldFColor;
	Color checkboxFColor;
	Color rememberColor;
	Color buttonBColor;
	Color buttonFColor;
	Color buttonHoverBColor;

	public JLabel label;
	public JPasswordField current;
	public JPasswordField passwordText;
	public JPasswordField confirm;

	JCheckBox checkBox;

	public JButton loginButton;


	public ChangePassword() {

		this.h1Color = new Color(62, 54, 104);
		this.fieldBColor = new Color(230, 230, 230);
		this.fieldFColor = new Color(144, 142, 155);
		this.checkboxFColor = new Color(148, 148, 148);
		this.buttonBColor = new Color(128, 128, 253);
		this.buttonFColor = new Color(237, 240, 255);
		this.createPanel();
	}

	public void createPanel() {
		this.setSize(400, 450);
		this.setLayout(null);

		/*
		 * LABEL
		 */
		this.label = new JLabel("Change Password", JLabel.CENTER);
		label.setSize(400, 80);
		label.setFont(new Font("Helvetica", Font.BOLD, 26));
		label.setForeground(this.h1Color);
		add(label);

		/*
		 * Current Password
		 */
		JLabel curLabel = new JLabel("Current password: ");
		curLabel.setBounds(40, 80, 120, 20);
		curLabel.setFont(new Font("Helvetica", Font.PLAIN, 12));
		add(curLabel);

		this.current = new JPasswordField(20);
		current.setBounds(40, 100, 320, 50);
		current.setBackground(this.fieldBColor);
		current.setForeground(this.fieldFColor);
		current.setBorder(
				BorderFactory.createCompoundBorder(current.getBorder(), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		add(current);

		current.requestFocus();

		/*
		 * New Password
		 */
		JLabel newLabel = new JLabel("New password: ");
		newLabel.setBounds(40, 160, 120, 20);
		newLabel.setFont(new Font("Helvetica", Font.PLAIN, 12));
		add(newLabel);

		this.passwordText = new JPasswordField(20);
		passwordText.setBounds(40, 180, 320, 50);
		passwordText.setBackground(this.fieldBColor);
		passwordText.setForeground(this.fieldFColor);
		passwordText.setBorder(BorderFactory.createCompoundBorder(passwordText.getBorder(),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		add(passwordText);

		/*
		 * Confirm
		 */
		JLabel confirmLabel = new JLabel("Confirm new password: ");
		confirmLabel.setBounds(40, 230, 140, 20);
		confirmLabel.setFont(new Font("Helvetica", Font.PLAIN, 12));
		add(confirmLabel);

		this.confirm = new JPasswordField(20);
		confirm.setBounds(40, 250, 320, 50);
		confirm.setBackground(this.fieldBColor);
		confirm.setForeground(this.fieldFColor);
		confirm.setBorder(
				BorderFactory.createCompoundBorder(confirm.getBorder(), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		add(confirm);

		/*
		 * LOGIN BUTTON
		 */
		loginButton = new JButton("Save");
		loginButton.setBounds(40, 320, 320, 50);
		loginButton.setBackground(this.buttonBColor);
		loginButton.setForeground(this.buttonFColor);
		loginButton.setOpaque(true);
		loginButton.setBorderPainted(false);

		this.add(loginButton);

		/*
		 * Listener
		 */

		current.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					passwordText.requestFocus();
				}

			}
		});

		passwordText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					confirm.requestFocus();
				}

			}
		});

		confirm.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginButton.doClick();
				}

			}
		});

		loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				loginButton.setBackground(new Color(103, 103, 207));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				loginButton.setBackground(new Color(128, 128, 253));
			}
		});

//		loginButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				checkLogin();
//			}
//
//		});
	}

//	public void checkLogin() {
//		String cur = "";
//		if (!String.valueOf(current.getPassword()).isBlank()) {
//			cur = String.valueOf(current.getPassword());
//		} else {
//			JOptionPane.showMessageDialog(null, "This field must not be null or empty!");
//			current.requestFocus();
//			return;
//		}
//
//		String newPass = "";
//		if (!String.valueOf(passwordText.getPassword()).isBlank()) {
//			newPass = String.valueOf(passwordText.getPassword());
//		} else {
//			JOptionPane.showMessageDialog(null, "This field must not be null or empty!");
//			passwordText.requestFocus();
//			return;
//		}
//
//		String confirm = "";
//		if (!String.valueOf(this.confirm.getPassword()).isBlank()) {
//			confirm = String.valueOf(this.confirm.getPassword());
//		} else {
//			JOptionPane.showMessageDialog(null, "This field must not be null or empty!");
//			this.confirm.requestFocus();
//			return;
//		}
//		AccountService service = new AccountService();
//
//		AccountModel model = service.findOne(this.accountId);
//		if (model != null) {
//
//			if (cur.equals(model.getPassword()) == false) {
//				JOptionPane.showMessageDialog(null, "Current password not match!");
//				current.requestFocus();
//				return;
//			}
//			if (newPass.equals(confirm) == false) {
//				JOptionPane.showMessageDialog(null, "Confirm password failed!");
//				passwordText.requestFocus();
//				return;
//			}
//
//			model.setPassword(newPass);
//			AccountModel ac = service.update(model);
//			if(ac != null) {
//				JOptionPane.showMessageDialog(null, "Save successfully!");
//
//			}
//		}
//	}
}
