package sdk.view;

import java.awt.LayoutManager;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class IDJPanel extends JPanel {

	private int id;

	public IDJPanel(int id) {
		super();
		this.id = id;
	}

	public IDJPanel(int id, boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	public IDJPanel(int id, LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	public IDJPanel(int id, LayoutManager layout) {
		super(layout);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
