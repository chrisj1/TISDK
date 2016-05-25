package sdk.view;

import javax.swing.JButton;

import sdk.core.Room;

public class RoomButton extends JButton {

	private Room room;

	public RoomButton(Room room) {
		this.room = room;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	
}
