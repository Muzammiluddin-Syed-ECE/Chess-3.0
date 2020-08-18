package mechanics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UserOutputs {
	JPanel output;
	String message;
	public UserOutputs (JPanel output, String message) {
		this.output = output;
		this.message = message;
	}
	public void display() {
		JOptionPane.showMessageDialog(null, output);
	}
	
}