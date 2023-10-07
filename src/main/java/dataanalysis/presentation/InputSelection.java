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

	InputSelection() {
		super();

		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		File inputFolder = new File(System.getProperty("user.dir").concat("/data/inputs"));
		String[] inputIds = Arrays.stream(Objects.requireNonNull(inputFolder.listFiles())).map(File::getName).toArray(String[]::new);

		JComboBox<String> existingInputs = new JComboBox<>(inputIds);
		existingInputs.setMaximumSize(new Dimension(200, 10));
		add(existingInputs);
		existingInputs.setAlignmentX(Component.RIGHT_ALIGNMENT);

		customPlaintext = new CipherInputBox();
		add(customPlaintext);
		add(getImportFileButton(customPlaintext));
		add(new JButton("Add Input"));
	}

	private JButton getImportFileButton(CipherInputBox outputBox) {
		JButton b = new JButton("Import input from text file");

		b.addActionListener(e -> {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
			int returnVal = chooser.showOpenDialog(new JFrame());
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " +
						chooser.getSelectedFile().getName());
				try {
					Scanner reader = new Scanner(chooser.getSelectedFile());
					StringBuilder cipherInput = new StringBuilder();
					while (reader.hasNextLine()) {
						cipherInput.append(reader.nextLine()).append("\n");
					}
					outputBox.getTextArea().setText(cipherInput.toString());
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
}