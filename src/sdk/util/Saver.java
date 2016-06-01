package sdk.util;

import java.util.ArrayList;
import java.util.Collections;

import sdk.core.Entity;
import sdk.core.Room;
import sdk.view.RoomPanel;

/**
 * Deals with saving a dungeon
 * @author Chris Jerrett
 */
public class Saver {

	/**
	 * Saves a arrayList of rooms
	 * @param rooms
	 */
	public static void saveRooms(ArrayList<Room> rooms)
	{
		Collections.sort(rooms);
		System.out.println(genOutput(rooms));

	}

	/**
	 * Generates the .dat file with links
	 * @param rooms the rooms to save
	 * @return a string that the .dat file should contain
	 */
	private static String genOutput(ArrayList<Room> rooms)
	{
		String output = "# Dungeon layout";
		output+=System.getProperty("line.separator");
		output+=System.getProperty("line.separator");
		output+="# Width and height of the rooms in tiles";

		output+=System.getProperty("line.separator");
		output+="w 16";
		output+=System.getProperty("line.separator");
		output+="h 9";

		output+=System.getProperty("line.separator");
		output+=System.getProperty("line.separator");

		output+="# Room Links, in the order of" +
				System.getProperty("line.separator") + "# r UP DOWN LEFT RIGHT\n# Put $ to represent no link"
				+ System.getProperty("line.separator") + "# The first room number is 0"
				+ System.getProperty("line.separator") + "# Room links. start with r";
		output+=System.getProperty("line.separator");
		output+=System.getProperty("line.separator");
		for(int i = 0; i < rooms.size(); i++)
		{
			output+="r ";
			if(rooms.get(i).getTop() == -1)
			{
				output+= "$ ";
			}
			else
			{
				output += rooms.get(i).getTop() + " ";
			}

			if(rooms.get(i).getBottom() == -1)
			{
				output += "$ ";
			}
			else
			{
				output += rooms.get(i).getBottom() + " ";
			}

			if(rooms.get(i).getLeft() == -1)
			{
				output += "$ ";
			}
			else
			{
				output += rooms.get(i).getLeft() + " ";
			}

			if(rooms.get(i).getRight() == -1)
			{
				output+= "$";
			}
			else
			{
				output+= rooms.get(i).getRight();
			}
			output+=System.getProperty("line.separator");
			for(Entity ent : rooms.get(i).getEntities())
			{
				output+= "e ";
				output+= ent.getEntityType().getName() + " ";
				output+=ent.getX();
				output+=" ";
				output+=ent.getY();
				output+=System.getProperty("line.separator");
			}
		}
		return output;
	}
}
