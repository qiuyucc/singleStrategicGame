/*
 * SignUpForm, shown if user selects Sign Up from LoginForm
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class SignUpForm extends JFrame implements ActionListener {
	
	private JFrame frame = new JFrame();
	private JLabel userLabel=new JLabel("USERNAME");
	private JLabel passwordLabel=new JLabel("PASSWORD");
	private JLabel confirmPasswordLabel=new JLabel("CONFIRM PASSWORD");
	private JTextField usernameTextField=new JTextField();
	private JPasswordField passwordField=new JPasswordField();
	private JPasswordField confirmPasswordField=new JPasswordField();
	private JButton registerButton=new JButton("REGISTER");
	private JButton resetButton=new JButton("RESET");
	private JButton backButton = new JButton("Back");
	
	public SignUpForm() {
		createWindow();
		setLocationAndSize();
		addComponentsToFrame();
		actionEvent();
	}
	    
	private void createWindow() {
		frame.setTitle("Misfit Registration");
		frame.setBounds(10,10,370,600);
		//frame.getContentPane().setBackground(Color.pink);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}
	
	private void setLocationAndSize() {
		userLabel.setBounds(20,150,100,30);
		passwordLabel.setBounds(20,220,100,30);
		confirmPasswordLabel.setBounds(20,290,140,30);
		usernameTextField.setBounds(170,150,150,30);
		passwordField.setBounds(170,220,150,30);
		confirmPasswordField.setBounds(170,290,150,30); 
		registerButton.setBounds(50,350,100,30);
		backButton.setBounds(200,350,100,30);
		resetButton.setBounds(200,500,100,30);
	}
	
	private void addComponentsToFrame() {
		frame.add(userLabel);
		frame.add(passwordLabel);
		frame.add(confirmPasswordLabel);
		frame.add(usernameTextField);
		frame.add(passwordField);
		frame.add(confirmPasswordField);
		frame.add(registerButton);
		frame.add(backButton);
		frame.add(resetButton);
	}
	
	public void close() {
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
	
	public void actionEvent() {
		registerButton.addActionListener(this);
		resetButton.addActionListener(this);
		backButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {
			frame.dispose();
			new LoginForm();	
		}
		if (e.getSource() == resetButton) {
			usernameTextField.setText("");
			passwordField.setText("");
			confirmPasswordField.setText("");			
		}
		if (e.getSource() == registerButton) {
			if(usernameTextField.getText().equals("")
					||passwordField.getPassword().equals("")
					||confirmPasswordField.getPassword().equals("")) {
				JOptionPane.showMessageDialog(this,  "Fields will not be blank");
			} else {   
				if (String.valueOf(passwordField.getPassword()).equalsIgnoreCase(String.valueOf(confirmPasswordField.getPassword()))) {   
					Main.admin.add(new StandardUser(usernameTextField.getText(),String.valueOf(passwordField.getPassword())));
					JOptionPane.showMessageDialog(this,"Successfully Registered");
				} else {
					JOptionPane.showMessageDialog(this,"Password doesn't match, please try again!");
				}
			}
		}
	}
}
