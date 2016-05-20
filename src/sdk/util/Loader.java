package sdk.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import sdk.core.Dungeon;
import sdk.core.Room;

import com.google.gson.Gson;

public class Loader {

	public static final int WALL = new Color(0,0,255).getRGB();
	public static final int SPACE = new Color(0,0,0).getRGB();

	public static Dungeon loadDungeon(String filePath) {
		URI url;
		ArrayList<String> full = "";
		try {
			url = Loader.class.getResource(filePath).toURI();
			File file = new File(url);
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String line = br.readLine();

			while (line != null) {
				full.add(line);
				line = br.readLine();
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return stringToDungeon(full);
	}

	private static Dungeon stringToDungeon(ArrayList<String> full) {
		int width = 0;
		int heigh = 0;
		for(String line: full) {
			if(line.startsWith("#")) continue;
			if(line.startsWith("r")) {
				//do stuff adoodles
			}
		}
		
		return null;
	}

	public static void saveDungeon(Dungeon dungeon) {
		Gson gson = new Gson();
		String json = gson.toJson(dungeon);
		System.out.println(json);
	}

	public static BufferedImage genBufferedImageFromRoom(Room room) throws IOException {
		BufferedImage bi = new BufferedImage(16, 9, BufferedImage.TYPE_INT_RGB);

		for(int row = 0; row < bi.getHeight(); row++) {
			for(int col = 0; col < bi.getWidth(); col++) {
				if(row != 0 && row != bi.getHeight()-1 && col != 0 && col != bi.getWidth()-1) {
					bi.setRGB(col, row, SPACE);
				}
				else {
					bi.setRGB(col, row, WALL);
				}
			}
		}
		if(room.getTop() != null) {
			for(int i = 7; i < 9; i++) {
				bi.setRGB(i, 0, SPACE);
			}
		}

		if(room.getBottom() != null) {
			for(int i = 7; i < 9; i++) {
				bi.setRGB(i, bi.getHeight()-1, SPACE);
			}
		}

		if(room.getLeft() != null) {
			for(int i = 3; i < 6; i++) {
				bi.setRGB(0, i, SPACE);
			}
		}

		if(room.getRight() != null) {
			for(int i = 3; i < 6; i++) {
				bi.setRGB(bi.getWidth()-1, i, SPACE);
			}
		}

		ImageIO.write(bi, "png", new File("text123.png"));
		return bi;

	}

}
