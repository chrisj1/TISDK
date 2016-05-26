package sdk.view;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import sdk.core.Room;
import sdk.util.RoomUtils;

public class ContextMenu extends JPopupMenu 
{
	
	JMenuItem addRoomAbove;
	JMenuItem addRoomBelow;
	JMenuItem addRoomRight;
	JMenuItem addRoomLeft;

    public ContextMenu(final RoomPanel room)
    {
        addRoomAbove = new JMenuItem("Add Room Above");
        addRoomAbove.addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int id = Editor.getEditor().getRooms().size();
				Rectangle panelBounds = room.getBounds();

				int x = (int) panelBounds.getX();
				int y = (int) (panelBounds.getY() - panelBounds.getHeight());
				Rectangle bounds = new Rectangle(x,y, (int)panelBounds.getWidth(), (int)panelBounds.getHeight());

				Room room = new Room(id, bounds);

				RoomPanel panel = new RoomPanel(room);
				panel.setBounds(bounds);
				try {
					Editor.getEditor().addRoom(panel);
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
				int id = Editor.getEditor().getRooms().size();
				Rectangle panelBounds = room.getBounds();

				int x = (int) panelBounds.getX();
				int y = (int) (panelBounds.getY() + panelBounds.getHeight());
				Rectangle bounds = new Rectangle(x,y, (int)panelBounds.getWidth(), (int)panelBounds.getHeight());

				Room room = new Room(id, bounds);

				RoomPanel panel = new RoomPanel(room);
				panel.setBounds(bounds);
				try {
					Editor.getEditor().addRoom(panel);
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
				int id = Editor.getEditor().getRooms().size();
				Rectangle panelBounds = room.getBounds();

				int x = (int) (panelBounds.getX() + panelBounds.getWidth());
				int y = (int) (panelBounds.getY());
				Rectangle bounds = new Rectangle(x,y, (int)panelBounds.getWidth(), (int)panelBounds.getHeight());

				Room room = new Room(id, bounds);

				RoomPanel panel = new RoomPanel(room);
				panel.setBounds(bounds);
				try {
					Editor.getEditor().addRoom(panel);
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
				int id = Editor.getEditor().getRooms().size();
				Rectangle panelBounds = room.getBounds();

				int x = (int) (panelBounds.getX() - panelBounds.getWidth());
				int y = (int) (panelBounds.getY());
				Rectangle bounds = new Rectangle(x,y, (int)panelBounds.getWidth(), (int)panelBounds.getHeight());

				Room room = new Room(id, bounds);

				RoomPanel panel = new RoomPanel(room);
				panel.setBounds(bounds);
				try {
					Editor.getEditor().addRoom(panel);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

        });
        add(addRoomLeft);

        addSeparator();

        final int x = room.getBounds().x;
        final int y = room.getBounds().y;
        
        if(RoomUtils.findRoomOnScreen(x, y - Editor.ROOM_HEIGHT) != null)
        {
        	JMenuItem connectAbove = new JMenuItem("Connect Above");
        	connectAbove.addActionListener(new ActionListener()
        	{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					RoomPanel top = RoomUtils.findRoomOnScreen(x, y - Editor.ROOM_HEIGHT);
					System.out.println(room);
					System.out.println(top);
					room.getRoom().setTop(top.getRoom().getId());
					top.getRoom().setBottom(room.getRoom().getId());
					room.refreshImage();
					top.refreshImage();
					Editor.getEditor().update();
				}
        	});
        	add(connectAbove);
        }
        if(RoomUtils.findRoomOnScreen(x, y + Editor.ROOM_HEIGHT) != null)
        {
        	JMenuItem connectBelow = new JMenuItem("Connect Below");
        	connectBelow.addActionListener(new ActionListener()
        	{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					RoomPanel bottom = RoomUtils.findRoomOnScreen(x, y + Editor.ROOM_HEIGHT);
					System.out.println(room);
					System.out.println(bottom);
					room.getRoom().setBottom(bottom.getRoom().getId());
					bottom.getRoom().setTop(room.getRoom().getId());
					room.refreshImage();
					bottom.refreshImage();
					Editor.getEditor().update();
				}
        	});
        	add(connectBelow);
        }
        if(RoomUtils.findRoomOnScreen(x + Editor.ROOM_WIDTH, y) != null)
        {
        	JMenuItem connectRight = new JMenuItem("Connect Right");
        	connectRight.addActionListener(new ActionListener()
        	{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					RoomPanel right = RoomUtils.findRoomOnScreen(x + Editor.ROOM_WIDTH + 1, y + 1);
					System.out.println(room);
					System.out.println(right);
					room.getRoom().setRight(right.getRoom().getId());
					right.getRoom().setLeft(room.getRoom().getId());
					room.refreshImage();
					right.refreshImage();
					Editor.getEditor().update();
				}
        	});
        	add(connectRight);
        }
        if(RoomUtils.findRoomOnScreen(x - Editor.ROOM_WIDTH, y) != null)
        {
        	JMenuItem connectLeft = new JMenuItem("Connect Left");
        	connectLeft.addActionListener(new ActionListener()
        	{
				@Override
				public void actionPerformed(ActionEvent arg0) {
					RoomPanel left = RoomUtils.findRoomOnScreen(x - Editor.ROOM_WIDTH + 1, y + 1);
					System.out.println(room);
					System.out.println(left);
					room.getRoom().setLeft(left.getRoom().getId());
					left.getRoom().setRight(room.getRoom().getId());
					room.refreshImage();
					left.refreshImage();
					Editor.getEditor().update();
				}
        	});
        	add(connectLeft);
        }
    }
}
