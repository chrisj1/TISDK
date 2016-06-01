package sdk.core;

import java.awt.EventQueue;

import sdk.view.DungeonEditor;
/**
 * Called at start up program
 * @author Chris Jerrett
 */
public class Main {

	/**
	 * Launches the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DungeonEditor window = new DungeonEditor();
					window.update();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
