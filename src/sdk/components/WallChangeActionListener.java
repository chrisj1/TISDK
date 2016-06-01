package sdk.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import sdk.core.Room.Walls;
import sdk.util.Drawer;
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
		try {
			panel.setImage(Drawer.genBufferedImageFromRoom(panel.getRoom())
					.getScaledInstance(DungeonEditor.WIDTH, DungeonEditor.HEIGHT, BufferedImage.SCALE_FAST));
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel.repaint();
	}

}
