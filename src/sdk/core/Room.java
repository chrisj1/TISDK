package sdk.core;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Room implements Comparable{

	/**
	 * Represents the list of floor assets
	 * @author Chris Jerrett
	 */
	public enum Floors
	{
		GRASS_FLOOR("GrassFloor.png");

		public final String file;
		Floors(String file)
		{
			this.file = file;
		}
	};

	public enum Walls
	{
		BLACK_WALL("BlackWall.png");
		//BRICK_WALL("CastleWall.png"),
		//MOSSY_WALL("MossyWall.png");

		public final String file;
		Walls(String file)
		{
			this.file = file;
		}
	};

	private Walls wall;
	private Floors floor;

	private int top;
	private int bottom;
	private int left;
	private int right;
	private int id;
	ArrayList<Entity> entities;

	private int x;
	private int y;

	public Room(int id, Rectangle bounds)
	{
		this.floor = Floors.GRASS_FLOOR;
		this.wall = Walls.BLACK_WALL;
		this.id = id;
		this.bottom = -1;
		this.left = -1;
		this.right = -1;
		this.top = -1;
		this.entities = new ArrayList<Entity>();
	}

	public boolean hasConnections()
	{
		return getTop() != -1 ||
				getRight() != -1 ||
				getLeft() != -1 ||
				getBottom() != -1;
	}


	/**
	 * @return the top
	 */
	public int getTop()
	{
		return top;
	}

	@Override
	public String toString() {
		return "Room [top=" + top + ", bottom=" + bottom + ", left=" + left
				+ ", right=" + right + ", id=" + id + ", entities=" + entities
				+ "]";
	}

	/**
	 * @return the bottom
	 */
	public int getBottom()
	{
		return bottom;
	}

	/**
	 * @return the left
	 */
	public int getLeft()
	{
		return left;
	}

	/**
	 * @return the right
	 */
	public int getRight()
	{
		return right;
	}

	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @param top the top to set
	 */
	public void setTop(int top)
	{
		this.top = top;
	}

	/**
	 * @param bottom the bottom to set
	 */
	public void setBottom(int bottom)
	{
		this.bottom = bottom;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(int left)
	{
		this.left = left;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(int right)
	{
		this.right = right;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * adds a entity to the room
	 * @param ent
	 */
	public void addEntity(Entity ent)
	{
		this.entities.add(ent);
	}

	/**
	 * @return the entities
	 */
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	/**
	 * gets the x
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x
	 * @param x the x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * gets the y
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the Y
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Checks to see if two rooms are equal
	 * @param obj the object to compare
	 */
	@Override
	public boolean equals(Object obj) {
		Room room2 = (Room) obj;
		return (top == room2.getTop() && bottom == room2.getBottom() &&
				left == room2.getLeft() && right == room2.getRight() &&
				id == room2.getId());
	}

	/**
	 * Compares the ids
	 */
	@Override
	public int compareTo(Object arg0) {
		Room room = (Room) arg0;
		return this.id - room.id;
	}

	/**
	 * Gets the wall
	 * @return the wall
	 */
	public Walls getWall() {
		return wall;
	}

	/**
	 * sets the wall
	 * @param wall the wall
	 */
	public void setWall(Walls wall) {
		this.wall = wall;
	}

	/**
	 * gets the floor
	 * @return the floor
	 */
	public Floors getFloor() {
		return floor;
	}

	/**
	 * sets the floor
	 * @param floor the floor
	 */
	public void setFloor(Floors floor) {
		this.floor = floor;
	}
}
