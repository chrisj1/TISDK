package sdk.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

	public static int WIDTH = 16;
	public static int HEIGHT = 9;

	private GridLayout layout;
	private JButton[][] grid;

	private boolean isConnecting;
	private boolean isDisconnecting;
	private Room[] connectedRooms;

	static boolean firstDraw = true;

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
		if (firstDraw) {
			Room[][] rooms= new Room[WIDTH][HEIGHT];

			int id = 0;
			for(int c = 0; c < HEIGHT; c++) {
				for(int r = 0; r < WIDTH; r++) {
					rooms[r][c] = new Room(id);
					id++;
				}
			}

			dungeon = new Dungeon(rooms);
		}
		try {
			createFrame();
			firstDraw = false;
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void createFrame() {
		frame = new JFrame();
		frame.setTitle("TI SDK");
		frame.setResizable(true);
		frame.setFont(new Font("Agency FB", Font.PLAIN, 12));
		frame.setBounds(0, 0, 1600, 1000);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException
	 */
	private void initialize() throws IOException {

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

		JMenu mntmDungeon = new JMenu("Dungeon");

		JMenuItem mntmCreateConnection = new JMenuItem("Create Connection");
		mntmCreateConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Creating connection");
				isConnecting = true;
				connectedRooms = new Room[2];
			}
		});

		JMenuItem mntmBlockConnection = new JMenuItem("Block Connection");
		mntmBlockConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Blocking connection");
				isDisconnecting = true;
				connectedRooms = new Room[2];
			}
		});
		mntmDungeon.add(mntmBlockConnection);
		mntmDungeon.add(mntmCreateConnection);
		menuBar.add(mntmDungeon);

		Room[][] rooms = dungeon.getRooms();


		layout = new GridLayout(rooms.length, rooms[0].length, 0, 0);
		frame.getContentPane().setLayout(layout);

		grid = new JButton[rooms.length][rooms[0].length];

		for(int c = 0; c < HEIGHT; c++) {
			for(int r = 0; r < WIDTH; r++) {
				grid[r][c] = new IDJButton(rooms.length * c + r);
			}
		}

		for(int c = 0; c < HEIGHT; c++) {
			for(int r = 0; r < WIDTH; r++) {
				frame.getContentPane().add(grid[r][c]);
			}
		}


		int width = 64*2;
		int height = 48;

		System.out.println(width);
		System.out.println(height);

		for(int row = 0; row < rooms.length; row++) {
			for(int col = 0; col < rooms[0].length; col++) {
				Room room = dungeon.getRooms()[row][col];
				BufferedImage img = Loader.genBufferedImageFromRoom(room);
				Graphics g = img.getGraphics();
				g.setFont(new Font("Arial", Font.BOLD, 64));
				g.setColor(Color.white);
				g.drawString("" + room.getId(), 20, 20);
				g.dispose();
				Image image = img.getScaledInstance(width, height, Image.SCALE_FAST);
				ImageIcon icon = new ImageIcon(image);
				JLabel label = new JLabel(icon);
				grid[row][col].addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						handleClick(arg0);
					}
					@Override
					public void mouseEntered(MouseEvent arg0) {
					}

					@Override
					public void mouseExited(MouseEvent arg0) {

					}

					@Override
					public void mousePressed(MouseEvent arg0) {
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {

					}

				});
				grid[row][col].add(label);
			}
		}
	}

	private void disconnectRoomsIfValid(Room one, Room two) {
		if(one.getId() + 1 == two.getId()) {
			one.setRight(-1);
			two.setLeft(-1);
			System.out.println("r");
		} else if(one.getId() - 1 == two.getId()) {
			two.setRight(-1);
			one.setLeft(-1);
			System.out.println("l");
		} else if(one.getId() + grid.length == two.getId()) {
			one.setBottom(-1);
			two.setTop(-1);
			System.out.println("b");
		} else if(one.getId() - grid.length == two.getId()) {
			two.setBottom(-1);
			one.setTop(-1);
			System.out.println("t");
		} else {
			System.out.println("None");
		}
	}

	private void connectRoomsIfValid(Room one, Room two) {
		if(one.getId() + 1 == two.getId()) {
			one.setRight(two.getId());
			two.setLeft(one.getId());
			System.out.println("r");
		} else if(one.getId() - 1 == two.getId()) {
			two.setRight(one.getId());
			one.setLeft(two.getId());
			System.out.println("l");
		} else if(one.getId() + HEIGHT == two.getId()) {
			one.setBottom(two.getId());
			two.setTop(one.getId());
			System.out.println("b");
		} else if(one.getId() - HEIGHT == two.getId()) {
			two.setBottom(one.getId());
			one.setTop(two.getId());
			System.out.println("t");
		} else {
			System.out.println("None");
		}
		System.out.println(HEIGHT + ": " + one.getId() + ", " + two.getId());
		System.out.println(one.getId() + HEIGHT == two.getId());
	}

	private Room findRoomByID(int id) {
		Room[][] rooms = dungeon.getRooms();
		for(int row = 0; row < WIDTH; row++) {
			for(int col = 0; col < HEIGHT; col++) {
				if(rooms[row][col].getId() == id) {
					System.out.println("ID: " + rooms[row][col].getId());
					return rooms[row][col];
				}
			}
		}
		return null;
	}

	private void handleClick(MouseEvent arg0) {
		System.out.println("Comp " + arg0.getComponent().getClass().getSuperclass());
		IDJButton button = (IDJButton) arg0.getComponent();
		boolean same = findRoomByID(button.getId()).getId() == button.getId();
		System.out.println("Button = ID: " + same);
		System.out.println("room id: " + findRoomByID(button.getId()).getId());
		if(isConnecting) {
			if(connectedRooms[0] == null)	{
				((IDJButton)arg0.getComponent()).setBackground(Color.RED);
				connectedRooms[0] = findRoomByID(button.getId());
			} else {
				connectedRooms[1] = findRoomByID(button.getId());
				connectRoomsIfValid(connectedRooms[0],  connectedRooms[1]);
				connectedRooms = null;
				isConnecting = false;
				try {
					frame.dispose();
					DungeonViewer window = new DungeonViewer();
					window.frame.setVisible(true);
					initialize();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(isDisconnecting) {
			if(connectedRooms[0] == null)	{
				connectedRooms[0] = findRoomByID(button.getId());
			} else {
				connectedRooms[1] = findRoomByID(button.getId());
				disconnectRoomsIfValid(connectedRooms[0],  connectedRooms[1]);
				connectedRooms = null;
				isDisconnecting = false;
				try {
					frame.dispose();
					DungeonViewer window = new DungeonViewer();
					window.frame.setVisible(true);
					initialize();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println(connectedRooms);
		}
	}

}
