package sdk.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import sdk.core.Entity;
import sdk.core.Entity.EntityType;
import sdk.core.Room.Floors;
import sdk.util.Drawer;
import sdk.view.DungeonEditor;
import sdk.view.RoomPanel;

/**
 * A listener for when the user adds a entity
 * 
 * @author Chris Jerrett
 * 		
 */
public class EntityChangeActionListener implements ActionListener
{
	
	private EntityType ent;
	
	/**
	 * Constructs a new listener
	 * 
	 * @param ent
	 */
	public EntityChangeActionListener(EntityType ent)
	{
		this.ent = ent;
	}
	
	/**
	 * Called when action performed and adds new entity at random loc
	 */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		RoomPanel panel = DungeonEditor.getEditor().getEnteredRoom();
		final int ROOM_MIN_X = 0;
		final int ROOM_MIN_Y = 0;
		final int ROOM_MAX_X = 512 * 2;
		final int ROOM_MAX_Y = 288 * 2;
		final int PADDING = 64;
		panel.getRoom().addEntity(new Entity(ent, ROOM_MIN_X + PADDING, ROOM_MIN_Y + PADDING, ROOM_MAX_X - PADDING,
		        ROOM_MAX_Y - PADDING));
	}
	
}
