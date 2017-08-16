import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JDesktopPane;
import java.awt.Font;
import java.awt.SystemColor;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Color;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class Detect extends JFrame{
	private CardLayout card;
	public Detect() {
		
		
		this.card = new CardLayout();
		
		this.setLayout(this.card);
		this.showduplicateOutput();
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		


	}

	public static void main(String[] args) {
		Detect gui = new Detect();
		
		

	}

	public void showduplicateOutput() {
		duplicateOutput output = new duplicateOutput();
		getContentPane().add(output, "Duplicated Output");
		this.card.show(this.getContentPane(), "Duplicated Output");
		
	}

}
