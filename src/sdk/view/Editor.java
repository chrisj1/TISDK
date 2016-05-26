package sdk.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ScrollPaneLayout;

import sdk.core.Room;
import sdk.util.Drawer;

public class Editor
{

	private static Editor editor;

	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;

	public static final int ROOM_WIDTH = WIDTH/10;
	public static final int ROOM_HEIGHT = HEIGHT/10;

	private int lowestX;
	private int highestX;
	private int lowestY;
	private int highestY;

	private JFrame frame;

	private ArrayList<RoomPanel> rooms;

	/**
	 * Create the application.
	 */
	public Editor()
	{
		this.lowestX = 0;
		this.lowestY = 0;
		this.highestX = 0;
		this.highestY = 0;
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
		frame.getContentPane().setBackground(Color.BLACK);
	}

	private void drawFirstRoom() throws IOException
	{
		Room room = new Room(0, new Rectangle());
		//MUST be done in one line or will not compile do to
		//inner class
		BufferedImage bi =  Drawer.toBufferedImage(Drawer.genBufferedImageFromRoom(room, 0)
				.getScaledInstance(ROOM_WIDTH, ROOM_HEIGHT, BufferedImage.SCALE_FAST));
		RoomPanel pane = new RoomPanel(room);
		pane.refreshImage();
		int x = WIDTH/2- ROOM_WIDTH/2;
		int y = HEIGHT/2 - ROOM_HEIGHT/2;
		
		pane.setBounds(x,y,ROOM_WIDTH, ROOM_HEIGHT);
		System.out.println(pane.getBounds());
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
			System.out.println(panel);
		}
		
		for(RoomPanel panel : this.rooms)
		{
			if(panel.getBounds().intersects(room.getBounds()))
			{
				System.out.println("Panel already exists or intersects");
				return;
			}

		}

		System.out.println(room);
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
}
