package dataanalysis.presentation;

import frequencyanalysissimulator.crypto.CaesarDecryptionMethod;
import frequencyanalysissimulator.crypto.DecryptionSettings;
import frequencyanalysissimulator.crypto.KeyLengthMethod;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ItemEvent;

class DecryptionParameters extends JPanel {
	private final DecryptionSettings argumentValues;
	private final JTextField key;

	DecryptionParameters() {
		super();

		// Set default methods to most reliable/accurate because the enum values are ordered in this way
		argumentValues = new DecryptionSettings(KeyLengthMethod.values()[0], CaesarDecryptionMethod.values()[0]);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		key = new JTextField(25);

		JPanel parametersSelection = new JPanel();
		parametersSelection.setLayout(new BoxLayout(parametersSelection, BoxLayout.Y_AXIS));
		parametersSelection.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLUE, Color.BLACK));

		parametersSelection.add(keyLengthAlgorithmParameterContainer());
		parametersSelection.add(caesarDecryptionAlgorithmParameterContainer());

		add(parametersSelection);
		add(keyContainer());
	}

	private JPanel keyLengthAlgorithmParameterContainer() {
		JPanel parameterContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		parameterContainer.add(new JLabel("Key Length Algorithm (Step 1)"));
		JComboBox<KeyLengthMethod> params = new JComboBox<>();
		for (KeyLengthMethod choice : KeyLengthMethod.values()) {
			params.addItem(choice);
		}
		params.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED && e.getItem() instanceof KeyLengthMethod) {
				argumentValues.setKeyLengthMethod((KeyLengthMethod) e.getItem());
			}
		});
		parameterContainer.add(params);
		parameterContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
		return parameterContainer;
	}

	private JPanel caesarDecryptionAlgorithmParameterContainer() {
		JPanel parameterContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		parameterContainer.add(new JLabel("Caesar Decryption Algorithm (Step 2)"));
		JComboBox<CaesarDecryptionMethod> params = new JComboBox<>();
		for (CaesarDecryptionMethod choice : CaesarDecryptionMethod.values()) {
			params.addItem(choice);
		}
		params.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				argumentValues.setCaesarDecryptionMethod((CaesarDecryptionMethod) e.getItem());
			}
		});
		parameterContainer.add(params);
		parameterContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
		return parameterContainer;
	}

	private JPanel keyContainer() {
		JPanel container = new JPanel();
		container.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.GREEN, Color.BLACK));
		container.add(new JLabel("Key"));
		container.add(key);

		return container;
	}

	DecryptionSettings getParameterInfo() {
		return argumentValues;
	}
}