package sdk.core;

import java.awt.EventQueue;

import sdk.view.DungeonEditor;

public class Main {

	/**
	 * Launch the application.
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
