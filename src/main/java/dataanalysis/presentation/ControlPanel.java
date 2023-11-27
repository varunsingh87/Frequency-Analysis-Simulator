package dataanalysis.presentation;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JFrame {

	ControlPanel() {
		super("Frequency Analysis Simulator Control Panel");
		setMinimumSize(new Dimension(750, 600));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setContentPane(new VigenereTab());
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(ControlPanel::new);
	}
}