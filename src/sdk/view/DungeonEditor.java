package sdk.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import sdk.components.DungeonContextListener;
import sdk.core.Room;
import sdk.util.Drawer;
import sdk.util.Saver;

/**
 * The main frame control app
 * @author Chris Jerrett
 */
public class DungeonEditor
{

	/**
	 * The state of the app
	 * @author Chris Jerrett
	 */
	public enum State {
		MAP(),
		ROOM
	}

	private static DungeonEditor editor;

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;

	public static final int ROOM_WIDTH = WIDTH/16;
	public static final int ROOM_HEIGHT = HEIGHT/16;

	public static final int MENU_BAR_HEIGHT = 50;

	private JFrame frame;

	private RoomPanel zoomedPanel;
	private ArrayList<RoomPanel> rooms;

	private static State state;

	private RoomPanel enteredRoom;

	/**
	 * Create the application.
	 */
	public DungeonEditor()
	{
		this.enteredRoom = null;
		this.rooms = new ArrayList<RoomPanel>();
		state = State.MAP;

		setUpFrame();
		frame.getContentPane().setLayout(null);

		try {
			drawFirstRoom();
		} catch (IOException e) {
			e.printStackTrace();
		}

		setUpSaveButton();
		finalizeFrame();

		editor = this;
	}

	/**
	 * Sets up the menubar
	 */
	private void setUpSaveButton() 
	{
		JButton save = new JButton("Save");
		final int X = 10;
		final int Y = 10;
		final int BUTTON_WIDTH = 70;
		final int BUTTON_HEIGHT = 50;
		save.setBounds(new Rectangle(X,Y,BUTTON_WIDTH,BUTTON_HEIGHT));

		save.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				ArrayList<Room> roomsArray = new ArrayList<Room>();
				for(RoomPanel panel : rooms)
				{
					roomsArray.add(panel.getRoom());
				}
				Saver.saveRooms(roomsArray);
			}
		});
		frame.add(save);

		frame.repaint();
		SwingUtilities.updateComponentTreeUI(frame);
	}

	/**
	 * Sets up the frame
	 */
	private void setUpFrame()
	{
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("TI SDK\r\n");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int topRightx = (screenSize.width/2 - WIDTH/2);
		int topRighty = (screenSize.height - HEIGHT)/2;

		frame.setBounds(topRightx, topRighty, WIDTH, HEIGHT + MENU_BAR_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setBackground(Color.BLACK);
		frame.getContentPane().setBackground(Color.GRAY);
	}

	/**
	 * Draws the first room
	 * @throws IOException
	 */
	private void drawFirstRoom() throws IOException
	{
		Room room = new Room(0, new Rectangle());
		RoomPanel pane = new RoomPanel(room);
		pane.refreshImage();
		int x = WIDTH/2- ROOM_WIDTH/2;
		int y = HEIGHT/2 - ROOM_HEIGHT/2;

		pane.setBounds(x,y,ROOM_WIDTH, ROOM_HEIGHT);
		pane.addMouseListener(new DungeonContextListener());
		frame.getContentPane().add(pane);
		rooms.add(pane);
	}

	/**
	 * finalizes the frame
	 */
	private void finalizeFrame()
	{
		frame.setVisible(true);
	}

	/**
	 * Adds a room panel to the map
	 * @param room the room panel
	 * @throws IOException
	 */
	public void addRoom(RoomPanel room) throws IOException
	{

		for(RoomPanel panel : this.rooms)
		{
			if(panel.getBounds().intersects(room.getBounds()))
			{
				System.out.println("Panel already exists or intersects");
				return;
			}

		}

		RoomPanel pane = new RoomPanel(room.getRoom());
		pane.refreshImage();
		pane.setBounds(room.getBounds());
		pane.addMouseListener(new DungeonContextListener());
		frame.getContentPane().add(pane);
		rooms.add(pane);
		frame.repaint();
	}

	/**
	 * UPdates the frame and the rooms
	 */
	public void update()
	{
		for(RoomPanel room : rooms)
		{
			room.refreshImage();
		}
		frame.repaint();

		ArrayList<Room> rooms = new ArrayList<Room>();
		for(RoomPanel panel : this.rooms)
		{
			rooms.add(panel.getRoom());
		}

		if(enteredRoom != null)
		{
			enteredRoom.refreshImage();
			enteredRoom.setImage(enteredRoom.getImage().getScaledInstance(WIDTH, HEIGHT, BufferedImage.SCALE_FAST));
			enteredRoom.repaint();
		}
	}
	/**
	 * Enters a room to edit
	 * @param room the room panel to edit
	 */
	public void enterRoom(RoomPanel room)
	{
		final JButton btExit = new JButton("Exit");
		
		final int X = 90;
		final int Y = 10;
		final int BUTTON_WIDTH = 70;
		final int BUTTON_HEIGHT = 50;
		btExit.setBounds(new Rectangle(X,Y,BUTTON_WIDTH,BUTTON_HEIGHT));
		
		btExit.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
					exitRoom();
					frame.remove(btExit);
			}
			
		});
		
		frame.add(btExit);
		
		enteredRoom = room;
		state = State.ROOM;
		Rectangle bounds = new Rectangle(0,0, DungeonEditor.WIDTH, DungeonEditor.HEIGHT);
		RoomPanel clone = room.clone(room);
		clone.setBounds(bounds);
		try {
			clone.setImage(Drawer.genBufferedImageFromRoom(room.getRoom())
					.getScaledInstance(WIDTH,HEIGHT,BufferedImage.SCALE_FAST));
		} catch (IOException e) {
			e.printStackTrace();
		}

		clone.repaint();
		clone.addMouseListener(new DungeonContextListener());
		for(RoomPanel panel: rooms)
		{
			frame.remove(panel);
		}
		frame.add(clone);
		SwingUtilities.updateComponentTreeUI(frame);
		enteredRoom = clone;
		setUpSaveButton();
	}

	public void exitRoom()
	{
		if(state == State.ROOM)
		{
			this.state = State.MAP;
			for(RoomPanel panel: rooms)
			{
				panel.refreshImage();
				frame.getContentPane().remove(enteredRoom);
				frame.getContentPane().add(panel);
				panel.repaint();
			}
			update();
			this.enteredRoom = null;
		}
	}

	/**
	 * Gets the rooms
	 * @return the rooms
	 */
	public ArrayList<RoomPanel> getRooms() {
		return rooms;
	}

	/**
	 * Sets the rooms
	 * @param rooms
	 */
	public void setRooms(ArrayList<RoomPanel> rooms) {
		this.rooms = rooms;
	}

	/**
	 * gets this dungeon editor
	 * @return
	 */
	public static DungeonEditor getEditor() {
		return editor;
	}

	/**
	 * Gets the JFrame
	 * @return the frame
	 */
	public JFrame getFrame()
	{
		return frame;
	}

	/**
	 * Gets the state
	 * @return the state
	 */
	public static State getState() {
		return state;
	}

	/**
	 * Sets the state
	 * @param state the state
	 */
	public static void setState(State state) {
		DungeonEditor.state = state;
	}

	/**
	 * @return the enteredRoom
	 */
	public RoomPanel getEnteredRoom() {
		return enteredRoom;
	}

	/**
	 * @param enteredRoom the enteredRoom to set
	 */
	public void setEnteredRoom(RoomPanel enteredRoom) {
		this.enteredRoom = enteredRoom;
	}
}
