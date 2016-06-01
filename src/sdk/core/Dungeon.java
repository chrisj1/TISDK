package sdk.core;

/**
 * Represents a dungeon with an [] of Rooms
 * @author Chris Jerrett
 *
 */
public class Dungeon 
{
	private Room[] rooms;
	
	/**
	 * Constructs a new Dungeon with a array of rooms
	 * @param rooms
	 */
	public Dungeon(Room[] rooms) 
	{
		this.rooms = rooms;
	}
	
	/**
	 * Gets the rooms ion the dungeon
	 * @return
	 */
	public Room[] getRooms() 
	{
		return rooms;
	}
	/**
	 * Sets the rooms in the Dungeon
	 * @param rooms
	 */
	public void setRooms(Room[] rooms) 
	{
		this.rooms = rooms;
	}

}
