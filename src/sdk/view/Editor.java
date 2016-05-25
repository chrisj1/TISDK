package sdk.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sdk.core.Room;
import sdk.util.Drawing;

public class Editor
{

	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;

	private int lowestX;
	private int highestX;
	private int lowestY;
	private int highestY;

	private JFrame frame;

	private RoomButton[] rooms;

	/**
	 * Create the application.
	 */
	public Editor()
	{
		this.lowestX = 0;
		this.lowestY = 0;
		this.highestX = 0;
		this.highestY = 0;

		setUpFrame();
		try {
			drawFirstRoom();
		} catch (IOException e) {
			e.printStackTrace();
		}

		finalizeFrame();
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

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException
	 */
	private void drawFirstRoom() throws IOException
	{
		Room room = new Room(0);
		RoomButton rb = new RoomButton(room);
		JLabel label = new JLabel();
		Icon icon = Drawing.genIconFromRoom(room, 0);
		label.setIcon(icon);
		rb.setVisible(true);
		frame.getContentPane().setLayout(null);
		rb.add(label);
		JPanel panel = new JPanel();
		int x = WIDTH/2- icon.getIconWidth()/2;
		int y = HEIGHT/2 - icon.getIconHeight()/2;
		panel.setBounds(x,y,icon.getIconWidth()+30, icon.getIconHeight()+30);
		panel.add(rb);
		rb.addMouseListener(new ContextListener());
		frame.getContentPane().add(panel);

	}

	private void finalizeFrame()
	{
		frame.setVisible(true);
	}

	private void drawRoom()
	{

	}

	public RoomButton[] getRooms() {
		return rooms;
	}

	public void setRooms(RoomButton[] rooms) {
		this.rooms = rooms;
	}
}
