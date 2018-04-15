package HypereditPackage;


import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class textSelectorActionListener implements CaretListener {

	FrameAssociator thisAssociator;

	public textSelectorActionListener(FrameAssociator window) {
		thisAssociator = window;

	}

	public void caretUpdate(CaretEvent e) {
		thisAssociator.setCaretEvent(e);
	}
}
