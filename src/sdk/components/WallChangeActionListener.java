package sdk.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sdk.core.Room.Walls;
import sdk.view.DungeonEditor;
import sdk.view.RoomPanel;

public class WallChangeActionListener implements ActionListener 
{

	private Walls wall;
	
	public WallChangeActionListener(Walls wall)
	{
		this.wall = wall;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		RoomPanel panel =  DungeonEditor.getEditor().getEnteredRoom();
		panel.getRoom().setWall(wall);
		DungeonEditor.getEditor().update();
		panel.repaint();
	}

}
