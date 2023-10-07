package dataanalysis.presentation;

import frequencyanalysissimulator.crypto.VigenereDecryption;

import javax.swing.*;
import java.awt.*;

public class VigenereTab extends JPanel {
	private final DecryptionParameters decryptParamsMenu;
	private final InputSelection inputInfoMenu;

	VigenereTab() {
		super(new BorderLayout());
		decryptParamsMenu = new DecryptionParameters();
		this.add(decryptParamsMenu, BorderLayout.WEST);

		inputInfoMenu = new InputSelection();
		this.add(inputInfoMenu, BorderLayout.CENTER);

		this.add(submission(), BorderLayout.SOUTH);
	}

	JComponent submission() {
		JPanel submission = new JPanel();

		JButton collect = new JButton("Collect Data");
		submission.add(collect);

		JLabel outputDir = new JLabel("Outputting to /data/outputs");
		submission.add(outputDir);

		collect.addActionListener(e -> {
			new VigenereDecryption(inputInfoMenu.getPlaintext());
		});

		return submission;
	}
}