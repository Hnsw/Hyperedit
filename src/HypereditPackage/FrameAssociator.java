package HypereditPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;

public class FrameAssociator {

	JFrame thisFrame;
	JButton thisBigger;
	JButton thisLinkify;
	JTextPane thisTextField;
	CaretEvent currentSelection;
	ArrayList<FrameAssociator> allFrames;

	public FrameAssociator(String Title, ArrayList<FrameAssociator>	otherFrames) {

		thisFrame = new JFrame(Title);
		thisBigger = new JButton("Bigger");
		thisLinkify = new JButton("Linkify");
		allFrames = otherFrames;
		thisTextField = new JTextPane();
		thisFrame.add(thisBigger);
		thisFrame.add(thisLinkify);
		thisFrame.add(thisTextField);
		
		thisLinkify.addActionListener(new LinkifyActionListener(thisTextField, this));

		FlowLayout LayoutManager = new FlowLayout();
		LayoutManager.setAlignment(FlowLayout.CENTER);
		thisFrame.setLayout(LayoutManager);
		Color bgColor = new Color(0, 0, 100);
		thisFrame.getContentPane().setBackground(bgColor);
		thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		thisFrame.setSize(800, 500);

		thisTextField.setPreferredSize(new Dimension(750, 850));
		thisTextField.setVisible(true);
		thisTextField.setBackground(new Color(255, 255, 255));
		float size = thisTextField.getFont().getSize() + 5.0f;
		thisTextField.setFont(thisTextField.getFont().deriveFont(size));
		thisTextField.addCaretListener(new textSelectorActionListener(this));
		

		thisFrame.setVisible(true);
		thisFrame.toFront();
	}

	public JButton getThisBigger() {
		return thisBigger;
	}

	public JButton getThisLinkify() {
		return thisLinkify;
	}

	public JTextPane getThisTextField() {
		return thisTextField;
	}

	public FrameAssociator returnFrame() {
		return this;
	}

	public JFrame getFrame() {
		return thisFrame;
	}

	public void handleSelection(String selectedText) {
		System.out.println(selectedText);
		if (selectedText.contains("x") == true) {
			for (int i = 0; i < allFrames.size(); i++) {
				FrameAssociator selectedFrame = allFrames.get(i);
				if (selectedFrame.getFrame().getTitle().equals((selectedText.substring(1)))) {
					selectedFrame.getFrame().toFront();
				}
			}
		} else if (selectedText.length() != 0) {
			thisTextField.replaceSelection("x" + selectedText);
			allFrames.add(new FrameAssociator(selectedText, allFrames));
//			allFrames.notifyAll();
			// Frames.add(new JFrame(selectedText));
			// JFrame newFrame = setupFrame(Frames.get(Frames.size() - 1));
			// newFrame.setSize(new Dimension(800, 500));
			//
			// JButton increaseSize = new JButton("bigger");
			// frame.add(increaseSize);
			// increaseSize.setVisible(true);
			//
			// JButton linkify = new JButton("linkify");
			// linkify.addActionListener((event)->{System.out.println("hello");});
			// frame.add(linkify);
			// linkify.setVisible(true);

		}
	}

	public void setCaretEvent(CaretEvent e) {
		currentSelection = e;
		System.out.println(currentSelection.getDot());
	}

	public CaretEvent getCurrentSelection() {
		return currentSelection;
	}
}
