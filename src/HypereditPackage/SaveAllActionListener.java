package HypereditPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveAllActionListener implements ActionListener {

	FrameAssociator associatedAssociatior;
	
	public SaveAllActionListener( FrameAssociator anAssociator ) {
		associatedAssociatior = anAssociator;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		associatedAssociatior.saveAll();
	}

}
