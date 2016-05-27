package sdk.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import sdk.core.Room;
import sdk.util.Saver;

public class Editor
{

	private static Editor editor;

	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;

	public static final int ROOM_WIDTH = WIDTH/16;
	public static final int ROOM_HEIGHT = HEIGHT/16;

	private JFrame frame;

	private ArrayList<RoomPanel> rooms;

	/**
	 * Create the application.
	 */
	public Editor()
	{
		this.rooms = new ArrayList<RoomPanel>();

		
		setUpFrame();		
		frame.getContentPane().setLayout(null);

		try {
			drawFirstRoom();
		} catch (IOException e) {
			e.printStackTrace();
		}

		finalizeFrame();

		setEditor(this);
	}

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

	private void drawFirstRoom() throws IOException
	{
		Room room = new Room(0, new Rectangle());
		RoomPanel pane = new RoomPanel(room);
		pane.refreshImage();
		int x = WIDTH/2- ROOM_WIDTH/2;
		int y = HEIGHT/2 - ROOM_HEIGHT/2;
		
		pane.setBounds(x,y,ROOM_WIDTH, ROOM_HEIGHT);
		pane.addMouseListener(new ContextListener());
		frame.getContentPane().add(pane);
		rooms.add(pane);
	}

	private void finalizeFrame()
	{
		frame.setVisible(true);
	}

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
		pane.addMouseListener(new ContextListener());
		frame.getContentPane().add(pane);
		rooms.add(pane);
		frame.repaint();
	}


	public ArrayList<RoomPanel> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<RoomPanel> rooms) {
		this.rooms = rooms;
	}

	public static Editor getEditor() {
		return editor;
	}

	public static void setEditor(Editor editor) {
		Editor.editor = editor;
	}
	
	public JFrame getFrame()
	{
		return frame;
	}

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
		Saver.saveRooms(rooms);
		
	}
}
