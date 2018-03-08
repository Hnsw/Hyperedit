package HypereditPackage;

import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

public class textSelectorActionListener implements CaretListener {

	FrameAssociator thisAssociator;

	public textSelectorActionListener(FrameAssociator window) {
		thisAssociator = window;

	}

	public void caretUpdate(CaretEvent e) {
		thisAssociator.setCaretEvent(e);
	}
}
