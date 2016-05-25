package sdk.core;

import java.awt.EventQueue;

import sdk.view.Editor;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editor window = new Editor();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
