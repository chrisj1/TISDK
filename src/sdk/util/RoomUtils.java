package sdk.util;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import sdk.core.Room;
import sdk.core.Room.Floors;
import sdk.core.Room.Walls;
import sdk.view.DungeonEditor;
import sdk.view.RoomPanel;

/**
 * Basic room utilies
 * @author Chris Jerrett
 */
public class RoomUtils
{

	public static BufferedImage genKeyBufferedImageFromRoom(Room room)
	{
		BufferedImage bi = new BufferedImage(16, 9, BufferedImage.TYPE_INT_RGB);

		int wall = new Color(room.getWall().id, room.getWall().id, 255).getRGB();
		int tile = new Color(room.getFloor().id, room.getFloor().id, 0).getRGB();

		for(int row = 0; row < bi.getHeight(); row++) {
			for(int col = 0; col < bi.getWidth(); col++) {
				if(row != 0 && row != bi.getHeight()-1 && col != 0 && col != bi.getWidth()-1)
				{
					bi.setRGB(col, row, tile);
				}
				else {
					bi.setRGB(col, row, wall);
				}
			}
		}
		if(room.getTop() != -1)
		{
			for(int i = 7; i < 9; i++)
			{
				bi.setRGB(i, 0, tile);
			}
		}

		if(room.getBottom() != -1)
		{
			for(int i = 7; i < 9; i++)
			{
				bi.setRGB(i, bi.getHeight()-1, tile);
			}
		}

		if(room.getLeft() != -1)
		{
			for(int i = 3; i < 6; i++) {
				bi.setRGB(0, i, tile);
			}
		}

		if(room.getRight() != -1)
		{
			for(int i = 3; i < 6; i++) {
				bi.setRGB(bi.getWidth()-1, i, tile);
			}
		}
		return bi;
	}

	/**
	 * Finds what roompanel a mouse is on
	 * @param x the x
	 * @param y the y
	 * @return a room panel
	 */
	public static RoomPanel findRoomOnScreen(int x, int y)
	{
		for(RoomPanel room: DungeonEditor.getEditor().getRooms())
		{

			if(room.getBounds().contains(new Point(x,y)))
			{
				return room;
			}
		}
		return null;
	}

}
