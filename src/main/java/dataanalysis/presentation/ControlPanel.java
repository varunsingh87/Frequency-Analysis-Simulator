package dataanalysis.presentation;

import javax.swing.*;

public class ControlPanel extends JFrame {
    
    ControlPanel() {
        super();
        this.setSize(700,700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(layoutCipherTabs());
        this.setVisible(true);
    }
    
    private static JTabbedPane layoutCipherTabs() {
        JTabbedPane jtp = new JTabbedPane();
        jtp.addTab("Vigenere", new ImageIcon(System.getProperty("user.dir").concat("/assets/encrypt.png")), new VigenereTab(), "Analyze decryption of a Vigenere cipher");
        jtp.addTab("Simple Substitution", new ImageIcon(System.getProperty("user.dir").concat("/assets/decrypt.png")), new JPanel(), "Analyze decryption of a simple substitution cipher");
        return jtp;
    }
    
    public static void main(String[] args) {
        new ControlPanel();
    }
}