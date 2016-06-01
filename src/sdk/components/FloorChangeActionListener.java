package sdk.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import sdk.core.Room.Floors;
import sdk.util.Drawer;
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
		try {
			panel.setImage(Drawer.genBufferedImageFromRoom(panel.getRoom())
					.getScaledInstance(DungeonEditor.WIDTH, DungeonEditor.HEIGHT, BufferedImage.SCALE_FAST));
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel.repaint();
	}

}
