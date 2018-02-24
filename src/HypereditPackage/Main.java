package HypereditPackage;

import java.awt.*;
import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 900);
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setSize(800, 900);
		textArea.setLineWrap(true);
		Font font = textArea.getFont();
	//	float size = font.getSize() + 1.0f;
		float size = 20;
		textArea.setFont( font.deriveFont(size) );
		frame.add(textArea);
		frame.setVisible(true);
	}

}
