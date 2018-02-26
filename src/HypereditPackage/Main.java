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
	
	static int lengthg;
	static int markg;
	

	public static void main(String[] args) {

			
		JFrame frame = setupFrame(new JFrame("Editor"));
		
		JButton increaseSize = new JButton("bigger");
		frame.add(increaseSize);
		increaseSize.setVisible(true);

		JButton linkify = new JButton("linkify");
		frame.add(linkify);
		linkify.setVisible(true);

		
		

		
		JTextPane textArea = new JTextPane();
		frame.add(textArea);
		textArea.setPreferredSize(new Dimension(750, 850));
		textArea.setVisible(true);
		textArea.setBackground(new Color(255,255,255));
		float size = textArea.getFont().getSize() + 5.0f;
		textArea.setFont(textArea.getFont().deriveFont(size));

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
				int	 length = dot - mark;
				lengthg = length;
				markg = mark;
				}
			}
		});



		linkify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedText = "noSelection";
				try {
					selectedText = textArea.getText(markg, lengthg);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
				if (selectedText.contains("x") == true) {

				} else if (selectedText.length() != 0) {
					textArea.replaceSelection("x" + selectedText);
					Frames.add(new JFrame(selectedText));
					JFrame newFrame = Frames.get(Frames.size() - 1);
					newFrame.setSize(200, 400);
					newFrame.setVisible(true);
				}
			}
		});

		
		frame.setSize(800, 500);
	}


	private static JFrame setupFrame(JFrame frame) {

		FlowLayout LayoutManager = new FlowLayout();
		LayoutManager.setAlignment(FlowLayout.CENTER);
		frame.setLayout(LayoutManager);
		Color bgColor = new Color(0, 0, 100);
		frame.getContentPane().setBackground(bgColor);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		return frame;
	}
}


