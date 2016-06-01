package sdk.components;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

import sdk.core.Room;
import sdk.view.RoomPanel;

/**
 * A context menu listener for the mouse screen
 * @author Chris Jerrett
 *
 */
public class DungeonContextListener extends MouseAdapter
{
	/**
	 * Called when mouse pressed
	 * @param e the mouse event
	 */
	public void mousePressed(MouseEvent e)
	{
		if(e.isPopupTrigger())
		{
			pop(e);
		}
	}

	/**
	 * Called when mouse pressed
	 * @param e the mouse event
	 */
	public void mouseReleased(MouseEvent e)
	{
		if(e.isPopupTrigger())
		{
			pop(e);
		}
	}

	/**
	 * Pops a new context pane
	 * @param e the mouse event
	 */
	private void pop(MouseEvent e)
	{
		RoomPanel room = ((RoomPanel)e.getSource());
		DungeonContextMenu menu = new DungeonContextMenu(room);
		menu.show(e.getComponent(), e.getX(), e.getY());
	}
}
