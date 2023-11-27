package dataanalysis.presentation;

import frequencyanalysissimulator.crypto.CaesarDecryptionMethod;
import frequencyanalysissimulator.crypto.DecryptionSettings;
import frequencyanalysissimulator.crypto.KeyLengthMethod;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

class DecryptionParameters extends JPanel {
	private final DecryptionSettings argumentValues;
	private final JTextField key;
	private final Font bodyFont;

	DecryptionParameters() {
		super();

		bodyFont = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
		// Set default methods to most reliable/accurate because the enum values are ordered in this way
		argumentValues = new DecryptionSettings(KeyLengthMethod.IOC, CaesarDecryptionMethod.KERCKHOFF);

		setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 10));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		key = new JTextField(25);

		add(createAside("Set the algorithms that will be used to decrypt the ciphertexts"));
		add(keyLengthAlgorithmParameterContainer());
		add(caesarDecryptionAlgorithmParameterContainer());
		add(createAside("Set the key whose subsequences will be used to encrypt your message"));
		add(keyContainer());
	}

	private JLabel createAside(String msg) {
		JLabel label = new JLabel(msg);
		label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		return label;
	}

	private JPanel keyLengthAlgorithmParameterContainer() {
		JPanel parameterContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("Key Length Algorithm (Step 1)");
		label.setFont(bodyFont);
		parameterContainer.add(label);
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

		parameterContainer.setMaximumSize(parameterContainer.getPreferredSize());
		return parameterContainer;
	}

	private JPanel caesarDecryptionAlgorithmParameterContainer() {
		JPanel parameterContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("Caesar Decryption Algorithm (Step 2)");
		label.setFont(bodyFont);
		parameterContainer.add(label);
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

		parameterContainer.setMaximumSize(parameterContainer.getPreferredSize());
		return parameterContainer;
	}

	private JPanel keyContainer() {
		JPanel container = new JPanel();
		container.add(new JLabel("Key"));
		container.add(key);

		return container;
	}

	DecryptionSettings getParameterInfo() {
		return argumentValues;
	}

	String getKey() {
		return key.getText();
	}
}