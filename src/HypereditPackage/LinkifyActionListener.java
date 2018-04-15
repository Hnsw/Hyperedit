package HypereditPackage;

import java.awt.event.*;

import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.text.BadLocationException;

public class LinkifyActionListener implements ActionListener, KeyListener{

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
			thisWindow.handleSelection(selectedText, mark, dot);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 17) {
			thisWindow.handleCtrlPress();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
