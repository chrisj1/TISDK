package sdk.core;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Room {

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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}	

}
