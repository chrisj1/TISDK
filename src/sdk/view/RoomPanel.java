package sdk.view;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import sdk.core.Room;

public class RoomPanel extends JPanel {

	private Room room;
	
	
	public RoomPanel(Room room) {
		super();
		this.room = room;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	
	
}
