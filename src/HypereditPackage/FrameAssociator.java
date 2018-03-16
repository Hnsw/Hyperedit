package HypereditPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class FrameAssociator {

	JFrame thisFrame;
	JButton thisBigger;
	JButton thisLinkify;
	JTextPane thisTextField;
	CaretEvent currentSelection;
	ArrayList<FrameAssociator> allFrames;
	StyledDocument thisStyle;
	SimpleAttributeSet thisNormal;
	SimpleAttributeSet thisBold;

	public FrameAssociator(String Title, ArrayList<FrameAssociator> otherFrames) {

		thisFrame = new JFrame(Title);
		thisBigger = new JButton("Bigger");
		thisLinkify = new JButton("Linkify");
		allFrames = otherFrames;
		thisTextField = new JTextPane();
		thisFrame.add(thisBigger);
		thisFrame.add(thisLinkify);
		thisFrame.add(thisTextField);

		LinkifyActionListener LinkifyListener = new LinkifyActionListener(thisTextField, this);
		thisLinkify.addActionListener(LinkifyListener);

		FlowLayout LayoutManager = new FlowLayout();
		LayoutManager.setAlignment(FlowLayout.CENTER);
		thisFrame.setLayout(LayoutManager);
		Color bgColor = new Color(0, 0, 100);
		thisFrame.getContentPane().setBackground(bgColor);
		// thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		thisFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		thisFrame.setSize(800, 500);

		thisTextField.setPreferredSize(new Dimension(750, 850));
		thisTextField.setVisible(true);
		thisTextField.setBackground(new Color(255, 255, 255));
		float size = thisTextField.getFont().getSize() + 5.0f;
		thisTextField.setFont(thisTextField.getFont().deriveFont(size));
		thisTextField.addCaretListener(new textSelectorActionListener(this));
		thisTextField.addKeyListener(LinkifyListener);

		thisStyle = (StyledDocument) thisTextField.getDocument();
		thisNormal = new SimpleAttributeSet();
		StyleConstants.setFontFamily(thisNormal, "SansSerif");
		StyleConstants.setFontSize(thisNormal, 16);
		StyleConstants.setBold(thisNormal, false);

		thisBold = new SimpleAttributeSet(thisNormal);
		StyleConstants.setBold(thisBold, true);

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

	public void handleSelection(String selectedText, int selStart, int selEnd) {

		if (selectedText.length() != 0) {
			for (int i = 0; i < allFrames.size(); i++) {
				FrameAssociator selectedFrame = allFrames.get(i);
				if ((selectedFrame.getFrame().getTitle().equals(selectedText))) {
					if (thisStyle.getStyle(selectedText) != thisBold) {
						replaceTextWithLink(selectedText, selStart, selEnd);
					}
					selectedFrame.getFrame().toFront();
					return;
				}
			}

			if (thisStyle.getStyle(selectedText) != thisBold) {
				replaceTextWithLink(selectedText, selStart, selEnd);
			}
			allFrames.add(new FrameAssociator(selectedText, allFrames));
		}
	}

	private void replaceTextWithLink(String selectedText, int selStart, int selEnd) {
		try {
			thisTextField.setSelectionStart(selStart);
			thisTextField.setSelectionEnd(selEnd);
			thisTextField.replaceSelection("");
			thisStyle.insertString(selStart, selectedText, thisBold);
			thisStyle.insertString(selEnd, " ", thisNormal);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private void setList(ArrayList<FrameAssociator> newListOfAllFrames) {
		allFrames = newListOfAllFrames;
	}

	public void setCaretEvent(CaretEvent e) {
		currentSelection = e;
	}

	public CaretEvent getCurrentSelection() {
		return currentSelection;
	}

	public void handleCtrlPress() {
		thisLinkify.doClick();

	}
}
