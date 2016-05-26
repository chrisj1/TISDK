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
		frame.getContentPane().setLayout(null);
		RoomPanel pane = new RoomPanel(room) {
			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawImage(bi, 0, 0, null);
			}
		};
		int x = WIDTH/2- bi.getWidth()/2;
		int y = HEIGHT/2 - bi.getHeight()/2;
		pane.setBounds(x,y,bi.getWidth(), bi.getHeight());
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

		Image bi = Drawer.genBufferedImageFromRoom(room.getRoom(), rooms.size()-1).getScaledInstance(ROOM_WIDTH, ROOM_HEIGHT, BufferedImage.SCALE_FAST);

		RoomPanel pane = new RoomPanel(room.getRoom())
		{
			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawImage(bi, 0, 0, null);
			}
		};
		pane.setBounds(room.getBounds());
		pane.addMouseListener(new ContextListener());
		frame.getContentPane().add(pane);
		rooms.add(pane);
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
}
