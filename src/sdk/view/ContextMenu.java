package sdk.view;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import sdk.core.Room;

public class ContextMenu extends JPopupMenu {

	JMenuItem addRoomAbove;
	JMenuItem addRoomBelow;
	JMenuItem addRoomRight;
	JMenuItem addRoomLeft;

    public ContextMenu(RoomPanel room){
        addRoomAbove = new JMenuItem("Add Room Above");
        addRoomAbove.addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Adding room");
				int id = Editor.getEditor().getRooms().size()-1;
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
        add(addRoomBelow);

        addRoomBelow = new JMenuItem("Add Room Right");
        add(addRoomBelow);

        addRoomBelow= new JMenuItem("Add Room Left");
        add(addRoomBelow);
    }
}
