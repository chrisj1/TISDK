package sdk.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
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
		room0.setBotton(room3);

		room1.setLeft(room0);
		room1.setRight(room2);
		room1.setBotton(room4);

		room2.setLeft(room1);
		room2.setBotton(room5);

		room3.setTop(room0);
		room3.setRight(room4);
		room3.setBotton(room6);

		room4.setTop(room1);
		room4.setRight(room5);
		room4.setLeft(room3);
		room4.setBotton(room7);

		room5.setTop(room2);
		room5.setRight(room5);
		room5.setLeft(room4);

		room6.setTop(room3);
		room6.setRight(room7);

		room7.setTop(room4);
		room7.setLeft(room6);
		room7.setRight(room8);

		room8.setTop(room5);
		room8.setRight(room7);

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
		file.add(mntmSave);

		JMenuItem mntmLoad = new JMenuItem("Load");
		file.add(mntmLoad);

		JMenuItem mntmNewDungeon = new JMenuItem("New Dungeon");
		file.add(mntmNewDungeon);

		JMenu mnNewMenu = new JMenu("Dungeon");
		menuBar.add(mnNewMenu);

		Room[][] rooms = dungeon.getRooms();


		layout = new GridLayout(rooms.length, rooms[0].length, 0, 0);
		frame.setLayout(layout);
		grid = new JPanel[rooms.length][rooms[0].length];

		for(int row = 0; row < rooms.length; row++) {
			for(int col = 0; col < rooms[0].length; col++) {
				grid[row][col] = new JPanel();
			}
		}


		for(int row = 0; row < rooms.length; row++) {
			for(int col = 0; col < rooms[0].length; col++) {
				Room room = dungeon.getRooms()[row][col];
				BufferedImage img = Loader.genBufferedImageFromRoom(room);
				room.setImage(img);
				System.out.println(img);
				ImageIcon icon = new ImageIcon(img);
				JLabel label = new JLabel(icon);
				grid[row][col].add(label);
			}
		}

		System.out.println(dungeon.getRooms().length * dungeon.getRooms()[0].length);
	}

}
