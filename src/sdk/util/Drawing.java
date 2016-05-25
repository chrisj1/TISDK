package sdk.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import sdk.core.Room;

public class Drawing {

	public static final int WIDTH = 32*16;
	public static final int HEIGHT = 32*9;

	public static BufferedImage genBufferedImageFromRoom(Room room, int num) throws IOException
	{
		room.setTop(1);
		room.setBottom(1);
		room.setRight(1);
		room.setLeft(1);
		
		System.out.println("WIDTH: " + WIDTH);
		System.out.println("HEIGTH: " + HEIGHT);

		BufferedImage floor = ImageIO.read(Drawing.class.getResourceAsStream("/assets/floor.png"));
		BufferedImage wall = ImageIO.read(Drawing.class.getResourceAsStream("/assets/wall.png"));


		BufferedImage bi = fillRoom(floor);
		bi = outlineRoom(wall, bi);
		bi = drawNumberOnRoom(bi, num);
		bi = addEntrances(bi, room, floor);	
		bi = toBufferedImage(bi.getScaledInstance(bi.getWidth()/4, bi.getHeight()/4, BufferedImage.SCALE_SMOOTH));
		return bi;
	}
	
	private static BufferedImage addEntrances(BufferedImage bi, Room room, BufferedImage floor) 
	{
		System.out.println(bi);
			if(room.getTop() != -1)
			{
				for (int row = 0; row < 32; row+=32) 
				{
					for (int col = WIDTH/2 - WIDTH/6; col < WIDTH/2 + WIDTH/6; col+=32) 
					{
						for(int iCol = 0; iCol < 32; iCol++)
						{
							for(int iRow = 0; iRow < 32; iRow++ )
							{
								int x = col+iCol;
								int y = row+iRow;
								Color color = new Color(floor.getRGB(iCol, iRow));
								bi.setRGB(x, y, color.getRGB());
							}
						}
					}
				}
			}
			
			if(room.getBottom() != -1)
			{
				for (int row = bi.getHeight()-32; row < bi.getHeight(); row+=32) 
				{
					for (int col = WIDTH/2 - WIDTH/6; col < WIDTH/2 + WIDTH/6; col+=32) 
					{
						for(int iCol = 0; iCol < 32; iCol++)
						{
							for(int iRow = 0; iRow < 32; iRow++ )
							{
								int x = col+iCol;
								int y = row+iRow;
								Color color = new Color(floor.getRGB(iCol, iRow));
								System.out.println("X: " + x + " Y: " + y);
								bi.setRGB(x, y, color.getRGB());
							}
						}
					}
				}
			}
			
			if(room.getLeft() != -1)
			{
				for (int row = HEIGHT/2 - HEIGHT/6; row < HEIGHT/2 + HEIGHT/6; row+=32) 
				{
					for (int col = 0; col < 32; col+=32) 
					{
						for(int iCol = 0; iCol < 32; iCol++)
						{
							for(int iRow = 0; iRow < 32; iRow++ )
							{
								int x = col+iCol;
								int y = row+iRow;
								Color color = new Color(floor.getRGB(iCol, iRow));
								System.out.println("X: " + x + " Y: " + y);
								bi.setRGB(x, y, color.getRGB());
							}
						}
					}
				}
			}
			
			if(room.getLeft() != -1)
			{
				for (int row = HEIGHT/2 - HEIGHT/6; row < HEIGHT/2 + HEIGHT/6; row+=32) 
				{
					for (int col = bi.getWidth()-32; col < bi.getWidth(); col+=32) 
					{
						for(int iCol = 0; iCol < 32; iCol++)
						{
							for(int iRow = 0; iRow < 32; iRow++ )
							{
								int x = col+iCol;
								int y = row+iRow;
								Color color = new Color(floor.getRGB(iCol, iRow));
								System.out.println("X: " + x + " Y: " + y);
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

	private static BufferedImage fillRoom(BufferedImage floor)
{
		BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);

		for(int col = 0; col < WIDTH; col+=32)
		{
			for(int row = 0; row < HEIGHT; row+=32)
			{
				for(int iCol = 0; iCol < 32; iCol++)
				{
					for(int iRow = 0; iRow < 32; iRow++ )
					{
						int x = col+iCol;
						int y = row+iRow;
						Color color = new Color(floor.getRGB(iCol, iRow));
						bi.setRGB(x, y, color.getRGB());
					}
				}
			}
		}
		return bi;
	}

	private static BufferedImage outlineRoom(BufferedImage wall, BufferedImage bi)
{
		for(int col = 0; col < WIDTH; col+=32)
		{
			for(int row = 0; row < HEIGHT; row+=32)
			{
				if((row < 32 || col < 32  || col+32 >= WIDTH || row+32 >= HEIGHT))
				{
					for(int iCol = 0; iCol < 32; iCol++)
					{
						for(int iRow = 0; iRow < 32; iRow++ )
						{
							int x = col+iCol;
							int y = row+iRow;
							Color color = new Color(wall.getRGB(iCol, iRow));
							bi.setRGB(x, y, color.getRGB());
						}
					}
				}
			}
		}
		return bi;
	}

	private static BufferedImage drawNumberOnRoom(BufferedImage room, int number)
	{
		Graphics2D g2d = room.createGraphics();
		String s = String.valueOf(number);
        g2d.setPaint(Color.BLACK);
        g2d.setFont(new Font("Serif", Font.BOLD, 80));
        FontMetrics fm = g2d.getFontMetrics();
        int x = room.getWidth() - fm.stringWidth(s)-200;
        int y = fm.getHeight()+50;
        g2d.drawString(s, x, y);
        g2d.dispose();
        return room;
	}
}
