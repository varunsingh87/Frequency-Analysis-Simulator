package dataanalysis.presentation;

import dataanalysis.DataCollector;
import dataanalysis.DataFileReader;
import dataanalysis.DataVisualizer;
import frequencyanalysissimulator.crypto.CaesarDecryptionMethod;
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
			KeyLengthMethod chosenStep1Method = decryptParamsMenu.getParameterInfo().getKeyLengthMethod();
			CaesarDecryptionMethod chosenStep2Method = decryptParamsMenu.getParameterInfo().getCaesarDecryptionMethodChoice();
			collect(chosenStep1Method, chosenStep2Method);
		});

		return submission;
	}

	/**
	 * Collects data on the current input with the given methods, or recursively on all methods if specified
	 *
	 * @param keyLengthMethod        The algorithm that is used to infer the length of the key, or ALL
	 * @param caesarDecryptionMethod The algorithm that is used to decrypt the Caesar ciphers, or All
	 */
	private void collect(KeyLengthMethod keyLengthMethod, CaesarDecryptionMethod caesarDecryptionMethod) {
		// Specific method for both steps (stopping case)
		if (caesarDecryptionMethod != CaesarDecryptionMethod.ALL && keyLengthMethod != KeyLengthMethod.ALL) {
			new Thread(() -> {
				DataCollector.main(new String[]{
						inputInfoMenu.getPlaintext(),
						inputInfoMenu.getPlaintextId(),
						keyLengthMethod.name(),
						caesarDecryptionMethod.name(),
						decryptParamsMenu.getKey()
				});
			}).start();
		} else if (keyLengthMethod == KeyLengthMethod.ALL) { // All key length methods
			for (KeyLengthMethod method : KeyLengthMethod.values()) {
				if (method != KeyLengthMethod.ALL) {
					collect(method, caesarDecryptionMethod);
				}
			}
		} else { // All caesar decryption methods
			for (CaesarDecryptionMethod method : CaesarDecryptionMethod.values()) {
				if (method != CaesarDecryptionMethod.ALL) {
					collect(keyLengthMethod, method);
				}
			}
		}
	}
}