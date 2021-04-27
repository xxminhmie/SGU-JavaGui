package xxminhmie.sgu.javagui.gui.panel.sub;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import xxminhmie.sgu.javagui.gui.LogInGUI;
import xxminhmie.sgu.javagui.gui.common.AddButton;
import xxminhmie.sgu.javagui.model.AccountModel;
import xxminhmie.sgu.javagui.service.impl.AccountService;

public class Profile extends JPanel {
	JLabel profile;
	JLabel username;
	JLabel password;

	AddButton changePasswordBtn;
	SubFrame changeFrame;

	public Profile() {
		this.setLayout(null);
		init();
	}

	public void init() {
		profile = new JLabel("Profile");
		profile.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
		profile.setBounds(120, 20, 200, 40);
		this.add(profile);

		username = new JLabel("Username: " + LogInGUI.ACCOUNT_LOGIN.getUsername());
		username.setBounds(20, 100, 200, 20);
		this.add(username);

		password = new JLabel("Password: " + "********");
		password.setBounds(20, 130, 200, 20);
		this.add(password);

		changePasswordBtn = new AddButton(20, 150, 120, 20);
		changePasswordBtn.setNameBtn("Set password");
		changePasswordBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));
		this.add(this.changePasswordBtn);
		changePasswordBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changePasswordButtonHandle();
			}
		});
	}

	/*
	 * 
	 */
	public void changePasswordButtonHandle() {
		ChangePassword panel = new ChangePassword();
		changeFrame = new SubFrame(panel);

		panel.loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//					panel.checkLogin();
				String cur = "";
				if (!String.valueOf(panel.current.getPassword()).isBlank()) {
					cur = String.valueOf(panel.current.getPassword());
				} else {
					JOptionPane.showMessageDialog(null, "This field must not be null or empty!");
					panel.current.requestFocus();
					return;
				}

				String newPass = "";
				if (!String.valueOf(panel.passwordText.getPassword()).isBlank()) {
					newPass = String.valueOf(panel.passwordText.getPassword());
				} else {
					JOptionPane.showMessageDialog(null, "This field must not be null or empty!");
					panel.passwordText.requestFocus();
					return;
				}

				String confirm = "";
				if (!String.valueOf(panel.confirm.getPassword()).isBlank()) {
					confirm = String.valueOf(panel.confirm.getPassword());
				} else {
					JOptionPane.showMessageDialog(null, "This field must not be null or empty!");
					panel.confirm.requestFocus();
					return;
				}
					AccountModel model = new AccountModel();
					AccountService service = new AccountService();
					model = service.findOne(LogInGUI.ACCOUNT_LOGIN.getId());
				if (model != null) {
					if (cur.equals(model.getPassword()) == false) {
						JOptionPane.showMessageDialog(null, "Current password not match!");
						panel.current.requestFocus();
						return;
					}
					if (newPass.equals(confirm) == false) {
						JOptionPane.showMessageDialog(null, "Confirm password failed!");
						panel.passwordText.requestFocus();
						return;
					}

					model.setPassword(newPass);
					AccountModel ac = service.update(model);
					if (ac != null) {
						JOptionPane.showMessageDialog(null, "Save successfully!");
							changeFrame.dispose();
					}
				}
			}
		});

		changeFrame.setSize(new Dimension(400, 450));
		changeFrame.setLocationRelativeTo(null); // this will center your application

		changeFrame.setVisible(true);

	}

}
