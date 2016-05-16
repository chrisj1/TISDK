package sdk.core;

import java.awt.image.BufferedImage;

public class Room {

	private BufferedImage image;
	private Room top;
	private Room botton;
	private Room left;
	private Room right;

	public Room() {
		this.image = null;
		this.top = null;
		this.botton = null;
		this.left = null;
		this.right = null;
	}

	public Room(BufferedImage image, Room top, Room botton, Room left, Room right) {
		this.image = image;
		this.top = top;
		this.botton = botton;
		this.left = left;
		this.right = right;
	}

	/**
	 * @return the image
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * @return the top
	 */
	public Room getTop() {
		return top;
	}
	/**
	 * @return the botton
	 */
	public Room getBotton() {
		return botton;
	}
	/**
	 * @return the left
	 */
	public Room getLeft() {
		return left;
	}
	/**
	 * @return the right
	 */
	public Room getRight() {
		return right;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	/**
	 * @param top the top to set
	 */
	public void setTop(Room top) {
		this.top = top;
	}
	/**
	 * @param botton the botton to set
	 */
	public void setBotton(Room botton) {
		this.botton = botton;
	}
	/**
	 * @param left the left to set
	 */
	public void setLeft(Room left) {
		this.left = left;
	}
	/**
	 * @param right the right to set
	 */
	public void setRight(Room right) {
		this.right = right;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "room";
	}

}
