package sdk.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sdk.core.Room.Floors;
import sdk.view.DungeonEditor;
import sdk.view.RoomPanel;

public class FloorChangeActionListener implements ActionListener 
{

	private Floors floor;
	
	public FloorChangeActionListener(Floors floor)
	{
		this.floor = floor;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		RoomPanel panel =  DungeonEditor.getEditor().getEnteredRoom();
		panel.getRoom().setFloor(floor);
		DungeonEditor.getEditor().update();
		panel.repaint();
	}

}
