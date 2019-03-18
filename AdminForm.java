/*
 * AdminForm class, displayed if user logs in as admin
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class AdminForm extends JFrame implements ActionListener{
	//change unit is not working currently
	JFrame frame = new JFrame();
	String colums [] = {"Username", "Password", "Highest Score"};
	DefaultTableModel model = new DefaultTableModel();
	JTable table = new JTable();
	JButton deleteButton = new JButton("Delete");
	JButton backButton = new JButton("Back");
	JButton updateButton = new JButton("Update");
	JLabel timeunitLabel = new JLabel("Time Unit");
	JTextField timeunitTextField=new JTextField();
	
	public AdminForm() {
		createWindow();
		setLocationAndSize();
		addComponentsToFrame();
		actionEvent();
		addRowToJTable();
	}
	
	public void createWindow() {
		frame.setTitle("Misfit Admin");
		frame.setBounds(10,10,370,600);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);	
	}
	
	public void setLocationAndSize() {
		table.setBounds(20,40,300,240);
		backButton.setBounds(200,300,100,30);
		deleteButton.setBounds(80, 300, 100, 30);
		timeunitLabel.setBounds(80,400,100,30);
		timeunitTextField.setBounds(170,400,150,30);
		updateButton.setBounds(200,460,100,30);
	}
       
	public void addComponentsToFrame() {
		frame.add(table);
		frame.add(backButton);
		frame.add(deleteButton);
		frame.add(timeunitLabel);
		frame.add(timeunitTextField);
		frame.add(updateButton);
	}
	
	public void addRowToJTable() {
		model.setColumnIdentifiers(colums);
		table.setModel(model);
		Object rowData[] = new Object[3];
		for (int i = 0; i < Main.admin.getUserList().size(); i++) {
			rowData[0] = Main.admin.getUserList().get(i).getUsername();
			rowData[1] = Main.admin.getUserList().get(i).getPassword();
			rowData[2] = Main.admin.getUserList().get(i).getHighScore();
			model.addRow(rowData);	
		}
	}
	
	public void actionEvent() {
		backButton.addActionListener(this);
		deleteButton.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {
			frame.dispose();
			new LoginForm();
		}
		if (e.getSource() == deleteButton) {
			try {
				int SelectedRowIndex = table.getSelectedRow();
				model.removeRow(SelectedRowIndex);
				Main.admin.getUserList().remove(SelectedRowIndex);
				JOptionPane.showMessageDialog(this, "The user has been deleted!");
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null,ex);
			}
		}
	}

}
