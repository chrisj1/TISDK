package sdk.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

import sdk.core.Room;

public class DungeonContextListener extends MouseAdapter{
	
	public void mousePressed(MouseEvent e){
		System.out.println(e.getXOnScreen());
		if(e.isPopupTrigger())
		{
			doPop(e);
		}
	}

	public void mouseReleased(MouseEvent e){
		if(e.isPopupTrigger())
		{
			doPop(e);
		}
	}

	private void doPop(MouseEvent e){
		RoomPanel room = ((RoomPanel)e.getSource());
		DungeonContextMenu menu = new DungeonContextMenu(room);
		menu.show(e.getComponent(), e.getX(), e.getY());
	}
}
