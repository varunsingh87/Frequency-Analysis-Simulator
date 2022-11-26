package frequencyanalysissimulator.presentation.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import frequencyanalysissimulator.business.Vigenere;

public class Main {

	private JFrame frame;

	private int pWidth;
	private int pHeight;

	private JTextArea outputBox;
	private JTextArea inputBox;
	private JSpinner keyLength;
	private JLabel inputSize;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();

		frame.add(new JLabel("Varun Singh's Frequency Analysis Simulator", JLabel.CENTER), BorderLayout.NORTH);
		frame.add(generateInputContainer(), BorderLayout.WEST);

		JPanel executeContainer = new JPanel(new FlowLayout());
		JButton execute = new JButton("Begin operation");
		executeContainer.add(execute);
		frame.add(executeContainer, BorderLayout.SOUTH);
		frame.add(output(), BorderLayout.CENTER);

		execute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Vigenere cipherSolver = new Vigenere((int) keyLength.getValue(), inputBox.getText());
				outputBox.setText(cipherSolver.decrypt());
				inputSize.setText("Input Length: " + inputBox.getText().length());
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Font fasDefaultFont = new Font("Verdana", Font.BOLD, 24);
		Font fasNormalFont = new Font("Verdana", Font.BOLD, 16);
		UIManager.getLookAndFeelDefaults().put("Button.font", fasDefaultFont);
		UIManager.getLookAndFeelDefaults().put("RadioButton.font", fasNormalFont);
		UIManager.getLookAndFeelDefaults().put("Label.font", fasDefaultFont);
		UIManager.getLookAndFeelDefaults().put("Spinner.font", fasNormalFont);
		UIManager.getLookAndFeelDefaults().put("TextArea.font", fasNormalFont);

		outputBox = new JTextArea("Awaiting output...");
		outputBox.setEditable(false);

		frame = new JFrame();
		pHeight = 500;
		pWidth = 600;
		frame.setBounds(100, 100, pWidth, pHeight);
		frame.setMinimumSize(new Dimension(pWidth, pHeight));
		frame.setIconImage(new ImageIcon("assets/icon.png").getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(10, 10));
		frame.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
	}

	private JComponent output() {
		JPanel output = new JPanel();
		output.setMaximumSize(new Dimension(700, pHeight));
		BoxLayout layout = new BoxLayout(output, BoxLayout.PAGE_AXIS);
		output.setLayout(layout);
		output.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		inputSize = new JLabel("Input Length: " + inputBox.getText().trim().length());
		output.add(inputSize);

		outputBox.setLineWrap(true);

		JScrollPane scrollOutputText = new JScrollPane(outputBox, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		output.add(scrollOutputText);

		return output;
	}

	private JPanel generateInputContainer() {
		JPanel inputContainer = new JPanel();
		inputContainer.setMaximumSize(new Dimension(700, pHeight));
		BoxLayout layout = new BoxLayout(inputContainer, BoxLayout.PAGE_AXIS);
		inputContainer.setLayout(layout);
		inputContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
		inputContainer.add(new JLabel("Action", JLabel.CENTER));

		keyLength = new JSpinner(new SpinnerNumberModel(3, 2, Short.MAX_VALUE, 1));
		keyLength.setMaximumSize(new Dimension(45, 20));
		keyLength.setAlignmentX(JSpinner.CENTER_ALIGNMENT);
		inputContainer.add(keyLength);

		ButtonGroup actionsGroup = new ButtonGroup();

		JRadioButton encrypt = new JRadioButton("Encryption");
		encrypt.setToolTipText("Convert plaintext to a ciphertext (Key required)");

		JRadioButton decrypt = new JRadioButton("Decryption");
		decrypt.setToolTipText("Convert ciphertext to a plaintext (Key optional)");
		actionsGroup.add(encrypt);
		actionsGroup.add(decrypt);

		JPanel actionsFormElement = new JPanel(new FlowLayout());
		actionsFormElement.add(encrypt);
		actionsFormElement.add(decrypt);

		inputContainer.add(actionsFormElement);

		JRadioButton caesar = new JRadioButton("Caesar cipher");
		caesar.setToolTipText(
				"A substitution cipher in which each letter is shifted to a fixed number of	letters in the alphabet to the right");
		JRadioButton monosub = new JRadioButton("Monoalphabetic Substitution Cipher");
		monosub.setToolTipText(
				"A cipher in which each letter maps to another letter in the alphabet for the entire cipher");
		JRadioButton vigenere = new JRadioButton("Vigenere cipher");
		vigenere.setToolTipText(
				"A polyalphabetic substitution cipher that cycles through a number of caesar ciphers equivalent to the length of the key where each letter in the key maps to its number index in the language's alphabet");

		ButtonGroup cipherTypesGroup = new ButtonGroup();
		cipherTypesGroup.add(caesar);
		cipherTypesGroup.add(monosub);
		cipherTypesGroup.add(vigenere);

		JPanel cipherTypesFormElement = new JPanel(new FlowLayout());
		cipherTypesFormElement.add(caesar);
		cipherTypesFormElement.add(monosub);
		cipherTypesFormElement.add(vigenere);

		inputBox = new JTextArea(10, 10);
		JScrollPane scrollInput = new JScrollPane(inputBox, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		inputBox.setLineWrap(true);
		inputBox.setBorder(new EmptyBorder(10, 10, 10, 10));
		inputBox.setFont(new Font("Segoe Script", Font.BOLD, 20));
		inputBox.setForeground(Color.BLUE);
		inputBox.setToolTipText("Ciphertext or plaintext");
		inputContainer.add(scrollInput);

		inputContainer.add(new JLabel("Cipher"));
		inputContainer.add(cipherTypesFormElement);

		return inputContainer;
	}
}
