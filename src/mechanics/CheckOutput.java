package mechanics;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CheckOutput extends JPanel{

	public CheckOutput(Piece king, Piece assassin ) {
		InputStream input = getClass().getResourceAsStream("download.png");
		JLabel label = null;
		try {
			label = new JLabel("", (Icon) ImageIO.read(input), JLabel.CENTER);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.add(label);
		// TODO Auto-generated constructor stub
	}

}