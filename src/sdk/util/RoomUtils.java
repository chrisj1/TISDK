package sdk.util;

import java.awt.Point;

import sdk.view.DungeonEditor;
import sdk.view.RoomPanel;

public class RoomUtils
{

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
