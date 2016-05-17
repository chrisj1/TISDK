package sdk.core;

public class Room {

	private Room top;
	private Room botton;
	private Room left;
	private Room right;
	private int id;

	public Room(int id) {
		this.top = null;
		this.botton = null;
		this.left = null;
		this.right = null;
		this.setId(id);
	}

	public Room(Room top, Room botton, Room left, Room right, int id) {
		this.top = top;
		this.botton = botton;
		this.left = left;
		this.right = right;
		this.setId(id);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
