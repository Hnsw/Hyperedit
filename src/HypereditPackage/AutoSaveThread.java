package HypereditPackage;

import java.util.ArrayList;

public class AutoSaveThread extends Thread {
	ArrayList<FrameAssociator> activeFrames;

	public AutoSaveThread(ArrayList<FrameAssociator> frames) {
		activeFrames = frames;
		this.setDaemon(true);
		setPriority(MIN_PRIORITY);
	}

	public void run() {
		try {
			sleep(10000);
			for (FrameAssociator frameAssociator : activeFrames) {
				frameAssociator.saveToDefault();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
