package sdk.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import sdk.core.Room;

public class ContextListener extends MouseAdapter{

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
		ContextMenu menu = new ContextMenu(room);
		menu.show(e.getComponent(), e.getX(), e.getY());
	}
}
