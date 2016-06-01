package sdk.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import sdk.core.Room;
import sdk.core.Room.Floors;
import sdk.core.Room.Walls;

/**
 * A class for working with buffered images and rooms
 *
 * @author Chris Jerrett
 */
public class Drawer
{

	public static final int WIDTH = 32 * 16;
	public static final int HEIGHT = 32 * 9;

	private static HashMap<String, BufferedImage> floors;
	private static HashMap<String, BufferedImage> walls;

	/**
	 * Generates a buffered image based on a room's connections and tiles
	 *
	 * @param room the room
	 * @return a buffered image of the room
	 * @throws IOException
	 */
	public static BufferedImage genBufferedImageFromRoom(Room room) throws IOException
	{
		if (walls == null || floors == null)
		{
			walls = new HashMap<String, BufferedImage>();
			floors = new HashMap<String, BufferedImage>();

			for (Walls wall : Walls.values())
			{
				walls.put(wall.name(), ImageIO.read(Drawer.class.getResourceAsStream("/assets/" + wall.file)));
			}

			for (Floors floor : Floors.values())
			{
				floors.put(floor.name(), ImageIO.read(Drawer.class.getResourceAsStream("/assets/" + floor.file)));
			}
		}

		BufferedImage bi = fillRoom(room);
		bi = outlineRoom(bi, room);
		bi = addEntrances(bi, room);

		return bi;
	}

	/**
	 * Adds the entrances to a buffered image
	 *
	 * @param bi the Room buffered Image
	 * @param room the room
	 * @return the buffedImage with entrances
	 */
	private static BufferedImage addEntrances(BufferedImage bi, Room room)
	{
		BufferedImage floor = floors.get(room.getFloor().name());

		if (room.getTop() != -1)
		{
			for (int row = 0; row < 32; row += 32)
			{
				for (int col = WIDTH / 2 - WIDTH / 8; col < WIDTH / 2 + WIDTH / 8; col += 32)
				{
					for (int iCol = 0; iCol < 32; iCol++)
					{
						for (int iRow = 0; iRow < 32; iRow++)
						{
							int x = col + iCol;
							int y = row + iRow;
							Color color = new Color(floor.getRGB(iCol, iRow));
							bi.setRGB(x, y, color.getRGB());
						}
					}
				}
			}
		}

		if (room.getBottom() != -1)
		{
			for (int row = bi.getHeight() - 32; row < bi.getHeight(); row += 32)
			{
				for (int col = WIDTH / 2 - WIDTH / 8; col < WIDTH / 2 + WIDTH / 8; col += 32)
				{
					for (int iCol = 0; iCol < 32; iCol++)
					{
						for (int iRow = 0; iRow < 32; iRow++)
						{
							int x = col + iCol;
							int y = row + iRow;
							Color color = new Color(floor.getRGB(iCol, iRow));
							bi.setRGB(x, y, color.getRGB());
						}
					}
				}
			}
		}

		if (room.getLeft() != -1)
		{
			for (int row = HEIGHT / 2 - HEIGHT / 6; row < HEIGHT / 2 + HEIGHT / 6; row += 32)
			{
				for (int col = 0; col < 32; col += 32)
				{
					for (int iCol = 0; iCol < 32; iCol++)
					{
						for (int iRow = 0; iRow < 32; iRow++)
						{
							int x = col + iCol;
							int y = row + iRow;
							Color color = new Color(floor.getRGB(iCol, iRow));
							bi.setRGB(x, y, color.getRGB());
						}
					}
				}
			}
		}

		if (room.getRight() != -1)
		{
			for (int row = HEIGHT / 2 - HEIGHT / 6; row < HEIGHT / 2 + HEIGHT / 6; row += 32)
			{
				for (int col = bi.getWidth() - 32; col < bi.getWidth(); col += 32)
				{
					for (int iCol = 0; iCol < 32; iCol++)
					{
						for (int iRow = 0; iRow < 32; iRow++)
						{
							int x = col + iCol;
							int y = row + iRow;
							Color color = new Color(floor.getRGB(iCol, iRow));
							bi.setRGB(x, y, color.getRGB());
						}
					}
				}
			}
		}
		return bi;
	}

	/**
	 * Converts a given Image into a BufferedImage
	 *
	 * @param img The Image to be converted
	 * @return The converted BufferedImage
	 */
	public static BufferedImage toBufferedImage(Image img)
	{
		if (img instanceof BufferedImage)
		{
			return (BufferedImage) img;
		}

		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		return bimage;
	}

	/**
	 * Fills a room with the floor and return a fresh bi
	 *
	 * @param the floor selected
	 * @return a new BufferedImage
	 */
	private static BufferedImage fillRoom(Room room)
	{
		BufferedImage floor = floors.get(room.getFloor().name());
		BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);

		for (int col = 0; col < WIDTH; col += 32)
		{
			for (int row = 0; row < HEIGHT; row += 32)
			{
				for (int iCol = 0; iCol < 32; iCol++)
				{
					for (int iRow = 0; iRow < 32; iRow++)
					{
						int x = col + iCol;
						int y = row + iRow;
						Color color = new Color(floor.getRGB(iCol, iRow));
						bi.setRGB(x, y, color.getRGB());
					}
				}
			}
		}
		return bi;
	}

	/**
	 * Adds the walls to a room
	 *
	 * @param bi the bi to add walls to
	 * @param room the room
	 * @return the outlined room
	 */
	private static BufferedImage outlineRoom(BufferedImage bi, Room room)
	{
		BufferedImage wall = walls.get(room.getWall().name());
		for (int col = 0; col < WIDTH; col += 32)
		{
			for (int row = 0; row < HEIGHT; row += 32)
			{
				if ((row < 32 || col < 32 || col + 32 >= WIDTH || row + 32 >= HEIGHT))
				{
					for (int iCol = 0; iCol < 32; iCol++)
					{
						for (int iRow = 0; iRow < 32; iRow++)
						{
							int x = col + iCol;
							int y = row + iRow;
							Color color = new Color(wall.getRGB(iCol, iRow));
							bi.setRGB(x, y, color.getRGB());
						}
					}
				}
			}
		}
		return bi;
	}
}
