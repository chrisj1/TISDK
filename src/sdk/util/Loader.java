package sdk.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;

import com.google.gson.Gson;

import sdk.core.Dungeon;
import sdk.core.Room;

public class Loader {

	public static final int WALL = new Color(0,0,255).getRGB();
	public static final int SPACE = new Color(0,0,0).getRGB();

	public static Dungeon loadDungeon(String filePath) {
		URI uri;
		Gson gson = null;
		String text = "";
		try {
			gson = new Gson();
			uri = Loader.class.getResource(filePath).toURI();
			List<String> lines = Files.readAllLines(Paths.get(uri),
					Charset.defaultCharset());

			for (String line : lines) {
				text+=line;
			}
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return gson.fromJson(filePath, Dungeon.class);
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

		if(room.getBotton() != null) {
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
