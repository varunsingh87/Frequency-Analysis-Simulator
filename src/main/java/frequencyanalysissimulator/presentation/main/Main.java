package frequencyanalysissimulator.presentation.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Main {

	private JFrame frame;

	private int pWidth;
	private int pHeight;

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

		JPanel form = new JPanel();
		form.setMaximumSize(new Dimension(700, pHeight));
		form.setBorder(new EmptyBorder(10, 10, 10, 10));
		BoxLayout bl = new BoxLayout(form, BoxLayout.PAGE_AXIS);
		form.setLayout(bl);
		form.add(new JLabel("Action"));

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

		form.add(actionsFormElement);

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

		JTextArea textInput = new JTextArea(10, 10);
		JScrollPane scrollInput = new JScrollPane(textInput, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textInput.setLineWrap(true);
		textInput.setBorder(new EmptyBorder(10, 10, 10, 10));
		textInput.setFont(new Font("Segoe Script", Font.BOLD, 20));
		textInput.setForeground(Color.BLUE);
		textInput.setToolTipText("Ciphertext or plaintext");
		form.add(scrollInput);

		form.add(new JLabel("Cipher"));
		form.add(cipherTypesFormElement);

		frame.getContentPane().add(form, BorderLayout.WEST);

		ImageIcon summary = new ImageIcon("assets/encrypt.png");
		Image summaryImg = summary.getImage().getScaledInstance(20, 20,
				java.awt.Image.SCALE_SMOOTH);
		JLabel jp = new JLabel(new ImageIcon(summaryImg));
		frame.getContentPane().add(new JLabel("Summary"), BorderLayout.EAST);
		frame.getContentPane().add(jp, BorderLayout.EAST);

		JPanel executeContainer = new JPanel(new FlowLayout());
		JButton execute = new JButton("Begin operation");
		executeContainer.add(execute);
		frame.add(executeContainer, BorderLayout.SOUTH);

		frame.add(new JLabel("Varun Singh's Frequency Analysis Simulator", JLabel.CENTER), BorderLayout.NORTH);

		execute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Font fasDefaultFont = new Font("Verdana", Font.BOLD, 24);
		UIManager.getLookAndFeelDefaults().put("Button.font", fasDefaultFont);
		UIManager.getLookAndFeelDefaults().put("RadioButton.font", new Font("Verdana", Font.BOLD, 16));
		UIManager.getLookAndFeelDefaults().put("Label.font", fasDefaultFont);
		frame = new JFrame();
		pHeight = 500;
		pWidth = 600;
		frame.setBounds(100, 100, pWidth, pHeight);
		frame.setMinimumSize(new Dimension(500, 600));
		frame.setIconImage(new ImageIcon("assets/icon.png").getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
