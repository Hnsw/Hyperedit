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
		while (true) {
			try {
				sleep(10000);
			//	System.out.println("Still alive");
				for (FrameAssociator frameAssociator : activeFrames) {
				//	System.out.println(frameAssociator.thisAutoSavePath);
					frameAssociator.saveToDefault();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void update(FrameAssociator loadedFrameAssociator) {
		activeFrames.add(loadedFrameAssociator);
	}

}
