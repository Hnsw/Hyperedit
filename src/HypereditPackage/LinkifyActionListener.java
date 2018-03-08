package HypereditPackage;

import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.text.BadLocationException;

public class LinkifyActionListener implements ActionListener {

	JTextPane thisTextField;
	CaretEvent coordinateCarrier;
	FrameAssociator thisWindow;

	public LinkifyActionListener(JTextPane textPane, FrameAssociator window) {
		thisTextField = textPane;
		thisWindow = window;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		coordinateCarrier = thisWindow.getCurrentSelection();
		String selectedText = "noSelection";
		int mark = coordinateCarrier.getMark();
		int dot = coordinateCarrier.getDot();
		if (dot != mark) {
			if (dot < mark) {
				int temp = dot;
				dot = mark;
				mark = temp;
			}
			int length = dot - mark;
			try {
				selectedText = thisTextField.getText(mark, length);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
			thisWindow.handleSelection(selectedText);
		}
	}
}
