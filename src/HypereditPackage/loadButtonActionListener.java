package HypereditPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.rtf.RTFEditorKit;


public class loadButtonActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser chooser = new JFileChooser();
		chooser.setApproveButtonText("Load");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int actionDialog = chooser.showOpenDialog(chooser);
		if (actionDialog != JFileChooser.APPROVE_OPTION) {
			return;
		}
		// Need way to get files in folder
		ArrayList<File> FilesToLoad = new ArrayList<File>();
		FilesToLoad = getFilesFromPath(chooser.getSelectedFile().getAbsolutePath());

		RTFEditorKit loadKit = new RTFEditorKit();
		for (File file : FilesToLoad) {
			FileReader fileReader = null;
			try {
				fileReader = new FileReader(file);
				JTextPane LoadPane = new JTextPane();
				loadKit.read(fileReader, LoadPane.getDocument(), 1);
				ArrayList<FrameAssociator> allFrames = new ArrayList<FrameAssociator>();
				FrameAssociator loadedFrameAssociator = new FrameAssociator(file.getName().replaceAll(".rtf", ""), allFrames);
				loadedFrameAssociator.setJTextPaneDoc(LoadPane.getDocument());
				allFrames.add(loadedFrameAssociator);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private ArrayList<File> getFilesFromPath(String absolutePath) {
		return null;
	}

}