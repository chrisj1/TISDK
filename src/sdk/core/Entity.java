package sdk.core;

import java.util.Random;

/**
 * An entity on the map
 * @author Chris Jerrett
 */
public class Entity
{

	/**
	 * A list of the types of entities
	 * @author Chris Jerrett
	 */
	public enum EntityType
	{
		BAT("Bat"),
		INQUISITOR("Inquisitor"),
		SKELETON("Skeleton"),
		KNIGHT("Knight");

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

	/**
	 * Constructs a new Entity
	 * @param entityType the type of the entity
	 * @param x the x pos
	 * @param y the y pos
	 */
	public Entity(EntityType entityType, int x, int y)
	{
		this.setEntityType(entityType);
		this.setX(x);
		this.setY(y);
	}

	/**
	 * Constructs a new entity in the range of x and ys
	 * @param entityType the type of the entity
	 * @param minX  the minimum x pos
	 * @param minY the minimus y pos
	 * @param maxX the max x pos
	 * @param maxY the y pos
	 */
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
