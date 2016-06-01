package sdk.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

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

	private JFrame frame;

	private ArrayList<RoomPanel> rooms;
	
	private Container dungeonCon;
	
	private static State state;

	/**
	 * Create the application.
	 */
	public DungeonEditor()
	{
		this.rooms = new ArrayList<RoomPanel>();
		state = State.MAP;
		
		setUpFrame();		
		frame.getContentPane().setLayout(null);

		try {
			drawFirstRoom();
		} catch (IOException e) {
			e.printStackTrace();
		}

		finalizeFrame();

		editor = this;
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

		frame.setBounds(topRightx, topRighty, WIDTH, HEIGHT);
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
	}
	/**
	 * Enters a room to edit
	 * @param room the room panel to edit
	 */
	public void enterRoom(RoomPanel room)
	{
		state = State.ROOM;
		dungeonCon = frame.getContentPane();
		Container container = new Container();
		Rectangle bounds = new Rectangle(0,0, DungeonEditor.WIDTH, DungeonEditor.HEIGHT);
		room.setBounds(bounds);
		try {
			room.setImage(Drawer.genBufferedImageFromRoom(room.getRoom())
					.getScaledInstance(WIDTH,HEIGHT,BufferedImage.SCALE_FAST));
		} catch (IOException e) {
			e.printStackTrace();
		}
		container.add(room);
		frame.setContentPane(container);
		SwingUtilities.updateComponentTreeUI(frame);
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
}
