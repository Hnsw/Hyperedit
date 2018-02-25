package HypereditPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

public class Main {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Editor");
		FlowLayout LayoutManager = new FlowLayout();
		LayoutManager.setAlignment(FlowLayout.CENTER);
		frame.setLayout(LayoutManager);
		Color bgColor = new Color(0, 0, 100);
		frame.getContentPane().setBackground(bgColor);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 900);
		frame.setVisible(true);

		JButton increaseSize = new JButton("bigger");
		frame.add(increaseSize);
		increaseSize.setVisible(true);

		JTextPane textArea = new JTextPane();
		frame.add(textArea);
		textArea.setPreferredSize(new Dimension(750, 850));
		textArea.setVisible(true);
		textArea.setBackground(new Color(10, 255, 255));

		increaseSize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				float size = textArea.getFont().getSize() + 1.0f;
				textArea.setFont(textArea.getFont().deriveFont(size));
			}
		});
		
		ArrayList<JFrame> Frames = new ArrayList<JFrame>();

		textArea.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				int dot = e.getDot();
				int mark = e.getMark();
				if (dot != mark) {
					if (dot < mark) {
						int temp = dot;
						dot = mark;
						mark = temp;
					}
					int length = dot - mark;
					String selectedText = "noSelection";
					try {
						selectedText = textArea.getText(mark, length);
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
					if (selectedText.contains(">") == true) {

					} else {
						textArea.replaceSelection(">" + selectedText);
					//	Frames.add(1, new JFrame());
					//	JFrame newFrame = Frames.get(1);
							JFrame newFrame = new JFrame();
						newFrame.setSize(100, 100);
						newFrame.setVisible(true);
						
						
					}

				}
			}
		});
	}

}
