package sdk.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import sdk.core.Dungeon;

public class Main {

	private JFrame frmTiSdk;
	
	public static Dungeon dungeon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmTiSdk.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTiSdk = new JFrame();
		frmTiSdk.setTitle("TI SDK");
		frmTiSdk.setResizable(false);
		frmTiSdk.setFont(new Font("Agency FB", Font.PLAIN, 12));
		frmTiSdk.setBounds(100, 100, 753, 522);
		frmTiSdk.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmTiSdk.setJMenuBar(menuBar);
		
		JMenu file = new JMenu("File");
		menuBar.add(file);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		file.add(mntmSave);
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		file.add(mntmLoad);
		
		JMenuItem mntmNewDungeon = new JMenuItem("New Dungeon");
		file.add(mntmNewDungeon);
		
		JMenu mnNewMenu = new JMenu("Dungeon");
		menuBar.add(mnNewMenu);
		frmTiSdk.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
	}

}
