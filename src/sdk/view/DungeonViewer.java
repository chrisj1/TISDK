package sdk.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import sdk.core.Dungeon;
import sdk.core.Room;
import sdk.util.Loader;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DungeonViewer {

	private JFrame frame;
	public static Dungeon dungeon;

	public static int WIDTH = 3;
	public static int HEIGHT = 3;

	private GridLayout layout;
	private JPanel[][] grid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DungeonViewer window = new DungeonViewer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DungeonViewer() {
		Room room0 = new Room();
		Room room1 = new Room();
		Room room2 = new Room();
		Room room3 = new Room();
		Room room4 = new Room();
		Room room5 = new Room();
		Room room6 = new Room();
		Room room7 = new Room();
		Room room8 = new Room();

		room0.setRight(room1);

		room1.setLeft(room0);
		room1.setRight(room2);
		room1.setBotton(room4);

		room2.setLeft(room1);

		room3.setRight(room4);

		room4.setTop(room1);
		room4.setRight(room5);
		room4.setLeft(room3);
		room4.setBotton(room7);

		room5.setLeft(room4);

		room6.setRight(room7);

		room7.setTop(room4);
		room7.setLeft(room6);
		room7.setRight(room8);

		room8.setLeft(room7);

		Room[] row0 = {
				room0, room1, room2
		};

		Room[] row1 = {
				room3, room4, room5
		};

		Room[] row2 = {
				room6, room7, room8
		};

		Room[][] rooms = {
				row0, row1, row2
		};

		dungeon = new Dungeon(rooms);
		try {
			Loader.genBufferedImageFromRoom(room4);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setTitle("TI SDK");
		frame.setResizable(false);
		frame.setFont(new Font("Agency FB", Font.PLAIN, 12));
		frame.setBounds(100, 100, 753, 522);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu file = new JMenu("File");
		menuBar.add(file);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Loader.saveDungeon(DungeonViewer.dungeon);
			}
		});
		file.add(mntmSave);

		JMenuItem mntmLoad = new JMenuItem("Load");
		file.add(mntmLoad);

		JMenuItem mntmNewDungeon = new JMenuItem("New Dungeon");
		file.add(mntmNewDungeon);

		JMenu mnNewMenu = new JMenu("Dungeon");
		menuBar.add(mnNewMenu);

		Room[][] rooms = dungeon.getRooms();


		layout = new GridLayout(rooms.length, rooms[0].length, 0, 0);
		frame.getContentPane().setLayout(layout);

		grid = new JPanel[rooms.length][rooms[0].length];

		for(int row = 0; row < rooms.length; row++) {
			for(int col = 0; col < rooms[0].length; col++) {
				grid[row][col] = new JPanel();
				//grid[row][col].setBackground(new Color(0,0,255));
			}
		}

		for(int row = 0; row < rooms.length; row++) {
			for(int col = 0; col < rooms[0].length; col++) {
				frame.getContentPane().add(grid[row][col]);
			}
		}

		for(int row = 0; row < rooms.length; row++) {
			for(int col = 0; col < rooms[0].length; col++) {
				Room room = dungeon.getRooms()[row][col];
				BufferedImage img = Loader.genBufferedImageFromRoom(room);
				Image image = img.getScaledInstance(img.getWidth() * 15, img.getHeight() * 15, Image.SCALE_FAST);
				ImageIcon icon = new ImageIcon(image);
				JLabel label = new JLabel(icon);
				grid[row][col].add(label);
			}
		}
	}

}
