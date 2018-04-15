package HypereditPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

public class FrameAssociator {

	JFrame thisFrame;
	JButton thisBigger;
	JButton thisLinkify;
	JButton thisSave;
	JButton thisSaveAll;
	JTextPane thisTextField;
	CaretEvent currentSelection;
	ArrayList<FrameAssociator> allFrames;
	StyledDocument thisStyle;
	SimpleAttributeSet thisNormal;
	SimpleAttributeSet thisBold;
	File thisSavePath;

	public FrameAssociator(String Title, ArrayList<FrameAssociator> otherFrames) {

		thisFrame = new JFrame(Title);
		thisBigger = new JButton("Bigger");
		thisLinkify = new JButton("Linkify");
		thisSave = new JButton("Save");
		thisSaveAll = new JButton("SaveAll");
		allFrames = otherFrames;
		thisTextField = new JTextPane();
		thisFrame.add(thisSave);
		thisFrame.add(thisSaveAll);
		thisFrame.add(thisBigger);
		thisFrame.add(thisLinkify);
		thisFrame.add(thisTextField);

		LinkifyActionListener LinkifyListener = new LinkifyActionListener(thisTextField, this);
		thisLinkify.addActionListener(LinkifyListener);

		SaveActionListener SaveListener = new SaveActionListener(this);
		thisSave.addActionListener(SaveListener);

		SaveAllActionListener SaveAllListener = new SaveAllActionListener(this);
		thisSaveAll.addActionListener(SaveAllListener);

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
		selectedText.trim();
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
			System.out.println("length:" + thisTextField.getText().length());
			System.out.println("selend:" + selEnd);
			System.out.println("after:" + thisTextField.getText(selEnd, 1));
//			int insertEnd = selEnd;
//			if (thisTextField.getText().length() >= selEnd + 1 && thisTextField.getText(selEnd, 1).equals(" ")) {
//				selEnd++;
//			}
			thisTextField.setSelectionStart(selStart);
			thisTextField.setSelectionEnd(selEnd);
			thisTextField.replaceSelection("");
			thisStyle.insertString(selStart, selectedText, thisBold);
			if (thisTextField.getText().length() == selEnd ) {
				thisStyle.insertString(selEnd, " ", thisNormal);
			}
//			RegularExpression regex = new RegularExpression("[a-Z]");
//			if (thisTextField.getText().length() >= selEnd + 1 && regex.matches(thisTextField.getText(selEnd, 1))) {
//				thisStyle.insertString(insertEnd, " ", thisNormal);
//			}

		} catch (BadLocationException e) {
			e.printStackTrace();
		}
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

	public JFileChooser save() {
		JFileChooser chooser = new JFileChooser();
		chooser.setApproveButtonText("Save");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int actionDialog = chooser.showSaveDialog(thisTextField);
		if (actionDialog != JFileChooser.APPROVE_OPTION) {
			return null;
		}
		thisSavePath = new File(chooser.getSelectedFile().getAbsolutePath() + "\\" + thisFrame.getTitle() + ".rtf");
		try {
			exportToRtf(thisTextField.getDocument(), thisSavePath);
			return chooser;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void save(JFileChooser savePath) {
		thisSavePath = new File(savePath.getSelectedFile().getAbsolutePath() + "\\" + thisFrame.getTitle() + ".rtf");
		try {
			exportToRtf(thisTextField.getDocument(), thisSavePath);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private void exportToRtf(Document doc, File file) throws IOException, BadLocationException {
		// By Olivier Facheux
		final StringWriter out = new StringWriter();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		RTFEditorKit kit = new RTFEditorKit();
		kit.write(baos, doc, doc.getStartPosition().getOffset(), doc.getLength());
		out.close();

		String rtfContent = baos.toString();
		{
			// replace "Monospaced" by a well-known monospace font
			rtfContent = rtfContent.replaceAll("Monospaced", "Courier New");
			final StringBuffer rtfContentBuffer = new StringBuffer(rtfContent);
			// final int endProlog = rtfContentBuffer.indexOf("\n\n");
			// // set a good Line Space and no Space Before or Space After each paragraph
			// rtfContentBuffer.insert(endProlog, "\n\\sl240");
			// rtfContentBuffer.insert(endProlog, "\n\\sb0\\sa0");
			rtfContent = rtfContentBuffer.toString();
		}
		final FileOutputStream fos = new FileOutputStream(file);
		fos.write(rtfContent.toString().getBytes());
		fos.close();

	}

	public void saveAll() {
		JFileChooser savePath = this.save();
		for (FrameAssociator frameAssociator : allFrames) {
			frameAssociator.save(savePath);
		}

	}

	public void setJTextPaneDoc(Document document) {
		thisTextField.setDocument(document);
	}
}
