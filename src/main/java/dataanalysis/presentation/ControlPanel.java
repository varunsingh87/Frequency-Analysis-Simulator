package dataanalysis.presentation;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JFrame {

	ControlPanel() {
		super();
		setMinimumSize(new Dimension(750, 600));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setContentPane(layoutCipherTabs());
		pack();
		setVisible(true);
	}

	private static JTabbedPane layoutCipherTabs() {
		JTabbedPane jtp = new JTabbedPane();
		jtp.addTab("Vigenere", new ImageIcon(System.getProperty("user.dir").concat("/assets/encrypt.png")), new VigenereTab(), "Analyze decryption of a Vigenere cipher");
		jtp.addTab("Simple Substitution", new ImageIcon(System.getProperty("user.dir").concat("/assets/decrypt.png")), new JPanel(), "Analyze decryption of a simple substitution cipher");
		return jtp;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(ControlPanel::new);
	}
}