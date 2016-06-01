package sdk.util;

import java.awt.Point;

import sdk.view.DungeonEditor;
import sdk.view.RoomPanel;

/**
 * Basic room utilies
 * @author Chris Jerrett
 */
public class RoomUtils
{

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
