package HypereditPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Main {
	static String firstTitle;
	static JFrame initialFrame = new JFrame("Dokument laden/erstellen");

	public static void main(String[] args) {

		ArrayList<FrameAssociator> Frames = new ArrayList<FrameAssociator>();
		getFirstTitle(Frames);

	}
	// static int lengthg;
	// static int markg;
	// static CaretEvent coordinateEventG;
	// static ArrayList<JFrame> Frames = new ArrayList<JFrame>();
	//
	// public static void main(String[] args) {
	//
	// JFrame frame = setupFrame(new JFrame("Editor"));
	//
	// JButton increaseSize = new JButton("bigger");
	// frame.add(increaseSize);
	// increaseSize.setVisible(true);
	//
	// JButton linkify = new JButton("linkify");
	// frame.add(linkify);
	// linkify.setVisible(true);
	//
	// JTextPane textArea = new JTextPane();
	// frame.add(textArea);
	// textArea.setPreferredSize(new Dimension(750, 850));
	// textArea.setVisible(true);
	// textArea.setBackground(new Color(255, 255, 255));
	// float size = textArea.getFont().getSize() + 5.0f;
	// textArea.setFont(textArea.getFont().deriveFont(size));
	// increaseSize.addActionListener(new ActionListener() {
	//
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// float size = textArea.getFont().getSize() + 1.0f;
	// textArea.setFont(textArea.getFont().deriveFont(size));
	// }
	// });
	//
	//
	//
	// textArea.addCaretListener(new CaretListener() {
	//
	// @Override
	// public void caretUpdate(CaretEvent e) {
	// coordinateEventG = e;
	// int dot = e.getDot();
	// int mark = e.getMark();
	// if (dot != mark) {
	// if (dot < mark) {
	// int temp = dot;
	// dot = mark;
	// mark = temp;
	// }
	// int length = dot - mark;
	// lengthg = length;
	// markg = mark;
	// }
	// }
	// });
	//
	// linkify.addActionListener(new ActionListener() {
	// public void actionPerformed(ActionEvent e) {
	// String selectedText = "noSelection";
	// try {
	// selectedText = textArea.getText(markg, lengthg);
	// } catch (BadLocationException e1) {
	// e1.printStackTrace();
	// }
	// if (selectedText.contains("x") == true) {
	// for (int i = 0; i < Frames.size(); i++) {
	// JFrame selectedFrame = Frames.get(i);
	// System.out.println(selectedFrame.getTitle());
	// System.out.println(selectedText);
	// if (selectedFrame.getTitle().equals((selectedText.substring(1)))) {
	// selectedFrame.toFront();
	// }
	// }
	// } else if (selectedText.length() != 0) {
	// textArea.replaceSelection("x" + selectedText);
	// Frames.add(new JFrame(selectedText));
	// JFrame newFrame = setupFrame(Frames.get(Frames.size() - 1));
	// newFrame.setSize(new Dimension(800, 500));
	//
	// JButton increaseSize = new JButton("bigger");
	// frame.add(increaseSize);
	// increaseSize.setVisible(true);
	//
	// JButton linkify = new JButton("linkify");
	// linkify.addActionListener((event) -> {
	// System.out.println("hello");
	// });
	// frame.add(linkify);
	// linkify.setVisible(true);
	//
	// }
	// }
	// });
	//
	// linkify.addActionListener(new LinkifyActionListener(association, this));
	//

	private static void getFirstTitle(ArrayList<FrameAssociator> Frames) {
		FlowLayout LayoutManager = new FlowLayout();
		LayoutManager.setAlignment(FlowLayout.CENTER);
		initialFrame.setLayout(LayoutManager);
		JButton confirm = new JButton("Confirm");
		JButton load = new JButton("Load");
		JTextField initialField = new JTextField();
		initialField.setPreferredSize(new Dimension(200, 100));
		
		
		load.addActionListener( new loadButtonActionListener(initialFrame));
		
		
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstTitle = initialField.getText();
				initialFrame.dispose();
				Frames.add(new FrameAssociator(firstTitle, Frames));
				AutoSaveThread autoSaver = new AutoSaveThread(Frames);
				autoSaver.start();
			}
		});

		initialFrame.add(initialField);
		initialFrame.add(confirm);
		initialFrame.add(load);
		initialFrame.setVisible(true);
		initialFrame.setSize(new Dimension(240, 200));
	}
}
	