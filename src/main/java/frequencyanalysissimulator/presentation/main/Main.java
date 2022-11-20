package frequencyanalysissimulator.presentation.main;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

import frequencyanalysissimulator.business.Vigenere;

public class Main {

	private JFrame frame;

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
		frame.setLayout(new BorderLayout());

		JPanel form = new JPanel();
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
				"A substitution cipher in which each letter is shifted to a fixed number of letters in the alphabet to the right");
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

		form.add(new JLabel("Cipher"));
		form.add(cipherTypesFormElement);

		frame.getContentPane().add(form, BorderLayout.WEST);

		ImageIcon summary = new ImageIcon("assets/encrypt.png");
		Image summaryImg = summary.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		JLabel jp = new JLabel(new ImageIcon(summaryImg));
		frame.getContentPane().add(new JLabel("Summary"), BorderLayout.EAST);
		frame.getContentPane().add(jp, BorderLayout.EAST);

		JButton execute = new JButton("Begin operation");
		frame.getContentPane().add(execute, BorderLayout.SOUTH);
		execute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Vigenere cipherSolver = new Vigenere(6,
						"YKBXG WLKHF TGLVH NGMKR FXGEX GWFXR HNKXT KLBVH FXMHU NKRVT XLTKG HMMHI KTBLX ABFMA XXOBE MATMF XGWHE BOXLT YMXKM AXFMA XZHHW BLHYM BGMXK KXWPB MAMAX BKUHG XLLHE XMBMU XPBMA VTXLT KMAXG HUEXU KNMNL ATMAM HEWRH NVTXL TKPTL TFUBM BHNLB YBMPX KXLHB MPTLT ZKBXO HNLYT NEMTG WZKBX OHNLE RATMA VTXLT KTGLP XKWBM AXKXN GWXKE XTOXH YUKNM NLTGW MAXKX LMYHK UKNMN LBLTG AHGHN KTUEX FTGLH TKXMA XRTEE TEEAH GHNKT UEXFX GVHFX BMHLI XTDBG VTXLT KLYNG XKTE");
				cipherSolver.decryptCosetByChiSquare((byte) 1);
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 500);
		frame.setIconImage(new ImageIcon("assets/icon.png").getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
