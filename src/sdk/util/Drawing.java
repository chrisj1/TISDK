package sdk.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import sdk.core.Room;

public class Drawing {

	public static final int WIDTH = 32*16;
	public static final int HEIGHT = 32*9;

	public static void main(String[] args)
	{
		Room room = new Room(10);
		try {
			genIconFromRoom(room, room.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Icon genIconFromRoom(Room room, int num) throws IOException
	{
		System.out.println("WIDTH: " + WIDTH);
		System.out.println("HEIGTH: " + HEIGHT);

		BufferedImage floor = ImageIO.read(Drawing.class.getResourceAsStream("/assets/floor.png"));
		BufferedImage wall = ImageIO.read(Drawing.class.getResourceAsStream("/assets/wall.png"));


		BufferedImage bi = fillRoom(floor);
		bi = outlineRoom(wall, bi);
		bi = drawNumberOnRoom(bi, num);

		ImageIO.write(bi, "png", new File("room123.png"));
		Icon icon = new ImageIcon(bi);
		return icon;
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
