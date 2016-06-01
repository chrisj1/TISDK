package sdk.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import sdk.core.Room;
import sdk.util.Drawer;
/**
 * A panel for a Room
 * @author Chris Jerrett
 */
public class RoomPanel extends JPanel implements Comparable{

	private Room room;

	private Image image;

	/**
	 * Constructs a room panel
	 * @param room the room
	 */
	public RoomPanel(Room room) {
		super();
		this.room = room;
	}

	/**
	 * Paints the component
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

	/**
	 * Refreshes all the images
	 */
	public void refreshImage()
	{
		try {
			this.image = Drawer.genBufferedImageFromRoom(room)
					.getScaledInstance(DungeonEditor.ROOM_WIDTH,
							DungeonEditor.ROOM_HEIGHT,BufferedImage.SCALE_FAST);
			ImageIO.write(Drawer.toBufferedImage(image), "png", new File("test4.png"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Sets the image
	 * @param image the image
	 */
	public void setImage(Image image)
	{
		this.image = image;
	}

	/**
	 * gets the room
	 * @return the room
	 */
	public Room getRoom()
	{
		return room;
	}

	/**
	 * Sets the room
	 * @param room the room
	 */
	public void setRoom(Room room)
	{
		this.room = room;
	}


	@Override
	public int compareTo(Object o)
	{
		return room.compareTo(o);
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}
}
