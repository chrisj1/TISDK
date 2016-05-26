package sdk.util;

import java.awt.Point;

import sdk.view.Editor;
import sdk.view.RoomPanel;

public class RoomUtils
{

	public static RoomPanel findRoomOnScreen(int x, int y)
	{
		for(RoomPanel room: Editor.getEditor().getRooms())
		{
			
			if(room.getBounds().contains(new Point(x,y)))
			{
				return room;
			}
		}
		return null;
	}

}
