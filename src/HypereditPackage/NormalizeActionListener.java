package HypereditPackage;

import java.awt.event.*;

import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.text.BadLocationException;

public class NormalizeActionListener implements ActionListener{

	JTextPane thisTextField;
	CaretEvent coordinateCarrier;
	FrameAssociator thisWindow;

	public NormalizeActionListener(JTextPane textPane, FrameAssociator window) {
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
			thisWindow.normalizeSelection(selectedText, mark, dot);
		}
	}
}
