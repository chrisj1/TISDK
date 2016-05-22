package sdk.core;

import java.util.Random;

public class Entity
{

	public enum EntityType
	{
		KNIGHT("Knight"),
		DRAGON("Dragon"),
		INQUISITOR("Inquisitor");

		private String name;
		private EntityType(String name)
		{
			this.name = name;
		}
		/**
		 * @return the name
		 */
		public String getName() 
		{
			return name;
		}
	};

	private EntityType entityType;
	private int x;
	private int y;

	public Entity(EntityType entityType, int x, int y)
	{
		this.setEntityType(entityType);
		this.setX(x);
		this.setY(y);
	}

	public Entity(EntityType entityType, int minX, int minY, int maxX, int maxY)
	{
		this.setEntityType(entityType);
		Random r = new Random();
		setX(r.nextInt(maxX - minX) + minX);
		setY(r.nextInt(maxX - minX) + minX);
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the entityType
	 */
	public EntityType getEntityType() {
		return entityType;
	}

	/**
	 * @param entityType the entityType to set
	 */
	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}
}
