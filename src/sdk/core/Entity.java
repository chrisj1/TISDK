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
		public String getName() {
			return name;
		}
	};

	private EntityType entityType;
	private int x;
	private int y;

	public Entity(EntityType entityType, int x, int y)
	{
		this.entityType = entityType;
		this.x = x;
		this.y = y;
	}

	public Entity(EntityType entityType, int minX, int minY, int maxX, int maxY)
	{
		this.entityType = entityType;
		Random r = new Random();
		x = r.nextInt(maxX - minX) + minX;
		y = r.nextInt(maxX - minX) + minX;
	}
}
