package HypereditPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveActionListener implements ActionListener {

	FrameAssociator associatedAssociatior;
	
	public SaveActionListener( FrameAssociator anAssociator ) {
		associatedAssociatior = anAssociator;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		associatedAssociatior.save();
	}

}
