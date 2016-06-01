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

public class EntityChangeActionListener implements ActionListener 
{

	private EntityType ent;
	
	public EntityChangeActionListener(EntityType ent)
	{
		this.ent = ent;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		RoomPanel panel =  DungeonEditor.getEditor().getEnteredRoom();
		panel.getRoom().addEntity(new Entity(ent, 0, 0, 100, 100));
	}

}
