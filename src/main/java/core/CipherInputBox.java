package core;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CipherInputBox extends JScrollPane {
	private final JTextArea textArea;

	public CipherInputBox() {
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea = new JTextArea();
		textArea.setMinimumSize(new Dimension(10, 10));
		textArea.setOpaque(false);
		textArea.setLineWrap(true);
		textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
		textArea.setFont(new Font("Segoe Script", Font.BOLD, 20));
		textArea.setForeground(Color.BLUE);
		textArea.setToolTipText("Ciphertext or plaintext");
		setViewportView(textArea);

		setOpaque(false);
	}

	public JTextArea getTextArea() {
		return textArea;
	}
}