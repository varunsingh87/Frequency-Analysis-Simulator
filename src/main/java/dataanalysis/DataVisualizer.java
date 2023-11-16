package dataanalysis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataVisualizer extends JPanel {
	private GraphPanel g;
	private JLabel trialIdLabel;

	private int setCount;
	private char trialCount;

	public DataVisualizer() {
		super(new BorderLayout());
		DataPopulater.main(new String[]{"all"});
		setCount = 1;
		trialCount = 'A';
		File dataFile = new File("data/outputs/HARSHTRAITSHINEIMPORT/ioc_kerckhoff/1A.csv");
		List<Double> averages = new ArrayList<>();
		try (Scanner dataFileReader = new Scanner(dataFile)) {
			dataFileReader.nextLine(); // Skip header
			while (dataFileReader.hasNextLine()) {
				String s = dataFileReader.nextLine();
				String avg = s.substring(s.lastIndexOf(',') + 1);
				averages.add(Double.parseDouble(avg));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		g = new GraphPanel(averages);
		add(g, BorderLayout.CENTER);
		add(dataGroupControls(), BorderLayout.NORTH);
		trialIdLabel = new JLabel(setCount + "" + trialCount);
		trialIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(trialIdLabel, BorderLayout.SOUTH);
	}

	private void updateTrialIdLabel() {
		trialIdLabel.setText(setCount + "" + trialCount);
	}

	public void onNextClick(ActionEvent e) {
		trialCount += (char) 1;
		File dataFile = new File("data/outputs/HARSHTRAITSHINEIMPORT/ioc_kerckhoff/" + setCount + trialCount + ".csv");
		if (!dataFile.exists()) {
			dataFile = new File("data/outputs/HARSHTRAITSHINEIMPORT/ioc_kerckhoff/" + ++setCount + (trialCount = 'A') + ".csv");
			if (!dataFile.exists()) {
				System.out.println("[System Log] End of data.");
				return;
			}
		}
		updateTrialIdLabel();
		List<Double> averages = new ArrayList<>();
		try (Scanner dataFileReader = new Scanner(dataFile)) {
			dataFileReader.nextLine(); // Skip header
			while (dataFileReader.hasNextLine()) {
				String s = dataFileReader.nextLine();
				String avg = s.substring(s.lastIndexOf(',') + 1);
				averages.add(Double.parseDouble(avg));
			}
			g.setScores(averages);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private JPanel dataGroupControls() {
		JPanel p = new JPanel();

		JButton nextButton = new JButton("Next file");
		nextButton.addActionListener(this::onNextClick);
		p.add(nextButton);

		JComboBox<String> algorithms = new JComboBox<>();
		algorithms.addItem("friedman_kasiski");
		algorithms.addItem("friedman_kerckhoff");
		algorithms.addItem("ioc_kasiski");
		algorithms.addItem("ioc_kerckhoff");
		p.add(algorithms);

		return p;
	}

	private static void createAndShowGui() {
		JFrame t = new JFrame();
		t.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		t.setContentPane(new DataVisualizer());
		t.setVisible(true);
		t.setMinimumSize(new Dimension(500, 500));
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(DataVisualizer::createAndShowGui);
	}
}
