package dataanalysis.presentation;

import core.CipherInputBox;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

class InputSelection extends JPanel {
	private final CipherInputBox customPlaintext;
	private String plaintextId;

	private final static int HORIZONTAL_PADDING = 15;
	private final static int VERTICAL_PADDING = 10;

	InputSelection() {
		super();

		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createBevelBorder(BevelBorder.RAISED),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)
		));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		customPlaintext = new CipherInputBox();
		add(customPlaintext);
		add(getImportFileButton(customPlaintext));
	}

	private JButton getImportFileButton(CipherInputBox outputBox) {
		JButton b = new JButton("Import input from text file");

		b.addActionListener(e -> {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
			int returnVal = chooser.showOpenDialog(new JFrame());
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				plaintextId = chooser.getSelectedFile().getName();
				System.out.println("You chose to open this file: " + plaintextId);
				try {
					Scanner reader = new Scanner(chooser.getSelectedFile());
					StringBuilder cipherInput = new StringBuilder();
					while (reader.hasNextLine()) {
						cipherInput.append(reader.nextLine()).append("\n");
					}
					outputBox.getTextArea().setText(cipherInput.toString());
					// Remove extension for identifier
					plaintextId = plaintextId.substring(0, plaintextId.lastIndexOf("."));
				} catch (FileNotFoundException ex) {
					System.out.println("File was not found");
				}
			}
		});

		return b;
	}

	public String getPlaintext() {
		return customPlaintext.getTextArea().getText();
	}

	String getPlaintextId() {
		return plaintextId;
	}
}