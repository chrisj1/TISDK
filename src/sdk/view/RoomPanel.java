package sdk.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;

import sdk.core.Room;
import sdk.util.Drawer;

public class RoomPanel extends JPanel implements Comparable{

	private Room room;
	
	private Image image;
	
	public RoomPanel(Room room) {
		super();
		this.room = room;
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{			
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
	
	public void refreshImage()
	{
		try {
			this.image = Drawer.genBufferedImageFromRoom(room, room.getId()).getScaledInstance(Editor.ROOM_WIDTH, Editor.ROOM_HEIGHT, BufferedImage.SCALE_FAST);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setImage(Image image)
	{
		this.image = image;
	}

	public Room getRoom() 
	{
		return room;
	}

	public void setRoom(Room room) 
	{
		this.room = room;
	}

	@Override
	public String toString() 
	{
		return "RoomPanel [room=" + room + ", image=" + image + ", bounds=" + getBounds() + "]";
	}

	@Override
	public int compareTo(Object o) 
	{
		return room.compareTo(o);
	}
	
	
	
}
