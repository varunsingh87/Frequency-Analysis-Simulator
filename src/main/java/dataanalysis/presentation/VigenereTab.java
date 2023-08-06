package dataanalysis.presentation;

import javax.swing.*;
import java.awt.*;

public class VigenereTab extends JPanel {
	VigenereTab() {
		super();
		this.setLayout(new BorderLayout());

		this.add(parameterSelection(), BorderLayout.WEST);
		this.add(submission(), BorderLayout.SOUTH);
	}

	JComponent parameterSelection() {
		DefaultListModel<String> params = new DefaultListModel<>();
		params.addElement("Key");
		params.addElement("Caesar Decryption Algorithm");
		params.addElement("Key Length Calculation Algorithm");

		JList<String> paramSelection = new JList<>(params);
		paramSelection.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		paramSelection.setLayoutOrientation(JList.VERTICAL);
		paramSelection.setVisibleRowCount(-1);

		return paramSelection;
	}

	JComponent submission() {
		JPanel submission = new JPanel();

		JButton collect = new JButton("Collect Data");
		submission.add(collect);

		JLabel outputDir = new JLabel("Outputting to /data/outputs");
		submission.add(outputDir);

		return submission;
	}
}