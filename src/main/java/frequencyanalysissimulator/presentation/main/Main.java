package frequencyanalysissimulator.presentation.main;

import core.CipherInputBox;
import frequencyanalysissimulator.crypto.CaesarDecryptionMethod;
import frequencyanalysissimulator.crypto.KeyLengthMethod;
import frequencyanalysissimulator.crypto.VigenereDecryption;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

	private JFrame frame;

	private JTextArea outputBox;
	private JTextArea inputBox;
	private JLabel inputSize;
	private JLabel key;
	private JLabel ratio;

	private KeyLengthMethod method;

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

		CipherInputBox scrollInput = new CipherInputBox();
		inputBox = scrollInput.getTextArea();
		frame.add(scrollInput, BorderLayout.CENTER);

		JPanel executeContainer = new JPanel(new FlowLayout());
		JButton execute = new JButton("Begin operation");
		executeContainer.add(execute);
		frame.add(executeContainer, BorderLayout.SOUTH);
		frame.add(output(), BorderLayout.EAST);
		frame.add(generateInputContainer(), BorderLayout.WEST);

		execute.addActionListener(e -> {
			VigenereDecryption cipherSolver = new VigenereDecryption(inputBox.getText(), KeyLengthMethod.IOC);
			outputBox.setText(cipherSolver.decrypt(CaesarDecryptionMethod.KERCKHOFF));
			System.out.println(cipherSolver.decrypt(CaesarDecryptionMethod.KERCKHOFF));
			inputSize.setText("Input Length: " + cipherSolver.getCipherText(true).length());
			key.setText("Computed Key: " + cipherSolver.getKey());
			ratio.setText("Cipher Length to Key Length Ratio: " + cipherSolver.getCipherKeyLenRatio());
		});


	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Font fasDefaultFont = new Font("Verdana", Font.BOLD, 24);
		Font fasNormalFont = new Font("Verdana", Font.PLAIN, 16);
		UIManager.getLookAndFeelDefaults().put("Button.font", fasDefaultFont);
		UIManager.getLookAndFeelDefaults().put("RadioButton.font", fasNormalFont);
		UIManager.getLookAndFeelDefaults().put("Label.font", fasNormalFont);
		UIManager.getLookAndFeelDefaults().put("Spinner.font", fasNormalFont);
		UIManager.getLookAndFeelDefaults().put("TextArea.font", fasNormalFont);

		outputBox = new JTextArea("Awaiting output...");
		outputBox.setEditable(false);
		outputBox.setMargin(new Insets(30, 30, 30, 30));

		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 500);
		frame.setMinimumSize(new Dimension(1000, 500));
		frame.setIconImage(new ImageIcon("assets/icon.png").getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(10, 10));
		frame.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
		frame.pack();
	}

	private JComponent output() {
		JPanel output = new JPanel();
		BoxLayout layout = new BoxLayout(output, BoxLayout.PAGE_AXIS);
		output.setLayout(layout);
		output.setBorder(new EtchedBorder(EtchedBorder.RAISED));

		outputBox.setLineWrap(true);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		outputBox.setSize((int) (d.getWidth() / 4.0), 500);
		outputBox.setOpaque(false);

		Color statsColor = new Color(0, 153, 0);

		JScrollPane scrollOutputText = new JScrollPane(outputBox, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollOutputText.getViewport().setOpaque(false);
		scrollOutputText.getViewport().setBackground(new Color(0, 0, 0, 0));
		output.add(scrollOutputText);

		inputSize = new JLabel("Input Length: " + inputBox.getText().trim().length());
		inputSize.setForeground(statsColor);
		output.add(inputSize);
		key = new JLabel("Computed Key: ");
		key.setForeground(statsColor);
		output.add(key);
		ratio = new JLabel("Cipher Length to Key Length Ratio: ", JLabel.CENTER);
		ratio.setForeground(statsColor);
		output.add(ratio);

		return output;
	}

	private JPanel generateInputContainer() {
		JPanel inputContainer = new JPanel();
		inputContainer.setPreferredSize(new Dimension(300, 200));

		BoxLayout layout = new BoxLayout(inputContainer, BoxLayout.PAGE_AXIS);
		inputContainer.setLayout(layout);
		inputContainer.setBorder(new EtchedBorder(EtchedBorder.RAISED));

		String[][] actionOptions = new String[][]{
				{"Encryption", "Convert plaintext to a ciphertext (Key required)"},
				{"Decryption", "Convert ciphertext to a plaintext (Key optional)"}
		};
		inputContainer.add(generateRadioGroup(actionOptions, "Action"));

		String[][] keylengthMethod = new String[][]{
				{"Kasiski", "Uses the Kasiski Examination"},
				{"Kerckhoff",
						"Use the Kerckhoff method - a modern variation of an improvement of the Kasiski Examination - to infer the key length"},
				{"Friedman",
						"Use the Friedman Test - a mathematical formula using statistics to infer the length of the key"}
		};
		inputContainer.add(generateRadioGroup(keylengthMethod, "Key Length"));

		String[][] cipherOptions = {
				{"Caesar",
						"A substitution cipher in which each letter is shifted to a fixed number of letters in the alphabet to the right"},
				{"Simple substitution",
						"A cipher in which each letter maps to another letter in the alphabet for the entire cipher"},
				{"Vigenere",
						"A polyalphabetic substitution cipher that cycles through a number of caesar ciphers equivalent to the length of the key where each letter in the key maps to its number index in the language's alphabet"},
				{"Beaufort", "A variant of the Vigenere cipher where the letters map in the reverse direction"},
				{"Variant Beaufort", "A variant of the Vigenere cipher where encryption and decryption are reversed"}
		};
		inputContainer.add(generateRadioGroup(cipherOptions, "Cipher"));

		return inputContainer;
	}

	private JComponent generateRadioGroup(String[][] elements, String label) {
		JPanel container = new JPanel(new BorderLayout());
		Box titleContainer = Box.createHorizontalBox();
		JLabel title = new JLabel(label);
		titleContainer.add(Box.createRigidArea(new Dimension(10, 7)));
		titleContainer.add(title);
		title.setAlignmentY(JLabel.TOP_ALIGNMENT);

		System.out.println(titleContainer.getPreferredSize().getWidth());
		titleContainer.add(Box.createHorizontalStrut((int) (100 - title.getPreferredSize().getWidth())));
		container.add(titleContainer, BorderLayout.NORTH);
		JPanel b1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		b1.setSize(100, container.getHeight());

		container.add(b1, BorderLayout.CENTER);
		ButtonGroup buttons = new ButtonGroup();

		for (String[] element : elements) {
			JRadioButton rb = new JRadioButton(element[0]);
			rb.setToolTipText(element[1]);
			buttons.add(rb);
			b1.add(rb);
		}

		return container;
	}
}
