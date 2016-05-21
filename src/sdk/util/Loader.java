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
	ArrayList<String> full = new ArrayList<String>();
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
	int height = 0;
	Room[][] rooms = null;
	int roomNum = 0;

	for(String line: full) {
	    if(width != 0 && height != 0 && rooms == null) {
		rooms = new Room[width][height];
	    }

	    if(line.startsWith("#")) continue;
	    if(line.startsWith("w")) {
		width = Integer.parseInt(line.substring(2));
		continue;
	    }
	    if(line.startsWith("h")) {
		height = Integer.parseInt(line.substring(2));
		continue;
	    }
	    if(line.startsWith("r")) {
		Room room = new Room(roomNum);
		int x = roomNum % width;
		int y = roomNum / width;

		line = line.substring(2);

		if(!line.startsWith("$")) {
		    String top = line.substring(0, line.charAt(' '));
		    room.setTop(Integer.parseInt(top));
		}

		line = line.substring(2);

		if(!line.startsWith("$")) {
		    String down = line.substring(0, line.charAt(' '));
		    room.setTop(Integer.parseInt(down));
		}

		line = line.substring(2);
		if(!line.startsWith("$")) {
		    String left = line.substring(0, line.charAt(' '));
		    room.setTop(Integer.parseInt(left));
		}

		line = line.substring(2);
		if(!line.startsWith("$")) {
		    String right = line.substring(0, line.charAt(' '));
		    room.setTop(Integer.parseInt(right));
		}


		rooms[x][y] = room;
		roomNum++;
	    }
	}

	return null;
    }

    public static void saveDungeon(Dungeon dungeon) {
	String output = "# Dungeon layout\n# Width and height of the rooms in tiles";

	output+="w" + dungeon.getRooms().length;
	output+="h" + dungeon.getRooms()[0].length;

	output+="\n";
	output+="# Room Links, in the order of\n# r UP DOWN LEFT RIGHT\n# Put $ to represent no link\n# The first room number is 0\n# Room links. start with r";

	for(int row = 0; row < dungeon.getRooms().length; row++) {
	    for(int col = 0; col < dungeon.getRooms()[0].length; col++) 
	    {
		output+="r ";
		if(dungeon.getRooms()[row][col].getTop() == -1) 
		{
		    output+= "$ ";
		} 
		else 
		{
		    output+= dungeon.getRooms()[row][col].getTop() + " ";				
		}
		
		if(dungeon.getRooms()[row][col].getBottom() == -1) 
		{
		    output+= "$ ";
		} 
		else 
		{
		    output+= dungeon.getRooms()[row][col].getBottom() + " ";				
		}
		
		if(dungeon.getRooms()[row][col].getLeft() == -1) 
		{
		    output+= "$ ";
		} 
		else 
		{
		    output+= dungeon.getRooms()[row][col].getLeft() + " ";				
		}
		
		if(dungeon.getRooms()[row][col].getRight() == -1) 
		{
		    output+= "$";
		} 
		else 
		{
		    output+= dungeon.getRooms()[row][col].getRight();				
		}
		output+="\n";
	    }
	}
	printToFile();
    }

    private static void printToFile()
    {
		
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
	if(room.getTop() != -1) {
	    for(int i = 7; i < 9; i++) {
		bi.setRGB(i, 0, SPACE);
	    }
	}

	if(room.getBottom() != -1) {
	    for(int i = 7; i < 9; i++) {
		bi.setRGB(i, bi.getHeight()-1, SPACE);
	    }
	}

	if(room.getLeft() != -1) {
	    for(int i = 3; i < 6; i++) {
		bi.setRGB(0, i, SPACE);
	    }
	}

	if(room.getRight() != -1) {
	    for(int i = 3; i < 6; i++) {
		bi.setRGB(bi.getWidth()-1, i, SPACE);
	    }
	}

	ImageIO.write(bi, "png", new File("text123.png"));
	return bi;

    }

}
