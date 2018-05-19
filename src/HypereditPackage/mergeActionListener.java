package HypereditPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

public class mergeActionListener implements ActionListener {

	FrameAssociator linkedFrameAssociator;
	StyledDocument compoundDocument;
	String compoundString;

	public mergeActionListener(FrameAssociator frameAssociator) {
		linkedFrameAssociator = frameAssociator;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ArrayList<FrameAssociator> allFrames = linkedFrameAssociator.getAllFrames();
		compoundString = "";
		for (FrameAssociator frameAssociator : allFrames) {
			compoundString = compoundString.concat(frameAssociator.getFrame().getTitle());
			compoundString = compoundString.concat(frameAssociator.getThisTextField().getText());
			// System.out.println(frameAssociator.thisFrame.getTitle());
		}
		System.out.println(compoundString);
		String mergedFileName = linkedFrameAssociator.thisSavePath.getParentFile().getName();
		File mergedFile = new File(linkedFrameAssociator.thisSavePath.getParentFile().getAbsolutePath() + "");
	}

}
