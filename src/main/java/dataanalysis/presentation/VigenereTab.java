package dataanalysis.presentation;

import dataanalysis.DataCollector;
import dataanalysis.DataFileReader;
import frequencyanalysissimulator.crypto.KeyLengthMethod;
import frequencyanalysissimulator.crypto.VigenereDecryption;

import javax.swing.*;
import java.awt.*;

public class VigenereTab extends JPanel {
	private final DecryptionParameters decryptParamsMenu;
	private final InputSelection inputInfoMenu;

	VigenereTab() {
		super(createBorderLayout());
		decryptParamsMenu = new DecryptionParameters();
		this.add(decryptParamsMenu, BorderLayout.WEST);

		inputInfoMenu = new InputSelection();
		this.add(inputInfoMenu, BorderLayout.CENTER);

		this.add(submission(), BorderLayout.SOUTH);
	}

	private static BorderLayout createBorderLayout() {
		BorderLayout bl = new BorderLayout();
		bl.setVgap(30);
		bl.setHgap(30);
		return bl;
	}

	JComponent submission() {
		JPanel submission = new JPanel();

		JButton collect = new JButton("Collect Data");
		submission.add(collect);

		JLabel outputDir = new JLabel("Outputting to /data/outputs");
		submission.add(outputDir);

		collect.addActionListener(e -> {
			new VigenereDecryption(inputInfoMenu.getPlaintext());
			DataCollector.main(new String[]{inputInfoMenu.getPlaintext(), "Custom", decryptParamsMenu.getParameterInfo().getKeyLengthMethod().name(), decryptParamsMenu.getParameterInfo().getCaesarDecryptionMethodChoice().name(), decryptParamsMenu.getKey()});
		});

		return submission;
	}
}