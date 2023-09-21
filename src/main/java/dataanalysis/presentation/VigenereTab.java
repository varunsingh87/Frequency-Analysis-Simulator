package dataanalysis.presentation;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import frequencyanalysissimulator.crypto.KeyLengthMethod;
import frequencyanalysissimulator.crypto.CaesarDecryptionMethod;

import coreui.CipherInputBox;

public class VigenereTab extends JPanel {
	VigenereTab() {
		super();
		this.setLayout(new BorderLayout());
		JPanel parametersContainer = new JPanel();
		parametersContainer.add(parameterSelection());
		this.add(parametersContainer, BorderLayout.WEST);
		this.add(submission(), BorderLayout.SOUTH);
		this.add(inputs(), BorderLayout.EAST);
	}

	JComponent parameterSelection() {
		final String[] KEY_LENGTH_ALGORITHMS = fullNamesFromEnums(KeyLengthMethod.values());
		final String[] CAESAR_DECRYPTION_ALGORITHMS = fullNamesFromEnums(CaesarDecryptionMethod.values());

		JPanel parametersSelection = new JPanel();
		parametersSelection.setLayout(new BoxLayout(parametersSelection, BoxLayout.Y_AXIS));
		parametersSelection.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLUE, Color.BLACK));

		JPanel step1 = parameterContainer("Key Length Algorithm (Step 1)", KEY_LENGTH_ALGORITHMS);
		step1.setAlignmentX(LEFT_ALIGNMENT);
		JPanel step2 = parameterContainer("Caesar Decryption Algorithm (Step 2)", CAESAR_DECRYPTION_ALGORITHMS);
		step2.setAlignmentX(LEFT_ALIGNMENT);
		parametersSelection.add(step1);
		parametersSelection.add(step2);
		return parametersSelection;
	}

	private <T extends Enum<T>> String[] fullNamesFromEnums(T[] methods) {
		return Arrays.stream(methods).map(T::toString).toArray(String[]::new);
	}

	JPanel parameterContainer(String label, String... choices) {
		JPanel parameterContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		parameterContainer.add(new JLabel(label));
		JComboBox<String> params = new JComboBox<>();
		for (String choice : choices) {
			params.addItem(choice);
		}
		parameterContainer.add(params);
		parameterContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
		return parameterContainer;
	}

	JComponent submission() {
		JPanel submission = new JPanel();

		JButton collect = new JButton("Collect Data");
		submission.add(collect);

		JLabel outputDir = new JLabel("Outputting to /data/outputs");
		submission.add(outputDir);

		return submission;
	}

	JComponent inputs() {
		JPanel inputControl = new JPanel();
		inputControl.setLayout(new BoxLayout(inputControl, BoxLayout.PAGE_AXIS));

		File inputFolder = new File(System.getProperty("user.dir").concat("/data/inputs"));
		String[] inputIds = Arrays.stream(Objects.requireNonNull(inputFolder.listFiles())).map(File::getName).toArray(String[]::new);

		JComboBox<String> existingInputs = new JComboBox<>(inputIds);
		inputControl.add(existingInputs);

		inputControl.add(new JButton("Add Input"));
		inputControl.add(new CipherInputBox());

		return inputControl;
	}
}