package sdk.core;

public class Dungeon {

	private String name;
	private Room[][] rooms;
	
	public Dungeon(Room[][] rooms) {
		this.rooms = rooms;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Room[][] getRooms() {
		return rooms;
	}
	public void setRooms(Room[][] rooms) {
		this.rooms = rooms;
	}

}
