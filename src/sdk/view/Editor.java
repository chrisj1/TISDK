package sdk.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
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

	private void drawFirstRoom() throws IOException
	{
		Room room = new Room(0);
		BufferedImage bi = Drawing.genBufferedImageFromRoom(room, 0);
		frame.getContentPane().setLayout(null);
		JPanel pane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(bi, 0, 0, null);
			}
		};
		int x = WIDTH/2- bi.getWidth()/2;
		int y = HEIGHT/2 - bi.getHeight()/2;
		pane.setBounds(x,y,bi.getWidth(), bi.getHeight());
		pane.addMouseListener(new ContextListener());
		frame.getContentPane().add(pane);

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
