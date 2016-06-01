package sdk.components;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import sdk.core.Entity;
import sdk.core.Entity.EntityType;
import sdk.core.Room;
import sdk.core.Room.Floors;
import sdk.core.Room.Walls;
import sdk.util.RoomUtils;
import sdk.view.DungeonEditor;
import sdk.view.RoomPanel;
import sdk.view.DungeonEditor.State;

/**
 * A dungeon context menu
 * @author Chris Jerrett
 *
 */
public class DungeonContextMenu extends JPopupMenu
{
	/**
	 * Creates a new popup menu
	 * @param room the room panel that is modified
	 */
	public DungeonContextMenu(RoomPanel room)
    {
    	if(DungeonEditor.getState() == State.MAP)
    	{
    		setUpMapMenu(room);
    	}
    	else
    	{
    		setUpRoomMenu(room);
    	}
    }

	/**
	 * Setsup the menu when editing a single room
	 * @param room
	 */
	private void setUpRoomMenu(RoomPanel room) 
	{
		for(Floors floor : Floors.values())
		{
			JMenuItem menuItem = new JMenuItem(floor.name());
			menuItem.addActionListener(new FloorChangeActionListener(floor));
			add(menuItem);
		}
		addSeparator();
		for(Walls wall : Walls.values())
		{
			JMenuItem menuItem = new JMenuItem(wall.name());
			menuItem.addActionListener(new WallChangeActionListener(wall));
			add(menuItem);
		}
		
		addSeparator();
		
		for(EntityType ent : EntityType.values())
		{
			JMenuItem menuItem = new JMenuItem(ent.name());
			menuItem.addActionListener(new EntityChangeActionListener(ent));
			add(menuItem);
		}
		
	}

	/**
	 * Adds Buttons and actions when in map view
	 * @param room the room panel
	 */
	private void setUpMapMenu(final RoomPanel room) 
	{
		JMenuItem addRoomAbove;
		JMenuItem addRoomBelow;
		JMenuItem addRoomRight;
		JMenuItem addRoomLeft;

		JMenuItem enterRoom;

		enterRoom = new JMenuItem("Edit Room");
    	enterRoom.addActionListener(new ActionListener()
    	{
    		@Override
			public void actionPerformed(ActionEvent arg0) {
    			DungeonEditor.getEditor().enterRoom(room);
			}
    	});
    	add(enterRoom);

        addRoomAbove = new JMenuItem("Add Room Above");
        addRoomAbove.addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int id = DungeonEditor.getEditor().getRooms().size();
				Rectangle panelBounds = room.getBounds();

				int x = (int) panelBounds.getX();
				int y = (int) (panelBounds.getY() - panelBounds.getHeight());
				Rectangle bounds = new Rectangle(x,y, (int)panelBounds.getWidth(), (int)panelBounds.getHeight());

				Room room = new Room(id, bounds);

				RoomPanel panel = new RoomPanel(room);
				panel.setBounds(bounds);
				try {
					DungeonEditor.getEditor().addRoom(panel);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

        });
        add(addRoomAbove);

        addRoomBelow = new JMenuItem("Add Room Below");
        addRoomBelow.addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int id = DungeonEditor.getEditor().getRooms().size();
				Rectangle panelBounds = room.getBounds();

				int x = (int) panelBounds.getX();
				int y = (int) (panelBounds.getY() + panelBounds.getHeight());
				Rectangle bounds = new Rectangle(x,y, (int)panelBounds.getWidth(), (int)panelBounds.getHeight());

				Room room = new Room(id, bounds);

				RoomPanel panel = new RoomPanel(room);
				panel.setBounds(bounds);
				try {
					DungeonEditor.getEditor().addRoom(panel);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

        });
        add(addRoomBelow);

        addRoomRight = new JMenuItem("Add Room Right");
        addRoomRight.addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int id = DungeonEditor.getEditor().getRooms().size();
				Rectangle panelBounds = room.getBounds();

				int x = (int) (panelBounds.getX() + panelBounds.getWidth());
				int y = (int) (panelBounds.getY());
				Rectangle bounds = new Rectangle(x,y, (int)panelBounds.getWidth(), (int)panelBounds.getHeight());

				Room room = new Room(id, bounds);

				RoomPanel panel = new RoomPanel(room);
				panel.setBounds(bounds);
				try {
					DungeonEditor.getEditor().addRoom(panel);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

        });
        add(addRoomRight);

        addRoomLeft= new JMenuItem("Add Room Left");
        addRoomLeft.addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int id = DungeonEditor.getEditor().getRooms().size();
				Rectangle panelBounds = room.getBounds();

				int x = (int) (panelBounds.getX() - panelBounds.getWidth());
				int y = (int) (panelBounds.getY());
				Rectangle bounds = new Rectangle(x,y, (int)panelBounds.getWidth(), (int)panelBounds.getHeight());

				Room room = new Room(id, bounds);

				RoomPanel panel = new RoomPanel(room);
				panel.setBounds(bounds);
				try {
					DungeonEditor.getEditor().addRoom(panel);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

        });
        add(addRoomLeft);

        addSeparator();

        final int x = room.getBounds().x;
        final int y = room.getBounds().y;

        if(RoomUtils.findRoomOnScreen(x, y - DungeonEditor.ROOM_HEIGHT) != null)
        {
        	JMenuItem connectAbove = new JMenuItem("Connect Above");
        	connectAbove.addActionListener(new ActionListener()
        	{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					RoomPanel top = RoomUtils.findRoomOnScreen(x, y - DungeonEditor.ROOM_HEIGHT);
					room.getRoom().setTop(top.getRoom().getId());
					top.getRoom().setBottom(room.getRoom().getId());
					room.refreshImage();
					top.refreshImage();
					DungeonEditor.getEditor().update();
				}
        	});
        	add(connectAbove);
        }
        if(RoomUtils.findRoomOnScreen(x, y + DungeonEditor.ROOM_HEIGHT) != null)
        {
        	JMenuItem connectBelow = new JMenuItem("Connect Below");
        	connectBelow.addActionListener(new ActionListener()
        	{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					RoomPanel bottom = RoomUtils.findRoomOnScreen(x, y + DungeonEditor.ROOM_HEIGHT);
					System.out.println(room);
					System.out.println(bottom);
					room.getRoom().setBottom(bottom.getRoom().getId());
					bottom.getRoom().setTop(room.getRoom().getId());
					DungeonEditor.getEditor().update();
				}
        	});
        	add(connectBelow);
        }
        if(RoomUtils.findRoomOnScreen(x + DungeonEditor.ROOM_WIDTH, y) != null)
        {
        	JMenuItem connectRight = new JMenuItem("Connect Right");
        	connectRight.addActionListener(new ActionListener()
        	{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					RoomPanel right = RoomUtils.findRoomOnScreen(x + DungeonEditor.ROOM_WIDTH + 1, y + 1);
					room.getRoom().setRight(right.getRoom().getId());
					right.getRoom().setLeft(room.getRoom().getId());
					room.refreshImage();
					right.refreshImage();
					DungeonEditor.getEditor().update();
				}
        	});
        	add(connectRight);
        }
        if(RoomUtils.findRoomOnScreen(x - DungeonEditor.ROOM_WIDTH, y) != null)
        {
        	JMenuItem connectLeft = new JMenuItem("Connect Left");
        	connectLeft.addActionListener(new ActionListener()
        	{
				@Override
				public void actionPerformed(ActionEvent arg0) {
					RoomPanel left = RoomUtils.findRoomOnScreen(x - DungeonEditor.ROOM_WIDTH + 1, y + 1);
					room.getRoom().setLeft(left.getRoom().getId());
					left.getRoom().setRight(room.getRoom().getId());
					room.refreshImage();
					left.refreshImage();
					DungeonEditor.getEditor().update();
				}
        	});
        	add(connectLeft);
        }
	}
}
